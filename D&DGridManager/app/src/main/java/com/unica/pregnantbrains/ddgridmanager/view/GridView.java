package com.unica.pregnantbrains.ddgridmanager.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.unica.pregnantbrains.ddgridmanager.ScrollBuffer;
import com.unica.pregnantbrains.ddgridmanager.model.CoordinateTransformer;
import com.unica.pregnantbrains.ddgridmanager.model.GridData;
import com.unica.pregnantbrains.ddgridmanager.model.GridDrawer;
import com.unica.pregnantbrains.ddgridmanager.model.LineCollection;
import com.unica.pregnantbrains.ddgridmanager.model.Units;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.StraightLine;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.EraserInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.FingerDrawInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.GridRepositioningInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.GridViewInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.MeasuringTapeInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.ZoomPanInteractionMode;

import javax.annotation.Nonnull;

public class GridView extends SurfaceView {

    private static final float INFO_POINT_SIZE_DP = 32;
    private boolean mEditingMask = false;

    /**
     * A simple 3-state machine to make sure that full-screen draws performed during
     * input processing are batched, and performed once at the very end of the draw.
     * @author Tim
     *
     */
    private enum FullscreenDrawLatch {
        NOT_BATCHING, // Not attempting to batch full-screen draw operations.
        BATCHING, // Attempting to batch full-screen draws, none have been requested.
        BATCHED // A full-screen draw has been requested.
    }

    /**
     * For the explanatory mask text, Y location of the first line in density-
     * Independent pixels.
     */
    private static final int EXPLANATORY_TEXT_INITIAL_Y_SP = 16;

    /**
     * For the explanatory mask text, height of each line in density-
     * Independent pixels.
     */
    private static final int EXPLANATORY_TEXT_LINE_HEIGHT_SP = 20;

    /**
     * Reference to the collection of lines that are actively being drawn.
     */
    private LineCollection mActiveLines;
    /**
     * Whether tokens should be drawn as manipulable.
     */
    private boolean mAreTokensManipulable = true;
    /**
     * The current map.
     */
    /**
     * Listener to publish refresh requests to.
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener;
    private GridData mData;
    /**
     * Detector object to detect regular gestures.
     */
    private GestureDetector mGestureDetector;
    /**
     * Interaction mode, defining how the view should currently respond to user
     * input.
     */
    private GridViewInteractionMode mInteractionMode;

    private FullscreenDrawLatch mDrawLatch = FullscreenDrawLatch.NOT_BATCHING;

