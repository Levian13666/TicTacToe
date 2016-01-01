package com.tictactoe.domain;

/**
 * Created by Levian on 29.12.2015.
 */
public class DecisionTree {

    private int winCoordinates[] = new int[4];
    private GameGrid.State winner = null;
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

    public GameGrid.State getWinner() {
        return winner;
    }

    public void setWinner(GameGrid.State winner) {
        this.winner = winner;
    }

    public int[] getWinCoordinates() {
        return winCoordinates;
    }

    public void setWinCoordinates(int[] winCoordinates) {
        this.winCoordinates = winCoordinates;
    }
}
