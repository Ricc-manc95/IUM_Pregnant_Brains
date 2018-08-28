// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;


// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListFactory, CalendarListEntryModificationsImpl, CalendarListEntry, CalendarListEntryModifications

public final class CalendarListFactoryImpl
    implements CalendarListFactory
{

    public CalendarListFactoryImpl()
    {
    }

    public final CalendarListEntryModifications modifyCalendarListEntry(CalendarListEntry calendarlistentry)
    {
        return new CalendarListEntryModificationsImpl(calendarlistentry);
    }
}
