// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.analytics;


// Referenced classes of package com.google.android.calendar.analytics:
//            AnalyticsLogger

public class AnalyticsLoggerHolder
{

    public static volatile AnalyticsLogger instance;

    public AnalyticsLoggerHolder()
    {
    }

    public static void set(AnalyticsLogger analyticslogger)
    {
        if (instance != null)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        instance = analyticslogger;
        com/google/android/calendar/analytics/AnalyticsLoggerHolder;
        JVM INSTR monitorenter ;
        if (instance == null)
        {
            instance = analyticslogger;
        }
        com/google/android/calendar/analytics/AnalyticsLoggerHolder;
        JVM INSTR monitorexit ;
        return;
        analyticslogger;
        com/google/android/calendar/analytics/AnalyticsLoggerHolder;
        JVM INSTR monitorexit ;
        throw analyticslogger;
    }
}
