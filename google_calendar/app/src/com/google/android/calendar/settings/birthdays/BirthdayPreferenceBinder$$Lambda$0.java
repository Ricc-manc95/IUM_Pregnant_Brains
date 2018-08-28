// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.birthdays;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.GoogleSettingsModifications;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsFactory;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.settings.birthdays:
//            BirthdayPreferenceBinder, BirthdayViewModel

final class arg._cls3
    implements android.support.v7.preference.er
{

    private final BirthdayPreferenceBinder arg$1;
    private final Account arg$2;
    private final ListPreference arg$3;

    public final boolean onPreferenceChange(Preference preference, Object obj)
    {
        Account account;
        ListPreference listpreference;
        BirthdayViewModel birthdayviewmodel;
        preference = arg$1;
        account = arg$2;
        listpreference = arg$3;
        obj = (String)obj;
        birthdayviewmodel = ((BirthdayPreferenceBinder) (preference)).viewModel;
        preference = ((Preference) (((BirthdayPreferenceBinder) (preference)).preferenceScreen)).mContext.getResources();
        if (!((String) (obj)).equals(preference.getString(0x7f13043b))) goto _L2; else goto _L1
_L1:
        preference = com.google.android.calendar.api.settings.S;
_L4:
        com.google.android.calendar.api.settings.bda._cls0 _lcls0 = (com.google.android.calendar.api.settings.S)birthdayviewmodel.birthdayModes.put(account, preference);
        if (_lcls0 == null)
        {
            LogUtils.wtf(BirthdayViewModel.TAG, "No such account", new Object[0]);
        }
        if (!preference.es(_lcls0))
        {
            CalendarApi.Settings.update(CalendarApi.SettingsFactory.modifyGoogleSettings((GoogleSettings)birthdayviewmodel.settingsMap.get(account)).setBirthdayMode(preference));
        }
        listpreference.setSummary(((CharSequence) (obj)));
        return true;
_L2:
        if (((String) (obj)).equals(preference.getString(0x7f13043d)))
        {
            preference = com.google.android.calendar.api.settings.ND_CONTACTS;
            continue; /* Loop/switch isn't completed */
        }
        if (!((String) (obj)).equals(preference.getString(0x7f13043e)))
        {
            break; /* Loop/switch isn't completed */
        }
        preference = com.google.android.calendar.api.settings.ND_CONTACTS;
        if (true) goto _L4; else goto _L3
_L3:
        preference = String.valueOf(obj);
        if (preference.length() != 0)
        {
            preference = "Unknown mode: ".concat(preference);
        } else
        {
            preference = new String("Unknown mode: ");
        }
        throw new IllegalArgumentException(preference);
    }

    (BirthdayPreferenceBinder birthdaypreferencebinder, Account account, ListPreference listpreference)
    {
        arg$1 = birthdaypreferencebinder;
        arg$2 = account;
        arg$3 = listpreference;
    }
}
