// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.concurrent.ScheduledExecutorService;

public final class PrimesThreadsConfigurations
{

    public final ActivityResumedCallback activityResumedCallback;
    public final InitAfterResumedFlag initAfterResumed;
    public final ScheduledExecutorService primesExecutorService;
    public final int primesInitializationPriority;
    public final int primesMetricExecutorPoolSize;
    public final int primesMetricExecutorPriority;

    PrimesThreadsConfigurations(ScheduledExecutorService scheduledexecutorservice, int i, int j, int k, InitAfterResumedFlag initafterresumedflag, ActivityResumedCallback activityresumedcallback)
    {
        primesExecutorService = scheduledexecutorservice;
        primesInitializationPriority = i;
        primesMetricExecutorPriority = j;
        primesMetricExecutorPoolSize = k;
        initAfterResumed = initafterresumedflag;
        activityResumedCallback = activityresumedcallback;
    }
}
