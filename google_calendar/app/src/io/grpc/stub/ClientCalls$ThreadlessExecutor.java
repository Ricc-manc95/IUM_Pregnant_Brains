// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.stub;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.stub:
//            ClientCalls

static final class 
    implements Executor
{

    public static final Logger log = Logger.getLogger(io/grpc/stub/ClientCalls$ThreadlessExecutor.getName());
    public final BlockingQueue queue = new LinkedBlockingQueue();

    public final void execute(Runnable runnable)
    {
        queue.add(runnable);
    }


    ()
    {
    }
}
