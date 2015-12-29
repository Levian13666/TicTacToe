package com.tictactoe.sevice;

import com.tictactoe.domain.DecisionNode;
import com.tictactoe.domain.DecisionTree;
import com.tictactoe.domain.GameGrid;
import com.tictactoe.domain.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameService {

    private enum Type {
        MAX(1), MIN(-1);

        private int scoreModifier;

        Type(int scoreModifier) {
            this.scoreModifier = scoreModifier;
        }

        public int getScoreModifier() {
            return scoreModifier;
        }
    }

    private static final int maxScore = 9;

    public static DecisionTree makeStep(GameGrid.State[][] gameState) {
        DecisionNode parentNode = new DecisionNode(null, gameState);
        calculate(parentNode, 0, Type.MAX);

        int[] step = null;
        for (DecisionNode node : parentNode.getNodes()) {
            if (node.getNodeScore().equals(parentNode.getNodeScore())) {
                step = node.getStep();
            }
        }

        return new DecisionTree(parentNode, step);
    }

    private static void calculate(DecisionNode parentNode, int level, Type type) {
        List<Pair<Integer, Integer>> availableCoordinates = getAvailableCoordinates(parentNode.getGameState());
        if (availableCoordinates.size() != 0) {
            for (Pair<Integer, Integer> coordinates : availableCoordinates) {
                DecisionNode decisionNode = new DecisionNode(null, copyGameState(parentNode.getGameState()));
                parentNode.getNodes().add(decisionNode);
                decisionNode.getGameState()[coordinates.getFirst()][coordinates.getSecond()] = type.equals(Type.MAX) ? GameGrid.State.CIRCLE : GameGrid.State.CROSS;
                decisionNode.setStep(new int[]{coordinates.getFirst(), coordinates.getSecond()});
                if (!GameGrid.getGameStatus(decisionNode.getGameState()).equals(GameGrid.State.NONE)) {
                    decisionNode.setNodeScore((maxScore - level) * type.getScoreModifier());
                    break;
                } else {
                    calculate(decisionNode, level + 1, type.equals(Type.MAX) ? Type.MIN : Type.MAX);
                }
            }
            DecisionNode bestNode = null;
            for (DecisionNode node : parentNode.getNodes()) {
                if (bestNode == null) {
                    bestNode = node;
                } else if ((type.equals(Type.MAX) && node.getNodeScore() > bestNode.getNodeScore())
                        || (type.equals(Type.MIN) && node.getNodeScore() < bestNode.getNodeScore())) {
                    bestNode = node;
                }
            }

            if (bestNode != null) {
                parentNode.setNodeScore(bestNode.getNodeScore());
            }
        } else {
            parentNode.setNodeScore(0);
        }
    }

    private static GameGrid.State[][] copyGameState(GameGrid.State[][] states) {
        GameGrid.State[][] result = new GameGrid.State[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(states[i], 0, result[i], 0, 3);
        }
        return result;
    }

    public static List<Pair<Integer, Integer>> getAvailableCoordinates(GameGrid.State[][] gameState) {
        List<Pair<Integer, Integer>> availableCoordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameState[i][j].equals(GameGrid.State.NONE)) {
                    availableCoordinates.add(new Pair<>(i, j));
                }
            }
        }
        return availableCoordinates;
    }

}
