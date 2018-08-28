// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            ListenableFutureWrapper

public final class UncancelableFuture extends ListenableFutureWrapper
{

    private UncancelableFuture(ListenableFuture listenablefuture)
    {
        super(listenablefuture);
    }

    public static ListenableFuture uncancelable(ListenableFuture listenablefuture)
    {
        boolean flag;
        if (!listenablefuture.isCancelled())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (listenablefuture.isDone())
        {
            return listenablefuture;
        } else
        {
            return new UncancelableFuture(listenablefuture);
        }
    }

    public final boolean cancel(boolean flag)
    {
        throw new UnsupportedOperationException("This future cannot be canceled.");
    }
}
