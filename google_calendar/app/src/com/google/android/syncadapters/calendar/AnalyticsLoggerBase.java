// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.content.Context;
import com.google.android.apps.calendar.commonsync.analytics.api.AnalyticsLogger;

public class AnalyticsLoggerBase
{

    public final AnalyticsLogger analytics;

    public AnalyticsLoggerBase(Context context, String s, double d, AnalyticsLogger analyticslogger)
    {
        analytics = analyticslogger;
        analytics.setSampleRate(context, s, d);
    }

    public void setCustomDimension(Context context, String s, int i, String s1)
    {
        analytics.setCustomDimension(context, s, i, s1);
    }

    public void setCustomMetric(Context context, String s, int i, long l)
    {
        analytics.setCustomMetric(context, s, i, l);
    }
}
