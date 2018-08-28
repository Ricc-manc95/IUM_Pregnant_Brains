// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            UrlSanitizer

public final class PrimesNetworkConfigurations
{

    public PrimesNetworkConfigurations()
    {
        this(false);
    }

    private PrimesNetworkConfigurations(boolean flag)
    {
        this(false, false);
    }

    private PrimesNetworkConfigurations(boolean flag, UrlSanitizer urlsanitizer, boolean flag1, int i)
    {
    }

    private PrimesNetworkConfigurations(boolean flag, boolean flag1)
    {
        this(flag, false, 50);
    }

    private PrimesNetworkConfigurations(boolean flag, boolean flag1, int i)
    {
        this(flag, null, flag1, 50);
    }

    static 
    {
        new PrimesNetworkConfigurations(false);
    }
}
