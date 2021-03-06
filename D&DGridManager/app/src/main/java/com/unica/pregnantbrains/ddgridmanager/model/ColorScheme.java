package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Color;

import java.io.Serializable;

public final class ColorScheme implements Serializable {
    private static final long serialVersionUID = 7366712691951842828L;

    public static final ColorScheme STANDARD = new ColorScheme(Color.rgb(250,250,250), Color.rgb(117, 117, 117));

    private int backgroundColor;
    private int lineColor;

    public ColorScheme(int backgroundColor, int lineColor) {
        this.backgroundColor = backgroundColor;
        this.lineColor = lineColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getLineColor() {
        return lineColor;
    }
}
