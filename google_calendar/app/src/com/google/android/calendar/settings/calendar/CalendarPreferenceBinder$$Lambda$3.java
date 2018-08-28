// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import com.google.android.calendar.settings.home.CalendarViewModel;
import com.google.common.base.Supplier;

final class arg._cls1
    implements Supplier
{

    private final CalendarViewModel arg$1;

    public final Object get()
    {
        return arg$1.calendarColor;
    }

    (CalendarViewModel calendarviewmodel)
    {
        arg$1 = calendarviewmodel;
    }
}
