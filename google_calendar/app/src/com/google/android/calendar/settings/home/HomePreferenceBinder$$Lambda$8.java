// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import android.content.res.Resources;
import android.support.v7.preference.Preference;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.settings.calendar.CalendarPreferenceFragment;
import com.google.android.calendar.settings.reminders.RemindersFragment;

// Referenced classes of package com.google.android.calendar.settings.home:
//            CalendarViewModel, HomePreferenceBinder, ReminderViewModel, CalendarListItemViewModel

final class arg._cls3
    implements android.support.v7.preference.tener
{

    private final HomePreferenceBinder arg$1;
    private final CalendarListItemViewModel arg$2;
    private final Resources arg$3;

    public final boolean onPreferenceClick(Preference preference)
    {
        Resources resources;
        Object obj;
        preference = arg$1;
        obj = arg$2;
        resources = arg$3;
        if (!(obj instanceof CalendarViewModel)) goto _L2; else goto _L1
_L1:
        obj = (CalendarViewModel)obj;
        preference.showFragment(CalendarPreferenceFragment.newInstance(((CalendarViewModel) (obj)).calendar.getDescriptor(), (String)((CalendarViewModel) (obj)).getDisplayName(resources)));
_L4:
        return true;
_L2:
        if (obj instanceof ReminderViewModel)
        {
            preference.showFragment(RemindersFragment.newInstance(((ReminderViewModel)obj).settings.getDescriptor()));
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    (HomePreferenceBinder homepreferencebinder, CalendarListItemViewModel calendarlistitemviewmodel, Resources resources)
    {
        arg$1 = homepreferencebinder;
        arg$2 = calendarlistitemviewmodel;
        arg$3 = resources;
    }
}
