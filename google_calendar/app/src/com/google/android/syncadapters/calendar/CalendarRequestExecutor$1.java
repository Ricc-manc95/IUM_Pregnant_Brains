// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.os.Process;
import com.android.calendarcommon2.LogUtils;
import com.google.api.services.calendar.CalendarRequest;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            CalendarRequestExecutor

final class val.request
    implements Callable
{

    private final CalendarRequestExecutor this$0;
    private final CalendarRequest val$request;
    private final String val$tag;

    public final Object call()
        throws Exception
    {
        Process.setThreadPriority(10);
        LogUtils.d(CalendarRequestExecutor.TAG, "start(%s) @%s", new Object[] {
            val$tag, Thread.currentThread().getName()
        });
        Object obj = CalendarRequestExecutor.executeInternal(val$tag, val$request);
        LogUtils.d(CalendarRequestExecutor.TAG, "stop(%s) @%s", new Object[] {
            val$tag, Thread.currentThread().getName()
        });
        return obj;
    }

    ()
    {
        this$0 = final_calendarrequestexecutor;
        val$tag = s;
        val$request = CalendarRequest.this;
        super();
    }
}
