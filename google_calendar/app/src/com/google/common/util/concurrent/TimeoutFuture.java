// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Future;

// Referenced classes of package com.google.common.util.concurrent:
//            ListenableFuture, AbstractFuture

public final class TimeoutFuture extends AbstractFuture.TrustedFuture
{

    public ListenableFuture delegateRef;
    public Future timer;

    public TimeoutFuture(ListenableFuture listenablefuture)
    {
        if (listenablefuture == null)
        {
            throw new NullPointerException();
        } else
        {
            delegateRef = (ListenableFuture)listenablefuture;
            return;
        }
    }

    protected final void afterDone()
    {
        maybePropagateCancellationTo(delegateRef);
        Future future = timer;
        if (future != null)
        {
            future.cancel(false);
        }
        delegateRef = null;
        timer = null;
    }

    protected final String pendingToString()
    {
        Object obj = delegateRef;
        if (obj != null)
        {
            obj = String.valueOf(obj);
            return (new StringBuilder(String.valueOf(obj).length() + 14)).append("inputFuture=[").append(((String) (obj))).append("]").toString();
        } else
        {
            return null;
        }
    }
}
