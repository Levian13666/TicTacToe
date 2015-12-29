package com.tictactoe.sevice;

import com.tictactoe.domain.DecisionNode;
import com.tictactoe.domain.DecisionTree;
import com.tictactoe.domain.GameGrid;
import com.tictactoe.domain.Pair;

import java.util.ArrayList;
import java.util.List;

public class GameService {

    public static Pair<int[], DecisionTree> makeStep(GameGrid.State[][] gameState) {
        DecisionTree decisionTree = new DecisionTree(calculate(new DecisionNode(null, gameState)));



        return new Pair<>(new int[]{0, 0}, decisionTree);
    }

    private static DecisionNode calculate(DecisionNode parentNode) {
        if (parentNode.getNodeValue().equals())
        List<Pair<Integer, Integer>> availableCoordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (parentNode.getGameState()[i][j].equals(GameGrid.State.NONE)) {
                    availableCoordinates.add(new Pair<>(i, j));
                }
            }
        }



        return parentNode;
    }

}
