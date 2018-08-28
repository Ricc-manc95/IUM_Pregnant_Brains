// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api.support;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;
import com.google.android.libraries.gcoreclient.common.api.GcoreResultCallback;

// Referenced classes of package com.google.android.libraries.gcoreclient.common.api.support:
//            GcoreResultCallbackDelegate, ResultWrapper

public final class GcorePendingResultImpl
    implements GcorePendingResult
{

    private final PendingResult pendingResult;
    private final ResultWrapper resultWrapper;

    public GcorePendingResultImpl(PendingResult pendingresult, ResultWrapper resultwrapper)
    {
        pendingResult = pendingresult;
        resultWrapper = resultwrapper;
    }

    public final void cancel()
    {
        pendingResult.cancel();
    }

    public final void setResultCallback(GcoreResultCallback gcoreresultcallback)
    {
        pendingResult.setResultCallback(new GcoreResultCallbackDelegate(gcoreresultcallback, resultWrapper));
    }
}
