// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.common.base:
//            Ticker, Platform

public final class Stopwatch
{

    public long elapsedNanos;
    public boolean isRunning;
    public long startTick;
    public final Ticker ticker;

    public Stopwatch()
    {
        ticker = Ticker.SYSTEM_TICKER;
    }

    public final long elapsed(TimeUnit timeunit)
    {
        long l;
        if (isRunning)
        {
            l = (ticker.read() - startTick) + elapsedNanos;
        } else
        {
            l = elapsedNanos;
        }
        return timeunit.convert(l, TimeUnit.NANOSECONDS);
    }

    public final Stopwatch start()
    {
        boolean flag;
        if (!isRunning)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("This stopwatch is already running."));
        } else
        {
            isRunning = true;
            startTick = ticker.read();
            return this;
        }
    }

    public final String toString()
    {
        String s1;
        TimeUnit timeunit;
        long l;
        if (isRunning)
        {
            l = (ticker.read() - startTick) + elapsedNanos;
        } else
        {
            l = elapsedNanos;
        }
        if (TimeUnit.DAYS.convert(l, TimeUnit.NANOSECONDS) > 0L)
        {
            timeunit = TimeUnit.DAYS;
        } else
        if (TimeUnit.HOURS.convert(l, TimeUnit.NANOSECONDS) > 0L)
        {
            timeunit = TimeUnit.HOURS;
        } else
        if (TimeUnit.MINUTES.convert(l, TimeUnit.NANOSECONDS) > 0L)
        {
            timeunit = TimeUnit.MINUTES;
        } else
        if (TimeUnit.SECONDS.convert(l, TimeUnit.NANOSECONDS) > 0L)
        {
            timeunit = TimeUnit.SECONDS;
        } else
        if (TimeUnit.MILLISECONDS.convert(l, TimeUnit.NANOSECONDS) > 0L)
        {
            timeunit = TimeUnit.MILLISECONDS;
        } else
        if (TimeUnit.MICROSECONDS.convert(l, TimeUnit.NANOSECONDS) > 0L)
        {
            timeunit = TimeUnit.MICROSECONDS;
        } else
        {
            timeunit = TimeUnit.NANOSECONDS;
        }
        s1 = Platform.formatCompact4Digits((double)l / (double)TimeUnit.NANOSECONDS.convert(1L, timeunit));
        _cls1..SwitchMap.java.util.concurrent.TimeUnit[timeunit.ordinal()];
        JVM INSTR tableswitch 1 7: default 112
    //                   1 245
    //                   2 289
    //                   3 295
    //                   4 301
    //                   5 307
    //                   6 313
    //                   7 319;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new AssertionError();
_L2:
        String s = "ns";
_L10:
        return (new StringBuilder(String.valueOf(s1).length() + 1 + String.valueOf(s).length())).append(s1).append(" ").append(s).toString();
_L3:
        s = "\u03BCs";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "ms";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "s";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "min";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "h";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "d";
        if (true) goto _L10; else goto _L9
_L9:
    }

    private class _cls1
    {

        public static final int $SwitchMap$java$util$concurrent$TimeUnit[];

        static 
        {
            $SwitchMap$java$util$concurrent$TimeUnit = new int[TimeUnit.values().length];
            try
            {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.NANOSECONDS.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror6) { }
            try
            {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MICROSECONDS.ordinal()] = 2;
            }
            catch (NoSuchFieldError nosuchfielderror5) { }
            try
            {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MILLISECONDS.ordinal()] = 3;
            }
            catch (NoSuchFieldError nosuchfielderror4) { }
            try
            {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.SECONDS.ordinal()] = 4;
            }
            catch (NoSuchFieldError nosuchfielderror3) { }
            try
            {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MINUTES.ordinal()] = 5;
            }
            catch (NoSuchFieldError nosuchfielderror2) { }
            try
            {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.HOURS.ordinal()] = 6;
            }
            catch (NoSuchFieldError nosuchfielderror1) { }
            try
            {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.DAYS.ordinal()] = 7;
            }
            catch (NoSuchFieldError nosuchfielderror)
            {
                return;
            }
        }
    }

}
