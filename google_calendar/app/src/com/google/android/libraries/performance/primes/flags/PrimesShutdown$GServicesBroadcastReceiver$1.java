// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.flags;

import com.google.android.libraries.performance.primes.Shutdown;

final class this._cls0
    implements Runnable
{

    private final utdownFlag this$0;

    public final void run()
    {
        utdown.updateShutdownFlag(utdownFlag);
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
