package com.unica.pregnantbrains.ddgridmanager.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import com.unica.pregnantbrains.ddgridmanager.model.CoordinateTransformer;
import com.unica.pregnantbrains.ddgridmanager.model.Units;

public final class CombatGrid {
    /**
     * Maximum size of squares formed by minor grid lines in dp.
     */
    private static final int LINE_SIZE_LIMIT = 8;
    private static final int LINE_WIDTH = 2;
    private static final int lineColor = Color.rgb(117,117,117);
    private static final int backgroundColor = Color.rgb(250,250,250);

    private String mUnits = "ft";
    private float mScale = 5;
    private CoordinateTransformer mGridToWorldTransformer = new CoordinateTransformer(0, 0, 1);

    //default constructor
    public CombatGrid() {}

    public static CombatGrid createGrid(final CoordinateTransformer transformer) {
        CombatGrid g = new CombatGrid();
        g.mGridToWorldTransformer = transformer;
        return g;
    }

    public final CoordinateTransformer gridSpaceToScreenSpaceTransformer(
            final CoordinateTransformer worldToScreen) {
        return this.mGridToWorldTransformer.compose(worldToScreen);
    }

    public final void drawToCanvas (final Canvas canvas, final CoordinateTransformer transformer) {
        CoordinateTransformer transformer1 = this.gridSpaceToScreenSpaceTransformer(transformer);
        this.drawGrid(canvas, transformer1);
    }
    //potrebbe servirci forse ma non lo sappiamo, però è bello
    public final CoordinateTransformer gridSpaceToWorldSpaceTransformer() {
        return this.mGridToWorldTransformer;
    }

    public final void drawBackground(final Canvas canvas){
        canvas.drawColor(backgroundColor);
    }

    public void drawGrid(final Canvas canvas, final CoordinateTransformer transformer) {

        Paint paint = new Paint();
        paint.setColor(lineColor);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        float squareSize = transformer.worldSpaceToScreenSpace(1.0f);
        float numSquareHorizontal = (float) width / squareSize;
        float numSquareVertical = numSquareHorizontal * ((float) height) / ((float) width);

        PointF origin = transformer.getOrigin();

        float offsetX = origin.x % squareSize;
        float offsetY = origin.y % squareSize;

        boolean shouldDrawCurrentLine = squareSize >= Units.dpToPx(LINE_SIZE_LIMIT);

        for (int i = 0; i <= numSquareHorizontal; i++) {
            if (shouldDrawCurrentLine) {
                canvas.drawLine(i * squareSize + offsetX, 0, i * squareSize + offsetX, height, paint);
            }
        }

        for (int i = 0; i <= numSquareVertical; i++) {
            if (shouldDrawCurrentLine) {
                canvas.drawLine(0, i * squareSize + offsetY, width, i * squareSize + offsetY, paint);
            }
        }

    }

    public String getUnits() {
        return mUnits;
    }

    public void setUnits(String units) {
        mUnits = units;
    }

    public float getScale() {
        return mScale;
    }

    public void setScale(float scale) {
        mScale = scale;
    }
}
