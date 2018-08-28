// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Strings;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            BiMap, Hashing, CollectPreconditions

public final class HashBiMap extends AbstractMap
    implements BiMap, Serializable
{

    private transient Set entrySet;
    public transient int firstInInsertionOrder;
    public transient int hashTableKToV[];
    public transient int hashTableVToK[];
    public transient BiMap inverse;
    private transient Set keySet;
    public transient Object keys[];
    private transient int lastInInsertionOrder;
    public transient int modCount;
    public transient int nextInBucketKToV[];
    public transient int nextInBucketVToK[];
    public transient int nextInInsertionOrder[];
    private transient int prevInInsertionOrder[];
    public transient int size;
    private transient Set valueSet;
    public transient Object values[];

    public HashBiMap(int i)
    {
        init(i);
    }

    private final void deleteFromTableKToV(int i, int j)
    {
        boolean flag;
        if (i != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        j = hashTableKToV.length - 1 & j;
        if (hashTableKToV[j] == i)
        {
            hashTableKToV[j] = nextInBucketKToV[i];
            nextInBucketKToV[i] = -1;
            return;
        }
        int k = hashTableKToV[j];
        int l;
        for (j = nextInBucketKToV[k]; j != -1; j = l)
        {
            if (j == i)
            {
                nextInBucketKToV[k] = nextInBucketKToV[i];
                nextInBucketKToV[i] = -1;
                return;
            }
            l = nextInBucketKToV[j];
            k = j;
        }

        String s = String.valueOf(keys[i]);
        throw new AssertionError((new StringBuilder(String.valueOf(s).length() + 32)).append("Expected to find entry with key ").append(s).toString());
    }

    private final void deleteFromTableVToK(int i, int j)
    {
        boolean flag;
        if (i != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        j = hashTableKToV.length - 1 & j;
        if (hashTableVToK[j] == i)
        {
            hashTableVToK[j] = nextInBucketVToK[i];
            nextInBucketVToK[i] = -1;
            return;
        }
        int k = hashTableVToK[j];
        int l;
        for (j = nextInBucketVToK[k]; j != -1; j = l)
        {
            if (j == i)
            {
                nextInBucketVToK[k] = nextInBucketVToK[i];
                nextInBucketVToK[i] = -1;
                return;
            }
            l = nextInBucketVToK[j];
            k = j;
        }

        String s = String.valueOf(values[i]);
        throw new AssertionError((new StringBuilder(String.valueOf(s).length() + 34)).append("Expected to find entry with value ").append(s).toString());
    }

    private final void ensureCapacity(int i)
    {
        if (nextInBucketKToV.length < i)
        {
            int j = ImmutableCollection.Builder.expandedCapacity(nextInBucketKToV.length, i);
            keys = Arrays.copyOf(keys, j);
            values = Arrays.copyOf(values, j);
            int ai[] = nextInBucketKToV;
            int l = ai.length;
            ai = Arrays.copyOf(ai, j);
            Arrays.fill(ai, l, j, -1);
            nextInBucketKToV = ai;
            ai = nextInBucketVToK;
            l = ai.length;
            ai = Arrays.copyOf(ai, j);
            Arrays.fill(ai, l, j, -1);
            nextInBucketVToK = ai;
            ai = prevInInsertionOrder;
            l = ai.length;
            ai = Arrays.copyOf(ai, j);
            Arrays.fill(ai, l, j, -1);
            prevInInsertionOrder = ai;
            ai = nextInInsertionOrder;
            l = ai.length;
            ai = Arrays.copyOf(ai, j);
            Arrays.fill(ai, l, j, -1);
            nextInInsertionOrder = ai;
        }
        if (hashTableKToV.length < i)
        {
            i = Hashing.closedTableSize(i, 1.0D);
            int ai1[] = new int[i];
            Arrays.fill(ai1, -1);
            hashTableKToV = ai1;
            ai1 = new int[i];
            Arrays.fill(ai1, -1);
            hashTableVToK = ai1;
            i = 0;
            while (i < size) 
            {
                Object obj = keys[i];
                int k;
                if (obj == null)
                {
                    k = 0;
                } else
                {
                    k = obj.hashCode();
                }
                k = (int)((long)Integer.rotateLeft((int)((long)k * 0xffffffffcc9e2d51L), 15) * 0x1b873593L) & hashTableKToV.length - 1;
                nextInBucketKToV[i] = hashTableKToV[k];
                hashTableKToV[k] = i;
                obj = values[i];
                if (obj == null)
                {
                    k = 0;
                } else
                {
                    k = obj.hashCode();
                }
                k = (int)((long)Integer.rotateLeft((int)((long)k * 0xffffffffcc9e2d51L), 15) * 0x1b873593L) & hashTableKToV.length - 1;
                nextInBucketVToK[i] = hashTableVToK[k];
                hashTableVToK[k] = i;
                i++;
            }
        }
    }

    private final void init(int i)
    {
        CollectPreconditions.checkNonnegative(i, "expectedSize");
        int j = Hashing.closedTableSize(i, 1.0D);
        size = 0;
        keys = new Object[i];
        values = new Object[i];
        int ai[] = new int[j];
        Arrays.fill(ai, -1);
        hashTableKToV = ai;
        ai = new int[j];
        Arrays.fill(ai, -1);
        hashTableVToK = ai;
        ai = new int[i];
        Arrays.fill(ai, -1);
        nextInBucketKToV = ai;
        ai = new int[i];
        Arrays.fill(ai, -1);
        nextInBucketVToK = ai;
        firstInInsertionOrder = -2;
        lastInInsertionOrder = -2;
        ai = new int[i];
        Arrays.fill(ai, -1);
        prevInInsertionOrder = ai;
        ai = new int[i];
        Arrays.fill(ai, -1);
        nextInInsertionOrder = ai;
    }

    private final void insertIntoTableKToV(int i, int j)
    {
        boolean flag;
        if (i != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            j = hashTableKToV.length - 1 & j;
            nextInBucketKToV[i] = hashTableKToV[j];
            hashTableKToV[j] = i;
            return;
        }
    }

    private final void insertIntoTableVToK(int i, int j)
    {
        boolean flag;
        if (i != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            j = hashTableKToV.length - 1 & j;
            nextInBucketVToK[i] = hashTableVToK[j];
            hashTableVToK[j] = i;
            return;
        }
    }

    private final void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        int j = objectinputstream.readInt();
        init(16);
        for (int i = 0; i < j; i++)
        {
            put(objectinputstream.readObject(), objectinputstream.readObject());
        }

    }

    private final void setSucceeds(int i, int j)
    {
        if (i == -2)
        {
            firstInInsertionOrder = j;
        } else
        {
            nextInInsertionOrder[i] = j;
        }
        if (j == -2)
        {
            lastInInsertionOrder = i;
            return;
        } else
        {
            prevInInsertionOrder[j] = i;
            return;
        }
    }

    private final void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.defaultWriteObject();
        objectoutputstream.writeInt(size());
        java.util.Map.Entry entry;
        for (Iterator iterator = entrySet().iterator(); iterator.hasNext(); objectoutputstream.writeObject(entry.getValue()))
        {
            entry = (java.util.Map.Entry)iterator.next();
            objectoutputstream.writeObject(entry.getKey());
        }

    }

    public final void clear()
    {
        Arrays.fill(keys, 0, size, null);
        Arrays.fill(values, 0, size, null);
        Arrays.fill(hashTableKToV, -1);
        Arrays.fill(hashTableVToK, -1);
        Arrays.fill(nextInBucketKToV, 0, size, -1);
        Arrays.fill(nextInBucketVToK, 0, size, -1);
        Arrays.fill(prevInInsertionOrder, 0, size, -1);
        Arrays.fill(nextInInsertionOrder, 0, size, -1);
        size = 0;
        firstInInsertionOrder = -2;
        lastInInsertionOrder = -2;
        modCount = modCount + 1;
    }

    public final boolean containsKey(Object obj)
    {
        return findEntryByKey(obj) != -1;
    }

    public final boolean containsValue(Object obj)
    {
        return findEntryByValue(obj) != -1;
    }

    public final Set entrySet()
    {
        Set set = entrySet;
        Object obj = set;
        if (set == null)
        {
            obj = new EntrySet();
            entrySet = ((Set) (obj));
        }
        return ((Set) (obj));
    }

    final int findEntry(Object obj, int i, int ai[], int ai1[], Object aobj[])
    {
        boolean flag;
        for (i = ai[hashTableKToV.length - 1 & i]; i != -1; i = ai1[i])
        {
            ai = ((int []) (aobj[i]));
            if (ai == obj || ai != null && ai.equals(obj))
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

    final int findEntryByKey(Object obj)
    {
        int i;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        return findEntry(obj, (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L), hashTableKToV, nextInBucketKToV, keys);
    }

    final int findEntryByValue(Object obj)
    {
        int i;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        return findEntry(obj, (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L), hashTableVToK, nextInBucketVToK, values);
    }

    public final Object get(Object obj)
    {
        int i = findEntryByKey(obj);
        if (i == -1)
        {
            return null;
        } else
        {
            return values[i];
        }
    }

    public final BiMap inverse()
    {
        BiMap bimap = inverse;
        Object obj = bimap;
        if (bimap == null)
        {
            obj = new Inverse();
            inverse = ((BiMap) (obj));
        }
        return ((BiMap) (obj));
    }

    public final Set keySet()
    {
        Set set = keySet;
        Object obj = set;
        if (set == null)
        {
            obj = new KeySet();
            keySet = ((Set) (obj));
        }
        return ((Set) (obj));
    }

    public final Object put(Object obj, Object obj1)
    {
        int i;
        int j;
        int k;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        j = (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
        k = findEntry(obj, j, hashTableKToV, nextInBucketKToV, keys);
        if (k != -1)
        {
            obj = values[k];
            if (obj == obj1 || obj != null && obj.equals(obj1))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                return obj1;
            } else
            {
                replaceValueInEntry(k, obj1, false);
                return obj;
            }
        }
        if (obj1 == null)
        {
            i = 0;
        } else
        {
            i = obj1.hashCode();
        }
        k = (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
        if (findEntry(obj1, k, hashTableVToK, nextInBucketVToK, values) == -1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("Value already present: %s", new Object[] {
                obj1
            }));
        } else
        {
            ensureCapacity(size + 1);
            keys[size] = obj;
            values[size] = obj1;
            insertIntoTableKToV(size, j);
            insertIntoTableVToK(size, k);
            setSucceeds(lastInInsertionOrder, size);
            setSucceeds(size, -2);
            size = size + 1;
            modCount = modCount + 1;
            return null;
        }
    }

    final Object putInverse(Object obj, Object obj1, boolean flag)
    {
        int i;
        int j;
        int k;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        k = (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
        j = findEntry(obj, k, hashTableVToK, nextInBucketVToK, values);
        if (j != -1)
        {
            obj = keys[j];
            if (obj == obj1 || obj != null && obj.equals(obj1))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                return obj1;
            } else
            {
                replaceKeyInEntry(j, obj1, false);
                return obj;
            }
        }
        j = lastInInsertionOrder;
        int l;
        if (obj1 == null)
        {
            i = 0;
        } else
        {
            i = obj1.hashCode();
        }
        l = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15));
        if (findEntry(obj1, l, hashTableKToV, nextInBucketKToV, keys) == -1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("Key already present: %s", new Object[] {
                obj1
            }));
        }
        ensureCapacity(size + 1);
        keys[size] = obj1;
        values[size] = obj;
        insertIntoTableKToV(size, l);
        insertIntoTableVToK(size, k);
        if (j == -2)
        {
            i = firstInInsertionOrder;
        } else
        {
            i = nextInInsertionOrder[j];
        }
        setSucceeds(j, size);
        setSucceeds(size, i);
        size = size + 1;
        modCount = modCount + 1;
        return null;
    }

    public final Object remove(Object obj)
    {
        boolean flag = false;
        int i;
        int j;
        int k;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        j = (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
        k = findEntry(obj, j, hashTableKToV, nextInBucketKToV, keys);
        if (k == -1)
        {
            return null;
        }
        obj = values[k];
        Object obj1 = values[k];
        if (obj1 == null)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = obj1.hashCode();
        }
        removeEntry(k, j, (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L));
        return obj;
    }

    final void removeEntry(int i, int j, int k)
    {
        int j1;
        boolean flag;
        if (i != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        deleteFromTableKToV(i, j);
        deleteFromTableVToK(i, k);
        setSucceeds(prevInInsertionOrder[i], nextInInsertionOrder[i]);
        j1 = size - 1;
        if (j1 == i) goto _L2; else goto _L1
_L1:
        Object obj1;
        j = prevInInsertionOrder[j1];
        k = nextInInsertionOrder[j1];
        setSucceeds(j, i);
        setSucceeds(i, k);
        Object obj = keys[j1];
        obj1 = values[j1];
        keys[i] = obj;
        values[i] = obj1;
        if (obj == null)
        {
            j = 0;
        } else
        {
            j = obj.hashCode();
        }
        j = (int)((long)Integer.rotateLeft((int)((long)j * 0xffffffffcc9e2d51L), 15) * 0x1b873593L) & hashTableKToV.length - 1;
        if (hashTableKToV[j] != j1) goto _L4; else goto _L3
_L3:
        hashTableKToV[j] = i;
_L7:
        nextInBucketKToV[i] = nextInBucketKToV[j1];
        nextInBucketKToV[j1] = -1;
        int l;
        if (obj1 == null)
        {
            j = 0;
        } else
        {
            j = obj1.hashCode();
        }
        j = (int)((long)Integer.rotateLeft((int)((long)j * 0xffffffffcc9e2d51L), 15) * 0x1b873593L) & hashTableKToV.length - 1;
        if (hashTableVToK[j] != j1) goto _L6; else goto _L5
_L5:
        hashTableVToK[j] = i;
_L9:
        nextInBucketVToK[i] = nextInBucketVToK[j1];
        nextInBucketVToK[j1] = -1;
_L2:
        keys[size - 1] = null;
        values[size - 1] = null;
        size = size - 1;
        modCount = modCount + 1;
        return;
_L4:
        k = hashTableKToV[j];
        j = nextInBucketKToV[k];
_L8:
label0:
        {
            if (j != j1)
            {
                break label0;
            }
            nextInBucketKToV[k] = i;
        }
          goto _L7
        l = nextInBucketKToV[j];
        k = j;
        j = l;
          goto _L8
_L6:
        k = hashTableVToK[j];
        j = nextInBucketVToK[k];
_L10:
label1:
        {
            if (j != j1)
            {
                break label1;
            }
            nextInBucketVToK[k] = i;
        }
          goto _L9
        int i1 = nextInBucketVToK[j];
        k = j;
        j = i1;
          goto _L10
    }

    final void removeEntryKeyHashKnown(int i, int j)
    {
        Object obj = values[i];
        int k;
        if (obj == null)
        {
            k = 0;
        } else
        {
            k = obj.hashCode();
        }
        removeEntry(i, j, (int)((long)Integer.rotateLeft((int)((long)k * 0xffffffffcc9e2d51L), 15) * 0x1b873593L));
    }

    final void removeEntryValueHashKnown(int i, int j)
    {
        Object obj = keys[i];
        int k;
        if (obj == null)
        {
            k = 0;
        } else
        {
            k = obj.hashCode();
        }
        removeEntry(i, (int)((long)Integer.rotateLeft((int)((long)k * 0xffffffffcc9e2d51L), 15) * 0x1b873593L), j);
    }

    final void replaceKeyInEntry(int i, Object obj, boolean flag)
    {
        int j;
        int k;
        int l;
        int i1;
        boolean flag1;
        if (i != -1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException();
        }
        int j1;
        if (obj == null)
        {
            j = 0;
        } else
        {
            j = obj.hashCode();
        }
        j1 = (int)((long)Integer.rotateLeft((int)((long)j * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
        l = findEntry(obj, j1, hashTableKToV, nextInBucketKToV, keys);
        k = lastInInsertionOrder;
        j = -2;
        if (l == -1) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        j = prevInInsertionOrder[l];
        k = nextInInsertionOrder[l];
        Object obj1 = values[l];
        if (obj1 == null)
        {
            i1 = 0;
        } else
        {
            i1 = obj1.hashCode();
        }
        removeEntry(l, j1, (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i1 * 0xffffffffcc9e2d51L), 15)));
        if (i == size)
        {
            i = k;
            i1 = l;
            k = j;
            j = i;
        } else
        {
            i1 = j;
            j = k;
            k = i1;
            i1 = i;
        }
_L6:
        if (k == i1)
        {
            i = prevInInsertionOrder[i1];
        } else
        {
            i = k;
            if (k == size)
            {
                i = l;
            }
        }
        if (j == i1)
        {
            k = nextInInsertionOrder[i1];
        } else
        {
            k = j;
            if (j == size)
            {
                k = l;
            }
        }
        setSucceeds(prevInInsertionOrder[i1], nextInInsertionOrder[i1]);
        obj1 = keys[i1];
        if (obj1 == null)
        {
            j = 0;
        } else
        {
            j = obj1.hashCode();
        }
        deleteFromTableKToV(i1, (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)j * 0xffffffffcc9e2d51L), 15)));
        keys[i1] = obj;
        if (obj == null)
        {
            j = 0;
        } else
        {
            j = obj.hashCode();
        }
        insertIntoTableKToV(i1, (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)j * 0xffffffffcc9e2d51L), 15)));
        setSucceeds(i, i1);
        setSucceeds(i1, k);
        return;
