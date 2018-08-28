// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            ShutdownListener, AppLifecycleMonitor, PrimesStartupListener

final class appLifecycleMonitor
    implements appLifecycleMonitor, ShutdownListener
{

    public boolean activityCreated;
    private final AppLifecycleMonitor appLifecycleMonitor;
    public final List startupListeners = new ArrayList();

    public final void onActivityCreated$51662RJ4E9NMIP1FC5O70BQ1CDQ6ITJ9EHSJMJ31DPI74RR9CGNMUSPF89QMSP3CCKTIILG_0()
    {
        this;
        JVM INSTR monitorenter ;
        activityCreated = true;
        this;
        JVM INSTR monitorexit ;
        appLifecycleMonitor.unregister(this);
        for (Iterator iterator = startupListeners.iterator(); iterator.hasNext(); ((PrimesStartupListener)iterator.next()).onFirstActivityCreated()) { }
        break MISSING_BLOCK_LABEL_58;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void onShutdown()
    {
        appLifecycleMonitor.unregister(this);
    }

    (AppLifecycleMonitor applifecyclemonitor)
    {
        appLifecycleMonitor = applifecyclemonitor;
        applifecyclemonitor.register(this);
    }
}
