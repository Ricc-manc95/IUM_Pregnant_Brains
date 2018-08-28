// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.gms;

import com.google.android.gms.common.api.PendingResult;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.util.gms:
//            ListenableFuturePendingResult

public final class GmsFutures
{

    public static ListenableFuture asFuture(PendingResult pendingresult)
    {
        if (pendingresult instanceof ListenableFuturePendingResult)
        {
            return ((ListenableFuturePendingResult)pendingresult).getFuture();
        } else
        {
            return new PendingResultFuture(pendingresult);
        }
    }

    private class PendingResultFuture extends AbstractFuture
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

        public PendingResultFuture(PendingResult pendingresult)
        {
            pendingResult = pendingresult;
            pendingResult.setResultCallback(this);
        }
    }

}
