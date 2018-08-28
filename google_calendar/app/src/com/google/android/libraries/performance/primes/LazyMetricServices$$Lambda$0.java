// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            Supplier, PrimesConfigurations

final class arg._cls1
    implements Supplier
{

    private final PrimesConfigurations arg$1;

    public final Object get()
    {
        return arg$1.metricTransmitter();
    }

    (PrimesConfigurations primesconfigurations)
    {
        arg$1 = primesconfigurations;
    }
}
