// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import com.android.calendarcommon2.LogUtils;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.FutureCallback;

final class val.future
    implements FutureCallback
{

    private final FluentFuture val$future;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.logOnFailure(val$future, "NotifActionTrampoline", "Failed to dismiss everyone declined warning.", new Object[0]);
    }

    public final volatile void onSuccess(Object obj)
    {
    }

    ()
    {
        val$future = fluentfuture;
        super();
    }
}
