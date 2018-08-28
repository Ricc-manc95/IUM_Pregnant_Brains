// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.Set;

// Referenced classes of package com.google.common.util.concurrent:
//            AggregateFutureState

static final class  extends 
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

    ()
    {
    }
}
