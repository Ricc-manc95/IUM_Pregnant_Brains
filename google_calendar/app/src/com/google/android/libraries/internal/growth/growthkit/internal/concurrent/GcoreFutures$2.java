// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.concurrent:
//            AsyncCloseableFunction, GcoreFutures, AsyncCloseable

final class val.client
    implements AsyncCloseableFunction
{

    private final Function val$call;
    private final GcoreGoogleApiClient val$client;

    public final AsyncCloseable apply(Object obj)
        throws Exception
    {
        return GcoreFutures.from((GcorePendingResult)val$call.apply(val$client));
    }

    unction()
    {
        val$call = function;
        val$client = gcoregoogleapiclient;
        super();
    }
}
