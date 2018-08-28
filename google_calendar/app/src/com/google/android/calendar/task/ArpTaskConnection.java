// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.gms.GmsFutures;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.LoadRemindersOptions;
import com.google.android.gms.reminders.Reminders;
import com.google.android.gms.reminders.RemindersApi;
import com.google.android.gms.reminders.UpdateRecurrenceOptions;
import com.google.android.gms.reminders.internal.ref.TaskRef;
import com.google.android.gms.reminders.model.Recurrence;
import com.google.android.gms.reminders.model.RecurrenceEnd;
import com.google.android.gms.reminders.model.RecurrenceInfo;
import com.google.android.gms.reminders.model.RemindersBuffer;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import com.google.android.gms.reminders.model.zzab;
import com.google.android.gms.reminders.model.zzah;
import com.google.android.gms.reminders.model.zzy;
import com.google.calendar.v2.client.service.api.time.DateTime;
import com.google.calendar.v2.client.service.api.time.Duration;
import com.google.calendar.v2.client.service.api.time.Period;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.task:
//            TaskConnection, ArpTaskDateTimeInCalendar

public final class ArpTaskConnection
    implements TaskConnection, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
{

    private static final ImmutableList VISIBLE_TASK_LISTS;
    private static final Random random = new Random();
    private volatile Map googleApiClientMap;
    public Future timeoutFuture;

    public ArpTaskConnection()
    {
        googleApiClientMap = new HashMap();
    }

    private static String generateStringId()
    {
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        return String.format(null, "%s_%d_%x", new Object[] {
            "CALENDAR", Long.valueOf(l), Integer.valueOf(Math.abs(random.nextInt()))
        });
    }

    private final GoogleApiClient getClient(Context context, String s)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj1;
        if (timeoutFuture != null)
        {
            timeoutFuture.cancel(false);
        }
        CalendarExecutor calendarexecutor = CalendarExecutor.DISK;
        class .Lambda._cls0
            implements Runnable
        {

            private final ArpTaskConnection arg$1;

            public final void run()
            {
                ArpTaskConnection arptaskconnection = arg$1;
                arptaskconnection.timeoutFuture = null;
                LogUtils.v("ArpTaskConnection", "Idle timeout - disconnecting", new Object[0]);
                arptaskconnection.disconnectAllClients();
            }

            .Lambda._cls0()
            {
                arg$1 = ArpTaskConnection.this;
            }
        }

        obj1 = new .Lambda._cls0();
        TimeUnit timeunit = TimeUnit.MILLISECONDS;
        timeoutFuture = calendarexecutor.getDelegate().schedule(((Runnable) (obj1)), 30000L, timeunit);
        obj1 = (GoogleApiClient)googleApiClientMap.get(s);
        Object obj;
        obj = obj1;
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_208;
        }
        obj = (new com.google.android.gms.common.api.GoogleApiClient.Builder(context)).addApi(Reminders.API);
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_130;
        }
        context = null;
