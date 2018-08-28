// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.TimeUnit;

// Referenced classes of package io.grpc.internal:
//            TimeProvider

final class is
    implements TimeProvider
{

    private final long offsetNanos;

    public final long currentTimeNanos()
    {
        return System.nanoTime() + offsetNanos;
    }

    ()
    {
        offsetNanos = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()) - System.nanoTime();
    }
}
