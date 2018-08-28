// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            Hashing

class CompactHashMap extends AbstractMap
    implements Serializable
{

    public transient long entries[];
    private transient Set entrySetView;
    private transient Set keySetView;
    public transient Object keys[];
    private transient float loadFactor;
    public transient int modCount;
    public transient int size;
    private transient int table[];
    private transient int threshold;
    public transient Object values[];
    private transient Collection valuesView;

    CompactHashMap()
    {
        init(3, 1.0F);
    }

    CompactHashMap(int i)
    {
        this(i, 1.0F);
    }

    CompactHashMap(int i, float f)
    {
        init(i, f);
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
                put(objectinputstream.readObject(), objectinputstream.readObject());
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
        for (int i = 0; i < size; i++)
        {
            objectoutputstream.writeObject(keys[i]);
            objectoutputstream.writeObject(values[i]);
        }

    }

    void accessEntry(int i)
    {
    }

    int adjustAfterRemove(int i, int j)
    {
        return i - 1;
    }

    public void clear()
    {
        modCount = modCount + 1;
        Arrays.fill(keys, 0, size, null);
        Arrays.fill(values, 0, size, null);
        Arrays.fill(table, -1);
        Arrays.fill(entries, -1L);
        size = 0;
    }

    public boolean containsKey(Object obj)
    {
        return indexOf(obj) != -1;
    }

    public boolean containsValue(Object obj)
    {
        boolean flag2 = false;
        int i = 0;
        do
        {
label0:
            {
                boolean flag1 = flag2;
                if (i < size)
                {
                    Object obj1 = values[i];
                    boolean flag;
                    if (obj == obj1 || obj != null && obj.equals(obj1))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        break label0;
                    }
                    flag1 = true;
                }
                return flag1;
            }
            i++;
        } while (true);
    }

    public Set entrySet()
    {
        if (entrySetView == null)
        {
            EntrySetView entrysetview = new EntrySetView();
            entrySetView = entrysetview;
            return entrysetview;
        } else
        {
            return entrySetView;
        }
    }

    int firstEntryIndex()
    {
        return !isEmpty() ? 0 : -1;
    }

    public Object get(Object obj)
    {
        int i = indexOf(obj);
        accessEntry(i);
        if (i == -1)
        {
            return null;
        } else
        {
            return values[i];
        }
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

    final int indexOf(Object obj)
    {
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
        boolean flag;
        long l;
        for (i = table[table.length - 1 & j]; i != -1; i = (int)l)
        {
            l = entries[i];
            if ((int)(l >>> 32) != j)
            {
                continue;
            }
            Object obj1 = keys[i];
            if (obj == obj1 || obj != null && obj.equals(obj1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return i;
            }
        }

        return -1;
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
            keys = new Object[i];
            values = new Object[i];
            al = new long[i];
            Arrays.fill(al, -1L);
            entries = al;
            threshold = Math.max(1, (int)((float)j * f));
            return;
        }
    }

    void insertEntry(int i, Object obj, Object obj1, int j)
    {
        entries[i] = (long)j << 32 | 0xffffffffL;
        keys[i] = obj;
        values[i] = obj1;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public Set keySet()
    {
        if (keySetView == null)
        {
            KeySetView keysetview = new KeySetView();
            keySetView = keysetview;
            return keysetview;
        } else
        {
            return keySetView;
        }
    }

    void moveLastEntry(int i)
    {
        int i1 = size() - 1;
        if (i < i1)
        {
            keys[i] = keys[i1];
            values[i] = values[i1];
            keys[i1] = null;
            values[i1] = null;
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
            keys[i] = null;
            values[i] = null;
            entries[i] = -1L;
            return;
        }
    }

    public Object put(Object obj, Object obj1)
    {
        Object aobj[];
        long al[];
        Object aobj1[];
        int i;
        int i2;
        int k2;
        al = entries;
        aobj1 = keys;
        aobj = values;
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
            Object obj2 = aobj1[i];
            if (obj == obj2 || obj != null && obj.equals(obj2))
            {
                j1 = 1;
            } else
            {
                j1 = 0;
            }
            if (j1 != 0)
            {
                obj = aobj[i];
                aobj[i] = obj1;
                accessEntry(i);
                return obj;
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
        insertEntry(k2, obj, obj1, i2);
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
                obj1 = entries;
                int j2 = obj.length;
                for (int l = 0; l < size; l++)
                {
                    int l2 = (int)(obj1[l] >>> 32);
                    int k3 = l2 & j2 - 1;
                    int i4 = obj[k3];
                    obj[k3] = l;
                    long l5 = l2;
                    obj1[l] = (long)i4 & 0xffffffffL | l5 << 32;
                }

                threshold = l1 + 1;
                table = ((int []) (obj));
            }
        }
        modCount = modCount + 1;
        return null;
    }

    public Object remove(Object obj)
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

    final Object remove(Object obj, int i)
    {
        int i1 = i & table.length - 1;
        int j = table[i1];
        if (j == -1)
        {
            return null;
        }
        int k = -1;
        do
        {
            if ((int)(entries[j] >>> 32) == i)
            {
                Object obj1 = keys[j];
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
                    obj = values[j];
                    if (k == -1)
                    {
                        table[i1] = (int)entries[j];
                    } else
                    {
                        entries[k] = entries[k] & 0xffffffff00000000L | (long)(int)entries[j] & 0xffffffffL;
                    }
                    moveLastEntry(j);
                    size = size - 1;
                    modCount = modCount + 1;
                    return obj;
                }
            }
            int l = (int)entries[j];
            if (l == -1)
            {
                return null;
            }
            k = j;
            j = l;
        } while (true);
    }

    void resizeEntries(int i)
    {
        keys = Arrays.copyOf(keys, i);
        values = Arrays.copyOf(values, i);
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

    public Collection values()
    {
        if (valuesView == null)
        {
            ValuesView valuesview = new ValuesView();
            valuesView = valuesview;
            return valuesview;
        } else
        {
            return valuesView;
        }
    }

    private class EntrySetView extends AbstractSet
    {

        private final CompactHashMap this$0;

        public final void clear()
        {
            CompactHashMap.this.clear();
        }

        public final boolean contains(Object obj)
        {
            if (obj instanceof java.util.Map.Entry)
            {
                Object obj1 = (java.util.Map.Entry)obj;
                int i = indexOf(((java.util.Map.Entry) (obj1)).getKey());
                if (i != -1)
                {
                    obj = values[i];
                    obj1 = ((java.util.Map.Entry) (obj1)).getValue();
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
                        return true;
                    }
                }
                return false;
            } else
            {
                return false;
            }
        }

        public final Iterator iterator()
        {
            return new _cls2();
        }

        public final boolean remove(Object obj)
        {
            if (obj instanceof java.util.Map.Entry)
            {
                Object obj1 = (java.util.Map.Entry)obj;
                int i = indexOf(((java.util.Map.Entry) (obj1)).getKey());
                if (i != -1)
                {
                    obj = values[i];
                    obj1 = ((java.util.Map.Entry) (obj1)).getValue();
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
                        obj = CompactHashMap.this;
                        ((CompactHashMap) (obj)).remove(((CompactHashMap) (obj)).keys[i], (int)(((CompactHashMap) (obj)).entries[i] >>> 32));
                        return true;
                    }
                }
            }
            return false;
        }

        public final int size()
        {
            return CompactHashMap.this.size;
        }

        EntrySetView()
        {
            this$0 = CompactHashMap.this;
            super();
        }

        private class _cls2 extends Itr
        {
            private class Itr
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

                private Itr()
                {
                    this$0 = CompactHashMap.this;
                    super();
                    expectedModCount = modCount;
                    currentIndex = firstEntryIndex();
                    indexToRemove = -1;
                }

                Itr(byte byte0)
                {
                    this();
                }
            }


            private final CompactHashMap this$0;

            final Object getOutput(int i)
            {
                return new MapEntry(i);
            }

            _cls2()
            {
                this$0 = CompactHashMap.this;
                super((byte)0);
            }

            private class MapEntry extends AbstractMapEntry
            {

                private final Object key;
                private int lastKnownIndex;
                private final CompactHashMap this$0;

                private final void updateLastKnownIndex()
                {
label0:
                    {
                        if (lastKnownIndex != -1 && lastKnownIndex < size())
                        {
                            Object obj = key;
                            Object obj1 = keys[lastKnownIndex];
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
                                break label0;
                            }
                        }
                        lastKnownIndex = indexOf(key);
                    }
                }

                public final Object getKey()
                {
                    return key;
                }

                public final Object getValue()
                {
                    updateLastKnownIndex();
                    if (lastKnownIndex == -1)
                    {
                        return null;
                    } else
                    {
                        return values[lastKnownIndex];
                    }
                }

                public final Object setValue(Object obj)
                {
                    updateLastKnownIndex();
                    if (lastKnownIndex == -1)
                    {
                        put(key, obj);
                        return null;
                    } else
                    {
                        Object obj1 = values[lastKnownIndex];
                        values[lastKnownIndex] = obj;
                        return obj1;
                    }
                }

                MapEntry(int i)
                {
                    this$0 = CompactHashMap.this;
                    super();
                    key = keys[i];
                    lastKnownIndex = i;
                }
            }

        }

    }


    private class KeySetView extends AbstractSet
    {

        private final CompactHashMap this$0;

        public final void clear()
        {
            CompactHashMap.this.clear();
        }

        public final boolean contains(Object obj)
        {
            return containsKey(obj);
        }

        public final Iterator iterator()
        {
            return new _cls1();
        }

        public final boolean remove(Object obj)
        {
            int i = indexOf(obj);
            if (i == -1)
            {
                return false;
            } else
            {
                obj = CompactHashMap.this;
                ((CompactHashMap) (obj)).remove(((CompactHashMap) (obj)).keys[i], (int)(((CompactHashMap) (obj)).entries[i] >>> 32));
                return true;
            }
        }

        public final int size()
        {
            return CompactHashMap.this.size;
        }

        KeySetView()
        {
            this$0 = CompactHashMap.this;
            super();
        }

        private class _cls1 extends Itr
        {

            private final CompactHashMap this$0;

            final Object getOutput(int i)
            {
                return keys[i];
            }

            _cls1()
            {
                this$0 = CompactHashMap.this;
                super((byte)0);
            }
        }

    }


    private class ValuesView extends AbstractCollection
    {

        private final CompactHashMap this$0;

        public final void clear()
        {
            CompactHashMap.this.clear();
        }

        public final Iterator iterator()
        {
            return new _cls3();
        }

        public final int size()
        {
            return CompactHashMap.this.size;
        }

        ValuesView()
        {
            this$0 = CompactHashMap.this;
            super();
        }

        private class _cls3 extends Itr
        {

            private final CompactHashMap this$0;

            final Object getOutput(int i)
            {
                return values[i];
            }

            _cls3()
            {
                this$0 = CompactHashMap.this;
                super((byte)0);
            }
        }

    }

}
