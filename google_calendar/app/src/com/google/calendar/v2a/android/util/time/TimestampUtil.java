// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.time;

import java.util.Calendar;
import java.util.TimeZone;

public final class TimestampUtil
{

    public static boolean isTimestampUtcMidnight(long l)
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(l);
        return calendar.get(11) == 0 && calendar.get(12) == 0 && calendar.get(13) == 0 && calendar.get(14) == 0;
    }

    public static long roundToMidnight(long l, String s, boolean flag, String s1)
    {
        if (isTimestampUtcMidnight(l))
        {
            return l;
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(s1));
        calendar.setTimeInMillis(l);
        int i;
        if (!s1.equals(s))
        {
            int j;
            int k;
            if (s != null)
            {
                s = TimeZone.getTimeZone(s);
            } else
            {
                s = TimeZone.getDefault();
            }
            calendar.setTimeZone(s);
        }
        j = calendar.get(1);
        k = calendar.get(2);
        i = calendar.get(5);
        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone(s1));
        calendar.set(1, j);
        calendar.set(2, k);
        if (!flag)
        {
            i++;
        }
        calendar.set(5, i);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }
}
