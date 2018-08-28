// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;


// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            CalendarEvent

abstract class $AutoValue_CalendarEvent extends CalendarEvent
{

    private final String calendarId;
    private final String eventId;

    $AutoValue_CalendarEvent(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException("Null calendarId");
        } else
        {
            calendarId = s;
            eventId = s1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof CalendarEvent))
        {
            break MISSING_BLOCK_LABEL_65;
        }
        obj = (CalendarEvent)obj;
        if (!calendarId.equals(((CalendarEvent) (obj)).getCalendarId()))
        {
            break; /* Loop/switch isn't completed */
        }
        if (eventId != null) goto _L4; else goto _L3
_L3:
        if (((CalendarEvent) (obj)).getEventId() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!eventId.equals(((CalendarEvent) (obj)).getEventId())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final String getCalendarId()
    {
        return calendarId;
    }

    public final String getEventId()
    {
        return eventId;
    }

    public int hashCode()
    {
        int j = calendarId.hashCode();
        int i;
        if (eventId == null)
        {
            i = 0;
        } else
        {
            i = eventId.hashCode();
        }
        return i ^ 0xf4243 * (j ^ 0xf4243);
    }

    public String toString()
    {
        String s = calendarId;
        String s1 = eventId;
        return (new StringBuilder(String.valueOf(s).length() + 36 + String.valueOf(s1).length())).append("CalendarEvent{calendarId=").append(s).append(", eventId=").append(s1).append("}").toString();
    }
}
