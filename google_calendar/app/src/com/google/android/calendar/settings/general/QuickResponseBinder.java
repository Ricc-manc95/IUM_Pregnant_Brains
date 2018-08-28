// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceViewModel

public final class QuickResponseBinder
{

    public final PreferenceScreen preferenceScreen;
    public GeneralPreferenceViewModel viewModel;

    public QuickResponseBinder(PreferenceScreen preferencescreen)
    {
        preferenceScreen = preferencescreen;
    }

    final void sortPreferences()
    {
        boolean flag = false;
        ArrayList arraylist = new ArrayList(((PreferenceGroup) (preferenceScreen)).mPreferenceList.size());
        for (int i = 0; i < ((PreferenceGroup) (preferenceScreen)).mPreferenceList.size(); i++)
        {
            arraylist.add(((Preference)((PreferenceGroup) (preferenceScreen)).mPreferenceList.get(i)).mTitle.toString());
        }

        class .Lambda._cls1
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls1();

            public final int compare(Object obj, Object obj1)
            {
                return ((String)obj).compareToIgnoreCase((String)obj1);
            }


            private .Lambda._cls1()
            {
            }
        }

        Collections.sort(arraylist, .Lambda._cls1..instance);
        for (int j = ((flag) ? 1 : 0); j < ((PreferenceGroup) (preferenceScreen)).mPreferenceList.size(); j++)
        {
            Preference preference = (Preference)((PreferenceGroup) (preferenceScreen)).mPreferenceList.get(j);
            int k = arraylist.indexOf(preference.mTitle.toString());
            if (k == preference.mOrder)
            {
                continue;
            }
            preference.mOrder = k;
            if (preference.mListener != null)
            {
                preference.mListener.onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
            }
        }

    }
}
