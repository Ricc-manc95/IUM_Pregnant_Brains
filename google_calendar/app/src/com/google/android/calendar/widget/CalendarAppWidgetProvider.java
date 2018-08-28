// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.CalendarContract;
import android.util.Log;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.intent.IntentUtils;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.calendar.widget:
//            WidgetUpdateUtils, CalendarAppWidgetService, WidgetUtils, WidgetDataReceiver

public class CalendarAppWidgetProvider extends AppWidgetProvider
{

    public CalendarAppWidgetProvider()
    {
    }

    static PendingIntent createWidgetLifeboatPendingIntent(Context context, int i)
    {
        Intent intent = new Intent(String.valueOf(context.getPackageName()).concat(".APPWIDGET_LIFEBOAT"));
        intent.setData(Uri.parse((new StringBuilder(41)).append("widget://com.android.calendar/").append(i).toString()));
        intent.setPackage(context.getPackageName());
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    static void notifyWidgetDataChanged(Context context, AppWidgetManager appwidgetmanager, int i)
    {
        if (android.os.Build.VERSION.SDK_INT < 23)
        {
            AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
            context = createWidgetLifeboatPendingIntent(context, i);
            alarmmanager.set(3, SystemClock.elapsedRealtime() + 20000L, context);
            LogUtils.v("CalendarWidgetProvider", "Installed lifeboat for %s", new Object[] {
                Integer.valueOf(i)
            });
        }
        appwidgetmanager.notifyAppWidgetViewDataChanged(i, 0x7f1003a7);
    }

    private static void performUpdate(Context context, AppWidgetManager appwidgetmanager, int ai[])
    {
        int k = ai.length;
        int j = 0;
        while (j < k) 
        {
            int l = ai[j];
            Object obj = AppWidgetManager.getInstance(context);
            int i;
            if (obj != null)
            {
                obj = ((AppWidgetManager) (obj)).getAppWidgetOptions(l);
                if (obj != null)
                {
                    if (180 < ((Bundle) (obj)).getInt("appWidgetMinWidth"))
                    {
                        i = 0;
                    } else
                    {
                        i = 1;
                    }
                } else
                {
                    i = 1;
                }
            } else
            {
                i = 1;
            }
            WidgetUpdateUtils.performUpdate(context, appwidgetmanager, l, i, false);
            j++;
        }
    }

    private static void setMidnightAlarm(Context context)
    {
        AlarmManager alarmmanager;
        long l;
        alarmmanager = (AlarmManager)context.getSystemService("alarm");
        if (Clock.mockedTimestamp <= 0L)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        l = Clock.mockedTimestamp;
_L1:
        l = Utils.getNextMidnight(null, l, Utils.getTimeZoneId(context));
        Intent intent = new Intent("com.google.android.calendar.widget.MIDNIGHT");
        intent.setPackage(context.getPackageName());
        alarmmanager.set(1, l, PendingIntent.getBroadcast(context, 0, intent, 0));
        return;
        try
        {
            l = System.currentTimeMillis();
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return;
        }
          goto _L1
    }

    static void startQueryRefresh(Context context)
    {
        Intent intent = new Intent(String.valueOf(context.getPackageName()).concat(".APPWIDGET_REFRESH_MODEL"));
        intent.setDataAndType(CalendarContract.CONTENT_URI, "vnd.android.data/update");
        intent = (Intent)intent.clone();
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    private static void updateWidgets(Context context, AppWidgetManager appwidgetmanager, Intent intent)
    {
        int ai[] = appwidgetmanager.getAppWidgetIds(new ComponentName(context, com/android/calendar/widget/CalendarAppWidgetProvider));
        if (ai != null && ai.length > 0)
        {
            boolean flag;
            if (LogUtils.maxEnabledLogLevel > 2)
            {
                flag = false;
            } else
            if (Log.isLoggable("CalendarWidgetProvider", 2))
            {
                flag = true;
            } else
            {
                flag = Log.isLoggable("CalendarWidgetProvider", 2);
            }
            if (flag)
            {
                LogUtils.v("CalendarWidgetProvider", "Received intent: %s", new Object[] {
                    intent
                });
                LogUtils.v("CalendarWidgetProvider", "... ids=%s", new Object[] {
                    Arrays.toString(ai)
                });
            }
            performUpdate(context, appwidgetmanager, ai);
            appwidgetmanager = new Intent(String.valueOf(context.getPackageName()).concat(".APPWIDGET_REFRESH_MODEL"));
            appwidgetmanager.setDataAndType(CalendarContract.CONTENT_URI, "vnd.android.data/update");
            appwidgetmanager = (Intent)appwidgetmanager.clone();
            appwidgetmanager.setPackage(context.getPackageName());
            context.sendBroadcast(appwidgetmanager);
        }
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appwidgetmanager, int i, Bundle bundle)
    {
        LogUtils.d("CalendarWidgetProvider", "onAppWidgetOptionsChanged:%d", new Object[] {
            Integer.valueOf(i)
        });
        int j;
        if (bundle != null)
        {
            if (180 < bundle.getInt("appWidgetMinWidth"))
            {
                j = 0;
            } else
            {
                j = 1;
            }
        } else
        {
            j = 1;
        }
        WidgetUpdateUtils.performUpdate(context, appwidgetmanager, i, j, false);
        LogUtils.d("CalendarWidgetProvider", "... notifyAppWidgetViewDataChanged -> %d", new Object[] {
            Integer.valueOf(i)
        });
        notifyWidgetDataChanged(context, appwidgetmanager, i);
        super.onAppWidgetOptionsChanged(context, appwidgetmanager, i, bundle);
    }

    public void onDisabled(Context context)
    {
        Object obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)obj).trackEvent(context, "schedule_widget", "disabled");
        obj = (AlarmManager)context.getSystemService("alarm");
        Intent intent = new Intent(String.valueOf(context.getPackageName()).concat(".APPWIDGET_SCHEDULED_UPDATE"));
        intent.setDataAndType(CalendarContract.CONTENT_URI, "vnd.android.data/update");
        intent.setPackage(context.getPackageName());
        ((AlarmManager) (obj)).cancel(PendingIntent.getBroadcast(context, 0, intent, 0));
        LogUtils.d("CalendarWidgetProvider", "onDisabled Canceling alarm for getScheduledUpdateIntent", new Object[0]);
        synchronized (CalendarAppWidgetService.widgetLoaderManagerLock)
        {
            if (CalendarAppWidgetService.widgetLoaderManager != null)
            {
                CalendarAppWidgetService.widgetLoaderManager = null;
            }
        }
        obj1 = (AlarmManager)context.getSystemService("alarm");
        intent = new Intent("com.google.android.calendar.widget.MIDNIGHT");
        intent.setPackage(context.getPackageName());
        ((AlarmManager) (obj1)).cancel(PendingIntent.getBroadcast(context, 0, intent, 0));
        super.onDisabled(context);
        return;
        context;
        obj1;
        JVM INSTR monitorexit ;
        throw context;
    }

