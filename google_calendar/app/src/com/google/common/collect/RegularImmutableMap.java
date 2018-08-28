// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Arrays;

// Referenced classes of package com.google.common.collect:
//            ImmutableMap, CollectPreconditions, ImmutableSet, ImmutableCollection

public final class RegularImmutableMap extends ImmutableMap
{

    public static final ImmutableMap EMPTY = new RegularImmutableMap(null, new Object[0], 0);
    public static final long serialVersionUID = 0L;
    private final transient Object alternatingKeysAndValues[];
    private final transient int hashTable[];
    private final transient int size;

    private RegularImmutableMap(int ai[], Object aobj[], int i)
    {
        hashTable = ai;
        alternatingKeysAndValues = aobj;
        size = i;
    }

    public static RegularImmutableMap create(int i, Object aobj[])
    {
        if (i == 0)
        {
            return (RegularImmutableMap)EMPTY;
        }
        if (i == 1)
        {
            CollectPreconditions.checkEntryNotNull(aobj[0], aobj[1]);
            return new RegularImmutableMap(null, aobj, 1);
        } else
        {
            Preconditions.checkPositionIndex(i, aobj.length >> 1);
            return new RegularImmutableMap(createHashTable(aobj, i, ImmutableSet.chooseTableSize(i), 0), aobj, i);
        }
    }

