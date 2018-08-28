// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import com.android.calendarcommon2.LogUtils;

public final class NotificationUtil
{

    public static boolean getDefaultVibrate(Context context, SharedPreferences sharedpreferences)
    {
        boolean flag;
        flag = true;
        if (!sharedpreferences.contains("preferences_alerts_vibrateWhen"))
        {
            break MISSING_BLOCK_LABEL_102;
        }
        String s = sharedpreferences.getString("preferences_alerts_vibrateWhen", null);
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        if (!s.equals(context.getString(0x7f130385)))
        {
            break MISSING_BLOCK_LABEL_83;
        }
_L1:
        sharedpreferences.edit().remove("preferences_alerts_vibrateWhen").apply();
        LogUtils.d("NotificationUtil", "Migrating KEY_ALERTS_VIBRATE_WHEN(%s) to KEY_ALERTS_VIBRATE = %b", new Object[] {
            s, Boolean.valueOf(flag)
        });
        return flag;
        flag = false;
          goto _L1
        context;
        LogUtils.w("NotificationUtil", context, "KEY_ALERTS_VIBRATE_WHEN preference is not a String type", new Object[0]);
        return sharedpreferences.getBoolean("preferences_alerts_vibrate", false);
    }

    public static void notify(NotificationManager notificationmanager, String s, int i, Notification notification)
    {
        notificationmanager.notify(s, i, notification);
        if (android.os.Build.VERSION.SDK_INT < 26) goto _L2; else goto _L1
_L1:
        boolean flag = notificationmanager.getNotificationChannel("REMINDERS").shouldVibrate();
_L3:
        if (flag)
        {
            try
            {
                LogUtils.v("NotificationUtil", "sent notification with vibration", new Object[0]);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (NotificationManager notificationmanager)
            {
                LogUtils.e("NotificationUtil", notificationmanager, "Failed to post a notification.", new Object[0]);
            }
        }
        break MISSING_BLOCK_LABEL_89;
_L2:
        if ((notification.defaults & 2) == 2)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        notificationmanager = notification.vibrate;
        if (notificationmanager == null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        flag = true;
          goto _L3
        flag = false;
          goto _L3
    }
}
