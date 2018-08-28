// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.AbstractSet;
import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            HashBiMap

abstract class biMap extends AbstractSet
{

    public final HashBiMap biMap;

    public void clear()
    {
        biMap.clear();
    }

    abstract Object forEntry(int i);

    public Iterator iterator()
    {
        class _cls1
            implements Iterator
        {

            private int expectedModCount;
            private int index;
            private int indexToRemove;
            private int remaining;
            private final HashBiMap.View this$0;

            public final boolean hasNext()
            {
                if (biMap.modCount != expectedModCount)
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
                    Object obj = forEntry(index);
                    indexToRemove = index;
                    index = biMap.nextInInsertionOrder[index];
                    remaining = remaining - 1;
                    return obj;
                }
            }

            public final void remove()
            {
                boolean flag1 = false;
                if (biMap.modCount != expectedModCount)
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
                HashBiMap hashbimap = biMap;
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
                if (index == biMap.size)
                {
                    index = indexToRemove;
                }
                indexToRemove = -1;
                expectedModCount = biMap.modCount;
            }

            _cls1()
            {
                this$0 = HashBiMap.View.this;
                super();
                index = biMap.firstInInsertionOrder;
                indexToRemove = -1;
                expectedModCount = biMap.modCount;
                remaining = biMap.size;
            }
        }

        return new _cls1();
    }

    public int size()
    {
        return biMap.size;
    }

    _cls1(HashBiMap hashbimap)
    {
        biMap = hashbimap;
    }
}
