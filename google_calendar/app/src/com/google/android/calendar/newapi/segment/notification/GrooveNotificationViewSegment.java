// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.notification;

import android.content.Context;
import android.view.View;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitInstance;
import com.google.android.calendar.api.habit.HabitReminders;
import com.google.android.calendar.event.ReminderUtils;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.model.GrooveHolder;
import com.google.android.calendar.newapi.model.GrooveInstanceHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import java.util.List;

public final class GrooveNotificationViewSegment extends TextTileView
    implements ViewSegment
{

    private final GrooveHolder model;

    public GrooveNotificationViewSegment(Context context, GrooveHolder grooveholder)
    {
        super(context);
        model = grooveholder;
    }

    private final String getSmartNotificationsString(HabitReminders habitreminders)
    {
        int i;
        if (habitreminders != null && habitreminders.enableFollowup)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        habitreminders = getContext();
        if (i != 0)
        {
            i = 0x7f13044c;
        } else
        {
            i = 0x7f13044b;
        }
        return habitreminders.getString(i);
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        setIconDrawable(0x7f020218);
        super.denseMode = false;
        setFocusable(true);
    }

    public final void updateUi()
    {
        Object obj1 = ((GrooveInstanceHolder)model).getHabitInstance();
        HabitReminders habitreminders = model.getHabit().getReminders();
        Object obj;
        boolean flag;
        if (habitreminders == null || habitreminders.useDefaultReminders)
        {
            obj = ((EventHolder)model).getEvent().getCalendarListEntry().getDefaultNotifications(1);
            if (((List) (obj)).isEmpty())
            {
                obj = null;
            } else
            {
                obj = Integer.valueOf(((Notification)((List) (obj)).get(0)).minutesBefore);
            }
        } else
        {
            obj = habitreminders.overrideMinutes;
        }
        if (((HabitInstance) (obj1)).getStatus() != 3 && obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj1 = new ReminderUtils(getContext());
        if (flag)
        {
            obj = ((ReminderUtils) (obj1)).constructLabel(((Integer) (obj)).intValue(), 1, false);
        } else
        {
            obj = getSmartNotificationsString(habitreminders);
        }
        setPrimaryText(new CharSequence[] {
            obj
        });
        if (flag)
        {
            obj = getSmartNotificationsString(habitreminders);
        } else
        {
            obj = "";
        }
        setSecondaryText(new CharSequence[] {
            obj
        });
        useTextAsContentDescription(0x7f13017a);
    }
}
