// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


public final class PrimesTikTokTraceConfigurations
{

    public static final PrimesTikTokTraceConfigurations DEFAULT;
    public final boolean isEnabled;
    public final int sampleRatePerSecond;

    PrimesTikTokTraceConfigurations(boolean flag, int i, int j)
    {
        isEnabled = flag;
        sampleRatePerSecond = i;
    }

    static 
    {
        Builder builder = new Builder();
        builder.sampleRatePerSecond = 10;
        builder.sampleRatePerThousand = 1000;
        builder.isEnabled = false;
        DEFAULT = new PrimesTikTokTraceConfigurations(builder.isEnabled, builder.sampleRatePerSecond, builder.sampleRatePerThousand);
    }

    private class Builder
    {

        public boolean isEnabled;
        public int sampleRatePerSecond;
        public int sampleRatePerThousand;

        Builder()
        {
        }
    }

}
