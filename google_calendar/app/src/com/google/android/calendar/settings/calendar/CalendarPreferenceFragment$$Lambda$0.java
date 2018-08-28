// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.settings.home.CalendarViewModel;
import com.google.android.calendar.settings.home.HomeViewModel;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.settings.calendar:
//            CalendarPreferenceBinder, CalendarPreferenceFragment

final class arg._cls1
    implements Consumer
{

    private final CalendarPreferenceFragment arg$1;

    public final void accept(Object obj)
    {
        CalendarPreferenceFragment calendarpreferencefragment = arg$1;
        obj = (HomeViewModel)obj;
        CalendarDescriptor calendardescriptor = (CalendarDescriptor)calendarpreferencefragment.getArguments().getParcelable("EXTRA_CALENDAR_DESCRIPTOR");
        obj = (CalendarViewModel)((HomeViewModel) (obj)).calendarViewModels.get(calendardescriptor);
        if (obj != null)
        {
            calendarpreferencefragment.addPreferencesFromResource(0x7f090003);
            calendarpreferencefragment.binder = new CalendarPreferenceBinder(calendarpreferencefragment.getContext(), calendarpreferencefragment, ((PreferenceFragmentCompat) (calendarpreferencefragment)).mPreferenceManager.mPreferenceScreen);
            calendarpreferencefragment.binder.bind(((CalendarViewModel) (obj)));
        }
    }

    (CalendarPreferenceFragment calendarpreferencefragment)
    {
        arg$1 = calendarpreferencefragment;
    }
}
