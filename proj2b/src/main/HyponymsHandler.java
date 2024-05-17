package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.HashMap;

public class HyponymsHandler extends NgordnetQueryHandler {
    public HashMap<String,String[]> wordMap;
    public HyponymsHandler() {


    }

    @Override
    public String handle(NgordnetQuery q) {
        return "hello!";
    }

//    public class GraphNode {
//        public int id;
//        public String word;
//        public String definition;
//
//        public GraphNode(int id, String word, String definition) {
//            this.id = id;
//            this.word = word;
//            this.definition = definition;
//        }
//    }
}
