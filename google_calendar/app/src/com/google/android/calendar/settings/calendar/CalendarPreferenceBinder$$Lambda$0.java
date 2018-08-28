// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.preference.Preference;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.settings.home.CalendarViewModel;

// Referenced classes of package com.google.android.calendar.settings.calendar:
//            CalendarPreferenceBinder

final class arg._cls2
    implements android.support.v7.preference.r
{

    private final CalendarPreferenceBinder arg$1;
    private final CalendarViewModel arg$2;

    public final boolean onPreferenceClick(Preference preference)
    {
        preference = arg$1;
        Object obj = arg$2;
        obj = (new Intent()).setAction("com.google.android.gms.family.v2.MANAGE").putExtra("accountName", ((CalendarViewModel) (obj)).calendar.getDescriptor().account.name).putExtra("appId", "calendar");
        ((Activity)((CalendarPreferenceBinder) (preference)).context).startActivityForResult(((Intent) (obj)), 0);
        return true;
    }

    (CalendarPreferenceBinder calendarpreferencebinder, CalendarViewModel calendarviewmodel)
    {
        arg$1 = calendarpreferencebinder;
        arg$2 = calendarviewmodel;
    }
}
