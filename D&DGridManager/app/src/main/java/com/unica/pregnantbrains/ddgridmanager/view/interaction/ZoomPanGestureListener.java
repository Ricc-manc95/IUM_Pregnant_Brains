package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.view.MotionEvent;

import com.unica.pregnantbrains.ddgridmanager.model.primitives.Token;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public class ZoomPanGestureListener extends GridViewGestureListener {
    public ZoomPanGestureListener(GridView view) {
        super(view);
    }

    Token currentToken = null;

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (currentToken != null) {
            currentToken.move(view.getTransformer().screenSpaceToWorldSpace(distanceX),
                    view.getTransformer().screenSpaceToWorldSpace(distanceY));
        }
        else {
            view.getTransformer().originX -= distanceX;
            view.getTransformer().originY -= distanceY;
        }
        view.invalidate();
        return true;
    }
}
