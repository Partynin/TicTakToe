package com.partinin.app.model;

/** BigField class is main class of the programme it contains multiple
 * instances of Field class. */

public class BigField {
    public static final int COUNT_OF_CELL = 3;
    private Field[][] bigField = new Field[3][3];

    public BigField() {
        fillBigCells();
    }

    private void fillBigCells() {
        for (int i = 0; i < COUNT_OF_CELL; i++) { // Vertical cells
            for (int j = 0; j < COUNT_OF_CELL; i++) { // Horizontal cells
                bigField[i][j] = new Field();
            }
        }
    }

    public Field getSmallField(Point position) {
        return bigField[position.getX()][position.getY()];
    }
}
