// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl:
//            ClearcutTriggeringRulePredicate

public final class ClearcutTriggeringRulePredicate_Factory
    implements Factory
{

    public static final ClearcutTriggeringRulePredicate_Factory INSTANCE = new ClearcutTriggeringRulePredicate_Factory();

    public ClearcutTriggeringRulePredicate_Factory()
    {
    }

    public final Object get()
    {
        return new ClearcutTriggeringRulePredicate();
    }

}
