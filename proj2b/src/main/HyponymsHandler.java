package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import edu.princeton.cs.algs4.In;
import org.apache.hc.core5.annotation.Internal;

import java.util.ArrayList;
import java.util.HashMap;

public class HyponymsHandler extends NgordnetQueryHandler {
    public HashMap<Integer, GraphNode> wordMap;
    public HashMap<String, GraphNode> wordMapWithStringKey;


    public HyponymsHandler(String synsetFile, String hyponymFile) {
        this.wordMap = new HashMap<Integer, GraphNode>();
        this.wordMapWithStringKey = new HashMap<String, GraphNode>();
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
            this.wordMapWithStringKey.put(word, graphNode);
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

    public ArrayList<String> findHyponyms(String word) {
        ArrayList<String> result = new ArrayList<String>();
        GraphNode graphNode = this.wordMapWithStringKey.get(word);
        if (graphNode == null) {
            return null;
        }
        result.add(word);
        for (GraphNode node : graphNode.hyponymSet) {
            result.add(node.word);
        }

        return result;
    }


    @Override
    public String handle(NgordnetQuery q) {
        System.out.println(q.words().get(0));
        return this.findHyponyms(q.words().get(0)).toString();
    }

}
