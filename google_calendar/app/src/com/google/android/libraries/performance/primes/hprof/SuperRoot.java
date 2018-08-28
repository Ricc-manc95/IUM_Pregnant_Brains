// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import java.util.List;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            HprofObject, ParseContext

public final class SuperRoot extends HprofObject
{

    private final List roots;

    public SuperRoot(List list)
    {
        super(0);
        roots = list;
    }

    public final String buildLeakSegment(ParseContext parsecontext, int i)
    {
        return null;
    }

    public final int computeShallowSize(ParseContext parsecontext)
    {
        return 0;
    }

    public final int getChildCount(ParseContext parsecontext)
    {
        return -1;
    }

    public final String getChildName(ParseContext parsecontext, int i)
    {
        return null;
    }

    public final int getChildValue(ParseContext parsecontext, int i)
    {
        return -1;
    }

    public final Iterable getReferencesForDominators()
    {
        return roots;
    }
}
