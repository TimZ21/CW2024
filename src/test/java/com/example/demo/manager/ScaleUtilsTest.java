package com.example.demo.manager;

import com.example.demo.manager.ScaleUtils;
import javafx.stage.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link ScaleUtils}.
 */
class ScaleUtilsTest {

    @BeforeEach
    void setUp() {
        // Reset scale and increments before each test
        ScaleUtils.scale = Screen.getPrimary().getOutputScaleX(); // This needs to be adjusted if running headless or in environments without a screen.
        ScaleUtils.incrementX = 0;
        ScaleUtils.incrementY = 0;
    }

    @Test
    void testIncrementXAtScale1() {
        ScaleUtils.scale = 1;
        ScaleUtils.updateScale();

        assertEquals(ScaleUtils.incrementX, 0);
    }

    @Test
    void testIncrementYAtScale1() {
        ScaleUtils.scale = 1;
        ScaleUtils.updateScale();

        assertEquals(ScaleUtils.incrementY, 0);
    }

    @Test
    void testIncrementXAtScale125() {
        ScaleUtils.scale = 1.25;
        ScaleUtils.updateScale();

        assertEquals(ScaleUtils.incrementX, 360);
    }

    @Test
    void testIncrementYAtScale125() {
        ScaleUtils.scale = 1.25;
        ScaleUtils.updateScale();

        assertEquals(ScaleUtils.incrementY, -100);
    }

    @Test
    void testIncrementXAtScale150() {
        ScaleUtils.scale = 1.50;
        ScaleUtils.updateScale();

        assertEquals(ScaleUtils.incrementX, 625);
    }

    @Test
    void testIncrementYAtScale150() {
        ScaleUtils.scale = 1.50;
        ScaleUtils.updateScale();

        assertEquals(ScaleUtils.incrementY, -300);
    }


}
