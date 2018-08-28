// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.widget:
//            WidgetUpdateUtils

public class CalendarAppWidgeLifeboatReceiver extends BroadcastReceiver
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/widget/CalendarAppWidgeLifeboatReceiver);

    public CalendarAppWidgeLifeboatReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        int i = 0;
        String s = intent.getAction();
        AppWidgetManager appwidgetmanager = AppWidgetManager.getInstance(context);
        if (!String.valueOf(context.getPackageName()).concat(".APPWIDGET_LIFEBOAT").equals(s))
        {
            LogUtils.e(TAG, "Unhandled action. Expexted %s, got %s", new Object[] {
                String.valueOf(context.getPackageName()).concat(".APPWIDGET_LIFEBOAT"), s
            });
            return;
        }
        LogUtils.w(TAG, "Received lifeboat alarm: %s", new Object[] {
            intent.getData().getLastPathSegment()
        });
        int j = Integer.parseInt(intent.getData().getLastPathSegment());
        intent = AppWidgetManager.getInstance(context);
        if (intent != null)
        {
            intent = intent.getAppWidgetOptions(j);
            if (intent != null)
            {
                if (180 >= intent.getInt("appWidgetMinWidth"))
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
        WidgetUpdateUtils.performUpdate(context, appwidgetmanager, j, i, true);
    }

}
