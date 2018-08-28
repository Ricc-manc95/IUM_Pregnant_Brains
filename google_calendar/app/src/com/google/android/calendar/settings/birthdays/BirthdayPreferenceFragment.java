// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.birthdays;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.google.android.calendar.api.color.NamedCalendarColor;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.settings.SettingsFragment;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.settings.view.ViewUtils;

// Referenced classes of package com.google.android.calendar.settings.birthdays:
//            BirthdayPreferenceBinder, BirthdayViewModel

public final class BirthdayPreferenceFragment extends SettingsFragment
    implements SingleChoiceDialogListener
{

    public BirthdayPreferenceBinder binder;

    public BirthdayPreferenceFragment()
    {
        super("birthday");
    }

    public final void onCreatePreferences$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0()
    {
        class .Lambda._cls0
            implements Consumer
        {

            private final BirthdayPreferenceFragment arg$1;

            public final void accept(Object obj)
            {
                Object obj1;
                Object obj2;
                Object obj3;
                int l;
                obj1 = arg$1;
                obj2 = (HomeViewModel)obj;
                ((PreferenceFragmentCompat) (obj1)).addPreferencesFromResource(0x7f090002);
                obj1.binder = new BirthdayPreferenceBinder(((Fragment) (obj1)), ((PreferenceFragmentCompat) (obj1)).mPreferenceManager.mPreferenceScreen);
                obj = ((BirthdayPreferenceFragment) (obj1)).binder;
                obj.viewModel = ((HomeViewModel) (obj2)).birthdayViewModel;
                obj1 = ((Preference) (((BirthdayPreferenceBinder) (obj)).preferenceScreen)).mContext;
                obj2 = ((Context) (obj1)).getResources();
                ((Preference) (((BirthdayPreferenceBinder) (obj)).preferenceScreen)).mPreferenceManager.mPreferenceDataStore = new InMemoryPreferenceDataStore();
                obj3 = new ArrayList();
                l = ((PreferenceGroup) (((BirthdayPreferenceBinder) (obj)).preferenceScreen)).mPreferenceList.size();
                for (int i = 0; i < l; i++)
                {
                    Preference preference1 = (Preference)((PreferenceGroup) (((BirthdayPreferenceBinder) (obj)).preferenceScreen)).mPreferenceList.get(i);
                    if (preference1.mKey != null && preference1.mKey.startsWith("account_"))
                    {
                        ((List) (obj3)).add(preference1);
                    }
                }

                obj3 = (ArrayList)obj3;
                int i1 = ((ArrayList) (obj3)).size();
                int j = 0;
                do
                {
                    if (j >= i1)
                    {
                        break;
                    }
                    Object obj4 = ((ArrayList) (obj3)).get(j);
                    l = j + 1;
                    obj4 = (Preference)obj4;
                    android.support.v7.preference.PreferenceScreen preferencescreen = ((BirthdayPreferenceBinder) (obj)).preferenceScreen;
                    preferencescreen.removePreferenceInt(((Preference) (obj4)));
                    j = l;
                    if (((Preference) (preferencescreen)).mListener != null)
                    {
                        ((Preference) (preferencescreen)).mListener.onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
                        j = l;
                    }
                } while (true);
                obj3 = ((BirthdayPreferenceBinder) (obj)).viewModel.birthdayModes.keySet().iterator();
                l = 2;
_L2:
                Account account;
                ListPreference listpreference;
                int k;
                if (!((Iterator) (obj3)).hasNext())
                {
                    break MISSING_BLOCK_LABEL_584;
                }
                account = (Account)((Iterator) (obj3)).next();
                com.google.android.calendar.api.settings.GoogleSettings.BirthdayMode birthdaymode = (com.google.android.calendar.api.settings.GoogleSettings.BirthdayMode)((BirthdayPreferenceBinder) (obj)).viewModel.birthdayModes.get(account);
                listpreference = new ListPreference(((Context) (obj1)));
                if (l != ((Preference) (listpreference)).mOrder)
                {
                    listpreference.mOrder = l;
                    if (((Preference) (listpreference)).mListener != null)
                    {
                        ((Preference) (listpreference)).mListener.onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
                    }
                }
                listpreference.setKey((new StringBuilder(19)).append("account_").append(l).toString());
                listpreference.setTitle(((Resources) (obj2)).getString(0x7f130442, new Object[] {
                    account.name
                }));
                switch (birthdaymode.ordinal())
                {
                default:
                    throw new RuntimeException();

                case 0: // '\0'
                    break; /* Loop/switch isn't completed */

                case 2: // '\002'
                    break MISSING_BLOCK_LABEL_576;

                case 1: // '\001'
                    k = 0x7f13043b;
                    break;
                }
_L3:
                String s = ((Resources) (obj2)).getString(k);
                listpreference.setSummary(s);
                listpreference.setEntries(((Preference) (listpreference)).mContext.getResources().getTextArray(0x7f0b0014));
                listpreference.mEntryValues = ((Preference) (listpreference)).mContext.getResources().getTextArray(0x7f0b0014);
                listpreference.setValue(s);
                listpreference.mDialogTitle = ((Preference) (listpreference)).mContext.getString(0x7f13043c);
                listpreference.mOnChangeListener = new BirthdayPreferenceBinder..Lambda._cls0(((BirthdayPreferenceBinder) (obj)), account, listpreference);
                ((BirthdayPreferenceBinder) (obj)).preferenceScreen.addPreference(listpreference);
                l++;
                if (true) goto _L2; else goto _L1
_L1:
                k = 0x7f13043d;
                  goto _L3
                k = 0x7f13043e;
                  goto _L3
                Fragment fragment = ((BirthdayPreferenceBinder) (obj)).fragment;
                Preference preference = ((BirthdayPreferenceBinder) (obj)).color;
                obj = ((BirthdayPreferenceBinder) (obj)).viewModel;
                obj.getClass();
                ViewUtils.bindColorPreference(fragment, preference, new BirthdayPreferenceBinder..Lambda._cls1(((BirthdayViewModel) (obj))), true);
                return;
            }

            .Lambda._cls0()
            {
                arg$1 = BirthdayPreferenceFragment.this;
            }
        }

        loadModel(new .Lambda._cls0());
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        Object obj1 = (NamedCalendarColor)obj;
        obj = binder;
        Object obj2 = ((BirthdayPreferenceBinder) (obj)).viewModel;
        com.google.android.calendar.api.color.CalendarColor calendarcolor = ((BirthdayViewModel) (obj2)).color;
        if (obj1 == calendarcolor || obj1 != null && obj1.equals(calendarcolor))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            obj2.color = ((com.google.android.calendar.api.color.CalendarColor) (obj1));
            SettingsShims.instance.setBirthdayColor(((BirthdayViewModel) (obj2)).context, ((com.google.android.calendar.api.color.CalendarColor) (obj1)));
        }
        obj1 = ((BirthdayPreferenceBinder) (obj)).fragment;
        obj2 = ((BirthdayPreferenceBinder) (obj)).color;
        obj = ((BirthdayPreferenceBinder) (obj)).viewModel;
        obj.getClass();
        ViewUtils.bindColorPreference(((Fragment) (obj1)), ((Preference) (obj2)), new BirthdayPreferenceBinder..Lambda._cls1(((BirthdayViewModel) (obj))), true);
    }

    public final void onStart()
    {
        super.onStart();
        setActionBarTitle(requireContext().getResources().getString(0x7f130194));
    }

    public final boolean onStartHelp(AppCompatActivity appcompatactivity)
    {
        SettingsShims.instance.launchHelpAndFeedback(appcompatactivity, requireContext().getResources().getString(0x7f1300e1));
        return true;
    }
}
