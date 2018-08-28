// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.tracing;

import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.apps.xplat.tracing:
//            TraceSection

public interface AsyncTraceSection
    extends TraceSection
{

    public abstract AsyncTraceSection annotate(String s, String s1);

    public abstract ListenableFuture endWhen(ListenableFuture listenablefuture);
}
