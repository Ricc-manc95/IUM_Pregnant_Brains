// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


final class CpuWallTime
{

    public final long cpuNanos;
    public final long wallNanos;

    CpuWallTime(long l, long l1)
    {
        wallNanos = l;
        cpuNanos = l1;
    }
}
