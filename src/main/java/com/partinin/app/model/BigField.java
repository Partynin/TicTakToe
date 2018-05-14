package com.partinin.app.model;

/**
 * The BigField class is the main class of the programme, in which it contains
 * multiple instances of the Field class.
 */

public class BigField implements ConstantsTicTakToe {

    private Field[][] bigFieldCells;

    public BigField() {
        bigFieldCells = new Field[COUNT_OF_CELL][COUNT_OF_CELL];
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

    void changeTurn() {
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                bigFieldCells[i][j].changeTurn();
            }
        }
    }

    public void checkWinnerOnBigField() {
        if (checkWinnerOnBigField('X') || checkWinnerOnBigField('O')) {
            for (int i = 0; i < COUNT_OF_CELL; i++) {
                for (int j = 0; j < COUNT_OF_CELL; j++) {
                    bigFieldCells[i][j].setBlocked(true);
                }
            }
        }
    }

    private boolean checkWinnerOnBigField(char token) {
        // Horizon lines
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            if (isWinnerOnHorizonLine(i, token)) {
                return true;
            }
        }

        // Vertical lines
        for (int j = 0; j < COUNT_OF_CELL; j++) {
            if (isWinnerOnVerticalLine(j, token)) {
                return true;
            }
        }

        // Left-right diagonal
        if (isWinnerOnLeftRightDiagonal(token)) {
            return true;
        }

        // Right-left diagonal
        return isWinnerOnRightLeftDiagonal(token);
    }

    private boolean isWinnerOnHorizonLine(int i, char token) {
        return (bigFieldCells[i][0].getWhoseWon() == token
                && bigFieldCells[i][1].getWhoseWon() == token
                && bigFieldCells[i][2].getWhoseWon() == token);
    }

    private boolean isWinnerOnVerticalLine(int j, char token) {
        return (bigFieldCells[0][j].getWhoseWon() == token
                && bigFieldCells[1][j].getWhoseWon() == token
                && bigFieldCells[2][j].getWhoseWon() == token);
    }

    private boolean isWinnerOnLeftRightDiagonal(char token) {
        return (bigFieldCells[0][0].getWhoseWon() == token
                && bigFieldCells[1][1].getWhoseWon() == token
                && bigFieldCells[2][2].getWhoseWon() == token);
    }

    private boolean isWinnerOnRightLeftDiagonal(char token) {
        return (bigFieldCells[0][2].getWhoseWon() == token
                && bigFieldCells[1][1].getWhoseWon() == token
                && bigFieldCells[2][0].getWhoseWon() == token);
    }
}
