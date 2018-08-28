// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.apps.calendar.timebox.AutoValue_Calendar;
import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.Item;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.CreationHandler;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.UncommittedEventDescriptor;
import com.google.common.base.Present;
import java.util.Calendar;

public final class CreationHandlerImpl
    implements CreationHandler
{

    private static final Object ENTRY_KEY = new Object();
    private final Calendar calendar = Calendar.getInstance();
    private final Context context;
    private final ObservableReference createEventPhantom;
    private final ObservableReference defaultCalendarColor;
    private final ObservableReference defaultDuration;
    private final TimeUtils timeUtils;

    public CreationHandlerImpl(Context context1, TimeUtils timeutils, ObservableReference observablereference, ObservableReference observablereference1, ObservableReference observablereference2)
    {
        context = context1;
        timeUtils = timeutils;
        defaultDuration = observablereference;
        createEventPhantom = observablereference1;
        defaultCalendarColor = observablereference2;
    }

    private final Item createPlaceholderItem()
    {
        Object obj = new AutoValue_Calendar(CalendarKey.NONE, CalendarAccessLevel.OWNER, null, null, null);
        obj = (new com.google.android.apps.calendar.timebox.AutoValue_EventItem_Event.Builder()).setAccessLevel(0).setInstanceModifiable(false).setSelfAttendeeStatus(com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED).setHasSmartMail(false).setHasImageData(false).setEndTimeUnspecified(false).setEveryoneDeclined(false).setOutOfOffice(false).setHasTimeProposals(false).setTitle(context.getResources().getString(0x7f130156)).setColor((Integer)defaultCalendarColor.get()).setCalendar(((com.google.android.apps.calendar.timebox.Calendar) (obj))).build();
        return (new com.google.android.apps.calendar.timebox.AutoValue_EventImpl.Builder()).setEventDescriptor(UncommittedEventDescriptor.INSTANCE).setEvent(((com.google.android.apps.calendar.timebox.EventItem.Event) (obj))).setSortType(com.google.android.apps.calendar.timebox.Item.SortType.EVENT).build();
    }

    public final void onDrag(long l, long l1)
    {
        ObservableReference observablereference = createEventPhantom;
        AutoValue_TimeRangeEntry autovalue_timerangeentry = new AutoValue_TimeRangeEntry(ENTRY_KEY, createPlaceholderItem(), TimeRange.newInstance(Utils.getTimeZone(context), false, l, l1));
        if (autovalue_timerangeentry == null)
        {
            throw new NullPointerException();
        } else
        {
            observablereference.set(new Present(autovalue_timerangeentry));
            return;
        }
    }

    public final void onTap(long l)
    {
        timeUtils.initCalendar(calendar);
        calendar.setTimeInMillis(l);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        l = calendar.getTimeInMillis();
        long l1 = ((Long)defaultDuration.get()).longValue();
        ObservableReference observablereference = createEventPhantom;
        AutoValue_TimeRangeEntry autovalue_timerangeentry = new AutoValue_TimeRangeEntry(ENTRY_KEY, createPlaceholderItem(), TimeRange.newInstance(Utils.getTimeZone(context), false, l, l1 + l));
        if (autovalue_timerangeentry == null)
        {
            throw new NullPointerException();
        } else
        {
            observablereference.set(new Present(autovalue_timerangeentry));
            return;
        }
    }

}
