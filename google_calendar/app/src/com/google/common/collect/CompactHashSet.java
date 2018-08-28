// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            Hashing

class CompactHashSet extends AbstractSet
    implements Serializable
{

    public transient Object elements[];
    public transient long entries[];
    private transient float loadFactor;
    public transient int modCount;
    private transient int size;
    private transient int table[];
    private transient int threshold;

    CompactHashSet()
    {
        init(3, 1.0F);
    }

    CompactHashSet(int i)
    {
        init(i, 1.0F);
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        init(3, 1.0F);
        int i = objectinputstream.readInt();
        do
        {
            i--;
            if (i >= 0)
            {
                add(objectinputstream.readObject());
            } else
            {
                return;
            }
        } while (true);
    }

    private void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.defaultWriteObject();
        objectoutputstream.writeInt(size);
        for (Iterator iterator1 = iterator(); iterator1.hasNext(); objectoutputstream.writeObject(iterator1.next())) { }
    }

    public boolean add(Object obj)
    {
        long al[];
        Object aobj[];
        int i;
        int i2;
        int k2;
        al = entries;
        aobj = elements;
        int i1;
        int i3;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        i2 = (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
        i3 = i2 & table.length - 1;
        k2 = size;
        i1 = table[i3];
        i = i1;
        if (i1 != -1) goto _L2; else goto _L1
_L1:
        table[i3] = k2;
_L6:
        if (k2 == 0x7fffffff)
        {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        break; /* Loop/switch isn't completed */
_L4:
        int j1;
        i = j1;
_L2:
        long l4;
        l4 = al[i];
        if ((int)(l4 >>> 32) == i2)
        {
            Object obj1 = aobj[i];
            if (obj == obj1 || obj != null && obj.equals(obj1))
            {
                j1 = 1;
            } else
            {
                j1 = 0;
            }
            if (j1 != 0)
            {
                return false;
            }
        }
        j1 = (int)l4;
        if (j1 != -1) goto _L4; else goto _L3
_L3:
        al[i] = l4 & 0xffffffff00000000L | 0xffffffffL & (long)k2;
        if (true) goto _L6; else goto _L5
_L5:
        int j3 = k2 + 1;
        int l3 = entries.length;
        if (j3 > l3)
        {
            int k1 = Math.max(1, l3 >>> 1) + l3;
            int j = k1;
            if (k1 < 0)
            {
                j = 0x7fffffff;
            }
            if (j != l3)
            {
                resizeEntries(j);
            }
        }
        insertEntry(k2, obj, i2);
        size = j3;
        if (k2 >= threshold)
        {
            int k = table.length * 2;
            if (table.length >= 0x40000000)
            {
                threshold = 0x7fffffff;
            } else
            {
                int l1 = (int)((float)k * loadFactor);
                obj = new int[k];
                Arrays.fill(((int []) (obj)), -1);
                long al1[] = entries;
                int j2 = obj.length;
                for (int l = 0; l < size; l++)
                {
                    int l2 = (int)(al1[l] >>> 32);
                    int k3 = l2 & j2 - 1;
                    int i4 = obj[k3];
                    obj[k3] = l;
                    long l5 = l2;
                    al1[l] = (long)i4 & 0xffffffffL | l5 << 32;
                }

                threshold = l1 + 1;
                table = ((int []) (obj));
            }
        }
        modCount = modCount + 1;
        return true;
    }

    int adjustAfterRemove(int i, int j)
    {
        return i - 1;
    }

    public void clear()
    {
        modCount = modCount + 1;
        Arrays.fill(elements, 0, size, null);
        Arrays.fill(table, -1);
        Arrays.fill(entries, -1L);
        size = 0;
    }

    public boolean contains(Object obj)
    {
        boolean flag1 = false;
        int i;
        int j;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        j = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15));
        i = table[table.length - 1 & j];
        do
        {
            long l;
label0:
            {
                boolean flag = flag1;
                if (i != -1)
                {
                    l = entries[i];
                    if ((int)(l >>> 32) != j)
                    {
                        break label0;
                    }
                    Object obj1 = elements[i];
                    if (obj == obj1 || obj != null && obj.equals(obj1))
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i == 0)
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            i = (int)l;
        } while (true);
    }

    int firstEntryIndex()
    {
        return !isEmpty() ? 0 : -1;
    }

    int getSuccessor(int i)
    {
        if (i + 1 < size)
        {
            return i + 1;
        } else
        {
            return -1;
        }
    }

    void init(int i, float f)
    {
        boolean flag1 = false;
        boolean flag;
        if (i >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Initial capacity must be non-negative"));
        }
        flag = flag1;
        if (f > 0.0F)
        {
            flag = true;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Illegal load factor"));
        } else
        {
            int j = Hashing.closedTableSize(i, f);
            long al[] = new int[j];
            Arrays.fill(al, -1);
            table = al;
            loadFactor = f;
            elements = new Object[i];
            al = new long[i];
            Arrays.fill(al, -1L);
            entries = al;
            threshold = Math.max(1, (int)((float)j * f));
            return;
        }
    }

    void insertEntry(int i, Object obj, int j)
    {
        entries[i] = (long)j << 32 | 0xffffffffL;
        elements[i] = obj;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public Iterator iterator()
    {
        return new _cls1();
    }

    void moveEntry(int i)
    {
        int i1 = size() - 1;
        if (i < i1)
        {
            elements[i] = elements[i1];
            elements[i1] = null;
            long l1 = entries[i1];
            entries[i] = l1;
            entries[i1] = -1L;
            int j = (int)(l1 >>> 32);
            int j1 = table.length - 1 & j;
            int k = table[j1];
            j = k;
            if (k == i1)
            {
                table[j1] = i;
                return;
            }
            do
            {
                long l2 = entries[j];
                l = (int)l2;
                if (l != i1)
                {
                    j = l;
                } else
                {
                    entries[j] = 0xffffffff00000000L & l2 | 0xffffffffL & (long)i;
                    return;
                }
            } while (true);
        } else
        {
            elements[i] = null;
            entries[i] = -1L;
            return;
        }
    }

    public boolean remove(Object obj)
    {
        int i;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        return remove(obj, (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L));
    }

    final boolean remove(Object obj, int i)
    {
        int i1 = i & table.length - 1;
        int j = table[i1];
        if (j == -1)
        {
            return false;
        }
        int k = -1;
        do
        {
            if ((int)(entries[j] >>> 32) == i)
            {
                Object obj1 = elements[j];
                boolean flag;
                if (obj == obj1 || obj != null && obj.equals(obj1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    if (k == -1)
                    {
                        table[i1] = (int)entries[j];
                    } else
                    {
                        entries[k] = entries[k] & 0xffffffff00000000L | 0xffffffffL & (long)(int)entries[j];
                    }
                    moveEntry(j);
                    size = size - 1;
                    modCount = modCount + 1;
                    return true;
                }
            }
            int l = (int)entries[j];
            if (l == -1)
            {
                return false;
            }
            k = j;
            j = l;
        } while (true);
    }

    void resizeEntries(int i)
    {
        elements = Arrays.copyOf(elements, i);
        long al[] = entries;
        int j = al.length;
        al = Arrays.copyOf(al, i);
        if (i > j)
        {
            Arrays.fill(al, j, i, -1L);
        }
        entries = al;
    }

    public int size()
    {
        return size;
    }

    public Object[] toArray()
    {
        return Arrays.copyOf(elements, size);
    }

    public Object[] toArray(Object aobj[])
    {
        Object aobj2[];
        int i;
        aobj2 = elements;
        i = size;
        Preconditions.checkPositionIndexes(0, i + 0, aobj2.length);
        if (aobj.length >= i) goto _L2; else goto _L1
_L1:
        Object aobj1[] = (Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), i);
_L4:
        System.arraycopy(((Object) (aobj2)), 0, ((Object) (aobj1)), 0, i);
        return aobj1;
_L2:
        aobj1 = aobj;
        if (aobj.length > i)
        {
            aobj[i] = null;
            aobj1 = aobj;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private class _cls1
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

        _cls1()
        {
            this$0 = CompactHashSet.this;
            super();
            expectedModCount = modCount;
            index = firstEntryIndex();
            indexToRemove = -1;
        }
    }

}
