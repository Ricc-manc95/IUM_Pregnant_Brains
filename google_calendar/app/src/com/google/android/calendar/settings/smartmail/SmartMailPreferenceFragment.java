// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.smartmail;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.google.android.calendar.settings.SettingsFragment;
import com.google.android.calendar.settings.SettingsShims;

public final class SmartMailPreferenceFragment extends SettingsFragment
{

    public SmartMailPreferenceFragment()
    {
        super("smart_mail");
    }

    public final void onCreatePreferences$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0()
    {
        class .Lambda._cls0
            implements Consumer
        {

            private final SmartMailPreferenceFragment arg$1;

            public final void accept(Object obj)
            {
                Object obj1 = arg$1;
                obj = (HomeViewModel)obj;
                ((PreferenceFragmentCompat) (obj1)).addPreferencesFromResource(0x7f090010);
                obj1 = new SmartMailPreferenceBinder(((PreferenceFragmentCompat) (obj1)).mPreferenceManager.mPreferenceScreen);
                obj1.viewModel = ((HomeViewModel) (obj)).smartMailViewModel;
                ContextThemeWrapper contextthemewrapper = new ContextThemeWrapper(((Preference) (((SmartMailPreferenceBinder) (obj1)).preferenceScreen)).mContext, 0x7f1400dd);
                ContextThemeWrapper contextthemewrapper1 = new ContextThemeWrapper(((Preference) (((SmartMailPreferenceBinder) (obj1)).preferenceScreen)).mContext, 0x7f1400de);
                obj = contextthemewrapper.getResources();
                ((Preference) (((SmartMailPreferenceBinder) (obj1)).preferenceScreen)).mPreferenceManager.mPreferenceDataStore = new InMemoryPreferenceDataStore();
                String s = com.google.android.calendar.api.settings.GoogleSettings.SmartMailMode.CREATE.name();
                String s1 = com.google.android.calendar.api.settings.GoogleSettings.SmartMailMode.CREATE_PRIVATE.name();
                String s2 = com.google.android.calendar.api.settings.GoogleSettings.SmartMailMode.CREATE_SECRET.name();
                String as[] = new String[3];
                as[0] = ((Resources) (obj)).getString(0x7f1304af);
                as[1] = ((Resources) (obj)).getString(0x7f1304b2);
                as[2] = ((Resources) (obj)).getString(0x7f1304b8);
                Iterator iterator = ((SmartMailPreferenceBinder) (obj1)).viewModel.smartMailModes.entrySet().iterator();
                int i = 0;
                while (iterator.hasNext()) 
                {
                    obj = (java.util.Map.Entry)iterator.next();
                    PreferenceCategory preferencecategory = new PreferenceCategory(contextthemewrapper);
                    Account account = (Account)((java.util.Map.Entry) (obj)).getKey();
                    com.google.android.calendar.api.settings.GoogleSettings.SmartMailMode smartmailmode = (com.google.android.calendar.api.settings.GoogleSettings.SmartMailMode)((java.util.Map.Entry) (obj)).getValue();
                    preferencecategory.setTitle(account.name);
                    ((SmartMailPreferenceBinder) (obj1)).preferenceScreen.addPreference(preferencecategory);
                    SwitchPreference switchpreference;
                    boolean flag;
                    if (((SmartMailPreferenceBinder) (obj1)).viewModel.shared.contains(account))
                    {
                        obj = new ListPreference(contextthemewrapper1);
                        ((Preference) (obj)).setKey((new StringBuilder(22)).append("visibility_").append(i).toString());
                        ((Preference) (obj)).setTitle(((Preference) (obj)).mContext.getString(0x7f1304b9));
                        ((ListPreference) (obj)).setEntries(as);
                        obj.mEntryValues = (new String[] {
                            s, s1, s2
                        });
                        obj.mOnChangeListener = new SmartMailPreferenceBinder..Lambda._cls0(((SmartMailPreferenceBinder) (obj1)), ((ListPreference) (obj)), account, as);
                        ((SmartMailPreferenceBinder) (obj1)).updateVisibilityMode(((ListPreference) (obj)), as, smartmailmode);
                    } else
                    {
                        obj = null;
                    }
                    switchpreference = new SwitchPreference(contextthemewrapper1);
                    switchpreference.setKey((new StringBuilder(19)).append("enabled_").append(i).toString());
                    switchpreference.setTitle(((Preference) (switchpreference)).mContext.getString(0x7f130333));
                    if (smartmailmode != com.google.android.calendar.api.settings.GoogleSettings.SmartMailMode.IGNORE)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    switchpreference.setChecked(flag);
                    switchpreference.mOnChangeListener = new SmartMailPreferenceBinder..Lambda._cls1(((SmartMailPreferenceBinder) (obj1)), switchpreference, account, ((ListPreference) (obj)), as);
                    preferencecategory.addPreference(switchpreference);
                    if (obj != null)
                    {
                        preferencecategory.addPreference(((Preference) (obj)));
                    }
                    i++;
                }
            }

            .Lambda._cls0()
            {
                arg$1 = SmartMailPreferenceFragment.this;
            }
        }

        loadModel(new .Lambda._cls0());
    }

    public final void onStart()
    {
        super.onStart();
        setActionBarTitle(requireContext().getResources().getString(0x7f130333));
    }

    public final boolean onStartHelp(AppCompatActivity appcompatactivity)
    {
        SettingsShims.instance.launchHelpAndFeedback(appcompatactivity, requireContext().getResources().getString(0x7f1301d9));
        return true;
    }
}
