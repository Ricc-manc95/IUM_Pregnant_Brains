package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Canvas;
import android.graphics.PointF;

public class Grid {
    /**
            * The color scheme to use when drawing this grid.
     */
    private ColorScheme mColorScheme = ColorScheme.STANDARD;

    private DrawStrategy mDrawStrategy = new DrawStrategy();

    private String mUnits = "ft";
    private float mScale = 5;
    /**
     * The transformation from grid space to world space. We track this
     * seperately as a property of the grid so that the grid can easily be
     * resized to fit a drawing.
     */
    private CoordinateTransformer mGridToWorldTransformer = new CoordinateTransformer(0, 0, 1);

    /**
     * Default constructor
     */
    public Grid() {

    }
    /**
     * Factory method that creates a grid with the given parameters.
     *
     * @param strategy
     *            The style of the grid.
     * @param colorScheme
     *            The GridColorScheme object to use.
     * @param transformer
     *            A grid space to world space transformation to use in this
     *            grid.
     * @return The created grid.
     */
    public static Grid createGrid(final DrawStrategy strategy, final ColorScheme colorScheme, final CoordinateTransformer transformer) {
        Grid g = new Grid();
        g.mDrawStrategy = strategy;
        g.mColorScheme = colorScheme;
        g.mGridToWorldTransformer = transformer;
        return g;
    }
    /**
     * Draws the grid on the given canvas.
     *
     * @param canvas
     *            The canvas to draw on.
     * @param transformer
     *            World space to screen space transformer (not grid to screen,
     *            since grid to world is defined in this class).
     */
    public final void draw(final Canvas canvas, final CoordinateTransformer transformer) {
        CoordinateTransformer tmpTransformer = this.gridSpaceToScreenSpaceTransformer(transformer);
        this.mDrawStrategy.drawGrid(canvas, tmpTransformer, this.mColorScheme);
    }
    /**
     * Fills the canvas with the background color.
     *
     * @param canvas
     *            The canvas to draw on.
     */
    public final void drawBackground(final Canvas canvas) {
        canvas.drawColor(this.getBackgroundColor());
    }
    /**
     * @return The color to use when drawing the background.
     */
    protected final int getBackgroundColor() {
        return this.mColorScheme.getBackgroundColor();
    }

    public ColorScheme getColorScheme() {
        return this.mColorScheme;
    }

    public DrawStrategy getDrawStrategy() {
        return this.mDrawStrategy;
    }

    /**
     * Given a point, returns a the point nearest to that point that will draw a
     * circle of the given diameter snapped to the grid.
     *
     * @param currentLocation
     *            The candidate point in grid space.
     * @param tokenDiameter
     *            Diameter of the token that will be drawn.
     * @return A point that is snapped to the grid.
     */
    public PointF getNearestSnapPoint(final PointF currentLocation, final float tokenDiameter) {
        return this.mDrawStrategy.getNearestSnapPoint(currentLocation, tokenDiameter);
    }

    /**
     * Gets a transformation between grid space and screen space, by composing
     * the known grid --> world transformation with the given world --> screen
     * transformation.
     *
     * @param worldToScreen
     *            Transformation from world space to screen space.
     * @return The grid space to screen space transformation.
     */
    public final CoordinateTransformer gridSpaceToScreenSpaceTransformer(final CoordinateTransformer worldToScreen) {
        return this.mGridToWorldTransformer.compose(worldToScreen);
    }
    /**
     * Returns the stored transformation from grid space to world space.
     *
     * @return The grid space to world space transformation.
     */
    public final CoordinateTransformer gridSpaceToWorldSpaceTransformer() {
        return this.mGridToWorldTransformer;
    }
    public void setColorScheme(ColorScheme scheme) {
        this.mColorScheme = scheme;

    }

    public String getUnits() {
        return mUnits;
    }

    public void setUnits(String units) {
        mUnits = units;
    }

    public float getScale() {
        return mScale;
    }

    public void setScale(float scale) {
        mScale = scale;
    }

    public void setDrawStrategy(DrawStrategy s) {
        this.mDrawStrategy = s;
    }
}
