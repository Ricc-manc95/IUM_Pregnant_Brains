// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.internal.GrpcUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class Resource
    implements io.grpc.internal.source
{

    public final void close(Object obj)
    {
        ((ExecutorService)obj).shutdown();
    }

    public final Object create()
    {
        return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-okhttp-%d", true));
    }

    Resource()
    {
    }
}
