package com.unica.pregnantbrains.ddgridmanager.model.primitives;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.shapes.Shape;

import com.unica.pregnantbrains.ddgridmanager.model.BoundingRectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StraightLine extends Shape{
    /**
     * The color to draw this line with.
     */
    private int mColor = Color.BLACK;
    /**
     * The stroke width to draw this line with. +Infinity will use a fill
     * instead (to ensure that it draws beneath all lines).
     */
    private float mWidth;
    /**
     * Where to toggle the line on and off, for erasing purposes. These values
     * are parameterized by the length of the line, so that all values fall in
     * the range [0,1].
     */
    private List<Float> mLineToggleParameterization;
    /**
     * X component of the pending move operation.
     */
    private float mDrawOffsetDeltaX = Float.NaN;

    /**
     * Y component of the pending move operation.
     */
    private float mDrawOffsetDeltaY = Float.NaN;
    /**
     * Cached path that represents this line.
     */
    private transient Path mPath;
    /**
     * First endpoint on the line. X coordinate guaranteed to be less than x
     * coordinate of mEnd.
     */
    private PointF mStart;
    /**
     * Second endpoint on the line. X coordinate guaranteed to be greater than x
     * coordinate of mStart.
     */
    private PointF mEnd;
    /**
     * Cached rectangle that bounds all the points in this line. This could be
     * computed on demand, but it is easy enough to update every time a point is
     * added.
     */
    private BoundingRectangle mBoundingRectangle = new BoundingRectangle();
    private Paint mPaint;


    /**
     * Constructor.
     *
     * @param color
     *            Color of the line.
     * @param newLineStrokeWidth
     *            Stroke width of the line.
     */
    public StraightLine(int color, float newLineStrokeWidth) {
        this.setColor(color);
        this.setWidth(newLineStrokeWidth);
    }

    public void addPoint(PointF p) {
        if (this.mStart == null) {
            this.mStart = p;
        } else {
            this.mEnd = p;
            // Re-create the bounding rectangle every time this is done.
            this.getBoundingRectangle().clear();
            this.getBoundingRectangle().updateBounds(this.mStart);
            this.getBoundingRectangle().updateBounds(this.mEnd);
            this.invalidatePath();
        }
    }
    /**
     * Makes sure that start point's X coordinate occurs before the end point's
     * X coordinate. This assumption is used elsewhere.
     */
    private void canonicalizePointOrder() {
        if (this.mEnd.x < this.mStart.x) {
            PointF tmp;
            tmp = this.mStart;
            this.mStart = this.mEnd;
            this.mEnd = tmp;
        }
    }
    public boolean contains(PointF p) {
        // Cannot define a region.
        return false;
    }
    public Path createPath() {
        if (this.mStart == null || this.mEnd == null) {
            return null;
        }
        Path path = new Path();

        if (this.mLineToggleParameterization != null) {
            // Erasing has happened, follow erasing instructions.
            boolean on = false;
            for (float toggleT : this.mLineToggleParameterization) {
                PointF togglePoint = this.parameterizationToPoint(toggleT);
                if (on) {
                    path.lineTo(togglePoint.x, togglePoint.y);
                } else {
                    path.moveTo(togglePoint.x, togglePoint.y);
                }
                on = !on;
            }
        } else {
            path.moveTo(this.mStart.x, this.mStart.y);
            path.lineTo(this.mEnd.x, this.mEnd.y);
        }

        return path;
    }

    public void erase(PointF center, float radius) {
        if (this.mStart == null
                || this.mEnd == null
                || !this.getBoundingRectangle().intersectsWithCircle(center,
                radius)) {
            return;
        }

        this.canonicalizePointOrder();

        // Special case - if we have only two points, this is probably
        // a large straight line and we want to erase the line if the
        // eraser intersects with it. However, this is an expensive
        // test, so we don't want to do it for all line segments when
        // they are generally small enough for the eraser to enclose.
        Util.IntersectionPair intersection =
                Util.lineCircleIntersection(this.mStart, this.mEnd, center,
                        radius);

        if (intersection != null) {
            float intersect1T =
                    this.pointToParameterization(intersection
                            .getIntersection1());
            float intersect2T =
                    this.pointToParameterization(intersection
                            .getIntersection2());

            this.insertErasedSegment(intersect1T, intersect2T);
            this.invalidatePath();
        }
    }
    /**
     *
     * @param segmentStart
     *            Start of the erased segment, parameterized by the length of
     *            the line.
     * @param segmentEnd
     *            End of the erased segment, parameterized by the length of the
     *            line.
     */
    void insertErasedSegment(float segmentStart, float segmentEnd) {
        // Make sure first intersections are ordered
        float tmp;
        if (segmentStart > segmentEnd) {
            tmp = segmentStart;
            segmentStart = segmentEnd;
            segmentEnd = tmp;
        }

        if (this.mLineToggleParameterization == null) {
            this.mLineToggleParameterization = new ArrayList<Float>();
            this.mLineToggleParameterization.add(0f);
            this.mLineToggleParameterization.add(1f);
        }

        // Location in the array before which to insert the first segment
        int segmentStartInsertion =
                Collections.binarySearch(this.mLineToggleParameterization,
                        segmentStart);
        if (segmentStartInsertion < 0) {
            segmentStartInsertion = -segmentStartInsertion - 1;
        }
        boolean startInDrawnRegion = segmentStartInsertion % 2 != 0;

        // Location in the array before which to insert the last segment.
        int segmentEndInsertion =
                -Collections.binarySearch(this.mLineToggleParameterization,
                        segmentEnd) - 1;
        if (segmentEndInsertion < 0) {
            segmentEndInsertion = -segmentEndInsertion - 1;
        }
        boolean endInDrawnRegion = segmentEndInsertion % 2 != 0;

        // Remove all segment starts or ends between the insertion points.
        // If we were to run the binary search again, segmentStartInsertion
        // should
        // remain unchanged and segmentEndInsertion should be equal to
        // segmentStartInsertion.
        // Guard this by making sure we don't try to remove from the end of the
        // list.
        if (segmentStartInsertion != this.mLineToggleParameterization.size()) {
            for (int i = 0; i < segmentEndInsertion - segmentStartInsertion; ++i) {
                this.mLineToggleParameterization.remove(segmentStartInsertion);
            }
        }

        if (endInDrawnRegion) {
            this.mLineToggleParameterization.add(segmentStartInsertion,
                    segmentEnd);
        }

        if (startInDrawnRegion) {
            this.mLineToggleParameterization.add(segmentStartInsertion,
                    segmentStart);
        }
    }
    public boolean isValid() {
        return this.mStart != null && this.mEnd != null;
    }

    public boolean needsOptimization() {
        return this.mLineToggleParameterization != null;
    }

    /**
     * Given a float in the range [0,1] that represents a distance along this
     * line scaled to the length of the line, returns a the coordinates where
     * that distance occurs on the line.
     *
     * @param t
     *            The parameterized distance.
     * @return The point where that parameterization occurs on the line.
     */
    private PointF parameterizationToPoint(float t) {
        return new PointF(this.mStart.x + t * (this.mEnd.x - this.mStart.x),
                this.mStart.y + t * (this.mEnd.y - this.mStart.y));
    }

    /**
     * Given a point, gives a distance scaled to the length of the line where
     * that point falls on the line.
     *
     * @param p
     *            The point to parameterize. Must fall on the line.
     * @return Distance along the line segment where the point falls, scaled to
     *         the range [0,1].
     */
    private float pointToParameterization(PointF p) {
        if (Math.abs(this.mEnd.y - this.mStart.y) > Math.abs(this.mEnd.x
                - this.mStart.x)) {
            return (p.y - this.mStart.y) / (this.mEnd.y - this.mStart.y);
        } else {
            return (p.x - this.mStart.x) / (this.mEnd.x - this.mStart.x);
        }
    }

    public List<Shape> removeErasedPoints() {
        List<Shape> shapes = new ArrayList<Shape>();

        if (this.mLineToggleParameterization.size() > 0) {
            for (int i = 0; i < this.mLineToggleParameterization.size(); i += 2) {
                float startT = this.mLineToggleParameterization.get(i);
                float endT = this.mLineToggleParameterization.get(i + 1);

                StraightLine l =
                        new StraightLine(this.getColor(), this.getWidth());
                l.addPoint(this.parameterizationToPoint(startT));
                l.addPoint(this.parameterizationToPoint(endT));
                shapes.add(l);
            }
        }
        this.mLineToggleParameterization = null;
        this.invalidatePath();
        return shapes;
    }




    protected Shape getMovedShape(float deltaX, float deltaY) {
        StraightLine l = new StraightLine(getColor(), this.getStrokeWidth());
        l.mStart = new PointF(this.mStart.x + deltaX, this.mStart.y + deltaY);
        l.mEnd = new PointF(this.mEnd.x + deltaX, this.mEnd.y + deltaY);
        l.getBoundingRectangle().updateBounds(this.getBoundingRectangle());
        l.getBoundingRectangle().move(deltaX, deltaY);
        return l;
    }
    /**
     * @return Whether this shape has a temporary pending move operation.
     */
    public boolean hasOffset() {
        return this.mDrawOffsetDeltaX == this.mDrawOffsetDeltaX;
    }
    /**
     * Changes the given canvas's transformation to apply this draw offset.
     *
     * @param c The canvas to modify.
     */
    public void applyDrawOffsetToCanvas(Canvas c) {
        if (this.hasOffset()) {
            c.save();
            c.translate(this.mDrawOffsetDeltaX, this.mDrawOffsetDeltaY);
        }
    }
    @Override
    public void draw(Canvas canvas, Paint paint) {

    }

    /**
            * @return Whether this shape has a temporary pending move operation.
     */
    public boolean hasOffset(StraightLine line) {
        return this.mDrawOffsetDeltaX == this.mDrawOffsetDeltaX;
    }
    /**
     * Draws the line on the given canvas.
     *
     * @param c Canvas to draw on.
     */
    public void draw(final Canvas c) {
        this.ensurePaintCreated();
        this.ensurePathCreated();
        if (this.mPath != null) {
            c.drawPath(this.mPath, this.mPaint);
        }
    }
    /**
     * If there is no Paint object cached for this line, create one and set the
     * appropriate color and stroke width.
     */
    protected void ensurePaintCreated() {
        if (this.mPaint == null) {
            this.mPaint = new Paint();
            this.mPaint.setColor(this.mColor);
            if (this.getWidth() == Float.POSITIVE_INFINITY) {
                this.mPaint.setStyle(Paint.Style.FILL);
            } else {
                this.mPaint.setStrokeWidth(this.getWidth());
                this.mPaint.setStyle(Paint.Style.STROKE);
            }
        }
    }
    /**
     * Changes the given canvas's transformation to remove this draw offset.
     *
     * @param c The canvas to modify.
     */
    public void revertDrawOffsetFromCanvas(Canvas c) {
        if (this.hasOffset()) {
            c.restore();
        }
    }

    /**
     * Creates the path if it is currently invalid.
     */
    private void ensurePathCreated() {
        if (this.mPath == null) {
            this.mPath = this.createPath();
        }
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public void setWidth(float width) {
        this.mWidth = width;
    }

    public BoundingRectangle getBoundingRectangle() {
        return this.mBoundingRectangle;
    }
    /**
     * Invalidates the path so that it is recreated on the next draw operation.
     */
    protected void invalidatePath() {
        this.mPath = null;
    }

    public int getColor() {
        return this.mColor;
    }

    public float getStrokeWidth() {
        return this.mWidth;
    }
}
