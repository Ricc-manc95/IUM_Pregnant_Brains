// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.birthdays;

import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;

// Referenced classes of package com.google.android.calendar.settings.birthdays:
//            BirthdayViewModel

public final class BirthdayPreferenceBinder
{

    public final Preference color;
    public final Fragment fragment;
    public final PreferenceScreen preferenceScreen;
    public BirthdayViewModel viewModel;

    BirthdayPreferenceBinder(Fragment fragment1, PreferenceScreen preferencescreen)
    {
        fragment = fragment1;
        preferenceScreen = preferencescreen;
        fragment1 = preferencescreen.findPreference("color");
        if (fragment1 == null)
        {
            throw new NullPointerException();
        } else
        {
            color = (Preference)fragment1;
            return;
        }
    }
}
