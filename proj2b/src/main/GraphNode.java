package main;

import java.util.HashSet;

public class GraphNode {
    public int id;
    public String word;
    public String definition;
    public HashSet<GraphNode> hyponymSet;

    public GraphNode(int id, String word, String definition) {
        this.id = id;
        this.word = word;
        this.definition = definition;
        this.hyponymSet = new HashSet<GraphNode>();
    }

    public void addHyponym(GraphNode node){
        this.hyponymSet.add(node);
    }

}
