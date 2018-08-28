// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.ClearcutLogger;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl:
//            TargetingRulePredicateImpl, TargetingClausePredicate

public final class TargetingRulePredicateImpl_Factory
    implements Factory
{

    private final Provider clearcutLoggerProvider;
    private final Provider targetingClausePredicateProvider;

    public TargetingRulePredicateImpl_Factory(Provider provider, Provider provider1)
    {
        targetingClausePredicateProvider = provider;
        clearcutLoggerProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = targetingClausePredicateProvider;
        Provider provider1 = clearcutLoggerProvider;
        return new TargetingRulePredicateImpl((TargetingClausePredicate)provider.get(), (ClearcutLogger)provider1.get());
    }
}
