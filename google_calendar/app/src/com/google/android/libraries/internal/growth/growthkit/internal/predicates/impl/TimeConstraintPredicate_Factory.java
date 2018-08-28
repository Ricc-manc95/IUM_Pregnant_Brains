// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl:
//            TimeConstraintPredicate

public final class TimeConstraintPredicate_Factory
    implements Factory
{

    private final Provider clockProvider;

    public TimeConstraintPredicate_Factory(Provider provider)
    {
        clockProvider = provider;
    }

    public final Object get()
    {
        return new TimeConstraintPredicate((Clock)clockProvider.get());
    }
}
