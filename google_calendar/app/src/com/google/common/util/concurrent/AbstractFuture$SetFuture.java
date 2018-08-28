// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture, ListenableFuture

static final class future
    implements Runnable
{

    public final ListenableFuture future;
    public final AbstractFuture owner;

    public final void run()
    {
        Object obj;
        if (owner.value == this)
        {
            if (AbstractFuture.ATOMIC_HELPER.casValue(owner, this, obj = AbstractFuture.getFutureValue(future)))
            {
                AbstractFuture.complete(owner);
                return;
            }
        }
    }

    er(AbstractFuture abstractfuture, ListenableFuture listenablefuture)
    {
        owner = abstractfuture;
        future = listenablefuture;
    }
}
