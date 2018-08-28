// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.accounts.Account;
import android.content.Context;
import com.google.android.calendar.executors.QueryExecutor;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.gms.reminders.model.RemindersBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

// Referenced classes of package com.google.android.calendar.task:
//            TaskAccount, TaskUtils, TaskConnection

final class ArpTaskAccount
    implements TaskAccount, TaskConnection.TasksLoadListener
{

    public final Account account;
    private int accountColor;
    public HashMap backgroundTimeLineTasks;
    public final Context context;
    public volatile TaskAccount.Tasks emptyTasks;
    private long endMillis;
    public boolean isVisible;
    public final LatencyLogger latencyLogger = LatencyLoggerHolder.get();
    public final TaskAccount.TaskAccountListener listener;
    public final Queue loadTagQueue = new ConcurrentLinkedQueue();
    public boolean shouldProfile;
    private long startMillis;
    private final TaskConnection taskConnection;
    public List taskMonthDataList;
    public volatile TaskAccount.Tasks tasks;

    ArpTaskAccount(Context context1, Account account1, TaskConnection taskconnection, TaskAccount.TaskAccountListener taskaccountlistener)
    {
        accountColor = -1;
        context = context1;
        account = account1;
        taskConnection = taskconnection;
        listener = taskaccountlistener;
    }

    public final String getAccountName()
    {
        return account.name;
    }

    public final TaskAccount.Tasks getTasks()
    {
        if (isVisible)
        {
            return tasks;
        } else
        {
            return emptyTasks;
        }
    }

    public final void loadTasks(long l, long l1, List list, boolean flag, boolean flag1, 
            int i)
    {
        boolean flag2;
        flag2 = isVisible;
        isVisible = flag;
        if (!shouldProfile) goto _L2; else goto _L1
_L1:
        latencyLogger.markAt(35);
_L6:
        startMillis = l;
        endMillis = l1;
        accountColor = TaskUtils.getTaskCalendarColor(account.name);
        taskMonthDataList = list;
        loadTagQueue.add(Integer.valueOf(i));
        taskConnection.loadTasks(context, account.name, l, l1, this);
_L4:
        return;
_L2:
        if (shouldLoad(l, l1, flag, flag1))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (flag2 != isVisible)
        {
            if (isVisible)
            {
                list = tasks;
            } else
            {
                list = emptyTasks;
            }
            if (list != null)
            {
                (new AsyncNotifyListener()).execute(new Void[0]);
                return;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void onTasksLoaded(RemindersBuffer remindersbuffer)
    {
        if (shouldProfile)
        {
            latencyLogger.markAt(36);
        }
        QueryExecutor.scheduleOnExecutor(new AsyncProcessLoadedTasks(taskMonthDataList), new RemindersBuffer[] {
            remindersbuffer
        });
    }

    public final boolean shouldLoad(long l, long l1, boolean flag, boolean flag1)
    {
        int i = TaskUtils.getTaskCalendarColor(account.name);
        return flag && (l != startMillis || l1 != endMillis || i != accountColor || flag1);
    }

    private class AsyncNotifyListener extends AsyncTask
    {

        private final ArpTaskAccount this$0;

        protected final Object doInBackground(Object aobj[])
        {
            listener.onTaskAccountLoaded(ArpTaskAccount.this, null);
            return null;
        }

        AsyncNotifyListener()
        {
            this$0 = ArpTaskAccount.this;
            super();
        }
    }


    private class AsyncProcessLoadedTasks extends AsyncProcessTasks
    {
        private class AsyncProcessTasks extends AsyncTask
        {

            private TaskAccount.Tasks backgroundEmptyTasks;
            private final List backgroundTaskMonthDataList;
            public TaskAccount.Tasks backgroundTasks;
            private final ArpTaskAccount this$0;

            final void addTaskToStorage(TimelineTask timelinetask)
            {
                int i = timelinetask.timeRange.getStartDay();
                Object obj = backgroundTasks.monthsTasksMap.floorEntry(Integer.valueOf(i));
                if (obj != null)
                {
                    obj = (com.google.android.calendar.timely.MonthData.TaskResults)((java.util.Map.Entry) (obj)).getValue();
                    boolean flag;
                    if (i >= ((com.google.android.calendar.timely.MonthData.TaskResults) (obj)).startDay && i <= ((com.google.android.calendar.timely.MonthData.TaskResults) (obj)).endDay)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        ((com.google.android.calendar.timely.MonthData.TaskResults) (obj)).addTimelineItem(timelinetask);
                    }
                }
            }

            final void finalizeStorage()
            {
                BundleTaskResults bundletaskresults;
                for (Iterator iterator = backgroundTasks.monthsTasksMap.values().iterator(); iterator.hasNext(); backgroundTasks.tasksList.addAll(bundletaskresults.tasks))
                {
                    bundletaskresults = (BundleTaskResults)(com.google.android.calendar.timely.MonthData.TaskResults)iterator.next();
                    bundletaskresults.finalizeStorage();
                }

            }

            final void finishProcessingTasks(boolean flag)
            {
                tasks = backgroundTasks;
                emptyTasks = backgroundEmptyTasks;
                TaskAccount.TaskAccountListener taskaccountlistener = listener;
                ArpTaskAccount arptaskaccount = ArpTaskAccount.this;
                Integer integer;
                if (flag)
                {
                    integer = (Integer)loadTagQueue.remove();
                } else
                {
                    integer = null;
                }
                taskaccountlistener.onTaskAccountLoaded(arptaskaccount, integer);
            }

            final void initializeStorage()
            {
                int i;
                int j;
                for (Iterator iterator = backgroundTaskMonthDataList.iterator(); iterator.hasNext(); backgroundEmptyTasks.monthsTasksMap.put(Integer.valueOf(i), new BundleTaskResults(i, j)))
                {
                    ArpTaskDataManager.TaskMonthData taskmonthdata = (ArpTaskDataManager.TaskMonthData)iterator.next();
                    i = taskmonthdata.startJulianDay;
                    j = taskmonthdata.startJulianDay;
                    j = (taskmonthdata.numDays + j) - 1;
                    backgroundTasks.monthsTasksMap.put(Integer.valueOf(i), new BundleTaskResults(i, j));
                }

            }

            AsyncProcessTasks(List list)
            {
                this$0 = ArpTaskAccount.this;
                super();
                backgroundTasks = new TaskAccount.Tasks();
                backgroundEmptyTasks = new TaskAccount.Tasks();
                backgroundTaskMonthDataList = list;
            }
        }


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
            finishProcessingTasks(true);
            Trace.endSection();
            return null;
            aremindersbuffer = aremindersbuffer[0];
            if (shouldProfile)
            {
                latencyLogger.markAt(38);
            }
            initializeStorage();
            backgroundTimeLineTasks = new HashMap();
            if (shouldProfile)
            {
                latencyLogger.markAt(39);
            }
            TimelineTask timelinetask;
            for (ArpRemindersBufferIterator arpremindersbufferiterator = new ArpRemindersBufferIterator(context, aremindersbuffer, account.name); arpremindersbufferiterator.hasNext(); backgroundTimeLineTasks.put((String)timelinetask.id, timelinetask))
            {
                timelinetask = (TimelineTask)arpremindersbufferiterator.next();
                addTaskToStorage(timelinetask);
            }

            break MISSING_BLOCK_LABEL_169;
            aremindersbuffer;
            Trace.endSection();
            throw aremindersbuffer;
            if (shouldProfile)
            {
                latencyLogger.markAt(40);
            }
            finalizeStorage();
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
            j = backgroundTasks.tasksList.size();
            if (((AbstractDataBuffer) (aremindersbuffer)).zzaKT != null)
            {
                ((AbstractDataBuffer) (aremindersbuffer)).zzaKT.close();
            }
            flag = shouldProfile;
            finishProcessingTasks(true);
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

        AsyncProcessLoadedTasks(List list)
        {
            this$0 = ArpTaskAccount.this;
            super(list);
        }
    }

}
