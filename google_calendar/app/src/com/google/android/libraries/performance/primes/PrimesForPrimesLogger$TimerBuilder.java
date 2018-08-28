// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.ArrayList;
import logs.proto.wireless.performance.mobile.nano.MicroCpuTime;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            CpuWallTime

final class 
{

    public final ArrayList timers = new ArrayList();

    final  addTimer(int i, CpuWallTime cpuwalltime, CpuWallTime cpuwalltime1)
    {
        if (cpuwalltime != null && cpuwalltime1 != null)
        {
            cpuwalltime = new CpuWallTime(cpuwalltime1.wallNanos - cpuwalltime.wallNanos, cpuwalltime1.cpuNanos - cpuwalltime.cpuNanos);
            cpuwalltime1 = new logs.proto.wireless.performance.mobile.nano.>();
            cpuwalltime1.ion = new MicroCpuTime();
            ((logs.proto.wireless.performance.mobile.nano.ion) (cpuwalltime1)).ion.cpuMicros = Long.valueOf(cpuwalltime.cpuNanos / 1000L);
            ((logs.proto.wireless.performance.mobile.nano.ion) (cpuwalltime1)).ion.wallMicros = Long.valueOf(cpuwalltime.wallNanos / 1000L);
            cpuwalltime1.sForPrimesEvent = i;
            timers.add(cpuwalltime1);
        }
        return this;
    }

    ()
    {
    }
}
