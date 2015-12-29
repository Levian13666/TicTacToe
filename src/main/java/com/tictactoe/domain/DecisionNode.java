package com.tictactoe.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levian on 29.12.2015.
 */
public class DecisionNode {

    private Integer nodeScore = null;
    private List<DecisionNode> nodes = new ArrayList<>();
    private GameGrid.State[][] gameState;
    private int[] step = new int[]{0,0};

    public DecisionNode(Integer nodeScore, GameGrid.State[][] gameState) {
        this.nodeScore = nodeScore;
        this.gameState = gameState;
    }

    public Integer getNodeScore() {
        return nodeScore;
    }

    public void setNodeScore(Integer nodeScore) {
        this.nodeScore = nodeScore;
    }

    public List<DecisionNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<DecisionNode> nodes) {
        this.nodes = nodes;
    }

    public GameGrid.State[][] getGameState() {
        return gameState;
    }

    public void setGameState(GameGrid.State[][] gameState) {
        this.gameState = gameState;
    }

    public int[] getStep() {
        return step;
    }

    public void setStep(int[] step) {
        this.step = step;
    }
}
