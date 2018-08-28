// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.tracing;

import com.google.apps.xplat.tracing.types.Level;
import java.util.concurrent.atomic.AtomicReferenceArray;

// Referenced classes of package com.google.apps.xplat.tracing:
//            NoOpTracerConfig, TracingApi, TracerConfig, WithLevelTracingApi, 
//            NoOpTracingApi

public class XTracer
{

    private static final int LEVEL_SIZE = Level.values().length;
    public static TracerConfig config;
    private volatile TracerConfig cachedConfig;
    public final String namespace;
    private volatile AtomicReferenceArray tracersWithLevel;

    public XTracer(String s)
    {
        namespace = s;
        cachedConfig = config;
    }

    public final TracingApi tracingAt(Level level)
    {
        if (cachedConfig == config) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        cachedConfig = config;
        tracersWithLevel = null;
        this;
        JVM INSTR monitorexit ;
_L2:
        AtomicReferenceArray atomicreferencearray;
        atomicreferencearray = tracersWithLevel;
        if (atomicreferencearray != null)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        this;
        JVM INSTR monitorenter ;
        atomicreferencearray = tracersWithLevel;
        Object obj;
        obj = atomicreferencearray;
        if (atomicreferencearray != null)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        obj = new AtomicReferenceArray(LEVEL_SIZE);
        tracersWithLevel = ((AtomicReferenceArray) (obj));
        this;
        JVM INSTR monitorexit ;
        atomicreferencearray = ((AtomicReferenceArray) (obj));
        TracingApi tracingapi;
        tracingapi = (TracingApi)atomicreferencearray.get(level.ordinal());
        obj = tracingapi;
        if (tracingapi != null) goto _L4; else goto _L3
_L3:
        this;
        JVM INSTR monitorenter ;
        tracingapi = (TracingApi)atomicreferencearray.get(level.ordinal());
        obj = tracingapi;
        if (tracingapi != null) goto _L6; else goto _L5
_L5:
        if (level.number < config.getMinLevel())
        {
            break MISSING_BLOCK_LABEL_160;
        }
        obj = new WithLevelTracingApi(this, level);
_L7:
        atomicreferencearray.set(level.ordinal(), obj);
_L6:
        this;
        JVM INSTR monitorexit ;
_L4:
        return ((TracingApi) (obj));
        level;
        this;
        JVM INSTR monitorexit ;
        throw level;
        level;
        this;
        JVM INSTR monitorexit ;
        throw level;
        obj = NoOpTracingApi.INSTANCE;
          goto _L7
        level;
        this;
        JVM INSTR monitorexit ;
        throw level;
    }

    static 
    {
        config = NoOpTracerConfig.INSTANCE;
    }
}
