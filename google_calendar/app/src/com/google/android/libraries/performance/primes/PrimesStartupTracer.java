// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.tracing.TraceData;
import com.google.android.libraries.performance.primes.tracing.Tracer;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesToken

final class PrimesStartupTracer
    implements PrimesStartupMeasureListener.OnActivityInit, PrimesStartupMeasureListener.OnDraw
{

    private boolean activeStartupTrace;
    private volatile int minSpanDurationMs;
    public volatile TraceData startupTraceData;

    PrimesStartupTracer(int i)
    {
        activeStartupTrace = false;
        startupTraceData = null;
        minSpanDurationMs = i;
    }

    private final void startPrimesTrace()
    {
        this;
        JVM INSTR monitorenter ;
        if (Tracer.start(PrimesToken.PRIMES_TOKEN, minSpanDurationMs, 100))
        {
            Tracer.createRootSpan(PrimesToken.PRIMES_TOKEN, "");
            activeStartupTrace = true;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void onActivityInit()
    {
        startPrimesTrace();
    }

    public final void onDraw()
    {
        if (activeStartupTrace)
        {
            startupTraceData = Tracer.stop(PrimesToken.PRIMES_TOKEN, "More Insights");
        }
    }

    final void shutdown()
    {
        this;
        JVM INSTR monitorenter ;
        this;
        JVM INSTR monitorenter ;
        if (activeStartupTrace)
        {
            startupTraceData = Tracer.stop(PrimesToken.PRIMES_TOKEN, "More Insights");
        }
        this;
        JVM INSTR monitorexit ;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
