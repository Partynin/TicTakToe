package com.partinin.app.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void isWon() {
        Field field = new Field();
        boolean actual;
        actual = field.isWon('X');
        assertFalse(actual);
    }
}