package com.partinin.app.model;

/**
 * BigField class is main class of the programme it contains multiple
 * instances of Field class.
 */

public class BigField {
    private static final int COUNT_OF_CELL = 3;
    private Field[][] bigFieldCells = new Field[3][3];

    public BigField() {
        generateCellsFromFields();
    }

    private void generateCellsFromFields() {
        for (int i = 0; i < COUNT_OF_CELL; i++) { // Vertical cells
            for (int j = 0; j < COUNT_OF_CELL; j++) { // Horizontal cells
                bigFieldCells[i][j] = new Field();
            }
        }
    }

    public Field getSmallField(Point position) {
        return bigFieldCells[position.getX()][position.getY()];
    }

    void blockOtherFields(Point position) {
        if (bigFieldCells[position.getX()][position.getY()].getWhoseWon() == ' ') {
            for (int i = 0; i < COUNT_OF_CELL; i++) {
                for (int j = 0; j < COUNT_OF_CELL; j++) {
                    bigFieldCells[i][j].setBlocked(true);
                }
            }
            bigFieldCells[position.getX()][position.getY()].setBlocked(false);
        } else {
            for (int i = 0; i < COUNT_OF_CELL; i++) {
                for (int j = 0; j < COUNT_OF_CELL; j++) {
                    bigFieldCells[i][j].setBlocked(false);
                }
            }
        }
    }

    public void changeTurn() {
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                bigFieldCells[i][j].changeTurn();
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
            if (bigFieldCells[i][0].getWhoseWon() == token
                    && bigFieldCells[i][1].getWhoseWon() == token
                    && bigFieldCells[i][2].getWhoseWon() == token) {
                return true;
            }

        // Vertical lines
        for (int j = 0; j < COUNT_OF_CELL; j++)
            if (bigFieldCells[0][j].getWhoseWon() == token
                    && bigFieldCells[1][j].getWhoseWon() == token
                    && bigFieldCells[2][j].getWhoseWon() == token) {
                return true;
            }

        // Left-right diagonal
        if (bigFieldCells[0][0].getWhoseWon() == token
                && bigFieldCells[1][1].getWhoseWon() == token
                && bigFieldCells[2][2].getWhoseWon() == token) {
            return true;
        }

        // Right-left diagonal
        if (bigFieldCells[0][2].getWhoseWon() == token
                && bigFieldCells[1][1].getWhoseWon() == token
                && bigFieldCells[2][0].getWhoseWon() == token) {
            return true;
        }

        return false;
    }
}
