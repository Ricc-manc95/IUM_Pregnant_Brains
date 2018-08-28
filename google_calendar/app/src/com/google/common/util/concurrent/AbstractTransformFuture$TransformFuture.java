// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Function;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractTransformFuture, AbstractFuture, ListenableFuture

final class  extends AbstractTransformFuture
{

    final Object doTransform(Object obj, Object obj1)
        throws Exception
    {
        return ((Function)obj).apply(obj1);
    }

    final void setResult(Object obj)
    {
        set(obj);
    }

    (ListenableFuture listenablefuture, Function function)
    {
        super(listenablefuture, function);
    }
}
