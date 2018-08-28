// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture, CombinedFuture

final class callable extends eTask
{

    private final Callable callable;
    private final CombinedFuture this$0;

    final Object runInterruptibly()
        throws Exception
    {
        thrownByExecute = false;
        return callable.call();
    }

    final void setValue(Object obj)
    {
        set(obj);
    }

    final String toPendingString()
    {
        return callable.toString();
    }

    public eTask(Callable callable1, Executor executor)
    {
        this$0 = CombinedFuture.this;
        super(CombinedFuture.this, executor);
        if (callable1 == null)
        {
            throw new NullPointerException();
        } else
        {
            callable = (Callable)callable1;
            return;
        }
    }
}
