// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture, ListenableFuture

public final class delegate extends delegate
    implements Runnable
{

    private ListenableFuture _flddelegate;

    protected final void afterDone()
    {
        _flddelegate = null;
    }

    protected final String pendingToString()
    {
        Object obj = _flddelegate;
        if (obj != null)
        {
            obj = String.valueOf(obj);
            return (new StringBuilder(String.valueOf(obj).length() + 11)).append("delegate=[").append(((String) (obj))).append("]").toString();
        } else
        {
            return null;
        }
    }

    public final void run()
    {
        ListenableFuture listenablefuture = _flddelegate;
        if (listenablefuture != null)
        {
            setFuture(listenablefuture);
        }
    }

    public (ListenableFuture listenablefuture)
    {
        _flddelegate = listenablefuture;
    }
}
