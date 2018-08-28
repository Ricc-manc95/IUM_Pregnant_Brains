// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.pseudonymous.impl;

import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousIdApi;
import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.gcoreclient.pseudonymous.impl:
//            GcorePseudonymousIdApiImpl, GcorePseudonymousDaggerModule

public final class GcorePseudonymousDaggerModule_GetPseudonymousIdApiFactory
    implements Factory
{

    private final GcorePseudonymousDaggerModule module;

    public GcorePseudonymousDaggerModule_GetPseudonymousIdApiFactory(GcorePseudonymousDaggerModule gcorepseudonymousdaggermodule)
    {
        module = gcorepseudonymousdaggermodule;
    }

    public final Object get()
    {
        Object obj = module;
        obj = new GcorePseudonymousIdApiImpl();
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (GcorePseudonymousIdApi)obj;
        }
    }
}
