// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


final class ure
    implements com.google.android.libraries.performance.primes.battery.
{

    public static final com.google.android.libraries.performance.primes.battery. $instance = new <init>();

    public final long getTime()
    {
        return System.currentTimeMillis();
    }


    private ure()
    {
    }
}
