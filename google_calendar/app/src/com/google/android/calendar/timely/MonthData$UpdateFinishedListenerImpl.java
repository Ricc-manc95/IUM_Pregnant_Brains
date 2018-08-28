// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthData

final class viewUpdateFinished
    implements viewUpdateFinished
{

    private AtomicInteger counter;
    private final MonthData this$0;

    private final void viewUpdateFinished()
    {
        if (eventsReady && tasksReady && viewUpdatePerformedListener != null)
        {
            viewUpdatePerformedListener.postOnViewUpdatePerformed();
        }
    }

    public final void notifyUpdateFinished()
    {
        if (counter.decrementAndGet() == 0)
        {
            viewUpdateFinished();
        }
    }

    r(int i)
    {
        this$0 = MonthData.this;
        super();
        counter = new AtomicInteger(i);
        if (i == 0)
        {
            viewUpdateFinished();
        }
    }
}
