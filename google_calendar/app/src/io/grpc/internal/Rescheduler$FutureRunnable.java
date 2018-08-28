// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.Executor;

// Referenced classes of package io.grpc.internal:
//            Rescheduler

final class rescheduler
    implements Runnable
{

    private final Rescheduler rescheduler;

    public final void run()
    {
        Executor executor = rescheduler.serializingExecutor;
        Rescheduler rescheduler1 = rescheduler;
        rescheduler1.getClass();
        executor.execute(new nnable(rescheduler1));
    }

    nnable(Rescheduler rescheduler1)
    {
        rescheduler = rescheduler1;
    }
}
