package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.PointF;
import android.graphics.RectF;

public class GridData {
    /**
     * Initial zoom level of newly created maps. Corresponds to 1 square = 64
     * pixels. Not density independent.
     */
    private static final int START_ZOOM = 64;
    /**
     * Annotation lines.
     */
    private LineCollection mAnnotationLines = new LineCollection();
    /**
     * Background lines.
     */
    private LineCollection mBackgroundLines = new LineCollection();
    /**
     * The map data currently being managed.
     */
    private static GridData instance;
    /**
     * The grid to draw.
     */
    private Grid mGrid = Grid.createGrid(new DrawStrategy(), new ColorScheme(ColorScheme.STANDARD.getBackgroundColor(), ColorScheme.STANDARD.getLineColor()), new CoordinateTransformer(0, 0, 64));;
    /**
     * Transformation from world space to screen space.
     */
    private CoordinateTransformer mTransformer = new CoordinateTransformer(0, 0, START_ZOOM);
    /**
     * Clears the map by loading a new instance.
     */
    public static void clear() {
        instance = new GridData();
    }
    /**
     * Gets the current map data instance.
     *
     * @return The map data.
     */
    public static GridData getInstance() {
        if (instance == null) {
            clear();
        }
        return instance;
    }
    /**
     * Return a *copy* of the singleton map data instance.  This should be treated as immutable,
     * as changes will not be reflected in the singleton instance.
     * @return A copy of the map data.
     *
    public static GridData getCopy() {
        if (instance == null) return null;
        return new GridData(instance);
    }
    /**
     * @return True if an instance of MapData has already been created.
     *
    public static boolean hasValidInstance() {
        return instance != null;
    }

    /**
     * Clears the currently loaded map data, forcing a reload next time map data
     * is needed.
     *
    public static void invalidate() {
        instance = null;
    }
    /**
     * Private constructor - singleton pattern.
     *
    private MapData() {

    }

    /**
     * Copy constructor
     *
    private MapData(MapData copyFrom) {
        this.mAnnotationLines = new LineCollection(copyFrom.mAnnotationLines);
        this.mBackgroundLines = new LineCollection(copyFrom.mBackgroundLines);
        this.mGmNoteLines = new LineCollection(copyFrom.mGmNoteLines);
        this.mBackgroundFogOfWar = new LineCollection(copyFrom.mBackgroundFogOfWar);
        this.mGmNotesFogOfWar = new LineCollection(copyFrom.mGmNotesFogOfWar);
        this.mAnntationCommandHistory = null;
        this.mBackgroundCommandHistory = null;
        this.mGmNotesCommandHistory = null;
        this.mTokenCollectionCommandHistory = null;

        this.mTokens = new TokenCollection(copyFrom.mTokens);
        this.mTransformer = new CoordinateTransformer(copyFrom.mTransformer);
        this.mBackgroundImages = new BackgroundImageCollection(copyFrom.mBackgroundImages);
        this.mGrid = new Grid(copyFrom.mGrid);
    }*/
    /**
     * Gets a rectangle that encompasses the entire map.
     *
     * @return The bounding rectangle.
     */
    public BoundingRectangle getBoundingRectangle() {
        BoundingRectangle r = new BoundingRectangle();

        r.updateBounds(this.mBackgroundLines.getBoundingRectangle());
        r.updateBounds(this.mAnnotationLines.getBoundingRectangle());

        return r;
    }
    /**
            * @return the grid
     */
    public Grid getGrid() {
        return this.mGrid;
    }
    /**
     * Gets the screen space bounding rectangle of the entire map based on the
     * current screen space transformation.
     *
     * @param marginsPx
     *            Margin to apply to each edge.
     * @return The bounding rectangle.
     */
    public RectF getScreenSpaceBoundingRect(int marginsPx) {
        BoundingRectangle worldSpaceRect = this.getBoundingRectangle();
        PointF ul = this.mTransformer.worldSpaceToScreenSpace(worldSpaceRect.getXMin(), worldSpaceRect.getYMin());
        PointF lr = this.mTransformer.worldSpaceToScreenSpace(worldSpaceRect.getXMax(), worldSpaceRect.getYMax());
        return new RectF(ul.x - marginsPx, ul.y - marginsPx, lr.x + marginsPx, lr.y + marginsPx);
    }
    /**
     * @return the transformer
     */
    public CoordinateTransformer getWorldSpaceTransformer() {
        return this.mTransformer;
    }
    /**
     * @param grid
     *            the grid to set
     */
    public void setGrid(final Grid grid) {
        this.mGrid = grid;
    }
    CoordinateTransformer mSavedTransformer = null;
    CoordinateTransformer mCastTransformer = null;

    /**
     * @return True if an instance of MapData has already been created.
     */
    public static boolean hasValidInstance() {
        return instance != null;
    }
    /**
     * Clears the currently loaded map data, forcing a reload next time map data
     * is needed.
     */
    public static void invalidate() {
        instance = null;
    }

    public void saveView() {
        mSavedTransformer = new CoordinateTransformer(mTransformer);
    }

    public void restoreView() {
        mTransformer = new CoordinateTransformer(mSavedTransformer);
    }

    public void castView() {
        mCastTransformer = new CoordinateTransformer(mTransformer);
    }

    public void stopCastingView() {
        mCastTransformer = null;
    }
    /**
     * @return the backgroundLines
     */
    public LineCollection getBackgroundLines() {
        return this.mBackgroundLines;
    }

    public LineCollection getAnnotationLines() {
        return mAnnotationLines;
    }
}
