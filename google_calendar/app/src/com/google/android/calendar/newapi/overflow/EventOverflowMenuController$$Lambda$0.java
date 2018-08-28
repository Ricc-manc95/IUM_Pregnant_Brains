// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.newapi.overflow:
//            OverflowMenuController, EventOverflowMenuController

final class arg._cls1
    implements Predicate
{

    private final EventOverflowMenuController arg$1;

    public final boolean apply(Object obj)
    {
        EventOverflowMenuController eventoverflowmenucontroller = arg$1;
        obj = (CalendarListEntry)obj;
        return ((CalendarListEntry) (obj)).getAccessLevel().isGreaterOrEqual(CalendarAccessLevel.WRITER) && !((CalendarListEntry) (obj)).getDescriptor().matches(((EventHolder)((OverflowMenuController) (eventoverflowmenucontroller)).model).getEvent().getCalendarListEntry().getDescriptor());
    }

    (EventOverflowMenuController eventoverflowmenucontroller)
    {
        arg$1 = eventoverflowmenucontroller;
    }
}
