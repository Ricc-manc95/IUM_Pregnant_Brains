// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof.collect;


// Referenced classes of package com.google.android.libraries.performance.primes.hprof.collect:
//            IntObjectMap

public final class values
    implements values
{

    public int key;
    private final int keys[];
    private int nextIndex;
    public Object value;
    private final Object values[];

    public final Object getValue()
    {
        return value;
    }

    public final boolean next()
    {
        Object aobj[];
        int i;
        for (value = null; (value == null || value == IntObjectMap.DELETED) && nextIndex < values.length; value = aobj[i])
        {
            aobj = values;
            i = nextIndex;
            nextIndex = i + 1;
        }

        if (nextIndex > 0)
        {
            key = keys[nextIndex - 1];
        }
        return value != null && value != IntObjectMap.DELETED;
    }

    public (int ai[], Object aobj[])
    {
        keys = ai;
        values = aobj;
    }
}
