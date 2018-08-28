// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import com.google.android.calendar.timely.MonthData;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskDataManager

final class this._cls0
    implements Runnable
{

    private final ArpTaskDataManager this$0;

    public final void run()
    {
        synchronized (ArpTaskDataManager.this)
        {
            if (!tasksReady)
            {
                todayMonthData.setEventsReady();
                setTasksReady();
                logRevealReason("timeout");
            }
        }
        return;
        exception;
        arptaskdatamanager;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ()
    {
        this$0 = ArpTaskDataManager.this;
        super();
    }
}
