// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog;
import com.google.android.calendar.executors.ThrottlingExecutor;
import com.google.android.calendar.utils.permission.PermissionsUtil;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            NotificationsInitializer

public static class .Lambda._cls0 extends BroadcastReceiver
{

    public void onReceive(Context context, Intent intent)
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).new_notifications();
        if (!RemoteFeatureConfig.NEW_NOTIFICATIONS.enabled())
        {
            return;
        }
        intent = intent.getAction();
        if (!NotificationsInitializer.initialized)
        {
            LogUtils.wtf(NotificationsInitializer.TAG, "Initialize method should be called first.", new Object[0]);
            return;
        }
        boolean flag;
        if (!PermissionsUtil.canRequestPermissions())
        {
            flag = true;
        } else
        if (PermissionsUtil.checkSelfPermission(context, "android.permission.READ_CALENDAR") == 0 && PermissionsUtil.checkSelfPermission(context, "android.permission.WRITE_CALENDAR") == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            context = NotificationsInitializer.TAG;
            NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.(context, "No calendar permissions.", new Object[0]));
            return;
        } else
        {
            String s = NotificationsInitializer.TAG;
            NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.(s, "Received an action: %s.", new Object[] {
                intent
            }));
            class .Lambda._cls0
                implements Runnable
            {

                private final Context arg$1;
                private final String arg$2;

                public final void run()
                {
                    Context context1 = arg$1;
                    String s1 = arg$2;
                    UserNotificationManager usernotificationmanager = UserNotificationManager.instance;
                    if (usernotificationmanager == null)
                    {
                        throw new NullPointerException(String.valueOf("Call initialize method first."));
                    } else
                    {
                        ((UserNotificationManager)usernotificationmanager).check(context1, s1);
                        return;
                    }
                }

            .Lambda._cls0(Context context, String s)
            {
                arg$1 = context;
                arg$2 = s;
            }
            }

            context = new .Lambda._cls0(context, intent);
            NotificationsInitializer.THROTTLING_EXECUTOR.execute(context);
            return;
        }
    }

    public .Lambda._cls0()
    {
    }
}
