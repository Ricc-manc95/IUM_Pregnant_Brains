package com.unica.pregnantbrains.ddgridmanager.view.interaction;


import android.graphics.PointF;
import android.view.MotionEvent;

import com.unica.pregnantbrains.ddgridmanager.model.CoordinateTransformer;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

/**
 * Base class for drawing interaction modes that implements some common behavior
 * that should always happen when drawing.
 *
 * @author Tim
 *
 */
public class BaseDrawInteractionMode extends GridViewInteractionMode {

    /**
     * Constructor.
     *
     * @param view
     *            The CombatView to manipulate.
     */
    public BaseDrawInteractionMode(GridView view) {
        super(view);
    }

    /**
     * Gets the draw location in screen space. Snaps to the grid if necessary.
     *
     * @param e
     *            The motion event to get the point from.
     * @return The point in screen space.
     */
    protected PointF getScreenSpacePoint(final MotionEvent e) {
        PointF p = new PointF(e.getX(), e.getY());
        if (this.getView().shouldSnapToGrid()) {
            CoordinateTransformer transformer =
                    this.getView().getGridSpaceTransformer();
            p =
                    transformer.worldSpaceToScreenSpace(this
                            .getView()
                            .getData()
                            .getGrid()
                            .getNearestSnapPoint(
                                    transformer.screenSpaceToWorldSpace(p), 0));
        }
        return p;
    }
}
