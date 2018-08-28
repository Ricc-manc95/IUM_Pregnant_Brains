// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api.support;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.libraries.gcoreclient.common.api.GcoreResultCallback;

// Referenced classes of package com.google.android.libraries.gcoreclient.common.api.support:
//            ResultWrapper

public final class GcoreResultCallbackDelegate
    implements ResultCallback
{

    private final GcoreResultCallback callback;
    private final ResultWrapper resultWrapper;

    public GcoreResultCallbackDelegate(GcoreResultCallback gcoreresultcallback, ResultWrapper resultwrapper)
    {
        callback = gcoreresultcallback;
        resultWrapper = resultwrapper;
    }

    public final void onResult(Result result)
    {
        callback.onResult(resultWrapper.wrap(result));
    }
}
