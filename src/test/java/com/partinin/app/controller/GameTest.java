package com.partinin.app.controller;

import com.partinin.app.model.BigField;
import com.partinin.app.view.BigFieldView;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testGetBigField() {
        BigField bigFieldExpected = Game.getBigField();

        assertNull(bigFieldExpected);
    }

    @Test
    public void testGetBigFieldView() {
        BigFieldView bigFieldView = Game.getBigFieldView();

        assertNull(bigFieldView);
    }
}
