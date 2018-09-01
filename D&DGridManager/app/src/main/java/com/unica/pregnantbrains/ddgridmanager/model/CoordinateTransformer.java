package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.PointF;

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

    /**
     * @param p
     *         point to transform
     * @return
     *         point transformed
     *
     */
    public PointF worldSpaceToScreenSpace(PointF p) {
        return new PointF(zoomLevel * p.x + originX, zoomLevel * p.y + originY);
    }

    /**
     * @param p
     *         point to transform
     * @return
     *         point transformed
     *
     */
    public PointF screenSpaceToWorldSpace(PointF p) {
        return new PointF((p.x - originX) / zoomLevel, (p.y - originY) / zoomLevel);
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
}
