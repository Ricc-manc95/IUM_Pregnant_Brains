// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthViewportController, MonthViewport

final class arg._cls3
    implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.stener
{

    private final MonthViewportController arg$1;
    private final double arg$2;
    private final float arg$3;

    public final void onStep(float f)
    {
        MonthViewportController monthviewportcontroller = arg$1;
        double d = arg$2;
        float f1 = arg$3;
        monthviewportcontroller.viewport.setFraction(d + (double)(f1 * f));
    }

    onListener(MonthViewportController monthviewportcontroller, double d, float f)
    {
        arg$1 = monthviewportcontroller;
        arg$2 = d;
        arg$3 = f;
    }
}
