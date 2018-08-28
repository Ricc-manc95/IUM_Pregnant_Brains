// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl:
//            TargetingClausePredicate, EventCountTargetingTermPredicate, AppStateTargetingTermPredicate

public final class TargetingClausePredicate_Factory
    implements Factory
{

    private final Provider appStateTargetingTermPredicateProvider;
    private final Provider eventCountTargetingTermPredicateProvider;

    public TargetingClausePredicate_Factory(Provider provider, Provider provider1)
    {
        eventCountTargetingTermPredicateProvider = provider;
        appStateTargetingTermPredicateProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = eventCountTargetingTermPredicateProvider;
        Provider provider1 = appStateTargetingTermPredicateProvider;
        return new TargetingClausePredicate((EventCountTargetingTermPredicate)provider.get(), (AppStateTargetingTermPredicate)provider1.get());
    }
}
