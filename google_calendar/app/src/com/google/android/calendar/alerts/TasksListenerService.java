// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.common.CalendarFeatureConfigDelegate;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.utils.collection.Iterables2;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.RemindersListenerService;
import com.google.android.gms.reminders.model.ReminderEvent;
import com.google.android.gms.reminders.model.ReminderEventBuffer;
import com.google.android.gms.reminders.model.ReminderEventEntity;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.alerts:
//            TaskAlertsManager, TasksBroadcastReceiver, TasksListenerHelper

public class TasksListenerService extends RemindersListenerService
{

    private static final boolean DEBUG;
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alerts/TasksListenerService);
    private TaskAlertsManager taskAlertsManager;

    public TasksListenerService()
    {
        if (DEBUG)
        {
            LogUtils.d(TAG, "  TasksListenerService", new Object[0]);
        }
    }

    public void onCreate()
    {
        super.onCreate();
        if (DEBUG)
        {
            LogUtils.d(TAG, "onCreate", new Object[0]);
        }
        taskAlertsManager = new TaskAlertsManager(this);
    }

    protected final void onReminderFired(ReminderEvent reminderevent)
    {
        if (DEBUG)
        {
            LogUtils.d(TAG, "onReminderFired", new Object[0]);
        }
        com.google.android.gms.reminders.model.Task task = reminderevent.getTask();
        if (task == null)
        {
            return;
        }
        if (CalendarFeatureConfigDelegate.useJobs == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (CalendarFeatureConfigDelegate.useJobs.booleanValue())
        {
            int i = reminderevent.getType();
            String s;
            boolean flag;
            if (i == 2)
            {
                s = "deleted";
            } else
            if (i == 1)
            {
                s = "changed";
            } else
            {
                s = "unknown";
            }
            reminderevent = reminderevent.getAccountName();
            flag = DEBUG;
            sendBroadcast((new Intent(this, com/google/android/calendar/alerts/TasksBroadcastReceiver)).setAction("com.google.android.calendar.alerts.ACTION_REMINDER_FIRED").putExtra("com.google.android.calendar.alerts.EXTRA_TASK", task).putExtra("com.google.android.calendar.alerts.EXTRA_EVENT_TYPE", s).putExtra("com.google.android.calendar.alerts.EXTRA_ACCOUNT_NAME", reminderevent).putExtra("com.google.android.calendar.alerts.EXTRA_IS_DEBUG", flag));
            return;
        }
        TaskAlertsManager taskalertsmanager = taskAlertsManager;
        int j = reminderevent.getType();
        String s1;
        if (j == 2)
        {
            s1 = "deleted";
        } else
        if (j == 1)
        {
            s1 = "changed";
        } else
        {
            s1 = "unknown";
        }
        TasksListenerHelper.handleReminderFired(this, taskalertsmanager, task, s1, reminderevent.getAccountName(), DEBUG);
    }

    protected final void onRemindersChanged(ReminderEventBuffer remindereventbuffer)
    {
        if (DEBUG)
        {
            LogUtils.d(TAG, "onRemindersChanged", new Object[0]);
        }
        boolean flag = Iterables2.isNullOrEmpty(remindereventbuffer);
        if (!flag) goto _L2; else goto _L1
_L1:
        if (((AbstractDataBuffer) (remindereventbuffer)).zzaKT != null)
        {
            ((AbstractDataBuffer) (remindereventbuffer)).zzaKT.close();
        }
_L7:
        return;
_L2:
        if (CalendarFeatureConfigDelegate.useJobs == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        break MISSING_BLOCK_LABEL_81;
        Exception exception;
        exception;
        if (((AbstractDataBuffer) (remindereventbuffer)).zzaKT != null)
        {
            ((AbstractDataBuffer) (remindereventbuffer)).zzaKT.close();
        }
        throw exception;
        if (!CalendarFeatureConfigDelegate.useJobs.booleanValue()) goto _L4; else goto _L3
_L3:
        final ArrayList reminderEvents;
        reminderEvents = new ArrayList();
        for (Iterator iterator = remindereventbuffer.iterator(); iterator.hasNext(); reminderEvents.add(new ReminderEventEntity((ReminderEvent)((ReminderEvent)iterator.next()).freeze()))) { }
        boolean flag1 = DEBUG;
        sendBroadcast((new Intent(this, com/google/android/calendar/alerts/TasksBroadcastReceiver)).setAction("com.google.android.calendar.alerts.ACTION_REMINDERS_CHANGED").putExtra("com.google.android.calendar.alerts.EXTRA_REMINDER_EVENTS", reminderEvents).putExtra("com.google.android.calendar.alerts.EXTRA_IS_DEBUG", flag1));
_L5:
        if (((AbstractDataBuffer) (remindereventbuffer)).zzaKT != null)
        {
            ((AbstractDataBuffer) (remindereventbuffer)).zzaKT.close();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        reminderEvents = new ArrayList();
        for (Iterator iterator1 = remindereventbuffer.iterator(); iterator1.hasNext(); reminderEvents.add(new ReminderEventEntity((ReminderEvent)((ReminderEvent)iterator1.next()).freeze()))) { }
        (new Handler(getMainLooper())).post(new _cls1());
        TasksListenerHelper.handleRemindersChanged$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFC5M6ASJKECNL8OBJDD0MOPBIEHPKQOBEC5JMASHR9HL62TJ15TQN8QBC5T66ISRK7DD2ILG_0(this, reminderEvents, DEBUG);
          goto _L5
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void onSnoozePresetChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFE9IMQQBECHIN4SPFDLNM8PBC5T9MSRRFF9IL0SJ5EDIN8GR8C5N6EPB48LR6ARJK89QMCPJ5E8TIILG_0()
    {
        if (DEBUG)
        {
            LogUtils.d(TAG, "onSnoozePresetChanged", new Object[0]);
        }
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        if (CalendarFeatureConfigDelegate.useJobs == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (!CalendarFeatureConfigDelegate.useJobs.booleanValue())
        {
            return super.onStartCommand(intent, i, j);
        } else
        {
            stopSelf(j);
            return 2;
        }
    }

    public ComponentName startService(Intent intent)
    {
        if (CalendarFeatureConfigDelegate.useJobs == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (!CalendarFeatureConfigDelegate.useJobs.booleanValue())
        {
            return super.startService(intent);
        } else
        {
            onHandleIntentInternal(intent);
            return null;
        }
    }

    static 
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            DEBUG = ((FeatureConfig)featureconfig).dogfood_features();
        }
    }

    private class _cls1
        implements Runnable
    {

        private final List val$reminderEvents;

        public final void run()
        {
            if (TaskDataFactory.instance == null)
            {
                TaskDataFactory.instance = new TaskDataFactory();
            }
            TaskDataFactory.instance.onTasksChanged(reminderEvents);
        }

        _cls1()
        {
            reminderEvents = list;
            super();
        }
    }

}
