// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.tracing;

import com.google.android.libraries.performance.primes.PrimesLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import logs.proto.wireless.performance.mobile.nano.Span;

// Referenced classes of package com.google.android.libraries.performance.primes.tracing:
//            SpanEvent, NoopList

public final class SpanProtoGenerator
{

    private long nextId;
    private final List result = new ArrayList();
    private final SpanEvent rootSpan;

    public SpanProtoGenerator(SpanEvent spanevent)
    {
        rootSpan = spanevent;
        nextId = 1L;
    }

    private final void traverse(SpanEvent spanevent, long l)
    {
        long l1 = -1L;
        List list = spanevent.children;
        spanevent.children = NoopList.NOOP_LIST;
        if (spanevent.spanType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0 != android.support.v4.content.ModernAsyncTask.Status.THREAD_ROOT_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0 || !list.isEmpty())
        {
            long l2 = nextId;
            nextId = 1L + l2;
            Span span = new Span();
            int i;
            if (spanevent.eventNameType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0 == android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0)
            {
                span.constantName = spanevent.spanName;
            } else
            {
                span.name = spanevent.spanName;
            }
            span.startTimeMs = Long.valueOf(spanevent.startMs);
            if (spanevent.endMs != -1L)
            {
                l1 = spanevent.endMs - spanevent.startMs;
            }
            span.durationMs = Long.valueOf(l1);
            span.threadId = Long.valueOf(spanevent.threadId);
            span.id = Long.valueOf(l2);
            span.parentId = Long.valueOf(l);
            if (spanevent.spanType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0 == android.support.v4.content.ModernAsyncTask.Status.ROOT_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            span.spanType = i;
            if (spanevent.spanType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0 == android.support.v4.content.ModernAsyncTask.Status.THREAD_ROOT_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0)
            {
                span.durationMs = Long.valueOf(((SpanEvent)list.get(list.size() - 1)).endMs - spanevent.startMs);
            }
            result.add(span);
            spanevent = list.iterator();
            while (spanevent.hasNext()) 
            {
                traverse((SpanEvent)spanevent.next(), span.id.longValue());
            }
        }
    }

    public final Span[] generate()
    {
        traverse(rootSpan, 0L);
        if (result.size() == 1)
        {
            PrimesLog.log(3, "TraceDataToProto", "No other span except for root span. Dropping trace...", new Object[0]);
            return null;
        } else
        {
            return (Span[])result.toArray(new Span[result.size()]);
        }
    }
}
