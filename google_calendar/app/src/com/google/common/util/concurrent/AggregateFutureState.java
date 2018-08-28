// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class AggregateFutureState
{
    static abstract class AtomicHelper
    {

        abstract void compareAndSetSeenExceptions(AggregateFutureState aggregatefuturestate, Set set, Set set1);

        abstract int decrementAndGetRemainingCount(AggregateFutureState aggregatefuturestate);

        AtomicHelper()
        {
        }
    }

    static final class SafeAtomicHelper extends AtomicHelper
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

        SafeAtomicHelper(AtomicReferenceFieldUpdater atomicreferencefieldupdater, AtomicIntegerFieldUpdater atomicintegerfieldupdater)
        {
            seenExceptionsUpdater = atomicreferencefieldupdater;
            remainingCountUpdater = atomicintegerfieldupdater;
        }
    }

    static final class SynchronizedAtomicHelper extends AtomicHelper
    {

        final void compareAndSetSeenExceptions(AggregateFutureState aggregatefuturestate, Set set, Set set1)
        {
            aggregatefuturestate;
            JVM INSTR monitorenter ;
            if (aggregatefuturestate.seenExceptions == null)
            {
                aggregatefuturestate.seenExceptions = set1;
            }
            aggregatefuturestate;
            JVM INSTR monitorexit ;
            return;
            set;
            aggregatefuturestate;
            JVM INSTR monitorexit ;
            throw set;
        }

        final int decrementAndGetRemainingCount(AggregateFutureState aggregatefuturestate)
        {
            aggregatefuturestate;
            JVM INSTR monitorenter ;
            int i;
            aggregatefuturestate.remaining = aggregatefuturestate.remaining - 1;
            i = aggregatefuturestate.remaining;
            aggregatefuturestate;
            JVM INSTR monitorexit ;
            return i;
            Exception exception;
            exception;
            aggregatefuturestate;
            JVM INSTR monitorexit ;
            throw exception;
        }

        SynchronizedAtomicHelper()
        {
        }
    }


    public static final AtomicHelper ATOMIC_HELPER;
    private static final Logger log;
    public volatile int remaining;
    public volatile Set seenExceptions;

    AggregateFutureState(int i)
    {
        seenExceptions = null;
        remaining = i;
    }

    abstract void addInitialException(Set set);

    static 
    {
        log = Logger.getLogger(com/google/common/util/concurrent/AggregateFutureState.getName());
        Throwable throwable = null;
        Object obj;
        try
        {
            obj = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(com/google/common/util/concurrent/AggregateFutureState, java/util/Set, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(com/google/common/util/concurrent/AggregateFutureState, "remaining"));
        }
        // Misplaced declaration of an exception variable
        catch (Throwable throwable)
        {
            obj = new SynchronizedAtomicHelper();
        }
        ATOMIC_HELPER = ((AtomicHelper) (obj));
        if (throwable != null)
        {
            log.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFutureState", "<clinit>", "SafeAtomicHelper is broken!", throwable);
        }
    }
}
