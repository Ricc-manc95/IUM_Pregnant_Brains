// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            Supplier, LazyMetricServices

final class this._cls0
    implements Supplier
{

    private final LazyMetricServices this$0;

    public final Object get()
    {
        return timerMetricService();
    }

    ()
    {
        this$0 = LazyMetricServices.this;
        super();
    }
}
