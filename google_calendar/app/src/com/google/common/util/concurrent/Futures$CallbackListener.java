// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

// Referenced classes of package com.google.common.util.concurrent:
//            Futures, FutureCallback

public final class callback
    implements Runnable
{

    private final FutureCallback callback;
    private final Future future;

    public final void run()
    {
        Object obj = Futures.getDone(future);
        callback.onSuccess(obj);
        return;
        Object obj1;
        obj1;
        callback.onFailure(((ExecutionException) (obj1)).getCause());
        return;
        obj1;
_L2:
        callback.onFailure(((Throwable) (obj1)));
        return;
        obj1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final String toString()
    {
        return (new com.google.common.base.r(getClass().getSimpleName())).addValue(callback).toString();
    }

    public (Future future1, FutureCallback futurecallback)
    {
        future = future1;
        callback = futurecallback;
    }
}
