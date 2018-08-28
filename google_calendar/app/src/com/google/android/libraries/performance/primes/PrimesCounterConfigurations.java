// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


public final class PrimesCounterConfigurations
{

    public static final PrimesCounterConfigurations DEFAULT = new PrimesCounterConfigurations(false);

    PrimesCounterConfigurations(boolean flag)
    {
    }

    static 
    {
        new Builder();
    }

    private class Builder
    {

        Builder()
        {
        }
    }

}
