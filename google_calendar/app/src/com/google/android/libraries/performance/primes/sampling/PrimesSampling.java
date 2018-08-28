// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.sampling;

import android.os.SystemClock;

public final class PrimesSampling
{

    public long firstSampleInLastSecond;
    private final int maxSamplesRate;
    public final Object mutex = new Object();
    public int samplesCount;

    public PrimesSampling(int i)
    {
        samplesCount = 0;
        firstSampleInLastSecond = 0L;
        maxSamplesRate = i;
    }

    public final boolean isSampleRateExceeded()
    {
label0:
        {
            synchronized (mutex)
            {
                if (SystemClock.elapsedRealtime() - firstSampleInLastSecond <= 1000L)
                {
                    break label0;
                }
            }
            return false;
        }
        if (samplesCount >= maxSamplesRate)
        {
            return true;
        }
        break MISSING_BLOCK_LABEL_46;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
        return false;
    }
}
