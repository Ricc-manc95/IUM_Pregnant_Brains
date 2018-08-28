// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.notification;

import android.accounts.Account;
import android.os.Bundle;
import com.google.android.apps.calendar.timely.store.PreferredNotification;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.timely.TimelyUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.api.event.notification:
//            Notification, NotificationsStoreUtils

public final class NotificationsTimelyStoreUtils
{

    public static PreferredNotification[] convertNotifications(List list, boolean flag)
    {
        PreferredNotification apreferrednotification[] = new PreferredNotification[list.size()];
        int i = 0;
        while (i < list.size()) 
        {
            Notification notification = (Notification)list.get(i);
            int j;
            if (flag)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            apreferrednotification[i] = new PreferredNotification(j, notification.minutesBefore, NotificationsStoreUtils.apiMethodToStoreMethod(notification.method));
            i++;
        }
        return apreferrednotification;
    }

    public static Notification[] convertNotifications(List list)
    {
        Notification anotification[] = new Notification[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            anotification[i] = new Notification(NotificationsStoreUtils.storeMethodToApiMethod(((PreferredNotification)list.get(i)).method), ((PreferredNotification)list.get(i)).minutes);
        }

        return anotification;
    }

    static Notification[] loadDefaultNotifications(CalendarDescriptor calendardescriptor, boolean flag)
    {
        return convertNotifications(Arrays.asList(TimelyStore.acquire(CalendarApi.getApiAppContext()).loadNotifications(String.valueOf(calendardescriptor.calendarKey.getLocalId()), calendardescriptor.account, flag, 1, null, "timestamp ASC")));
    }

    public static Notification[] loadDefaultNotifications(CalendarDescriptor calendardescriptor, boolean flag, com.google.android.apps.calendar.timely.store.TimelyStore.Notifications notifications)
    {
        if (notifications == null)
        {
            return loadDefaultNotifications(calendardescriptor, flag);
        }
        ArrayList arraylist = new ArrayList();
        calendardescriptor = String.valueOf(calendardescriptor.calendarKey.getLocalId());
        if (notifications.defaultNotificationsMap.containsKey(calendardescriptor))
        {
            calendardescriptor = (com.google.android.apps.calendar.timely.store.TimelyStore.DefaultNotifications)notifications.defaultNotificationsMap.get(calendardescriptor);
            notifications = new ArrayList();
            int i;
            int j;
            if (!((com.google.android.apps.calendar.timely.store.TimelyStore.DefaultNotifications) (calendardescriptor)).noTimedNotifications)
            {
                if (((com.google.android.apps.calendar.timely.store.TimelyStore.DefaultNotifications) (calendardescriptor)).timedNotifications.size() == 0 && ((com.google.android.apps.calendar.timely.store.TimelyStore.DefaultNotifications) (calendardescriptor)).isExchange)
                {
                    notifications.add(com.google.android.apps.calendar.timely.store.TimelyStore.DefaultNotifications.EXCHANGE_DEFAULT_TIMED_NOTIFICATION);
                } else
                {
                    notifications.addAll(((com.google.android.apps.calendar.timely.store.TimelyStore.DefaultNotifications) (calendardescriptor)).timedNotifications);
                }
            }
            if (!((com.google.android.apps.calendar.timely.store.TimelyStore.DefaultNotifications) (calendardescriptor)).noAllDayNotifications)
            {
                if (((com.google.android.apps.calendar.timely.store.TimelyStore.DefaultNotifications) (calendardescriptor)).allDayNotifications.size() == 0 && ((com.google.android.apps.calendar.timely.store.TimelyStore.DefaultNotifications) (calendardescriptor)).isExchange)
                {
                    notifications.add(com.google.android.apps.calendar.timely.store.TimelyStore.DefaultNotifications.EXCHANGE_DEFAULT_ALL_DAY_NOTIFICATION);
                } else
                {
                    notifications.addAll(((com.google.android.apps.calendar.timely.store.TimelyStore.DefaultNotifications) (calendardescriptor)).allDayNotifications);
                }
            }
            calendardescriptor = (PreferredNotification[])notifications.toArray(new PreferredNotification[notifications.size()]);
        } else
        {
            calendardescriptor = new PreferredNotification[0];
        }
        if (calendardescriptor != null)
        {
            j = calendardescriptor.length;
            i = 0;
            while (i < j) 
            {
                notifications = calendardescriptor[i];
                boolean flag1;
                if (((PreferredNotification) (notifications)).allDay > 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1 == flag)
                {
                    arraylist.add(notifications);
                }
                i++;
            }
        }
        return convertNotifications(arraylist);
    }

    public static void storeDefaultNotifications(List list, CalendarDescriptor calendardescriptor, boolean flag)
    {
        TimelyStore.acquire(CalendarApi.getApiAppContext()).updateDefaultNotifications(CalendarApi.getApiAppContext(), String.valueOf(calendardescriptor.calendarKey.getLocalId()), calendardescriptor.account, flag, convertNotifications(list, flag));
        list = calendardescriptor.account;
        if (AccountUtil.isGoogleAccount(list))
        {
            Bundle bundle = new Bundle();
            bundle.putString("sync_extra_local_calendar_id", String.valueOf(calendardescriptor.calendarKey.getLocalId()));
            bundle.putString("sync_extra_server_calendar_id", calendardescriptor.calendarId);
            bundle.putBoolean("sync_extra_all_day", flag);
            TimelyUtils.triggerSyncAdapterSyncWithExtras(list, "sync_extra_update_default_notifications", true, bundle);
        }
    }

    public static void storePreferredNotifications(List list, Account account, boolean flag)
    {
        TimelyStore timelystore = TimelyStore.acquire(CalendarApi.getApiAppContext());
        android.content.Context context = CalendarApi.getApiAppContext();
        list = convertNotifications(list, flag);
        PreferredNotification apreferrednotification[] = timelystore.loadRecentlyUsedNotificationsForAccount(account, flag);
        timelystore.updateNotifications(account.name, account, flag, list, apreferrednotification, 0, context);
    }
}