_L4:
        obj = String.valueOf(obj);
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(obj).length() + 28)).append("Key already present in map: ").append(((String) (obj))).toString());
_L2:
        i1 = i;
        if (true) goto _L6; else goto _L5
_L5:
    }

    final void replaceValueInEntry(int i, Object obj, boolean flag)
    {
label0:
        {
            boolean flag1;
            if (i != -1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalArgumentException();
            }
            Object obj1;
            int j;
            int k;
            int l;
            if (obj == null)
            {
                j = 0;
            } else
            {
                j = obj.hashCode();
            }
            l = (int)((long)Integer.rotateLeft((int)((long)j * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
            k = findEntry(obj, l, hashTableVToK, nextInBucketVToK, values);
            j = i;
            if (k != -1)
            {
                if (!flag)
                {
                    break label0;
                }
                obj1 = keys[k];
                if (obj1 == null)
                {
                    j = 0;
                } else
                {
                    j = obj1.hashCode();
                }
                removeEntry(k, (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)j * 0xffffffffcc9e2d51L), 15)), l);
                j = i;
                if (i == size)
                {
                    j = k;
                }
            }
            obj1 = values[j];
            if (obj1 == null)
            {
                i = 0;
            } else
            {
                i = obj1.hashCode();
            }
            deleteFromTableVToK(j, (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L));
            values[j] = obj;
            insertIntoTableVToK(j, l);
            return;
        }
        obj = String.valueOf(obj);
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(obj).length() + 30)).append("Value already present in map: ").append(((String) (obj))).toString());
    }

    public final int size()
    {
        return size;
    }

    public final Collection values()
    {
        Set set = valueSet;
        Object obj = set;
        if (set == null)
        {
            obj = new ValueSet();
            valueSet = ((Set) (obj));
        }
        return ((Collection) (obj));
    }

    private class EntrySet extends View
    {
        private class View extends AbstractSet
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
                    private final View this$0;

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
                        this$0 = View.this;
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

            View()
            {
                biMap = HashBiMap.this;
            }
        }


        private final HashBiMap this$0;

        public final boolean contains(Object obj)
        {
            if (obj instanceof java.util.Map.Entry)
            {
                Object obj1 = (java.util.Map.Entry)obj;
                obj = ((java.util.Map.Entry) (obj1)).getKey();
                obj1 = ((java.util.Map.Entry) (obj1)).getValue();
                int i = findEntryByKey(obj);
                if (i != -1)
                {
                    obj = values[i];
                    boolean flag;
                    if (obj1 == obj || obj1 != null && obj1.equals(obj))
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

        final Object forEntry(int i)
        {
            return new EntryForKey(i);
        }

        public final boolean remove(Object obj)
        {
            boolean flag1 = false;
            boolean flag = flag1;
            if (obj instanceof java.util.Map.Entry)
            {
                Object obj1 = (java.util.Map.Entry)obj;
                obj = ((java.util.Map.Entry) (obj1)).getKey();
                obj1 = ((java.util.Map.Entry) (obj1)).getValue();
                HashBiMap hashbimap;
                int i;
                int j;
                int k;
                if (obj == null)
                {
                    i = 0;
                } else
                {
                    i = obj.hashCode();
                }
                j = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15));
                hashbimap = HashBiMap.this;
                k = hashbimap.findEntry(obj, j, hashbimap.hashTableKToV, hashbimap.nextInBucketKToV, hashbimap.keys);
                flag = flag1;
                if (k != -1)
                {
                    obj = values[k];
                    if (obj1 == obj || obj1 != null && obj1.equals(obj))
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    flag = flag1;
                    if (i != 0)
                    {
                        removeEntryKeyHashKnown(k, j);
                        flag = true;
                    }
                }
            }
            return flag;
        }

        EntrySet()
        {
            this$0 = HashBiMap.this;
            super();
        }

        private class EntryForKey extends AbstractMapEntry
        {

            private int index;
            private final Object key;
            private final HashBiMap this$0;

            private final void updateIndex()
            {
label0:
                {
                    if (index != -1 && index <= size)
                    {
                        Object obj = keys[index];
                        Object obj1 = key;
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
                    index = findEntryByKey(key);
                }
            }

            public final Object getKey()
            {
                return key;
            }

            public final Object getValue()
            {
                updateIndex();
                if (index == -1)
                {
                    return null;
                } else
                {
                    return values[index];
                }
            }

            public final Object setValue(Object obj)
            {
                updateIndex();
                Object obj1;
                if (index == -1)
                {
                    obj1 = put(key, obj);
                } else
                {
                    Object obj2 = values[index];
                    boolean flag;
                    if (obj2 == obj || obj2 != null && obj2.equals(obj))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    obj1 = obj;
                    if (!flag)
                    {
                        replaceValueInEntry(index, obj, false);
                        return obj2;
                    }
                }
                return obj1;
            }

            EntryForKey(int i)
            {
                this$0 = HashBiMap.this;
                super();
                key = keys[i];
                index = i;
            }
        }

    }


    private class Inverse extends AbstractMap
        implements BiMap, Serializable
    {

        private final HashBiMap forward;
        private transient Set inverseEntrySet;

        private final void readObject(ObjectInputStream objectinputstream)
            throws ClassNotFoundException, IOException
        {
            objectinputstream.defaultReadObject();
            forward.inverse = this;
        }

        public final void clear()
        {
            forward.clear();
        }

        public final boolean containsKey(Object obj)
        {
            return forward.containsValue(obj);
        }

        public final boolean containsValue(Object obj)
        {
            return forward.containsKey(obj);
        }

        public final Set entrySet()
        {
            Set set = inverseEntrySet;
            Object obj = set;
            if (set == null)
            {
                obj = forward. new InverseEntrySet();
                inverseEntrySet = ((Set) (obj));
            }
            return ((Set) (obj));
        }

        public final Object get(Object obj)
        {
            HashBiMap hashbimap = forward;
            int i = hashbimap.findEntryByValue(obj);
            if (i == -1)
            {
                return null;
            } else
            {
                return hashbimap.keys[i];
            }
        }

        public final BiMap inverse()
        {
            return forward;
        }

        public final Set keySet()
        {
            return (Set)forward.values();
        }

        public final Object put(Object obj, Object obj1)
        {
            return forward.putInverse(obj, obj1, false);
        }

        public final Object remove(Object obj)
        {
            boolean flag = false;
            HashBiMap hashbimap = forward;
            int i;
            int j;
            int k;
            if (obj == null)
            {
                i = 0;
            } else
            {
                i = obj.hashCode();
            }
            j = (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
            k = hashbimap.findEntry(obj, j, hashbimap.hashTableVToK, hashbimap.nextInBucketVToK, hashbimap.values);
            if (k == -1)
            {
                return null;
            }
            obj = hashbimap.keys[k];
            Object obj1 = hashbimap.keys[k];
            if (obj1 == null)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = obj1.hashCode();
            }
            hashbimap.removeEntry(k, (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L), j);
            return obj;
        }

        public final int size()
        {
            return forward.size;
        }

        public final Collection values()
        {
            return forward.keySet();
        }

        Inverse()
        {
            forward = HashBiMap.this;
        }

        private class InverseEntrySet extends View
        {

            public final boolean contains(Object obj)
            {
                if (obj instanceof java.util.Map.Entry)
                {
                    Object obj1 = (java.util.Map.Entry)obj;
                    obj = ((java.util.Map.Entry) (obj1)).getKey();
                    obj1 = ((java.util.Map.Entry) (obj1)).getValue();
                    int i = biMap.findEntryByValue(obj);
                    if (i != -1)
                    {
                        obj = biMap.keys[i];
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

            final Object forEntry(int i)
            {
                return biMap. new EntryForValue(i);
            }

            public final boolean remove(Object obj)
            {
                boolean flag1 = false;
                boolean flag = flag1;
                if (obj instanceof java.util.Map.Entry)
                {
                    Object obj1 = (java.util.Map.Entry)obj;
                    obj = ((java.util.Map.Entry) (obj1)).getKey();
                    obj1 = ((java.util.Map.Entry) (obj1)).getValue();
                    HashBiMap hashbimap;
                    int i;
                    int j;
                    int k;
                    if (obj == null)
                    {
                        i = 0;
                    } else
                    {
                        i = obj.hashCode();
                    }
                    j = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15));
                    hashbimap = biMap;
                    k = hashbimap.findEntry(obj, j, hashbimap.hashTableVToK, hashbimap.nextInBucketVToK, hashbimap.values);
                    flag = flag1;
                    if (k != -1)
                    {
                        obj = biMap.keys[k];
                        if (obj == obj1 || obj != null && obj.equals(obj1))
                        {
                            i = 1;
                        } else
                        {
                            i = 0;
                        }
                        flag = flag1;
                        if (i != 0)
                        {
                            biMap.removeEntryValueHashKnown(k, j);
                            flag = true;
                        }
                    }
                }
                return flag;
            }

            InverseEntrySet()
            {
            }

            private class EntryForValue extends AbstractMapEntry
            {

                private final HashBiMap biMap;
                private int index;
                private final Object value;

                private final void updateIndex()
                {
label0:
                    {
                        if (index != -1 && index <= biMap.size)
                        {
                            Object obj = value;
                            Object obj1 = biMap.values[index];
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
                        index = biMap.findEntryByValue(value);
                    }
                }

                public final Object getKey()
                {
                    return value;
                }

                public final Object getValue()
                {
                    updateIndex();
                    if (index == -1)
                    {
                        return null;
                    } else
                    {
                        return biMap.keys[index];
                    }
                }

                public final Object setValue(Object obj)
                {
                    updateIndex();
                    Object obj1;
                    if (index == -1)
                    {
                        obj1 = biMap.putInverse(value, obj, false);
                    } else
                    {
                        Object obj2 = biMap.keys[index];
                        boolean flag;
                        if (obj2 == obj || obj2 != null && obj2.equals(obj))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        obj1 = obj;
                        if (!flag)
                        {
                            biMap.replaceKeyInEntry(index, obj, false);
                            return obj2;
                        }
                    }
                    return obj1;
                }

                EntryForValue(int i)
                {
                    biMap = HashBiMap.this;
                    value = values[i];
                    index = i;
                }
            }

        }

    }


    private class KeySet extends View
    {

        private final HashBiMap this$0;

        public final boolean contains(Object obj)
        {
            return containsKey(obj);
        }

        final Object forEntry(int i)
        {
            return keys[i];
        }

        public final boolean remove(Object obj)
        {
            boolean flag = false;
            HashBiMap hashbimap;
            int i;
            int j;
            if (obj == null)
            {
                i = 0;
            } else
            {
                i = obj.hashCode();
            }
            i = (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
            hashbimap = HashBiMap.this;
            j = hashbimap.findEntry(obj, i, hashbimap.hashTableKToV, hashbimap.nextInBucketKToV, hashbimap.keys);
            if (j != -1)
            {
                removeEntryKeyHashKnown(j, i);
                flag = true;
            }
            return flag;
        }

        KeySet()
        {
            this$0 = HashBiMap.this;
            super();
        }
    }


    private class ValueSet extends View
    {

        private final HashBiMap this$0;

        public final boolean contains(Object obj)
        {
            return containsValue(obj);
        }

        final Object forEntry(int i)
        {
            return values[i];
        }

        public final boolean remove(Object obj)
        {
            boolean flag = false;
            HashBiMap hashbimap;
            int i;
            int j;
            if (obj == null)
            {
                i = 0;
            } else
            {
                i = obj.hashCode();
            }
            i = (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
            hashbimap = HashBiMap.this;
            j = hashbimap.findEntry(obj, i, hashbimap.hashTableVToK, hashbimap.nextInBucketVToK, hashbimap.values);
            if (j != -1)
            {
                removeEntryValueHashKnown(j, i);
                flag = true;
            }
            return flag;
        }

        ValueSet()
        {
            this$0 = HashBiMap.this;
            super();
        }
    }

}
