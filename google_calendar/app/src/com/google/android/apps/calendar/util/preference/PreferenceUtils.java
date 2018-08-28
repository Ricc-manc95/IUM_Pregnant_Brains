// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.preference;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.android.calendar.utils.ColorUtils;
import java.util.Calendar;

public final class PreferenceUtils
{

    public static int getBirthdayColor(Context context)
    {
        return ColorUtils.getDisplayColorFromColor(context.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("preferences_birthdays_color", 0xff92e1c0));
    }

    public static int getFirstDayOfWeekAsCalendar(Context context)
    {
        context = context.getSharedPreferences("com.google.android.calendar_preferences", 0);
        String s = context.getString("preferences_week_start_day", PreferencesConstants.WEEK_START_DEFAULT);
        int i;
        try
        {
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            int j = Calendar.getInstance().getFirstDayOfWeek();
            context.edit().putString("preferences_week_start_day", String.valueOf(j)).apply();
            return j;
        }
label0:
        {
            if (i > 0 && i <= 7)
            {
                return i;
            }
            break label0;
        }
    }
}
