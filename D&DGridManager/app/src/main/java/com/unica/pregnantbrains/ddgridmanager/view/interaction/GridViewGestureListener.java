package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.unica.pregnantbrains.ddgridmanager.model.CoordinateTransformer;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.Token;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public class GridViewGestureListener extends SimpleGestureListener {

    protected GridView view;

    public GridViewGestureListener(GridView view) {
        this.view = view;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        view.getTransformer().zoomLevel *= detector.getScaleFactor();

        // Don't let the object get too small or too large.
        view.getTransformer().zoomLevel = Math.max(0.1f, Math.min(view.getTransformer().zoomLevel, 5.0f));

        view.invalidate();
        return true;
    }
}