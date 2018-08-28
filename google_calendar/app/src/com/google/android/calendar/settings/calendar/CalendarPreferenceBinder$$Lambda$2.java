// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.support.v7.preference.Preference;
import com.google.android.calendar.settings.home.CalendarViewModel;

// Referenced classes of package com.google.android.calendar.settings.calendar:
//            CalendarPreferenceBinder

final class arg._cls2
    implements android.support.v7.preference.er
{

    private final CalendarPreferenceBinder arg$1;
    private final CalendarViewModel arg$2;

    public final boolean onPreferenceChange(Preference preference, Object obj)
    {
        preference = arg$1;
        CalendarViewModel calendarviewmodel = arg$2;
        String s = (String)obj;
        if (!calendarviewmodel.isNameEditable())
        {
            throw new IllegalStateException();
        }
        String s1 = calendarviewmodel.displayName;
        boolean flag;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            calendarviewmodel.displayName = s;
            calendarviewmodel.updateCalendar(new com.google.android.calendar.settings.home.a._cls2.arg._cls2(calendarviewmodel));
        }
        ((CalendarPreferenceBinder) (preference)).name.setSummary((String)obj);
        return true;
    }

    (CalendarPreferenceBinder calendarpreferencebinder, CalendarViewModel calendarviewmodel)
    {
        arg$1 = calendarpreferencebinder;
        arg$2 = calendarviewmodel;
    }
}
