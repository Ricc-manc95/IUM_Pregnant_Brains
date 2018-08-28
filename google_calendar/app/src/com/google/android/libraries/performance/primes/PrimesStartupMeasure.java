// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesLog, NoPiiString

public final class PrimesStartupMeasure
{

    public static final List ON_ACTIVITY_INIT_EMPTY_LIST = Collections.emptyList();
    public static final List ON_DRAW_EMPTY_LIST = Collections.emptyList();
    public static volatile PrimesStartupMeasure instance = new PrimesStartupMeasure();
    public volatile long appClassLoadedAt;
    public volatile long appOnCreateAt;
    public volatile boolean firstActivityPausedBeforeDraw;
    public volatile long firstAppInteractiveAt;
    public volatile long firstDrawnAt;
    public volatile long firstOnActivityCreatedAt;
    public volatile long firstOnActivityInitAt;
    public volatile long firstOnActivityResumedAt;
    public volatile long firstOnActivityStartedAt;
    public final Object onActivityInitListenerLock = new Object();
    public volatile List onActivityInitListeners;
    public final Object onDrawListenerLock = new Object();
    public volatile List onDrawListeners;
    public volatile boolean startedByUser;
    private final List startupActivityInfos = new ArrayList();
    public volatile NoPiiString startupType;

    PrimesStartupMeasure()
    {
        onActivityInitListeners = ON_ACTIVITY_INIT_EMPTY_LIST;
        onDrawListeners = ON_DRAW_EMPTY_LIST;
    }

    final void addToOrReplaceStartupActivityInfos(StartupActivityInfo startupactivityinfo)
    {
        List list = startupActivityInfos;
        list;
        JVM INSTR monitorenter ;
        if (startupActivityInfos.size() != 3)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        startupActivityInfos.set(2, startupactivityinfo);
_L2:
        return;
        startupActivityInfos.add(startupactivityinfo);
        if (true) goto _L2; else goto _L1
_L1:
        startupactivityinfo;
        list;
        JVM INSTR monitorexit ;
        throw startupactivityinfo;
    }

    final StartupActivityInfo[] getStartupActivityInfos()
    {
        StartupActivityInfo astartupactivityinfo[];
        synchronized (startupActivityInfos)
        {
            astartupactivityinfo = (StartupActivityInfo[])startupActivityInfos.toArray(new StartupActivityInfo[startupActivityInfos.size()]);
        }
        return astartupactivityinfo;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void runOnActivityInitListeners()
    {
        Object obj = onActivityInitListenerLock;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = onActivityInitListeners.iterator();
_L1:
        PrimesStartupMeasureListener.OnActivityInit onactivityinit;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_67;
        }
        onactivityinit = (PrimesStartupMeasureListener.OnActivityInit)iterator.next();
        onactivityinit.onActivityInit();
          goto _L1
        RuntimeException runtimeexception;
        runtimeexception;
        PrimesLog.log(3, "PrimesStartupMeasure", runtimeexception, "Error running onActivityInit listener", new Object[0]);
          goto _L1
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        onActivityInitListeners = Collections.emptyList();
        obj;
        JVM INSTR monitorexit ;
    }

    final void runOnDrawListeners()
    {
        Object obj = onDrawListenerLock;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = onDrawListeners.iterator();
_L1:
        PrimesStartupMeasureListener.OnDraw ondraw;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_67;
        }
        ondraw = (PrimesStartupMeasureListener.OnDraw)iterator.next();
        ondraw.onDraw();
          goto _L1
        RuntimeException runtimeexception;
        runtimeexception;
        PrimesLog.log(3, "PrimesStartupMeasure", runtimeexception, "Error running onDraw listener", new Object[0]);
          goto _L1
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        onDrawListeners = Collections.emptyList();
        obj;
        JVM INSTR monitorexit ;
    }


    private class StartupActivityInfo
    {

        public volatile String activityName;
        public volatile long onActivityCreatedAt;

        StartupActivityInfo()
        {
        }
    }

}
