// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.security.impl;

import com.google.android.libraries.gcoreclient.security.GcoreProviderInstaller;
import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.gcoreclient.security.impl:
//            GcoreProviderInstallerImpl, GcoreSecurityDaggerModule

public final class GcoreSecurityDaggerModule_GetGcoreProviderInstallerFactory
    implements Factory
{

    private final GcoreSecurityDaggerModule module;

    public GcoreSecurityDaggerModule_GetGcoreProviderInstallerFactory(GcoreSecurityDaggerModule gcoresecuritydaggermodule)
    {
        module = gcoresecuritydaggermodule;
    }

    public final Object get()
    {
        Object obj = module;
        obj = new GcoreProviderInstallerImpl();
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (GcoreProviderInstaller)obj;
        }
    }
}
