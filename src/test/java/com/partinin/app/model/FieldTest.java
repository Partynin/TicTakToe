package com.partinin.app.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void testGetWhoseWonAfterInitial() {
        char expectedValue = ' ';

        Field testField = new Field();

        char actualFieldValue = testField.getWhoseWon();

        assertEquals(expectedValue, actualFieldValue);
    }
}
