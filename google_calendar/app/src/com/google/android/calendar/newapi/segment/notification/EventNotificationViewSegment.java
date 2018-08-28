// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.notification;

import android.content.Context;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.event.ReminderUtils;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class EventNotificationViewSegment extends TextTileView
    implements ViewSegment
{

    private final EventHolder model;
    private final ReminderUtils reminderUtils = new ReminderUtils(getContext());

    public EventNotificationViewSegment(Context context, EventHolder eventholder)
    {
        super(context);
        indentContent();
        setIconDrawable(0x7f020218);
        super.denseMode = false;
        setFocusable(true);
        model = eventholder;
    }

    private final List getDefaultNotifications()
    {
        CalendarListEntry calendarlistentry = model.getEvent().getCalendarListEntry();
        byte byte0;
        if (model.getEvent().isAllDayEvent())
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        return calendarlistentry.getDefaultNotifications(byte0);
    }

    public final void updateUi()
    {
        boolean flag;
        if (model.getEvent().getNotifications() == null)
        {
            flag = getDefaultNotifications().isEmpty();
        } else
        {
            flag = model.getEvent().getNotifications().isEmpty();
        }
        if (flag)
        {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        CharSequence acharsequence[] = new ArrayList();
        Object obj = model.getEvent().getNotifications();
        if (obj == null)
        {
            Notification notification;
            for (obj = getDefaultNotifications().iterator(); ((Iterator) (obj)).hasNext(); acharsequence.add(reminderUtils.constructLabel(notification.minutesBefore, notification.method, model.getEvent().isAllDayEvent())))
            {
                notification = (Notification)((Iterator) (obj)).next();
            }

        } else
        {
            obj = ((List) (obj)).iterator();
            while (((Iterator) (obj)).hasNext()) 
            {
                Notification notification1 = (Notification)((Iterator) (obj)).next();
                acharsequence.add(reminderUtils.constructLabel(notification1.minutesBefore, notification1.method, model.getEvent().isAllDayEvent()));
            }
        }
        if (acharsequence == null)
        {
            acharsequence = null;
        } else
        {
            acharsequence = (CharSequence[])acharsequence.toArray(new CharSequence[acharsequence.size()]);
        }
        setPrimaryText(acharsequence);
        useTextAsContentDescription(0x7f13017a);
    }
}
