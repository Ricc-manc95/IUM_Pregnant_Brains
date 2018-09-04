package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class SimpleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

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
