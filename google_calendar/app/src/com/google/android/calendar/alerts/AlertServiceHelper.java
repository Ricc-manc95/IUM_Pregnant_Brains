// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.backup.BackupManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags;
import com.google.android.apps.calendar.config.common.CalendarFeatureConfigDelegate;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.intent.IntentUtils;
import com.google.android.calendar.utils.notification.NotificationChannels;
import com.google.android.calendar.utils.notification.NotificationPrefs;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.alerts:
//            EventNotificationInfo, NotificationManagerWrapper, AlertUtils, AlarmScheduler, 
//            AlertBuilder, DismissAlarmsService, AlertActionIntentBuilder, ContactNotificationImpl, 
//            ContactNotification, EveryoneDeclinedAlertBuilderHelper, NotificationActionTrampoline, NotificationManagerWrapperImpl, 
//            AlarmManagerInterface

public final class AlertServiceHelper
{

    private static final String ALERT_PROJECTION[] = {
        "_id", "event_id", "state", "title", "eventLocation", "selfAttendeeStatus", "allDay", "alarmTime", "minutes", "begin", 
        "end", "sync_data9 as sync_data9", "dirty as dirty", "account_type"
    };
    private static final long ALL_DAY_GRACE_PERIOD;
    private static final long MINUTE;
    private static final long MIN_DEPRIORITIZE_GRACE_PERIOD;
    private static Boolean receivedProviderReminderBroadcast = null;

    private static long calculateNextRefreshTime(Context context, long l, long l1, com.google.android.calendar.time.Time time, ArrayList arraylist)
    {
        arraylist = (ArrayList)arraylist;
        int j = arraylist.size();
        int i = 0;
        long l2 = l;
        while (i < j) 
        {
            EventNotificationInfo eventnotificationinfo = (EventNotificationInfo)arraylist.get(i);
            l = eventnotificationinfo.endTime;
            if (eventnotificationinfo.allDay)
            {
                l = Utils.convertAlldayUtcToLocal(time, eventnotificationinfo.endTime, Time.getCurrentTimezone());
            } else
            if (eventnotificationinfo.endTimeUnspecified)
            {
                l = eventnotificationinfo.startTime + 0x36ee80L;
            }
            if (l <= l1)
            {
                l = 0x7fffffffffffffffL;
            }
            l2 = Math.min(l2, l);
            i++;
        }
        return Math.min(l2, Utils.getNextMidnight(null, l1, Utils.getTimeZoneId(context)));
    }

    static void hideNotification(NotificationManagerWrapper notificationmanagerwrapper, int i, String s)
    {
        notificationmanagerwrapper.cancel(s, i);
    }

    private static void logEventIdsBumped(List list, List list1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (list != null)
        {
            for (list = list.iterator(); list.hasNext(); stringbuilder.append(","))
            {
                stringbuilder.append(((CpEventKey)((EventNotificationInfo)list.next()).eventKey).localId());
            }

        }
        if (list1 != null)
        {
            for (list = list1.iterator(); list.hasNext(); stringbuilder.append(","))
            {
                stringbuilder.append(((CpEventKey)((EventNotificationInfo)list.next()).eventKey).localId());
            }

        }
        if (stringbuilder.length() > 0 && stringbuilder.charAt(stringbuilder.length() - 1) == ',')
        {
            stringbuilder.setLength(stringbuilder.length() - 1);
        }
        if (stringbuilder.length() > 0)
        {
            LogUtils.d("AlertServiceHelper", "Reached max postings, bumping event IDs {%s} to digest.", new Object[] {
                stringbuilder
            });
        }
    }

