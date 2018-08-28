// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.accounts.Account;
import android.content.res.Resources;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.util.Pair;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.GoogleSettingsModifications;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsFactory;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.settings.general:
//            EventDurationBinder, GeneralPreferenceViewModel

final class arg._cls3
    implements android.support.v7.preference.istener
{

    private final EventDurationBinder arg$1;
    private final Account arg$2;
    private final ListPreference arg$3;

    public final boolean onPreferenceChange(Preference preference, Object obj)
    {
        preference = arg$1;
        Object obj1 = arg$2;
        ListPreference listpreference = arg$3;
        int i = Integer.parseInt((String)obj);
        Pair pair;
        GeneralPreferenceViewModel generalpreferenceviewmodel;
        boolean flag;
        long l;
        if (i == -1)
        {
            l = -1L;
        } else
        {
            l = TimeUnit.MINUTES.toMillis(i);
        }
        generalpreferenceviewmodel = ((EventDurationBinder) (preference)).viewModel;
        obj = (GoogleSettings)((Pair)generalpreferenceviewmodel.eventDurations.get(obj1)).first;
        pair = Pair.create(obj, Long.valueOf(l));
        obj1 = (Pair)generalpreferenceviewmodel.eventDurations.put(obj1, pair);
        if (pair == obj1 || pair != null && pair.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            if (l <= 0L)
            {
                CalendarApi.Settings.update(CalendarApi.SettingsFactory.modifyGoogleSettings(((GoogleSettings) (obj))).setEndTimeUnspecifiedByDefault(true).setDefaultEventDurationMillis(0L));
            } else
            {
                CalendarApi.Settings.update(CalendarApi.SettingsFactory.modifyGoogleSettings(((GoogleSettings) (obj))).setEndTimeUnspecifiedByDefault(false).setDefaultEventDurationMillis(l));
            }
        }
        if (i <= 0)
        {
            preference = ((EventDurationBinder) (preference)).noDurationString;
        } else
        {
            preference = ((EventDurationBinder) (preference)).resources.getString(0x7f1301cc, new Object[] {
                Integer.valueOf(i)
            });
        }
        listpreference.setSummary(preference);
        return true;
    }

    (EventDurationBinder eventdurationbinder, Account account, ListPreference listpreference)
    {
        arg$1 = eventdurationbinder;
        arg$2 = account;
        arg$3 = listpreference;
    }
}
