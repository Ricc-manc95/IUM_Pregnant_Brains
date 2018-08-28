// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import com.android.calendarcommon2.LogUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class TimeZoneUtils
{

    private static final String TAG = com/google/android/syncadapters/calendar/TimeZoneUtils.getSimpleName();
    private static final Map displayNameToId = new HashMap();

    public TimeZoneUtils()
    {
    }

    public static String checkForValidTimezoneId(String s)
    {
        if (s == null)
        {
            return null;
        } else
        {
            return (String)displayNameToId.get(s.toLowerCase());
        }
    }

    private static void put(String s, String s1)
    {
        LogUtils.v(TAG, "%s: %s", new Object[] {
            s, s1
        });
        displayNameToId.put(s, s1);
    }

    static 
    {
        Locale locale = Locale.getDefault();
        String as[] = TimeZone.getAvailableIDs();
        int j = as.length;
        for (int i = 0; i < j; i++)
        {
            String s = as[i];
            TimeZone timezone = TimeZone.getTimeZone(s);
            put(timezone.getDisplayName(true, 1, locale).toLowerCase(locale), s);
            put(timezone.getDisplayName(false, 1, locale).toLowerCase(locale), s);
            put(timezone.getDisplayName(true, 0, locale).toLowerCase(locale), s);
            put(timezone.getDisplayName(false, 0, locale).toLowerCase(locale), s);
        }

    }
}
