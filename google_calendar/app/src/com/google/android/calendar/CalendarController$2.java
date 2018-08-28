// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.calendar.time.Time;

// Referenced classes of package com.google.android.calendar:
//            CalendarController, Utils

final class this._cls0
    implements Runnable
{

    private final CalendarController this$0;

    public final void run()
    {
        Time time = CalendarController.this.time;
        String s = Utils.getTimeZoneId(activity, this);
        time.writeFieldsToImpl();
        time.impl.switchTimezone(s);
        time.copyFieldsFromImpl();
    }

    ()
    {
        this$0 = CalendarController.this;
        super();
    }
}
