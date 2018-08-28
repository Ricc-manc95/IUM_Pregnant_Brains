// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


public final class PrimesMemoryLeakConfigurations
{

    public static final PrimesMemoryLeakConfigurations DEFAULT = new PrimesMemoryLeakConfigurations(false);
    public final boolean enabled;
    public final boolean heapDumpEnabled;

    private PrimesMemoryLeakConfigurations(boolean flag)
    {
        this(false, false);
    }

    private PrimesMemoryLeakConfigurations(boolean flag, boolean flag1)
    {
        if (flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        enabled = flag;
        heapDumpEnabled = false;
    }

}
