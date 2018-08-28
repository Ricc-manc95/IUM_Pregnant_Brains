// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Bundle;
import java.util.concurrent.CountDownLatch;

final class val.latch
    implements com.google.android.gms.common.api.ctionCallbacks
{

    private final CountDownLatch val$latch;

    public final void onConnected(Bundle bundle)
    {
        val$latch.countDown();
    }

    public final void onConnectionSuspended(int i)
    {
    }

    tionCallbacks()
    {
        val$latch = countdownlatch;
        super();
    }
}
