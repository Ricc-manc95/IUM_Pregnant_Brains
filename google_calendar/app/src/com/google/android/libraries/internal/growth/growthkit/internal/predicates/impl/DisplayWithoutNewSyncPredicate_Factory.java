// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl:
//            DisplayWithoutNewSyncPredicate

public final class DisplayWithoutNewSyncPredicate_Factory
    implements Factory
{

    public static final DisplayWithoutNewSyncPredicate_Factory INSTANCE = new DisplayWithoutNewSyncPredicate_Factory();

    public DisplayWithoutNewSyncPredicate_Factory()
    {
    }

    public final Object get()
    {
        return new DisplayWithoutNewSyncPredicate();
    }

}
