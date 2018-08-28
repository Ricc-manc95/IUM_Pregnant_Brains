// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.api.common:
//            FuturePendingResult

final class val.resultCallback
    implements FutureCallback
{

    private final FuturePendingResult this$0;
    private final ResultCallback val$resultCallback;

    public final void onFailure(Throwable throwable)
    {
        val$resultCallback.onResult(createFailedResult(throwable));
    }

    public final void onSuccess(Object obj)
    {
        obj = (Result)obj;
        val$resultCallback.onResult(((Result) (obj)));
    }

    ()
    {
        this$0 = final_futurependingresult;
        val$resultCallback = ResultCallback.this;
        super();
    }
}
