package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.graphics.Point;
import android.view.MotionEvent;

import com.unica.pregnantbrains.ddgridmanager.model.Line;
import com.unica.pregnantbrains.ddgridmanager.model.PointF;
import com.unica.pregnantbrains.ddgridmanager.model.Util;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;
import com.unica.pregnantbrains.ddgridmanager.model.CoordinateTransformer;

public final class DrawInteractionMode extends GridViewInteractionMode {
    private float lastPointX;
    private float lastPointY;

    public DrawInteractionMode(GridView view) {
        super(view);
    }

    private Line currentLine;

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (currentLine == null) {
            return true;
        }
        boolean DEBUG_shoulddraw = shouldDrawLine(e2.getX(), e2.getY());
        if (shouldDrawLine(e2.getX(), e2.getY())) {
            addLinePoint(e2);
        }

        return true;
    }
    private void addLinePoint(final MotionEvent e) {
        PointF p = getScreenSpacePoint(e);
        currentLine./*addPoint*/setEndPoint(view.getTransformer().screenSpaceToWorldSpace(/*new PointF(e2.getX(), e2.getY()))*/p));
        view.invalidate();
        lastPointX = p.x;
        lastPointY = p.y;
        //lastPointX = e.getX();
        //lastPointY = e.getY();
    }

    /**
     * Gets the draw location in screen space.  Snaps to the grid if necessary.
     * @param e The motion event to get the point from.
     * @return The point in screen space.
     */
    private PointF getScreenSpacePoint(final MotionEvent e) {
        PointF p = new PointF(e.getX(), e.getY());
        CoordinateTransformer transformer = view.getGridSpaceTransformer();
        p = transformer.worldSpaceToScreenSpace(view.getData().getGrid().getNearestSnapPoint(transformer.screenSpaceToWorldSpace(p), 0));
        return p;
    }

    private boolean shouldDrawLine(float newPointX, float newPointY) {
        return Util.distance(lastPointX, lastPointY, newPointX, newPointY) > 3;
    }

    public boolean onDown(MotionEvent e) {
        currentLine = view.createLine();
        PointF p = getScreenSpacePoint(e);
        lastPointX = p.x;
        lastPointY = p.y;
        //lastPointX = e.getX();
        //lastPointY = e.getY();
        return true;
    }
}
