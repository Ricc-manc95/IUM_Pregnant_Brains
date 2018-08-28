// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.tracing;

import com.google.apps.xplat.tracing.types.Level;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.apps.xplat.tracing:
//            AsyncTraceSection, BlockingTraceSection, XTracer

final class level
    implements AsyncTraceSection, BlockingTraceSection
{

    private final Level level;
    private final XTracer tracer;

    public final AsyncTraceSection annotate(String s, String s1)
    {
        return this;
    }

    public final void close()
    {
    }

    public final ListenableFuture endWhen(ListenableFuture listenablefuture)
    {
        return listenablefuture;
    }

    (XTracer xtracer, Level level1)
    {
        tracer = xtracer;
        level = level1;
    }
}
