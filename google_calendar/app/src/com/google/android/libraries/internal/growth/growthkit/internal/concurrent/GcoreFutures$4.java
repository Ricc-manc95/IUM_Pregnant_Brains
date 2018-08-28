// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;

final class val.settableFuture
    implements com.google.android.libraries.gcoreclient.common.api.lient.GcoreConnectionCallbacks
{

    private final SettableFuture val$settableFuture;

    public final void onConnected$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R55B0____0()
    {
        val$settableFuture.set(null);
    }

    public final void onConnectionSuspended$514IILG_0()
    {
    }

    llbacks()
    {
        val$settableFuture = settablefuture;
        super();
    }
}
