package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Color;

public final class ColorScheme {
    /**
     * Dark Grey on white.
     */
    public static final ColorScheme STANDARD = new ColorScheme(Color.rgb(250, 250, 250), Color.rgb(117, 117, 117));
    /**
     * The color to draw in the background.
     */
    private final int mBackgroundColor;
    /**
     * The color to draw grid lines with.
     */
    private final int mLineColor;
    /**
     * Given the name of a color scheme, returns the scheme represented by that
     * name. If the scheme is not found, returns the standard grey-on-white
     * color scheme.
     *
     * @param //name
     *            The name of the scheme to use.
     * @return The color scheme.
     */
    public static ColorScheme fromNamedScheme(/*final String name*/) {
        /*if (name.equals("Graph Paper")) {
            return GRAPH_PAPER;
        }
        if (name.equals("Grass")) {
            return GRASS;
        }
        if (name.equals("Ice")) {
            return ICE;
        }
        if (name.equals("Forest")) {
            return FOREST;
        }
        if (name.equals("Night")) {
            return NIGHT;
        }
        if (name.equals("Dungeon")) {
            return DUNGEON;
        }
        if (name.equals("Hologram")) {
            return HOLOGRAM;
        }
        if (name.equals("Console")) {
            return CONSOLE;
        }*/
        return STANDARD;
    }
    /**
     * Constructor.
     *
     * @param backgroundColor
     *            The color to draw in the background.
     * @param lineColor
     *            The color to draw grid lines with.
     */
    public ColorScheme(final int backgroundColor, final int lineColor) {
        this.mBackgroundColor = backgroundColor;
        this.mLineColor = lineColor;
    }
    /**
     * @return The color to draw in the background.
     */
    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    /**
     * @return The color to draw grid lines with.
     */
    public int getLineColor() {
        return this.mLineColor;
    }
}