    /**
     * The color to use when creating a new line.
     */
    private int mNewLineColor = Color.BLACK;
    /**
     * The stroke width to use when creating a new line.
     */
    private float mNewLineStrokeWidth;
    /**
     * Detector object to detect pinch zoom.
     */
    private ScaleGestureDetector mScaleDetector;
    /**
     * Whether tokens being moved should snap to the grid.
     */
    private boolean mSnapToGrid;
    /**
     * Callback for the Android graphics surface management system.
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final SurfaceHolder.Callback mSurfaceHolderCallback =
            new SurfaceHolder.Callback() {
                @Override
                public void surfaceChanged(SurfaceHolder arg0, int arg1,
                                           int arg2, int arg3) {
                    GridView.this.refreshMap();
                }

                @Override
                public void surfaceCreated(SurfaceHolder arg0) {
                    GridView.this.mSurfaceReady = true;
                    GridView.this.refreshMap();
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder arg0) {
                    GridView.this.mSurfaceReady = false;

                }
            };
    /**
     * Whether the surface is ready to draw.
     */
    private boolean mSurfaceReady = false;
    private final ScrollBuffer mScrollBuffer = new ScrollBuffer();
    //private TokenImageManager.Loader mLoader;
    /**
     * Constructor.
     *
     * @param context
     *            The context to create this view in.
     */
    @SuppressLint("NewApi")
    public GridView(final Context context) {
        super(context);

        this.setFocusable(true);
        this.setFocusableInTouchMode(true);

        /*this.setTokenManipulationMode();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            OnDragListener onDrag = new OnDragListener() {
                @Override
                public boolean onDrag(final View view, final DragEvent event) {
                    Log.d("DRAG", Integer.toString(event.getAction()));
                    if (event.getAction() == DragEvent.ACTION_DROP) {
                        BaseToken toAdd = (BaseToken) event.getLocalState();
                        PointF location =
                                GridView.this.getGridSpaceTransformer()
                                        .screenSpaceToWorldSpace(
                                                new PointF(event.getX(), event
                                                        .getY())
                                        );
                        if (GridView.this.mSnapToGrid) {
                            location =
                                    GridView.this
                                            .getData()
                                            .getGrid()
                                            .getNearestSnapPoint(location,
                                                    toAdd.getSize());
                        }
                        toAdd.setLocation(location);
                        GridView.this.getData().getTokens().addToken(toAdd);
                        GridView.this.alertTokensChanged();
                        TokenDatabase.getInstance(GridView.this.getContext()).tagToken(toAdd.getTokenId(), TokenDatabase.RECENTLY_USED);
                        return true;
                    } else if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
                        return true;
                    }
                    return false;
                }
            };
            this.setOnDragListener(onDrag);
        }*/

        this.getHolder().addCallback(this.mSurfaceHolderCallback);
        // setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }
    /**
     * Creates a new line on whatever line set is currently active, using the
     * currently set color, stroke width, and type.
     *
     * @return The new line.
     */
    public StraightLine createLine() {
        return this.createLine(this.mActiveLines);
    }

    /**
     * Creates a line in the given line collection with the currently set color,
     * stroke width, and type.
     *
     * @param lines
     *            The line collection to create the line in.
     * @return The created line.
     */
    protected StraightLine createLine(LineCollection lines) {
        return (StraightLine) lines.createStraightLine(this.mNewLineColor, this.mNewLineStrokeWidth);
        //throw new IllegalArgumentException("Invalid new line type.");
    }
    /**
     * Draws the contents of the view to the given canvas.
     *
     * @param canvas
     *            The canvas to draw on.
     */
    private void drawOnCanvas(final Canvas canvas, final Rect dirty) {
        new GridDrawer()
                .drawGridLines(true)
                //.drawTokens(true)
                //.areTokensManipulable(this.mAreTokensManipulable)
                .draw(canvas, this.getData(), dirty);

        this.mInteractionMode.draw(canvas);
    }
    /**
     * Gets the currently active line collection.
     *
     * @return The active lines.
     */
    public LineCollection getActiveLines() {
        return this.mActiveLines;
    }

    /**
     * Gets the current map data.
     *
     * @return data The map data.
     */
    public GridData getData() {
        return this.mData;
    }
    /**
     * Gets a transformer from grid space to screen space, by composing the grid
     * to world and the world to screen transformers.
     *
     * @return The composed transformation.
     */
    public CoordinateTransformer getGridSpaceTransformer() {
        return this.getData().getGrid().gridSpaceToScreenSpaceTransformer(this.getData().getWorldSpaceTransformer());
    }
    /**
     * Gets a preview image of the map currently displayed in the view.
     *
     * @return A bitmap containing the preview image.
     */
    public Bitmap getPreview() {
        if (this.getWidth() == 0 || this.getHeight() == 0) {
            return null;
        }
        Bitmap bitmap =
                Bitmap.createBitmap(this.getWidth(), this.getHeight(),
                        Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        new GridDrawer().drawGridLines(false)
                .drawTokens(true).areTokensManipulable(true)
                .draw(canvas, this.getData(), canvas.getClipBounds());

        return bitmap;
    }
    /**
     * Returns the current token collection.
     *
     * @return The tokens.
     *
    public TokenCollection getTokens() {
        return this.getData().getTokens();
    }*/
    /**
     * Returns the world space to screen space transformer used by the view.
     *
     * @return The transformation object.
     */
    public CoordinateTransformer getWorldSpaceTransformer() {
        return this.getData().getWorldSpaceTransformer();
    }
    @Override
    public boolean onTouchEvent(@Nonnull final MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            this.mInteractionMode.addFinger();
        }

        startBatchingDraws();
        this.mGestureDetector.onTouchEvent(ev);
        this.mScaleDetector.onTouchEvent(ev);

        // If a finger was removed, optimize the lines by removing unused
        // points.
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            this.mInteractionMode.removeFinger();
            this.mInteractionMode.onUp(ev);

            /* If the end of a gesture, load any newly required token images.
            if (this.mInteractionMode.getNumberOfFingers() == 0) {
                this.loadNewTokenImages();
            }*/
        }

        // If one or more fullscreen draws was requested, do so now, and either
        // way leave us open to non-touch-event-driven draw requests.
        stopBatchingDraws();
        return true;
    }
    private void startBatchingDraws() {
        if (this.mDrawLatch == FullscreenDrawLatch.NOT_BATCHING)
            this.mDrawLatch = FullscreenDrawLatch.BATCHING;
    }

