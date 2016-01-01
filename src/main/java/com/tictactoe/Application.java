package com.tictactoe;

import com.tictactoe.domain.DecisionTree;
import com.tictactoe.domain.GameGrid;
import com.tictactoe.domain.Pair;
import com.tictactoe.sevice.GameService;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class Application {

    public static final GameGrid GAME_GRID = new GameGrid();

    private static Logger LOG = Logger.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equals("web")) {
            SpringApplication.run(Application.class, args);
        } else {
            GameGrid gameGrid = new GameGrid();

            GameGrid.printGrid(gameGrid.getGrid());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String input = "";
            while (!input.equals("q")) {
                input = reader.readLine();
                if (!input.equals("q")) {
                    try {
                        gameGrid.getGrid()[Integer.valueOf(input.split(",")[0])][Integer.valueOf(input.split(",")[1])] = GameGrid.State.CROSS;
                        GameGrid.printGrid(gameGrid.getGrid());
                        if (GameService.getAvailableCoordinates(gameGrid.getGrid()).size() == 0) {
                            break;
                        }
                        if (GameGrid.getGameStatus(gameGrid.getGrid()).equals(GameGrid.State.NONE)) {
                            LOG.info("Please wait...");
                            DecisionTree decisionTree = GameService.makeStep(gameGrid.getGrid());
                            gameGrid.getGrid()[decisionTree.getStep()[0]][decisionTree.getStep()[1]] = GameGrid.State.CIRCLE;
                            GameGrid.printGrid(gameGrid.getGrid());
                            if (!GameGrid.getGameStatus(gameGrid.getGrid()).equals(GameGrid.State.NONE)) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        LOG.info("Incorrect input...");
                    }
                }
            }
        }
    }
}
