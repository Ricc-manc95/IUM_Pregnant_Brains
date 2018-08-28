// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import java.util.List;

public final class CalendarSyncInfo
{

    public int accessLevel;
    public Account account;
    public long calendarId;
    public String calendarSyncId;
    public String calendarTimezone;
    public List defaultAllDayReminders;
    public List defaultReminders;

    public CalendarSyncInfo()
    {
    }
}
