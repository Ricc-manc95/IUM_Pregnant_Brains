// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            ShutdownListener, PrimesLog, Supplier

public abstract class Shutdown
{

    private final List listeners = new ArrayList();
    public volatile boolean shutdown;

    public Shutdown()
    {
    }

    public abstract void init$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFADQN0S3CD5IN4EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUKRLE1O6OQB5E8TIILG_0(Context context, Supplier supplier);

    public final boolean registerShutdownListener(ShutdownListener shutdownlistener)
    {
        List list = listeners;
        list;
        JVM INSTR monitorenter ;
        List list1;
        if (shutdown)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        list1 = listeners;
        if (shutdownlistener != null)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        throw new NullPointerException();
        shutdownlistener;
        list;
        JVM INSTR monitorexit ;
        throw shutdownlistener;
        list1.add((ShutdownListener)shutdownlistener);
        list;
        JVM INSTR monitorexit ;
        return true;
        list;
        JVM INSTR monitorexit ;
        return false;
    }

    public final void shutdown()
    {
        this;
        JVM INSTR monitorenter ;
        if (shutdown) goto _L2; else goto _L1
_L1:
        shutdown = true;
        PrimesLog.log(3, "PrimesShutdown", "Shutdown ...", new Object[0]);
        Object obj = listeners;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = listeners.iterator();
_L3:
        ShutdownListener shutdownlistener;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_98;
        }
        shutdownlistener = (ShutdownListener)iterator.next();
        shutdownlistener.onShutdown();
          goto _L3
        RuntimeException runtimeexception;
        runtimeexception;
        PrimesLog.log(3, "PrimesShutdown", runtimeexception, "ShutdownListener crashed", new Object[0]);
          goto _L3
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        listeners.clear();
        PrimesLog.log(3, "PrimesShutdown", "All ShutdownListeners notified.", new Object[0]);
        obj;
        JVM INSTR monitorexit ;
_L2:
        this;
        JVM INSTR monitorexit ;
    }

    public final void updateShutdownFlag(Supplier supplier)
    {
        if (!shutdown && ((Boolean)supplier.get()).booleanValue())
        {
            shutdown();
        }
        return;
        supplier;
        throw supplier;
    }
}
