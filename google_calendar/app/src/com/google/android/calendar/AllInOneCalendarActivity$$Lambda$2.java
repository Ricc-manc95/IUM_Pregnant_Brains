// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.timely.rooms.RoomServiceStatusTask;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

final class arg._cls1
    implements Runnable
{

    private final AllInOneCalendarActivity arg$1;

    public final void run()
    {
        Object obj = arg$1;
        RoomServiceStatusTask roomservicestatustask = new RoomServiceStatusTask();
        obj = ((Context) (obj)).getApplicationContext();
        CalendarExecutor.NET.execute(new com.google.android.calendar.timely.rooms.it>(roomservicestatustask, ((Context) (obj))));
    }

    .Lambda._cls0(AllInOneCalendarActivity allinonecalendaractivity)
    {
        arg$1 = allinonecalendaractivity;
    }
}
