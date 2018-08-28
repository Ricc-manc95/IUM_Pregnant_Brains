// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            CrashMetricService

final class this._cls0
    implements Runnable
{

    private final CrashMetricService this$0;

    public final void run()
    {
        recordStartupEvent(3, null);
    }

    ()
    {
        this$0 = CrashMetricService.this;
        super();
    }
}
