package com.unica.pregnantbrains.ddgridmanager.model.primitives;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import com.unica.pregnantbrains.ddgridmanager.model.BoundingRectangle;
import com.unica.pregnantbrains.ddgridmanager.model.CoordinateTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a single vector-drawn line.
 * @author Pregnant Brains
 *
 */
public class Line {
    private static float MIN_POINT_DISTANCE = 0;

    private int color = Color.BLACK;
    private int width = 2;

    private BoundingRectangle boundingRectangle = new BoundingRectangle();
    private List<PointF> points = new ArrayList<PointF>();
    private List<Boolean> shouldDraw = new ArrayList<Boolean>();

    public Line (int color) {
        this.color = color;
    }

    public void addPoint (PointF p) {
        if (points.size() == 0 || isFarEnoughFromLastPoint(p)) {
            points.add(p);
            shouldDraw.add(true);
            boundingRectangle.updateBounds(p);
        }
    }

    private boolean isFarEnoughFromLastPoint(PointF p) {
        return Util.distance(p, points.get(points.size() - 1)) > MIN_POINT_DISTANCE;
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
        Line l = new Line(color);

        optimizedLines.add(l);

        for (int i = 0; i < points.size(); i++) {
            if (this.shouldDraw.get(i).booleanValue()) {
                l.addPoint(points.get(i));
            } else if (l.points.size() > 0) {
                //Do not add a line with only one point in it, those are useless
                if (l.points.size() == 1) {
                    optimizedLines.remove(l);
                }
                l = new Line(color);
                optimizedLines.add(l);
            }
        }

        return optimizedLines;
    }
}