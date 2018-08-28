// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

// Referenced classes of package com.google.common.collect:
//            CompactHashSet, ObjectArrays

final class CompactLinkedHashSet extends CompactHashSet
{

    private transient int firstEntry;
    private transient int lastEntry;
    private transient int predecessor[];
    private transient int successor[];

    CompactLinkedHashSet()
    {
    }

    CompactLinkedHashSet(int i)
    {
        super(i);
    }

    private final void succeeds(int i, int j)
    {
        if (i == -2)
        {
            firstEntry = j;
        } else
        {
            successor[i] = j;
        }
        if (j == -2)
        {
            lastEntry = i;
            return;
        } else
        {
            predecessor[j] = i;
            return;
        }
    }

    final int adjustAfterRemove(int i, int j)
    {
        if (i == size())
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
        Arrays.fill(predecessor, -1);
        Arrays.fill(successor, -1);
    }

    final int firstEntryIndex()
    {
        return firstEntry;
    }

    final int getSuccessor(int i)
    {
        return successor[i];
    }

    final void init(int i, float f)
    {
        super.init(i, f);
        predecessor = new int[i];
        successor = new int[i];
        Arrays.fill(predecessor, -1);
        Arrays.fill(successor, -1);
        firstEntry = -2;
        lastEntry = -2;
    }

    final void insertEntry(int i, Object obj, int j)
    {
        super.insertEntry(i, obj, j);
        succeeds(lastEntry, i);
        succeeds(i, -2);
    }

    final void moveEntry(int i)
    {
        int j = size() - 1;
        super.moveEntry(i);
        succeeds(predecessor[i], successor[i]);
        if (j != i)
        {
            succeeds(predecessor[j], i);
            succeeds(i, successor[j]);
        }
        predecessor[j] = -1;
        successor[j] = -1;
    }

    final void resizeEntries(int i)
    {
        super.resizeEntries(i);
        int j = predecessor.length;
        predecessor = Arrays.copyOf(predecessor, i);
        successor = Arrays.copyOf(successor, i);
        if (j < i)
        {
            Arrays.fill(predecessor, j, i, -1);
            Arrays.fill(successor, j, i, -1);
        }
    }

    public final Object[] toArray()
    {
        return ObjectArrays.fillArray(this, new Object[size()]);
    }

    public final Object[] toArray(Object aobj[])
    {
        int i = size();
        if (aobj.length < i)
        {
            aobj = (Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), i);
        }
        ObjectArrays.fillArray(this, aobj);
        if (aobj.length > i)
        {
            aobj[i] = null;
        }
        return aobj;
    }
}
