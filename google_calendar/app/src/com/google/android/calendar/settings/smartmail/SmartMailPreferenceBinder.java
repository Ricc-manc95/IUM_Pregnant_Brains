// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.smartmail;

import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.settings.smartmail:
//            SmartMailViewModel

class SmartMailPreferenceBinder
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/settings/smartmail/SmartMailPreferenceBinder);
    public final PreferenceScreen preferenceScreen;
    public SmartMailViewModel viewModel;

    SmartMailPreferenceBinder(PreferenceScreen preferencescreen)
    {
        preferenceScreen = preferencescreen;
    }

    final void updateVisibilityMode(ListPreference listpreference, String as[], com.google.android.calendar.api.settings.GoogleSettings.SmartMailMode smartmailmode)
    {
        byte byte0;
        boolean flag;
        byte0 = -1;
        flag = false;
        if (smartmailmode != com.google.android.calendar.api.settings.GoogleSettings.SmartMailMode.IGNORE) goto _L2; else goto _L1
_L1:
        if (byte0 >= 0)
        {
            flag = true;
        }
        if (flag)
        {
            as = as[byte0];
        } else
        {
            as = "";
        }
        if (((Preference) (listpreference)).mVisible != flag)
        {
            listpreference.mVisible = flag;
            if (((Preference) (listpreference)).mListener != null)
            {
                ((Preference) (listpreference)).mListener.onPreferenceVisibilityChange(listpreference);
            }
        }
        listpreference.setValue(smartmailmode.name());
        listpreference.setSummary(as);
        return;
_L2:
        switch (smartmailmode.ordinal())
        {
        default:
            LogUtils.wtf(TAG, "Unhandled mode type", new Object[0]);
            break;

        case 0: // '\0'
            byte0 = 0;
            break;

        case 1: // '\001'
            byte0 = 1;
            break;

        case 2: // '\002'
            byte0 = 2;
            break;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

}
