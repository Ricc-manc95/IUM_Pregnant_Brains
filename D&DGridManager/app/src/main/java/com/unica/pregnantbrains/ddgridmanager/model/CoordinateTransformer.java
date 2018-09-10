package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.PointF;
import android.support.design.widget.CoordinatorLayout;

/**
 * Defines a transformation from one 2D coordinate system to another coordinate
 * system.
 *
 * @author Pregnant Brains
 *
 */
public final class CoordinateTransformer {
    public float zoomLevel = 1.0f;
    public float originX = 0.0f;
    public float originY = 0.0f;

    public CoordinateTransformer(float originX, float originY, float zoomLevel) {
        this.originX = originX;
        this.originY = originY;
        this.zoomLevel = zoomLevel;
    }

    /**
     * Changes the scale of the transformation
     * @param scaleFactor Amount to change the zoom level by
     * @param invariant Screen space point that should map to the same world space
     * 		point before and after the transformation.
     */
    public void zoom(float scaleFactor, PointF invariant) {
        float lastZoomLevel = zoomLevel;
        float lastOriginX = originX;
        float lastOriginY = originY;

        zoomLevel *= scaleFactor;

        // Change the origin so that we zoom around the focus point.
        // Derived by assuming that the focus point should map to the same point in world space before and after the zoom.
        originX = invariant.x - (invariant.x - lastOriginX) * zoomLevel / lastZoomLevel;
        originY = invariant.y - (invariant.y - lastOriginY) * zoomLevel / lastZoomLevel;

    }

    /**
     * @param p
     *         point to transform
     * @return
     *         point transformed
     *
     */
    public PointF worldSpaceToScreenSpace(PointF p) {
        return worldSpaceToScreenSpace(p.x ,  p.y);
    }

    /**
     * @param p
     *         point to transform
     * @return
     *         point transformed
     *
     */
    public PointF screenSpaceToWorldSpace(PointF p) {
        return screenSpaceToWorldSpace(p.x, p.y);
    }

    public PointF worldSpaceToScreenSpace(float x, float y) {
        return new PointF(zoomLevel * x + originX, zoomLevel * y + originY);
    }

    public PointF screenSpaceToWorldSpace(float x, float y) {
        return new PointF((x - originX) / zoomLevel, (y - originY) / zoomLevel);
    }

    /**
     * @param f
     *         distance to transform
     * @return
     *         distance transformed
     *
     */
    public float worldSpaceToScreenSpace(float f) {
        return f * zoomLevel;
    }

    /**
     * @param f
     *         distance to transform
     * @return
     *         distance transformed
     *
     */
    public float screenSpaceToWorldSpace(float f) {
        return f / zoomLevel;
    }

    public CoordinateTransformer compose(CoordinateTransformer second) {
        return new CoordinateTransformer(
                second.worldSpaceToScreenSpace(originX) + second.originX,
                second.worldSpaceToScreenSpace(originY) + second.originY,
                zoomLevel * second.zoomLevel);
    }
    public PointF getOrigin() {
        return new PointF(originX, originY);
    }

    public void moveOrigin(float dx, float dy) {
        originX += dx;
        originY += dy;
    }

    public void setOriginInWorldSpace(float x, float y) {
        originX = x * zoomLevel;
        originY = y * zoomLevel;
    }

    public void setZoom(float zoomLevel) {
        this.zoomLevel = zoomLevel;

    }
}
