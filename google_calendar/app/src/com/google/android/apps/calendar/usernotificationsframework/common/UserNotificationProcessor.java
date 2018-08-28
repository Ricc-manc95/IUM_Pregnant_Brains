// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.common;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationPlugin;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationState;
import com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog;
import com.google.android.apps.calendar.usernotificationsframework.storage.UserNotificationStore;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import com.google.common.base.Optional;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

class UserNotificationProcessor
{

    public static final long DEFAULT_NOTIFICATION_CHECK_WINDOW_MILLIS;
    public static final Executor SERIAL_EXECUTOR;
    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/usernotificationsframework/common/UserNotificationProcessor);
    public final UserNotificationPlugin userNotificationPlugin;
    public final UserNotificationStore userNotificationStore;

    public UserNotificationProcessor(UserNotificationStore usernotificationstore, UserNotificationPlugin usernotificationplugin)
    {
        userNotificationStore = usernotificationstore;
        userNotificationPlugin = usernotificationplugin;
    }

    static final boolean lambda$scheduleNextCheck$3$UserNotificationProcessor(Map map, UserNotification usernotification)
    {
label0:
        {
            if (map.containsKey(usernotification))
            {
                map = (UserNotificationState)map.get(usernotification);
                boolean flag;
                if (map.equals(UserNotificationState.ACCEPTED) || map.equals(UserNotificationState.DISMISSED) || map.equals(UserNotificationState.OBSOLETE) || map.equals(UserNotificationState.EXPIRED))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    break label0;
                }
            }
            return true;
        }
        return false;
    }

    final boolean updateNotificationState(UserNotification usernotification, UserNotificationState usernotificationstate, UserNotificationState usernotificationstate1, Optional optional, boolean flag)
    {
        String s;
        String s1;
        int i;
        long l;
        long l1;
        boolean flag1;
        try
        {
            flag1 = userNotificationPlugin.onNotificationStateUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELPMASJEDTQ6IPJ9CDGN8QBFDPPMCSJ1DLINERRIDCNM6RREEHP62ORKECNLASR5E976UT39CPKM6OBKD5NMSEQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UTBJCLP6SRRKD5J6IOR1EHKMURJJCPP62RB5ETNN4QPFCDNMST3IC5HN8SPFALPMASIEDTQ6IPJ9CDGN8QBFDP9N8OBKCKTKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQN6PBIDPNN8QB6D5HM2T39DTN76PJIC5MMATRFE9LIUORFDPQ74OB3EHPIULBJCLP4SRRKD5J6IOR1EHKMURIJEHGN8P9R9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQFE1Q6IRREC5M3MAAQ0(usernotification, usernotificationstate, usernotificationstate1);
        }
        // Misplaced declaration of an exception variable
        catch (UserNotification usernotification)
        {
            usernotificationstate = TAG;
            NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls5(usernotificationstate, usernotification, "Failed on running notification state transition (user_update=%s).", new Object[] {
                Boolean.valueOf(flag)
            }));
            return false;
        }
        if (flag1)
        {
            userNotificationStore.setNotificationState(usernotification, usernotificationstate1);
        }
        s = TAG;
        if (flag1)
        {
            optional = "was";
        } else
        {
            optional = "was NOT";
        }
        i = usernotification.hashCode();
        s1 = LogUtils.sanitizeName(TAG, usernotification.getEntityFingerprint());
        l = usernotification.getTriggerMillis();
        l1 = usernotification.getExpirationMillis();
        NotificationLog.SERIAL_EXECUTOR.execute(new com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog..Lambda._cls3(s, "Notification status %s updated: id='%s', entity='%s', trigger_time='%s', expiration_time='%s', old_status='%s', new_status='%s' (user_update=%s).", new Object[] {
            optional, Integer.valueOf(i), s1, Long.valueOf(l), Long.valueOf(l1), usernotificationstate, usernotificationstate1, Boolean.valueOf(flag)
        }));
        return flag1;
    }

    static 
    {
        SERIAL_EXECUTOR = CalendarExecutors.serialExecutor(CalendarExecutor.DISK);
        DEFAULT_NOTIFICATION_CHECK_WINDOW_MILLIS = TimeUnit.DAYS.toMillis(1L);
    }
}
