// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

final class arg._cls2
    implements ResultCallback
{

    private final GoogleApiClient arg$1;
    private final Runnable arg$2;

    public final void onResult(Result result)
    {
        result = arg$1;
        Runnable runnable = arg$2;
        if (result.isConnected())
        {
            result.disconnect();
        }
        runnable.run();
    }

    Q(GoogleApiClient googleapiclient, Runnable runnable)
    {
        arg$1 = googleapiclient;
        arg$2 = runnable;
    }
}
