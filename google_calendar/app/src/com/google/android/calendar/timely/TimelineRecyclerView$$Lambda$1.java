// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineRecyclerView

final class arg._cls1
    implements Runnable
{

    private final TimelineRecyclerView arg$1;

    public final void run()
    {
        TimelineRecyclerView timelinerecyclerview = arg$1;
        Time time = timelinerecyclerview.recycleTime;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        time.set(l);
        timelinerecyclerview.goTo(timelinerecyclerview.recycleTime, true);
        timelinerecyclerview.isPositioningToNow = false;
    }

    (TimelineRecyclerView timelinerecyclerview)
    {
        arg$1 = timelinerecyclerview;
    }
}
