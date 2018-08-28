// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.util.concurrent.FutureCallback;
import java.util.concurrent.atomic.AtomicReference;

public final class CancelableFutureCallback
    implements FutureCallback
{

    public final AtomicReference nestedFutureCallbackReference = new AtomicReference();

    public CancelableFutureCallback(FutureCallback futurecallback)
    {
        nestedFutureCallbackReference.set(futurecallback);
    }

    public final void onFailure(Throwable throwable)
    {
        FutureCallback futurecallback = (FutureCallback)nestedFutureCallbackReference.getAndSet(null);
        if (futurecallback != null)
        {
            futurecallback.onFailure(throwable);
        }
    }

    public final void onSuccess(Object obj)
    {
        FutureCallback futurecallback = (FutureCallback)nestedFutureCallbackReference.getAndSet(null);
        if (futurecallback != null)
        {
            futurecallback.onSuccess(obj);
        }
    }
}
