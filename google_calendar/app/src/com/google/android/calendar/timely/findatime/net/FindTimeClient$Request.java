// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.net;

import com.google.android.calendar.timely.FindTimeTimeframe;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public final class existingEventCalendarId
{

    public final ImmutableList attendees;
    public final String calendarEventReference;
    public final boolean considerExistingRooms;
    public final String existingEventCalendarId;
    public final String existingEventId;
    public final FindTimeTimeframe timeframe;
    public final TimeZone timezone;

    public final boolean equals(Object obj)
    {
        boolean flag1 = true;
        if (obj != null && (obj instanceof existingEventCalendarId)) goto _L2; else goto _L1
_L1:
        flag1 = false;
_L4:
        return flag1;
_L2:
        obj = (existingEventCalendarId)obj;
        Object obj1 = timeframe;
        Object obj2 = ((timeframe) (obj)).timeframe;
        boolean flag;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || !attendees.equals(((attendees) (obj)).attendees) || !timezone.getID().equals(((timezone) (obj)).timezone.getID()) || considerExistingRooms != ((considerExistingRooms) (obj)).considerExistingRooms)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = calendarEventReference;
        obj2 = ((calendarEventReference) (obj)).calendarEventReference;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = existingEventId;
        obj2 = ((existingEventId) (obj)).existingEventId;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = existingEventCalendarId;
        obj = ((existingEventCalendarId) (obj)).existingEventCalendarId;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L4; else goto _L3
_L3:
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            attendees, timezone, Boolean.valueOf(considerExistingRooms), calendarEventReference, existingEventId, existingEventCalendarId, timeframe
        });
    }

    public (List list, FindTimeTimeframe findtimetimeframe, TimeZone timezone1, boolean flag, String s, String s1, String s2)
    {
        attendees = ImmutableList.copyOf(list);
        timeframe = findtimetimeframe;
        timezone = timezone1;
        considerExistingRooms = flag;
        calendarEventReference = s;
        existingEventId = s1;
        existingEventCalendarId = s2;
    }
}
