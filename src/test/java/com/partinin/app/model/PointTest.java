package com.partinin.app.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void testGetXReturnValue1() {
        int x = 1;
        int y = 1;

        int expectedValue = 1;

        Point point = new Point(x, y);

        int actualValue = point.getX();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetYReturnValue1() {
        int x = 1;
        int y = 1;

        int expectedValue = 1;

        Point point = new Point(x, y);

        int actualValue = point.getY();

        assertEquals(expectedValue, actualValue);
    }
}
