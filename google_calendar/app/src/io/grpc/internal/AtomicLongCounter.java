// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package io.grpc.internal:
//            LongCounter

final class AtomicLongCounter
    implements LongCounter
{

    private final AtomicLong counter = new AtomicLong();

    AtomicLongCounter()
    {
    }

    public final void add(long l)
    {
        counter.getAndAdd(1L);
    }
}