_L1:
        obj.zzafe = context;
        if (this != null)
        {
            break MISSING_BLOCK_LABEL_144;
        }
        throw new NullPointerException(String.valueOf("Listener must not be null"));
        context;
        this;
        JVM INSTR monitorexit ;
        throw context;
        context = new Account(s, "com.google");
          goto _L1
        ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj)).zzaJl.add(this);
        if (this != null)
        {
            break MISSING_BLOCK_LABEL_170;
        }
        throw new NullPointerException(String.valueOf("Listener must not be null"));
        ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj)).zzaJm.add(this);
        obj = ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj)).build();
        googleApiClientMap.put(s, obj);
        LogUtils.v("ArpTaskConnection", "Creating new GoogleApiClient", new Object[0]);
        this;
        JVM INSTR monitorexit ;
        return ((GoogleApiClient) (obj));
    }

    private final GoogleApiClient getClientAndStartConnecting(Context context, String s)
    {
        context = getClient(context, s);
        if (!context.isConnected() && !context.isConnecting())
        {
            LogUtils.v("ArpTaskConnection", "Connecting to GoogleApiClient", new Object[0]);
            context.connect();
        }
        return context;
    }

    private static LoadRemindersOptions getLoadTaskOptions(long l, long l1)
    {
        int i = 0;
        com.google.android.gms.reminders.LoadRemindersOptions.Builder builder = new com.google.android.gms.reminders.LoadRemindersOptions.Builder();
        builder.zzcfO = Long.valueOf(l);
        builder.zzcfP = Long.valueOf(l1);
        builder.zzcfS = true;
        builder.zzcfT = 0;
        builder = builder.setLoadReminderType(new int[] {
            1, 0
        });
        ImmutableList immutablelist = (ImmutableList)VISIBLE_TASK_LISTS;
        int j = immutablelist.size();
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
        while (i < j) 
        {
            Object obj = immutablelist.get(i);
            i++;
            int k = ((Integer)obj).intValue();
            if (builder.zzcfN == null)
            {
                builder.zzcfN = new ArrayList();
            }
            builder.zzcfN.add(Integer.valueOf(k));
        }
        return builder.build();
    }

    private static Task getRecurrenceTemplate(Task task)
    {
        task = new com.google.android.gms.reminders.model.Task.Builder(task);
        if (false)
        {
            throw new NullPointerException();
        }
        task.zzcjl = null;
        if (false)
        {
            throw new NullPointerException();
        }
        task.zzcjc = null;
        if (false)
        {
            throw new NullPointerException();
        } else
        {
            task.zzcjr = null;
            task.zzcjn = null;
            return task.build();
        }
    }

    private final Long getReferenceTimeMillis(Context context, String s, Task task)
    {
        Object obj = null;
        if (task.getDueDate() == null)
        {
            LogUtils.e("ArpTaskConnection", "task.getDueDate null in getReferenceTimeMillis", new Object[0]);
            return null;
        }
        if (task.getRecurrenceInfo() == null)
        {
            LogUtils.e("ArpTaskConnection", "task.getRecurrenceInfo null in getReferenceTimeMillis", new Object[0]);
            return null;
        }
        DateTimeService datetimeservice = new DateTimeService(TimeZone.getDefault().getID());
        long l = ArpTaskDateTimeInCalendar.getDueTimeMillis(task.getDueDate(), datetimeservice);
        context = loadTasksGetBuffer((com.google.android.gms.reminders.RemindersApi.LoadRemindersResult)loadInstancesByIdStart(context, s, task.getRecurrenceInfo().getRecurrenceId(), Long.valueOf(l), null).await(), null);
        if (context != null)
        {
            int i;
            if (((AbstractDataBuffer) (context)).zzaKT == null)
            {
                i = 0;
            } else
            {
                i = ((AbstractDataBuffer) (context)).zzaKT.zzaNZ;
            }
            s = (Task)((Task)new TaskRef(((RemindersBuffer) (context)).zzaKT, i - 1)).freeze();
            if (((AbstractDataBuffer) (context)).zzaKT != null)
            {
                ((AbstractDataBuffer) (context)).zzaKT.close();
            }
            context = s.getDueDate();
            if (context != null)
            {
                context = new DateTimeImpl(ArpTaskDateTimeInCalendar.getDueTimeMillis(context, datetimeservice), datetimeservice.calendarTimeZone);
                if (context == null)
                {
                    context = obj;
                } else
                {
                    context = context.plusPeriod(new Period(0, 0, 0, 1, 0, 0, 0L)).withMillisOfDay(0);
                }
                return Long.valueOf(context.minusDuration(new Duration(1L)).getMillis());
            }
        }
        return Long.valueOf(l);
    }

    private static UpdateRecurrenceOptions getUpdateRecurrenceOptionsForAllFutureInstance(long l)
    {
        com.google.android.gms.reminders.UpdateRecurrenceOptions.Builder builder = new com.google.android.gms.reminders.UpdateRecurrenceOptions.Builder();
        builder.zzcgg = Integer.valueOf(1);
        builder.zzcgf = Long.valueOf(l);
        return new UpdateRecurrenceOptions(builder.zzcgg, null, builder.zzcgf);
    }

    private final PendingResult loadInstancesByIdStart(Context context, String s, String s1, Long long1, Long long2)
    {
        com.google.android.gms.reminders.LoadRemindersOptions.Builder builder = new com.google.android.gms.reminders.LoadRemindersOptions.Builder();
        builder.zzcfT = 0;
        if (builder.zzcfX == null)
        {
            builder.zzcfX = new ArrayList();
        }
        builder.zzcfX.add(s1);
        builder.zzcfS = true;
        s1 = builder.setLoadReminderType(new int[] {
            1
        });
        s1.zzbSV = 1;
        if (long1 != null)
        {
            s1.zzcfP = long1;
        }
        if (long2 != null)
        {
            s1.zzcfO = long2;
        }
        s1 = s1.build();
        return Reminders.RemindersApi.loadReminders(getClientAndStartConnecting(context, s), s1);
    }

    private static RemindersBuffer loadTasksGetBuffer(com.google.android.gms.reminders.RemindersApi.LoadRemindersResult loadremindersresult, Integer integer)
    {
label0:
        {
            boolean flag;
            if (loadremindersresult.getStatus().zzaEP <= 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                LogUtils.e("ArpTaskConnection", "loadTask failed", new Object[0]);
                return null;
            }
            loadremindersresult = loadremindersresult.getRemindersBuffer();
            if (loadremindersresult != null)
            {
                int i;
                if (((AbstractDataBuffer) (loadremindersresult)).zzaKT == null)
                {
                    i = 0;
                } else
                {
                    i = ((AbstractDataBuffer) (loadremindersresult)).zzaKT.zzaNZ;
                }
                if (i != 0 || integer != null && integer.intValue() == 0)
                {
                    break label0;
                }
            }
            if (loadremindersresult != null && ((AbstractDataBuffer) (loadremindersresult)).zzaKT != null)
            {
                ((AbstractDataBuffer) (loadremindersresult)).zzaKT.close();
            }
            LogUtils.e("ArpTaskConnection", "loadTask succeeded but no tasks loaded", new Object[0]);
            return null;
        }
        if (integer != null)
        {
            int j;
            if (((AbstractDataBuffer) (loadremindersresult)).zzaKT == null)
            {
                j = 0;
            } else
            {
                j = ((AbstractDataBuffer) (loadremindersresult)).zzaKT.zzaNZ;
            }
            if (j != integer.intValue())
            {
                LogUtils.e("ArpTaskConnection", "loadTask succeeded but the number of tasks different than expected", new Object[0]);
                return null;
            }
        }
        return loadremindersresult;
    }

    public final boolean createTask(Context context, String s, Task task)
    {
        Object obj = null;
        s = getClientAndBlockUntilConnected(context, s);
        if (task.getTaskId() != null)
        {
            context = task;
        } else
        {
            com.google.android.gms.reminders.model.Task.Builder builder = new com.google.android.gms.reminders.model.Task.Builder(task);
            context = new com.google.android.gms.reminders.model.TaskId.Builder();
            context.zzcjE = generateStringId();
            zzah zzah1 = new zzah(((com.google.android.gms.reminders.model.TaskId.Builder) (context)).zzcjE, null);
            context = obj;
            if (zzah1 != null)
            {
                context = (TaskId)zzah1.freeze();
            }
            builder.zzcjc = context;
            context = Integer.valueOf(7);
            boolean flag;
            if (context == null || context.intValue() == 1 || context.intValue() == 8 || context.intValue() == 10 || context.intValue() == 2 || context.intValue() == 3 || context.intValue() == 4 || context.intValue() == 14 || context.intValue() == 11 || context.intValue() == 5 || context.intValue() == 6 || context.intValue() == 13 || context.intValue() == 12 || context.intValue() == 7 || context.intValue() == 9)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("Invalid constant for SystemListId. Use value in ModelConstants"));
            }
            builder.zzcjd = context;
            context = builder.build();
        }
        task = task.getRecurrenceInfo();
        if (task == null) goto _L2; else goto _L1
