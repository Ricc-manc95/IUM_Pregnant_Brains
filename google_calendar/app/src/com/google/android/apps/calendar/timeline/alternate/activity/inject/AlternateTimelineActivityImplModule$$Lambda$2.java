// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.activity.inject;

import com.google.android.calendar.latency.LatencyLogger;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.activity.inject:
//            AlternateTimelineActivityImplModule

final class arg._cls2
    implements Runnable
{

    private final LatencyLogger arg$1;
    private final int arg$2;

    public final void run()
    {
        AlternateTimelineActivityImplModule.lambda$providesItemProviders$1$AlternateTimelineActivityImplModule(arg$1, arg$2);
    }

    (LatencyLogger latencylogger, int i)
    {
        arg$1 = latencylogger;
        arg$2 = i;
    }
}
