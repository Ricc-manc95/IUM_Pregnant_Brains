// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

// Referenced classes of package com.google.common.util.concurrent:
//            AggregateFutureState

static final class remainingCountUpdater extends remainingCountUpdater
{

    private final AtomicIntegerFieldUpdater remainingCountUpdater;
    private final AtomicReferenceFieldUpdater seenExceptionsUpdater;

    final void compareAndSetSeenExceptions(AggregateFutureState aggregatefuturestate, Set set, Set set1)
    {
        seenExceptionsUpdater.compareAndSet(aggregatefuturestate, null, set1);
    }

    final int decrementAndGetRemainingCount(AggregateFutureState aggregatefuturestate)
    {
        return remainingCountUpdater.decrementAndGet(aggregatefuturestate);
    }

    (AtomicReferenceFieldUpdater atomicreferencefieldupdater, AtomicIntegerFieldUpdater atomicintegerfieldupdater)
    {
        seenExceptionsUpdater = atomicreferencefieldupdater;
        remainingCountUpdater = atomicintegerfieldupdater;
    }
}
