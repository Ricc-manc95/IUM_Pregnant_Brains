// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction.swipe;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timely.TimelineItem;

public class SwipeUtils
{

    public SwipeUtils()
    {
    }

    public static int getSupportedSwipeDirections(TimelineItem timelineitem)
    {
        return !((Boolean)timelineitem.perform(new _cls1(), new Void[0])).booleanValue() ? 0 : 1;
    }

    public static Integer getSwipeIcon(TimelineItem timelineitem, int i)
    {
        if (i != 1)
        {
            return null;
        } else
        {
            return Integer.valueOf(((Integer)timelineitem.perform(new _cls2(), new Void[0])).intValue());
        }
    }

    static 
    {
        LogUtils.getLogTag(com/google/android/calendar/timely/interaction/swipe/SwipeUtils);
    }

    private class _cls1 extends TimelineItemOperation
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

        _cls1()
        {
        }
    }


    private class _cls2 extends TimelineItemOperation
    {

        public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
        {
            return Integer.valueOf(0x7f0201de);
        }

        public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
        {
            return Integer.valueOf(0x7f0201e3);
        }

        public final Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
        {
            return Integer.valueOf(0x7f020238);
        }

        public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
        {
            return Integer.valueOf(0x7f0201e3);
        }

        _cls2()
        {
        }
    }

}
