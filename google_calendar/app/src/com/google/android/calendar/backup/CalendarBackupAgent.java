// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.backup;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.libraries.backup.PersistentBackupAgentHelper;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableMap;
import java.util.Map;

public class CalendarBackupAgent extends PersistentBackupAgentHelper
{

    private static final ImmutableSet BACKED_UP_KEYS = ImmutableSet.of("preference_key_grid_mode", "preference_key_last_view", "preference_showMoreEvents", "preferences_alerts", "preferences_alerts_vibrate", "preferences_birthdays_color", new String[] {
        "preferences_hide_declined", "preferences_last_display_tz", "preferences_quick_responses", "preferences_show_week_num", "preferences_week_start_day", "seenOOBE", "preferences_alternate_calendar", "com.google.android.calendar.family.state"
    });

    public CalendarBackupAgent()
    {
    }

    protected final Map getBackupSpecification()
    {
        Object obj = BACKED_UP_KEYS;
        if (obj == null)
        {
            throw new NullPointerException("Null collection given.");
        } else
        {
            obj = new com.google.android.libraries.backup.BackupKeyPredicates._cls1(((java.util.Collection) (obj)));
            CollectPreconditions.checkEntryNotNull("com.google.android.calendar_preferences", obj);
            return RegularImmutableMap.create(1, new Object[] {
                "com.google.android.calendar_preferences", obj
            });
        }
    }

    public final void onPreferencesRestored$5166KOBMC4NNAT39DGNL6PBK7D4IILG_0()
    {
        String s = null;
        SharedPreferences sharedpreferences = getSharedPreferences("com.google.android.calendar_preferences", 0);
        if (!sharedpreferences.contains("preferences_home_tz") || !sharedpreferences.contains("preferences_home_tz_enabled"))
        {
            return;
        }
        boolean flag = sharedpreferences.getBoolean("preferences_home_tz_enabled", false);
        if (flag)
        {
            s = sharedpreferences.getString("preferences_home_tz", null);
        }
        new com.google.android.calendar.time.TimeUtils.TimeZoneUtils();
        if (flag)
        {
            s = "auto";
        }
        com.google.android.calendar.time.TimeUtils.TimeZoneUtils.setTimeZone(this, s);
    }

}
