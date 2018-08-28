// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Trace;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.FutureCallback;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            EventRangedQueryHandler, RangedData

final class val.data
    implements FutureCallback
{

    private final EventRangedQueryHandler this$0;
    private final RangedData val$data;
    private final int val$queryToken;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.wtf("MonthQueryHandler", throwable, "Error processing events", new Object[0]);
        onQueryComplete(val$queryToken, val$data, null);
        class .Lambda._cls2
            implements Runnable
        {

            private final EventRangedQueryHandler arg$1;

            public final void run()
            {
                arg$1.deQueue();
            }

            .Lambda._cls2(EventRangedQueryHandler eventrangedqueryhandler)
            {
                arg$1 = eventrangedqueryhandler;
            }
        }

        CalendarExecutor.MAIN.execute(new .Lambda._cls2(EventRangedQueryHandler.this));
    }

    public final void onSuccess(Object obj)
    {
        obj = (List)obj;
        Trace.beginSection("AbstractRangedQueryHandler queryComplete");
        onQueryComplete(val$queryToken, val$data, ((List) (obj)));
        class .Lambda._cls0
            implements Runnable
        {

            private final EventRangedQueryHandler arg$1;

            public final void run()
            {
                arg$1.deQueue();
            }

            .Lambda._cls0(EventRangedQueryHandler eventrangedqueryhandler)
            {
                arg$1 = eventrangedqueryhandler;
            }
        }

        CalendarExecutor.MAIN.execute(new .Lambda._cls0(EventRangedQueryHandler.this));
        Trace.endSection();
        return;
        obj;
        class .Lambda._cls1
            implements Runnable
        {

            private final EventRangedQueryHandler arg$1;

            public final void run()
            {
                arg$1.deQueue();
            }

            .Lambda._cls1(EventRangedQueryHandler eventrangedqueryhandler)
            {
                arg$1 = eventrangedqueryhandler;
            }
        }

        CalendarExecutor.MAIN.execute(new .Lambda._cls1(EventRangedQueryHandler.this));
        Trace.endSection();
        throw obj;
    }

    .Lambda._cls1()
    {
        this$0 = final_eventrangedqueryhandler;
        val$queryToken = i;
        val$data = RangedData.this;
        super();
    }
}
