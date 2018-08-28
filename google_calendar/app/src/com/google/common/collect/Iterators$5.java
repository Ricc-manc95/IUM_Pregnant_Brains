// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            AbstractIterator

public final class ator extends AbstractIterator
{

    private final Predicate val$retainIfTrue;
    private final Iterator val$unfiltered;

    protected final Object computeNext()
    {
        while (val$unfiltered.hasNext()) 
        {
            Object obj = val$unfiltered.next();
            if (val$retainIfTrue.apply(obj))
            {
                return obj;
            }
        }
        super.state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0 = android.support.v4.content.ask.Status.DONE._fld9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0;
        return null;
    }

    public Task.Status()
    {
        val$unfiltered = iterator;
        val$retainIfTrue = predicate;
        super();
    }
}
