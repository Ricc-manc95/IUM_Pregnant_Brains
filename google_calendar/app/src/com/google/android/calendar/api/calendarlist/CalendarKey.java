// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            AutoValue_CalendarKey

public abstract class CalendarKey
    implements Parcelable, Comparable
{

    public static final CalendarKey BIRTHDAY = new AutoValue_CalendarKey(0L);
    public static final CalendarKey NONE = new AutoValue_CalendarKey(-1L);

    public CalendarKey()
    {
    }

    public static CalendarKey newInstance(long l)
    {
        boolean flag;
        if (l > 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("A valid local id should be passed to this factory method. "));
        } else
        {
            return new AutoValue_CalendarKey(l);
        }
    }

    public int compareTo(Object obj)
    {
        obj = (CalendarKey)obj;
        long l = getLocalId();
        long l1 = ((CalendarKey) (obj)).getLocalId();
        if (l < l1)
        {
            return -1;
        }
        return l != l1 ? 1 : 0;
    }

    public abstract long getLocalId();

}
