// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package io.grpc.internal:
//            GrpcUtil

final class rvice
    implements heduledExecutorFactory
{

    public final ScheduledExecutorService createScheduledExecutor()
    {
        return Executors.newSingleThreadScheduledExecutor(GrpcUtil.getThreadFactory("grpc-shared-destroyer-%d", true));
    }

    rvice()
    {
    }
}
