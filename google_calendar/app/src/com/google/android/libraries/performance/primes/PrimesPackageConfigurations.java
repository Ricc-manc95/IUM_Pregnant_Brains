// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesDirStatsConfigurations

public final class PrimesPackageConfigurations
{

    public static final PrimesPackageConfigurations DEFAULT = new PrimesPackageConfigurations(false);
    public final PrimesDirStatsConfigurations dirStatsConfigs;
    public final boolean enabled;
    public final boolean manualCapture;

    public PrimesPackageConfigurations(boolean flag)
    {
        this(flag, false);
    }

    private PrimesPackageConfigurations(boolean flag, boolean flag1)
    {
        this(flag, false, PrimesDirStatsConfigurations.DEFAULT);
    }

    private PrimesPackageConfigurations(boolean flag, boolean flag1, PrimesDirStatsConfigurations primesdirstatsconfigurations)
    {
        enabled = flag;
        manualCapture = flag1;
        dirStatsConfigs = primesdirstatsconfigurations;
    }

}
