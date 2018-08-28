// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.accounts.Account;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.newapi.segment.calendar:
//            EventCalendarEditSegmentController, AutoValue_UiCalendarUtils_UiCalendarListEntry, CalendarFormatter

final class arg._cls1
    implements Function
{

    private final EventCalendarEditSegmentController arg$1;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (CalendarListEntry)obj;
        obj1 = ((EventCalendarEditSegmentController) (obj1)).formatter;
        return new AutoValue_UiCalendarUtils_UiCalendarListEntry(Utils.getCalendarNameToDisplay(((CalendarListEntry) (obj)).isPrimary(), ((CalendarListEntry) (obj)).getDisplayName(), ((CalendarListEntry) (obj)).getDescriptor().account.type, ((CalendarFormatter) (obj1)).defaultEventsTitle), ((CalendarListEntry) (obj)).getDescriptor().account.name, ((CalendarListEntry) (obj)).getColor().getValue(), ((CalendarListEntry) (obj)));
    }

    (EventCalendarEditSegmentController eventcalendareditsegmentcontroller)
    {
        arg$1 = eventcalendareditsegmentcontroller;
    }
}
