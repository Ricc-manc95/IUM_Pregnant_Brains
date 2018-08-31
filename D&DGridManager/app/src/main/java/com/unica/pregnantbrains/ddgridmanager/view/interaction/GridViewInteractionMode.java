package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewConfiguration;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

import com.unica.pregnantbrains.ddgridmanager.model.GridData;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.Util;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

/**
 * This class roots a strategy pattern hierarchy that primarily defines actions
 * to take on the CombatView given a particular gesture. These have been
 * extracted so that different interaction modes (panning, drawing, moving
 * tokens, etc) can easily and modularly be defined.
 *
 * @author Tim Bocek
 */
public class GridViewInteractionMode extends SimpleOnScaleGestureListener implements OnGestureListener, OnDoubleTapListener {
    private static final float LONG_PRESS_CANCEL_THRESHOLD = 3;

    /**
     * Number of fingers currently down.
     */
    private int mFingers;

    /**
     * The CombatView that this interaction mode manipulates.
     */
    private final GridView mView;

    /**
     * We are rolling our own long press in order to let a long press be
     * followed by a scroll without lifting a finger!
     */
    protected class CustomLongPressDetector {
        private final Handler mLongPressHandler = new Handler();
        private boolean mPossibleLongPress = false;
        private boolean mDefiniteLongPress = false;
        private MotionEvent mOriginalDownEvent = null;
        private final Runnable mLongPressRunnable = new Runnable() {
            @Override
            public void run() {
                if (mPossibleLongPress) {
                    mPossibleLongPress = false;
                    mDefiniteLongPress = true;
                    GridViewInteractionMode.this.onLongPress(mOriginalDownEvent);
                }
            }
        };

        public boolean onDown(final MotionEvent event) {
            mPossibleLongPress = true;
            mDefiniteLongPress = false;
            mOriginalDownEvent = event;
            mLongPressHandler.postDelayed(this.mLongPressRunnable, ViewConfiguration.getLongPressTimeout());
            return true;
        }

        public boolean onScroll(final MotionEvent arg0, final MotionEvent arg1,
                                final float arg2, final float arg3) {
            // Check if this is still a possible long press
            if (Util.distance(arg0.getX(), arg0.getY(), arg1.getX(), arg1.getY()) > LONG_PRESS_CANCEL_THRESHOLD) {
                this.mPossibleLongPress = false;
                this.mLongPressHandler.removeCallbacks(this.mLongPressRunnable);
            }
            return true;
        }

        public void onUp(final MotionEvent event) {
            this.mPossibleLongPress = false;
            this.mLongPressHandler.removeCallbacks(this.mLongPressRunnable);
        }

        public boolean isLongPress() {
            return mDefiniteLongPress;
        }
    }
    protected final CustomLongPressDetector customLongPressDetector = new CustomLongPressDetector();

    /**
     * Constructor.
     *
     * @param view
     *            The CombatView that this interaction mode manipulates.
     */
    public GridViewInteractionMode(final GridView view) {
        this.mView = view;
    }

    /**
     * Increments the number of fingers.
     */
    public final void addFinger() {
        this.mFingers++;
    }

    /**
     * @return true if the class wants to use Android's default long press logic, False otherwise.
     */
    public boolean useDefaultLongPressLogic() { return true; }

    /**
     * Allows the manipulation mode to draw custom user interface elements.
     *
     * @param c
     *            The canvas to draw on.
     */
    public void draw(final Canvas c) {
    }

    protected GridData getData() {
        return this.mView.getData();
    }

    /**
     * @return Gets the number of fingers currently down.
     */
    public int getNumberOfFingers() {
        return this.mFingers;
    }

    /**
     * @return The CombatView being manipulated.
     */
    protected GridView getView() {
        return this.mView;
    }

    @Override
    public boolean onDoubleTap(final MotionEvent arg0) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(final MotionEvent arg0) {
        return true;
    }

    @Override
    public boolean onDown(final MotionEvent event) {
        return false;
    }

    /**
     * Called when this interaction mode is stopped.
     */
    public void onEndMode() {

    }

    @Override
    public boolean onFling(final MotionEvent arg0, final MotionEvent arg1,
                           final float arg2, final float arg3) {
        return false;
    }

    @Override
    public void onLongPress(final MotionEvent arg0) {
    }

    @Override
    public boolean onScale(final ScaleGestureDetector detector) {
        Log.d("Interaction", "Scale");
        this.getView().getWorldSpaceTransformer().zoom(detector.getScaleFactor(), new PointF(detector.getFocusX(), detector.getFocusY()));
        this.getView().refreshMap();
        return true;
    }

    @Override
    public boolean onScroll(final MotionEvent arg0, final MotionEvent arg1,
                            final float arg2, final float arg3) {
        return false;
    }

    @Override
    public void onShowPress(final MotionEvent arg0) {
    }

    @Override
    public boolean onSingleTapConfirmed(final MotionEvent arg0) {
        return true;
    }

    @Override
    public boolean onSingleTapUp(final MotionEvent ev) {
        return false;
    }

    /**
     * Called when this interaction mode is started.
     */
    public void onStartMode() {

    }

    /**
     * Action to take when a finger is lifted.
     *
     * @param event
     *            Event info.
     */
    public void onUp(final MotionEvent event) {
        Log.d("Interaction", "Up");
    }

    /**
     * Decrements the number of fingers.
     */
    public final void removeFinger() {
        this.mFingers--;
    }
}