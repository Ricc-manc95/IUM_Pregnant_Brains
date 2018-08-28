// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.primes.api;

import android.content.Context;
import com.google.android.libraries.performance.primes.TimerEvent;

public interface PerformanceMetricCollector
{

    public abstract TimerEvent initializeTimer();

    public abstract void logTime(TimerEvent timerevent, String s);

    public abstract void logTime(TimerEvent timerevent, String s, com.google.calendar.v2a.android.util.metric.MetricUtils.Result result);

    public abstract void recordMemory(String s);

    public abstract void start(Context context, int ai[], boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4);

    public abstract Thread.UncaughtExceptionHandler wrapCrashReportingIntoUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler);
}
