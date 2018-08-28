// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.settings.data;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

// Referenced classes of package com.google.android.calendar.timely.settings.data:
//            CalendarProperties, DefaultCalendarHelper

final class arg._cls1
    implements Consumer
{

    private final CalendarProperties arg$1;

    public final void accept(Object obj)
    {
        CalendarProperties calendarproperties = arg$1;
        calendarproperties.settings = (ImmutableMap)obj;
        obj = (CalendarDescriptor)DefaultCalendarHelper.readDefaultCalendarDescriptorToSharedPrefs((ImmutableSet)calendarproperties.calendars.keySet(), calendarproperties.context).orNull();
        if (obj != null)
        {
            obj = (CalendarListEntry)calendarproperties.calendars.get(obj);
            if (obj != null)
            {
                calendarproperties.setPropertyValue(3, Integer.valueOf(calendarproperties.getDefaultEventDuration(((CalendarListEntry) (obj)).getDescriptor().account)));
            }
        }
    }

    (CalendarProperties calendarproperties)
    {
        arg$1 = calendarproperties;
    }
}
