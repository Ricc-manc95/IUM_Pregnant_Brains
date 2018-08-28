// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import com.google.android.calendar.api.event.notification.Notification;

// Referenced classes of package com.google.android.calendar.settings.calendar:
//            CalendarPreferenceBinder

final class arg._cls3
    implements com.google.android.calendar.settings.tener
{

    private final CalendarPreferenceBinder arg$1;
    private final boolean arg$2;
    private final int arg$3;

    public final void onSet(int i, int j)
    {
        arg$1.setOrAddNotification(arg$2, arg$3, new Notification(j, i));
    }

    (CalendarPreferenceBinder calendarpreferencebinder, boolean flag, int i)
    {
        arg$1 = calendarpreferencebinder;
        arg$2 = flag;
        arg$3 = i;
    }
}
