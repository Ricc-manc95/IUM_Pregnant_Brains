// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.flags;

import android.content.Context;
import com.google.android.libraries.performance.primes.PrimesLog;
import com.google.android.libraries.performance.primes.Supplier;

// Referenced classes of package com.google.android.libraries.performance.primes.flags:
//            ServiceFlags

public final class context
    implements Supplier
{

    private final Context context;

    public final Object get()
    {
        PrimesLog.log(4, "PrimesTesting", "GserviceFlagsSupplier.get()", new Object[0]);
        return ServiceFlags.readPrimesFlags(context, (new com.google.android.libraries.performance.primes.plier.context()).context());
    }

    public (Context context1)
    {
        context = context1;
    }
}
