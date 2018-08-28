// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import android.support.v7.preference.Preference;
import com.google.android.calendar.settings.holidays.HolidayPreferenceFragment;

// Referenced classes of package com.google.android.calendar.settings.home:
//            HomePreferenceBinder

final class arg._cls1
    implements android.support.v7.preference.tener
{

    private final HomePreferenceBinder arg$1;

    public final boolean onPreferenceClick(Preference preference)
    {
        arg$1.showFragment(new HolidayPreferenceFragment());
        return true;
    }

    (HomePreferenceBinder homepreferencebinder)
    {
        arg$1 = homepreferencebinder;
    }
}
