// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesLog

final class allback
    implements allback
{

    public final void onFailure(Throwable throwable)
    {
        PrimesLog.log(5, "PrimesExecutors", throwable, "Background task failed", new Object[0]);
    }

    allback()
    {
    }
}
