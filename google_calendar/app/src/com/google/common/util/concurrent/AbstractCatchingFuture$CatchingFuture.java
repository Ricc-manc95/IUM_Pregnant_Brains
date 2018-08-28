// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Function;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractCatchingFuture, AbstractFuture, ListenableFuture

final class  extends AbstractCatchingFuture
{

    final Object doFallback(Object obj, Throwable throwable)
        throws Exception
    {
        return ((Function)obj).apply(throwable);
    }

    final void setResult(Object obj)
    {
        set(obj);
    }

    (ListenableFuture listenablefuture, Class class1, Function function)
    {
        super(listenablefuture, class1, function);
    }
}
