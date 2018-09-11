package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

import com.unica.pregnantbrains.ddgridmanager.model.PointF;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public class GridViewInteractionMode extends SimpleOnScaleGestureListener implements OnGestureListener, OnDoubleTapListener {

    protected GridView view;

    public GridViewInteractionMode(final GridView view) {
        this.view = view;
    }
    @Override
    public boolean onScale(final ScaleGestureDetector detector) {
        view.getTransformer().zoom(detector.getScaleFactor(), new PointF(detector.getFocusX(), detector.getFocusY()));
        view.invalidate();
        return true;
    }

    /**
     * Allows the manipulation mode to draw custom user interface elements.
     * @param c
     *  canvas
     */
    public void draw(Canvas c) {

    }

    public void onUp(MotionEvent event) {

    }

    @Override
    public boolean onDown(MotionEvent event) {
        return false;
    }
    @Override
    public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
                           float arg3) {
        return false;
    }
    @Override
    public void onLongPress(MotionEvent arg0) {
    }
    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
                            float arg3) {
        return false;
    }
    @Override
    public void onShowPress(MotionEvent arg0) {
    }
    @Override
    public boolean onSingleTapUp(MotionEvent ev) {
        return false;
    }
    @Override
    public boolean onDoubleTap(MotionEvent arg0) {
        return true;
    }
    @Override
    public boolean onDoubleTapEvent(MotionEvent arg0) {
        return true;
    }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent arg0) {
        return true;
    }
}