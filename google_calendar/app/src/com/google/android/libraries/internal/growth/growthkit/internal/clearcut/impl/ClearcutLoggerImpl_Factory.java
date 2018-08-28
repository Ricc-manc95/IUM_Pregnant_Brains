// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.clearcut.impl;

import android.content.Context;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.Counter2;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.clearcut.impl:
//            ClearcutLoggerImpl

public final class ClearcutLoggerImpl_Factory
    implements Factory
{

    private final Provider appPackageNameProvider;
    private final Provider clearcutLoggerProvider;
    private final Provider contextProvider;
    private final Provider impressionsCounterProvider;
    private final Provider streamzIncrementsProvider;

    public ClearcutLoggerImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4)
    {
        clearcutLoggerProvider = provider;
        appPackageNameProvider = provider1;
        contextProvider = provider2;
        streamzIncrementsProvider = provider3;
        impressionsCounterProvider = provider4;
    }

    public final Object get()
    {
        Provider provider = clearcutLoggerProvider;
        Provider provider1 = appPackageNameProvider;
        Provider provider2 = contextProvider;
        Provider provider3 = streamzIncrementsProvider;
        Provider provider4 = impressionsCounterProvider;
        return new ClearcutLoggerImpl((PerAccountProvider)provider.get(), (String)provider1.get(), (Context)provider2.get(), (StreamzIncrements)provider3.get(), (Counter2)provider4.get());
    }
}
