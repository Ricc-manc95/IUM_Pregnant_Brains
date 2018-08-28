// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import com.google.android.calendar.time.Time;
import java.util.Calendar;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskDataManager

public static final class numDays
{

    public final int numDays;
    public final int startJulianDay;

    public (int i)
    {
        Object obj = new Time();
        ((Time) (obj)).writeFieldsToImpl();
        ((Time) (obj)).impl.setJulianDay(i);
        ((Time) (obj)).copyFieldsFromImpl();
        ((Time) (obj)).writeFieldsToImpl();
        long l = ((Time) (obj)).impl.toMillis(false);
        obj = Calendar.getInstance();
        ((Calendar) (obj)).setTimeInMillis(l);
        startJulianDay = i - (((Calendar) (obj)).get(5) - 1);
        numDays = ((Calendar) (obj)).getActualMaximum(5);
    }

    public numDays(int i, int j)
    {
        startJulianDay = i;
        numDays = (j - i) + 1;
    }
}
