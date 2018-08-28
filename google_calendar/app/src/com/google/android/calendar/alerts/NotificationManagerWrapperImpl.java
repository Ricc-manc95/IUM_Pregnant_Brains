// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.common.CalendarFeatureConfigDelegate;
import com.google.android.calendar.utils.notification.NotificationUtil;

// Referenced classes of package com.google.android.calendar.alerts:
//            NotificationManagerWrapper

class NotificationManagerWrapperImpl extends NotificationManagerWrapper
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alerts/NotificationManagerWrapperImpl);
    private static NotificationManagerWrapperImpl instance;
    public NotificationManager notificationManager;

    private NotificationManagerWrapperImpl(Context context)
    {
        notificationManager = (NotificationManager)context.getSystemService("notification");
    }

    public static NotificationManagerWrapper getInstance(Context context)
    {
        com/google/android/calendar/alerts/NotificationManagerWrapperImpl;
        JVM INSTR monitorenter ;
        if (instance == null)
        {
            instance = new NotificationManagerWrapperImpl(context);
        }
        context = instance;
        com/google/android/calendar/alerts/NotificationManagerWrapperImpl;
        JVM INSTR monitorexit ;
        return context;
        context;
        throw context;
    }

    public final void cancel(String s, int i)
    {
        NotificationManager notificationmanager = notificationManager;
        if (CalendarFeatureConfigDelegate.useModernNotifications == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (!CalendarFeatureConfigDelegate.useModernNotifications.booleanValue())
        {
            s = null;
        }
        if (CalendarFeatureConfigDelegate.useModernNotifications == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (CalendarFeatureConfigDelegate.useModernNotifications.booleanValue())
        {
            i = 0x20000000;
        }
        notificationmanager.cancel(s, i);
    }

    public final void cancelAllOc()
    {
        StatusBarNotification astatusbarnotification[] = notificationManager.getActiveNotifications();
        int j = astatusbarnotification.length;
        for (int i = 0; i < j; i++)
        {
            StatusBarNotification statusbarnotification = astatusbarnotification[i];
            if (statusbarnotification.getId() == 0x20000000)
            {
                notificationManager.cancel(statusbarnotification.getTag(), statusbarnotification.getId());
            }
        }

    }

    public final void notify(String s, int i, AlertServiceHelper.EventNotificationWrapper eventnotificationwrapper)
    {
        if (CalendarFeatureConfigDelegate.useModernNotifications == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (!CalendarFeatureConfigDelegate.useModernNotifications.booleanValue()) goto _L2; else goto _L1
_L1:
        Notification notification = eventnotificationwrapper.notification;
        notification.flags = notification.flags | 8;
        LogUtils.d(TAG, "FLAG_ONLY_ALERT_ONCE set for notification %s", new Object[] {
            notification
        });
_L4:
        Object obj;
        obj = notificationManager;
        if (CalendarFeatureConfigDelegate.useModernNotifications == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        break MISSING_BLOCK_LABEL_275;
_L2:
        if (android.os.Build.VERSION.SDK_INT < 23) goto _L4; else goto _L3
_L3:
        String s1;
        obj = eventnotificationwrapper.notification;
        s1 = ((Notification) (obj)).extras.getString("UID");
        if (!TextUtils.isEmpty(s1)) goto _L6; else goto _L5
_L5:
        LogUtils.wtf(TAG, "Notification tag is empty", new Object[0]);
_L8:
        int j = 0;
_L9:
        if (j != 0)
        {
            return;
        }
          goto _L4
_L6:
        StatusBarNotification astatusbarnotification[];
        int k;
        astatusbarnotification = notificationManager.getActiveNotifications();
        k = astatusbarnotification.length;
        j = 0;
_L10:
        if (j >= k) goto _L8; else goto _L7
_L7:
label0:
        {
            StatusBarNotification statusbarnotification = astatusbarnotification[j];
            Notification notification1 = statusbarnotification.getNotification();
            if (!s1.equals(notification1.extras.get("UID")))
            {
                break label0;
            }
            LogUtils.d(TAG, "Repost of %s detected: %s %s", new Object[] {
                s1, obj, notification1
            });
            if (i != statusbarnotification.getId() || ((Notification) (obj)).priority <= 0)
            {
                break label0;
            }
            LogUtils.d(TAG, "Ignoring repost", new Object[0]);
            j = 1;
        }
          goto _L9
        j++;
          goto _L10
        if (!CalendarFeatureConfigDelegate.useModernNotifications.booleanValue())
        {
            s = null;
        }
        if (CalendarFeatureConfigDelegate.useModernNotifications == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (CalendarFeatureConfigDelegate.useModernNotifications.booleanValue())
        {
            i = 0x20000000;
        }
        NotificationUtil.notify(((NotificationManager) (obj)), s, i, eventnotificationwrapper.notification);
        return;
          goto _L9
    }

}
