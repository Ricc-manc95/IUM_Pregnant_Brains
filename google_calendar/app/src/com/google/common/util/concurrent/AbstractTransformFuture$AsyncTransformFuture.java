// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


// Referenced classes of package com.google.common.util.concurrent:
//            AbstractTransformFuture, AsyncFunction, ListenableFuture, AbstractFuture

final class  extends AbstractTransformFuture
{

    final Object doTransform(Object obj, Object obj1)
        throws Exception
    {
        obj = ((AsyncFunction)obj).apply(obj1);
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)?"));
        } else
        {
            return obj;
        }
    }

    final void setResult(Object obj)
    {
        setFuture((ListenableFuture)obj);
    }

    (ListenableFuture listenablefuture, AsyncFunction asyncfunction)
    {
        super(listenablefuture, asyncfunction);
    }
}
