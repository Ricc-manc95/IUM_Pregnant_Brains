// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.support.v7.preference.Preference;
import com.google.android.calendar.settings.home.CalendarViewModel;

final class arg._cls1
    implements android.support.v7.preference.er
{

    private final CalendarViewModel arg$1;

    public final boolean onPreferenceChange(Preference preference, Object obj)
    {
        preference = arg$1;
        boolean flag = ((Boolean)obj).booleanValue();
        if (!preference.isSyncEditable())
        {
            throw new IllegalStateException();
        }
        if (flag != ((CalendarViewModel) (preference)).syncEnabled)
        {
            preference.syncEnabled = flag;
            preference.updateCalendar(new com.google.android.calendar.settings.home.a._cls1.arg._cls1(flag));
        }
        return true;
    }

    (CalendarViewModel calendarviewmodel)
    {
        arg$1 = calendarviewmodel;
    }
}
