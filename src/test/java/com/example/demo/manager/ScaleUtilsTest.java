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
    void testScaleRelocateAtScale1() {
        ScaleUtils.scale = 1;
        ScaleUtils.updateScale();

        double originalX = 100;
        double originalY = 50;

        assertEquals(originalX + -650, ScaleUtils.scaleXRelocate(originalX));
        assertEquals(originalY, ScaleUtils.scaleYRelocate(originalY));
    }

    @Test
    void testScaleRelocateAtScale1_25() {
        ScaleUtils.scale = 1.25;
        ScaleUtils.updateScale();

        double originalX = 100;
        double originalY = 50;

        assertEquals(originalX + -300, ScaleUtils.scaleXRelocate(originalX));
        assertEquals(originalY - 50, ScaleUtils.scaleYRelocate(originalY));
    }

    @Test
    void testScaleRelocateAtScale1_5() {
        ScaleUtils.scale = 1.5;
        ScaleUtils.updateScale();

        double originalX = 100;
        double originalY = 50;

        assertEquals(originalX, ScaleUtils.scaleXRelocate(originalX));
        assertEquals(originalY, ScaleUtils.scaleYRelocate(originalY));
    }
}
