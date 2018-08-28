// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import com.android.timezonepicker.fullscreen.TimeZonePickerHelper;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceBinder, GeneralPreferenceViewModel

final class arg._cls1
    implements android.support.v7.preference.r
{

    private final GeneralPreferenceBinder arg$1;

    public final boolean onPreferenceClick(Preference preference)
    {
        GeneralPreferenceBinder generalpreferencebinder = arg$1;
        if (!generalpreferencebinder.viewModel.useDeviceTimezone)
        {
            android.content.Context context = generalpreferencebinder.fragment.getContext();
            preference = generalpreferencebinder.viewModel;
            if (((GeneralPreferenceViewModel) (preference)).useDeviceTimezone)
            {
                preference = ((GeneralPreferenceViewModel) (preference)).deviceTimezone;
            } else
            {
                preference = ((GeneralPreferenceViewModel) (preference)).timezone;
            }
            preference = TimeZonePickerHelper.createIntent(context, null, null, preference);
            generalpreferencebinder.fragment.startActivityForResult(preference, 2);
        }
        return true;
    }

    (GeneralPreferenceBinder generalpreferencebinder)
    {
        arg$1 = generalpreferencebinder;
    }
}
