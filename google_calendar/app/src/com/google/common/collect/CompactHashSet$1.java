// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package com.google.common.collect:
//            CompactHashSet

final class indexToRemove
    implements Iterator
{

    private int expectedModCount;
    private int index;
    private int indexToRemove;
    private final CompactHashSet this$0;

    public final boolean hasNext()
    {
        return index >= 0;
    }

    public final Object next()
    {
        if (modCount != expectedModCount)
        {
            throw new ConcurrentModificationException();
        }
        if (!hasNext())
        {
            throw new NoSuchElementException();
        } else
        {
            indexToRemove = index;
            Object obj = elements[index];
            index = getSuccessor(index);
            return obj;
        }
    }

    public final void remove()
    {
        if (modCount != expectedModCount)
        {
            throw new ConcurrentModificationException();
        }
        boolean flag;
        if (indexToRemove >= 0)
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
            expectedModCount = expectedModCount + 1;
            CompactHashSet.this.remove(elements[indexToRemove], (int)(entries[indexToRemove] >>> 32));
            index = adjustAfterRemove(index, indexToRemove);
            indexToRemove = -1;
            return;
        }
    }

    ()
    {
        this$0 = CompactHashSet.this;
        super();
        expectedModCount = modCount;
        index = firstEntryIndex();
        indexToRemove = -1;
    }
}