    private void stopBatchingDraws() {
        if (this.mDrawLatch == FullscreenDrawLatch.BATCHED) {
            this.mDrawLatch = FullscreenDrawLatch.NOT_BATCHING;
            this.refreshMap();
        } else {
            this.mDrawLatch = FullscreenDrawLatch.NOT_BATCHING;
        }
    }
    /**
     * Removes all erased points from the currently active set of lines.
     *
    public void optimizeActiveLines() {
        this.mActiveLines.optimize();
    }*/

    /**
     * Places a token on the screen, at a location chosen by the view.
     *
     * @param t
     *            The token to place.
     *
    public void placeToken(final BaseToken t) {
        PointF attemptedLocationScreenSpace =
                new PointF(this.getWidth() / 2.0f, this.getHeight() / 2.0f);
        PointF attemptedLocationGridSpace =
                this.getGridSpaceTransformer().screenSpaceToWorldSpace(
                        attemptedLocationScreenSpace);

        this.getData()
                .getTokens()
                .placeTokenNearby(t, attemptedLocationGridSpace,
                        this.getData().getGrid(),
                        this.mTokensSnapToIntersections);
        this.getData().getTokens().addToken(t);
        this.refreshMap(t.getBoundingRectangle().toRectF(), this.getGridSpaceTransformer());
    }*/
    /**
     * Redraws the contents of the map.
     * @param invalidBounds Screen space portion to redraw.s
     */
    public void refreshMap(Rect invalidBounds) {
        // Make sure the refresh being requested is sane.
        // (This could be violated when starting to draw for a shape that was
        // just now created).
        if (invalidBounds.left > invalidBounds.right || invalidBounds.top > invalidBounds.bottom){
            return;
        }

        // If we already need a full screen refresh as part of this draw,
        // be smart and don't redraw just part of the screen!
        if (this.mDrawLatch == FullscreenDrawLatch.BATCHED) {
            return;
        }

        if (!this.mSurfaceReady) {
            return;
        }

        // Make sure that any scale changes are reflected in the way that scale-independent sprites
        // (such as info points) are drawn.
        float infoWidthScreenSpace = Units.dpToPx(INFO_POINT_SIZE_DP);

        SurfaceHolder holder = this.getHolder();
        Canvas canvas = holder.lockCanvas(invalidBounds);
        if (canvas != null) {
            canvas.clipRect(invalidBounds);
            this.drawOnCanvas(canvas, invalidBounds);
            holder.unlockCanvasAndPost(canvas);
        }

        if (this.mOnRefreshListener != null) {
            if (this.mInteractionMode.getNumberOfFingers() == 0) {
                this.mOnRefreshListener.onRefresh();
            }
        }

        // If we called this, then a non-scroll operation triggered a map refresh.
        // This means the scroll buffer will contain out-of-date info.
        this.mScrollBuffer.invalidateBuffers();
    }
    /**
     * Refreshes the entire map.
     */
    public void refreshMap() {
        // If we are batching full screen draw operations, defer this operation
        // until we are ready for the batch.
        if (this.mDrawLatch != FullscreenDrawLatch.NOT_BATCHING) {
            this.mDrawLatch = FullscreenDrawLatch.BATCHED;
        } else {
            refreshMap(new Rect(0,0,this.getWidth(),this.getHeight()));
        }
    }

    /**
     * Refreshes the portion of the map, using the given transformer to transform to screen space.
     */
    public void refreshMap(RectF invalidBounds, CoordinateTransformer transformer) {
        refreshMap(transformer.worldSpaceToScreenSpace(invalidBounds));
    }
    /**
     * Sets the map data displayed. Forces a redraw.
     *
     * @param data
     *            The new map data.
     */
    public void setData(final GridData data) {

        /*boolean useBackgroundLines = (this.mData == null) ||
                this.mActiveLines == this.mData.getBackgroundLines();*/
        this.mData = data;
        /*this.mActiveLines = useBackgroundLines ? this.mData.getBackgroundLines() : this.mData.getAnnotationLines();
        //this.alertTokensChanged();*/
    }
    /**
     * Sets the interaction mode to drawing lines.
     */
    public void setDrawMode() {
        this.setInteractionMode(new FingerDrawInteractionMode(this));
    }

