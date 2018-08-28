// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.CountDownLatch;

final class val.latch
    implements com.google.android.gms.common.api.nectionFailedListener
{

    private final CountDownLatch val$latch;

    public final void onConnectionFailed(ConnectionResult connectionresult)
    {
        val$latch.countDown();
    }

    ectionFailedListener()
    {
        val$latch = countdownlatch;
        super();
    }
}
