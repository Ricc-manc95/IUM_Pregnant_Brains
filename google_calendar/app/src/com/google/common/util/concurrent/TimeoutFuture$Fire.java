// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.TimeoutException;

// Referenced classes of package com.google.common.util.concurrent:
//            TimeoutFuture, ListenableFuture, AbstractFuture

public final class timeoutFutureRef
    implements Runnable
{

    private TimeoutFuture timeoutFutureRef;

    public final void run()
    {
        TimeoutFuture timeoutfuture = timeoutFutureRef;
        if (timeoutfuture != null) goto _L2; else goto _L1
_L1:
        ListenableFuture listenablefuture;
        return;
_L2:
        if ((listenablefuture = timeoutfuture.delegateRef) == null) goto _L1; else goto _L3
_L3:
        timeoutFutureRef = null;
        if (listenablefuture.isDone())
        {
            timeoutfuture.setFuture(listenablefuture);
            return;
        }
        String s = String.valueOf(listenablefuture);
        timeoutfuture.setException(new TimeoutException((new StringBuilder(String.valueOf(s).length() + 18)).append("Future timed out: ").append(s).toString()));
        listenablefuture.cancel(true);
        return;
        Exception exception;
        exception;
        listenablefuture.cancel(true);
        throw exception;
    }

    public (TimeoutFuture timeoutfuture)
    {
        timeoutFutureRef = timeoutfuture;
    }
}
