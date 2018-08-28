// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.settings.general:
//            QuickResponseBinder, GeneralPreferenceViewModel

final class arg._cls3
    implements android.support.v7.preference.istener
{

    private final QuickResponseBinder arg$1;
    private final String arg$2;
    private final EditTextPreference arg$3;

    public final boolean onPreferenceChange(Preference preference, Object obj)
    {
label0:
        {
            preference = arg$1;
            String s = arg$2;
            EditTextPreference edittextpreference = arg$3;
            obj = ((String)obj).trim();
            GeneralPreferenceViewModel generalpreferenceviewmodel = ((QuickResponseBinder) (preference)).viewModel;
            if (generalpreferenceviewmodel.quickResponses.contains(obj))
            {
                boolean flag;
                boolean flag1;
                boolean flag2;
                if (s == obj || s != null && s.equals(obj))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    break label0;
                }
            }
            generalpreferenceviewmodel.quickResponses.remove(s);
            generalpreferenceviewmodel.quickResponses.add(obj);
            generalpreferenceviewmodel.context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().ringSet("preferences_quick_responses", generalpreferenceviewmodel.quickResponses).();
        }
        edittextpreference.setTitle(((CharSequence) (obj)));
        flag1 = edittextpreference.shouldDisableDependents();
        edittextpreference.mText = ((String) (obj));
        edittextpreference.persistString(((String) (obj)));
        flag2 = edittextpreference.shouldDisableDependents();
        if (flag2 != flag1)
        {
            edittextpreference.notifyDependencyChange(flag2);
        }
        preference.sortPreferences();
        return true;
    }

    (QuickResponseBinder quickresponsebinder, String s, EditTextPreference edittextpreference)
    {
        arg$1 = quickresponsebinder;
        arg$2 = s;
        arg$3 = edittextpreference;
    }
}
