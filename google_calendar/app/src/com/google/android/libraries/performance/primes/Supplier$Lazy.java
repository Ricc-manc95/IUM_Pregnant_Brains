// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            Supplier

public final class factory
    implements Supplier
{

    private volatile Supplier factory;
    private volatile Object instance;

    public final Object get()
    {
        if (instance != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (instance == null)
        {
            instance = factory.get();
            factory = null;
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        return instance;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public (Supplier supplier)
    {
        factory = supplier;
    }
}
