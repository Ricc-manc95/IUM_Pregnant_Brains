// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            AbstractMultimap, ImmutableCollection, ImmutableMap, ImmutableSet, 
//            CompactHashMap, EmptyImmutableListMultimap, ImmutableList, ImmutableListMultimap, 
//            CollectPreconditions, UnmodifiableIterator, Serialization

public abstract class ImmutableMultimap extends AbstractMultimap
    implements Serializable
{
    public static class Builder
    {

        private Map builderMap;

        public ImmutableMultimap build()
        {
            Set set = builderMap.entrySet();
            if (set.isEmpty())
            {
                return EmptyImmutableListMultimap.INSTANCE;
            }
            ImmutableMap.Builder builder = new ImmutableMap.Builder(set.size());
            Iterator iterator = set.iterator();
            int i = 0;
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                Object obj = (java.util.Map.Entry)iterator.next();
                Object obj1 = ((java.util.Map.Entry) (obj)).getKey();
                obj = (Collection)((java.util.Map.Entry) (obj)).getValue();
                if (true)
                {
                    obj = ImmutableList.copyOf(((Collection) (obj)));
                } else
                {
                    obj = ImmutableList.sortedCopyOf(null, ((Iterable) (obj)));
                }
                if (!((ImmutableList) (obj)).isEmpty())
                {
                    builder.put(obj1, obj);
                    i = ((ImmutableList) (obj)).size() + i;
                }
            } while (true);
            return new ImmutableListMultimap(builder.build(), i);
        }

        public Builder put(Object obj, Object obj1)
        {
            CollectPreconditions.checkEntryNotNull(obj, obj1);
            Collection collection = (Collection)builderMap.get(obj);
            Object obj2 = collection;
            if (collection == null)
            {
                Map map1 = builderMap;
                obj2 = new ArrayList();
                map1.put(obj, obj2);
            }
            ((Collection) (obj2)).add(obj1);
            return this;
        }

        public Builder()
        {
            builderMap = new CompactHashMap();
        }
    }

    static final class EntryCollection extends ImmutableCollection
    {

        public static final long serialVersionUID = 0L;
        private final ImmutableMultimap multimap;

        public final boolean contains(Object obj)
        {
            if (obj instanceof java.util.Map.Entry)
            {
                obj = (java.util.Map.Entry)obj;
                return multimap.containsEntry(((java.util.Map.Entry) (obj)).getKey(), ((java.util.Map.Entry) (obj)).getValue());
            } else
            {
                return false;
            }
        }

        final boolean isPartialView()
        {
            return multimap.map.isPartialView();
        }

        public final UnmodifiableIterator iterator()
        {
            return multimap. new _cls1();
        }

        public final volatile Iterator iterator()
        {
            return iterator();
        }

        public final int size()
        {
            return multimap.size();
        }

        EntryCollection(ImmutableMultimap immutablemultimap)
        {
            multimap = immutablemultimap;
        }

        private class _cls1 extends UnmodifiableIterator
        {

            private final Iterator asMapItr;
            private Object currentKey;
            private final ImmutableMultimap this$0;
            private Iterator valueItr;

            public final boolean hasNext()
            {
                return valueItr.hasNext() || asMapItr.hasNext();
            }

            public final Object next()
            {
                if (!valueItr.hasNext())
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)asMapItr.next();
                    currentKey = entry.getKey();
                    valueItr = (UnmodifiableIterator)((ImmutableCollection)entry.getValue()).iterator();
                }
                return new ImmutableEntry(currentKey, valueItr.next());
            }

            _cls1()
            {
                this$0 = ImmutableMultimap.this;
                super();
                asMapItr = (UnmodifiableIterator)((ImmutableSet)map.entrySet()).iterator();
                currentKey = null;
                valueItr = Iterators.ArrayItr.EMPTY;
            }
        }

    }

    static final class FieldSettersHolder
    {

        public static final Serialization.FieldSetter MAP_FIELD_SETTER = Serialization.getFieldSetter(com/google/common/collect/ImmutableMultimap, "map");
        public static final Serialization.FieldSetter SIZE_FIELD_SETTER = Serialization.getFieldSetter(com/google/common/collect/ImmutableMultimap, "size");

    }


    public static final long serialVersionUID = 0L;
    public final transient ImmutableMap map;
    private final transient int size;

    ImmutableMultimap(ImmutableMap immutablemap, int i)
    {
        map = immutablemap;
        size = i;
    }

    public final Map asMap()
    {
        return map;
    }

    public final void clear()
    {
        throw new UnsupportedOperationException();
    }

    public final volatile boolean containsEntry(Object obj, Object obj1)
    {
        return super.containsEntry(obj, obj1);
    }

    final Map createAsMap()
    {
        throw new AssertionError("should never be called");
    }

    final Collection createEntries()
    {
        return new EntryCollection(this);
    }

    final Set createKeySet()
    {
        throw new AssertionError("unreachable");
    }

    public final Collection entries()
    {
        return (ImmutableCollection)super.entries();
    }

    final Iterator entryIterator()
    {
        return new _cls1();
    }

    public volatile boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    public abstract ImmutableCollection get(Object obj);

    public volatile Collection get(Object obj)
    {
        return get(obj);
    }

    public volatile int hashCode()
    {
        return super.hashCode();
    }

    public final Set keySet()
    {
        return (ImmutableSet)map.keySet();
    }

    public final boolean put(Object obj, Object obj1)
    {
        throw new UnsupportedOperationException();
    }

    public final boolean remove(Object obj, Object obj1)
    {
        throw new UnsupportedOperationException();
    }

    public final int size()
    {
        return size;
    }

    public volatile String toString()
    {
        return super.toString();
    }
}
