// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;


final class AutoValue_EventResult_Timespan extends EventResult.Timespan
{

    private final boolean allDay;
    private final long endMs;
    private final long startMs;

    AutoValue_EventResult_Timespan(long l, long l1, boolean flag)
    {
        startMs = l;
        endMs = l1;
        allDay = flag;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof EventResult.Timespan)
            {
                if (startMs != ((EventResult.Timespan) (obj = (EventResult.Timespan)obj)).getStartMs() || endMs != ((EventResult.Timespan) (obj)).getEndMs() || allDay != ((EventResult.Timespan) (obj)).isAllDay())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final long getEndMs()
    {
        return endMs;
    }

    public final long getStartMs()
    {
        return startMs;
    }

    public final int hashCode()
    {
        int i = (int)(startMs >>> 32 ^ startMs);
        int j = (int)(endMs >>> 32 ^ endMs);
        char c;
        if (allDay)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return c ^ ((i ^ 0xf4243) * 0xf4243 ^ j) * 0xf4243;
    }

    public final boolean isAllDay()
    {
        return allDay;
    }

    public final String toString()
    {
        long l = startMs;
        long l1 = endMs;
        boolean flag = allDay;
        return (new StringBuilder(80)).append("Timespan{startMs=").append(l).append(", endMs=").append(l1).append(", allDay=").append(flag).append("}").toString();
    }
}
