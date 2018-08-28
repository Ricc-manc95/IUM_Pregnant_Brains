// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;


// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarKey

abstract class $AutoValue_CalendarKey extends CalendarKey
{

    private final long localId;

    $AutoValue_CalendarKey(long l)
    {
        localId = l;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof CalendarKey)
            {
                if (localId != ((CalendarKey) (obj = (CalendarKey)obj)).getLocalId())
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

    public final long getLocalId()
    {
        return localId;
    }

    public int hashCode()
    {
        return 0xf4243 ^ (int)(localId >>> 32 ^ localId);
    }

    public String toString()
    {
        long l = localId;
        return (new StringBuilder(41)).append("CalendarKey{localId=").append(l).append("}").toString();
    }
}
