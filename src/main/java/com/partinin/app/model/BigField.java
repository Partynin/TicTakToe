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

    public void checkWinner() {

    }
}
