// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

// Referenced classes of package io.grpc.internal:
//            SerializingExecutor

static final class runStateUpdater extends runStateUpdater
{

    private final AtomicIntegerFieldUpdater runStateUpdater;

    public final boolean runStateCompareAndSet(SerializingExecutor serializingexecutor, int i, int j)
    {
        return runStateUpdater.compareAndSet(serializingexecutor, 0, -1);
    }

    public final void runStateSet(SerializingExecutor serializingexecutor, int i)
    {
        runStateUpdater.set(serializingexecutor, 0);
    }

    (AtomicIntegerFieldUpdater atomicintegerfieldupdater)
    {
        runStateUpdater = atomicintegerfieldupdater;
    }
}
