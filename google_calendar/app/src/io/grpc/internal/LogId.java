// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.atomic.AtomicLong;

public final class LogId
{

    public static final AtomicLong idAlloc = new AtomicLong();
    public final long id;
    private final String tag;

    public LogId(String s, long l)
    {
        tag = s;
        id = l;
    }

    public final String toString()
    {
        String s = tag;
        long l = id;
        return (new StringBuilder(String.valueOf(s).length() + 21)).append(s).append("-").append(l).toString();
    }

}
