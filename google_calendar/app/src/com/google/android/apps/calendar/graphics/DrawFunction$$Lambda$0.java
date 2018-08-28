// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.google.android.calendar.utils.rtl.RtlContext;

// Referenced classes of package com.google.android.apps.calendar.graphics:
//            DrawFunction, RtlMirroring

public final class arg._cls1
    implements DrawFunction
{

    private final Drawable arg$1;

    public final void draw(Canvas canvas)
    {
        arg$1.draw(canvas);
    }

    public final void draw(Canvas canvas, float f, RtlContext rtlcontext, RtlMirroring rtlmirroring)
    {
        boolean flag;
        if (rtlcontext.isRtl() && rtlmirroring.mirrorInRtl())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        drawMirroredIf(canvas, f, flag);
    }

    public final void drawMirrored(Canvas canvas, float f)
    {
        canvas.save();
        canvas.scale(-1F, 1.0F, f, 0.0F);
        draw(canvas);
        canvas.restore();
    }

    public final void drawMirroredIf(Canvas canvas, float f, boolean flag)
    {
        if (flag)
        {
            drawMirrored(canvas, f);
            return;
        } else
        {
            draw(canvas);
            return;
        }
    }

    public (Drawable drawable)
    {
        arg$1 = drawable;
    }
}
