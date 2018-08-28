// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import com.google.android.calendar.settings.SettingsFragment;

public final class QuickResponseFragment extends SettingsFragment
{

    public QuickResponseFragment()
    {
        super("quick_response");
    }

    public final void onCreatePreferences$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0()
    {
        class .Lambda._cls0
            implements Consumer
        {

            private final QuickResponseFragment arg$1;

            public final void accept(Object obj)
            {
                Object obj1 = arg$1;
                HomeViewModel homeviewmodel = (HomeViewModel)obj;
                ((PreferenceFragmentCompat) (obj1)).addPreferencesFromResource(0x7f09000d);
                obj = new QuickResponseBinder(((PreferenceFragmentCompat) (obj1)).mPreferenceManager.mPreferenceScreen);
                obj1 = homeviewmodel.generalPreferenceViewModel;
                ((Preference) (((QuickResponseBinder) (obj)).preferenceScreen)).mPreferenceManager.mPreferenceDataStore = new InMemoryPreferenceDataStore();
                obj.viewModel = ((GeneralPreferenceViewModel) (obj1));
                ((QuickResponseBinder) (obj)).preferenceScreen.removeAll();
                obj1 = (UnmodifiableIterator)ImmutableSet.copyOf(((QuickResponseBinder) (obj)).viewModel.quickResponses).iterator();
                for (int i = 0; ((Iterator) (obj1)).hasNext(); i++)
                {
                    String s = (String)((Iterator) (obj1)).next();
                    EditTextPreference edittextpreference = new EditTextPreference(((Preference) (((QuickResponseBinder) (obj)).preferenceScreen)).mContext);
                    edittextpreference.setTitle(s);
                    boolean flag = edittextpreference.shouldDisableDependents();
                    edittextpreference.mText = s;
                    edittextpreference.persistString(s);
                    boolean flag1 = edittextpreference.shouldDisableDependents();
                    if (flag1 != flag)
                    {
                        edittextpreference.notifyDependencyChange(flag1);
                    }
                    edittextpreference.setKey((new StringBuilder(16)).append("key_ ").append(i).toString());
                    ((QuickResponseBinder) (obj)).preferenceScreen.addPreference(edittextpreference);
                    edittextpreference.mOnChangeListener = new QuickResponseBinder..Lambda._cls0(((QuickResponseBinder) (obj)), s, edittextpreference);
                }

                ((QuickResponseBinder) (obj)).sortPreferences();
            }

            .Lambda._cls0()
            {
                arg$1 = QuickResponseFragment.this;
            }
        }

        loadModel(new .Lambda._cls0());
    }

    public final void onStart()
    {
        super.onStart();
        setActionBarTitle(requireContext().getResources().getString(0x7f1303cd));
    }
}
