// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


public final class PrimesCpuConfigurations
{

    public static final PrimesCpuConfigurations DEFAULT = new PrimesCpuConfigurations(false);
    public final boolean enabled;
    public final int initialDelay;
    public final int numSamples;
    public final int timeBetweenSamples;

    private PrimesCpuConfigurations(boolean flag)
    {
        this(false, 5, 15000, 2000);
    }

    private PrimesCpuConfigurations(boolean flag, int i, int j, int k)
    {
        enabled = flag;
        numSamples = 5;
        initialDelay = 15000;
        timeBetweenSamples = 2000;
    }

}
