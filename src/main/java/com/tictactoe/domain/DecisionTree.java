package com.tictactoe.domain;

/**
 * Created by Levian on 29.12.2015.
 */
public class DecisionTree {

    private DecisionNode mainNode;
    private int step[] = new int[2];

    public DecisionTree(DecisionNode mainNode) {
        this.mainNode = mainNode;
    }

    public DecisionTree(DecisionNode mainNode, int[] step) {
        this.mainNode = mainNode;
        this.step = step;
    }

    public DecisionNode getMainNode() {
        return mainNode;
    }

    public void setMainNode(DecisionNode mainNode) {
        this.mainNode = mainNode;
    }

    public int[] getStep() {
        return step;
    }

    public void setStep(int[] step) {
        this.step = step;
    }
}
