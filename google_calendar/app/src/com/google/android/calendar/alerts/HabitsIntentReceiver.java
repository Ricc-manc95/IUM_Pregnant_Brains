// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.common.CalendarFeatureConfigDelegate;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsIntentService, HabitsIntentServiceHelper

public class HabitsIntentReceiver extends WakefulBroadcastReceiver
{

    private static final ImmutableList FORWARD_ACTIONS = ImmutableList.of("com.google.android.calendar.intent.action.TRACKING_SYNC_INITIATED", "com.google.android.calendar.intent.action.GROOVE_REQUEST_UPSYNCED", "com.google.android.calendar.intent.action.GROOVE_SYNCED", "com.google.android.calendar.intent.action.REFRESH_GROOVE_NOTIFICATIONS", "com.google.android.calendar.intent.action.HABITS_FORCE_SYNC", "com.google.android.calendar.intent.action.HABITS_POST_BELONG_CHECK_NOTIFICATION");
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alerts/HabitsIntentReceiver);
    public HabitsIntentServiceHelper habitsServiceHelper;

    public HabitsIntentReceiver()
    {
        habitsServiceHelper = null;
    }

    public void onReceive(Context context, Intent intent)
    {
        Object obj = intent.getExtras();
        String s = TAG;
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
        obj = new Intent(intent);
        if (!FORWARD_ACTIONS.contains(intent.getAction()))
        {
            ((Intent) (obj)).setAction("com.google.android.calendar.intent.action.HABITS_NOTIFICATION");
        }
        ((Intent) (obj)).setClass(context, com/google/android/calendar/alerts/HabitsIntentService);
        if (CalendarFeatureConfigDelegate.useJobs == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (CalendarFeatureConfigDelegate.useJobs.booleanValue())
        {
            class .Lambda._cls0
                implements Runnable
            {

                private final HabitsIntentReceiver arg$1;
                private final Context arg$2;
                private final Intent arg$3;

                public final void run()
                {
                    HabitsIntentReceiver habitsintentreceiver = arg$1;
                    Context context1 = arg$2;
                    Intent intent1 = arg$3;
                    if (habitsintentreceiver.habitsServiceHelper == null)
                    {
                        habitsintentreceiver.habitsServiceHelper = new HabitsIntentServiceHelper(context1);
                    }
                    habitsintentreceiver.habitsServiceHelper.onHandle(intent1);
                }

            .Lambda._cls0(Context context, Intent intent)
            {
                arg$1 = HabitsIntentReceiver.this;
                arg$2 = context;
                arg$3 = intent;
            }
            }

            CalendarExecutor.DISK.execute(new .Lambda._cls0(context, ((Intent) (obj))));
            return;
        } else
        {
            startWakefulService(context, ((Intent) (obj)));
            return;
        }
    }

}
