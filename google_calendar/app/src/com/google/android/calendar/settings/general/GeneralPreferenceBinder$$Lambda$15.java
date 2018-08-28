// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.preference.Preference;
import com.google.android.calendar.settings.SettingsShims;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceBinder, GeneralPreferenceViewModel

final class arg._cls2
    implements android.support.v7.preference.er
{

    private final GeneralPreferenceBinder arg$1;
    private final Resources arg$2;

    public final boolean onPreferenceChange(Preference preference, Object obj)
    {
        preference = arg$1;
        Resources resources = arg$2;
        obj = (String)obj;
        GeneralPreferenceViewModel generalpreferenceviewmodel = ((GeneralPreferenceBinder) (preference)).viewModel;
        int i = Integer.parseInt(((String) (obj)));
        if (i != generalpreferenceviewmodel.firstDayOfWeek)
        {
            generalpreferenceviewmodel.firstDayOfWeek = i;
            generalpreferenceviewmodel.context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().context("preferences_week_start_day", Integer.toString(i)).context();
            SettingsShims.instance.broadcastWidgetUpdate(generalpreferenceviewmodel.context);
        }
        preference.setFirstDayOfWeekSummary(resources, ((String) (obj)));
        return true;
    }

    (GeneralPreferenceBinder generalpreferencebinder, Resources resources)
    {
        arg$1 = generalpreferencebinder;
        arg$2 = resources;
    }
}
