// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.latency;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.calendar.latency:
//            LatencyLoggerHolder, LatencyLogger

public final class LatencyLoggerModule_ProvidesLatencyLoggerFactory
    implements Factory
{

    public static final LatencyLoggerModule_ProvidesLatencyLoggerFactory INSTANCE = new LatencyLoggerModule_ProvidesLatencyLoggerFactory();

    public LatencyLoggerModule_ProvidesLatencyLoggerFactory()
    {
    }

    public final Object get()
    {
        LatencyLogger latencylogger = LatencyLoggerHolder.get();
        if (latencylogger == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (LatencyLogger)latencylogger;
        }
    }

}
