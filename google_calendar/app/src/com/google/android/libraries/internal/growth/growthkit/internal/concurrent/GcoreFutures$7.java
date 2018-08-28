// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.android.libraries.gcoreclient.common.api.GcoreReleasable;
import com.google.android.libraries.gcoreclient.common.api.GcoreResult;
import com.google.android.libraries.gcoreclient.common.api.GcoreResultCallback;
import com.google.android.libraries.gcoreclient.common.api.GcoreStatus;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;

final class val.future
    implements GcoreResultCallback
{

    private final SettableFuture val$future;

    public final void onResult(GcoreResult gcoreresult)
    {
        GcoreStatus gcorestatus = gcoreresult.getStatus();
        if (gcorestatus.isInterrupted())
        {
            gcoreresult = String.valueOf(gcoreresult);
            throw new AssertionError((new StringBuilder(String.valueOf(gcoreresult).length() + 47)).append("We never use the blocking API for these calls: ").append(gcoreresult).toString());
        }
        boolean flag;
        if (!gcorestatus.isSuccess())
        {
            flag = false;
            val$future.setException(new oreException(gcorestatus));
        } else
        {
            flag = val$future.set(gcoreresult);
        }
        if (!flag && (gcoreresult instanceof GcoreReleasable))
        {
            ((GcoreReleasable)gcoreresult).release();
        }
    }

    oreException()
    {
        val$future = settablefuture;
        super();
    }
}
