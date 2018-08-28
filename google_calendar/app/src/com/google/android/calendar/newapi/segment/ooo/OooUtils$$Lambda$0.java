// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.ooo;

import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.common.base.Predicate;

final class arg._cls1
    implements Predicate
{

    private final CalendarDescriptor arg$1;

    public final boolean apply(Object obj)
    {
        CalendarDescriptor calendardescriptor = arg$1;
        return ((CalendarListEntry)obj).getDescriptor().equals(calendardescriptor);
    }

    (CalendarDescriptor calendardescriptor)
    {
        arg$1 = calendardescriptor;
    }
}
