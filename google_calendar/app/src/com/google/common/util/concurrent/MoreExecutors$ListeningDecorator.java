// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractListeningExecutorService

class delegate extends AbstractListeningExecutorService
{

    private final ExecutorService _flddelegate;

    public final boolean awaitTermination(long l, TimeUnit timeunit)
        throws InterruptedException
    {
        return _flddelegate.awaitTermination(l, timeunit);
    }

    public final void execute(Runnable runnable)
    {
        _flddelegate.execute(runnable);
    }

    public final boolean isShutdown()
    {
        return _flddelegate.isShutdown();
    }

    public final boolean isTerminated()
    {
        return _flddelegate.isTerminated();
    }

    public final void shutdown()
    {
        _flddelegate.shutdown();
    }

    public final List shutdownNow()
    {
        return _flddelegate.shutdownNow();
    }

    (ExecutorService executorservice)
    {
        if (executorservice == null)
        {
            throw new NullPointerException();
        } else
        {
            _flddelegate = (ExecutorService)executorservice;
            return;
        }
    }
}
