// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.leak;

import com.google.android.libraries.performance.primes.PrimesLog;
import java.lang.ref.ReferenceQueue;

// Referenced classes of package com.google.android.libraries.performance.primes.leak:
//            LeakWatcherThread, GarbageReference, LeakListener

public final class LeakWatcher
{

    public LeakListener leakListener;
    public LeakWatcherThread leakWatcherThread;
    private final LeakWatcherThread.LeakWatcherThreadFactory leakWatcherThreadFactory;
    private boolean started;

    public LeakWatcher()
    {
        this(new LeakWatcherThread.LeakWatcherThreadFactory());
    }

    private LeakWatcher(LeakWatcherThread.LeakWatcherThreadFactory leakwatcherthreadfactory)
    {
        started = false;
        if (leakwatcherthreadfactory == null)
        {
            throw new NullPointerException();
        } else
        {
            leakWatcherThreadFactory = (LeakWatcherThread.LeakWatcherThreadFactory)leakwatcherthreadfactory;
            return;
        }
    }

    public final void start()
    {
        this;
        JVM INSTR monitorenter ;
        started = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void stop()
    {
        this;
        JVM INSTR monitorenter ;
        if (started)
        {
            started = false;
            if (leakWatcherThread != null)
            {
                leakWatcherThread.interrupt();
                leakWatcherThread = null;
            }
            PrimesLog.log(3, "LeakWatcher", "Stopping leak watcher thread.", new Object[0]);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void watch(Object obj, String s)
    {
        this;
        JVM INSTR monitorenter ;
        LeakWatcherThread leakwatcherthread;
        if (!started)
        {
            break MISSING_BLOCK_LABEL_149;
        }
        if (leakWatcherThread == null)
        {
            LeakListener leaklistener = leakListener;
            leakWatcherThread = new LeakWatcherThread(new ReferenceQueue(), new LeakWatcherThread.GarbageReferenceFactoryImpl(), leaklistener);
            leakWatcherThread.start();
            PrimesLog.log(3, "LeakWatcher", "Starting leak watcher thread.", new Object[0]);
        }
        leakwatcherthread = leakWatcherThread;
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        throw new NullPointerException();
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_100;
        }
        throw new NullPointerException();
        PrimesLog.log(3, "LeakWatcherThread", "Watching %s", new Object[] {
            s
        });
        s = leakwatcherthread.referenceFactory.newReference(obj, s, leakwatcherthread.referenceQueue);
        synchronized (leakwatcherthread.incomingList)
        {
            s.insertAfter(leakwatcherthread.incomingList);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
    }
}
