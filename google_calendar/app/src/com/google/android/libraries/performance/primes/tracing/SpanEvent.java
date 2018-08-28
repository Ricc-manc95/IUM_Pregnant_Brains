// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.tracing;

import android.os.SystemClock;
import com.google.android.libraries.performance.primes.PrimesToken;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SpanEvent
{

    public volatile List children;
    public long endMs;
    public final int eventNameType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0;
    public String spanName;
    public int spanType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0;
    public long startMs;
    public final long threadId;

    SpanEvent(String s, int i, long l, long l1, long l2, int j)
    {
        endMs = -1L;
        spanName = s;
        eventNameType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0 = i;
        startMs = l;
        endMs = l1;
        threadId = l2;
        spanType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0 = j;
        if (spanType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0 == android.support.v4.content.ModernAsyncTask.Status.THREAD_ROOT_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0)
        {
            children = Collections.synchronizedList(new ArrayList());
            return;
        } else
        {
            children = Collections.emptyList();
            return;
        }
    }

    public static SpanEvent newSpan$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFA1P6IRB5EDA6UQR5DOTKOQJ1EPGIUR31DPJIUKRKE9KMSPPR9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357D54KIICCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUT3IC5HMIRJ75T9N0OBE8LR6ARJK4H9N0OBEAHSN0P9R55666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFEHP62OR9DPJIUKRGC5N4ATJ5DPQ3M___0(PrimesToken primestoken, String s, int i, long l, long l1, long l2, int j)
    {
        if (primestoken == null)
        {
            throw new NullPointerException();
        } else
        {
            return new SpanEvent(s, i, l, l1, l2, j);
        }
    }

    public final void addChildSpan(SpanEvent spanevent)
    {
        if (children == Collections.EMPTY_LIST)
        {
            children = new ArrayList();
        }
        children.add(spanevent);
    }

    public final void addChildSpans(List list)
    {
        if (children == Collections.EMPTY_LIST)
        {
            children = new ArrayList();
        }
        children.addAll(list);
    }

    static 
    {
        new SpanEvent("", android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0, SystemClock.elapsedRealtime(), -1L, Thread.currentThread().getId(), android.support.v4.content.ModernAsyncTask.Status.CHILD_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0);
    }
}
