// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            FluentIterable

public final class le extends FluentIterable
{

    private final Predicate val$retainIfTrue;
    private final Iterable val$unfiltered;

    public final Iterator iterator()
    {
        Iterator iterator1 = val$unfiltered.iterator();
        Predicate predicate = val$retainIfTrue;
        if (iterator1 == null)
        {
            throw new NullPointerException();
        }
        if (predicate == null)
        {
            throw new NullPointerException();
        } else
        {
            return new <init>(iterator1, predicate);
        }
    }

    public le()
    {
        val$unfiltered = iterable;
        val$retainIfTrue = predicate;
        super();
    }
}
