// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.util;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.util:
//            IdleTracker

public final class this._cls0
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

    public a()
    {
        this$0 = IdleTracker.this;
        super();
    }
}
