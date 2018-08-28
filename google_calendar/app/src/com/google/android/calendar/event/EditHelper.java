// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.Context;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;

public final class EditHelper
{

    public static long constructDefaultStartTime(long l)
    {
        Time time = new Time();
        time.impl.timezone = time.timezone;
        time.impl.set(l);
        time.impl.toMillis(true);
        time.copyFieldsFromImpl();
        time.second = 0;
        time.minute = 30;
        time.writeFieldsToImpl();
        long l1 = time.impl.toMillis(false);
        if (l < l1)
        {
            return l1;
        } else
        {
            return l1 + 0x1b7740L;
        }
    }

    public static long constructDefaultStartTimeWithoutCorrection(Time time, Context context)
    {
        context = new Time(Utils.getTimeZoneId(context));
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        ((Time) (context)).impl.timezone = ((Time) (context)).timezone;
        ((Time) (context)).impl.set(l);
        ((Time) (context)).impl.toMillis(true);
        context.copyFieldsFromImpl();
        time.hour = ((Time) (context)).hour;
        time.minute = ((Time) (context)).minute;
        time.second = ((Time) (context)).second;
        time.writeFieldsToImpl();
        return time.impl.toMillis(false);
    }
}
