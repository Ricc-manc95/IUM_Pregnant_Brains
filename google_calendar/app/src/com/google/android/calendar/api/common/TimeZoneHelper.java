// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

public final class TimeZoneHelper
{

    private static Set timeZoneIds;

    private static Set getTimeZoneIds()
    {
        com/google/android/calendar/api/common/TimeZoneHelper;
        JVM INSTR monitorenter ;
        Set set;
        if (timeZoneIds == null)
        {
            String as[] = TimeZone.getAvailableIDs();
            HashSet hashset = new HashSet(as.length);
            timeZoneIds = hashset;
            Collections.addAll(hashset, as);
        }
        set = timeZoneIds;
        com/google/android/calendar/api/common/TimeZoneHelper;
        JVM INSTR monitorexit ;
        return set;
        Exception exception;
        exception;
        throw exception;
    }

    public static boolean isValidTimeZoneId(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        while (getTimeZoneIds().contains(s) || !"GMT".equalsIgnoreCase(TimeZone.getTimeZone(s).getID())) 
        {
            return true;
        }
        return false;
    }
}
