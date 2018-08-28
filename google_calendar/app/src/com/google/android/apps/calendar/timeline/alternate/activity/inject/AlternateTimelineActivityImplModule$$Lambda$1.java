// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.activity.inject;

import com.google.android.apps.calendar.timeline.alternate.view.api.ItemProvider;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.activity.inject:
//            AlternateTimelineActivityImplModule

final class arg._cls3
    implements ItemProvider
{

    private final AtomicInteger arg$1;
    private final LatencyLogger arg$2;
    private final ItemProvider arg$3;

    public final FluentFuture loadItems(int i, int j)
    {
        return AlternateTimelineActivityImplModule.lambda$providesItemProviders$2$AlternateTimelineActivityImplModule(arg$1, arg$2, arg$3, i, j);
    }

    (AtomicInteger atomicinteger, LatencyLogger latencylogger, ItemProvider itemprovider)
    {
        arg$1 = atomicinteger;
        arg$2 = latencylogger;
        arg$3 = itemprovider;
    }
}
