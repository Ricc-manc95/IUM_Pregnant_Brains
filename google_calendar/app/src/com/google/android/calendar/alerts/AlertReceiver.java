// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.common.CalendarFeatureConfigDelegate;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;

// Referenced classes of package com.google.android.calendar.alerts:
//            AlertServiceHelper, AlertService

public class AlertReceiver extends WakefulBroadcastReceiver
{

    public AlertReceiver()
    {
    }

    static final void lambda$onReceive$0$AlertReceiver(Context context, Intent intent, android.content.BroadcastReceiver.PendingResult pendingresult)
    {
        AlertServiceHelper.processRequest(context, intent);
        pendingresult.finish();
    }

    public void onReceive(Context context, Intent intent)
    {
        Object obj = intent.getExtras();
        if (obj != null)
        {
            obj = ((Bundle) (obj)).toString();
        } else
        {
            obj = "empty";
        }
        LogUtils.d("AlertReceiver", "onReceive: %s extras=%s", new Object[] {
            intent, obj
        });
        obj = intent.getAction();
        obj = (new Intent()).setClass(context, com/google/android/calendar/alerts/AlertService).putExtras(intent).putExtra("action", ((String) (obj)));
        intent = intent.getData();
        if (intent != null)
        {
            ((Intent) (obj)).putExtra("uri", intent.toString());
        }
        if (CalendarFeatureConfigDelegate.useJobs == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (CalendarFeatureConfigDelegate.useJobs.booleanValue() || RemoteFeatureConfig.REMOVE_ALERT_SERVICE.enabled())
        {
            intent = goAsync();
            class .Lambda._cls0
                implements Runnable
            {

                private final Context arg$1;
                private final Intent arg$2;
                private final android.content.BroadcastReceiver.PendingResult arg$3;

                public final void run()
                {
                    AlertReceiver.lambda$onReceive$0$AlertReceiver(arg$1, arg$2, arg$3);
                }

            .Lambda._cls0(Context context, Intent intent, android.content.BroadcastReceiver.PendingResult pendingresult)
            {
                arg$1 = context;
                arg$2 = intent;
                arg$3 = pendingresult;
            }
            }

            CalendarExecutor.BACKGROUND.execute(new .Lambda._cls0(context, ((Intent) (obj)), intent));
            return;
        } else
        {
            startWakefulService(context, ((Intent) (obj)));
            return;
        }
    }
}
