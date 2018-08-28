// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Arrays;

// Referenced classes of package com.google.common.collect:
//            CompactHashMap

final class CompactLinkedHashMap extends CompactHashMap
{

    private final boolean accessOrder;
    private transient int firstEntry;
    private transient int lastEntry;
    private transient long links[];

    CompactLinkedHashMap()
    {
        this(3);
    }

    CompactLinkedHashMap(int i)
    {
        this(i, 1.0F, false);
    }

    private CompactLinkedHashMap(int i, float f, boolean flag)
    {
        super(i, 1.0F);
        accessOrder = false;
    }

    private final void setSucceeds(int i, int j)
    {
        if (i == -2)
        {
            firstEntry = j;
        } else
        {
            links[i] = links[i] & 0xffffffff00000000L | (long)j & 0xffffffffL;
        }
        if (j == -2)
        {
            lastEntry = i;
            return;
        } else
        {
            links[j] = links[j] & 0xffffffffL | (long)i << 32;
            return;
        }
    }

    final void accessEntry(int i)
    {
        if (accessOrder)
        {
            setSucceeds((int)(links[i] >>> 32), getSuccessor(i));
            setSucceeds(lastEntry, i);
            setSucceeds(i, -2);
            modCount = modCount + 1;
        }
    }

    final int adjustAfterRemove(int i, int j)
    {
        if (i >= size())
        {
            return j;
        } else
        {
            return i;
        }
    }

    public final void clear()
    {
        super.clear();
        firstEntry = -2;
        lastEntry = -2;
    }

    final int firstEntryIndex()
    {
        return firstEntry;
    }

    final int getSuccessor(int i)
    {
        return (int)links[i];
    }

    final void init(int i, float f)
    {
        super.init(i, f);
        firstEntry = -2;
        lastEntry = -2;
        links = new long[i];
        Arrays.fill(links, -1L);
    }

    final void insertEntry(int i, Object obj, Object obj1, int j)
    {
        super.insertEntry(i, obj, obj1, j);
        setSucceeds(lastEntry, i);
        setSucceeds(i, -2);
    }

    final void moveLastEntry(int i)
    {
        int j = size() - 1;
        setSucceeds((int)(links[i] >>> 32), getSuccessor(i));
        if (i < j)
        {
            setSucceeds((int)(links[j] >>> 32), i);
            setSucceeds(i, getSuccessor(j));
        }
        super.moveLastEntry(i);
    }

    final void resizeEntries(int i)
    {
        super.resizeEntries(i);
        links = Arrays.copyOf(links, i);
    }
}