_L1:
        if (((Status)Reminders.RemindersApi.createRecurrence(s, generateStringId(), task.getRecurrence(), getRecurrenceTemplate(context)).await()).zzaEP > 0) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (((Status)Reminders.RemindersApi.createReminder(s, context).await()).zzaEP > 0)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public final boolean deleteRecurrence(Context context, String s, Task task, int i)
    {
        GoogleApiClient googleapiclient = getClientAndBlockUntilConnected(context, s);
        RecurrenceInfo recurrenceinfo = task.getRecurrenceInfo();
        context = getReferenceTimeMillis(context, s, task);
        if (context == null)
        {
            LogUtils.e("ArpTaskConnection", "deleteRecurrence failed because failing to calculate referenceTimeMillis", new Object[0]);
        }
        if (i == 0)
        {
            context = UpdateRecurrenceOptions.ALL_INSTANCES_OPTION;
        } else
        {
            context = getUpdateRecurrenceOptionsForAllFutureInstance(context.longValue());
        }
        return ((Status)Reminders.RemindersApi.deleteRecurrence(googleapiclient, recurrenceinfo.getRecurrenceId(), context).await()).zzaEP <= 0;
    }

    public final boolean deleteTask(Context context, String s, Task task)
    {
        context = getClientAndBlockUntilConnected(context, s);
        return ((Status)Reminders.RemindersApi.deleteReminder(context, task.getTaskId()).await()).zzaEP <= 0;
    }

    final void disconnectAllClients()
    {
        this;
        JVM INSTR monitorenter ;
        LogUtils.v("ArpTaskConnection", "disconnectAllClients called", new Object[0]);
        for (Iterator iterator = googleApiClientMap.values().iterator(); iterator.hasNext(); ((GoogleApiClient)iterator.next()).disconnect()) { }
        break MISSING_BLOCK_LABEL_59;
        Exception exception;
        exception;
        throw exception;
        googleApiClientMap.clear();
        this;
        JVM INSTR monitorexit ;
    }

    final GoogleApiClient getClientAndBlockUntilConnected(Context context, String s)
    {
        context = getClient(context, s);
        if (!context.isConnected())
        {
            LogUtils.v("ArpTaskConnection", "Blocking connect to GoogleApiClient", new Object[0]);
            context.blockingConnect();
        }
        return context;
    }

    public final Task loadTaskSynchronous(Context context, String s, String s1)
    {
        Object obj = null;
        s1 = loadTasksFinish((com.google.android.gms.reminders.RemindersApi.LoadRemindersResult)loadTasksByIdStart(context, s, new String[] {
            s1
        }).await(), 1);
        boolean flag;
        if (s1 == null)
        {
            s1 = null;
        } else
        {
            s1 = s1[0];
        }
        if (s1 != null && s1.getRecurrenceInfo() != null && !s1.getRecurrenceInfo().getMaster().booleanValue() && !s1.getRecurrenceInfo().getExceptional().booleanValue())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            context = ((Context) (obj));
            if (s1 != null)
            {
                context = new com.google.android.gms.reminders.model.Task.Builder(s1);
                if (false)
                {
                    throw new NullPointerException();
                }
                context.zzcjr = null;
                context = context.build();
            }
            return context;
        }
        obj = s1.getRecurrenceInfo().getRecurrenceId();
        com.google.android.gms.reminders.LoadRemindersOptions.Builder builder = new com.google.android.gms.reminders.LoadRemindersOptions.Builder();
        builder.zzcfT = 1;
        if (builder.zzcfX == null)
        {
            builder.zzcfX = new ArrayList();
        }
        builder.zzcfX.add(obj);
        obj = builder.build();
        context = loadTasksFinish((com.google.android.gms.reminders.RemindersApi.LoadRemindersResult)Reminders.RemindersApi.loadReminders(getClientAndStartConnecting(context, s), ((LoadRemindersOptions) (obj))).await(), 1);
        if (context == null)
        {
            context = null;
        } else
        {
            context = context[0];
        }
        s = new com.google.android.gms.reminders.model.Task.Builder(s1);
        if (context == null)
        {
            context = null;
        } else
        {
            context = new com.google.android.gms.reminders.model.RecurrenceInfo.Builder(context.getRecurrenceInfo());
            context.zzciW = Boolean.valueOf(false);
            context = new zzab(((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (context)).zzciU, ((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (context)).zzciV, ((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (context)).zzciW, ((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (context)).zzciX, true);
        }
        if (context != null)
        {
            context = (RecurrenceInfo)context.freeze();
        } else
        {
            context = null;
        }
        s.zzcjr = context;
        return s.build();
    }

    public final void loadTasks(Context context, String s, long l, long l1, final TaskConnection.TasksLoadListener listener)
    {
        context = loadTasksAsync(context, s, l, l1);
        s = new _cls1();
        listener = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            context.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(context, s), listener);
            return;
        }
    }

    public final FluentFuture loadTasksAsync(Context context, String s, long l, long l1)
    {
        LoadRemindersOptions loadremindersoptions = getLoadTaskOptions(l, l1);
        context = Reminders.RemindersApi.loadReminders(getClientAndStartConnecting(context, s), loadremindersoptions);
        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj)
            {
                return ((com.google.android.gms.reminders.RemindersApi.LoadRemindersResult)obj).getRemindersBuffer();
            }


            private .Lambda._cls1()
            {
            }
        }

        s = .Lambda._cls1..instance;
        context = AbstractTransformFuture.create(GmsFutures.asFuture(context), new com.google.android.apps.calendar.util.gms.GmsFutures..Lambda._cls0(s), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        if (context instanceof FluentFuture)
        {
            return (FluentFuture)context;
        } else
        {
            return new ForwardingFluentFuture(context);
        }
    }

    final transient PendingResult loadTasksByIdStart(Context context, String s, String as[])
    {
        ArrayList arraylist = new ArrayList();
        int k = as.length;
        for (int i = 0; i < k; i++)
        {
            String s1 = as[i];
            com.google.android.gms.reminders.model.TaskId.Builder builder = new com.google.android.gms.reminders.model.TaskId.Builder();
            builder.zzcjE = s1;
            arraylist.add(new zzah(builder.zzcjE, null));
        }

        as = new com.google.android.gms.reminders.LoadRemindersOptions.Builder();
        TaskId ataskid[] = (TaskId[])arraylist.toArray(new TaskId[arraylist.size()]);
        as.zzcga = ataskid;
        int l = ataskid.length;
        boolean flag;
        for (int j = 0; j < l; j++)
        {
            TaskId taskid = ataskid[j];
            if (taskid == null)
            {
                throw new NullPointerException(String.valueOf("Cannot pass in null taskId"));
            }
            if (!TextUtils.isEmpty(taskid.getClientAssignedId()))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("Cannot pass in empty client assigned id"));
            }
        }

        as = as.build();
        return Reminders.RemindersApi.loadReminders(getClientAndStartConnecting(context, s), as);
    }

    final Task[] loadTasksFinish(com.google.android.gms.reminders.RemindersApi.LoadRemindersResult loadremindersresult, int i)
    {
        loadremindersresult = loadTasksGetBuffer(loadremindersresult, Integer.valueOf(i));
        if (loadremindersresult == null)
        {
            return null;
        }
        Task atask[] = new Task[i];
        for (int j = 0; j < i; j++)
        {
            atask[j] = (Task)((Task)new TaskRef(((RemindersBuffer) (loadremindersresult)).zzaKT, j)).freeze();
        }

        if (((AbstractDataBuffer) (loadremindersresult)).zzaKT != null)
        {
            ((AbstractDataBuffer) (loadremindersresult)).zzaKT.close();
        }
        return atask;
    }

    public final RemindersBuffer loadTasksSynchronous(Context context, String s, long l, long l1)
    {
        LoadRemindersOptions loadremindersoptions = getLoadTaskOptions(l, l1);
        context = (com.google.android.gms.reminders.RemindersApi.LoadRemindersResult)Reminders.RemindersApi.loadReminders(getClientAndStartConnecting(context, s), loadremindersoptions).await();
        boolean flag;
        if (context.getStatus().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            LogUtils.e("ArpTaskConnection", "loadTask failed", new Object[0]);
            return null;
        } else
        {
            return context.getRemindersBuffer();
        }
    }

    public final void onConnected(Bundle bundle)
    {
        LogUtils.v("ArpTaskConnection", "GoogleApiClient connected", new Object[0]);
    }

    public final void onConnectionFailed(ConnectionResult connectionresult)
    {
        LogUtils.e("ArpTaskConnection", "GoogleApiClient connection failed: %s", new Object[] {
            connectionresult
        });
    }

    public final void onConnectionSuspended(int i)
    {
        LogUtils.w("ArpTaskConnection", "GoogleApiClient connection suspended: %d", new Object[] {
            Integer.valueOf(i)
        });
    }

    public final boolean updateRecurrence(Context context, String s, Task task, Task task1, int i)
    {
        Object obj;
        GoogleApiClient googleapiclient = getClientAndBlockUntilConnected(context, s);
        RecurrenceInfo recurrenceinfo = task.getRecurrenceInfo();
        Object obj1 = getReferenceTimeMillis(context, s, task);
        if (obj1 == null)
        {
            LogUtils.e("ArpTaskConnection", "UpdateRecurrence failed because failing to calculate referenceTimeMillis", new Object[0]);
        }
        obj = getUpdateRecurrenceOptionsForAllFutureInstance(((Long) (obj1)).longValue());
        if (task1.getRecurrenceInfo() == null)
        {
            obj = new com.google.android.gms.reminders.model.Task.Builder(task1);
            task1 = new com.google.android.gms.reminders.model.TaskId.Builder();
            task1.zzcjE = generateStringId();
            task1 = new zzah(((com.google.android.gms.reminders.model.TaskId.Builder) (task1)).zzcjE, null);
            if (task1 != null)
            {
                task1 = (TaskId)task1.freeze();
            } else
            {
                task1 = null;
            }
            obj.zzcjc = task1;
            task1 = ((com.google.android.gms.reminders.model.Task.Builder) (obj)).build();
            if (!deleteRecurrence(context, s, task, 1))
            {
                return false;
            } else
            {
                return createTask(context, s, task1);
            }
        }
        boolean flag;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        if (((Long) (obj1)).longValue() < l)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        task = recurrenceinfo.getRecurrence();
        obj1 = task1.getRecurrenceInfo().getRecurrence();
        if (task == obj1 || task != null && task.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && i != 0)
        {
            return ((Status)Reminders.RemindersApi.updateRecurrence(googleapiclient, recurrenceinfo.getRecurrenceId(), getRecurrenceTemplate(task1), ((UpdateRecurrenceOptions) (obj))).await()).zzaEP <= 0;
        }
        task = ((Task) (obj));
        if (i != 0)
        {
            task = getUpdateRecurrenceOptionsForAllFutureInstance(l);
        }
        obj = task1.getRecurrenceInfo().getRecurrence();
        obj1 = recurrenceinfo.getRecurrence();
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0 || ((Recurrence) (obj)).getRecurrenceEnd() == null || ((Recurrence) (obj)).getRecurrenceEnd().getNumOccurrences() == null)
        {
            break MISSING_BLOCK_LABEL_766;
        }
        if (task1.getDueDate() == null)
        {
            LogUtils.e("ArpTaskConnection", "task.getDueDate null in getRecurrenceEndDateTime", new Object[0]);
        } else
        {
label0:
            {
                if (task1.getRecurrenceInfo() != null)
                {
                    break label0;
                }
                LogUtils.e("ArpTaskConnection", "task.getRecurrenceInfo null in getRecurrenceEndDateTime", new Object[0]);
            }
        }
