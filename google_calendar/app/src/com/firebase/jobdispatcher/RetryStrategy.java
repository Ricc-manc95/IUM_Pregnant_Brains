// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;


public final class RetryStrategy
{

    public static final RetryStrategy DEFAULT_EXPONENTIAL = new RetryStrategy(1, 30, 3600);
    public static final RetryStrategy DEFAULT_LINEAR = new RetryStrategy(2, 30, 3600);
    public final int initialBackoff;
    public final int maximumBackoff;
    public final int policy;

    RetryStrategy(int i, int j, int k)
    {
        policy = i;
        initialBackoff = j;
        maximumBackoff = k;
    }

}
