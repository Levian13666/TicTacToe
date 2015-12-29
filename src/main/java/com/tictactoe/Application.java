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
                        LOG.info("Please wait...");
                        Pair<int[], DecisionTree> result = GameService.makeStep(gameGrid.getGrid());
                        gameGrid.getGrid()[result.getFirst()[0]][result.getFirst()[1]] = GameGrid.State.CIRCLE;
                        GameGrid.printGrid(gameGrid.getGrid());
                    } catch (NumberFormatException e) {
                        LOG.info("Incorrect input...");
                    }
                }
            }
        }
    }
}
