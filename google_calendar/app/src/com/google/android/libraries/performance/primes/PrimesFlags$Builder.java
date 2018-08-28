// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesFlags

public final class startupTraceEnable
{

    public boolean batteryExperimentEnable;
    public boolean leakDetectionEnable;
    public boolean leakDetectionV2Enable;
    public boolean magicEyeLogEnable;
    public boolean memorySummaryDisable;
    public boolean persistCrashStatsEnable;
    public boolean primesForPrimesEnabled;
    public boolean primesTraceEnabled;
    public boolean startupTraceEnable;
    public boolean urlAutoSanitizationEnable;

    public final PrimesFlags build()
    {
        return new PrimesFlags(leakDetectionEnable, leakDetectionV2Enable, memorySummaryDisable, batteryExperimentEnable, magicEyeLogEnable, persistCrashStatsEnable, startupTraceEnable, urlAutoSanitizationEnable, primesForPrimesEnabled, primesTraceEnabled, false);
    }

    public I()
    {
        startupTraceEnable = true;
    }
}
