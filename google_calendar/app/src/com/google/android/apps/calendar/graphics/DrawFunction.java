// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics;

import android.graphics.Canvas;
import com.google.android.calendar.utils.rtl.RtlContext;

// Referenced classes of package com.google.android.apps.calendar.graphics:
//            RtlMirroring

public interface DrawFunction
{

    public abstract void draw(Canvas canvas);

    public abstract void draw(Canvas canvas, float f, RtlContext rtlcontext, RtlMirroring rtlmirroring);

    public abstract void drawMirrored(Canvas canvas, float f);

    public abstract void drawMirroredIf(Canvas canvas, float f, boolean flag);
}
