// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.primes;

import android.content.Context;
import com.google.android.libraries.performance.primes.PrimesConfigurations;
import com.google.android.libraries.performance.primes.PrimesConfigurationsProvider;
import com.google.android.libraries.performance.primes.PrimesCrashConfigurations;
import com.google.android.libraries.performance.primes.PrimesMemoryConfigurations;
import com.google.android.libraries.performance.primes.PrimesPackageConfigurations;
import com.google.android.libraries.performance.primes.PrimesTimerConfigurations;

// Referenced classes of package com.google.android.apps.calendar.primes:
//            PerformanceMetricCollectorImpl

final class arg._cls6
    implements PrimesConfigurationsProvider
{

    private final PerformanceMetricCollectorImpl arg$1;
    private final Context arg$2;
    private final PrimesMemoryConfigurations arg$3;
    private final boolean arg$4;
    private final boolean arg$5;
    private final boolean arg$6;

    public final PrimesConfigurations get()
    {
        Object obj = arg$1;
        obj = arg$2;
        PrimesMemoryConfigurations primesmemoryconfigurations = arg$3;
        boolean flag = arg$4;
        boolean flag1 = arg$5;
        boolean flag2 = arg$6;
        com.google.android.libraries.performance.primes.mesMemoh mesmemoh = new com.google.android.libraries.performance.primes.mesMemoh();
        mesmemoh.itter = new icTransmitter(((Context) (obj)));
        mesmemoh.s = primesmemoryconfigurations;
        mesmemoh. = new PrimesTimerConfigurations(flag, 20);
        mesmemoh. = new PrimesCrashConfigurations(flag1);
        mesmemoh.gs = new PrimesPackageConfigurations(flag2);
        obj = new com.google.android.libraries.performance.primes.gs(mesmemoh.itter, null, mesmemoh.s, mesmemoh., mesmemoh., null, mesmemoh.gs, null, null, null, null, null, null, null, null);
        if (obj instanceof com.google.android.libraries.performance.primes.gs)
        {
            return ((PrimesConfigurations) (obj));
        } else
        {
            return new com.google.android.libraries.performance.primes.gs(((PrimesConfigurations) (obj)));
        }
    }

    (PerformanceMetricCollectorImpl performancemetriccollectorimpl, Context context, PrimesMemoryConfigurations primesmemoryconfigurations, boolean flag, boolean flag1, boolean flag2)
    {
        arg$1 = performancemetriccollectorimpl;
        arg$2 = context;
        arg$3 = primesmemoryconfigurations;
        arg$4 = flag;
        arg$5 = flag1;
        arg$6 = flag2;
    }
}
