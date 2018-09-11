package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a single vector-drawn line.
 * @author Pregnant Brains
 *
 */
public final class Line implements Serializable {
    private static final long serialVersionUID = 1431504565823371730L;

    private int color = Color.BLACK;
    private int width = 2;

    private BoundingRectangle boundingRectangle = new BoundingRectangle();
    private List<PointF> points = new ArrayList<PointF>();
    private List<Boolean> shouldDraw = new ArrayList<Boolean>();

    public Line (int color, int strokeWidth) {
        this.color = color;
        this.width = strokeWidth;
    }

    public void addPoint (PointF p) {
        points.add(p);
        shouldDraw.add(true);
        boundingRectangle.updateBounds(p);
    }


    public void draw(Canvas c, CoordinateTransformer transformer) {
        //Do not try to draw a line with too few points.
        if (points.size() < 2) return;

        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(width);

        for (int i = 0; i < points.size() - 1; ++i) {
            if (shouldDraw.get(i).booleanValue() && shouldDraw.get(i+1).booleanValue()) {
                PointF p1 = transformer.worldSpaceToScreenSpace(points.get(i));
                PointF p2 = transformer.worldSpaceToScreenSpace(points.get(i+1));
                c.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
            }
        }
    }

    public void erase(PointF center, float radius) {
        if (boundingRectangle.intersectsWithCircle(center, radius)) {
            for (int i = 0; i < points.size(); ++i) {
                if (Util.distance(center, points.get(i)) < radius) {
                    shouldDraw.set(i, false);
                }
            }
        }
    }

    public List<Line> removeErasedPoints() {
        List<Line> optimizedLines = new ArrayList<Line>();
        Line l = new Line(color, width);

        optimizedLines.add(l);

        for (int i = 0; i < points.size(); i++) {
            if (this.shouldDraw.get(i).booleanValue()) {
                l.addPoint(points.get(i));
            } else if (l.points.size() > 0) {
                //Do not add a line with only one point in it, those are useless
                if (l.points.size() == 1) {
                    optimizedLines.remove(l);
                }
                l = new Line(color, width);
                optimizedLines.add(l);
            }
        }

        return optimizedLines;
    }

    public BoundingRectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    /**
     * @return This line's stroke width.
     */
    public int getStrokeWidth() {
        return this.width;
    }
}
