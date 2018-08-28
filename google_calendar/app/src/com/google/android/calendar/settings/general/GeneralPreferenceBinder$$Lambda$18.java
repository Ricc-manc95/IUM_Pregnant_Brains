// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.support.v7.preference.Preference;
import com.google.android.calendar.settings.SettingsShims;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceBinder, GeneralPreferenceViewModel

final class arg._cls2
    implements android.support.v7.preference.er
{

    private final GeneralPreferenceBinder arg$1;
    private final GeneralPreferenceViewModel arg$2;

    public final boolean onPreferenceChange(Preference preference, Object obj)
    {
        preference = arg$1;
        GeneralPreferenceViewModel generalpreferenceviewmodel = arg$2;
        int i = Integer.parseInt((String)obj);
        obj = ((GeneralPreferenceBinder) (preference)).viewModel;
        if (((GeneralPreferenceViewModel) (obj)).alternateCalendar != i)
        {
            obj.alternateCalendar = i;
            SettingsShims.instance.setAlternateCalendar(((GeneralPreferenceViewModel) (obj)).context, i);
        }
        preference.bindAlternateCalendar(generalpreferenceviewmodel);
        return true;
    }

    (GeneralPreferenceBinder generalpreferencebinder, GeneralPreferenceViewModel generalpreferenceviewmodel)
    {
        arg$1 = generalpreferencebinder;
        arg$2 = generalpreferenceviewmodel;
    }
}
