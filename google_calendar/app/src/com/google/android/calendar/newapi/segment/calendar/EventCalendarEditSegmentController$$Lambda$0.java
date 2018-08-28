// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.newapi.segment.ooo.OooUtils;
import com.google.common.base.Predicate;

final class 
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        return OooUtils.isOooSupportedCalendar((CalendarListEntry)obj);
    }


    private ()
    {
    }
}
