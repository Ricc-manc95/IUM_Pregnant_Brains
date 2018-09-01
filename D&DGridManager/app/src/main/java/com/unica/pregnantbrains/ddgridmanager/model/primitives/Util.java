package com.unica.pregnantbrains.ddgridmanager.model.primitives;

import android.graphics.PointF;

/**
 * Random graphics utilities.
 *
 * @author Pregnant Brains
 *
 */
public final class Util {

    public static float distance(PointF p1, PointF p2) {
        return (float)Math.sqrt((double)(p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
    }
}

