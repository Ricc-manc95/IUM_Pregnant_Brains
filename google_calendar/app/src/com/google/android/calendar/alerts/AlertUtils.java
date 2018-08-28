// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.notification.NotificationPrefs;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.alerts:
//            AlertReceiver, AlarmManagerInterface

public final class AlertUtils
{

    private static final String EXTENDED_PROPERTIES_PROJECTION[] = {
        "name", "value"
    };

    public static AlarmManagerInterface createAlarmManager(Context context)
    {
        return new _cls1();
    }

    static void flushOldAlertsFromInternalStorage(Context context)
    {
        Object obj;
        long l;
label0:
        {
            obj = context.getSharedPreferences("calendar_alerts", 0);
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            if (l - ((SharedPreferences) (obj)).getLong("preference_flushTimeMs", 0L) > 0x5265c00L)
            {
                LogUtils.d("AlertUtils", "Flushing old alerts from shared prefs table", new Object[0]);
                context = ((SharedPreferences) (obj)).edit();
                context.putLong("preference_flushTimeMs", l);
                if (((SharedPreferences) (obj)).getAll() != null && !((SharedPreferences) (obj)).getAll().entrySet().isEmpty())
                {
                    break label0;
                }
                context.apply();
            }
            return;
        }
        Time time = new Time();
        obj = ((SharedPreferences) (obj)).getAll().entrySet().iterator();
        do
        {
            if (((Iterator) (obj)).hasNext())
            {
                Object obj1 = (java.util.Map.Entry)((Iterator) (obj)).next();
                String s = (String)((java.util.Map.Entry) (obj1)).getKey();
                obj1 = ((java.util.Map.Entry) (obj1)).getValue();
                if (s.startsWith("preference_alert_"))
                {
                    if (obj1 instanceof Long)
                    {
                        long l1 = ((Long)obj1).longValue();
                        if (l - l1 >= 0x5265c00L)
                        {
                            context.remove(s);
                            boolean flag;
                            if (LogUtils.maxEnabledLogLevel > 3)
                            {
                                flag = false;
                            } else
                            if (Log.isLoggable("AlertUtils", 3))
                            {
                                flag = true;
                            } else
                            {
                                flag = Log.isLoggable("AlertUtils", 3);
                            }
                            if (flag)
                            {
                                LogUtils.d("AlertUtils", "SharedPrefs key %s: removed (%d days old)", new Object[] {
                                    s, Integer.valueOf(getIntervalInDays(l1, l, time))
                                });
                            }
                        } else
                        {
                            boolean flag1;
                            if (LogUtils.maxEnabledLogLevel > 3)
                            {
                                flag1 = false;
                            } else
                            if (Log.isLoggable("AlertUtils", 3))
                            {
                                flag1 = true;
                            } else
                            {
                                flag1 = Log.isLoggable("AlertUtils", 3);
                            }
                            if (flag1)
                            {
                                LogUtils.d("AlertUtils", "SharedPrefs key %s: keep (%d days old)", new Object[] {
                                    s, Integer.valueOf(getIntervalInDays(l1, l, time))
                                });
                            }
                        }
                    } else
                    {
                        LogUtils.e("AlertUtils", "SharedPrefs key %s did not have Long value: %s", new Object[] {
                            s, obj1
                        });
                    }
                }
            } else
            {
                context.apply();
                return;
            }
        } while (true);
    }

