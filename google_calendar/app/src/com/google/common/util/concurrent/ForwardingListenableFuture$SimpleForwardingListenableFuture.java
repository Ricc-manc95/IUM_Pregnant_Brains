// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Future;

// Referenced classes of package com.google.common.util.concurrent:
//            ForwardingListenableFuture, ListenableFuture

public class delegate extends ForwardingListenableFuture
{

    private final ListenableFuture _flddelegate;

    protected final ListenableFuture _mthdelegate()
    {
        return _flddelegate;
    }

    protected final volatile Object _mthdelegate()
    {
        return _mthdelegate();
    }

    protected final volatile Future _mthdelegate()
    {
        return _mthdelegate();
    }

    public (ListenableFuture listenablefuture)
    {
        if (listenablefuture == null)
        {
            throw new NullPointerException();
        } else
        {
            _flddelegate = (ListenableFuture)listenablefuture;
            return;
        }
    }
}
