package com.example.demo.manager;

import javafx.stage.Screen;

/**
 * This {@code ScaleUtils} provides utility methods to handle scaling and positioning of UI components
 * based on the current screen scale factor. This is useful for adapting UI
 * elements to different display resolutions and scaling settings.
 */
public class ScaleUtils {
    /**
     * The system using scale factor, determined by the primary screen's output scale.
     */
    public static double scale = Screen.getPrimary().getOutputScaleX();

    /**
     * Horizontal offset to adjust component positioning based on the scale factor.
     */
    public static int incrementX = 0;

    /**
     * Vertical offset to adjust component positioning based on the scale factor.
     */
    public static int incrementY = 0;

    /**
     * Updates the scale-related offsets based on the current screen scale factor.
     * This method adjusts {@code incrementX} and {@code incrementY} to ensure
     * elements are positioned appropriately across different scaling settings.
     */
    public static void updateScale() {
        if (scale == 1) {
            incrementX = -650;
            incrementY = 0;
        } else if (scale == 1.25) {
            incrementX = -300;
            incrementY = -50;
        }
    }

    /**
     * Adjusts a horizontal position value according to the current scale increment.
     *
     * @param value The original horizontal position.
     * @return The adjusted horizontal position.
     */
    public static double scaleXRelocate(double value) {
        return value + incrementX;
    }

    /**
     * Adjusts a vertical position value according to the current scale increment.
     *
     * @param value The original vertical position.
     * @return The adjusted vertical position.
     */
    public static double scaleYRelocate(double value) {
        return value + incrementY;
    }
}
