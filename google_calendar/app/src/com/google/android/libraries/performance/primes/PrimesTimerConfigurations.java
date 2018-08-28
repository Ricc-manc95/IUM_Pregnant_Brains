// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesPerEventConfigurationFlags

public final class PrimesTimerConfigurations
{

    public static final PrimesTimerConfigurations DEFAULT = new PrimesTimerConfigurations(false);
    private static final PrimesPerEventConfigurationFlags DEFAULT_PER_EVENT_CONFIG = new _cls1();
    public final boolean enabled;
    public final PrimesPerEventConfigurationFlags perEventConfigFlags;
    public final int sampleRatePerSecond;

    public PrimesTimerConfigurations()
    {
        this(false);
    }

    private PrimesTimerConfigurations(boolean flag)
    {
        this(false, 10);
    }

    public PrimesTimerConfigurations(boolean flag, int i)
    {
        this(flag, i, DEFAULT_PER_EVENT_CONFIG);
    }

    private PrimesTimerConfigurations(boolean flag, int i, PrimesPerEventConfigurationFlags primespereventconfigurationflags)
    {
        enabled = flag;
        sampleRatePerSecond = i;
        if (primespereventconfigurationflags == null)
        {
            primespereventconfigurationflags = DEFAULT_PER_EVENT_CONFIG;
        }
        perEventConfigFlags = primespereventconfigurationflags;
    }


    private class _cls1
        implements PrimesPerEventConfigurationFlags
    {

        public final boolean isFlagEnabled$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9B8______0()
        {
            return true;
        }

        _cls1()
        {
        }
    }

}
