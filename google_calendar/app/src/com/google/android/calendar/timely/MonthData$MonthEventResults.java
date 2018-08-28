// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthData, TimelineItem

static final class events
    implements events
{

    public final ArrayList events;

    public final void addTimelineItem(TimelineItem timelineitem)
    {
        events.add(timelineitem);
    }

    (int i)
    {
        events = new ArrayList(i);
    }
}
