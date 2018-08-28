// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.accounts.Account;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.timely.settings.data.CalendarProperties;

// Referenced classes of package com.google.android.calendar.groove:
//            CreateGrooveActivity

final class arg._cls2
    implements Runnable
{

    private final CreateGrooveActivity arg$1;
    private final Habit arg$2;

    public final void run()
    {
        CalendarListEntry acalendarlistentry[];
        Object obj1;
        Object obj = arg$1;
        obj1 = arg$2.getDescriptor();
        obj = CalendarListEntryCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        acalendarlistentry = (CalendarListEntry[])((ListenableFutureCache)obj).result;
        if (acalendarlistentry == null) goto _L2; else goto _L1
_L1:
        Account account;
        int i;
        int j;
        account = ((HabitDescriptor) (obj1)).calendar.account;
        obj1 = ((HabitDescriptor) (obj1)).calendar.calendarId;
        j = acalendarlistentry.length;
        i = 0;
_L7:
        if (i >= j) goto _L2; else goto _L3
_L3:
        CalendarListEntry calendarlistentry = acalendarlistentry[i];
        if (!account.equals(calendarlistentry.getDescriptor().account) || !((String) (obj1)).equals(calendarlistentry.getDescriptor().calendarId)) goto _L5; else goto _L4
_L4:
        CalendarDescriptor calendardescriptor = calendarlistentry.getDescriptor();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        ((CalendarProperties)calendarproperties).setDefaultCalendarIdValue(calendardescriptor, true);
_L2:
        return;
_L5:
        i++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    (CreateGrooveActivity creategrooveactivity, Habit habit)
    {
        arg$1 = creategrooveactivity;
        arg$2 = habit;
    }
}
