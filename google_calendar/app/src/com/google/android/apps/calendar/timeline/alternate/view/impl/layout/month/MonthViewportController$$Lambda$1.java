// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthViewportController, MonthViewport

final class arg._cls1
    implements Function
{

    private final MonthViewportController arg$1;

    public final Object apply(Object obj)
    {
        obj = arg$1.viewport;
        ((MonthViewport) (obj)).setFraction(Math.round(((MonthViewport) (obj)).startFraction));
        return new Object();
    }

    (MonthViewportController monthviewportcontroller)
    {
        arg$1 = monthviewportcontroller;
    }
}
