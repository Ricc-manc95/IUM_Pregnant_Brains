// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.os.SystemClock;
import com.google.android.libraries.stitch.util.Preconditions;

public final class TimerEvent
{

    public static final TimerEvent EMPTY_TIMER = new TimerEvent();
    public long endMs;
    public boolean hasTrace;
    public final long startMs;
    public int timerStatus$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0;

    TimerEvent()
    {
        this(SystemClock.elapsedRealtime());
    }

    private TimerEvent(long l)
    {
        endMs = -1L;
        timerStatus$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0 = android.support.v4.content.ModernAsyncTask.Status.UNKNOWN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0;
        hasTrace = false;
        startMs = l;
    }

    TimerEvent(long l, long l1)
    {
        endMs = -1L;
        timerStatus$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0 = android.support.v4.content.ModernAsyncTask.Status.UNKNOWN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0;
        hasTrace = false;
        boolean flag;
        if (l1 >= l)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(Preconditions.format("End time %s is before start time %s.", new Object[] {
                Long.valueOf(l1), Long.valueOf(l)
            }));
        } else
        {
            startMs = l;
            endMs = l1;
            return;
        }
    }

    public static boolean isEmpty(TimerEvent timerevent)
    {
        return timerevent == null || timerevent == EMPTY_TIMER;
    }

}
