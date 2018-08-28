// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.util;

import com.google.android.apps.calendar.timeline.alternate.util.AutoValue_YearMonth;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.collect.Range;
import java.util.Calendar;

public final class YearMonthHelper
{

    private final Calendar calendar = Calendar.getInstance();
    public final TimeUtils timeUtils;
    public final int weeksInMonth;

    YearMonthHelper(TimeUtils timeutils, int i)
    {
        timeUtils = timeutils;
        calendar.setFirstDayOfWeek(((Integer)timeutils.firstDayOfWeek.get()).intValue());
        weeksInMonth = i;
    }

    public final YearMonth createForMs(long l)
    {
        this;
        JVM INSTR monitorenter ;
        AutoValue_YearMonth autovalue_yearmonth;
        timeUtils.initCalendar(calendar);
        calendar.setTimeInMillis(l);
        autovalue_yearmonth = new AutoValue_YearMonth(calendar.get(1), calendar.get(2));
        this;
        JVM INSTR monitorexit ;
        return autovalue_yearmonth;
        Exception exception;
        exception;
        throw exception;
    }

    public final int getFirstVisibleJulianDay(YearMonth yearmonth)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        timeUtils.initCalendar(calendar);
        calendar.set(yearmonth.getYear(), yearmonth.getMonth(), 1);
        calendar.getTimeInMillis();
        calendar.set(7, ((Integer)timeUtils.firstDayOfWeek.get()).intValue());
        i = timeUtils.msToJulianDate(calendar.getTimeInMillis());
        this;
        JVM INSTR monitorexit ;
        return i;
        yearmonth;
        throw yearmonth;
    }

    public final Range getMonthRange(YearMonth yearmonth)
    {
        this;
        JVM INSTR monitorenter ;
        timeUtils.initCalendar(calendar);
        calendar.set(yearmonth.getYear(), yearmonth.getMonth(), 1);
        int i = timeUtils.msToJulianDate(calendar.getTimeInMillis());
        calendar.add(2, 1);
        yearmonth = Range.closed(Integer.valueOf(i), Integer.valueOf(timeUtils.msToJulianDate(calendar.getTimeInMillis()) - 1));
        this;
        JVM INSTR monitorexit ;
        return yearmonth;
        yearmonth;
        throw yearmonth;
    }

    public final long getStartMonthMs(YearMonth yearmonth)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        timeUtils.initCalendar(calendar);
        calendar.set(yearmonth.getYear(), yearmonth.getMonth(), 1);
        l = calendar.getTimeInMillis();
        this;
        JVM INSTR monitorexit ;
        return l;
        yearmonth;
        throw yearmonth;
    }
}
