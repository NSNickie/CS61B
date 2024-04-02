package ngrams;

import java.sql.Time;
import java.time.temporal.Temporal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

import edu.princeton.cs.algs4.In;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 * <p>
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    public HashMap<String, TimeSeries> wordMap;
    public TimeSeries countTimeSeries;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wordMap = new HashMap<String, TimeSeries>();
        readWordsFile(wordsFilename);
        readCountsFile(countsFilename);
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries ts = wordMap.get(word);
        if (ts == null) {
            return new TimeSeries();
        }
        return new TimeSeries(ts, startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        TimeSeries ts = wordMap.get(word);
        if (ts == null) {
            return new TimeSeries();
        }
        return new TimeSeries(ts, MIN_YEAR, MAX_YEAR);

    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return countTimeSeries;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (wordMap.get(word) == null) {
            return new TimeSeries();
        }
        return new TimeSeries(wordMap.get(word), startYear, endYear).dividedBy(countTimeSeries);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if (wordMap.get(word) == null) {
            return new TimeSeries();
        }
        return new TimeSeries(wordMap.get(word), MIN_YEAR, MAX_YEAR).dividedBy(countTimeSeries);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words, int startYear, int endYear) {
        TimeSeries sum_ts = new TimeSeries();
        for (String word : words) {
            TimeSeries word_ts = new TimeSeries(wordMap.get(word), startYear, endYear).dividedBy(countTimeSeries);
            sum_ts = sum_ts.plus(word_ts);
        }
        return sum_ts;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries sum_ts = new TimeSeries();
        for (String word : words) {
            TimeSeries word_ts = weightHistory(word);
            sum_ts = sum_ts.plus(word_ts);
        }
        return sum_ts;
    }

    private void readWordsFile(String wordsFileName) {
        In in = new In(wordsFileName);
        TimeSeries ts = new TimeSeries();
        String word = null;


        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split("\t");
            if (word == null) {
                word = splitLine[0];
            }
            String year = splitLine[1];
            String appear = splitLine[2];
            if (!Objects.equals(word, splitLine[0])) {
                wordMap.put(word, ts);
                word = splitLine[0];
                ts = new TimeSeries();
                ts.put(Integer.parseInt(year), Double.parseDouble(appear));
            } else {
                ts.put(Integer.parseInt(year), Double.parseDouble(appear));
            }
        }
        // add last word timeseries.
        wordMap.put(word, ts);
    }

    private void readCountsFile(String countsFileName) {
        In in = new In(countsFileName);
        TimeSeries ts = new TimeSeries();

        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            String year = splitLine[0];
            String count = splitLine[1];
            ts.put(Integer.parseInt(year), Double.parseDouble(count));
        }
        countTimeSeries = ts;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
