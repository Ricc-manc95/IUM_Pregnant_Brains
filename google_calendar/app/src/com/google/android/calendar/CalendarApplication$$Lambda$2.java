// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.calendar.alerts.AlertServiceHelper;

// Referenced classes of package com.google.android.calendar:
//            CalendarApplication

final class arg._cls1
    implements Runnable
{

    private final CalendarApplication arg$1;

    public final void run()
    {
        AlertServiceHelper.updateAlertNotification(arg$1);
    }

    (CalendarApplication calendarapplication)
    {
        arg$1 = calendarapplication;
    }
}
