// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.streamz;

import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.streamz.GcoreClearcutStreamzLogger;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StreamzProdModule_ProvideStreamzLoggerFactory
    implements Factory
{

    private final Provider clearcutLoggerProvider;

    public StreamzProdModule_ProvideStreamzLoggerFactory(Provider provider)
    {
        clearcutLoggerProvider = provider;
    }

    public final Object get()
    {
        GcoreClearcutStreamzLogger gcoreclearcutstreamzlogger = new GcoreClearcutStreamzLogger((GcoreClearcutLogger)((PerAccountProvider)clearcutLoggerProvider.get()).forAccount(null), "STREAMZ_ANDROID_GROWTH");
        if (gcoreclearcutstreamzlogger == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (GcoreClearcutStreamzLogger)gcoreclearcutstreamzlogger;
        }
    }
}
