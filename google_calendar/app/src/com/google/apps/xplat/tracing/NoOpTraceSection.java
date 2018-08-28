// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.tracing;

import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.apps.xplat.tracing:
//            AsyncTraceSection, BlockingTraceSection

public final class NoOpTraceSection
    implements AsyncTraceSection, BlockingTraceSection
{

    public static final NoOpTraceSection INSTANCE = new NoOpTraceSection();

    private NoOpTraceSection()
    {
    }

    public final volatile AsyncTraceSection annotate(String s, String s1)
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }

    public final void close()
    {
    }

    public final ListenableFuture endWhen(ListenableFuture listenablefuture)
    {
        return listenablefuture;
    }

}
