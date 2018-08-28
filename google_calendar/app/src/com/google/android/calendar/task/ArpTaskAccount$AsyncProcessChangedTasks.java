// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.accounts.Account;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.gms.reminders.model.ReminderEvent;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskAccount, TaskUtils, ArpLoadTester

final class this._cls0 extends this._cls0
{

    private final ArpTaskAccount this$0;

    private final transient Void doInBackground(List alist[])
    {
        ArpTaskAccount arptaskaccount = ArpTaskAccount.this;
        arptaskaccount;
        JVM INSTR monitorenter ;
        if (alist != null && alist[0] != null) goto _L2; else goto _L1
_L1:
        arptaskaccount;
        JVM INSTR monitorexit ;
        return null;
_L2:
        Object obj = alist[0];
        if (((List) (obj)).size() == 0 || backgroundTimeLineTasks == null) goto _L1; else goto _L3
_L3:
        DateTimeService datetimeservice;
        int i;
        if (shouldProfile)
        {
            latencyLogger.markAt(38);
        }
        izeStorage();
        if (shouldProfile)
        {
            latencyLogger.markAt(39);
        }
        alist = account.name;
        datetimeservice = new DateTimeService(context);
        i = TaskUtils.getTaskCalendarColor(alist);
        if (Clock.mockedTimestamp <= 0L) goto _L5; else goto _L4
_L4:
        long l = Clock.mockedTimestamp;
_L8:
        Time time = DateTimeService.toDateTimeImpl((new DateTimeImpl(l, datetimeservice.calendarTimeZone)).withTime(0, 0, 0)).time;
        time.writeFieldsToImpl();
        l = time.impl.toMillis(false);
        obj = ((List) (obj)).iterator();
_L7:
        ReminderEvent reminderevent;
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break MISSING_BLOCK_LABEL_354;
            }
            reminderevent = (ReminderEvent)((Iterator) (obj)).next();
        } while (alist == null || reminderevent == null);
        if (!alist.equals(reminderevent.getAccountName())) goto _L7; else goto _L6
_L6:
        String s;
        Task task;
        s = reminderevent.getTask().getTaskId().getClientAssignedId();
        task = reminderevent.getTask();
        if (reminderevent.getType() != 2 && TaskUtils.shouldTaskBeInCalendar(task))
        {
            break MISSING_BLOCK_LABEL_316;
        }
        backgroundTimeLineTasks.remove(s);
          goto _L7
        alist;
        arptaskaccount;
        JVM INSTR monitorexit ;
        throw alist;
_L5:
        l = System.currentTimeMillis();
          goto _L8
        TimelineTask timelinetask = new TimelineTask(TaskUtils.createTaskData(task, alist, i, datetimeservice, l));
        backgroundTimeLineTasks.put(s, timelinetask);
          goto _L7
        for (alist = backgroundTimeLineTasks.values().iterator(); alist.hasNext(); ToStorage((TimelineTask)alist.next())) { }
        boolean flag;
        if (shouldProfile)
        {
            latencyLogger.markAt(40);
        }
        eStorage();
        if (shouldProfile)
        {
            latencyLogger.markAt(41);
        }
        flag = shouldProfile;
        rocessingTasks(false);
        if (!flag) goto _L1; else goto _L9
_L9:
        ArpLoadTester.logLoadEnd(account.name, backgroundTimeLineTasks.size(), 0);
          goto _L1
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((List[])aobj);
    }

    (List list)
    {
        this$0 = ArpTaskAccount.this;
        super(ArpTaskAccount.this, list);
    }
}
