// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc:
//            Context

final class listener
    implements Runnable
{

    private final Executor executor;
    public final r listener;
    private final Context this$0;

    final void deliver()
    {
        try
        {
            executor.execute(this);
            return;
        }
        catch (Throwable throwable)
        {
            Context.log.logp(Level.INFO, "io.grpc.Context$ExecutableListener", "deliver", "Exception notifying context listener", throwable);
        }
    }

    public final void run()
    {
        listener.cancelled(Context.this);
    }

    r(Executor executor1, r r)
    {
        this$0 = Context.this;
        super();
        executor = executor1;
        listener = r;
    }
}
