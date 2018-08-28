// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.holidays;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.google.android.calendar.api.color.NamedCalendarColor;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.prefs.PrefService;
import com.google.android.calendar.settings.SettingsFragment;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.settings.view.ViewUtils;

// Referenced classes of package com.google.android.calendar.settings.holidays:
//            HolidayPreferenceBinder, HolidayViewModel

public final class HolidayPreferenceFragment extends SettingsFragment
    implements SingleChoiceDialogListener
{

    public HolidayPreferenceBinder binder;

    public HolidayPreferenceFragment()
    {
        super("holiday");
    }

    public final void onCreatePreferences$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0()
    {
        class .Lambda._cls0
            implements Consumer
        {

            private final HolidayPreferenceFragment arg$1;

            public final void accept(Object obj)
            {
                boolean flag = false;
                Object obj1 = arg$1;
                Object obj2 = (HomeViewModel)obj;
                ((PreferenceFragmentCompat) (obj1)).addPreferencesFromResource(0x7f090007);
                obj1.binder = new HolidayPreferenceBinder(((Fragment) (obj1)), ((PreferenceFragmentCompat) (obj1)).mPreferenceManager.mPreferenceScreen);
                obj = ((HolidayPreferenceFragment) (obj1)).binder;
                obj.viewModel = ((HomeViewModel) (obj2)).holidayViewModel;
                obj1 = ((Preference) (((HolidayPreferenceBinder) (obj)).preferenceScreen)).mContext;
                ((Preference) (((HolidayPreferenceBinder) (obj)).preferenceScreen)).mPreferenceManager.mPreferenceDataStore = ((HolidayPreferenceBinder) (obj)).dataStore;
                obj2 = new ContextThemeWrapper(((Context) (obj1)), 0x7f1400dd);
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
                        ((Preference) (preferencescreen)).mListener.onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
                        j = l;
                    }
                } while (true);
                obj3 = ((HolidayPreferenceBinder) (obj)).viewModel.countrySubscriptions.keySet().iterator();
                for (int k = 2; ((Iterator) (obj3)).hasNext(); k++)
                {
                    Account account = (Account)((Iterator) (obj3)).next();
                    PreferenceCategory preferencecategory = new PreferenceCategory(((Context) (obj2)));
                    if (k != ((Preference) (preferencecategory)).mOrder)
                    {
                        preferencecategory.mOrder = k;
                        if (((Preference) (preferencecategory)).mListener != null)
                        {
                            ((Preference) (preferencecategory)).mListener.onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
                        }
                    }
                    preferencecategory.setTitle(account.name);
                    preferencecategory.setKey((new StringBuilder(19)).append("account_").append(k).toString());
                    ((HolidayPreferenceBinder) (obj)).preferenceScreen.addPreference(preferencecategory);
                    preferencecategory.addPreference(((HolidayPreferenceBinder) (obj)).createHolidayPreference(((Context) (obj1)), ((HolidayPreferenceBinder) (obj)).viewModel.countryCalendars, (Set)((HolidayPreferenceBinder) (obj)).viewModel.countrySubscriptions.get(account), (new StringBuilder(19)).append("country_").append(k).toString(), 0x7f13013f, 0x7f130140, new HolidayPreferenceBinder..Lambda._cls0(((HolidayPreferenceBinder) (obj)), account)));
                    preferencecategory.addPreference(((HolidayPreferenceBinder) (obj)).createHolidayPreference(((Context) (obj1)), ((HolidayPreferenceBinder) (obj)).viewModel.religiousCalendars, (Set)((HolidayPreferenceBinder) (obj)).viewModel.religiousSubscriptions.get(account), (new StringBuilder(21)).append("religious_").append(k).toString(), 0x7f1303db, 0x7f1303dc, new HolidayPreferenceBinder..Lambda._cls1(((HolidayPreferenceBinder) (obj)), account)));
                }

                obj1 = ((HolidayPreferenceBinder) (obj)).fragment;
                obj2 = ((HolidayPreferenceBinder) (obj)).color;
                obj = ((HolidayPreferenceBinder) (obj)).viewModel;
                obj.getClass();
                ViewUtils.bindColorPreference(((Fragment) (obj1)), ((Preference) (obj2)), new HolidayPreferenceBinder..Lambda._cls2(((HolidayViewModel) (obj))), true);
            }

            .Lambda._cls0()
            {
                arg$1 = HolidayPreferenceFragment.this;
            }
        }

        loadModel(new .Lambda._cls0());
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        Object obj1 = (NamedCalendarColor)obj;
        obj = binder;
        Object obj2 = ((HolidayPreferenceBinder) (obj)).viewModel;
        com.google.android.calendar.api.color.CalendarColor calendarcolor = PrefService.getInstance(((HolidayViewModel) (obj2)).context).holidaysColor;
        if (obj1 == calendarcolor || obj1 != null && obj1.equals(calendarcolor))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            PrefService.getInstance(((HolidayViewModel) (obj2)).context).setHolidaysColor(((com.google.android.calendar.api.color.CalendarColor) (obj1)));
        }
        obj1 = ((HolidayPreferenceBinder) (obj)).fragment;
        obj2 = ((HolidayPreferenceBinder) (obj)).color;
        obj = ((HolidayPreferenceBinder) (obj)).viewModel;
        obj.getClass();
        ViewUtils.bindColorPreference(((Fragment) (obj1)), ((Preference) (obj2)), new HolidayPreferenceBinder..Lambda._cls2(((HolidayViewModel) (obj))), true);
    }

    public final void onStart()
    {
        super.onStart();
        setActionBarTitle(requireContext().getResources().getString(0x7f130199));
    }

    public final boolean onStartHelp(AppCompatActivity appcompatactivity)
    {
        SettingsShims.instance.launchHelpAndFeedback(appcompatactivity, requireContext().getResources().getString(0x7f1300e1));
        return true;
    }
}
