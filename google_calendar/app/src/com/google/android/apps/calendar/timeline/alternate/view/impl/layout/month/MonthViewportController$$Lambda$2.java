// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthViewportController, MonthViewport

final class arg._cls1
    implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.tener
{

    private final MonthViewportController arg$1;

    public final void scrollPx(int i)
    {
        MonthViewportController monthviewportcontroller = arg$1;
        MonthViewport monthviewport = monthviewportcontroller.viewport;
        float f = (float)i / (float)monthviewportcontroller.viewport.width;
        double d = monthviewport.startFraction;
        if (monthviewport.isRtl)
        {
            i = -1;
        } else
        {
            i = 1;
        }
        monthviewport.setFraction((double)((float)i * f) + d);
    }

    nListener(MonthViewportController monthviewportcontroller)
    {
        arg$1 = monthviewportcontroller;
    }
}
