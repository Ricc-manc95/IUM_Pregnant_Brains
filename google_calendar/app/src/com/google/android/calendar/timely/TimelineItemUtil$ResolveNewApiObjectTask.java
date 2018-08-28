// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventKey;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItemOperation, TimelineItemUtil, TimelineEvent, TimelineGroove

static final class Q extends TimelineItemOperation
{

    public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
    {
        return CalendarApi.Events.read((EventKey)timelineevent.eventKey);
    }

    public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
    {
        return CalendarApi.Events.read((EventKey)((TimelineEvent) (timelinegroove)).eventKey);
    }

    Q()
    {
    }
}
