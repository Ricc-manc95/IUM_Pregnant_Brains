// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package com.google.common.collect:
//            AbstractMapEntry

final class dCount
    implements Iterator
{

    private int expectedModCount;
    private k nextEntry;
    private final k this$1;
    private k toRemove;

    public final boolean hasNext()
    {
        if (dCount != expectedModCount)
        {
            throw new ConcurrentModificationException();
        }
        return nextEntry != this._cls1.this;
    }

    public final Object next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        } else
        {
            this._cls1 _lcls1 = (hasNext)nextEntry;
            Object obj = _lcls1.getValue();
            toRemove = _lcls1;
            nextEntry = _lcls1.successorInValueSet;
            return obj;
        }
    }

    public final void remove()
    {
        if (dCount != expectedModCount)
        {
            throw new ConcurrentModificationException();
        }
        boolean flag;
        if (toRemove != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("no calls to next() since the last call to remove()"));
        } else
        {
            move(toRemove.getValue());
            expectedModCount = dCount;
            toRemove = null;
            return;
        }
    }

    k()
    {
        this$1 = this._cls1.this;
        super();
        nextEntry = rstEntry;
        expectedModCount = dCount;
    }
}
