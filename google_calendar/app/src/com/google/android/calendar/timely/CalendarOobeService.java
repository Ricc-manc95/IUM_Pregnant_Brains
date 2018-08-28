// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CalendarOobeService extends Service
{

    private final com.google.android.calendar.ICalendarOobeService.Stub binder = new _cls1();

    public CalendarOobeService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        return binder;
    }

    private class _cls1 extends com.google.android.calendar.ICalendarOobeService.Stub
    {

        private final CalendarOobeService this$0;

        public final boolean isOobeCompleted()
        {
            return PreferencesUtils.hasOobeBeenSeen(CalendarOobeService.this);
        }

        _cls1()
        {
            this$0 = CalendarOobeService.this;
            super();
        }
    }

}
