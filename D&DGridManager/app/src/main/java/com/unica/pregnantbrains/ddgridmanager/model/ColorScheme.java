package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Color;

public class ColorScheme {
    public static final ColorScheme STANDARD = new ColorScheme(Color.rgb(250,250,250), Color.rgb(117, 117, 117));

    private int backgroundColor;
    private int lineColor;

    public ColorScheme(int backgroundColor, int lineColor) {
        this.backgroundColor = backgroundColor;
        this.lineColor = lineColor;
    }

    int getBackgroundColor() {
        return backgroundColor;
    }

    int getLineColor() {
        return lineColor;
    }
}
