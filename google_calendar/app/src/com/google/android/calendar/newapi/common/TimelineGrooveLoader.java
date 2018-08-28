// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import android.content.Context;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitFactory;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.api.habit.HabitReminders;
import com.google.android.calendar.groove.GrooveUtils;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.calendar.newapi.common:
//            AsyncTaskLoader

public final class TimelineGrooveLoader extends AsyncTaskLoader
{

    private Context context;
    private HabitClient habitClient;
    private TimelineGroove timelineItem;

    public TimelineGrooveLoader(Context context1, TimelineGroove timelinegroove)
    {
        habitClient = CalendarApi.Habits;
        context = context1;
        timelineItem = timelinegroove;
    }

    protected final Object runInBackground(Object aobj[])
    {
        aobj = (com.google.android.calendar.api.habit.HabitClient.ReadResult)habitClient.read(timelineItem.descriptor).await();
        boolean flag;
        if (((com.google.android.calendar.api.habit.HabitClient.ReadResult) (aobj)).getStatus().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            aobj = ((com.google.android.calendar.api.habit.HabitClient.ReadResult) (aobj)).getStatus().zzaIk;
            super.success = false;
            super.failureMessage = ((String) (aobj));
            return null;
        }
        aobj = ((com.google.android.calendar.api.habit.HabitClient.ReadResult) (aobj)).getHabit();
        if (aobj == null)
        {
            return null;
        }
        timelineItem.mods = CalendarApi.HabitFactory.modifyHabit(((Habit) (aobj)));
        TimelineGroove timelinegroove = timelineItem;
        Object obj = timelineItem;
        int i;
        if (((TimelineGroove) (obj)).mods != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = -1;
        } else
        {
            HabitReminders habitreminders = ((TimelineGroove) (obj)).mods.getReminders();
            if (habitreminders != null && !habitreminders.useDefaultReminders)
            {
                if (habitreminders.overrideMinutes == null)
                {
                    i = -1;
                } else
                {
                    i = habitreminders.overrideMinutes.intValue();
                }
            } else
            {
                obj = ((TimelineGroove) (obj)).descriptor;
                i = GrooveUtils.getDefaultReminderMinutes(context, ((HabitDescriptor) (obj)).calendar.account, ((HabitDescriptor) (obj)).calendar.calendarId);
            }
        }
        timelinegroove.preInstanceMinutes = i;
        timelineItem.type = Integer.valueOf(((Habit) (aobj)).getType());
        return ((Object) (aobj));
    }
}
