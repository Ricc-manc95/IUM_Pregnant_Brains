// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.os.AsyncTask;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.timely.TimelineTask;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import java.util.Queue;

// Referenced classes of package com.google.android.calendar.task:
//            BundleTaskResults, ArpTaskAccount

abstract class backgroundTaskMonthDataList extends AsyncTask
{

    private backgroundTasks backgroundEmptyTasks;
    private final List backgroundTaskMonthDataList;
    public backgroundTasks backgroundTasks;
    private final ArpTaskAccount this$0;

    final void addTaskToStorage(TimelineTask timelinetask)
    {
        int i = timelinetask.timeRange.getStartDay();
        Object obj = backgroundTasks.backgroundTasks.floorEntry(Integer.valueOf(i));
        if (obj != null)
        {
            obj = (com.google.android.calendar.timely.ckgroundTasks)((java.util.Tasks) (obj)).Tasks();
            boolean flag;
            if (i >= ((com.google.android.calendar.timely.ckgroundTasks) (obj)).ckgroundTasks && i <= ((com.google.android.calendar.timely.ckgroundTasks) (obj)).ckgroundTasks)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                ((com.google.android.calendar.timely.ckgroundTasks) (obj)).Item(timelinetask);
            }
        }
    }

    final void finalizeStorage()
    {
        BundleTaskResults bundletaskresults;
        for (Iterator iterator = backgroundTasks.backgroundTasks.values().iterator(); iterator.hasNext(); backgroundTasks.backgroundTasks.addAll(bundletaskresults.tasks))
        {
            bundletaskresults = (BundleTaskResults)(com.google.android.calendar.timely.ckgroundTasks)iterator.next();
            bundletaskresults.finalizeStorage();
        }

    }

    final void finishProcessingTasks(boolean flag)
    {
        tasks = backgroundTasks;
        emptyTasks = backgroundEmptyTasks;
          = listener;
        ArpTaskAccount arptaskaccount = ArpTaskAccount.this;
        Integer integer;
        if (flag)
        {
            integer = (Integer)loadTagQueue.remove();
        } else
        {
            integer = null;
        }
        .nTaskAccountLoaded(arptaskaccount, integer);
    }

    final void initializeStorage()
    {
        int i;
        int j;
        for (Iterator iterator = backgroundTaskMonthDataList.iterator(); iterator.hasNext(); backgroundEmptyTasks.backgroundEmptyTasks.put(Integer.valueOf(i), new BundleTaskResults(i, j)))
        {
            nTaskAccountLoaded ntaskaccountloaded = (backgroundEmptyTasks)iterator.next();
            i = ntaskaccountloaded.startJulianDay;
            j = ntaskaccountloaded.startJulianDay;
            j = (ntaskaccountloaded.numDays + j) - 1;
            backgroundTasks.backgroundTasks.put(Integer.valueOf(i), new BundleTaskResults(i, j));
        }

    }

    (List list)
    {
        this$0 = ArpTaskAccount.this;
        super();
        backgroundTasks = new backgroundTasks();
        backgroundEmptyTasks = new backgroundEmptyTasks();
        backgroundTaskMonthDataList = list;
    }
}
