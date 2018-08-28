// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.time;

import java.util.Calendar;
import java.util.TimeZone;

public final class CalendarUtils
{

    public static Calendar createTimeInNewTimeZoneRetainingFields(long l, TimeZone timezone, TimeZone timezone1)
    {
        timezone = Calendar.getInstance(timezone);
        timezone.setTimeInMillis(l);
        Calendar calendar = (Calendar)timezone.clone();
        calendar.setTimeZone(timezone1);
        calendar.set(timezone.get(1), timezone.get(2), timezone.get(5), timezone.get(11), timezone.get(12), timezone.get(13));
        calendar.set(14, timezone.get(14));
        return calendar;
    }
}
