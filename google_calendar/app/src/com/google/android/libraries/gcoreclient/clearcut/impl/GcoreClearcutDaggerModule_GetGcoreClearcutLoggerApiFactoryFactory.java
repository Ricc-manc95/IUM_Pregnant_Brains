// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.clearcut.impl;

import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLoggerFactory;
import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.gcoreclient.clearcut.impl:
//            GcoreClearcutLoggerFactoryImpl

public final class GcoreClearcutDaggerModule_GetGcoreClearcutLoggerApiFactoryFactory
    implements Factory
{

    public static final GcoreClearcutDaggerModule_GetGcoreClearcutLoggerApiFactoryFactory INSTANCE = new GcoreClearcutDaggerModule_GetGcoreClearcutLoggerApiFactoryFactory();

    public GcoreClearcutDaggerModule_GetGcoreClearcutLoggerApiFactoryFactory()
    {
    }

    public final Object get()
    {
        GcoreClearcutLoggerFactoryImpl gcoreclearcutloggerfactoryimpl = new GcoreClearcutLoggerFactoryImpl();
        if (gcoreclearcutloggerfactoryimpl == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (GcoreClearcutLoggerFactory)gcoreclearcutloggerfactoryimpl;
        }
    }

}
