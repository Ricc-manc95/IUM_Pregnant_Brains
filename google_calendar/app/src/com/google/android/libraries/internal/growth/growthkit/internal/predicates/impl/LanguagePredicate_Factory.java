// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl:
//            LanguagePredicate

public final class LanguagePredicate_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider sharedPrefsFutureProvider;

    public LanguagePredicate_Factory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        sharedPrefsFutureProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = sharedPrefsFutureProvider;
        return new LanguagePredicate((Context)provider.get(), (ListenableFuture)provider1.get());
    }
}
