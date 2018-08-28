// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

// Referenced classes of package com.google.common.collect:
//            CollectPreconditions, RegularImmutableMap, ImmutableCollection, ImmutableSet, 
//            Sets

public abstract class ImmutableMap
    implements Serializable, Map
{

    private transient ImmutableSet entrySet;
    private transient ImmutableSet keySet;
    private transient ImmutableCollection values;

    ImmutableMap()
    {
    }

    public static ImmutableMap copyOf(Map map)
    {
        if ((map instanceof ImmutableMap) && !(map instanceof SortedMap))
        {
            map = (ImmutableMap)map;
            map.isPartialView();
            return map;
        }
        map = map.entrySet();
        Builder builder;
        int i;
        if (map instanceof Collection)
        {
            i = ((Collection)map).size();
        } else
        {
            i = 4;
        }
        builder = new Builder(i);
        builder.putAll(map);
        return builder.build();
    }

    public static ImmutableMap of(Object obj, Object obj1, Object obj2, Object obj3)
    {
        CollectPreconditions.checkEntryNotNull(obj, obj1);
        CollectPreconditions.checkEntryNotNull(obj2, obj3);
        return RegularImmutableMap.create(2, new Object[] {
            obj, obj1, obj2, obj3
        });
    }

    public static ImmutableMap of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
    {
        CollectPreconditions.checkEntryNotNull(obj, obj1);
        CollectPreconditions.checkEntryNotNull(obj2, obj3);
        CollectPreconditions.checkEntryNotNull(obj4, obj5);
        return RegularImmutableMap.create(3, new Object[] {
            obj, obj1, obj2, obj3, obj4, obj5
        });
    }

    public final void clear()
    {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(Object obj)
    {
        return get(obj) != null;
    }

    public boolean containsValue(Object obj)
    {
        return ((ImmutableCollection)values()).contains(obj);
    }

    abstract ImmutableSet createEntrySet();

    abstract ImmutableSet createKeySet();

    abstract ImmutableCollection createValues();

    public Set entrySet()
    {
        ImmutableSet immutableset1 = entrySet;
        ImmutableSet immutableset = immutableset1;
        if (immutableset1 == null)
        {
            immutableset = createEntrySet();
            entrySet = immutableset;
        }
        return immutableset;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj instanceof Map)
        {
            obj = (Map)obj;
            return entrySet().equals(((Map) (obj)).entrySet());
        } else
        {
            return false;
        }
    }

    public abstract Object get(Object obj);

    public final Object getOrDefault(Object obj, Object obj1)
    {
        obj = get(obj);
        if (obj != null)
        {
            obj1 = obj;
        }
        return obj1;
    }

    public int hashCode()
    {
        return Sets.hashCodeImpl((ImmutableSet)entrySet());
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    abstract boolean isPartialView();

    public Set keySet()
    {
        ImmutableSet immutableset1 = keySet;
        ImmutableSet immutableset = immutableset1;
        if (immutableset1 == null)
        {
            immutableset = createKeySet();
            keySet = immutableset;
        }
        return immutableset;
    }

    public final Object put(Object obj, Object obj1)
    {
        throw new UnsupportedOperationException();
    }

    public final void putAll(Map map)
    {
        throw new UnsupportedOperationException();
    }

    public final Object remove(Object obj)
    {
        throw new UnsupportedOperationException();
    }

    public String toString()
    {
        int i = size();
        CollectPreconditions.checkNonnegative(i, "size");
        StringBuilder stringbuilder = (new StringBuilder((int)Math.min((long)i << 3, 0x40000000L))).append('{');
        Iterator iterator = entrySet().iterator();
        boolean flag = true;
        java.util.Map.Entry entry;
        for (; iterator.hasNext(); stringbuilder.append(entry.getKey()).append('=').append(entry.getValue()))
        {
            entry = (java.util.Map.Entry)iterator.next();
            if (!flag)
            {
                stringbuilder.append(", ");
            }
            flag = false;
        }

        return stringbuilder.append('}').toString();
    }

    public ImmutableCollection values()
    {
        ImmutableCollection immutablecollection1 = values;
        ImmutableCollection immutablecollection = immutablecollection1;
        if (immutablecollection1 == null)
        {
            immutablecollection = createValues();
            values = immutablecollection;
        }
        return immutablecollection;
    }

    public volatile Collection values()
    {
        return values();
    }

    Object writeReplace()
    {
        return new SerializedForm();
    }

    private class Builder
    {

        public Object alternatingKeysAndValues[];
        public boolean entriesUsed;
        public int size;

        private final void ensureCapacity(int i)
        {
            if (i << 1 > alternatingKeysAndValues.length)
            {
                alternatingKeysAndValues = Arrays.copyOf(alternatingKeysAndValues, ImmutableCollection.Builder.expandedCapacity(alternatingKeysAndValues.length, i << 1));
                entriesUsed = false;
            }
        }

        public ImmutableMap build()
        {
            entriesUsed = true;
            return RegularImmutableMap.create(size, alternatingKeysAndValues);
        }

        public Builder put(Object obj, Object obj1)
        {
            ensureCapacity(size + 1);
            CollectPreconditions.checkEntryNotNull(obj, obj1);
            alternatingKeysAndValues[size * 2] = obj;
            alternatingKeysAndValues[size * 2 + 1] = obj1;
            size = size + 1;
            return this;
        }

        public Builder put(java.util.Map.Entry entry)
        {
            return put(entry.getKey(), entry.getValue());
        }

        public Builder putAll(Iterable iterable)
        {
            if (iterable instanceof Collection)
            {
                int i = size;
                ensureCapacity(((Collection)iterable).size() + i);
            }
            for (iterable = iterable.iterator(); iterable.hasNext(); put((java.util.Map.Entry)iterable.next())) { }
            return this;
        }

        public Builder()
        {
            this(4);
        }

        public Builder(int i)
        {
            alternatingKeysAndValues = new Object[i * 2];
            size = 0;
            entriesUsed = false;
        }
    }


    private class SerializedForm
        implements Serializable
    {

        public static final long serialVersionUID = 0L;
        private final Object keys[];
        private final Object values[];

        final Object createMap(Builder builder)
        {
            for (int i = 0; i < keys.length; i++)
            {
                builder.put(keys[i], values[i]);
            }

            return builder.build();
        }

        Object readResolve()
        {
            return createMap(new Builder(keys.length));
        }

        SerializedForm()
        {
            keys = new Object[size()];
            values = new Object[size()];
            immutablemap = (UnmodifiableIterator)((ImmutableSet)entrySet()).iterator();
            for (int i = 0; hasNext(); i++)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)next();
                keys[i] = entry.getKey();
                values[i] = entry.getValue();
            }

        }
    }

}
