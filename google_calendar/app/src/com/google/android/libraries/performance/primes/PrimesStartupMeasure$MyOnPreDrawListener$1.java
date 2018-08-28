// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.os.SystemClock;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesStartupMeasure

final class this._cls1
    implements Runnable
{

    private final this._cls1 this$1;

    public final void run()
    {
        firstDrawnAt = SystemClock.elapsedRealtime();
        runOnDrawListeners();
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
