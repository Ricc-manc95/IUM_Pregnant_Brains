// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import android.text.format.Time;
import java.util.Calendar;

public final class RecurrenceStartShifter
{

    public static int getJulianDay(Calendar calendar)
    {
        Time time = new Time("UTC");
        time.set(calendar.get(5), calendar.get(2), calendar.get(1));
        return Time.getJulianDay(time.toMillis(false), time.gmtoff);
    }
}
