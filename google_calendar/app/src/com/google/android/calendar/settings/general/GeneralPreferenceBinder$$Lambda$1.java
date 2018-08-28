// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.settings.SettingsShims;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceViewModel

final class arg._cls1
    implements Consumer
{

    private final GeneralPreferenceViewModel arg$1;

    public final void accept(Object obj)
    {
        GeneralPreferenceViewModel generalpreferenceviewmodel = arg$1;
        boolean flag = ((Boolean)obj).booleanValue();
        if (flag != generalpreferenceviewmodel.showWeekNumber)
        {
            generalpreferenceviewmodel.showWeekNumber = flag;
            generalpreferenceviewmodel.context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().n("preferences_show_week_num", generalpreferenceviewmodel.showWeekNumber).showWeekNumber();
            SettingsShims.instance.broadcastWidgetUpdate(generalpreferenceviewmodel.context);
        }
    }

    (GeneralPreferenceViewModel generalpreferenceviewmodel)
    {
        arg$1 = generalpreferenceviewmodel;
    }
}
