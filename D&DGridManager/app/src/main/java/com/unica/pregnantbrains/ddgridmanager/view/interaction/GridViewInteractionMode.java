package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.unica.pregnantbrains.ddgridmanager.model.PointF;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public class GridViewInteractionMode extends SimpleGestureListener {

    protected GridView view;

    public GridViewInteractionMode(GridView view) {
        this.view = view;
    }
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        view.getTransformer().zoom(detector.getScaleFactor(), new PointF(detector.getFocusX(), detector.getFocusY()));
        view.invalidate();
        return true;
    }

    /**
     * Allows the manipulation mode to draw custom user interface elements.
     * @param c
     */
    public void draw(Canvas c) {

    }

    public void onUp(MotionEvent event) {

    }
}