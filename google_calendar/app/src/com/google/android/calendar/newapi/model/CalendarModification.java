// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;

// Referenced classes of package com.google.android.calendar.newapi.model:
//            CalendarListEntryHolder

public interface CalendarModification
    extends CalendarListEntryHolder
{

    public abstract boolean canChangeOrganizer();

    public abstract void switchCalendar(CalendarListEntry calendarlistentry);
}
