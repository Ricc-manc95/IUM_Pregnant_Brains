// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.tracing;

import java.util.Comparator;

// Referenced classes of package com.google.android.libraries.performance.primes.tracing:
//            SpanEvent

final class Q
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        obj = (SpanEvent)obj;
        obj1 = (SpanEvent)obj1;
        return (int)(((SpanEvent) (obj)).startMs - ((SpanEvent) (obj1)).startMs);
    }


    private Q()
    {
    }
}
