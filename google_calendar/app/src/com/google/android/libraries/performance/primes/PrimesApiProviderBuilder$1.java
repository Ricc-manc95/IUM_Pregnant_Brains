// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.content.Context;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            Supplier, PrimesApiProviderBuilder

final class this._cls0
    implements Supplier
{

    private final PrimesApiProviderBuilder this$0;

    public final Object get()
    {
        return application.getSharedPreferences("primes", 0);
    }

    ()
    {
        this$0 = PrimesApiProviderBuilder.this;
        super();
    }
}