    static String formatTimeLocation(Context context, long l, boolean flag, long l1, boolean flag1, String s)
    {
        String s1 = Utils.getTimeZoneId(context, null);
        StringBuilder stringbuilder;
        Object obj;
        StringBuilder stringbuilder1;
        long l2;
        if (Clock.mockedTimestamp > 0L)
        {
            l2 = Clock.mockedTimestamp;
        } else
        {
            l2 = System.currentTimeMillis();
        }
        obj = context.getResources();
        if (flag)
        {
            l1 = l;
        }
        stringbuilder = new StringBuilder();
        stringbuilder1 = new StringBuilder();
        if (Utils.getDisplayedDateTimes(l, l1, l2, s1, flag1, 7, context, stringbuilder, stringbuilder1))
        {
            stringbuilder.append(((Resources) (obj)).getString(0x7f130151)).append(stringbuilder1);
        } else
        if (stringbuilder1.length() > 0)
        {
            stringbuilder.append(((Resources) (obj)).getString(0x7f13014d)).append(stringbuilder1);
        }
        if (!flag1 && !s1.equals(android.text.format.Time.getCurrentTimezone()))
        {
            obj = new Time(s1);
            ((Time) (obj)).impl.timezone = ((Time) (obj)).timezone;
            ((Time) (obj)).impl.set(l);
            ((Time) (obj)).impl.toMillis(true);
            ((Time) (obj)).copyFieldsFromImpl();
            if (((Time) (obj)).isDst != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            stringbuilder.append(" ").append(TimeZone.getTimeZone(s1).getDisplayName(flag, 0, Locale.getDefault()));
        }
        flag = RtlUtils.isLayoutDirectionRtl(context);
        context = new StringBuilder(RtlUtils.forceTextAlignment(stringbuilder, flag));
        if (s != null)
        {
            s = s.trim();
            if (!TextUtils.isEmpty(s))
            {
                context.append('\n').append(RtlUtils.forceTextAlignment(s, flag));
            }
        }
        return context.toString();
    }

    public static String getExtendedProperty(Context context, long l, String s)
    {
        Cursor cursor;
        Object obj = null;
        cursor = context.getContentResolver().query(android.provider.CalendarContract.ExtendedProperties.CONTENT_URI, EXTENDED_PROPERTIES_PROJECTION, "event_id=?", new String[] {
            Long.toString(l)
        }, null);
        context = obj;
        if (cursor == null) goto _L2; else goto _L1
_L1:
        if (!cursor.moveToFirst()) goto _L4; else goto _L3
_L3:
        context = cursor.getString(0);
        if (context == null) goto _L6; else goto _L5
_L5:
        if (!context.equals(s)) goto _L6; else goto _L7
_L7:
        context = cursor.getString(1);
        cursor.close();
_L2:
        return context;
_L6:
        boolean flag = cursor.moveToNext();
        if (flag) goto _L3; else goto _L4
_L4:
        cursor.close();
        return null;
        context;
        cursor.close();
        throw context;
    }

    private static String getFiredAlertsKey(long l, long l1, long l2)
    {
        return (new StringBuilder(79)).append("preference_alert_").append(l).append("_").append(l1).append("_").append(l2).toString();
    }

    public static SharedPreferences getFiredAlertsTable(Context context)
    {
        return context.getSharedPreferences("calendar_alerts", 0);
    }

    private static int getIntervalInDays(long l, long l1, Time time)
    {
        time.impl.timezone = time.timezone;
        time.impl.set(l);
        time.impl.toMillis(true);
        time.copyFieldsFromImpl();
        int i = android.text.format.Time.getJulianDay(l, time.gmtoff);
        time.impl.timezone = time.timezone;
        time.impl.set(l1);
        time.impl.toMillis(true);
        time.copyFieldsFromImpl();
        return android.text.format.Time.getJulianDay(l1, time.gmtoff) - i;
    }

    public static int getNotificationDefaults(NotificationPrefs notificationprefs)
    {
        byte byte0 = 4;
        if (notificationprefs.getDefaultVibrate())
        {
            byte0 = 6;
        }
        return byte0;
    }

    static boolean hasAlertFiredInSharedPrefs(Context context, long l, long l1, long l2)
    {
        return context.getSharedPreferences("calendar_alerts", 0).contains(getFiredAlertsKey(l, l1, l2));
    }

    public static void scheduleAlarm(Context context, AlarmManagerInterface alarmmanagerinterface, long l)
    {
        scheduleAlarmHelper(context, alarmmanagerinterface, l, false);
    }

    private static void scheduleAlarmHelper(Context context, AlarmManagerInterface alarmmanagerinterface, long l, boolean flag)
    {
        Intent intent = new Intent("com.google.android.calendar.EVENT_REMINDER_APP");
        intent.setClass(context, com/google/android/calendar/alerts/AlertReceiver);
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            android.net.Uri.Builder builder = android.provider.CalendarContract.CalendarAlerts.CONTENT_URI.buildUpon();
            ContentUris.appendId(builder, l);
            intent.setData(builder.build());
            i = 0;
        }
        intent.putExtra("alarmTime", l);
        context = PendingIntent.getBroadcast(context, 0, intent, 0x8000000);
        if (flag)
        {
            alarmmanagerinterface.setExact(i, l, context);
            return;
        } else
        {
            alarmmanagerinterface.setExactAndAllowWhileIdle(i, l, context);
            return;
        }
    }

    static void scheduleNextNotificationRefresh(Context context, AlarmManagerInterface alarmmanagerinterface, long l)
    {
        scheduleAlarmHelper(context, alarmmanagerinterface, l, true);
    }

    static void setAlertFiredInSharedPrefs(Context context, long l, long l1, long l2)
    {
        context = context.getSharedPreferences("calendar_alerts", 0).edit();
        context.putLong(getFiredAlertsKey(l, l1, l2), l2);
        context.apply();
    }


    private class _cls1
        implements AlarmManagerInterface
    {

        private final AlarmManager val$mgr;

        public final void cancel(PendingIntent pendingintent)
        {
            mgr.cancel(pendingintent);
        }

        public final void setExact(int i, long l, PendingIntent pendingintent)
        {
            mgr.setExact(i, l, pendingintent);
        }

        public final void setExactAndAllowWhileIdle(int i, long l, PendingIntent pendingintent)
        {
            AlarmManager alarmmanager = mgr;
            if (MncUtil.isMnc())
            {
                alarmmanager.setExactAndAllowWhileIdle(i, l, pendingintent);
                return;
            } else
            {
                alarmmanager.setExact(i, l, pendingintent);
                return;
            }
        }

        _cls1()
        {
            mgr = alarmmanager;
            super();
        }
    }

}
