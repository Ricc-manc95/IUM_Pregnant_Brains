// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.accounts.Account;
import android.content.res.Resources;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceManager;
import android.util.Pair;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.settings.InMemoryPreferenceDataStore;
import com.google.android.calendar.settings.home.HomeViewModel;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.settings.general:
//            EventDurationBinder, GeneralPreferenceViewModel, EventDurationFragment

final class arg._cls1
    implements Consumer
{

    private final EventDurationFragment arg$1;

    public final void accept(Object obj)
    {
        Object obj1 = arg$1;
        obj = (HomeViewModel)obj;
        ((PreferenceFragmentCompat) (obj1)).addPreferencesFromResource(0x7f090004);
        obj1 = new EventDurationBinder(((PreferenceFragmentCompat) (obj1)).mPreferenceManager.mPreferenceScreen);
        obj1.viewModel = ((HomeViewModel) (obj)).generalPreferenceViewModel;
        ((Preference) (((EventDurationBinder) (obj1)).preferenceScreen)).mPreferenceManager.mPreferenceDataStore = new InMemoryPreferenceDataStore();
        for (int i = ((PreferenceGroup) (((EventDurationBinder) (obj1)).preferenceScreen)).mPreferenceList.size() - 1; i >= 0; i--)
        {
            obj = (Preference)((PreferenceGroup) (((EventDurationBinder) (obj1)).preferenceScreen)).mPreferenceList.get(i);
            if (((Preference) (obj)).mOrder != 1)
            {
                continue;
            }
            android.support.v7.preference.PreferenceScreen preferencescreen = ((EventDurationBinder) (obj1)).preferenceScreen;
            preferencescreen.removePreferenceInt(((Preference) (obj)));
            if (((Preference) (preferencescreen)).mListener != null)
            {
                ((Preference) (preferencescreen)).mListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
            }
        }

        int ai[] = ((EventDurationBinder) (obj1)).resources.getIntArray(0x7f0b001d);
        String as[] = new String[ai.length];
        String as1[] = new String[ai.length];
        int j = 0;
        while (j < as1.length) 
        {
            int k = ai[j];
            if (k <= 0)
            {
                obj = ((EventDurationBinder) (obj1)).noDurationString;
            } else
            {
                obj = ((EventDurationBinder) (obj1)).resources.getString(0x7f1301cc, new Object[] {
                    Integer.valueOf(k)
                });
            }
            as1[j] = ((String) (obj));
            as[j] = Integer.toString(k);
            j++;
        }
        Iterator iterator = ((EventDurationBinder) (obj1)).viewModel.eventDurations.keySet().iterator();
        j = 1;
        while (iterator.hasNext()) 
        {
            Account account = (Account)iterator.next();
            long l1 = ((Long)((Pair)((EventDurationBinder) (obj1)).viewModel.eventDurations.get(account)).second).longValue();
            ListPreference listpreference;
            int l;
            if (l1 <= 0L)
            {
                l = -1;
            } else
            {
                l = (int)TimeUnit.MILLISECONDS.toMinutes(l1);
            }
            listpreference = new ListPreference(((Preference) (((EventDurationBinder) (obj1)).preferenceScreen)).mContext);
            listpreference.setTitle(account.name);
            if (1 != ((Preference) (listpreference)).mOrder)
            {
                listpreference.mOrder = 1;
                if (((Preference) (listpreference)).mListener != null)
                {
                    ((Preference) (listpreference)).mListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
                }
            }
            listpreference.setKey((new StringBuilder(19)).append("account_").append(j).toString());
            listpreference.mEntryValues = as;
            listpreference.setEntries(as1);
            listpreference.setValue(Integer.toString(l));
            if (l <= 0)
            {
                obj = ((EventDurationBinder) (obj1)).noDurationString;
            } else
            {
                obj = ((EventDurationBinder) (obj1)).resources.getString(0x7f1301cc, new Object[] {
                    Integer.valueOf(l)
                });
            }
            listpreference.setSummary(((CharSequence) (obj)));
            listpreference.mOnChangeListener = new nit>(((EventDurationBinder) (obj1)), account, listpreference);
            ((EventDurationBinder) (obj1)).preferenceScreen.addPreference(listpreference);
            j++;
        }
    }

    (EventDurationFragment eventdurationfragment)
    {
        arg$1 = eventdurationfragment;
    }
}
