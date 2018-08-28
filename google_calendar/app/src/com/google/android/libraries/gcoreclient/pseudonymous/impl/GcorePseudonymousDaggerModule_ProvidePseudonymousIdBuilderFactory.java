// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.pseudonymous.impl;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.gcoreclient.pseudonymous.impl:
//            GcorePseudonymousDaggerModule

public final class GcorePseudonymousDaggerModule_ProvidePseudonymousIdBuilderFactory
    implements Factory
{

    private final GcorePseudonymousDaggerModule module;

    public GcorePseudonymousDaggerModule_ProvidePseudonymousIdBuilderFactory(GcorePseudonymousDaggerModule gcorepseudonymousdaggermodule)
    {
        module = gcorepseudonymousdaggermodule;
    }

    public final Object get()
    {
        Object obj = module;
        obj = new GcorePseudonymousIdImpl.Builder();
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousId.Builder)obj;
        }
    }
}
