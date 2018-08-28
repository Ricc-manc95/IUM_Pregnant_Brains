// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl:
//            EventCountTargetingTermPredicate

public final class EventCountTargetingTermPredicate_Factory
    implements Factory
{

    public static final EventCountTargetingTermPredicate_Factory INSTANCE = new EventCountTargetingTermPredicate_Factory();

    public EventCountTargetingTermPredicate_Factory()
    {
    }

    public final Object get()
    {
        return new EventCountTargetingTermPredicate();
    }

}
