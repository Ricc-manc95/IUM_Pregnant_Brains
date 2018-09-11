package com.unica.pregnantbrains.ddgridmanager.model;

import com.unica.pregnantbrains.ddgridmanager.model.PointF;

/**
 * Random graphics utilities.
 *
 * @author Pregnant Brains
 *
 */
public final class Util {

    public static float distance(PointF p1, PointF p2) {
        //return (float)Math.sqrt((double)(p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
        return distance(p1.x, p1.y, p2.x, p2.y);
    }

    public static float distance(float x1, float y1, float x2, float y2) {
        return (float)Math.sqrt((double)(x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
}

