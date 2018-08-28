// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.accounts.Account;
import android.os.Trace;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.model.RemindersBuffer;
import java.util.HashMap;
import java.util.List;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskAccount, ArpRemindersBufferIterator, ArpLoadTester

final class  extends 
{

    private final ArpTaskAccount this$0;

    private final transient Void doInBackground(RemindersBuffer aremindersbuffer[])
    {
        synchronized (ArpTaskAccount.this)
        {
            aremindersbuffer = processLoadedTasks(aremindersbuffer);
        }
        return aremindersbuffer;
        aremindersbuffer;
        arptaskaccount;
        JVM INSTR monitorexit ;
        throw aremindersbuffer;
    }

    private final transient Void processLoadedTasks(RemindersBuffer aremindersbuffer[])
    {
        Trace.beginSection("ArpTaskAccount ProcessLoadedTasks");
        if (aremindersbuffer != null && aremindersbuffer[0] != null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        ProcessingTasks(true);
        Trace.endSection();
        return null;
        aremindersbuffer = aremindersbuffer[0];
        if (shouldProfile)
        {
            latencyLogger.markAt(38);
        }
        lizeStorage();
        backgroundTimeLineTasks = new HashMap();
        if (shouldProfile)
        {
            latencyLogger.markAt(39);
        }
        TimelineTask timelinetask;
        for (ArpRemindersBufferIterator arpremindersbufferiterator = new ArpRemindersBufferIterator(context, aremindersbuffer, account.name); arpremindersbufferiterator.hasNext(); backgroundTimeLineTasks.put((String)timelinetask.id, timelinetask))
        {
            timelinetask = (TimelineTask)arpremindersbufferiterator.next();
            kToStorage(timelinetask);
        }

        break MISSING_BLOCK_LABEL_169;
        aremindersbuffer;
        Trace.endSection();
        throw aremindersbuffer;
        if (shouldProfile)
        {
            latencyLogger.markAt(40);
        }
        zeStorage();
        if (shouldProfile)
        {
            latencyLogger.markAt(41);
        }
        if (((AbstractDataBuffer) (aremindersbuffer)).zzaKT != null)
        {
            break MISSING_BLOCK_LABEL_303;
        }
        int i = 0;
_L1:
        int j;
        boolean flag;
        j = backgroundTasks.backgroundTasks.size();
        if (((AbstractDataBuffer) (aremindersbuffer)).zzaKT != null)
        {
            ((AbstractDataBuffer) (aremindersbuffer)).zzaKT.close();
        }
        flag = shouldProfile;
        ProcessingTasks(true);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_298;
        }
        ArpLoadTester.logLoadEnd(account.name, i, i - j);
        Trace.endSection();
        return null;
        i = ((AbstractDataBuffer) (aremindersbuffer)).zzaKT.zzaNZ;
          goto _L1
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((RemindersBuffer[])aobj);
    }

    (List list)
    {
        this$0 = ArpTaskAccount.this;
        super(ArpTaskAccount.this, list);
    }
}
