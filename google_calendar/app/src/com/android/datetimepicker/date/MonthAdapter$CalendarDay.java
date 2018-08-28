// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.text.format.Time;
import java.util.Calendar;

public final class day
    implements Comparable
{

    private Calendar calendar;
    public int day;
    public int month;
    private Time time;
    public int year;

    private final void setTime(long l)
    {
        if (calendar == null)
        {
            calendar = Calendar.getInstance();
        }
        calendar.setTimeInMillis(l);
        month = calendar.get(2);
        year = calendar.get(1);
        day = calendar.get(5);
    }

    public final int compareTo(calendar calendar1)
    {
        int j = Integer.compare(year, calendar1.year);
        int i = j;
        if (j == 0)
        {
            int k = Integer.compare(month, calendar1.month);
            i = k;
            if (k == 0)
            {
                i = Integer.compare(day, calendar1.day);
            }
        }
        return i;
    }

    public final volatile int compareTo(Object obj)
    {
        return compareTo((compareTo)obj);
    }

    public final void setJulianDay(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if (time == null)
        {
            time = new Time();
        }
        time.setJulianDay(i);
        setTime(time.toMillis(false));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public ()
    {
        setTime(System.currentTimeMillis());
    }

    public setTime(int i, int j, int k)
    {
        year = i;
        month = j;
        day = k;
    }

    public day(long l)
    {
        setTime(l);
    }

    public setTime(Calendar calendar1)
    {
        year = calendar1.get(1);
        month = calendar1.get(2);
        day = calendar1.get(5);
    }
}
