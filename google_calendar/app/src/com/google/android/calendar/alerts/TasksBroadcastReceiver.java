// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;

public class TasksBroadcastReceiver extends BroadcastReceiver
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alerts/TasksBroadcastReceiver);

    public TasksBroadcastReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        if (intent == null || intent.getAction() == null)
        {
            LogUtils.d(TAG, "Received incorrect intent.", new Object[0]);
            return;
        }
        LogUtils.d(TAG, "onReceive, action: %s", new Object[] {
            intent.getAction()
        });
        final android.content.BroadcastReceiver.PendingResult result = goAsync();
        class .Lambda._cls0
            implements Runnable
        {

            private final TasksBroadcastReceiver arg$1;
            private final Intent arg$2;
            private final Context arg$3;

            public final void run()
            {
                Object obj;
                Object obj1;
                String s;
                byte byte0;
                obj = arg$1;
                obj1 = arg$2;
                obj = arg$3;
                s = ((Intent) (obj1)).getAction();
                byte0 = -1;
                s.hashCode();
                JVM INSTR lookupswitch 2: default 52
            //                           -1427604549: 86
            //                           386343836: 101;
                   goto _L1 _L2 _L3
_L1:
                break; /* Loop/switch isn't completed */
_L3:
                break MISSING_BLOCK_LABEL_101;
_L4:
                switch (byte0)
                {
                default:
                    throw new IllegalArgumentException("Invalid action.");

                case 0: // '\0'
                    ArrayList arraylist = ((Intent) (obj1)).getParcelableArrayListExtra("com.google.android.calendar.alerts.EXTRA_REMINDER_EVENTS");
                    boolean flag = ((Intent) (obj1)).getBooleanExtra("com.google.android.calendar.alerts.EXTRA_IS_DEBUG", false);
                    if (!Iterables2.isNullOrEmpty(arraylist))
                    {
                        obj1 = new ArrayList(arraylist);
                        new TaskAlertsManager(((Context) (obj)));
                        TasksListenerHelper.handleRemindersChanged$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFC5M6ASJKECNL8OBJDD0MOPBIEHPKQOBEC5JMASHR9HL62TJ15TQN8QBC5T66ISRK7DD2ILG_0(((Context) (obj)), ((java.util.List) (obj1)), flag);
                        if (TaskDataFactory.instance == null)
                        {
                            TaskDataFactory.instance = new TaskDataFactory();
                        }
                        TaskDataFactory.instance.onTasksChanged(((java.util.List) (obj1)));
                    }
                    return;

                case 1: // '\001'
                    Task task = (Task)((Intent) (obj1)).getParcelableExtra("com.google.android.calendar.alerts.EXTRA_TASK");
                    String s1 = ((Intent) (obj1)).getStringExtra("com.google.android.calendar.alerts.EXTRA_EVENT_TYPE");
                    String s2 = ((Intent) (obj1)).getStringExtra("com.google.android.calendar.alerts.EXTRA_ACCOUNT_NAME");
                    boolean flag1 = ((Intent) (obj1)).getBooleanExtra("com.google.android.calendar.alerts.EXTRA_IS_DEBUG", false);
                    TasksListenerHelper.handleReminderFired(((Context) (obj)), new TaskAlertsManager(((Context) (obj))), task, s1, s2, flag1);
                    return;
                }
_L2:
                if (s.equals("com.google.android.calendar.alerts.ACTION_REMINDERS_CHANGED"))
                {
                    byte0 = 0;
                }
                  goto _L4
                if (s.equals("com.google.android.calendar.alerts.ACTION_REMINDER_FIRED"))
                {
                    byte0 = 1;
                }
                  goto _L4
            }

            .Lambda._cls0(Intent intent, Context context)
            {
                arg$1 = TasksBroadcastReceiver.this;
                arg$2 = intent;
                arg$3 = context;
            }
        }

        context = (FluentFuture)CalendarExecutor.MAIN.submit(new .Lambda._cls0(intent, context));
        intent = new _cls1();
        result = CalendarExecutor.MAIN;
        if (intent == null)
        {
            throw new NullPointerException();
        } else
        {
            context.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(context, intent), result);
            return;
        }
    }


    private class _cls1
        implements FutureCallback
    {

        private final String val$action;
        private final android.content.BroadcastReceiver.PendingResult val$result;

        public final void onFailure(Throwable throwable)
        {
            result.finish();
            LogUtils.e(TasksBroadcastReceiver.TAG, throwable, "Failed to handle %s.", new Object[] {
                action
            });
        }

        public final void onSuccess(Object obj)
        {
            result.finish();
        }

        _cls1()
        {
            result = pendingresult;
            action = s;
            super();
        }
    }

}
