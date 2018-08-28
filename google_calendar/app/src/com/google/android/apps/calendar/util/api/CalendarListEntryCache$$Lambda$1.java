// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.api;

import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.common.base.Supplier;

public final class 
    implements Supplier
{

    public static final Supplier $instance = new <init>();

    public final Object get()
    {
        return CalendarApi.CalendarList.list(null);
    }


    private ()
    {
    }
}
