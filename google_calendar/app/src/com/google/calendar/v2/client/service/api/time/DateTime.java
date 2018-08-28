// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2.client.service.api.time;


// Referenced classes of package com.google.calendar.v2.client.service.api.time:
//            Instant, Duration, Period

public interface DateTime
    extends Instant
{

    public abstract int getDayOfMonth();

    public abstract int getHourOfDay();

    public abstract int getMinuteOfHour();

    public abstract int getMonthOfYear();

    public abstract int getYear();

    public abstract DateTime minusDuration(Duration duration);

    public abstract DateTime minusPeriod(Period period);

    public abstract DateTime plusPeriod(Period period);

    public abstract DateTime withDate(int i, int j, int k);

    public abstract DateTime withMillisOfDay(int i);

    public abstract DateTime withTime(int i, int j, int k);
}
