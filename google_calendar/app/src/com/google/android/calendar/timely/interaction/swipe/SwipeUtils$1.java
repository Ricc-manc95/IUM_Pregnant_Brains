// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction.swipe;

import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTaskType;

final class  extends TimelineItemOperation
{

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        return Boolean.valueOf(false);
    }

    public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
    {
        return Boolean.valueOf(timelineevent.calendarAccessLevel.isGreaterOrEqual(CalendarAccessLevel.WRITER));
    }

    public final Object onAnyReminder(TimelineTaskType timelinetasktype, Object aobj[])
    {
        boolean flag;
        if (!timelinetasktype.isDone())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
    }

    public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
    {
        boolean flag;
        if (((TimelineEvent) (timelinegroove)).calendarAccessLevel.isGreaterOrEqual(CalendarAccessLevel.OWNER) && !timelinegroove.completed)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
    }

    ()
    {
    }
}
