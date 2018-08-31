package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public final class DrawStrategy {
    /**
     * Maximum size of squares formed by minor grid lines in dp.
     */
    private static final int GRID_LINE_SIZE_LIMIT = 8;

    /**
     * Width to draw grid lines with.
     */
    private static final float GRID_LINE_WIDTH = 2;

    public void drawGrid(final Canvas canvas, final CoordinateTransformer transformer, final ColorScheme colorScheme) {

        Paint paint = new Paint();
        paint.setColor(colorScheme.getLineColor());
        paint.setStrokeWidth(Units.dpToPx(GRID_LINE_WIDTH));

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        float squareSize = transformer.worldSpaceToScreenSpace(1.0f);
        float numSquaresHorizontal = (float) width / squareSize;
        float numSquaresVertical = numSquaresHorizontal * ((float) height) / ((float) width);

        boolean shouldDrawLine = squareSize >= Units.dpToPx(GRID_LINE_SIZE_LIMIT);

        PointF origin = transformer.getOrigin();

        float offsetX = origin.x % squareSize;
        float offsetY = origin.y % squareSize;

        for (int i = 0; i <= numSquaresHorizontal; ++i) {
            if (shouldDrawLine) {
                canvas.drawLine(i * squareSize + offsetX, 0, i * squareSize + offsetX, height, paint);
            }
        }

        for (int i = 0; i <= numSquaresVertical; ++i) {
            if (shouldDrawLine) {
                canvas.drawLine(0, i * squareSize + offsetY, width, i * squareSize + offsetY, paint);
            }
        }
    }

    // Returns nearest snap point in grid space
    public PointF getNearestSnapPoint(final PointF currentLocation, final float tokenDiameter) {
        // Special case when snapping to a point instead of a region: round to
        // nearest grid location!
        if (tokenDiameter == 0) {
            return new PointF(Math.round(currentLocation.x),
                    Math.round(currentLocation.y));
        }

        float previousGridLineX =
                (float) Math.floor((double) currentLocation.x);
        float previousGridLineY =
                (float) Math.floor((double) currentLocation.y);
        float offset =
                .5f * tokenDiameter - (float) Math.floor(.5 * tokenDiameter);

        // If we have a token that is smaller than one grid line, find the
        // nearest subgrid line instead.
        if (tokenDiameter < 1) {
            previousGridLineX +=
                    (currentLocation.x - previousGridLineX)
                            - (currentLocation.x - previousGridLineX)
                            % tokenDiameter;
            previousGridLineY +=
                    (currentLocation.y - previousGridLineY)
                            - (currentLocation.y - previousGridLineY)
                            % tokenDiameter;
        }

        return new PointF(previousGridLineX + offset, previousGridLineY
                + offset);
    }
}
