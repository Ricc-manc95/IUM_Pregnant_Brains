// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.time;

import android.content.Context;
import android.text.format.Time;

// Referenced classes of package com.google.android.calendar.time:
//            TimeUtils, TimeZoneImpl, DateTimeService

final class val.context
    implements Runnable
{

    private final DateTimeService this$0;
    private final Context val$context;

    public final void run()
    {
        DateTimeService datetimeservice = DateTimeService.this;
        Object obj = val$context;
        Utils utils = TimeUtils.tZUtils;
        if (Utils.firstTZRequest)
        {
            Utils.getTimeZone(((Context) (obj)), null, false);
        }
        if (Utils.useHomeTZ)
        {
            obj = Utils.homeTZ;
        } else
        {
            obj = Time.getCurrentTimezone();
        }
        datetimeservice.calendarTimeZone = new TimeZoneImpl(((String) (obj)));
    }

    Utils()
    {
        this$0 = final_datetimeservice;
        val$context = Context.this;
        super();
    }
}
