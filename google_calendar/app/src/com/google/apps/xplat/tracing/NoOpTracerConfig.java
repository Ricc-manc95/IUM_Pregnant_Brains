// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.tracing;

import com.google.apps.xplat.tracing.types.Level;

// Referenced classes of package com.google.apps.xplat.tracing:
//            TracerConfig, NoOpTracerBackend, NoOpTraceSampler, TracerBackend, 
//            TraceSampler

public final class NoOpTracerConfig
    implements TracerConfig
{

    public static final NoOpTracerConfig INSTANCE = new NoOpTracerConfig();

    private NoOpTracerConfig()
    {
    }

    public final TracerBackend getBackend()
    {
        return NoOpTracerBackend.INSTANCE;
    }

    public final int getMinLevel()
    {
        return Level.CRITICAL.number + 1;
    }

    public final TraceSampler getSampler()
    {
        return NoOpTraceSampler.INSTANCE;
    }

}
