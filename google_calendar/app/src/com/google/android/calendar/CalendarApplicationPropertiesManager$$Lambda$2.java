// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar:
//            CalendarApplicationPropertiesManager

final class arg._cls1
    implements android.view.accessibility.stener
{

    private final CalendarApplicationPropertiesManager arg$1;

    public final void onAccessibilityStateChanged(boolean flag)
    {
        Object obj = arg$1;
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        obj = new <init>(((CalendarApplicationPropertiesManager) (obj)));
        TimeUnit timeunit = TimeUnit.SECONDS;
        calendarexecutor.getDelegate().schedule(((Runnable) (obj)), 1L, timeunit);
    }

    tener(CalendarApplicationPropertiesManager calendarapplicationpropertiesmanager)
    {
        arg$1 = calendarapplicationpropertiesmanager;
    }
}
