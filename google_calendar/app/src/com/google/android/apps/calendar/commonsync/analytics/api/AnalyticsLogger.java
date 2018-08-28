// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.commonsync.analytics.api;

import android.content.Context;

public interface AnalyticsLogger
{

    public abstract void setCustomDimension(Context context, String s, int i, String s1);

    public abstract void setCustomMetric(Context context, String s, int i, long l);

    public abstract void setSampleRate(Context context, String s, double d);

    public abstract void trackEvent(Context context, String s, String s1, String s2, String s3, Long long1);

    public abstract void trackScreenView(Context context, String s, String s1);

    public abstract void trackTiming(Context context, String s, String s1, long l, String s2, String s3);
}
