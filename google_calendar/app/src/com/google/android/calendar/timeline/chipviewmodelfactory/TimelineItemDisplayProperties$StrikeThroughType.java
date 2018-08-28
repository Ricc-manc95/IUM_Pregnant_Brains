// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTaskType;

final class  extends TimelineItemOperation
{

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        return Integer.valueOf(0);
    }

    public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
    {
        byte byte0;
        if (timelineevent.hasDeclinedStatus())
        {
            byte0 = 2;
        } else
        {
            byte0 = 0;
        }
        return Integer.valueOf(byte0);
    }

    public final Object onAnyReminder(TimelineTaskType timelinetasktype, Object aobj[])
    {
        byte byte0;
        if (timelinetasktype.isDone())
        {
            byte0 = 2;
        } else
        {
            byte0 = 0;
        }
        return Integer.valueOf(byte0);
    }

    public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
    {
        int i;
        if (timelinegroove.completed)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return Integer.valueOf(i);
    }

    ()
    {
    }
}
