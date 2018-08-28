// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.CalendarContract;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.usernotificationsframework.common.UserNotificationActionHelper;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationState;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;

// Referenced classes of package com.google.android.calendar.alerts:
//            EventNotificationInfo, NotificationManagerWrapperImpl, AlertServiceHelper, AlertActionIntentBuilder

public class DismissAlarmsService extends IntentService
{

    private static final String PROJECTION[] = {
        "state"
    };

    public DismissAlarmsService()
    {
        super("DismissAlarmsService");
    }

    public static PendingIntent createClickNotificationIntent(Context context, EventNotificationInfo eventnotificationinfo, int i)
    {
        eventnotificationinfo = createDismissAlarmsIntentInternal(context, eventnotificationinfo, i, "com.google.android.calendar.SHOW", null);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        return PendingIntent.getService(context, (int)l, eventnotificationinfo, 0x8000000);
    }

    public static PendingIntent createClickNotificationIntent(Context context, EventNotificationInfo eventnotificationinfo, int i, Bundle bundle)
    {
        eventnotificationinfo = createDismissAlarmsIntentInternal(context, eventnotificationinfo, i, "com.google.android.calendar.SHOW", bundle);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        return PendingIntent.getService(context, (int)l, eventnotificationinfo, 0x8000000);
    }

    private static Intent createDismissAlarmsIntentInternal(Context context, EventNotificationInfo eventnotificationinfo, int i, String s, Bundle bundle)
    {
        Object obj = eventnotificationinfo.eventKey;
        StringBuilder stringbuilder = (new StringBuilder(obj.getClass().getSimpleName())).append('|');
        ((EventKey) (obj)).serializeInternal(stringbuilder);
        obj = stringbuilder.toString();
        obj = (new Intent()).setClass(context, com/google/android/calendar/alerts/DismissAlarmsService).setAction(s).putExtra("eventkey", ((String) (obj))).putExtra("eventstart", eventnotificationinfo.startTime).putExtra("notificationid", i).putExtra("notificationtag", eventnotificationinfo.tag);
        if (!eventnotificationinfo.endTimeUnspecified)
        {
            ((Intent) (obj)).putExtra("eventend", eventnotificationinfo.endTime);
        }
        if (eventnotificationinfo.userNotification != null)
        {
            eventnotificationinfo = eventnotificationinfo.userNotification;
            if ("com.google.android.calendar.SHOW".equals(s))
            {
                context = UserNotificationState.ACCEPTED;
            } else
            {
                context = UserNotificationState.DISMISSED;
            }
            UserNotificationActionHelper.attachStateUpdateToUserAction(((Intent) (obj)), eventnotificationinfo, context);
        }
        if (bundle != null)
        {
            ((Intent) (obj)).putExtras(bundle);
        }
        return ((Intent) (obj));
    }

    public static PendingIntent createDismissNotificationIntent(Context context, EventNotificationInfo eventnotificationinfo, int i)
    {
        eventnotificationinfo = createDismissAlarmsIntentInternal(context, eventnotificationinfo, i, "com.google.android.calendar.DISMISS", null);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        return PendingIntent.getService(context, (int)l, eventnotificationinfo, 0x8000000);
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onHandleIntent(Intent intent)
    {
        LogUtils.d("DismissAlarmsService", "onReceive: a=%s %s", new Object[] {
            intent.getAction(), intent
        });
        Object obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).new_notifications();
        if (RemoteFeatureConfig.NEW_NOTIFICATIONS.enabled())
        {
            UserNotificationActionHelper.updateOnUserAction(intent);
        }
        EventKey eventkey = EventKey.deserialize(intent.getStringExtra("eventkey"));
        long l = intent.getLongExtra("eventstart", -1L);
        long l1 = intent.getLongExtra("eventend", -1L);
        int i = intent.getIntExtra("notificationid", -1);
        Object obj1 = intent.getStringExtra("notificationtag");
        if (eventkey instanceof CpEventKey)
        {
            long l2 = ((CpEventKey)eventkey).localId();
            Uri uri = android.provider.CalendarContract.CalendarAlerts.CONTENT_URI;
            if (l2 != -1L)
            {
                obj = (new StringBuilder(41)).append("state=1 AND event_id=").append(l2).toString();
            } else
            {
                obj = "state=1";
            }
            if (AndroidPermissionUtils.hasMandatoryPermissions(this) && Utils.isCalendarStorageEnabled(this))
            {
                ContentResolver contentresolver = getContentResolver();
                ContentValues contentvalues = new ContentValues();
                contentvalues.put(PROJECTION[0], Integer.valueOf(2));
                contentresolver.update(uri, contentvalues, ((String) (obj)), null);
            } else
            {
                LogUtils.e("DismissAlarmsService", "Could not mark CalendarAlert as dismissed due to lack of permissions or access to calendar storage", new Object[0]);
            }
        }
        AlertServiceHelper.hideNotification(NotificationManagerWrapperImpl.getInstance(this), i, ((String) (obj1)));
        if ("com.google.android.calendar.SHOW".equals(intent.getAction()))
        {
            LatencyLoggerHolder.get().markAt(30);
            obj = new Intent("android.intent.action.VIEW");
            obj1 = CalendarContract.CONTENT_URI.buildUpon();
            l2 = AlertActionIntentBuilder.getCpLocalEventIdFrom(eventkey);
            ((android.net.Uri.Builder) (obj1)).appendEncodedPath((new StringBuilder(27)).append("events/").append(l2).toString());
            ((Intent) (obj)).setDataAndType(((android.net.Uri.Builder) (obj1)).build(), "vnd.android.cursor.item/event");
            ((Intent) (obj)).setPackage(getPackageName());
            ((Intent) (obj)).putExtra("beginTime", l);
            ((Intent) (obj)).putExtra("endTime", l1);
            ((Intent) (obj)).putExtra("intent_source", "notification");
            obj1 = (new StringBuilder(eventkey.getClass().getSimpleName())).append('|');
            eventkey.serializeInternal(((StringBuilder) (obj1)));
            ((Intent) (obj)).putExtra("eventkey", ((StringBuilder) (obj1)).toString());
            ((Intent) (obj)).putExtras(intent.getExtras());
            ((Intent) (obj)).addFlags(0x10000000);
            startActivity(((Intent) (obj)));
            intent = "open";
        } else
        if ("com.google.android.calendar.DISMISS".equals(intent.getAction()))
        {
            intent = "dismiss";
        } else
        {
            intent = null;
        }
        if (intent != null)
        {
            obj = AnalyticsLoggerHolder.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)obj).trackEvent(this, "notification", intent);
        }
    }

}
