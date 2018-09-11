package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.Serializable;

public class Grid implements Serializable{
    private static final long serialVersionUID = -5601662822842544495L;

    public ColorScheme colorScheme = ColorScheme.STANDARD;
    private CoordinateTransformer mGridToWorldTransformer = new CoordinateTransformer(0,0,1);

    public void draw(Canvas canvas, CoordinateTransformer transformer) {
        drawBackground(canvas);
        drawGrid(canvas, transformer);
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawColor(colorScheme.getBackgroundColor());
    }

    public CoordinateTransformer gridSpaceToScreenSpaceTransformer(CoordinateTransformer worldToScreen) {
        return mGridToWorldTransformer.compose(worldToScreen);
    }
    public CoordinateTransformer gridSpaceToWorldSpaceTransformer() {
        return mGridToWorldTransformer;
    }

    private void drawGrid(Canvas canvas, CoordinateTransformer worldToScreenTransformer) {
        Paint paint = new Paint();
        paint.setColor(colorScheme.getLineColor());

        CoordinateTransformer transformer = gridSpaceToScreenSpaceTransformer(worldToScreenTransformer);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        float squareSize = transformer.worldSpaceToScreenSpace(1.0f);
        float numSquaresHorizontal = (float)width/squareSize;
        float numSquaresVertical = (numSquaresHorizontal * ((float)height)/((float)width));

        PointF origin = transformer.getOrigin();

        float offsetX = origin.x % squareSize;
        float offsetY = origin.y % squareSize;

        int thickLineStartX = (int)((origin.x % (squareSize * 5)) / squareSize);
        int thickLineStartY = (int)((origin.y % (squareSize * 5)) / squareSize);

        for (int i = 0; i <= numSquaresHorizontal; ++i) {
            if ((i-thickLineStartX)%5 == 0) {
                paint.setStrokeWidth(2);
            }
            else {
                paint.setStrokeWidth(1);
            }
            canvas.drawLine(i * squareSize + offsetX, 0, i * squareSize + offsetX, height, paint);
        }

        for (int i = 0; i <= numSquaresVertical; ++i) {
            if ((i-thickLineStartY)%5 == 0) {
                paint.setStrokeWidth(3);
            }
            else {
                paint.setStrokeWidth(1);
            }
            canvas.drawLine(0, i * squareSize + offsetY, width, i * squareSize + offsetY, paint);
        }
    }

    //Returns nearest snap point in grid space
    public PointF getNearestSnapPoint(PointF currentLocation, float tokenDiameter) {
        float previousGridLineX = (float) Math.floor((double) currentLocation.x);
        float previousGridLineY = (float) Math.floor((double) currentLocation.y);
        float offset = .5f * tokenDiameter - (float)Math.floor(.5 * tokenDiameter);

        // If we have a token that is smaller than one grid line, find the nearest subgrid
        // line instead.
        if (tokenDiameter < 1) {
            previousGridLineX += (currentLocation.x - previousGridLineX) - (currentLocation.x - previousGridLineX) % tokenDiameter;
            previousGridLineY += (currentLocation.y - previousGridLineY) - (currentLocation.y - previousGridLineY) % tokenDiameter;
        }

        return new PointF(previousGridLineX + offset, previousGridLineY + offset);
    }

    public static Grid createGrid(ColorScheme colorScheme) {
        Grid g = new Grid();
        g.colorScheme = colorScheme;
        return g;
    }

    public static Grid createGrid(CoordinateTransformer transformer) {
        Grid g = createGrid(ColorScheme.STANDARD);
        g.mGridToWorldTransformer = transformer;
        return g;
    }
}
