// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesStartupMeasure

public final class this._cls0
    implements Runnable
{

    private final PrimesStartupMeasure this$0;

    public final void run()
    {
        PrimesStartupMeasure primesstartupmeasure = PrimesStartupMeasure.this;
        boolean flag;
        if (firstOnActivityCreatedAt != 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        primesstartupmeasure.startedByUser = flag;
    }

    public ()
    {
        this$0 = PrimesStartupMeasure.this;
        super();
    }
}
