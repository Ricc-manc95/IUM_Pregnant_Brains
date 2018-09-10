package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public class GridRepositioningInteractionMode extends GridViewInteractionMode {
    public GridRepositioningInteractionMode(GridView view) {
        super(view);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        view.getData().grid.gridSpaceToWorldSpaceTransformer().moveOrigin(
                -view.getTransformer().screenSpaceToWorldSpace(distanceX),
                -view.getTransformer().screenSpaceToWorldSpace(distanceY));
        view.invalidate();
        return true;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        PointF invariantPointWorldSpace = view.getTransformer().screenSpaceToWorldSpace(detector.getFocusX(), detector.getFocusY());
        view.getData().grid.gridSpaceToWorldSpaceTransformer().zoom(detector.getScaleFactor(), invariantPointWorldSpace);
        view.invalidate();
        return true;
    }
}
