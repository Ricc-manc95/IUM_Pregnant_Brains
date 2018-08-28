// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.drawable;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

public final class ColorCircle extends ShapeDrawable
{

    public ColorCircle(Resources resources, int i)
    {
        super(new OvalShape());
        i = resources.getDimensionPixelSize(i);
        setIntrinsicHeight(i);
        setIntrinsicWidth(i);
        getPaint().setStrokeWidth(resources.getDimensionPixelSize(0x7f0e00b1));
    }

    public final ColorCircle setColor(int i)
    {
        getPaint().setColor(i);
        getPaint().setStyle(android.graphics.Paint.Style.FILL);
        return this;
    }
}
