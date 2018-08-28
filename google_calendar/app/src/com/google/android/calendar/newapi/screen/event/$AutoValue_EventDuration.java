// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;


// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            EventDuration

abstract class $AutoValue_EventDuration extends EventDuration
{

    private final boolean endTimeUnspecified;
    private final long millis;

    $AutoValue_EventDuration(long l, boolean flag)
    {
        millis = l;
        endTimeUnspecified = flag;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof EventDuration)
            {
                if (millis != ((EventDuration) (obj = (EventDuration)obj)).getMillis() || endTimeUnspecified != ((EventDuration) (obj)).isEndTimeUnspecified())
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

    public final long getMillis()
    {
        return millis;
    }

    public int hashCode()
    {
        int i = (int)(millis >>> 32 ^ millis);
        char c;
        if (endTimeUnspecified)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return c ^ (i ^ 0xf4243) * 0xf4243;
    }

    public final boolean isEndTimeUnspecified()
    {
        return endTimeUnspecified;
    }

    public String toString()
    {
        long l = millis;
        boolean flag = endTimeUnspecified;
        return (new StringBuilder(68)).append("EventDuration{millis=").append(l).append(", endTimeUnspecified=").append(flag).append("}").toString();
    }
}
