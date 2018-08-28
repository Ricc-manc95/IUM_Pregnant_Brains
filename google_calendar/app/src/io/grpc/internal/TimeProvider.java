// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


public interface TimeProvider
{

    public static final TimeProvider SYSTEM_TIME_PROVIDER = new _cls1();

    public abstract long currentTimeNanos();


    private class _cls1
        implements TimeProvider
    {

        private final long offsetNanos;

        public final long currentTimeNanos()
        {
            return System.nanoTime() + offsetNanos;
        }

        _cls1()
        {
            offsetNanos = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()) - System.nanoTime();
        }
    }

}
