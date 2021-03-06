package com.unica.pregnantbrains.ddgridmanager.model;


import java.io.Serializable;
import java.util.Collection;

public class BoundingRectangle implements Serializable{
    private static final long serialVersionUID = -1795363421941586135L;

    private float boundsXMin = Float.MAX_VALUE;
    private float boundsXMax = Float.MIN_VALUE;
    private float boundsYMin = Float.MAX_VALUE;
    private float boundsYMax = Float.MIN_VALUE;

    public float getXMin() {
        return boundsXMin;
    }
    public float getXMax() {
        return boundsXMax;
    }
    public float getYMin() {
        return boundsYMin;
    }
    public float getYMax() {
        return boundsYMax;
    }

    public float getWidth() {
        return boundsXMax - boundsXMin;
    }

    public float getHeight() {
        return boundsYMax - boundsYMin;
    }

    public void updateBounds(PointF p) {
        this.boundsXMin = Math.min(this.boundsXMin, p.x);
        this.boundsXMax = Math.max(this.boundsXMax, p.x);
        this.boundsYMin = Math.min(this.boundsYMin, p.y);
        this.boundsYMax = Math.max(this.boundsYMax, p.y);
    }

    public void updateBounds(BoundingRectangle other) {
        this.boundsXMin = Math.min(this.boundsXMin, other.boundsXMin);
        this.boundsXMax = Math.max(this.boundsXMax, other.boundsXMax);
        this.boundsYMin = Math.min(this.boundsYMin, other.boundsYMin);
        this.boundsYMax = Math.max(this.boundsYMax, other.boundsYMax);
    }

    public boolean contains(PointF p) {
        return p.x >= boundsXMin &&
               p.x <= boundsXMax &&
               p.y >= boundsYMin &&
               p.y <= boundsYMax;
    }

    public boolean intersectsWithCircle(PointF center, float radius) {
        return center.x + radius >= boundsXMin &&
               center.x - radius <= boundsXMax &&
               center.y + radius >= boundsYMin &&
               center.y - radius <= boundsYMax;
    }

    /**
     * Updates the bounds for an entire collection of points.
     * @param points The points to update with.
     */
    public void updateBounds(final Collection<PointF> points) {
        for (PointF p : points) {
            updateBounds(p);
        }
    }
}
