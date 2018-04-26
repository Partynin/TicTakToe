package com.partinin.app.model;

/**
 * BigField class is main class of the programme it contains multiple
 * instances of Field class.
 */

public class BigField {
    private static final int COUNT_OF_CELL = 3;
    private Field[][] bigField = new Field[3][3];

    public BigField() {
        generateSmallFields();
    }

    private void generateSmallFields() {
        for (int i = 0; i < COUNT_OF_CELL; i++) { // Vertical cells
            for (int j = 0; j < COUNT_OF_CELL; j++) { // Horizontal cells
                bigField[i][j] = new Field();
            }
        }
    }

    public Field getSmallField(Point position) {
        return bigField[position.getX()][position.getY()];
    }

    void blockOtherFields(Point position) {
        if (bigField[position.getX()][position.getY()].getWhoseWon() == ' ') {
            for (int i = 0; i < COUNT_OF_CELL; i++) {
                for (int j = 0; j < COUNT_OF_CELL; j++) {
                    bigField[i][j].setBlocked(true);
                }
            }
            bigField[position.getX()][position.getY()].setBlocked(false);
        } else {
            for (int i = 0; i < COUNT_OF_CELL; i++) {
                for (int j = 0; j < COUNT_OF_CELL; j++) {
                    bigField[i][j].setBlocked(false);
                }
            }
        }
    }

    public void changeTurn() {
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                bigField[i][j].changeTurn();
            }
        }
    }

    public void checkWinner() {

    }

    /**
     * Determines if the player with the specified token wins
     */
    private boolean isWon(char token) {
        // Horizon lines
        for (int i = 0; i < COUNT_OF_CELL; i++)
            if (bigField[i][0].getWhoseWon() == token
                    && bigField[i][1].getWhoseWon() == token
                    && bigField[i][2].getWhoseWon() == token) {
                return true;
            }

        // Vertical lines
        for (int j = 0; j < COUNT_OF_CELL; j++)
            if (bigField[0][j].getWhoseWon() == token
                    && bigField[1][j].getWhoseWon() == token
                    && bigField[2][j].getWhoseWon() == token) {
                return true;
            }

        // Left-right diagonal
        if (bigField[0][0].getWhoseWon() == token
                && bigField[1][1].getWhoseWon() == token
                && bigField[2][2].getWhoseWon() == token) {
            return true;
        }

        // Right-left diagonal
        if (bigField[0][2].getWhoseWon() == token
                && bigField[1][1].getWhoseWon() == token
                && bigField[2][0].getWhoseWon() == token) {
            return true;
        }

        return false;
    }
}
