package com.tictactoe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.log4j.Logger;

public class GameGrid {

    private static Logger LOG = Logger.getLogger(GameGrid.class);

    public enum State {
        NONE (0, " "),
        CIRCLE (-1, "0"),
        CROSS (1, "X");

        private int value;
        private String label;

        State(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }
    }

    private State[][] grid = {
        {State.NONE, State.NONE, State.NONE},
        {State.NONE, State.NONE, State.NONE},
        {State.NONE, State.NONE, State.NONE}
    };

    public State[][] getGrid() {
        return grid;
    }

    public void setGrid(State[][] grid) {
        this.grid = grid;
    }

    @JsonIgnore
    public static void printGrid(State[][] grid) {
        LOG.info("");
        for (int i = 0; i < 3; i++) {
            LOG.info(String.format(
                    "%s|%s|%s",
                    grid[i][0].getLabel(),
                    grid[i][1].getLabel(),
                    grid[i][2].getLabel()
            ));
            LOG.info(i < 2 ? "-----" : "");
        }
        State gameState = getGameStatus(grid);
        LOG.info(String.format("Game State: %s", gameState.equals(State.NONE) ? "-" : "Winner - " + gameState.getLabel()));
    }

    /**
     * If game ended returns type of winner ('State.CIRCLE'||'State.CROSS') and 'State.None' in another way
     *
     * Grid:
     * 0,0 0,1 0,2
     * 1,0 1,1 1,2
     * 2,0 2,1 2,2
     *
     */
    @JsonIgnore
    public static State getGameStatus(State[][] grid) {
        //pwc - possible winning coordinates
        int[][][] pwc = {
                {{0,0}, {0,1}, {0,2}}, {{1,0}, {1,1}, {1,2}}, {{2,0}, {2,1}, {2,2}},
                {{0,0}, {1,0}, {2,0}}, {{0,1}, {1,1}, {2,1}}, {{0,2}, {1,2}, {2,2}},
                {{0,0}, {1,1}, {2,2}}, {{0,2}, {1,1}, {2,0}}
        };
        int result = 0;
        for (int[][] aPwc : pwc) {
            result = grid[aPwc[0][0]][aPwc[0][1]].getValue()
                    + grid[aPwc[1][0]][aPwc[1][1]].getValue()
                    + grid[aPwc[2][0]][aPwc[2][1]].getValue();
            if (result == -3 || result == 3) {
                break;
            } else {
                result = 0;
            }
        }
        return result == 0 ? State.NONE : result == -3 ? State.CIRCLE : State.CROSS;
    }

    @JsonIgnore
    public static int[] getWinnerCoordinates(State[][] grid) {
        int[] winnerCoordinates = new int[4];
        //pwc - possible winning coordinates
        int[][][] pwc = {
                {{0,0}, {0,1}, {0,2}}, {{1,0}, {1,1}, {1,2}}, {{2,0}, {2,1}, {2,2}},
                {{0,0}, {1,0}, {2,0}}, {{0,1}, {1,1}, {2,1}}, {{0,2}, {1,2}, {2,2}},
                {{0,0}, {1,1}, {2,2}}, {{0,2}, {1,1}, {2,0}}
        };
        for (int[][] aPwc : pwc) {
            int result = grid[aPwc[0][0]][aPwc[0][1]].getValue()
                    + grid[aPwc[1][0]][aPwc[1][1]].getValue()
                    + grid[aPwc[2][0]][aPwc[2][1]].getValue();
            if (result == -3 || result == 3) {
                winnerCoordinates[0] = aPwc[0][0];
                winnerCoordinates[1] = aPwc[0][1];
                winnerCoordinates[2] = aPwc[2][0];
                winnerCoordinates[3] = aPwc[2][1];
                break;
            }
        }
        return winnerCoordinates;
    }

}
