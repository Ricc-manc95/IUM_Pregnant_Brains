package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.view.MotionEvent;

import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public class ZoomPanInteractionMode extends GridViewInteractionMode {
    public ZoomPanInteractionMode(GridView view) {
        super(view);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        view.getTransformer().moveOrigin(-distanceX, -distanceY);
        view.invalidate();
        return true;
    }
}
