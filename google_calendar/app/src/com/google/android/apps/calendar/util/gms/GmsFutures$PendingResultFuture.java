// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.gms;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.common.util.concurrent.AbstractFuture;

public final class pendingResult extends AbstractFuture
    implements ResultCallback
{

    private final PendingResult pendingResult;

    public final boolean cancel(boolean flag)
    {
        if (pendingResult.isCanceled())
        {
            return false;
        } else
        {
            pendingResult.cancel();
            return true;
        }
    }

    public final void onResult(Result result)
    {
        set(result);
    }

    public (PendingResult pendingresult)
    {
        pendingResult = pendingresult;
        pendingResult.setResultCallback(this);
    }
}
