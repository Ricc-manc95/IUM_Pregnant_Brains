// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitFactory;
import com.google.android.calendar.api.habit.HabitFilterOptions;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.RecordingApi;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.TimeoutFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.belong:
//            FitOperationServiceHelper, FitApiManager, FitHabitsApiManager, FitIntegrationManager, 
//            BelongUtils, FitIntegrationOperation

public class FitOperationReceiver extends WakefulBroadcastReceiver
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/belong/FitOperationReceiver);

    public FitOperationReceiver()
    {
    }

    static final void lambda$onReceive$0$FitOperationReceiver(Context context, String s, int i, EventKey eventkey)
    {
        byte byte0;
        context = new FitOperationServiceHelper(context);
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 3: default 52
    //                   -28480746: 113
    //                   1692950261: 98
    //                   2077537696: 128;
           goto _L1 _L2 _L3 _L4
_L1:
        byte0;
        JVM INSTR tableswitch 0 2: default 80
    //                   0 143
    //                   1 152
    //                   2 362;
           goto _L5 _L6 _L7 _L8
_L5:
        LogUtils.e(FitOperationServiceHelper.TAG, "Unknown actions: %s", new Object[] {
            s
        });
_L9:
        return;
_L3:
        if (s.equals("com.google.android.calendar.intent.action.FIT_ACTIVITY_CHECK"))
        {
            byte0 = 0;
        }
          goto _L1
_L2:
        if (s.equals("com.google.android.calendar.intent.action.FIT_SUBSCRIPTION_REFRESH"))
        {
            byte0 = 1;
        }
          goto _L1
_L4:
        if (s.equals("com.google.android.calendar.intent.action.FIT_DISABLE_INTEGRATION"))
        {
            byte0 = 2;
        }
          goto _L1
_L6:
        context.performActivityCheck(i, eventkey, true);
        return;
_L7:
        s = AccountsUtil.getGoogleAccounts(((FitOperationServiceHelper) (context)).context);
        int k = s.length;
        i = 0;
        while (i < k) 
        {
            eventkey = s[i];
            Context context1 = ((FitOperationServiceHelper) (context)).context;
            FitApiManager fitapimanager = new FitApiManager(context1, eventkey);
            FitHabitsApiManager fithabitsapimanager = new FitHabitsApiManager(context1, eventkey);
            long l1;
            if (Clock.mockedTimestamp > 0L)
            {
                l1 = Clock.mockedTimestamp;
            } else
            {
                l1 = System.currentTimeMillis();
            }
            eventkey = new FitIntegrationManager(context1, eventkey, fitapimanager, fithabitsapimanager, l1);
            if (!((FitIntegrationManager) (eventkey)).fitApiManager.connect())
            {
                LogUtils.e(FitIntegrationManager.TAG, "Failed to connect to GoogleApiClient", new Object[0]);
            } else
            if (((FitIntegrationManager) (eventkey)).habitApiManager.hasHabitsWithEnabledIntegration(((FitIntegrationManager) (eventkey)).timeMillis))
            {
                eventkey = ((FitIntegrationManager) (eventkey)).fitApiManager;
                boolean flag;
                if (Fitness.RecordingApi.subscribe(((FitApiManager) (eventkey)).client, FitApiManager.DATA_TYPE).await().getStatus().zzaEP <= 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    LogUtils.e(FitApiManager.TAG, "Subscription failed", new Object[0]);
                }
            } else
            {
                ((FitIntegrationManager) (eventkey)).fitApiManager.unsubscribe();
            }
            i++;
        }
          goto _L9
_L8:
        s = AccountsUtil.getGoogleAccounts(((FitOperationServiceHelper) (context)).context);
        int l = s.length;
        i = 0;
        while (i < l) 
        {
            eventkey = s[i];
            Object obj = ((FitOperationServiceHelper) (context)).context;
            Object obj1 = new FitApiManager(((Context) (obj)), eventkey);
            FitHabitsApiManager fithabitsapimanager1 = new FitHabitsApiManager(((Context) (obj)), eventkey);
            com.google.android.calendar.api.habit.Habit ahabit[];
            int i1;
            long l2;
            if (Clock.mockedTimestamp > 0L)
            {
                l2 = Clock.mockedTimestamp;
            } else
            {
                l2 = System.currentTimeMillis();
            }
            eventkey = new FitIntegrationManager(((Context) (obj)), eventkey, ((FitApiManager) (obj1)), fithabitsapimanager1, l2);
            obj = ((FitIntegrationManager) (eventkey)).habitApiManager;
            obj1 = (new HabitFilterOptions(((FitHabitsApiManager) (obj)).account.name)).setFitIntegrationStatus(Integer.valueOf(20));
            ahabit = ((com.google.android.calendar.api.habit.HabitClient.ListResult)((FitHabitsApiManager) (obj)).habitClient.list(((HabitFilterOptions) (obj1))).await()).getHabits();
            i1 = ahabit.length;
            for (int j = 0; j < i1; j++)
            {
                Object obj2 = ahabit[j];
                obj2 = CalendarApi.HabitFactory.modifyHabit(((com.google.android.calendar.api.habit.Habit) (obj2))).setFitIntegrationStatus(10);
                ((FitHabitsApiManager) (obj)).habitClient.update(((HabitModifications) (obj2))).await();
            }

            if (ahabit.length > 0)
            {
                BelongUtils.onIntegrationStatusChange(((FitHabitsApiManager) (obj)).context, false, ahabit.length);
            }
            if (!((FitIntegrationManager) (eventkey)).fitApiManager.connect())
            {
                LogUtils.e(FitIntegrationManager.TAG, "Failed to connect to GoogleApiClient", new Object[0]);
            } else
            {
                ((FitIntegrationManager) (eventkey)).fitApiManager.unsubscribe();
            }
            i++;
        }
          goto _L9
    }

    public void onReceive(Context context, Intent intent)
    {
        Object obj = intent.getExtras();
        String s = TAG;
        Object obj1;
        if (obj != null)
        {
            obj = ((Bundle) (obj)).toString();
        } else
        {
            obj = "empty";
        }
        LogUtils.d(s, "onReceive: %s extras=%s", new Object[] {
            intent, obj
        });
        obj1 = intent.getAction();
        obj = FitIntegrationOperation.getOperationForAction(((String) (obj1)));
        if (obj == null)
        {
            return;
        } else
        {
            int i = intent.getIntExtra("check_source", 0);
            Object obj2 = (EventKey)intent.getParcelableExtra("instance_id");
            intent = goAsync();
            com.google.common.base.Functions.ConstantFunction constantfunction = new com.google.common.base.Functions.ConstantFunction(com.google.calendar.v2a.android.util.metric.MetricUtils.Result.SUCCESS);
            class .Lambda._cls0
                implements Runnable
            {

                private final Context arg$1;
                private final String arg$2;
                private final int arg$3;
                private final EventKey arg$4;

                public final void run()
                {
                    FitOperationReceiver.lambda$onReceive$0$FitOperationReceiver(arg$1, arg$2, arg$3, arg$4);
                }

            .Lambda._cls0(Context context, String s, int i, EventKey eventkey)
            {
                arg$1 = context;
                arg$2 = s;
                arg$3 = i;
                arg$4 = eventkey;
            }
            }

            context = (FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls0(context, ((String) (obj1)), i, ((EventKey) (obj2))));
            obj1 = TimeUnit.SECONDS;
            obj2 = CalendarExecutor.BACKGROUND;
            TimeoutFuture timeoutfuture = new TimeoutFuture(context);
            com.google.common.util.concurrent.TimeoutFuture.Fire fire = new com.google.common.util.concurrent.TimeoutFuture.Fire(timeoutfuture);
            timeoutfuture.timer = ((ScheduledExecutorService) (obj2)).schedule(fire, 9L, ((TimeUnit) (obj1)));
            context.addListener(fire, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            context = (FluentFuture)MetricUtils.withMetrics(constantfunction, (FluentFuture)timeoutfuture, ((com.google.calendar.v2a.android.util.metric.MetricUtils.Operation) (obj)));
            intent.getClass();
            class .Lambda._cls1
                implements Runnable
            {

                private final android.content.BroadcastReceiver.PendingResult arg$1;

                public final void run()
                {
                    arg$1.finish();
                }

            .Lambda._cls1(android.content.BroadcastReceiver.PendingResult pendingresult)
            {
                arg$1 = pendingresult;
            }
            }

            context.addListener(new .Lambda._cls1(intent), CalendarExecutor.BACKGROUND);
            return;
        }
    }

}