    private static boolean processQuery(Cursor cursor, Context context, long l, ArrayList arraylist, ArrayList arraylist1)
    {
        ContentResolver contentresolver;
        HashMap hashmap;
        int i;
        contentresolver = context.getContentResolver();
        hashmap = new HashMap();
        i = 0;
_L22:
        Object obj1;
        Object obj2;
        int j;
        int i1;
        long l1;
        long l5;
        if (cursor.moveToNext())
        {
            l1 = cursor.getLong(0);
            l5 = cursor.getLong(1);
            i1 = cursor.getInt(8);
            obj1 = cursor.getString(3);
            obj2 = cursor.getString(4);
            j = cursor.getInt(5);
            break MISSING_BLOCK_LABEL_82;
        }
          goto _L1
_L36:
        long l3;
        l3 = cursor.getLong(9);
        l4 = cursor.getLong(10);
        obj3 = ContentUris.withAppendedId(android.provider.CalendarContract.CalendarAlerts.CONTENT_URI, l1);
        l6 = cursor.getLong(7);
        k = cursor.getInt(2);
        Object obj;
        long l2;
        boolean flag;
        boolean flag2;
        boolean flag4;
        if (cursor.getInt(6) != 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        obj = AlertUtils.getExtendedProperty(context, l5, "endTimeUnspecified");
        if (obj == null) goto _L3; else goto _L2
_L2:
        if (!((String) (obj)).equals("1")) goto _L3; else goto _L4
_L4:
        flag4 = true;
_L40:
        Object obj4;
        boolean flag6;
        boolean flag7;
        if ((l - l6) / MINUTE < 1L && !AlertUtils.hasAlertFiredInSharedPrefs(context, l5, l3, l6))
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
        flag = false;
        flag6 = flag;
        if (!RemoteFeatureConfig.EVERYONE_DECLINED.enabled()) goto _L6; else goto _L5
_L5:
        if (cursor.getLong(12) != 0L)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        flag7 = AccountUtil.isGoogleType(cursor.getString(13));
        flag6 = flag;
        if (j != 0) goto _L6; else goto _L7
_L7:
        flag6 = flag;
        if (!flag7) goto _L6; else goto _L8
_L8:
        obj = EventExtrasFlags.fromCursor(cursor, 11);
        if ((((EventExtrasFlags) (obj)).flags & 0x800) != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L10; else goto _L9
_L9:
        j = ((EventExtrasFlags) (obj)).flags;
        if ((j & 0x2000) != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0) goto _L10; else goto _L11
_L11:
        flag = true;
_L41:
        flag6 = flag;
_L6:
        if (LogUtils.maxEnabledLogLevel <= 3) goto _L13; else goto _L12
_L12:
        flag = false;
_L30:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_494;
        }
        LogUtils.d("AlertServiceHelper", "alertCursor result: alarmTime:%d alertId:%d eventId:%d state: %d minutes:%d declined:%b responded:%b beginTime:%d endTime:%d allDay:%b alarmTime:%d %s everyoneDeclinedAndNotDismissed:%b", new Object[] {
            Long.valueOf(l6), Long.valueOf(l1), Long.valueOf(l5), Integer.valueOf(k), Integer.valueOf(i1), Boolean.valueOf(flag1), Boolean.valueOf(flag3), Long.valueOf(l3), Long.valueOf(l4), Boolean.valueOf(flag2), 
            Long.valueOf(l6), (new StringBuilder(24)).append(" newAlertOverride: ").append(flag5).toString(), Boolean.valueOf(flag6)
        });
        obj4 = new ContentValues();
        i1 = -1;
        flag = false;
        if (!flag2) goto _L15; else goto _L14
_L14:
        obj = TimeZone.getDefault().getID();
        l2 = Utils.convertAlldayUtcToLocal(null, l3, ((String) (obj)));
        l1 = Utils.convertAlldayUtcToLocal(null, l4, ((String) (obj)));
          goto _L16
_L37:
        if (!flag2) goto _L18; else goto _L17
_L17:
        if (l - l2 >= ALL_DAY_GRACE_PERIOD)
        {
            break MISSING_BLOCK_LABEL_1196;
        }
          goto _L18
_L38:
        j = 1;
        flag = true;
        ((ContentValues) (obj4)).put("receivedTime", Long.valueOf(l));
        i++;
_L35:
        if (j == -1) goto _L20; else goto _L19
_L19:
        ((ContentValues) (obj4)).put("state", Integer.valueOf(j));
        AlertUtils.setAlertFiredInSharedPrefs(context, l5, l3, l6);
_L34:
        if (((ContentValues) (obj4)).size() > 0)
        {
            contentresolver.update(((Uri) (obj3)), ((ContentValues) (obj4)), null, null);
        }
        if (j != 1) goto _L22; else goto _L21
_L21:
        obj1 = new EventNotificationInfo(l5, ((String) (obj1)), ((String) (obj2)), l3, flag4, l4, l6, flag2, flag, flag6);
        if (!hashmap.containsKey(Long.valueOf(l5))) goto _L24; else goto _L23
_L23:
        obj2 = (EventNotificationInfo)hashmap.get(Long.valueOf(l5));
        l3 = ((EventNotificationInfo) (obj2)).startTime;
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_733;
        }
        l3 = Utils.convertAlldayUtcToLocal(null, ((EventNotificationInfo) (obj2)).startTime, ((String) (obj)));
        l3 -= l;
        l4 = l2 - l;
        if (l4 >= 0L || l3 <= 0L) goto _L26; else goto _L25
_L25:
        if (Math.abs(l4) < MIN_DEPRIORITIZE_GRACE_PERIOD)
        {
            j = 1;
        } else
        {
            j = 0;
        }
_L31:
        if (j == 0) goto _L22; else goto _L27
_L27:
        arraylist.remove(obj2);
        arraylist1.remove(obj2);
        obj3 = ((EventNotificationInfo) (obj2)).eventKey;
        obj4 = (new StringBuilder(obj3.getClass().getSimpleName())).append('|');
        ((EventKey) (obj3)).serializeInternal(((StringBuilder) (obj4)));
        LogUtils.d("AlertServiceHelper", "Dropping alert for recurring event ID:%s, startTime:%s in favor of startTime:%s", new Object[] {
            ((StringBuilder) (obj4)).toString(), Long.valueOf(((EventNotificationInfo) (obj2)).startTime), Long.valueOf(((EventNotificationInfo) (obj1)).startTime)
        });
_L24:
        hashmap.put(Long.valueOf(l5), obj1);
        if (l1 <= l) goto _L29; else goto _L28
_L28:
        arraylist.add(obj1);
          goto _L22
        obj;
        LogUtils.w("AlertServiceHelper", ((Throwable) (obj)), "Failed to parse event extra flags", new Object[0]);
        flag6 = flag;
          goto _L6
        context;
        if (cursor != null)
        {
            cursor.close();
        }
        throw context;
_L13:
        if (!Log.isLoggable("AlertServiceHelper", 3))
        {
            break MISSING_BLOCK_LABEL_957;
        }
        flag = true;
          goto _L30
        flag = Log.isLoggable("AlertServiceHelper", 3);
          goto _L30
_L26:
        if (Math.abs(l4) < Math.abs(l3))
        {
            j = 1;
        } else
        {
            j = 0;
        }
          goto _L31
_L29:
        if (!flag2 || obj == null) goto _L22; else goto _L32
_L32:
        if (!DateUtils.isToday(l2)) goto _L22; else goto _L33
_L33:
        arraylist1.add(obj1);
          goto _L22
_L1:
        if (cursor != null)
        {
            cursor.close();
        }
        return i > 0;
_L20:
        j = k;
          goto _L34
_L39:
        j = i1;
          goto _L35
_L43:
        obj = null;
        l1 = l4;
        l2 = l3;
          goto _L16
        Object obj3;
        int k;
        long l4;
        long l6;
        boolean flag1;
        boolean flag3;
        boolean flag5;
        if (j == 2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (j != 0 && j != 3)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
          goto _L36
_L16:
        if (!flag1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (l - l4 >= 0L)
        {
            break MISSING_BLOCK_LABEL_1196;
        }
          goto _L37
_L18:
        if (j == 0)
        {
            break MISSING_BLOCK_LABEL_1196;
        }
        if (k != 0 && !flag5) goto _L39; else goto _L38
_L3:
        flag4 = false;
          goto _L40
_L10:
        flag = false;
          goto _L41
_L15:
        if (!flag4) goto _L43; else goto _L42
_L42:
        obj = null;
        l1 = 0x36ee80L + l3;
        l2 = l3;
          goto _L16
        j = 2;
          goto _L35
    }

    static void processRequest(Context context, Intent intent)
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).new_notifications();
        if (!RemoteFeatureConfig.NEW_NOTIFICATIONS.enabled())
        {
            if (!AndroidPermissionUtils.hasMandatoryPermissions(context))
            {
                LogUtils.d("AlertServiceHelper", "Calendar permissions are not granted.  Cannot process message.", new Object[0]);
                return;
            }
            intent = intent.getExtras();
            if (intent == null)
            {
                LogUtils.d("AlertServiceHelper", "Empty message received, ignoring.", new Object[0]);
                return;
            }
            Object obj = intent.getString("action");
            if (!IntentUtils.isSuperfluousProviderChange(((String) (obj))))
            {
                LogUtils.d("AlertServiceHelper", "%d Action = %s", new Object[] {
                    Long.valueOf(intent.getLong("alarmTime", 0L)), obj
                });
                boolean flag = ((String) (obj)).equals("android.intent.action.EVENT_REMINDER");
                if (flag)
                {
                    if (receivedProviderReminderBroadcast == null)
                    {
                        receivedProviderReminderBroadcast = Boolean.valueOf(context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_received_provider_reminder_broadcast", false));
                    }
                    if (!receivedProviderReminderBroadcast.booleanValue())
                    {
                        receivedProviderReminderBroadcast = Boolean.valueOf(true);
                        LogUtils.d("AlertServiceHelper", "Setting key %s to: true", new Object[] {
                            "preference_received_provider_reminder_broadcast"
                        });
                        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putBoolean("preference_received_provider_reminder_broadcast", true).apply();
                        (new BackupManager(context)).dataChanged();
                    }
                }
                if (flag || IntentUtils.hasProviderChanged(((String) (obj))) || ((String) (obj)).equals("android.intent.action.EVENT_REMINDER") || ((String) (obj)).equals("com.google.android.calendar.EVENT_REMINDER_APP") || ((String) (obj)).equals("android.intent.action.MY_PACKAGE_REPLACED") || ((String) (obj)).equals("android.intent.action.LOCALE_CHANGED"))
                {
                    if (CalendarFeatureConfigDelegate.useJobs == null)
                    {
                        throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
                    }
                    long l;
                    if (!CalendarFeatureConfigDelegate.useJobs.booleanValue() && !RemoteFeatureConfig.REMOVE_ALERT_SERVICE.enabled() && IntentUtils.hasProviderChanged(((String) (obj))))
                    {
                        try
                        {
                            Thread.sleep(5000L);
                        }
                        // Misplaced declaration of an exception variable
                        catch (Intent intent) { }
                    }
                    updateAlertNotification(context);
                } else
                if (((String) (obj)).equals("android.intent.action.TIME_SET"))
                {
                    rescheduleMissedAlarms(context.getContentResolver(), context, AlertUtils.createAlarmManager(context));
                    updateAlertNotification(context);
                } else
                if (((String) (obj)).equals("removeOldReminders"))
                {
                    intent = context.getContentResolver();
                    if (Clock.mockedTimestamp > 0L)
                    {
                        l = Clock.mockedTimestamp;
                    } else
                    {
                        l = System.currentTimeMillis();
                    }
                    obj = new ContentValues();
                    ((ContentValues) (obj)).put("state", Integer.valueOf(2));
                    intent.update(android.provider.CalendarContract.CalendarAlerts.CONTENT_URI, ((ContentValues) (obj)), "end<? AND state=?", new String[] {
                        Long.toString(l), Integer.toString(0)
                    });
                } else
                {
                    LogUtils.w("AlertServiceHelper", "Invalid action: %s", new Object[] {
                        obj
                    });
                }
                if (receivedProviderReminderBroadcast == null || !receivedProviderReminderBroadcast.booleanValue())
                {
                    LogUtils.d("AlertServiceHelper", "Scheduling next alarm with AlarmScheduler. sEventReminderReceived: %s", new Object[] {
                        receivedProviderReminderBroadcast
                    });
                    AlarmScheduler.scheduleNextAlarm(context);
                    return;
                }
            }
        }
    }

    private static void rescheduleMissedAlarms(ContentResolver contentresolver, Context context, AlarmManagerInterface alarmmanagerinterface)
    {
        long l;
        Uri uri;
        String s;
        String s1;
        String s2;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        uri = android.provider.CalendarContract.CalendarAlerts.CONTENT_URI;
        s = Long.toString(l);
        s1 = Long.toString(l - 0x5265c00L);
        s2 = Long.toString(l);
        contentresolver = contentresolver.query(uri, new String[] {
            "alarmTime"
        }, "state=0 AND alarmTime<? AND alarmTime>? AND end>=?", new String[] {
            s, s1, s2
        }, "alarmTime ASC");
        if (contentresolver == null)
        {
            return;
        }
        LogUtils.d("AlertServiceHelper", "missed alarms found: %d", new Object[] {
            Integer.valueOf(contentresolver.getCount())
        });
        l = -1L;
_L2:
        long l1;
        if (!contentresolver.moveToNext())
        {
            break; /* Loop/switch isn't completed */
        }
        l1 = contentresolver.getLong(0);
        if (l == l1)
        {
            continue; /* Loop/switch isn't completed */
        }
        LogUtils.w("AlertServiceHelper", "rescheduling missed alarm. alarmTime: %d", new Object[] {
            Long.valueOf(l1)
        });
        AlertUtils.scheduleAlarm(context, alarmmanagerinterface, l1);
        l = l1;
        if (true) goto _L2; else goto _L1
_L1:
        contentresolver.close();
        return;
        context;
        contentresolver.close();
        throw context;
    }

    private static void scheduleNextNotificationRefresh(Context context, AlarmManagerInterface alarmmanagerinterface, long l, long l1)
    {
        if (l < 0x7fffffffffffffffL && l > l1)
        {
            AlertUtils.scheduleNextNotificationRefresh(context, alarmmanagerinterface, l);
            boolean flag;
            if (LogUtils.maxEnabledLogLevel > 3)
            {
                flag = false;
            } else
            if (Log.isLoggable("AlertServiceHelper", 3))
            {
                flag = true;
            } else
            {
                flag = Log.isLoggable("AlertServiceHelper", 3);
            }
            if (flag)
            {
                l1 = (l - l1) / MINUTE;
                context = new com.google.android.calendar.time.Time();
                ((com.google.android.calendar.time.Time) (context)).impl.timezone = ((com.google.android.calendar.time.Time) (context)).timezone;
                ((com.google.android.calendar.time.Time) (context)).impl.set(l);
                ((com.google.android.calendar.time.Time) (context)).impl.toMillis(true);
                context.copyFieldsFromImpl();
                LogUtils.d("AlertServiceHelper", "Scheduling next notification refresh in %d min at: %d:%02d", new Object[] {
                    Long.valueOf(l1), Integer.valueOf(((com.google.android.calendar.time.Time) (context)).hour), Integer.valueOf(((com.google.android.calendar.time.Time) (context)).minute)
                });
            }
        } else
        if (l < l1)
        {
            LogUtils.e("AlertServiceHelper", "Illegal state: next notification refresh time found to be in the past.", new Object[0]);
            return;
        }
    }

    static void showNotification(Context context, NotificationManagerWrapper notificationmanagerwrapper, EventNotificationInfo eventnotificationinfo, int i, String s, boolean flag, boolean flag1, boolean flag2)
    {
        AlertBuilder alertbuilder = new AlertBuilder(context);
        flag1 = RtlUtils.isLayoutDirectionRtl(alertbuilder.context);
        Object obj5 = RtlUtils.forceTextAlignmentOrUseDefault(eventnotificationinfo.eventName, flag1, alertbuilder.context.getString(0x7f130358));
        String s1 = AlertUtils.formatTimeLocation(alertbuilder.context, eventnotificationinfo.startTime, eventnotificationinfo.endTimeUnspecified, eventnotificationinfo.endTime, eventnotificationinfo.allDay, eventnotificationinfo.location);
        String s5 = alertbuilder.context.getResources().getString(0x7f13035e, new Object[] {
            obj5, s1
        });
        Object obj3 = new android.support.v4.app.NotificationCompat.BigTextStyle();
        ((android.support.v4.app.NotificationCompat.BigTextStyle) (obj3)).bigText(s1);
        Object obj4 = DismissAlarmsService.createClickNotificationIntent(alertbuilder.context, eventnotificationinfo, i);
        PendingIntent pendingintent1 = DismissAlarmsService.createDismissNotificationIntent(alertbuilder.context, eventnotificationinfo, i);
        Object obj1 = alertbuilder.intentBuilder;
        Object obj = eventnotificationinfo.eventKey;
        PendingIntent pendingintent;
        Object obj6;
        int j;
        int k;
        if (((AlertActionIntentBuilder) (obj1)).createMapActivityIntentFromLegacyLocation(((EventKey) (obj))) == null)
        {
            obj = null;
        } else
        {
            Intent intent = (new Intent("com.google.android.calendar.MAP")).setClass(((AlertActionIntentBuilder) (obj1)).context, com/google/android/calendar/alerts/NotificationActionTrampoline);
            StringBuilder stringbuilder = (new StringBuilder(obj.getClass().getSimpleName())).append('|');
            ((EventKey) (obj)).serializeInternal(stringbuilder);
            intent = intent.putExtra("eventkey", stringbuilder.toString());
            obj1 = ((AlertActionIntentBuilder) (obj1)).context;
            stringbuilder = (new StringBuilder(obj.getClass().getSimpleName())).append('|');
            ((EventKey) (obj)).serializeInternal(stringbuilder);
            obj = PendingIntent.getActivity(((Context) (obj1)), stringbuilder.toString().hashCode(), intent, 0x8000000);
        }
        pendingintent = alertbuilder.intentBuilder.createMailTrampolineIntent(eventnotificationinfo.eventKey, false);
        obj1 = alertbuilder.intentBuilder;
        obj6 = eventnotificationinfo.eventKey;
        obj1 = new ContactNotificationImpl(((AlertActionIntentBuilder) (obj1)).context, ((EventKey) (obj6)));
        if (!((ContactNotification) (obj1)).isValid())
        {
            obj1 = null;
        }
        if (flag)
        {
            j = 2;
        } else
        {
            j = 0;
        }
        k = j | 4;
        obj6 = new android.support.v4.app.NotificationCompat.Builder(alertbuilder.context);
        obj6.mColor = alertbuilder.context.getResources().getColor(0x7f0d01d7);
        obj5 = ((android.support.v4.app.NotificationCompat.Builder) (obj6)).setContentTitle(((CharSequence) (obj5))).setTicker(RtlUtils.forceTextAlignment(s5, flag1)).setContentText(s1);
        ((android.support.v4.app.NotificationCompat.Builder) (obj5)).mNotification.icon = 0x7f020133;
        obj5.mContentIntent = ((PendingIntent) (obj4));
        ((android.support.v4.app.NotificationCompat.Builder) (obj5)).mNotification.deleteIntent = pendingintent1;
        ((android.support.v4.app.NotificationCompat.Builder) (obj5)).mNotification.when = 0L;
        if (flag2)
        {
            j = 2;
        } else
        {
            j = 0;
        }
        obj5.mPriority = j;
        obj5.mCategory = "event";
        obj3 = ((android.support.v4.app.NotificationCompat.Builder) (obj5)).setStyle(((android.support.v4.app.NotificationCompat.Style) (obj3)));
        if (TextUtils.isEmpty(s))
        {
            s = null;
        } else
        {
            s = Uri.parse(s);
        }
        obj3 = ((android.support.v4.app.NotificationCompat.Builder) (obj3)).setSound(s);
        ((android.support.v4.app.NotificationCompat.Builder) (obj3)).mNotification.defaults = k;
        if ((k & 4) != 0)
        {
            s = ((android.support.v4.app.NotificationCompat.Builder) (obj3)).mNotification;
            s.flags = ((Notification) (s)).flags | 1;
        }
        if (android.os.Build.VERSION.SDK_INT >= 25)
        {
            s = s1.split(System.lineSeparator());
            if (s.length > 1)
            {
                ((android.support.v4.app.NotificationCompat.Builder) (obj3)).setContentText(s[0]);
            }
        }
        obj4 = new EveryoneDeclinedAlertBuilderHelper(alertbuilder.context, eventnotificationinfo, i);
        if (((EveryoneDeclinedAlertBuilderHelper) (obj4)).showEveryoneDeclined)
        {
            String s2 = ((EveryoneDeclinedAlertBuilderHelper) (obj4)).context.getString(0x7f1301df);
            s = s2;
            if (s2 != null)
            {
                s = s2;
                if (s2.length() > 5120)
                {
                    s = s2.subSequence(0, 5120);
                }
            }
            obj3.mSubText = s;
        }
        s = alertbuilder.intentBuilder;
        if (!((EveryoneDeclinedAlertBuilderHelper) (obj4)).showEveryoneDeclined)
        {
            j = 0;
        } else
        {
            Object obj2 = new Bundle();
            ((Bundle) (obj2)).putString("everyoneDeclinedAction", "delete");
            obj2 = DismissAlarmsService.createClickNotificationIntent(((EveryoneDeclinedAlertBuilderHelper) (obj4)).context, ((EveryoneDeclinedAlertBuilderHelper) (obj4)).info, ((EveryoneDeclinedAlertBuilderHelper) (obj4)).notificationId, ((Bundle) (obj2)));
            if (((EveryoneDeclinedAlertBuilderHelper) (obj4)).isOrganizer)
            {
                String s3 = ((EveryoneDeclinedAlertBuilderHelper) (obj4)).context.getString(0x7f1301dd);
                ((android.support.v4.app.NotificationCompat.Builder) (obj3)).mActions.add(new android.support.v4.app.NotificationCompat.Action(0x7f0201df, s3, ((PendingIntent) (obj2))));
                if (((EveryoneDeclinedAlertBuilderHelper) (obj4)).showFindTime)
                {
                    s = ((EveryoneDeclinedAlertBuilderHelper) (obj4)).context.getString(0x7f1301e1);
                    obj2 = new Bundle();
                    ((Bundle) (obj2)).putString("everyoneDeclinedAction", "reschedule");
                    obj2 = DismissAlarmsService.createClickNotificationIntent(((EveryoneDeclinedAlertBuilderHelper) (obj4)).context, ((EveryoneDeclinedAlertBuilderHelper) (obj4)).info, ((EveryoneDeclinedAlertBuilderHelper) (obj4)).notificationId, ((Bundle) (obj2)));
                    ((android.support.v4.app.NotificationCompat.Builder) (obj3)).mActions.add(new android.support.v4.app.NotificationCompat.Action(0x7f020230, s, ((PendingIntent) (obj2))));
                } else
                {
                    obj2 = ((EveryoneDeclinedAlertBuilderHelper) (obj4)).context.getString(0x7f1301de);
                    s = s.createMailTrampolineIntent(((EveryoneDeclinedAlertBuilderHelper) (obj4)).info.eventKey, true);
                    ((android.support.v4.app.NotificationCompat.Builder) (obj3)).mActions.add(new android.support.v4.app.NotificationCompat.Action(0x7f0201eb, ((CharSequence) (obj2)), s));
                }
                LoggingUtils.logEveryoneDeclined(((EveryoneDeclinedAlertBuilderHelper) (obj4)).context, "shown_organizer", false, ((EveryoneDeclinedAlertBuilderHelper) (obj4)).attendees);
            } else
            {
                String s4 = ((EveryoneDeclinedAlertBuilderHelper) (obj4)).context.getString(0x7f1301dd);
                ((android.support.v4.app.NotificationCompat.Builder) (obj3)).mActions.add(new android.support.v4.app.NotificationCompat.Action(0x7f0201df, s4, ((PendingIntent) (obj2))));
                obj2 = ((EveryoneDeclinedAlertBuilderHelper) (obj4)).context.getString(0x7f1301de);
                s = s.createMailTrampolineIntent(((EveryoneDeclinedAlertBuilderHelper) (obj4)).info.eventKey, true);
                ((android.support.v4.app.NotificationCompat.Builder) (obj3)).mActions.add(new android.support.v4.app.NotificationCompat.Action(0x7f0201eb, ((CharSequence) (obj2)), s));
                LoggingUtils.logEveryoneDeclined(((EveryoneDeclinedAlertBuilderHelper) (obj4)).context, "shown_invitee", false, ((EveryoneDeclinedAlertBuilderHelper) (obj4)).attendees);
            }
            j = 2;
        }
        j += 0;
        if (obj != null && j < 2)
        {
            s = alertbuilder.context.getResources().getString(0x7f13032c);
            ((android.support.v4.app.NotificationCompat.Builder) (obj3)).mActions.add(new android.support.v4.app.NotificationCompat.Action(0x7f02020f, s, ((PendingIntent) (obj))));
            j++;
        }
        k = j;
        if (obj1 != null)
        {
            k = j;
            if (j < 2)
            {
                s = ((ContactNotification) (obj1)).createTrampolineIntent();
                k = j;
                if (s != null)
                {
                    k = ((ContactNotification) (obj1)).getIconResource();
                    obj = alertbuilder.context.getResources().getString(((ContactNotification) (obj1)).getLabelResource());
                    ((android.support.v4.app.NotificationCompat.Builder) (obj3)).mActions.add(new android.support.v4.app.NotificationCompat.Action(k, ((CharSequence) (obj)), s));
                    k = j + 1;
                }
            }
        }
        if (pendingintent != null && k < 2)
        {
            s = alertbuilder.context.getResources().getString(0x7f1301bf);
            ((android.support.v4.app.NotificationCompat.Builder) (obj3)).mActions.add(new android.support.v4.app.NotificationCompat.Action(0x7f0201eb, s, pendingintent));
        }
        s = alertbuilder.context;
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            NotificationChannels.initialize(s, NotificationChannels.channelsCreated);
            obj3.mChannelId = "REMINDERS";
        }
        s = ((android.support.v4.app.NotificationCompat.Builder) (obj3)).build();
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            ((Notification) (s)).extras.putString("UID", eventnotificationinfo.tag);
        }
        obj = new EventNotificationWrapper(s, eventnotificationinfo);
        notificationmanagerwrapper.notify(eventnotificationinfo.tag, i, ((EventNotificationWrapper) (obj)));
        notificationmanagerwrapper = AnalyticsLoggerHolder.instance;
        if (notificationmanagerwrapper == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)notificationmanagerwrapper).trackEvent(context, "notification", "create");
        context = eventnotificationinfo.eventKey;
        notificationmanagerwrapper = (new StringBuilder(context.getClass().getSimpleName())).append('|');
        context.serializeInternal(notificationmanagerwrapper);
        eventnotificationinfo = notificationmanagerwrapper.toString();
        if (((Notification) (s)).sound == null)
        {
            context = "quiet";
        } else
        {
            context = "LOUD";
        }
        if (((Notification) (s)).priority == 2)
        {
            notificationmanagerwrapper = ", high-priority";
        } else
        {
            notificationmanagerwrapper = "";
        }
        LogUtils.d("AlertServiceHelper", "Posting individual alarm notification, eventId:%s, notificationId:%s, %s%s", new Object[] {
            eventnotificationinfo, Integer.valueOf(i), context, notificationmanagerwrapper
        });
    }

    private static void showNotifications(Context context, NotificationManagerWrapper notificationmanagerwrapper, NotificationPrefs notificationprefs, int i, List list, boolean flag, boolean flag1)
    {
        Iterator iterator = list.iterator();
        do
        {
            while (iterator.hasNext()) 
            {
                EventNotificationInfo eventnotificationinfo = (EventNotificationInfo)iterator.next();
                if (eventnotificationinfo.newAlert)
                {
                    boolean flag2;
                    if (flag1 || notificationprefs.silenced)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    if (flag2)
                    {
                        list = null;
                    } else
                    {
                        if (notificationprefs.ringtone == null)
                        {
                            notificationprefs.ringtone = PreferencesUtils.getRingtonePreference(notificationprefs.context);
                        }
                        list = notificationprefs.ringtone;
                    }
                    notificationprefs.silenced = true;
                } else
                {
                    list = null;
                }
                showNotification(context, notificationmanagerwrapper, eventnotificationinfo, i, list, notificationprefs.getDefaultVibrate(), true, flag);
                i++;
            }
            return;
        } while (true);
    }

    public static void updateAlertNotification(Context context)
    {
        Object obj;
        Object obj1;
        Object obj2;
        long l2;
        obj = context.getContentResolver();
        if (Clock.mockedTimestamp > 0L)
        {
            l2 = Clock.mockedTimestamp;
        } else
        {
            l2 = System.currentTimeMillis();
        }
        obj2 = context.getSharedPreferences("com.google.android.calendar_preferences", 0);
        LogUtils.d("AlertServiceHelper", "Beginning updateAlertNotification", new Object[0]);
        obj1 = NotificationManagerWrapperImpl.getInstance(context);
        if (((SharedPreferences) (obj2)).getBoolean("preferences_alerts", true)) goto _L2; else goto _L1
_L1:
        LogUtils.d("AlertServiceHelper", "alert preference is OFF", new Object[0]);
        if (CalendarFeatureConfigDelegate.useModernNotifications == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (CalendarFeatureConfigDelegate.useModernNotifications.booleanValue())
        {
            ((NotificationManagerWrapper) (obj1)).cancelAllOc();
        } else
        {
            int i = 0;
            while (i <= 20) 
            {
                ((NotificationManagerWrapper) (obj1)).cancel(null, i);
                i++;
            }
        }
_L4:
        return;
_L2:
        if (!AndroidPermissionUtils.hasMandatoryPermissions(context))
        {
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            obj = ((ContentResolver) (obj)).query(android.provider.CalendarContract.CalendarAlerts.CONTENT_URI, ALERT_PROJECTION, "(state=? OR state=?) AND alarmTime<=?", new String[] {
                Integer.toString(1), Integer.toString(0), Long.toString(l2)
            }, "begin DESC, end DESC, alarmTime DESC");
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            LogUtils.d("AlertServiceHelper", "Error occurred when fetching alerts from cursor.", new Object[0]);
            obj = null;
        }
        if (obj != null && ((Cursor) (obj)).getCount() != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        LogUtils.d("AlertServiceHelper", "No fired or scheduled alerts", new Object[0]);
        if (CalendarFeatureConfigDelegate.useModernNotifications == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (CalendarFeatureConfigDelegate.useModernNotifications.booleanValue())
        {
            ((NotificationManagerWrapper) (obj1)).cancelAllOc();
            return;
        }
        int j = 0;
        while (j <= 20) 
        {
            ((NotificationManagerWrapper) (obj1)).cancel(null, j);
            j++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        AlarmManagerInterface alarmmanagerinterface;
        alarmmanagerinterface = AlertUtils.createAlarmManager(context);
        if (CalendarFeatureConfigDelegate.useModernNotifications == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (!CalendarFeatureConfigDelegate.useModernNotifications.booleanValue())
        {
            break; /* Loop/switch isn't completed */
        }
        LogUtils.d("AlertServiceHelper", "alertCursor count:%d", new Object[] {
            Integer.valueOf(((Cursor) (obj)).getCount())
        });
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist2 = new ArrayList();
        ArrayList arraylist4 = new ArrayList();
        boolean flag = processQuery(((Cursor) (obj)), context, l2, arraylist2, arraylist4);
        arraylist.addAll(arraylist2);
        arraylist.addAll(arraylist4);
        if (arraylist.isEmpty())
        {
            if (CalendarFeatureConfigDelegate.useModernNotifications == null)
            {
                throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
            }
            if (CalendarFeatureConfigDelegate.useModernNotifications.booleanValue())
            {
                ((NotificationManagerWrapper) (obj1)).cancelAllOc();
                return;
            }
            int k = 0;
            while (k <= 20) 
            {
                ((NotificationManagerWrapper) (obj1)).cancel(null, k);
                k++;
            }
        } else
        {
            obj = new NotificationPrefs(context, ((SharedPreferences) (obj2)));
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            showNotifications(context, ((NotificationManagerWrapper) (obj1)), ((NotificationPrefs) (obj)), -1, arraylist, true, flag);
            obj = new HashSet();
            for (obj2 = arraylist.iterator(); ((Iterator) (obj2)).hasNext(); ((Set) (obj)).add(((EventNotificationInfo)((Iterator) (obj2)).next()).tag)) { }
            obj1 = ((NotificationManagerWrapperImpl)obj1).notificationManager;
            obj2 = ((NotificationManager) (obj1)).getActiveNotifications();
            int l1 = obj2.length;
            for (int l = 0; l < l1; l++)
            {
                StatusBarNotification statusbarnotification = obj2[l];
                if (statusbarnotification.getId() == 0x20000000 && !((Set) (obj)).contains(statusbarnotification.getTag()))
                {
                    ((NotificationManager) (obj1)).cancel(statusbarnotification.getTag(), statusbarnotification.getId());
                }
            }

            scheduleNextNotificationRefresh(context, alarmmanagerinterface, calculateNextRefreshTime(context, 0x7fffffffffffffffL, l2, new com.google.android.calendar.time.Time(), arraylist), l2);
            AlertUtils.flushOldAlertsFromInternalStorage(context);
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
        ArrayList arraylist1;
        ArrayList arraylist3;
        boolean flag1;
        LogUtils.d("AlertServiceHelper", "alertCursor count:%d", new Object[] {
            Integer.valueOf(((Cursor) (obj)).getCount())
        });
        arraylist1 = new ArrayList();
        arraylist3 = new ArrayList();
        flag1 = processQuery(((Cursor) (obj)), context, l2, arraylist1, arraylist3);
        if (arraylist1.size() + arraylist3.size() != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (CalendarFeatureConfigDelegate.useModernNotifications == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (CalendarFeatureConfigDelegate.useModernNotifications.booleanValue())
        {
            ((NotificationManagerWrapper) (obj1)).cancelAllOc();
            return;
        }
        int i1 = 0;
        while (i1 <= 20) 
        {
            ((NotificationManagerWrapper) (obj1)).cancel(null, i1);
            i1++;
        }
        if (true) goto _L4; else goto _L6
_L6:
        NotificationPrefs notificationprefs = new NotificationPrefs(context, ((SharedPreferences) (obj2)));
        if (!flag1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (arraylist1.size() > 20)
        {
            obj2 = arraylist1.subList(0, arraylist1.size() - 20);
            logEventIdsBumped(arraylist3, ((List) (obj2)));
            arraylist3.clear();
            ((List) (obj2)).clear();
        }
        if (arraylist3.size() + arraylist1.size() > 20)
        {
            obj2 = arraylist3.subList(20 - arraylist1.size(), arraylist3.size());
            logEventIdsBumped(((List) (obj2)), null);
            ((List) (obj2)).clear();
        }
        obj2 = new HashSet(arraylist1.size() + arraylist3.size());
        for (Iterator iterator = Arrays.asList(new ArrayList[] {
    arraylist1, arraylist3
}).iterator(); iterator.hasNext();)
        {
            Iterator iterator1 = ((Iterable)iterator.next()).iterator();
            while (iterator1.hasNext()) 
            {
                ((Set) (obj2)).add(Long.valueOf(((CpEventKey)((EventNotificationInfo)iterator1.next()).eventKey).localId()));
            }
        }

        obj2 = new com.google.android.calendar.time.Time();
        showNotifications(context, ((NotificationManagerWrapper) (obj1)), notificationprefs, 1, arraylist1, true, flag1);
        int j1 = arraylist1.size() + 1;
        long l3 = calculateNextRefreshTime(context, 0x7fffffffffffffffL, l2, ((com.google.android.calendar.time.Time) (obj2)), arraylist1);
        showNotifications(context, ((NotificationManagerWrapper) (obj1)), notificationprefs, j1, Lists.reverse(arraylist3), false, flag1);
        int i2 = j1 + arraylist3.size();
        l3 = calculateNextRefreshTime(context, l3, l2, ((com.google.android.calendar.time.Time) (obj2)), arraylist3);
        if (i2 <= 20)
        {
            for (int k1 = i2; k1 <= 20; k1++)
            {
                ((NotificationManagerWrapper) (obj1)).cancel(null, k1);
            }

            LogUtils.d("AlertServiceHelper", "Canceling leftover notification IDs %d-%d", new Object[] {
                Integer.valueOf(i2), Integer.valueOf(20)
            });
        }
        scheduleNextNotificationRefresh(context, alarmmanagerinterface, l3, l2);
        AlertUtils.flushOldAlertsFromInternalStorage(context);
        return;
    }

    static 
    {
        MINUTE = TimeUnit.MINUTES.toMillis(1L);
        MIN_DEPRIORITIZE_GRACE_PERIOD = TimeUnit.MINUTES.toMillis(15L);
        ALL_DAY_GRACE_PERIOD = TimeUnit.HOURS.toMillis(24L);
    }

    private class EventNotificationWrapper
    {

        public final Notification notification;

        public EventNotificationWrapper(Notification notification1, EventNotificationInfo eventnotificationinfo)
        {
            notification = notification1;
        }
    }

}
