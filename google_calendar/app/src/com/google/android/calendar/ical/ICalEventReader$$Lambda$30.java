// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventReader

final class arg._cls2
    implements Function
{

    private final ICalEventReader arg$1;
    private final Optional arg$2;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj1 = arg$2;
        obj = (Event[])obj;
        obj1 = (Long)((Optional) (obj1)).orNull();
        HashMap hashmap = new HashMap(obj.length);
        int j = obj.length;
        for (int i = 0; i < j; i++)
        {
            Event event = obj[i];
            if (event.getDescriptor().isRecurringException() && !ICalEventReader.originalStartMatches(event, ((Long) (obj1))))
            {
                continue;
            }
            com.google.android.calendar.api.calendarlist.CalendarDescriptor calendardescriptor = event.getCalendarListEntry().getDescriptor();
            if (!hashmap.containsKey(calendardescriptor) || ICalEventReader.originalStartMatches(event, ((Long) (obj1))))
            {
                hashmap.put(calendardescriptor, event);
            }
        }

        return hashmap.values();
    }

    ry(ICalEventReader icaleventreader, Optional optional)
    {
        arg$1 = icaleventreader;
        arg$2 = optional;
    }
}
