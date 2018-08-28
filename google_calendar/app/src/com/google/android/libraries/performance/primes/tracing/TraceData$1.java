// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.tracing;

import android.os.Looper;
import android.os.SystemClock;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.libraries.performance.primes.tracing:
//            SpanEvent, TraceData

final class this._cls0 extends ThreadLocal
{

    private final TraceData this$0;

    protected final Object initialValue()
    {
        long l = Thread.currentThread().getId();
        Object obj;
        readData readdata;
        int i;
        int j;
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            obj = "UI Thread";
        } else
        {
            obj = String.valueOf(Thread.currentThread().getName());
            if (((String) (obj)).length() != 0)
            {
                obj = "Thread: ".concat(((String) (obj)));
            } else
            {
                obj = new String("Thread: ");
            }
        }
        i = android.support.v4.content.ask.Status.CONSTANT._fld9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0;
        j = android.support.v4.content.ask.Status.THREAD_ROOT_SPAN._fld9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0;
        obj = new SpanEvent(((String) (obj)), i, SystemClock.elapsedRealtime(), -1L, l, j);
        readdata = new readData(l, ((SpanEvent) (obj)));
        readdata.stack.push(obj);
        numOfSpans.incrementAndGet();
        parentSpanToThreadData.put(obj, readdata);
        return new WeakReference(readdata);
    }

    readData()
    {
        this$0 = TraceData.this;
        super();
    }
}
