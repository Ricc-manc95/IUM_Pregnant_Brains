// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            CrashMetricService

final class this._cls1
    implements Runnable
{

    private final rdStartupEvent this$1;

    public final void run()
    {
        CrashMetricService crashmetricservice = _fld0;
        if (crashmetricservice.deferPrimesStats.getAndSet(false))
        {
            crashmetricservice.recordStartupEvent(2, crashmetricservice.deferredPrevCrash);
            crashmetricservice.recordStartupEvent(3, null);
        }
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
