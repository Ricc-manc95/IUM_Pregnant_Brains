// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.internal.AtomicBackoff;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

final class val.keepAliveTimeNanosState
    implements Runnable
{

    private final io.grpc.internal.l.keepAliveTimeNanosState val$keepAliveTimeNanosState;

    public final void run()
    {
        io.grpc.internal.l.keepAliveTimeNanosState keepalivetimenanosstate = val$keepAliveTimeNanosState;
        long l = Math.max(keepalivetimenanosstate.keepAliveTimeNanosState << 1, keepalivetimenanosstate.keepAliveTimeNanosState);
        if (keepalivetimenanosstate.keepAliveTimeNanosState.value.compareAndSet(keepalivetimenanosstate.keepAliveTimeNanosState, l))
        {
            AtomicBackoff.log.logp(Level.WARNING, "io.grpc.internal.AtomicBackoff$State", "backoff", "Increased {0} to {1}", new Object[] {
                keepalivetimenanosstate.keepAliveTimeNanosState.name, Long.valueOf(l)
            });
        }
    }

    ()
    {
        val$keepAliveTimeNanosState = ;
        super();
    }
}
