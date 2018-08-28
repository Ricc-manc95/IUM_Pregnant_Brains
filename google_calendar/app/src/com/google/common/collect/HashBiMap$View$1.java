// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package com.google.common.collect:
//            HashBiMap

final class Map
    implements Iterator
{

    private int expectedModCount;
    private int index;
    private int indexToRemove;
    private int remaining;
    private final t this$0;

    public final boolean hasNext()
    {
        if (Map.modCount != expectedModCount)
        {
            throw new ConcurrentModificationException();
        }
        return index != -2 && remaining > 0;
    }

    public final Object next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        } else
        {
            Object obj = rEntry(index);
            indexToRemove = index;
            index = Map.nextInInsertionOrder[index];
            remaining = remaining - 1;
            return obj;
        }
    }

    public final void remove()
    {
        boolean flag1 = false;
        if (Map.modCount != expectedModCount)
        {
            throw new ConcurrentModificationException();
        }
        boolean flag;
        if (indexToRemove != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("no calls to next() since the last call to remove()"));
        }
        HashBiMap hashbimap = Map;
        int j = indexToRemove;
        Object obj = hashbimap.keys[j];
        int i;
        int k;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        k = (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
        obj = hashbimap.values[j];
        if (obj == null)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = obj.hashCode();
        }
        hashbimap.removeEntry(j, k, (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L));
        if (index == Map.size)
        {
            index = indexToRemove;
        }
        indexToRemove = -1;
        expectedModCount = Map.modCount;
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
        index = Map.firstInInsertionOrder;
        indexToRemove = -1;
        expectedModCount = Map.modCount;
        remaining = Map.size;
    }
}
