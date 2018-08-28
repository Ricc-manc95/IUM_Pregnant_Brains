// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.settings.home:
//            CalendarViewModel

final class arg._cls1
    implements AsyncFunction
{

    private final CalendarViewModel arg$1;

    public final ListenableFuture apply(Object obj)
    {
        obj = arg$1;
        return CalendarApi.CalendarList.read(((CalendarViewModel) (obj)).calendar.getDescriptor());
    }

    (CalendarViewModel calendarviewmodel)
    {
        arg$1 = calendarviewmodel;
    }
}
