// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.FinalizableReferenceQueue;
import com.google.common.base.FinalizableWeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class UnhandledThrowableReference extends FinalizableWeakReference
{

    private static final FinalizableReferenceQueue queue = new FinalizableReferenceQueue();
    private static final Set refs = Collections.synchronizedSet(new HashSet());
    private Thread thread;
    private Throwable throwable;

    UnhandledThrowableReference(Object obj)
    {
        super(obj, queue);
        refs.add(this);
    }

    final void clearThrowable()
    {
        this;
        JVM INSTR monitorenter ;
        thread = null;
        throwable = null;
        refs.remove(this);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void finalizeReferent()
    {
        this;
        JVM INSTR monitorenter ;
        refs.remove(this);
        if (throwable != null)
        {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, throwable);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final void setThrowable(Thread thread1, Throwable throwable1)
    {
        this;
        JVM INSTR monitorenter ;
        thread = thread1;
        throwable = throwable1;
        this;
        JVM INSTR monitorexit ;
        return;
        thread1;
        throw thread1;
    }

    static 
    {
        Logger.getLogger(com/google/common/base/FinalizableReferenceQueue.getName()).setLevel(Level.SEVERE);
    }
}
