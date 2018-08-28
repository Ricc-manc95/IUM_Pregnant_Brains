// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.commonsync.analytics.noop;

import android.content.Context;
import com.google.android.apps.calendar.commonsync.analytics.api.AnalyticsLogger;

public final class NoopAnalyticsLogger
    implements AnalyticsLogger
{

    public NoopAnalyticsLogger()
    {
    }

    public final void setCustomDimension(Context context, String s, int i, String s1)
    {
    }

    public final void setCustomMetric(Context context, String s, int i, long l)
    {
    }

    public final void setSampleRate(Context context, String s, double d)
    {
    }

    public final void trackEvent(Context context, String s, String s1, String s2, String s3, Long long1)
    {
    }

    public final void trackScreenView(Context context, String s, String s1)
    {
    }

    public final void trackTiming(Context context, String s, String s1, long l, String s2, String s3)
    {
    }
}
