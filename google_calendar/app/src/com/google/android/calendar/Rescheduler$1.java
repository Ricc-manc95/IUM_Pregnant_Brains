// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTask;

final class eItemOperation extends TimelineItemOperation
{

    private final Resources val$resources;

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        return val$resources.getString(0x7f130102);
    }

    public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
    {
label0:
        {
            boolean flag = false;
            if (timelineevent.getClass() != com/google/android/calendar/timely/TimelineEvent)
            {
                return (String)super.onAnyEvent(timelineevent, new Void[0]);
            }
            if (timelineevent.isInstanceModifiable)
            {
                aobj = timelineevent.calendarAccessLevel;
                CalendarAccessLevel calendaraccesslevel = CalendarAccessLevel.WRITER;
                if (calendaraccesslevel == null)
                {
                    throw new NullPointerException();
                }
                if (((CalendarAccessLevel) (aobj)).level - calendaraccesslevel.level < 0)
                {
                    flag = true;
                }
                if (!flag && TextUtils.equals(timelineevent.organizer, timelineevent.ownerAccount))
                {
                    break label0;
                }
            }
            return val$resources.getString(0x7f130102);
        }
        return null;
    }

    public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
    {
        timelinegroove = ((TimelineEvent) (timelinegroove)).calendarAccessLevel;
        aobj = CalendarAccessLevel.WRITER;
        if (aobj == null)
        {
            throw new NullPointerException();
        }
        boolean flag;
        if (((CalendarAccessLevel) (timelinegroove)).level - ((CalendarAccessLevel) (aobj)).level < 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return val$resources.getString(0x7f130102);
        } else
        {
            return null;
        }
    }

    public final volatile Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
    {
        return null;
    }

    eTask()
    {
        val$resources = resources1;
        super();
    }
}
