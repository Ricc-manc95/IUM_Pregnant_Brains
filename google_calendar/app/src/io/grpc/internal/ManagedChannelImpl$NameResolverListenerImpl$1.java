// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.LoadBalancer;
import io.grpc.Status;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, BackoffPolicy, ClientTransportFactory

final class val.error
    implements Runnable
{

    private final vice this$1;
    private final Status val$error;

    public final void run()
    {
        if (lper == lbHelper)
        {
            lper.lper.handleNameResolutionError(val$error);
            if (nameResolverRefreshFuture == null)
            {
                if (nameResolverBackoffPolicy == null)
                {
                    nameResolverBackoffPolicy = backoffPolicyProvider.error();
                }
                long l = nameResolverBackoffPolicy.nextBackoffNanos();
                if (ManagedChannelImpl.logger.isLoggable(Level.FINE))
                {
                    ManagedChannelImpl.logger.log(Level.FINE, "[{0}] Scheduling DNS resolution backoff for {1} ns", new Object[] {
                        logId, Long.valueOf(l)
                    });
                }
                nameResolverRefresh = new val.error(_fld0);
                nameResolverRefreshFuture = transportFactory.getScheduledExecutorService().schedule(nameResolverRefresh, l, TimeUnit.NANOSECONDS);
                return;
            }
        }
    }

    ()
    {
        this$1 = final_;
        val$error = Status.this;
        super();
    }
}
