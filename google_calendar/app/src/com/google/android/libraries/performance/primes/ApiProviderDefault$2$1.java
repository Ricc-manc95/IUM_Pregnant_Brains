// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            Supplier

final class this._cls1
    implements Supplier
{

    private final l.application this$1;

    public final Object get()
    {
        return Boolean.valueOf(ProcessStats.isMyProcessInForeground(application));
    }

    ats()
    {
        this$1 = this._cls1.this;
        super();
    }
}
