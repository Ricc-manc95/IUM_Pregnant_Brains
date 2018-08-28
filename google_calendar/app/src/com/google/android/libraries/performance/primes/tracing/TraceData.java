// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.tracing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.libraries.performance.primes.tracing:
//            SpanEvent

public final class TraceData
{

    public final ThreadLocal activeNode = new _cls1();
    public final AtomicInteger numOfSpans = new AtomicInteger(0);
    public final Map parentSpanToThreadData = new ConcurrentHashMap();
    public SpanEvent rootSpan;

    public TraceData()
    {
    }

    public final SpanEvent linkTraceAndGetRootSpan()
    {
        ArrayList arraylist = new ArrayList(parentSpanToThreadData.keySet());
        class .Lambda._cls0
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls0();

            public final int compare(Object obj, Object obj1)
            {
                obj = (SpanEvent)obj;
                obj1 = (SpanEvent)obj1;
                return (int)(((SpanEvent) (obj)).startMs - ((SpanEvent) (obj1)).startMs);
            }


            private .Lambda._cls0()
            {
            }
        }

        Collections.sort(arraylist, .Lambda._cls0..instance);
        rootSpan.addChildSpans(arraylist);
        return rootSpan;
    }

    private class _cls1 extends ThreadLocal
    {

        private final TraceData this$0;

        protected final Object initialValue()
        {
            long l = Thread.currentThread().getId();
            Object obj;
            ThreadData threaddata;
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
            i = android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0;
            j = android.support.v4.content.ModernAsyncTask.Status.THREAD_ROOT_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0;
            obj = new SpanEvent(((String) (obj)), i, SystemClock.elapsedRealtime(), -1L, l, j);
            threaddata = new ThreadData(l, ((SpanEvent) (obj)));
            threaddata.stack.push(obj);
            numOfSpans.incrementAndGet();
            parentSpanToThreadData.put(obj, threaddata);
            return new WeakReference(threaddata);
        }

        _cls1()
        {
            this$0 = TraceData.this;
            super();
        }

        private class ThreadData
        {

            public final ArrayDeque stack = new ArrayDeque();
            public final long threadId;
            public final SpanEvent threadSpan;

            ThreadData(long l, SpanEvent spanevent)
            {
                threadId = l;
                threadSpan = spanevent;
                PrimesLog.log(3, "TraceData", "Instantiate thread-data, thread:%d name:%s", new Object[] {
                    Long.valueOf(threadId), threadSpan.spanName
                });
            }
        }

    }

}
