package com.tictactoe.domain;

/**
 * Created by Levian on 29.12.2015.
 */
public class DecisionTree {

    private DecisionNode mainNode;

    public DecisionTree(DecisionNode mainNode) {
        this.mainNode = mainNode;
    }

    public DecisionNode getMainNode() {
        return mainNode;
    }

    public void setMainNode(DecisionNode mainNode) {
        this.mainNode = mainNode;
    }
}
