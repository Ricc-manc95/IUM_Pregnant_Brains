// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Pair;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class GeneralPreferenceViewModel
{

    public int alternateCalendar;
    public final Context context;
    public String deviceTimezone;
    public boolean enableFlingingBluetooth;
    public final Map eventDurations = new HashMap();
    public int firstDayOfWeek;
    public final boolean hasHabits;
    public boolean notifyOnThisDevice;
    public final Set quickResponses = new HashSet();
    public String ringtoneUri;
    private final ImmutableMap settings;
    public boolean showDeclinedEvents;
    public final boolean showFlinging;
    public boolean showWeekNumber;
    public String timezone;
    public boolean useDeviceTimezone;
    public boolean useStandardTone;
    public boolean vibrate;

    public GeneralPreferenceViewModel(Context context1, ImmutableMap immutablemap, boolean flag, boolean flag1)
    {
        int i = 0;
        super();
        context = context1;
        firstDayOfWeek = PreferenceUtils.getFirstDayOfWeekAsCalendar(context1);
        settings = immutablemap;
        hasHabits = flag;
        showFlinging = flag1;
        immutablemap = context.getSharedPreferences("com.google.android.calendar_preferences", 0);
        if (!immutablemap.getBoolean("preferences_home_tz_enabled", false))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        useDeviceTimezone = flag;
        deviceTimezone = SettingsShims.instance.getTimezone(context1, true);
        timezone = SettingsShims.instance.getTimezone(context1, false);
        alternateCalendar = PreferencesConstants.getAlternateCalendarPref(context1);
        showWeekNumber = immutablemap.getBoolean("preferences_show_week_num", false);
        if (!immutablemap.getBoolean("preferences_hide_declined", false))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        showDeclinedEvents = flag;
        notifyOnThisDevice = immutablemap.getBoolean("preferences_alerts", true);
        useStandardTone = immutablemap.getBoolean("preferences_use_standard_tone", true);
        ringtoneUri = PreferencesUtils.getRingtonePreference(context);
        vibrate = immutablemap.getBoolean("preferences_alerts_vibrate", false);
        enableFlingingBluetooth = immutablemap.getBoolean("preferences_flinging_bluetooth", false);
        immutablemap = (UnmodifiableIterator)((ImmutableCollection)settings.values()).iterator();
        do
        {
            if (!immutablemap.hasNext())
            {
                break;
            }
            Object obj = (Settings)immutablemap.next();
            if ((obj instanceof GoogleSettings) && ContentResolver.getIsSyncable(((Settings) (obj)).getDescriptor(), "com.android.calendar") > 0)
            {
                obj = (GoogleSettings)obj;
                long l;
                if (((GoogleSettings) (obj)).isEndTimeUnspecifiedByDefault())
                {
                    l = -1L;
                } else
                {
                    l = ((GoogleSettings) (obj)).getDefaultEventDurationMillis();
                }
                eventDurations.put(((GoogleSettings) (obj)).getDescriptor(), Pair.create(obj, Long.valueOf(l)));
            }
        } while (true);
        immutablemap = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getStringSet("preferences_quick_responses", Collections.emptySet());
        if (immutablemap.isEmpty())
        {
            context1 = context1.getResources().getStringArray(0x7f0b0049);
            for (int j = context1.length; i < j; i++)
            {
                immutablemap = context1[i];
                quickResponses.add(immutablemap);
            }

        } else
        {
            quickResponses.addAll(immutablemap);
        }
    }

    final void updateTimezone()
    {
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putBoolean("preferences_home_tz_enabled", useDeviceTimezone).apply();
        SettingsShims settingsshims = SettingsShims.instance;
        Context context1 = context;
        String s;
        if (useDeviceTimezone)
        {
            s = "auto";
        } else
        {
            s = timezone;
        }
        settingsshims.setTimezone(context1, s);
    }
}
