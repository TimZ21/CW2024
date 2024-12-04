package com.example.demo.actors.manager;

import javafx.stage.Screen;

public class ScaleUtils {
    // Default scale factor; update this during application start or when needed
    public static double scale = Screen.getPrimary().getOutputScaleX();
    public static int incrementX = 0;
    public static int incrementY = 0;
    public static void updateScale() {
        if (scale == 1) {
            incrementX = -650;
            incrementY = 0;
        }
        else if (scale == 1.25) {
            incrementX = -300;
            incrementY = -50;
        }
    }
    public static double scaleXRelocate(double value) {
        return value + incrementX;
    }
    public static double scaleYRelocate(double value) {
        return value + incrementY;
    }
}
