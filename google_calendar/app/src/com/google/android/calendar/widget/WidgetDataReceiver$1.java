// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.calendar.widget:
//            CalendarAppWidgetService, WidgetDataReceiver

final class val.result
    implements Runnable
{

    private final WidgetDataReceiver this$0;
    private final android.content.dingResult val$result;

    public final void run()
    {
        LogUtils.d("CalendarWidget", "running refresh runnable when:%d", new Object[] {
            Integer.valueOf(CalendarAppWidgetService.currentVersion.get())
        });
        timezone = Utils.getTimeZoneId(context);
        startRefreshData(val$result);
    }

    ice()
    {
        this$0 = final_widgetdatareceiver;
        val$result = android.content.dingResult.this;
        super();
    }
}
