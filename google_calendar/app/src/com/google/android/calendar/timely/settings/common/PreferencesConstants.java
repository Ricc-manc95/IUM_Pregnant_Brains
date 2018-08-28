// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.settings.common;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.calendarcommon2.LogUtils;
import java.util.Calendar;

public class PreferencesConstants
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/settings/common/PreferencesConstants);
    public static final String WEEK_START_DEFAULT = String.valueOf(Calendar.getInstance().getFirstDayOfWeek());

    public PreferencesConstants()
    {
    }

    public static int getAlternateCalendarPref(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT < 24)
        {
            return 0;
        }
        int i = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("preferences_alternate_calendar", 0);
        switch (i)
        {
        default:
            LogUtils.w(TAG, "Unknown alternate calendar pref: %d. Returning ALTERNATE_CALENDAR_DEFAULT", new Object[] {
                Integer.valueOf(i)
            });
            return 0;

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
            return i;
        }
    }

}
