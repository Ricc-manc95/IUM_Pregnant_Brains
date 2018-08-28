// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.Display;
import android.view.WindowManager;

public final class LayoutDimensions
{

    public final Context context;
    private int screenWidth360;
    private int screenWidth411;
    public int screenWidth480;

    public LayoutDimensions(Context context1)
    {
        context = context1;
        screenWidth360 = context1.getResources().getDimensionPixelSize(0x7f0e034f);
        screenWidth411 = context1.getResources().getDimensionPixelSize(0x7f0e0350);
        screenWidth480 = context1.getResources().getDimensionPixelSize(0x7f0e0351);
    }

    public final RectF getMargin(boolean flag)
    {
        float f3;
        float f4;
        int i;
        int j;
        f4 = 0.0F;
        f3 = 0.0F;
        i = context.getResources().getDimensionPixelOffset(0x7f0e0213);
        j = context.getResources().getDimensionPixelOffset(0x7f0e0212);
        if (!flag) goto _L2; else goto _L1
_L1:
        float f2;
        float f5;
        f5 = i;
        f4 = 0.0F;
        f2 = 0.0F;
_L4:
        return new RectF(f2, f5, f3, f4);
_L2:
        Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int k = point.x;
        float f;
        float f1;
        if (k >= screenWidth360)
        {
            f1 = i;
            f = i;
            f2 = i;
            f3 = i;
        } else
        {
            f = 0.0F;
            f1 = 0.0F;
            f2 = 0.0F;
            f3 = f4;
        }
        f4 = f;
        f5 = f1;
        if (k >= screenWidth411)
        {
            f2 = j;
            f3 = j;
            f4 = f;
            f5 = f1;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final int getSurveyWidth()
    {
        Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        if (point.x < screenWidth480)
        {
            Display display1 = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
            Point point1 = new Point();
            display1.getSize(point1);
            return point1.x;
        } else
        {
            return context.getResources().getDimensionPixelSize(0x7f0e0225) + context.getResources().getDimensionPixelOffset(0x7f0e0212) * 2;
        }
    }
}
