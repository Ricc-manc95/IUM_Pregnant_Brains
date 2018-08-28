// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.concurrent.TimeUnit;

public final class Deadline
    implements Comparable
{

    private static final long MAX_OFFSET;
    private static final long MIN_OFFSET;
    private static final SystemTicker SYSTEM_TICKER = new SystemTicker();
    public final long deadlineNanos;
    private volatile boolean expired;
    private final Ticker ticker;

    private Deadline(Ticker ticker1, long l, long l1, boolean flag)
    {
        ticker = ticker1;
        l1 = Math.min(MAX_OFFSET, Math.max(MIN_OFFSET, l1));
        deadlineNanos = l + l1;
        if (flag && l1 <= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        expired = flag;
    }

    private Deadline(Ticker ticker1, long l, boolean flag)
    {
        this(ticker1, ticker1.read(), l, true);
    }

    public static Deadline after(long l, TimeUnit timeunit)
    {
        SystemTicker systemticker = SYSTEM_TICKER;
        if (timeunit == null)
        {
            throw new NullPointerException(String.valueOf("units"));
        } else
        {
            return new Deadline(systemticker, timeunit.toNanos(l), true);
        }
    }

    public final int compareTo(Object obj)
    {
        obj = (Deadline)obj;
        long l = deadlineNanos - ((Deadline) (obj)).deadlineNanos;
        if (l < 0L)
        {
            return -1;
        }
        return l <= 0L ? 0 : 1;
    }

    public final boolean isExpired()
    {
label0:
        {
            if (!expired)
            {
                if (deadlineNanos - ticker.read() > 0L)
                {
                    break label0;
                }
                expired = true;
            }
            return true;
        }
        return false;
    }

    public final long timeRemaining(TimeUnit timeunit)
    {
        long l = ticker.read();
        if (!expired && deadlineNanos - l <= 0L)
        {
            expired = true;
        }
        return timeunit.convert(deadlineNanos - l, TimeUnit.NANOSECONDS);
    }

    public final String toString()
    {
        long l = timeRemaining(TimeUnit.NANOSECONDS);
        return (new StringBuilder(32)).append(l).append(" ns from now").toString();
    }

    static 
    {
        long l = TimeUnit.DAYS.toNanos(36500L);
        MAX_OFFSET = l;
        MIN_OFFSET = -l;
    }

    private class Ticker
    {

        public abstract long read();

        Ticker()
        {
        }
    }


    private class SystemTicker extends Ticker
    {

        public final long read()
        {
            return System.nanoTime();
        }

        SystemTicker()
        {
        }
    }

}
