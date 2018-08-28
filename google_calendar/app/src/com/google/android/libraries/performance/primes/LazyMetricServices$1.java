// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            Supplier, PrimesConfigurations, PrimesGlobalConfigurations, MetricStamper

final class val.configs
    implements Supplier
{

    private final Application val$application;
    private final PrimesConfigurations val$configs;

    public final Object get()
    {
          = new ();
        .applicationContext = val$application;
        .componentNameSupplier = val$configs.globalConfigurations().componentNameSupplier;
        return MetricStamper.createMetricStamper(.applicationContext, .componentNameSupplier);
    }

    tions()
    {
        val$application = application1;
        val$configs = primesconfigurations;
        super();
    }
}
