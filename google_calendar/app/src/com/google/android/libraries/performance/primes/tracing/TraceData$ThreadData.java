// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.tracing;

import com.google.android.libraries.performance.primes.PrimesLog;
import java.util.ArrayDeque;

// Referenced classes of package com.google.android.libraries.performance.primes.tracing:
//            SpanEvent

final class threadSpan
{

    public final ArrayDeque stack = new ArrayDeque();
    public final long threadId;
    public final SpanEvent threadSpan;

    (long l, SpanEvent spanevent)
    {
        threadId = l;
        threadSpan = spanevent;
        PrimesLog.log(3, "TraceData", "Instantiate thread-data, thread:%d name:%s", new Object[] {
            Long.valueOf(threadId), threadSpan.spanName
        });
    }
}
