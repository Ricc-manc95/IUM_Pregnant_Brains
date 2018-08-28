// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.common.base.Receiver;
import com.google.common.util.concurrent.FutureCallback;

public final class val.failureConsumer
    implements FutureCallback
{

    private final Receiver val$failureConsumer;
    private final Receiver val$successConsumer;

    public final void onFailure(Throwable throwable)
    {
        if (val$failureConsumer != null)
        {
            val$failureConsumer.accept(throwable);
        }
    }

    public final void onSuccess(Object obj)
    {
        if (val$successConsumer != null)
        {
            val$successConsumer.accept(obj);
        }
    }

    public ()
    {
        val$successConsumer = receiver;
        val$failureConsumer = receiver1;
        super();
    }
}
