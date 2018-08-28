// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof.collect;

import java.util.Iterator;

public final class MergedEnumerable
    implements IntObjectMap.Enumerable
{

    private IntObjectMap.Enumerable activeEnumerator;
    private final Iterator enumerators;

    public MergedEnumerable(Iterator iterator)
    {
        enumerators = iterator;
        if (enumerators.hasNext())
        {
            iterator = (IntObjectMap.Enumerable)enumerators.next();
        } else
        {
            iterator = null;
        }
        activeEnumerator = iterator;
    }

    public final Object getValue()
    {
        return activeEnumerator.getValue();
    }

    public final boolean next()
    {
        while (activeEnumerator != null && !activeEnumerator.next()) 
        {
            IntObjectMap.Enumerable enumerable;
            if (enumerators.hasNext())
            {
                enumerable = (IntObjectMap.Enumerable)enumerators.next();
            } else
            {
                enumerable = null;
            }
            activeEnumerator = enumerable;
        }
        return activeEnumerator != null;
    }
}