_L2:
        context = null;
_L3:
        if (context == null)
        {
            LogUtils.e("ArpTaskConnection", "updateRecurrence failed because failing to calculate recurrenceEndDate", new Object[0]);
        }
        s = new com.google.android.gms.reminders.model.Recurrence.Builder(((Recurrence) (obj)));
        obj = new com.google.android.gms.reminders.model.RecurrenceEnd.Builder(((Recurrence) (obj)).getRecurrenceEnd());
        obj.zzciJ = null;
        DateTimeService datetimeservice;
        long l1;
        if (context != null)
        {
            context = (com.google.android.gms.reminders.model.DateTime)context.freeze();
        } else
        {
            context = null;
        }
        obj.zzciI = context;
        context = new zzy(((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (obj)).zzciI, ((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (obj)).zzciJ, ((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (obj)).zzciK, ((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (obj)).zzciL, true);
        if (context != null)
        {
            context = (RecurrenceEnd)context.freeze();
        } else
        {
            context = null;
        }
        s.zzciD = context;
        context = s.build();
_L4:
        return ((Status)Reminders.RemindersApi.changeRecurrence(googleapiclient, recurrenceinfo.getRecurrenceId(), generateStringId(), context, getRecurrenceTemplate(task1), task).await()).zzaEP <= 0;
        datetimeservice = new DateTimeService(context);
        l1 = ArpTaskDateTimeInCalendar.getDueTimeMillis(task1.getDueDate(), datetimeservice);
        context = loadTasksGetBuffer((com.google.android.gms.reminders.RemindersApi.LoadRemindersResult)loadInstancesByIdStart(context, s, task1.getRecurrenceInfo().getRecurrenceId(), null, Long.valueOf(l1)).await(), null);
        if (context == null) goto _L2; else goto _L1
_L1:
        if (((AbstractDataBuffer) (context)).zzaKT == null)
        {
            i = 0;
        } else
        {
            i = ((AbstractDataBuffer) (context)).zzaKT.zzaNZ;
        }
        s = (Task)((Task)new TaskRef(((RemindersBuffer) (context)).zzaKT, i - 1)).freeze();
        if (((AbstractDataBuffer) (context)).zzaKT != null)
        {
            ((AbstractDataBuffer) (context)).zzaKT.close();
        }
        context = s.getDueDate();
          goto _L3
        context = ((Context) (obj));
          goto _L4
    }

    public final boolean updateTask(Context context, String s, Task task, Task task1)
    {
        GoogleApiClient googleapiclient = getClientAndBlockUntilConnected(context, s);
        RecurrenceInfo recurrenceinfo = task1.getRecurrenceInfo();
        if (task.getRecurrenceInfo() == null && recurrenceinfo != null)
        {
            Task task2 = loadTasksFinish((com.google.android.gms.reminders.RemindersApi.LoadRemindersResult)loadTasksByIdStart(context, s, new String[] {
                task.getTaskId().getClientAssignedId()
            }).await(), 1);
            boolean flag;
            if (task2 == null)
            {
                task2 = null;
            } else
            {
                task2 = task2[0];
            }
            if (task2 != null && task2.getRecurrenceInfo() != null && !task2.getRecurrenceInfo().getMaster().booleanValue() && task2.getRecurrenceInfo().getExceptional().booleanValue())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if (!deleteTask(context, s, task2))
                {
                    return false;
                } else
                {
                    return createTask(context, s, task1);
                }
            }
            return ((Status)Reminders.RemindersApi.makeTaskRecurring(googleapiclient, task.getTaskId(), generateStringId(), recurrenceinfo.getRecurrence(), getRecurrenceTemplate(task1)).await()).zzaEP <= 0;
        }
        return ((Status)Reminders.RemindersApi.updateReminder(googleapiclient, task1).await()).zzaEP <= 0;
    }

    public final FluentFuture updateTasksDoneStatus(Context context, String s, boolean flag, Set set)
    {
        class .Lambda._cls2
            implements Callable
        {

            private final ArpTaskConnection arg$1;
            private final Context arg$2;
            private final String arg$3;
            private final Set arg$4;
            private final boolean arg$5;

            public final Object call()
            {
                Object obj3 = arg$1;
                Context context1 = arg$2;
                String s1 = arg$3;
                Object obj2 = arg$4;
                boolean flag3 = arg$5;
                GoogleApiClient googleapiclient = ((ArpTaskConnection) (obj3)).getClientAndBlockUntilConnected(context1, s1);
                Object aobj[] = (Object[])Array.newInstance(java/lang/String, 0);
                Object obj;
                boolean flag1;
                if (obj2 instanceof Collection)
                {
                    obj = (Collection)obj2;
                } else
                {
                    Iterator iterator = ((Iterable) (obj2)).iterator();
                    obj = new ArrayList();
                    Iterators.addAll(((Collection) (obj)), iterator);
                }
                aobj = ((ArpTaskConnection) (obj3)).loadTasksFinish((com.google.android.gms.reminders.RemindersApi.LoadRemindersResult)((ArpTaskConnection) (obj3)).loadTasksByIdStart(context1, s1, (String[])((Collection) (obj)).toArray(aobj)).await(), ((Set) (obj2)).size());
                if (aobj != null)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    throw new VerifyException();
                }
                obj2 = new ArrayList();
                obj3 = new ArrayList();
                int j = aobj.length;
                int i = 0;
                while (i < j) 
                {
                    Object obj1 = aobj[i];
                    com.google.android.gms.reminders.model.Task.Builder builder = new com.google.android.gms.reminders.model.Task.Builder(((Task) (obj1)));
                    if (flag3)
                    {
                        builder.zzcji = Boolean.valueOf(false);
                        builder.zzcjj = Boolean.valueOf(false);
                        builder.zzcjg = Boolean.valueOf(true);
                        long l;
                        if (Clock.mockedTimestamp > 0L)
                        {
                            l = Clock.mockedTimestamp;
                        } else
                        {
                            l = System.currentTimeMillis();
                        }
                        builder.zzcjf = Long.valueOf(l);
                        obj1 = null;
                    } else
                    {
                        builder.zzcji = Boolean.valueOf(false);
                        builder.zzcjg = Boolean.valueOf(false);
                        DateTimeService datetimeservice = new DateTimeService(context1);
                        boolean flag2;
                        if (((Task) (obj1)).getRecurrenceInfo() != null && ((Task) (obj1)).getDueDate() != null)
                        {
                            obj1 = Long.valueOf(ArpTaskDateTimeInCalendar.getDueTimeMillis(((Task) (obj1)).getDueDate(), datetimeservice));
                            long l2 = ((Long) (obj1)).longValue();
                            long l1;
                            if (Clock.mockedTimestamp > 0L)
                            {
                                l1 = Clock.mockedTimestamp;
                            } else
                            {
                                l1 = System.currentTimeMillis();
                            }
                            if (l2 > l1)
                            {
                                flag2 = true;
                            } else
                            {
                                flag2 = false;
                            }
                        } else
                        {
                            flag2 = false;
                            obj1 = null;
                        }
                        if (flag2)
                        {
                            builder.zzcjj = Boolean.valueOf(true);
                        } else
                        {
                            builder.zzcjj = Boolean.valueOf(false);
                            if (false)
                            {
                                throw new NullPointerException();
                            }
                            builder.zzcjl = null;
                            if (Clock.mockedTimestamp > 0L)
                            {
                                l1 = Clock.mockedTimestamp;
                            } else
                            {
                                l1 = System.currentTimeMillis();
                            }
                            obj1 = Long.valueOf(l1);
                        }
                    }
                    ((List) (obj2)).add(builder.build());
                    ((List) (obj3)).add(obj1);
                    i++;
                }
                if (((Status)Reminders.RemindersApi.batchUpdateReminder(googleapiclient, ((List) (obj2))).await()).zzaEP <= 0)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (!flag2)
                {
                    throw new VerifyException();
                }
                obj1 = (Long)((List) (obj3)).get(0);
                if (obj1 == null)
                {
                    return Absent.INSTANCE;
                } else
                {
                    return new Present(obj1);
                }
            }

            .Lambda._cls2(Context context, String s, Set set, boolean flag)
            {
                arg$1 = ArpTaskConnection.this;
                arg$2 = context;
                arg$3 = s;
                arg$4 = set;
                arg$5 = flag;
            }
        }

        return (FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls2(context, s, set, flag));
    }

    static 
    {
        com.google.common.collect.ImmutableList.Builder builder = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)(new com.google.common.collect.ImmutableList.Builder()).add(Integer.valueOf(16))).add(Integer.valueOf(1))).add(Integer.valueOf(8))).add(Integer.valueOf(10))).add(Integer.valueOf(2))).add(Integer.valueOf(3))).add(Integer.valueOf(15))).add(Integer.valueOf(4))).add(Integer.valueOf(11))).add(Integer.valueOf(5))).add(Integer.valueOf(6))).add(Integer.valueOf(13))).add(Integer.valueOf(12))).add(Integer.valueOf(7))).add(Integer.valueOf(9))).add(Integer.valueOf(17));
        builder.forceCopy = true;
        VISIBLE_TASK_LISTS = ImmutableList.asImmutableList(builder.contents, builder.size);
    }

    private class _cls1
        implements FutureCallback
    {

        private final TaskConnection.TasksLoadListener val$listener;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.e("ArpTaskConnection", "loadTasks failed", new Object[0]);
            listener.onTasksLoaded(null);
        }

        public final void onSuccess(Object obj)
        {
            obj = (RemindersBuffer)obj;
            listener.onTasksLoaded(((RemindersBuffer) (obj)));
        }

        _cls1()
        {
            listener = tasksloadlistener;
            super();
        }
    }

}
