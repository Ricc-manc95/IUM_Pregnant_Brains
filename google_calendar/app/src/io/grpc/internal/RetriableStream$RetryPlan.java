// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


final class backoffNanos
{

    public final long backoffNanos;
    public final boolean shouldRetry;

    (boolean flag, long l)
    {
        shouldRetry = flag;
        backoffNanos = l;
    }
}
