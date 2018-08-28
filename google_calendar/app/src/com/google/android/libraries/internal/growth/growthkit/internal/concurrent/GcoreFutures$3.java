// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.common.util.concurrent.FutureCallback;

final class val.client
    implements FutureCallback
{

    private final GcoreGoogleApiClient val$client;

    public final void onFailure(Throwable throwable)
    {
        val$client.disconnect();
    }

    public final volatile void onSuccess(Object obj)
    {
    }

    ()
    {
        val$client = gcoregoogleapiclient;
        super();
    }
}
