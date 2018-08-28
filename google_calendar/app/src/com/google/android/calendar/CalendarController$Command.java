// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.calendar.time.Time;

// Referenced classes of package com.google.android.calendar:
//            CalendarController

public static final class type
{

    public Time endTime;
    public long extraLong;
    public Time selectedTime;
    public Time startTime;
    public long type;

    public final String toString()
    {
        String s;
        StringBuilder stringbuilder;
        s = "Unknown";
        stringbuilder = new StringBuilder();
        if ((type & 32L) == 0L) goto _L2; else goto _L1
_L1:
        s = "Go to time/event";
_L4:
        stringbuilder.append(s);
        stringbuilder.append(": uri=");
        stringbuilder.append(": id=");
        stringbuilder.append(0L);
        stringbuilder.append(", selected=");
        stringbuilder.append(selectedTime);
        stringbuilder.append(", start=");
        stringbuilder.append(startTime);
        stringbuilder.append(", end=");
        stringbuilder.append(endTime);
        stringbuilder.append(", x=");
        stringbuilder.append(0);
        stringbuilder.append(", y=");
        stringbuilder.append(0);
        return stringbuilder.toString();
_L2:
        if ((type & 128L) != 0L)
        {
            s = "Refresh events";
        } else
        if ((type & 512L) != 0L)
        {
            s = "Gone home";
        } else
        if ((type & 1024L) != 0L)
        {
            s = "Update title";
        } else
        if ((type & 8192L) != 0L)
        {
            s = "Update alternate month range";
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public (long l)
    {
        type = l;
    }
}
