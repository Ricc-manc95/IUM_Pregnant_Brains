// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package com.google.common.collect:
//            CompactHashMap

abstract class <init>
    implements Iterator
{

    private int currentIndex;
    private int expectedModCount;
    private int indexToRemove;
    private final CompactHashMap this$0;

    abstract Object getOutput(int i);

    public boolean hasNext()
    {
        return currentIndex >= 0;
    }

    public Object next()
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
            indexToRemove = currentIndex;
            Object obj = getOutput(currentIndex);
            currentIndex = getSuccessor(currentIndex);
            return obj;
        }
    }

    public void remove()
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
            CompactHashMap compacthashmap = CompactHashMap.this;
            int i = indexToRemove;
            compacthashmap.remove(compacthashmap.keys[i], (int)(compacthashmap.entries[i] >>> 32));
            currentIndex = adjustAfterRemove(currentIndex, indexToRemove);
            indexToRemove = -1;
            return;
        }
    }

    private ()
    {
        this$0 = CompactHashMap.this;
        super();
        expectedModCount = modCount;
        currentIndex = firstEntryIndex();
        indexToRemove = -1;
    }

    indexToRemove(byte byte0)
    {
        this();
    }
}
