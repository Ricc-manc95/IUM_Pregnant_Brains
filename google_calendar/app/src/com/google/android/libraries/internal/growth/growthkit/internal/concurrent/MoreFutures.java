// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.common.base.Receiver;
import com.google.common.util.concurrent.ListenableFuture;

public final class MoreFutures
{

    public static void addCallback(ListenableFuture listenablefuture, final Receiver successConsumer, final Receiver failureConsumer)
    {
        com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        successConsumer = new _cls1();
        if (successConsumer == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, successConsumer), directexecutor);
            return;
        }
    }

    private class _cls1
        implements FutureCallback
    {

        private final Receiver val$failureConsumer;
        private final Receiver val$successConsumer;

        public final void onFailure(Throwable throwable)
        {
            if (failureConsumer != null)
            {
                failureConsumer.accept(throwable);
            }
        }

        public final void onSuccess(Object obj)
        {
            if (successConsumer != null)
            {
                successConsumer.accept(obj);
            }
        }

        public _cls1()
        {
            successConsumer = receiver;
            failureConsumer = receiver1;
            super();
        }
    }

}
