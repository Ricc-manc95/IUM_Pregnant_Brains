package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.view.MotionEvent;

import com.unica.pregnantbrains.ddgridmanager.model.Line;
import com.unica.pregnantbrains.ddgridmanager.model.PointF;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public class DrawInteractionMode extends GridViewInteractionMode {

    public DrawInteractionMode(GridView view) {
        super(view);
    }

    private Line currentLine;

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (currentLine == null) {
            return true;
        }
        currentLine.addPoint(view.getTransformer().screenSpaceToWorldSpace(new PointF(e2.getX(), e2.getY())));
        view.invalidate();
        return true;
    }

    public boolean onDown(MotionEvent e) {
        currentLine = view.createLine();
        return true;
    }
}
