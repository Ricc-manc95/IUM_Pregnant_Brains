// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.latency;


// Referenced classes of package com.google.android.calendar.latency:
//            NoopLatencyLogger, LatencyLogger

public final class LatencyLoggerHolder
{

    private static LatencyLogger latencyLogger;

    public static LatencyLogger get()
    {
        com/google/android/calendar/latency/LatencyLoggerHolder;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (latencyLogger != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_34;
        }
        throw new IllegalStateException();
        exception;
        com/google/android/calendar/latency/LatencyLoggerHolder;
        JVM INSTR monitorexit ;
        throw exception;
        LatencyLogger latencylogger = latencyLogger;
        com/google/android/calendar/latency/LatencyLoggerHolder;
        JVM INSTR monitorexit ;
        return latencylogger;
    }

    public static void set(LatencyLogger latencylogger)
    {
        com/google/android/calendar/latency/LatencyLoggerHolder;
        JVM INSTR monitorenter ;
        latencyLogger = latencylogger;
        com/google/android/calendar/latency/LatencyLoggerHolder;
        JVM INSTR monitorexit ;
        return;
        latencylogger;
        throw latencylogger;
    }

    static 
    {
        latencyLogger = NoopLatencyLogger.LOGGER;
    }
}
