package com.tictactoe.controllers;

import com.tictactoe.Application;
import com.tictactoe.domain.DecisionTree;
import com.tictactoe.domain.GameGrid;
import com.tictactoe.domain.HttpEntity;
import com.tictactoe.sevice.GameService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.crypto.Des;

@RestController
@RequestMapping("rest")
public class WebRestController {

    @RequestMapping("/step")
    public HttpEntity<DecisionTree> makeStep(
            @RequestParam int x,
            @RequestParam int y
    ) {
        Application.GAME_GRID.getGrid()[x][y] = GameGrid.State.CROSS;
        DecisionTree decisionTree;
        if (GameGrid.getGameStatus(Application.GAME_GRID.getGrid()) == GameGrid.State.NONE) {
            decisionTree = GameService.makeStep(Application.GAME_GRID.getGrid());
            Application.GAME_GRID.getGrid()[decisionTree.getStep()[0]][decisionTree.getStep()[1]] = GameGrid.State.CIRCLE;
            if (GameGrid.getGameStatus(Application.GAME_GRID.getGrid()) != GameGrid.State.NONE) {
                decisionTree.setWinner(GameGrid.State.CIRCLE);
                decisionTree.setWinCoordinates(GameGrid.getWinnerCoordinates(Application.GAME_GRID.getGrid()));
            }
        } else {
            decisionTree = new DecisionTree(null);
            decisionTree.setWinner(GameGrid.State.CROSS);
            decisionTree.setWinCoordinates(GameGrid.getWinnerCoordinates(Application.GAME_GRID.getGrid()));
        }
        return new HttpEntity<>(decisionTree);
    }
    
    @RequestMapping("/reset")
    public void reset() {
        GameGrid.State[][] grid = {
                {GameGrid.State.NONE, GameGrid.State.NONE, GameGrid.State.NONE},
                {GameGrid.State.NONE, GameGrid.State.NONE, GameGrid.State.NONE},
                {GameGrid.State.NONE, GameGrid.State.NONE, GameGrid.State.NONE}
        };
        Application.GAME_GRID.setGrid(grid);
    }

}
