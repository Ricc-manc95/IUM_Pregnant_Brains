// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.android.apps.calendar.graphics.DrawFunction;
import com.google.android.apps.calendar.graphics.RtlMirroring;
import com.google.android.calendar.utils.rtl.RtlContext;

// Referenced classes of package com.google.android.apps.calendar.graphics.drawable:
//            DrawableWrapper

public final class per extends DrawableWrapper
{

    private final DrawFunction val$drawFunction;
    private final RtlContext val$rtlContext;
    private final RtlMirroring val$rtlMirroring;

    public final void draw(Canvas canvas)
    {
        val$drawFunction.draw(canvas, getBounds().exactCenterX(), val$rtlContext, val$rtlMirroring);
    }

    public per(RtlMirroring rtlmirroring)
    {
        val$drawFunction = drawfunction;
        val$rtlContext = rtlcontext;
        val$rtlMirroring = rtlmirroring;
        super(final_drawable);
    }
}
