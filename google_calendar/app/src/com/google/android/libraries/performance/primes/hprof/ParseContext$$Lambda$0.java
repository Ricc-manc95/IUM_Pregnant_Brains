// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import com.google.android.libraries.performance.primes.hprof.collect.IntIntMap;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            ParseContext

final class arg._cls1
    implements r
{

    private final ParseContext arg$1;

    public final void addRootSize(int i, int j)
    {
        arg$1.rootTagSizes.putIfAbsent(i, j);
    }

    r(ParseContext parsecontext)
    {
        arg$1 = parsecontext;
    }
}
