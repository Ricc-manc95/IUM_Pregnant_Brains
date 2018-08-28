// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.util;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;

public final class IdleTracker
{

    public int nonIdleCount;
    public final ObservableReference observable;

    public IdleTracker()
    {
        observable = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Boolean.TRUE);
        nonIdleCount = 0;
    }

    public final int eventCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = nonIdleCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean isIdle()
    {
        this;
        JVM INSTR monitorenter ;
        int i = nonIdleCount;
        boolean flag;
        if (i == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public final OnGoingEvent onEventBegin()
    {
        this;
        JVM INSTR monitorenter ;
        OnGoingEvent ongoingevent;
        if (nonIdleCount == 0)
        {
            observable.set(Boolean.FALSE);
        }
        nonIdleCount = nonIdleCount + 1;
        ongoingevent = new OnGoingEvent();
        this;
        JVM INSTR monitorexit ;
        return ongoingevent;
        Exception exception;
        exception;
        throw exception;
    }

    private class OnGoingEvent
    {

        private final IdleTracker this$0;

        public final void onComplete()
        {
            IdleTracker idletracker = IdleTracker.this;
            idletracker;
            JVM INSTR monitorenter ;
            Exception exception;
            boolean flag;
            if (nonIdleCount > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                break MISSING_BLOCK_LABEL_41;
            }
            throw new IllegalStateException();
            exception;
            idletracker;
            JVM INSTR monitorexit ;
            throw exception;
            IdleTracker idletracker1 = IdleTracker.this;
            idletracker1.nonIdleCount = idletracker1.nonIdleCount - 1;
            if (nonIdleCount == 0)
            {
                observable.set(Boolean.TRUE);
            }
            idletracker;
            JVM INSTR monitorexit ;
        }

        public OnGoingEvent()
        {
            this$0 = IdleTracker.this;
            super();
        }
    }

}
