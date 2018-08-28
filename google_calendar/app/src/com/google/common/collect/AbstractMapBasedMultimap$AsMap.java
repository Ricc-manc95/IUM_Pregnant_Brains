// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            AbstractMapBasedMultimap, Maps, AbstractMultimap

final class submap extends submap
{

    public final transient Map submap;
    public final AbstractMapBasedMultimap this$0;

    public final void clear()
    {
        if (submap == map)
        {
            AbstractMapBasedMultimap.this.clear();
        } else
        {
            class AsMapIterator
                implements Iterator
            {

                private Collection collection;
                private final Iterator delegateIterator;
                private final AbstractMapBasedMultimap.AsMap this$1;

                public final boolean hasNext()
                {
                    return delegateIterator.hasNext();
                }

                public final Object next()
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)delegateIterator.next();
                    collection = (Collection)entry.getValue();
                    AbstractMapBasedMultimap.AsMap asmap = AbstractMapBasedMultimap.AsMap.this;
                    Object obj = entry.getKey();
                    return new ImmutableEntry(obj, asmap.this$0.wrapCollection(obj, (Collection)entry.getValue()));
                }

                public final void remove()
                {
                    boolean flag;
                    if (collection != null)
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
                        delegateIterator.remove();
                        AbstractMapBasedMultimap abstractmapbasedmultimap = this$0;
                        int i = collection.size();
                        abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - i;
                        collection.clear();
                        collection = null;
                        return;
                    }
                }

            AsMapIterator()
            {
                this$1 = AbstractMapBasedMultimap.AsMap.this;
                super();
                delegateIterator = submap.entrySet().iterator();
            }
            }

            AsMapIterator asmapiterator = new AsMapIterator();
            if (asmapiterator == null)
            {
                throw new NullPointerException();
            }
            while (asmapiterator.hasNext()) 
            {
                asmapiterator.next();
                asmapiterator.remove();
            }
        }
    }

    public final boolean containsKey(Object obj)
    {
        return Maps.safeContainsKey(submap, obj);
    }

    protected final Set createEntrySet()
    {
        class AsMapEntries extends Maps.EntrySet
        {

            private final AbstractMapBasedMultimap.AsMap this$1;

            public final boolean contains(Object obj)
            {
                return Collections2.safeContains(submap.entrySet(), obj);
            }

            public final Iterator iterator()
            {
                return new AsMapIterator();
            }

            final Map map()
            {
                return AbstractMapBasedMultimap.AsMap.this;
            }

            public final boolean remove(Object obj)
            {
                if (!contains(obj))
                {
                    return false;
                }
                Object obj1 = (java.util.Map.Entry)obj;
                obj = this$0;
                obj1 = ((java.util.Map.Entry) (obj1)).getKey();
                obj1 = (Collection)Maps.safeRemove(((AbstractMapBasedMultimap) (obj)).map, obj1);
                if (obj1 != null)
                {
                    int i = ((Collection) (obj1)).size();
                    ((Collection) (obj1)).clear();
                    obj.totalSize = ((AbstractMapBasedMultimap) (obj)).totalSize - i;
                }
                return true;
            }

            AsMapEntries()
            {
                this$1 = AbstractMapBasedMultimap.AsMap.this;
                super();
            }
        }

        return new AsMapEntries();
    }

    public final boolean equals(Object obj)
    {
        return this == obj || submap.equals(obj);
    }

    public final Object get(Object obj)
    {
        Collection collection = (Collection)Maps.safeGet(submap, obj);
        if (collection == null)
        {
            return null;
        } else
        {
            return wrapCollection(obj, collection);
        }
    }

    public final int hashCode()
    {
        return submap.hashCode();
    }

    public final Set keySet()
    {
        return AbstractMultimap.this.keySet();
    }

    public final Object remove(Object obj)
    {
        obj = (Collection)submap.remove(obj);
        if (obj == null)
        {
            return null;
        } else
        {
            Collection collection = createCollection();
            collection.addAll(((Collection) (obj)));
            AbstractMapBasedMultimap abstractmapbasedmultimap = AbstractMapBasedMultimap.this;
            int i = ((Collection) (obj)).size();
            abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - i;
            ((Collection) (obj)).clear();
            return collection;
        }
    }

    public final int size()
    {
        return submap.size();
    }

    public final String toString()
    {
        return submap.toString();
    }

    AsMapEntries(Map map)
    {
        this$0 = AbstractMapBasedMultimap.this;
        super();
        submap = map;
    }
}
