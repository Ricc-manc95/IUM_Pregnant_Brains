// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import javax.inject.Provider;

public final class SqliteClearcutEventsStoreFactory
{

    public final Provider dbHelperProvider;
    public final Provider executorProvider;

    public SqliteClearcutEventsStoreFactory(Provider provider, Provider provider1)
    {
        executorProvider = (Provider)checkNotNull(provider, 1);
        dbHelperProvider = (Provider)checkNotNull(provider1, 2);
    }

    static Object checkNotNull(Object obj, int i)
    {
        if (obj == null)
        {
            throw new NullPointerException((new StringBuilder(93)).append("@AutoFactory method argument is null but is not marked @Nullable. Argument index: ").append(i).toString());
        } else
        {
            return obj;
        }
    }
}
