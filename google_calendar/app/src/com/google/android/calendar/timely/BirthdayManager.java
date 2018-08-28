// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.content.Context;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.utils.account.PrimaryAccountSelector;

public final class BirthdayManager
{

    public static CalendarListEntry getPrimaryBirthdayCalendar(Context context, CalendarListEntry acalendarlistentry[])
    {
        if (acalendarlistentry != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        if (PrimaryAccountSelector.instance == null)
        {
            PrimaryAccountSelector.instance = new PrimaryAccountSelector(context);
        }
        context = PrimaryAccountSelector.instance.account;
        if (context != null)
        {
            int j = acalendarlistentry.length;
            int i = 0;
            while (i < j) 
            {
                CalendarListEntry calendarlistentry = acalendarlistentry[i];
                if (CalendarType.isBirthdayCalendar(calendarlistentry.getDescriptor().calendarId) && ((Account) (context)).name.equals(calendarlistentry.getDescriptor().account.name))
                {
                    return calendarlistentry;
                }
                i++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }
}
