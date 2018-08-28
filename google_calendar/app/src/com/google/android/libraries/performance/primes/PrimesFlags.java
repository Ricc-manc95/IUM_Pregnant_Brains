// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


public final class PrimesFlags
{

    public final boolean batteryExperimentEnabled;
    public final boolean deferredStartupLoggingEnabled;
    public final boolean leakDetectionEnabled;
    public final boolean leakDetectionV2Enabled;
    public final boolean magicEyeLogEnabled;
    public final boolean memorySummaryDisabled;
    public final boolean persistCrashStatsEnabled;
    public final boolean primesForPrimesEnabled;
    public final boolean primesTraceEnabled;
    public final boolean startupTraceEnabled;
    public final boolean urlAutoSanitizationEnabled;

    PrimesFlags(boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, boolean flag5, boolean flag6, 
            boolean flag7, boolean flag8, boolean flag9, boolean flag10)
    {
        leakDetectionEnabled = flag;
        leakDetectionV2Enabled = flag1;
        memorySummaryDisabled = flag2;
        batteryExperimentEnabled = flag3;
        magicEyeLogEnabled = flag4;
        persistCrashStatsEnabled = flag5;
        startupTraceEnabled = flag6;
        urlAutoSanitizationEnabled = flag7;
        primesForPrimesEnabled = flag8;
        primesTraceEnabled = flag9;
        deferredStartupLoggingEnabled = flag10;
    }
}
