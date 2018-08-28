// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarKey;

public abstract class Calendar
{

    public Calendar()
    {
    }

    public abstract CalendarAccessLevel getAccessLevel();

    public abstract String getAccountName();

    public abstract String getAccountType();

    public abstract CalendarKey getKey();

    public abstract String getOwnerAccount();
}
