// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.birthdays;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BirthdayViewModel
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/settings/birthdays/BirthdayViewModel);
    public Map birthdayModes;
    public CalendarColor color;
    public final Context context;
    public Map settingsMap;

    public BirthdayViewModel(Context context1, ImmutableMap immutablemap)
    {
        birthdayModes = new HashMap();
        settingsMap = new HashMap();
        context = context1;
        immutablemap = (UnmodifiableIterator)((ImmutableCollection)immutablemap.values()).iterator();
        do
        {
            if (!immutablemap.hasNext())
            {
                break;
            }
            Settings settings = (Settings)immutablemap.next();
            if (settings instanceof GoogleSettings)
            {
                GoogleSettings googlesettings = (GoogleSettings)settings;
                com.google.android.calendar.api.settings.GoogleSettings.BirthdayMode birthdaymode = googlesettings.getBirthdayMode();
                if (birthdaymode != null)
                {
                    birthdayModes.put(settings.getDescriptor(), birthdaymode);
                    settingsMap.put(settings.getDescriptor(), googlesettings);
                }
            }
        } while (true);
        int i = context1.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("preferences_birthdays_color", 0xff92e1c0);
        color = CalendarApi.getColorFactory().createBirthdayColor(i);
    }

}
