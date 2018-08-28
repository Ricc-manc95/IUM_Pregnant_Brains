// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.calendar.utils.version.NycUtils;

public class CalendarSupportActivity extends AppCompatActivity
{

    public CalendarSupportActivity()
    {
        com.google.calendar.v2a.android.util.metric.MetricUtils.OneStepMeasurements onestepmeasurements = com.google.calendar.v2a.android.util.metric.MetricUtils.OneStepMeasurements.ACTIVITY_INIT;
        if (onestepmeasurements.action != null)
        {
            onestepmeasurements.action.run();
        }
    }

    protected void attachBaseContext(Context context)
    {
        super.attachBaseContext(context);
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displaymetrics = new DisplayMetrics();
            display.getMetrics(displaymetrics);
            int i = Math.max(displaymetrics.heightPixels, displaymetrics.widthPixels);
            display.getRealMetrics(displaymetrics);
            int j = Math.max(displaymetrics.heightPixels, displaymetrics.widthPixels);
            boolean flag;
            if ((float)i < (float)j * 0.8F)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                context = null;
            } else
            {
                Configuration configuration = new Configuration();
                if (NycUtils.isDeviceTablet(context))
                {
                    configuration.smallestScreenWidthDp = 600;
                }
                configuration.orientation = 1;
                context = configuration;
            }
            if (context != null)
            {
                applyOverrideConfiguration(context);
            }
        }
    }
}
