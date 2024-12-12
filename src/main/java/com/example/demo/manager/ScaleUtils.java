package com.example.demo.manager;

import javafx.stage.Screen;

/**
 * This {@code ScaleUtils} provides utility methods to handle scaling and positioning of UI components
 * based on the current screen scale factor. This is useful for adapting UI
 * elements to different display resolutions and scaling settings.
 *
 * <p>
 * See the source code at <a href=https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/manager/ScaleUtils.java">ScaleUtils.java</a>
 */
public class ScaleUtils {
    /**
     * The system using scale factor, determined by the primary screen's output scale.
     */
    public static double scale = Screen.getPrimary().getOutputScaleX();

    /**
     * Horizontal offset to adjust component positioning based on the scale factor.
     */
    public static double incrementX = 0;

    /**
     * Vertical offset to adjust component positioning based on the scale factor.
     */
    public static double incrementY = 0;

    /**
     * Updates the scale-related offsets based on the current screen scale factor.
     * This method adjusts {@code incrementX} and {@code incrementY} to ensure
     * elements are positioned appropriately across different scaling settings.
     */

    // 450 / scale
    public static void updateScale() {
        if (scale == 1.25) {
            incrementX = 360;
            incrementY = -100;
        } else if (scale == 1.5) {
            incrementX = 625;
            incrementY = -300;
        }
    }
}
