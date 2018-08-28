// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.format.DateUtils;
import android.widget.RemoteViews;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;

// Referenced classes of package com.google.android.calendar.widget:
//            CalendarAppWidgetService

class WidgetUpdateUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/widget/WidgetUpdateUtils);

    WidgetUpdateUtils()
    {
    }

    static void performUpdate(Context context, AppWidgetManager appwidgetmanager, int i, int j, boolean flag)
    {
        LogUtils.d(TAG, "Building widget %d update...", new Object[] {
            Integer.valueOf(i)
        });
        RemoteViews remoteviews = new RemoteViews(context.getPackageName(), 0x7f050193);
        LogUtils.v(TAG, "... update events_list", new Object[0]);
        Object obj1 = new Intent(context, com/google/android/calendar/widget/CalendarAppWidgetService);
        Uri uri = Uri.parse(((Intent) (obj1)).toUri(1));
        Object obj;
        int k;
        int l;
        int i1;
        int j1;
        long l1;
        if (flag)
        {
            obj = "vnd.android.data/lifeboat";
        } else
        {
            obj = null;
        }
        ((Intent) (obj1)).setDataAndType(uri, ((String) (obj)));
        ((Intent) (obj1)).putExtra("appWidgetId", i);
        LogUtils.v(TAG, "updateIntent = %s", new Object[] {
            obj1
        });
        remoteviews.setRemoteAdapter(0x7f1003a7, ((Intent) (obj1)));
        obj = new Intent();
        ((Intent) (obj)).setAction("android.intent.action.VIEW");
        ((Intent) (obj)).setPackage(context.getPackageName());
        ((Intent) (obj)).setFlags(0x1000c000);
        ((Intent) (obj)).putExtra("intent_source", "widget");
        remoteviews.setPendingIntentTemplate(0x7f1003a7, PendingIntent.getActivity(context, 0, ((Intent) (obj)), 0x8000000));
        LogUtils.v(TAG, "... updateHeader", new Object[0]);
        obj = new Time(Utils.getTimeZoneId(context));
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        ((Time) (obj)).set(l1);
        ((Time) (obj)).writeFieldsToImpl();
        l1 = ((Time) (obj)).impl.toMillis(true);
        if (j == 0)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0)
        {
            j = 0x7f1002a2;
        } else
        {
            j = 0x7f1003a4;
        }
        if (k != 0)
        {
            i1 = 0x7f1003a2;
        } else
        {
            i1 = 0x7f1003a5;
        }
        if (k != 0)
        {
            l = 0x7f1003a3;
        } else
        {
            l = 0x7f1003a6;
        }
        if (k != 0)
        {
            j1 = 0x7f1003a4;
        } else
        {
            j1 = 0x7f1002a2;
        }
        remoteviews.setViewVisibility(j, 0);
        remoteviews.setViewVisibility(j1, 8);
        remoteviews.setTextViewText(i1, DateUtils.getDayOfWeekString(((Time) (obj)).weekDay + 1, 20));
        j1 = PreferencesConstants.getAlternateCalendarPref(context);
        i1 = 16;
        if (k == 0)
        {
            i1 = 0x10010;
        }
        obj1 = new StringBuilder(Utils.tZUtils.formatDateRange(context, l1, l1, i1));
        if (k != 0 && j1 != 0)
        {
            ((StringBuilder) (obj1)).append(" (").append(AlternateCalendarUtils.getAlternateMonthDayString(Utils.getJulianDay(((Time) (obj)).year, ((Time) (obj)).month, ((Time) (obj)).monthDay), context.getResources(), j1)).append(")");
        }
        remoteviews.setTextViewText(l, ((CharSequence) (obj1)));
        obj = context.getResources();
        if (j1 == 0)
        {
            k = 0x7f0e03fb;
        } else
        {
            k = 0x7f0e03fc;
        }
        remoteviews.setTextViewTextSize(l, 0, ((Resources) (obj)).getDimensionPixelSize(k));
        obj = new Intent("android.intent.action.VIEW");
        ((Intent) (obj)).setPackage(context.getPackageName());
        obj1 = String.valueOf(CalendarContract.CONTENT_URI);
        obj1 = (new StringBuilder(String.valueOf(obj1).length() + 6)).append(((String) (obj1))).append("/time/").toString();
        ((Intent) (obj)).setData(Uri.parse((new StringBuilder(String.valueOf(obj1).length() + 20)).append(((String) (obj1))).append(l1).toString()));
        ((Intent) (obj)).putExtra("intent_source", "widget");
        remoteviews.setOnClickPendingIntent(j, PendingIntent.getActivity(context, 0, ((Intent) (obj)), 0));
        LogUtils.v(TAG, "... updateAppWidget", new Object[0]);
        appwidgetmanager.updateAppWidget(i, remoteviews);
    }

}
