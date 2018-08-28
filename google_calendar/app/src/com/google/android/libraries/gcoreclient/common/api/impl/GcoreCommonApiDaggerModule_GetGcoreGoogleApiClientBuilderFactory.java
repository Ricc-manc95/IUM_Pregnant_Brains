// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.gcoreclient.common.api.impl:
//            GcoreCommonApiDaggerModule

public final class GcoreCommonApiDaggerModule_GetGcoreGoogleApiClientBuilderFactory
    implements Factory
{

    private final Provider contextProvider;
    private final GcoreCommonApiDaggerModule module;

    public GcoreCommonApiDaggerModule_GetGcoreGoogleApiClientBuilderFactory(GcoreCommonApiDaggerModule gcorecommonapidaggermodule, Provider provider)
    {
        module = gcorecommonapidaggermodule;
        contextProvider = provider;
    }

    public final Object get()
    {
        Object obj = module;
        obj = new GcoreGoogleApiClientImpl.Builder((Context)contextProvider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder)obj;
        }
    }
}
