package com.unica.pregnantbrains.ddgridmanager.model;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

public class GridDrawer {
    private boolean mAreTokensManipulable;
    private boolean mDrawGridLines;
    private boolean mDrawTokens;
    private CoordinateTransformer mTransformer;

    public GridDrawer areTokensManipulable(boolean val) {
        this.mAreTokensManipulable = val;
        return this;
    }

    public void draw(Canvas canvas, GridData m, Rect bounds) {
        if (mTransformer == null) {
            mTransformer = m.getWorldSpaceTransformer();
        }

        PointF wsOrigin = mTransformer.screenSpaceToWorldSpace(bounds.left, bounds.top);
        float wsWidth = mTransformer.screenSpaceToWorldSpace(bounds.width());
        float wsHeight = mTransformer.screenSpaceToWorldSpace(bounds.height());
        RectF worldSpaceBounds = new RectF(wsOrigin.x, wsOrigin.y, wsOrigin.x + wsWidth, wsOrigin.y + wsHeight);

        m.getGrid().drawBackground(canvas);

        canvas.save();
        mTransformer.setMatrix(canvas);
        m.getBackgroundLines().drawAllLinesBelowGrid(canvas);
        canvas.restore();

        if (this.mDrawGridLines) {
            m.getGrid().draw(canvas, mTransformer);
        }

        canvas.save();
        mTransformer.setMatrix(canvas);
        m.getBackgroundLines().drawAllLinesAboveGrid(canvas);

        canvas.restore();

        canvas.save();
        mTransformer.setMatrix(canvas);

        canvas.restore();

        canvas.save();
        CoordinateTransformer gridSpace =
                m.getGrid().gridSpaceToScreenSpaceTransformer(mTransformer);
        /*if (this.mDrawTokens) {
            m.getTokens().drawAllTokens(canvas, gridSpace,
                    m.getGrid().isDark(), this.mAreTokensManipulable);
        }*/
        canvas.restore();
    }

    public GridDrawer drawGridLines(boolean val) {
        this.mDrawGridLines = val;
        return this;
    }

    public GridDrawer drawTokens(boolean val) {
        this.mDrawTokens = val;
        return this;
    }

    /**
     * Sets the coordinate transformer to use instead of the default coordinate transformer that
     * is present in the map data.
     *
     * @param transformer The transformer to use for world space.
     * @return this instance for chaining calls.
     */
    public GridDrawer useCustomWorldSpaceTransformer(CoordinateTransformer transformer) {
        mTransformer = transformer;
        return this;
    }
}
