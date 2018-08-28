// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.util;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;

public final class RectUtils
{

    public static void rotateRectForOrientation(int i, Rect rect, Rect rect1)
    {
        Matrix matrix = new Matrix();
        matrix.setRotate(-i);
        RectF rectf = new RectF(rect);
        RectF rectf1 = new RectF(rect1);
        matrix.mapRect(rectf);
        matrix.mapRect(rectf1);
        matrix.reset();
        matrix.setTranslate(-rectf.left, -rectf.top);
        matrix.mapRect(rectf);
        matrix.mapRect(rectf1);
        rect.set((int)rectf.left, (int)rectf.top, (int)rectf.right, (int)rectf.bottom);
        rect1.set((int)rectf1.left, (int)rectf1.top, (int)rectf1.right, (int)rectf1.bottom);
    }
}
