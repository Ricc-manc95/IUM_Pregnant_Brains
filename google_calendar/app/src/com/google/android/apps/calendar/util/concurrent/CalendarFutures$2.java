// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.util.concurrent.FutureCallback;

final class val.onFailure
    implements FutureCallback
{

    private final Consumer val$onFailure;
    private final Consumer val$onSuccess;

    public final void onFailure(Throwable throwable)
    {
        val$onFailure.accept(throwable);
    }

    public final void onSuccess(Object obj)
    {
        val$onSuccess.accept(obj);
    }

    ()
    {
        val$onSuccess = consumer;
        val$onFailure = consumer1;
        super();
    }
}
