package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.apache.hc.core5.annotation.Internal;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.Time;

import java.util.*;
import java.util.stream.Collectors;

public class HyponymsHandler extends NgordnetQueryHandler {
    public HashMap<Integer, GraphNode> wordMap;
    public NGramMap ngramMap;


    public HyponymsHandler(String wordsFile, String totalCountsFile, String synsetFile, String hyponymFile) {
        this.wordMap = new HashMap<Integer, GraphNode>();
        this.ngramMap = new NGramMap(wordsFile, totalCountsFile);
        this.buildGraph(synsetFile, hyponymFile);
    }

    public void buildGraph(String synsetFile, String hyponymFile) {
        In rawSynsets = new In(synsetFile);
        In rawHyponymFile = new In(hyponymFile);
        while (rawSynsets.hasNextLine()) {
            String nextLine = rawSynsets.readLine();
            String[] splitLine = nextLine.split(",");
            int id = Integer.parseInt(splitLine[0]);
            String word = splitLine[1];
            String definition = splitLine[2];
            GraphNode graphNode = new GraphNode(id, word, definition);
            this.wordMap.put(id, graphNode);
        }
        while (rawHyponymFile.hasNextLine()) {
            String nextLine = rawHyponymFile.readLine();
            String[] splitLine = nextLine.split(",");
            int id = Integer.parseInt(splitLine[0]);
            for (int i = 1; i < splitLine.length; i++) {
                String hyponymId = splitLine[i];
                wordMap.get(id).addHyponym(wordMap.get(Integer.parseInt(hyponymId)));
            }
        }
    }

    public String[] findHyponyms(List<String> words) {
        HashSet<Integer> hyponymsSet = this.findHyponymsIds(words.get(0));
        for (String word : words) {
            hyponymsSet.retainAll(this.findHyponymsIds(word));
        }

        HashSet<String> hyponymsWordSet = new HashSet<String>();
        for (Integer id : hyponymsSet) {
            String mapWords = this.wordMap.get(id).word;
            String[] splitWords = mapWords.split(" ");
            hyponymsWordSet.addAll(Arrays.asList(splitWords));
        }
        String[] result = hyponymsWordSet.toArray(new String[0]);

        Arrays.sort(result);
        return result;

    }

    public HashSet<Integer> findHyponymsIds(String word) {
        HashSet<Integer> result = new HashSet<Integer>();

        for (GraphNode node : this.wordMap.values()) {
            //split the words, find if target word exists
            String[] words = node.word.split(" ");
            for (String splitWord : words) {
                if (splitWord.equals(word)) {
                    findHyponymsByDFS(node, result);
                    break;
                }
            }
        }
        return result;
    }

    public void findHyponymsByDFS(GraphNode node, HashSet<Integer> set) {
        set.add(node.id);
        for (GraphNode hyponymNode : node.hyponymSet) {
            findHyponymsByDFS(hyponymNode, set);
        }
    }


    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        if (k != 0) {
            HashMap<String, Double> countMap = new HashMap<String, Double>();
            String[] hyponyms = this.findHyponyms(words);

            for (String hyponym : hyponyms) {
                TimeSeries ts = this.ngramMap.countHistory(hyponym, startYear, endYear);
                Double sum = 0.0;
                for (Double value : ts.values()) {
                    sum += value;
                }
                countMap.put(hyponym, sum);
            }
            List<String> topKKeys = countMap.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .limit(k)
                    .map(Map.Entry::getKey)
                    .toList();
            String[] result = topKKeys.toArray(new String[0]);
            Arrays.sort(result);
            return Arrays.toString(result) ;
        }

        return Arrays.toString(this.findHyponyms(q.words()));
    }


}