    public void setEditingLayerMask(boolean editingLayerMask) {
        this.mEditingMask = editingLayerMask;
        this.refreshMap();
    }

    /**
     * Sets the interaction mode to erasing lines.
     */
    public void setEraseMode() {
        this.setInteractionMode(new EraserInteractionMode(this));
    }


    /**
     * Sets the interaction mode to the given listener.
     *
     * @param mode
     *            The interaction mode to use.
     */
    private void setInteractionMode(final GridViewInteractionMode mode,
                                    boolean areTokensManipulable) {
        //this.setAreTokensManipulable(areTokensManipulable);
        if (this.mInteractionMode != null) {
            this.mInteractionMode.onEndMode();
        }

        this.mGestureDetector = new GestureDetector(this.getContext(), mode);
        this.mGestureDetector.setOnDoubleTapListener(mode);
        this.mGestureDetector.setIsLongpressEnabled(mode.useDefaultLongPressLogic());
        this.mScaleDetector = new ScaleGestureDetector(this.getContext(), mode);
        this.mInteractionMode = mode;

        this.mInteractionMode.onStartMode();

        this.refreshMap();
    }


    private void setInteractionMode(final GridViewInteractionMode mode) {
        this.setInteractionMode(mode, false);
    }
    /**
     * @param newLineColor
     *            the newLineColor to set
     */
    public void setNewLineColor(final int newLineColor) {
        this.mNewLineColor = newLineColor;
    }

    /**
     * @param width
     *            the newLineStrokeWidth to set
     */
    public void setNewLineStrokeWidth(final float width) {
        this.mNewLineStrokeWidth = width;
    }
    /**
     * Sets the listener to publish refresh requests to.
     *
     * @param onRefreshListener
     *            The listener to use.
     */
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        this.mOnRefreshListener = onRefreshListener;
    }

    /**
     * Sets the interaction mode to resizing the grid independent of anything
     * already drawn.
     */
    public void setResizeGridMode() {
        this.setInteractionMode(new GridRepositioningInteractionMode(this));
    }

    /**
     * @param shouldSnapToGrid
     *            the shouldSnapToGrid to set
     */
    public void setShouldSnapToGrid(final boolean shouldSnapToGrid) {
        this.mSnapToGrid = shouldSnapToGrid;
    }
    /**
     * Sets the interaction mode to simple zooming and panning.
     */
    public void setZoomPanMode() {
        this.setInteractionMode(new ZoomPanInteractionMode(this));
    }

    /**
     * @return the shouldSnapToGrid
     */
    public boolean shouldSnapToGrid() {
        return this.mSnapToGrid;
    }
    /**
     * Called when this combat view is refreshed.
     *
     * @author Tim
     *
     */
    public interface OnRefreshListener {
        /**
         * Called when this combat view is refreshed.
         */
        void onRefresh(boolean interactionDone);
    }
    public void setMeasuringTapeMode() {
        this.setInteractionMode(new MeasuringTapeInteractionMode(this));
    }
    @Override
    protected void onSizeChanged (int w, int h, int oldW, int oldH) {
        mScrollBuffer.allocateBitmaps(w, h);
    }

    public void scroll(float deltaXF, float deltaYF) {
        ScrollBuffer.DrawRequest req = mScrollBuffer.scroll(deltaXF, deltaYF);
        if (req == null) return;

        getWorldSpaceTransformer()
                .moveOrigin(req.deltaX, req.deltaY);

        for (Rect r: req.invalidRegions) {
            req.canvas.clipRect(r, Region.Op.REPLACE);
            this.drawOnCanvas(req.canvas, r);
        }

        SurfaceHolder holder = this.getHolder();
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            canvas.drawBitmap(mScrollBuffer.getActiveBuffer(), 0, 0, null);
            holder.unlockCanvasAndPost(canvas);
        }
    }
}
