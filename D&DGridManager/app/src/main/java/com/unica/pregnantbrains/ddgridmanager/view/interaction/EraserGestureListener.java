package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.graphics.PointF;
import android.view.MotionEvent;

import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public class EraserGestureListener extends GridViewGestureListener {

    public EraserGestureListener(GridView view) {
        super(view);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        for (int i = 0; i < view.getLines().size(); ++i) {
            view.getLines().get(i).erase(
                    view.getTransformer().screenSpaceToWorldSpace(new PointF(e2.getX(), e2.getY())),
                    30 / view.getTransformer().zoomLevel);
        }
        view.invalidate();
        return true;
    }

}