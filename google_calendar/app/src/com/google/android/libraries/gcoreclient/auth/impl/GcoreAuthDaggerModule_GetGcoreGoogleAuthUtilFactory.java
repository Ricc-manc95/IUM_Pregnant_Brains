// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.auth.impl;

import android.content.Context;
import com.google.android.libraries.gcoreclient.auth.GcoreGoogleAuthUtil;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.gcoreclient.auth.impl:
//            GcoreGoogleAuthUtilImpl, GcoreAuthDaggerModule

public final class GcoreAuthDaggerModule_GetGcoreGoogleAuthUtilFactory
    implements Factory
{

    private final Provider contextProvider;
    private final GcoreAuthDaggerModule module;

    public GcoreAuthDaggerModule_GetGcoreGoogleAuthUtilFactory(GcoreAuthDaggerModule gcoreauthdaggermodule, Provider provider)
    {
        module = gcoreauthdaggermodule;
        contextProvider = provider;
    }

    public final Object get()
    {
        Object obj = module;
        obj = new GcoreGoogleAuthUtilImpl((Context)contextProvider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (GcoreGoogleAuthUtil)obj;
        }
    }
}
