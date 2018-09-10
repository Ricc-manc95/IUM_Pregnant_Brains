package com.unica.pregnantbrains.ddgridmanager.view.interaction;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.widget.Toast;

import com.unica.pregnantbrains.ddgridmanager.CombatGrid;
import com.unica.pregnantbrains.ddgridmanager.model.CoordinateTransformer;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.Token;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.Util;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public class TokenManipulationInteractionMode extends ZoomPanInteractionMode {
    private static final int GRID_SNAP_THRESHOLD = 20;
    public TokenManipulationInteractionMode(GridView view) {
        super(view);
        // TODO Auto-generated constructor stub
    }
    Token currentToken = null;
    private PointF originalLocation;
    boolean down = false;
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        boolean b = true;
        if (currentToken != null) {
            CoordinateTransformer transformer = view.getGridSpaceTransformer();
            PointF currentPointScreenSpace = new PointF(e2.getX(), e2.getY());
            // Get the nearest snap point in screen space
            PointF nearestSnapPointWorldSpace = view.mData.grid.getNearestSnapPoint(transformer.screenSpaceToWorldSpace(currentPointScreenSpace), currentToken.getSize());
            // Snap to that point if it is less than a threshold
            float distanceToSnapPoint = Util.distance(transformer.worldSpaceToScreenSpace(nearestSnapPointWorldSpace), currentPointScreenSpace);
            currentToken.setLocation(distanceToSnapPoint < GRID_SNAP_THRESHOLD ? nearestSnapPointWorldSpace : transformer.screenSpaceToWorldSpace(currentPointScreenSpace));
        }
        else {
            b = super.onScroll(e1, e2, distanceX, distanceY);
        }
        view.invalidate();
        return b;
    }
    public boolean onDown(MotionEvent e) {
        currentToken = view.getTokens().getTokenUnderPoint(new PointF(e.getX(), e.getY()), view.getGridSpaceTransformer());

        if (currentToken != null)
            originalLocation = currentToken.getLocation();

        down = true;
        return true;
    }

    public void onLongPress(MotionEvent e) {
        if (currentToken != null) {
            view.showContextMenu();
        }
    }

    /**@Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.token_context_delete_token:
                view.getTokens().remove(currentToken);
                return true;
            case R.id.token_context_size_tenth:
                currentToken.setDiameter(.1f);
                return true;
            case R.id.token_context_size_quarter:
                currentToken.setDiameter(.25f);
                return true;
            case R.id.token_context_size_half:
                currentToken.setDiameter(.5f);
                return true;
            case R.id.token_context_size_one:
                currentToken.setDiameter(1);
                return true;
            case R.id.token_context_size_two:
                currentToken.setDiameter(2);
                return true;
            case R.id.token_context_size_four:
                currentToken.setDiameter(4);
                return true;
        }
        return false;
    }*/

    public void onUp(MotionEvent ev) {
        down = false;
        view.invalidate();
    }

    public void draw(Canvas c) {
        if (currentToken != null && down) {
            currentToken.drawGhost(c, view.getGridSpaceTransformer(), originalLocation);
        }
    }
}
