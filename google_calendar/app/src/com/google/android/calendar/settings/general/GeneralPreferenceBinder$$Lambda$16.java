// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.support.v7.preference.Preference;
import android.support.v7.preference.TwoStatePreference;
import com.google.android.calendar.settings.SettingsShims;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceBinder, GeneralPreferenceViewModel

final class arg._cls1
    implements android.support.v7.preference.er
{

    private final GeneralPreferenceBinder arg$1;

    public final boolean onPreferenceChange(Preference preference, Object obj)
    {
        GeneralPreferenceBinder generalpreferencebinder = arg$1;
        preference = generalpreferencebinder.viewModel;
        boolean flag = ((Boolean)obj).booleanValue();
        if (flag != ((GeneralPreferenceViewModel) (preference)).useDeviceTimezone)
        {
            preference.useDeviceTimezone = flag;
            preference.updateTimezone();
        }
        generalpreferencebinder.useDeviceTimezonePreference.setChecked(((Boolean)obj).booleanValue());
        obj = generalpreferencebinder.timezonePreference;
        preference = generalpreferencebinder.viewModel;
        SettingsShims settingsshims = SettingsShims.instance;
        android.content.Context context = ((GeneralPreferenceViewModel) (preference)).context;
        if (((GeneralPreferenceViewModel) (preference)).useDeviceTimezone)
        {
            preference = ((GeneralPreferenceViewModel) (preference)).deviceTimezone;
        } else
        {
            preference = ((GeneralPreferenceViewModel) (preference)).timezone;
        }
        ((Preference) (obj)).setSummary(settingsshims.getTimezoneName(context, preference));
        preference = generalpreferencebinder.timezonePreference;
        if (!generalpreferencebinder.viewModel.useDeviceTimezone)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (preference.mSelectable != flag)
        {
            preference.mSelectable = flag;
            preference.notifyChanged();
        }
        return true;
    }

    (GeneralPreferenceBinder generalpreferencebinder)
    {
        arg$1 = generalpreferencebinder;
    }
}
