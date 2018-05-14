package com.partinin.app.model;

/**
 * Point class contains the coordinates of the cell on the field.
 */

public class Point {

    private int x;
    private int y;

    public Point(int xCoordinate, int yCoordinate) {
        this.x = xCoordinate;
        this.y = yCoordinate;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