    public void onEnabled(Context context)
    {
        Object obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)obj).trackEvent(context, "schedule_widget", "enabled");
        obj = (AlarmManager)context.getSystemService("alarm");
        Object obj1 = new Intent(String.valueOf(context.getPackageName()).concat(".APPWIDGET_SCHEDULED_UPDATE"));
        ((Intent) (obj1)).setDataAndType(CalendarContract.CONTENT_URI, "vnd.android.data/update");
        ((Intent) (obj1)).setPackage(context.getPackageName());
        obj1 = PendingIntent.getBroadcast(context, 0, ((Intent) (obj1)), 0);
        ((AlarmManager) (obj)).cancel(((PendingIntent) (obj1)));
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        ((AlarmManager) (obj)).set(1, l + 0x1499700L, ((PendingIntent) (obj1)));
        LogUtils.d("CalendarWidgetProvider", "onEnabled Set alarm for getScheduledUpdateIntent", new Object[0]);
        super.onEnabled(context);
        obj = new Intent(String.valueOf(context.getPackageName()).concat(".APPWIDGET_REFRESH_MODEL"));
        ((Intent) (obj)).setDataAndType(CalendarContract.CONTENT_URI, "vnd.android.data/update");
        obj = (Intent)((Intent) (obj)).clone();
        ((Intent) (obj)).setPackage(context.getPackageName());
        context.sendBroadcast(((Intent) (obj)));
        setMidnightAlarm(context);
    }

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getAction();
        AppWidgetManager appwidgetmanager = AppWidgetManager.getInstance(context);
        boolean flag;
        if (s.equals("android.intent.action.TIME_SET") || s.equals("android.intent.action.TIMEZONE_CHANGED") || s.equals("android.intent.action.DATE_CHANGED") || s.equals("com.google.android.calendar.widget.MIDNIGHT"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            updateWidgets(context, appwidgetmanager, intent);
            setMidnightAlarm(context);
        } else
        {
            if (IntentUtils.hasProviderChanged(s) || s.equals("android.intent.action.LOCALE_CHANGED") || s.equals("com.google.android.timely.intent.action.TASK_SETTINGS_CHANGED") || s.equals(WidgetUtils.getWidgetCallerIsSyncAdapterAction(context)) || s.equals(WidgetUtils.getWidgetTaskChanged(context)) || s.equals(String.valueOf(context.getPackageName()).concat(".APPWIDGET_SCHEDULED_UPDATE")))
            {
                updateWidgets(context, appwidgetmanager, intent);
                return;
            }
            if (s.equals("android.appwidget.action.APPWIDGET_UPDATE"))
            {
                super.onReceive(context, intent);
                if (CalendarAppWidgetService.currentVersion.get() <= 0)
                {
                    intent = intent.getExtras();
                    if (intent != null)
                    {
                        intent = intent.getIntArray("appWidgetIds");
                        if (intent != null && intent.length > 0)
                        {
                            LogUtils.v("CalendarWidgetProvider", "refresh query after an install", new Object[0]);
                            intent = new Intent(String.valueOf(context.getPackageName()).concat(".APPWIDGET_REFRESH_MODEL"));
                            intent.setDataAndType(CalendarContract.CONTENT_URI, "vnd.android.data/update");
                            intent = (Intent)intent.clone();
                            intent.setPackage(context.getPackageName());
                            context.sendBroadcast(intent);
                            return;
                        }
                    }
                }
            } else
            if (s.equals(String.valueOf(context.getPackageName()).concat(".APPWIDGET_NEXT_UPDATE")))
            {
                Intent intent1;
                long l;
                long l1;
                long l2;
                if (Clock.mockedTimestamp > 0L)
                {
                    l1 = Clock.mockedTimestamp;
                } else
                {
                    l1 = System.currentTimeMillis();
                }
                l = WidgetDataReceiver.getNextMidnightTimeMillis(l1, Utils.getTimeZoneId(context));
                l2 = 0x1499700L + l1;
                if (l2 <= l)
                {
                    l = l2;
                }
                l2 = intent.getLongExtra("TriggerTime", l);
                if (l2 <= l)
                {
                    if (l2 < l1 - 10000L)
                    {
                        LogUtils.w("CalendarWidgetProvider", "Encountered bad trigger time <%s>", new Object[] {
                            WidgetUtils.formatDebugTime(l1, l2)
                        });
                    } else
                    {
                        l = l2;
                    }
                }
                intent = (AlarmManager)context.getSystemService("alarm");
                intent1 = new Intent(String.valueOf(context.getPackageName()).concat(".APPWIDGET_SCHEDULED_UPDATE"));
                intent1.setDataAndType(CalendarContract.CONTENT_URI, "vnd.android.data/update");
                intent1.setPackage(context.getPackageName());
                context = PendingIntent.getBroadcast(context, 0, intent1, 0);
                intent.cancel(context);
                if (Clock.mockedTimestamp > 0L)
                {
                    l1 = Clock.mockedTimestamp;
                } else
                {
                    l1 = System.currentTimeMillis();
                }
                l2 = l;
                if (l < l1)
                {
                    LogUtils.w("CalendarWidgetProvider", "Encountered late trigger time <%s>", new Object[] {
                        WidgetUtils.formatDebugTime(l1, l)
                    });
                    l2 = 1000L + l1;
                }
                intent.set(1, l2, context);
                LogUtils.d("CalendarWidgetProvider", "... Set alarm for getScheduledUpdateIntent at:%s", new Object[] {
                    WidgetUtils.formatDebugTime(l2, l1)
                });
                return;
            } else
            {
                super.onReceive(context, intent);
                return;
            }
        }
    }

    public void onUpdate(Context context, AppWidgetManager appwidgetmanager, int ai[])
    {
        LogUtils.d("CalendarWidgetProvider", "onUpdate", new Object[0]);
        performUpdate(context, appwidgetmanager, ai);
    }
}
