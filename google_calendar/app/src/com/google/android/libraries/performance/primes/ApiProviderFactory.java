// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesConfigurationsProvider, Supplier, PrimesThreadsConfigurations, ApiProvider

interface ApiProviderFactory
{

    public abstract ApiProvider create(Application application, PrimesConfigurationsProvider primesconfigurationsprovider, Supplier supplier, Supplier supplier1, PrimesThreadsConfigurations primesthreadsconfigurations, Supplier supplier2);
}
