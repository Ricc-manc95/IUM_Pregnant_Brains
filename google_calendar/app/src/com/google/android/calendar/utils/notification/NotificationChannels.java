// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.calendar.timely.settings.PreferencesUtils;

// Referenced classes of package com.google.android.calendar.utils.notification:
//            NotificationPrefs

public final class NotificationChannels
{

    public static boolean channelsCreated = false;

    public static void initialize(Context context, boolean flag)
    {
        if (android.os.Build.VERSION.SDK_INT >= 26 && !channelsCreated)
        {
            channelsCreated = true;
            NotificationManager notificationmanager = (NotificationManager)context.getSystemService(android/app/NotificationManager);
            NotificationChannel notificationchannel = new NotificationChannel("REMINDERS", context.getString(0x7f1303e3), 5);
            setSoundAndVibration(context, notificationchannel);
            notificationchannel.setShowBadge(true);
            notificationmanager.createNotificationChannel(notificationchannel);
            if (flag)
            {
                NotificationChannel notificationchannel1 = new NotificationChannel("DEBUG", context.getString(0x7f130157), 2);
                setSoundAndVibration(context, notificationchannel1);
                notificationchannel1.setShowBadge(false);
                notificationmanager.createNotificationChannel(notificationchannel1);
                return;
            }
        }
    }

    private static void setSoundAndVibration(Context context, NotificationChannel notificationchannel)
    {
        Object obj = null;
        NotificationPrefs notificationprefs = new NotificationPrefs(context, context.getSharedPreferences("com.google.android.calendar_preferences", 0));
        boolean flag;
        if (notificationprefs.silenced)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            context = null;
        } else
        {
            if (notificationprefs.ringtone == null)
            {
                notificationprefs.ringtone = PreferencesUtils.getRingtonePreference(notificationprefs.context);
            }
            context = notificationprefs.ringtone;
        }
        notificationprefs.silenced = true;
        if (TextUtils.isEmpty(context))
        {
            context = obj;
        } else
        {
            context = Uri.parse(context);
        }
        if (context != null)
        {
            notificationchannel.setSound(context, (new android.media.AudioAttributes.Builder()).setUsage(10).setContentType(0).build());
        }
        notificationchannel.enableVibration(notificationprefs.getDefaultVibrate());
    }

}
