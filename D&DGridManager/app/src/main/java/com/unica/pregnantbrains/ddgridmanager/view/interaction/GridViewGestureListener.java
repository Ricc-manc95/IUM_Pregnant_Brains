package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.unica.pregnantbrains.ddgridmanager.model.CoordinateTransformer;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.Token;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public class GridViewGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener implements GestureDetector.OnGestureListener {
    protected GridView view;

    Token currentToken = null;

    public void GridViewGestureListener(GridView view) {
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

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (currentToken != null) {
            currentToken.move(view.getTransformer().screenSpaceToWorldSpace(distanceX),
                    view.getTransformer().screenSpaceToWorldSpace(distanceY));
        }
        else {
            view.getTransformer().originX -= distanceX;
            view.getTransformer().originY -= distanceY;
        }
        view.invalidate();
        return true;
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
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
    public void onShowPress(MotionEvent arg0) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent ev) {
        return false;
    }

    /*@Override
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
    }*/
}
