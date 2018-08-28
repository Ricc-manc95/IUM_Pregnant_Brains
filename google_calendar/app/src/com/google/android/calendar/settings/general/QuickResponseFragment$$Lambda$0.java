// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceManager;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.settings.InMemoryPreferenceDataStore;
import com.google.android.calendar.settings.home.HomeViewModel;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.settings.general:
//            QuickResponseBinder, GeneralPreferenceViewModel, QuickResponseFragment

final class arg._cls1
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
            edittextpreference.mOnChangeListener = new nit>(((QuickResponseBinder) (obj)), s, edittextpreference);
        }

        ((QuickResponseBinder) (obj)).sortPreferences();
    }

    (QuickResponseFragment quickresponsefragment)
    {
        arg$1 = quickresponsefragment;
    }
}