    static int[] createHashTable(Object aobj[], int i, int j, int k)
    {
        if (i == 1)
        {
            CollectPreconditions.checkEntryNotNull(aobj[k], aobj[k ^ 1]);
            return null;
        }
        int ai[] = new int[j];
        Arrays.fill(ai, -1);
        int l = 0;
label0:
        do
        {
            if (l < i)
            {
                Object obj1 = aobj[l * 2 + k];
                Object obj = aobj[l * 2 + (k ^ 1)];
                CollectPreconditions.checkEntryNotNull(obj1, obj);
                int i1 = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)obj1.hashCode() * 0xffffffffcc9e2d51L), 15));
                do
                {
                    i1 &= j - 1;
                    int j1 = ai[i1];
                    if (j1 == -1)
                    {
                        ai[i1] = l * 2 + k;
                        l++;
                        continue label0;
                    }
                    if (aobj[j1].equals(obj1))
                    {
                        ai = String.valueOf(obj1);
                        obj = String.valueOf(obj);
                        obj1 = String.valueOf(aobj[j1]);
                        aobj = String.valueOf(aobj[j1 ^ 1]);
                        throw new IllegalArgumentException((new StringBuilder(String.valueOf(ai).length() + 39 + String.valueOf(obj).length() + String.valueOf(obj1).length() + String.valueOf(((Object) (aobj))).length())).append("Multiple entries with same key: ").append(ai).append("=").append(((String) (obj))).append(" and ").append(((String) (obj1))).append("=").append(((String) (aobj))).toString());
                    }
                    i1++;
                } while (true);
            }
            return ai;
        } while (true);
    }

    static Object get(int ai[], Object aobj[], int i, int j, Object obj)
    {
        if (obj == null)
        {
            return null;
        }
        if (i == 1)
        {
            if (aobj[j].equals(obj))
            {
                return aobj[j ^ 1];
            } else
            {
                return null;
            }
        }
        if (ai == null)
        {
            return null;
        }
        j = ai.length;
        i = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)obj.hashCode() * 0xffffffffcc9e2d51L), 15));
        do
        {
            i &= j - 1;
            int k = ai[i];
            if (k == -1)
            {
                return null;
            }
            if (aobj[k].equals(obj))
            {
                return aobj[k ^ 1];
            }
            i++;
        } while (true);
    }

    final ImmutableSet createEntrySet()
    {
        return new EntrySet(this, alternatingKeysAndValues, 0, size);
    }

    final ImmutableSet createKeySet()
    {
        return new KeySet(this, new KeysOrValuesAsList(alternatingKeysAndValues, 0, size));
    }

    final ImmutableCollection createValues()
    {
        return new KeysOrValuesAsList(alternatingKeysAndValues, 1, size);
    }

    public final Object get(Object obj)
    {
        return get(hashTable, alternatingKeysAndValues, size, 0, obj);
    }

    final boolean isPartialView()
    {
        return false;
    }

    public final int size()
    {
        return size;
    }


    private class EntrySet extends ImmutableSet
    {

        public final transient Object alternatingKeysAndValues[];
        public final transient int keyOffset;
        private final transient ImmutableMap map;
        public final transient int size;

        public final boolean contains(Object obj)
        {
            boolean flag1 = false;
            boolean flag = flag1;
            if (obj instanceof java.util.Map.Entry)
            {
                Object obj1 = (java.util.Map.Entry)obj;
                obj = ((java.util.Map.Entry) (obj1)).getKey();
                obj1 = ((java.util.Map.Entry) (obj1)).getValue();
                flag = flag1;
                if (obj1 != null)
                {
                    flag = flag1;
                    if (obj1.equals(map.get(obj)))
                    {
                        flag = true;
                    }
                }
            }
            return flag;
        }

        final int copyIntoArray(Object aobj[], int i)
        {
            return asList().copyIntoArray(aobj, i);
        }

        final ImmutableList createAsList()
        {
            class _cls1 extends ImmutableList
            {

                private final EntrySet this$0;

                public final Object get(int i)
                {
                    Preconditions.checkElementIndex(i, EntrySet.this.size);
                    return new java.util.AbstractMap.SimpleImmutableEntry(alternatingKeysAndValues[i * 2 + keyOffset], alternatingKeysAndValues[i * 2 + (keyOffset ^ 1)]);
                }

                public final boolean isPartialView()
                {
                    return true;
                }

                public final int size()
                {
                    return EntrySet.this.size;
                }

                _cls1()
                {
                    this$0 = EntrySet.this;
                    super();
                }
            }

            return new _cls1();
        }

        final boolean isPartialView()
        {
            return true;
        }

        public final UnmodifiableIterator iterator()
        {
            return (UnmodifiableIterator)asList().iterator();
        }

        public final volatile Iterator iterator()
        {
            return iterator();
        }

        public final int size()
        {
            return size;
        }

        EntrySet(ImmutableMap immutablemap, Object aobj[], int i, int j)
        {
            map = immutablemap;
            alternatingKeysAndValues = aobj;
            keyOffset = i;
            size = j;
        }
    }


    private class KeySet extends ImmutableSet
    {

        private final transient ImmutableList list;
        private final transient ImmutableMap map;

        public final ImmutableList asList()
        {
            return list;
        }

        public final boolean contains(Object obj)
        {
            return map.get(obj) != null;
        }

        final int copyIntoArray(Object aobj[], int i)
        {
            return asList().copyIntoArray(aobj, i);
        }

        final boolean isPartialView()
        {
            return true;
        }

        public final UnmodifiableIterator iterator()
        {
            return (UnmodifiableIterator)asList().iterator();
        }

        public final volatile Iterator iterator()
        {
            return iterator();
        }

        public final int size()
        {
            return map.size();
        }

        KeySet(ImmutableMap immutablemap, ImmutableList immutablelist)
        {
            map = immutablemap;
            list = immutablelist;
        }
    }


    private class KeysOrValuesAsList extends ImmutableList
    {

        private final transient Object alternatingKeysAndValues[];
        private final transient int offset;
        private final transient int size;

        public final Object get(int i)
        {
            Preconditions.checkElementIndex(i, size);
            return alternatingKeysAndValues[i * 2 + offset];
        }

        final boolean isPartialView()
        {
            return true;
        }

        public final int size()
        {
            return size;
        }

        KeysOrValuesAsList(Object aobj[], int i, int j)
        {
            alternatingKeysAndValues = aobj;
            offset = i;
            size = j;
        }
    }

}
