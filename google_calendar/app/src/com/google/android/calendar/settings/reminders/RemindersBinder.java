// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.reminders;

import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;
import com.google.android.calendar.settings.home.ReminderViewModel;

// Referenced classes of package com.google.android.calendar.settings.reminders:
//            RemindersFragment

public final class RemindersBinder
{

    public final Preference color;
    public final RemindersFragment fragment;
    private final PreferenceScreen preferenceScreen;
    public ReminderViewModel viewModel;

    public RemindersBinder(RemindersFragment remindersfragment, PreferenceScreen preferencescreen)
    {
        fragment = remindersfragment;
        preferenceScreen = preferencescreen;
        remindersfragment = preferenceScreen.findPreference("color");
        if (remindersfragment == null)
        {
            throw new NullPointerException();
        } else
        {
            color = (Preference)remindersfragment;
            return;
        }
    }
}
