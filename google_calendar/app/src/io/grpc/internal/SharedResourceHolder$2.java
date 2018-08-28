// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.IdentityHashMap;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package io.grpc.internal:
//            SharedResourceHolder

final class val.instance
    implements Runnable
{

    private final SharedResourceHolder this$0;
    private final stance val$cached;
    private final Object val$instance;
    private final source val$resource;

    public final void run()
    {
        synchronized (SharedResourceHolder.this)
        {
            if (val$cached.refcount == 0)
            {
                val$resource.close(val$instance);
                instances.remove(val$resource);
                if (instances.isEmpty())
                {
                    destroyer.shutdown();
                    destroyer = null;
                }
            }
        }
        return;
        exception;
        sharedresourceholder;
        JVM INSTR monitorexit ;
        throw exception;
    }

    source()
    {
        this$0 = final_sharedresourceholder;
        val$cached = stance;
        val$resource = source;
        val$instance = Object.this;
        super();
    }
}
