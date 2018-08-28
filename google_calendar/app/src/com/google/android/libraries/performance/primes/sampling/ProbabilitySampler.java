// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.sampling;

import android.os.SystemClock;
import java.util.Random;

public final class ProbabilitySampler
{

    public final Random random;
    public final float samplingRate;

    public ProbabilitySampler(float f)
    {
        this(f, new Random(SystemClock.elapsedRealtime()));
    }

    private ProbabilitySampler(float f, Random random1)
    {
        boolean flag;
        if (f > 0.0F && f <= 1.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Sampling rate should be a floating number > 0 and <= 1."));
        } else
        {
            samplingRate = f;
            random = random1;
            return;
        }
    }
}
