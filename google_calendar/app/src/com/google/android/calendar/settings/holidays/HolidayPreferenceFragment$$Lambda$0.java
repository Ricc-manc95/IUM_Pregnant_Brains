// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.holidays;

import android.accounts.Account;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.view.ContextThemeWrapper;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.settings.home.HomeViewModel;
import com.google.android.calendar.settings.view.ViewUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.settings.holidays:
//            HolidayPreferenceBinder, HolidayPreferenceFragment, HolidayViewModel

final class arg._cls1
    implements Consumer
{

    private final HolidayPreferenceFragment arg$1;

    public final void accept(Object obj)
    {
        boolean flag = false;
        Object obj1 = arg$1;
        Object obj2 = (HomeViewModel)obj;
        ((PreferenceFragmentCompat) (obj1)).addPreferencesFromResource(0x7f090007);
        obj1.binder = new HolidayPreferenceBinder(((android.support.v4.app.Fragment) (obj1)), ((PreferenceFragmentCompat) (obj1)).mPreferenceManager.mPreferenceScreen);
        obj = ((HolidayPreferenceFragment) (obj1)).binder;
        obj.viewModel = ((HomeViewModel) (obj2)).holidayViewModel;
        obj1 = ((Preference) (((HolidayPreferenceBinder) (obj)).preferenceScreen)).mContext;
        ((Preference) (((HolidayPreferenceBinder) (obj)).preferenceScreen)).mPreferenceManager.mPreferenceDataStore = ((HolidayPreferenceBinder) (obj)).dataStore;
        obj2 = new ContextThemeWrapper(((android.content.Context) (obj1)), 0x7f1400dd);
        Object obj3 = new ArrayList();
        int i1 = ((PreferenceGroup) (((HolidayPreferenceBinder) (obj)).preferenceScreen)).mPreferenceList.size();
        for (int i = 0; i < i1; i++)
        {
            Preference preference = (Preference)((PreferenceGroup) (((HolidayPreferenceBinder) (obj)).preferenceScreen)).mPreferenceList.get(i);
            if (preference.mKey.startsWith("account_"))
            {
                ((List) (obj3)).add(preference);
            }
        }

        obj3 = (ArrayList)obj3;
        i1 = ((ArrayList) (obj3)).size();
        int j = ((flag) ? 1 : 0);
        do
        {
            if (j >= i1)
            {
                break;
            }
            Object obj4 = ((ArrayList) (obj3)).get(j);
            int l = j + 1;
            obj4 = (Preference)obj4;
            android.support.v7.preference.PreferenceScreen preferencescreen = ((HolidayPreferenceBinder) (obj)).preferenceScreen;
            preferencescreen.removePreferenceInt(((Preference) (obj4)));
            j = l;
            if (((Preference) (preferencescreen)).mListener != null)
            {
                ((Preference) (preferencescreen)).mListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
                j = l;
            }
        } while (true);
        obj3 = ((HolidayPreferenceBinder) (obj)).viewModel.countrySubscriptions.keySet().iterator();
        for (int k = 2; ((Iterator) (obj3)).hasNext(); k++)
        {
            Account account = (Account)((Iterator) (obj3)).next();
            PreferenceCategory preferencecategory = new PreferenceCategory(((android.content.Context) (obj2)));
            if (k != ((Preference) (preferencecategory)).mOrder)
            {
                preferencecategory.mOrder = k;
                if (((Preference) (preferencecategory)).mListener != null)
                {
                    ((Preference) (preferencecategory)).mListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
                }
            }
            preferencecategory.setTitle(account.name);
            preferencecategory.setKey((new StringBuilder(19)).append("account_").append(k).toString());
            ((HolidayPreferenceBinder) (obj)).preferenceScreen.addPreference(preferencecategory);
            preferencecategory.addPreference(((HolidayPreferenceBinder) (obj)).createHolidayPreference(((android.content.Context) (obj1)), ((HolidayPreferenceBinder) (obj)).viewModel.countryCalendars, (Set)((HolidayPreferenceBinder) (obj)).viewModel.countrySubscriptions.get(account), (new StringBuilder(19)).append("country_").append(k).toString(), 0x7f13013f, 0x7f130140, new nit>(((HolidayPreferenceBinder) (obj)), account)));
            preferencecategory.addPreference(((HolidayPreferenceBinder) (obj)).createHolidayPreference(((android.content.Context) (obj1)), ((HolidayPreferenceBinder) (obj)).viewModel.religiousCalendars, (Set)((HolidayPreferenceBinder) (obj)).viewModel.religiousSubscriptions.get(account), (new StringBuilder(21)).append("religious_").append(k).toString(), 0x7f1303db, 0x7f1303dc, new nit>(((HolidayPreferenceBinder) (obj)), account)));
        }

        obj1 = ((HolidayPreferenceBinder) (obj)).fragment;
        obj2 = ((HolidayPreferenceBinder) (obj)).color;
        obj = ((HolidayPreferenceBinder) (obj)).viewModel;
        obj.getClass();
        ViewUtils.bindColorPreference(((android.support.v4.app.Fragment) (obj1)), ((Preference) (obj2)), new nit>(((HolidayViewModel) (obj))), true);
    }

    (HolidayPreferenceFragment holidaypreferencefragment)
    {
        arg$1 = holidaypreferencefragment;
    }
}
