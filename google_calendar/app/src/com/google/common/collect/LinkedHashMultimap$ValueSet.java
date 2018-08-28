// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Arrays;
import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            Hashing, LinkedHashMultimap

final class y extends y
    implements ink
{

    public ink firstEntry;
    private y hashTable[];
    private final Object key;
    private ink lastEntry;
    public int modCount;
    private int size;
    private final LinkedHashMultimap this$0;

    public final boolean add(Object obj)
    {
        boolean flag1 = false;
        y y1;
        int i;
        int l;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        i = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15));
        l = i & hashTable.length - 1;
        y1 = hashTable[l];
        for (y y = y1; y != null; y = y.nextInValueBucket)
        {
            if (y.matchesValue(obj, i))
            {
                return false;
            }
        }

        obj = new y(key, obj, i, y1);
        Object obj1 = lastEntry;
        ((ink) (obj1)).setSuccessorInValueSet(((ink) (obj)));
        ((ink) (obj)).setPredecessorInValueSet(((ink) (obj1)));
        ((ink) (obj)).setSuccessorInValueSet(this);
        setPredecessorInValueSet(((ink) (obj)));
        obj1 = multimapHeaderEntry.predecessorInMultimap;
        obj1.successorInMultimap = ((y) (obj));
        obj.predecessorInMultimap = ((y) (obj1));
        obj1 = multimapHeaderEntry;
        obj.successorInMultimap = ((y) (obj1));
        obj1.predecessorInMultimap = ((y) (obj));
        hashTable[l] = ((y) (obj));
        size = size + 1;
        modCount = modCount + 1;
        l = size;
        int i1 = hashTable.length;
        boolean flag = flag1;
        if ((double)l > 1.0D * (double)i1)
        {
            flag = flag1;
            if (i1 < 0x40000000)
            {
                flag = true;
            }
        }
        if (flag)
        {
            y ay[] = new y[hashTable.length << 1];
            hashTable = ay;
            int j = ay.length;
            for (obj = firstEntry; obj != this; obj = ((ink) (obj)).getSuccessorInValueSet())
            {
                y y2 = (y)obj;
                int k = y2.smearedValueHash & j - 1;
                y2.nextInValueBucket = ay[k];
                ay[k] = y2;
            }

        }
        return true;
    }

    public final void clear()
    {
        Arrays.fill(hashTable, null);
        size = 0;
        for (ink ink = firstEntry; ink != this; ink = ink.getSuccessorInValueSet())
        {
            y y1 = (y)ink;
            y y = y1.predecessorInMultimap;
            y1 = y1.successorInMultimap;
            y.successorInMultimap = y1;
            y1.predecessorInMultimap = y;
        }

        setSuccessorInValueSet(this);
        setPredecessorInValueSet(this);
        modCount = modCount + 1;
    }

    public final boolean contains(Object obj)
    {
        boolean flag1 = false;
        y y;
        int i;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        i = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15));
        y = hashTable[hashTable.length - 1 & i];
        do
        {
label0:
            {
                boolean flag = flag1;
                if (y != null)
                {
                    if (!y.matchesValue(obj, i))
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            y = y.nextInValueBucket;
        } while (true);
    }

    public final ink getPredecessorInValueSet()
    {
        return lastEntry;
    }

    public final ink getSuccessorInValueSet()
    {
        return firstEntry;
    }

    public final Iterator iterator()
    {
        class _cls1
            implements Iterator
        {

            private int expectedModCount;
            private LinkedHashMultimap.ValueSetLink nextEntry;
            private final LinkedHashMultimap.ValueSet this$1;
            private LinkedHashMultimap.ValueEntry toRemove;

            public final boolean hasNext()
            {
                if (modCount != expectedModCount)
                {
                    throw new ConcurrentModificationException();
                }
                return nextEntry != LinkedHashMultimap.ValueSet.this;
            }

            public final Object next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                } else
                {
                    LinkedHashMultimap.ValueEntry valueentry = (LinkedHashMultimap.ValueEntry)nextEntry;
                    Object obj = valueentry.getValue();
                    toRemove = valueentry;
                    nextEntry = valueentry.successorInValueSet;
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
                    LinkedHashMultimap.ValueSet.this.remove(toRemove.getValue());
                    expectedModCount = modCount;
                    toRemove = null;
                    return;
                }
            }

            _cls1()
            {
                this$1 = LinkedHashMultimap.ValueSet.this;
                super();
                nextEntry = firstEntry;
                expectedModCount = modCount;
            }
        }

        return new _cls1();
    }

    public final boolean remove(Object obj)
    {
        boolean flag1 = false;
        y y;
        Object obj1;
        int i;
        int j;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        i = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15));
        j = i & hashTable.length - 1;
        obj1 = null;
        y = hashTable[j];
        do
        {
label0:
            {
                boolean flag = flag1;
                if (y != null)
                {
                    if (!y.matchesValue(obj, i))
                    {
                        break label0;
                    }
                    if (obj1 == null)
                    {
                        hashTable[j] = y.nextInValueBucket;
                    } else
                    {
                        obj1.nextInValueBucket = y.nextInValueBucket;
                    }
                    obj = y.getPredecessorInValueSet();
                    obj1 = y.getSuccessorInValueSet();
                    ((ink) (obj)).setSuccessorInValueSet(((ink) (obj1)));
                    ((ink) (obj1)).setPredecessorInValueSet(((ink) (obj)));
                    obj = y.predecessorInMultimap;
                    y = y.successorInMultimap;
                    obj.successorInMultimap = y;
                    y.predecessorInMultimap = ((y) (obj));
                    size = size - 1;
                    modCount = modCount + 1;
                    flag = true;
                }
                return flag;
            }
            y y1 = y.nextInValueBucket;
            obj1 = y;
            y = y1;
        } while (true);
    }

    public final void setPredecessorInValueSet(ink ink)
    {
        lastEntry = ink;
    }

    public final void setSuccessorInValueSet(ink ink)
    {
        firstEntry = ink;
    }

    public final int size()
    {
        return size;
    }

    ink(Object obj, int i)
    {
        this$0 = LinkedHashMultimap.this;
        super();
        size = 0;
        modCount = 0;
        key = obj;
        firstEntry = this;
        lastEntry = this;
        hashTable = new y[Hashing.closedTableSize(i, 1.0D)];
    }
}
