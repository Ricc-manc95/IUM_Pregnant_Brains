// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.util;

import android.graphics.Rect;

public class BitmapUtils
{

    public static void calculateCroppedSrcRect(int i, int j, int k, int l, int i1, int j1, float f, float f1, 
            boolean flag, float f2, Rect rect)
    {
        int k1 = j1;
        if (j1 <= 0)
        {
            k1 = 1;
        }
        float f4 = (float)i / (float)k;
        float f5 = (float)j / (float)l;
        float f3 = f4;
        if (f5 < f4)
        {
            f3 = f5 / f2;
        }
        f2 = Math.min(k1, f3);
        j1 = Math.round((float)k * f2);
        k = Math.round((float)l * f2);
        l = Math.round(f2 * (float)i1);
        i1 = Math.min(l, j) / 2;
        rect.left = (int)((float)(i - j1) * f);
        rect.right = j1 + rect.left;
        if (flag)
        {
            i = Math.max(i1, Math.min(j - i1, Math.round((float)j * f1)));
        } else
        {
            i = Math.round((float)Math.abs(j - l) * f1 + (float)i1);
        }
        rect.top = i - k / 2;
        rect.bottom = rect.top + k;
    }
}
