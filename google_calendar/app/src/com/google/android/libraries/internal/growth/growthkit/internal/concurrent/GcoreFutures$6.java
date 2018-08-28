// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;

final class val.pendingResult
    implements Runnable
{

    private final SettableFuture val$future;
    private final GcorePendingResult val$pendingResult;

    public final void run()
    {
        if (val$future.isCancelled())
        {
            val$pendingResult.cancel();
        }
    }

    ()
    {
        val$future = settablefuture;
        val$pendingResult = gcorependingresult;
        super();
    }
}
