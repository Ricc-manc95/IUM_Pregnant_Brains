// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl:
//            InstalledAppsPredicate

public final class InstalledAppsPredicate_Factory
    implements Factory
{

    private final Provider contextProvider;

    public InstalledAppsPredicate_Factory(Provider provider)
    {
        contextProvider = provider;
    }

    public final Object get()
    {
        return new InstalledAppsPredicate((Context)contextProvider.get());
    }
}