// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar:
//            CalendarApplication

final class moryRecording
    implements Consumer
{

    public static final Consumer $instance = new <init>();

    public final void accept(Object obj)
    {
        CalendarApplication.lambda$initializePerformanceMetrics$1$CalendarApplication((com.google.calendar.v2a.android.util.metric.n)obj);
    }


    private moryRecording()
    {
    }
}
