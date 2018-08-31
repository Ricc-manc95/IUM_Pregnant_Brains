package com.unica.pregnantbrains.ddgridmanager.view.interaction;


import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.unica.pregnantbrains.ddgridmanager.view.GridView;

/**
 * Defines an interaction mode that lets the user scroll and pinch-zoom to move
 * and resize the grid without affecting anything that has been drawn.
 *
 * @author Tim Bocek
 *
 */
public final class GridRepositioningInteractionMode extends
        GridViewInteractionMode {

    /**
     * Constructor.
     *
     * @param view
     *            The CombatView to interact with.
     */
    public GridRepositioningInteractionMode(final GridView view) {
        super(view);
    }

    @Override
    public boolean onScale(final ScaleGestureDetector detector) {
        PointF invariantPointWorldSpace =
                this.getView()
                        .getWorldSpaceTransformer()
                        .screenSpaceToWorldSpace(detector.getFocusX(),
                                detector.getFocusY());
        this.getData().getGrid().gridSpaceToWorldSpaceTransformer()
                .zoom(detector.getScaleFactor(), invariantPointWorldSpace);
        this.getView().refreshMap();
        return true;
    }

    @Override
    public boolean onScroll(final MotionEvent e1, final MotionEvent e2,
                            final float distanceX, final float distanceY) {
        float deltaX =
                -this.getView().getWorldSpaceTransformer()
                        .screenSpaceToWorldSpace(distanceX);
        float deltaY =
                -this.getView().getWorldSpaceTransformer()
                        .screenSpaceToWorldSpace(distanceY);
        this.getData().getGrid().gridSpaceToWorldSpaceTransformer()
                .moveOrigin(deltaX, deltaY);
        this.getView().refreshMap();
        return true;
    }

}
