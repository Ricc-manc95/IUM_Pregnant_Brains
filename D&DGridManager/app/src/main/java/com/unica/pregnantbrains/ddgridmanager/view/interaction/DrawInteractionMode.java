package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.view.MotionEvent;

import com.unica.pregnantbrains.ddgridmanager.model.Line;
import com.unica.pregnantbrains.ddgridmanager.model.PointF;
import com.unica.pregnantbrains.ddgridmanager.model.Util;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

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
    private void addLinePoint(MotionEvent e2) {
        currentLine.addPoint(view.getTransformer().screenSpaceToWorldSpace(new PointF(e2.getX(), e2.getY())));
        view.invalidate();lastPointX = e2.getX();
        lastPointY = e2.getY();
    }

    private boolean shouldDrawLine(float newPointX, float newPointY) {
        return Util.distance(lastPointX, lastPointY, newPointX, newPointY) > 3;
    }

    public boolean onDown(MotionEvent e) {
        currentLine = view.createLine();
        lastPointX = e.getX();
        lastPointY = e.getY();
        return true;
    }
}
