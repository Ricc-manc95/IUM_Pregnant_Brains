// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            BatteryMetricService

final class arg._cls4
    implements Callable
{

    private final BatteryMetricService arg$1;
    private final int arg$2;
    private final String arg$3;
    private final boolean arg$4;

    public final Object call()
    {
        return arg$1.captureBattery(arg$2, arg$3, arg$4);
    }

    (BatteryMetricService batterymetricservice, int i, String s, boolean flag)
    {
        arg$1 = batterymetricservice;
        arg$2 = i;
        arg$3 = s;
        arg$4 = flag;
    }
}
