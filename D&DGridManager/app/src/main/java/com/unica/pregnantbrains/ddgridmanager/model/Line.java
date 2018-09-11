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
    private float width = 2f;

    private BoundingRectangle boundingRectangle = new BoundingRectangle();
    private List<PointF> points = new ArrayList<PointF>();
    private List<Boolean> shouldDraw = new ArrayList<Boolean>();

    public Line (int color, float strokeWidth) {
        this.color = color;
        this.width = strokeWidth;
    }

    public void addPoint (PointF p) {
        points.add(p);
        shouldDraw.add(true);
        boundingRectangle =  new BoundingRectangle();
        boundingRectangle.updateBounds(p);
    }


    public void draw(Canvas c/*, CoordinateTransformer transformer*/) {
        //Do not try to draw a line with too few points.
        if (points.size() < 2) return;

        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(width);

        for (int i = 0; i < points.size() - 1; ++i) {
            if (shouldDraw.get(i) && shouldDraw.get(i+1)) {
                PointF p1 = /*transformer.worldSpaceToScreenSpace(*/points.get(i)/*)*/;
                PointF p2 = /*transformer.worldSpaceToScreenSpace(*/points.get(i+1)/*)*/;
                c.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
            }
        }
    }

    public void erase(PointF center, float radius) {
        if (boundingRectangle.intersectsWithCircle(center, radius)) {
            /*for (int i = 0; i < points.size(); ++i) {
                if (Util.distance(center, points.get(i)) < radius) {
                    shouldDraw.set(i, false);
                }
            }*/
            if (points.size() == 2) {
                // Special case - if we have only two points, this is probably
                // a large straight line and we want to erase the line if the
                // eraser intersects with it.  However, this is an expensive
                // test, so we don't want to do it for all line segments when
                // they are generally small enough for the eraser to enclose.
                // Formula from:
                // http://mathworld.wolfram.com/Circle-LineIntersection.html
                PointF p1 = this.points.get(0);
                PointF p2 = this.points.get(1);
                // Transform to standard coordinate system where circle is
                // centered at (0, 0)
                float x1 = p1.x - center.x;
                float y1 = p1.y - center.y;
                float x2 = p2.x - center.x;
                float y2 = p2.y - center.y;
                float dsquared = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
                float det = x1 * y2 - x2 * y1;
                float discriminant = radius * radius * dsquared - det * det;
                // Intersection if the discriminant is non-negative
                if (discriminant >= 0) {
                    shouldDraw.set(0, false);
                    shouldDraw.set(1, false);
                }
            } else {
                for (int i = 0; i < points.size(); ++i) {
                    if (Util.distance(center, points.get(i)) < radius) {
                        shouldDraw.set(i, false);
                    }
                }
            }
        }
    }

    public List<Line> removeErasedPoints() {
        List<Line> optimizedLines = new ArrayList<Line>();
        Line l = new Line(color, width);

        optimizedLines.add(l);

        for (int i = 0; i < points.size(); i++) {
            if (this.shouldDraw.get(i)) {
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
    public float getStrokeWidth() {
        return this.width;
    }

    /**
     * Sets the end point of the line.  This is used when a straight line is
     * desired.
     * @param p The point to set.
     */
    public void setEndPoint(final PointF p) {
        if (points.size() > 1) {
            points.remove(points.size() - 1);
            shouldDraw.remove(shouldDraw.size() - 1);
        }
        points.add(p);
        shouldDraw.add(true);
    }
}
