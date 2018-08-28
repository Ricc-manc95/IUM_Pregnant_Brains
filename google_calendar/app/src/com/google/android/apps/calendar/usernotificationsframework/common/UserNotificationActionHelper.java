// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.common;

import android.content.Intent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationState;
import com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog;
import com.google.common.base.Absent;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.common:
//            UserNotificationBroadcastReceiver

public class UserNotificationActionHelper
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/usernotificationsframework/common/UserNotificationActionHelper);

    public UserNotificationActionHelper()
    {
    }

    public static void attachStateUpdateToUserAction(Intent intent, UserNotification usernotification, UserNotificationState usernotificationstate)
    {
        UserNotificationBroadcastReceiver.attachStateUpdateToUserAction(intent, usernotification, usernotificationstate, Absent.INSTANCE);
    }

    public static void updateOnUserAction(Intent intent)
    {
        String s = TAG;
        String s1 = intent.getAction();
        NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls3(s, "Notification status update on action: %s.", new Object[] {
            s1
        }));
        NotificationLog.logOnFailure(UserNotificationBroadcastReceiver.updateNotificationFromIntentAsync(intent), TAG, "Failed to update notification.", new Object[0]);
    }

}
