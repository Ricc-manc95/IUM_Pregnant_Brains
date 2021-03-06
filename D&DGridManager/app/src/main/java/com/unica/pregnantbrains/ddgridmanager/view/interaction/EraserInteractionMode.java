package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.unica.pregnantbrains.ddgridmanager.model.PointF;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public final class EraserInteractionMode extends GridViewInteractionMode {

    private static final float ERASER_RADIUS = 30;
    private boolean erasing;
    private PointF lastErasedPoint;
    public EraserInteractionMode(GridView view) {
        super(view);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // Set up to draw erase indicator
        this.erasing = true;
        this.lastErasedPoint = new PointF(e2.getX(), e2.getY());
        // Erase
        view.getActiveLines().erase(
                view.getTransformer().screenSpaceToWorldSpace(this.lastErasedPoint),
                view.getTransformer().screenSpaceToWorldSpace(ERASER_RADIUS));

        view.invalidate();
        return true;
    }

    public void onUp(MotionEvent event) {
        this.erasing = false;
        this.view.optimizeActiveLines();
        view.invalidate();
    }

    @Override
    public void draw(Canvas c) {
        Paint p = new Paint();
        //Draw a light grey circle showing the erase diameter.
        p.setColor(Color.rgb(180,180,180));

        if (erasing)
            c.drawCircle(lastErasedPoint.x, lastErasedPoint.y, ERASER_RADIUS, p);
    }
}