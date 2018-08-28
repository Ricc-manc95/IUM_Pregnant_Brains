// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            Multimap

abstract class AbstractMultimap
    implements Multimap
{

    private transient Map asMap;
    private transient Collection entries;
    private transient Set keySet;

    AbstractMultimap()
    {
    }

    public Map asMap()
    {
        Map map1 = asMap;
        Map map = map1;
        if (map1 == null)
        {
            map = createAsMap();
            asMap = map;
        }
        return map;
    }

    public boolean containsEntry(Object obj, Object obj1)
    {
        obj = (Collection)asMap().get(obj);
        return obj != null && ((Collection) (obj)).contains(obj1);
    }

    abstract Map createAsMap();

    abstract Collection createEntries();

    abstract Set createKeySet();

    public Collection entries()
    {
        Collection collection1 = entries;
        Collection collection = collection1;
        if (collection1 == null)
        {
            collection = createEntries();
            entries = collection;
        }
        return collection;
    }

    abstract Iterator entryIterator();

    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof Multimap)
        {
            obj = (Multimap)obj;
            return asMap().equals(((Multimap) (obj)).asMap());
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return asMap().hashCode();
    }

    public Set keySet()
    {
        Set set1 = keySet;
        Set set = set1;
        if (set1 == null)
        {
            set = createKeySet();
            keySet = set;
        }
        return set;
    }

    public boolean put(Object obj, Object obj1)
    {
        return get(obj).add(obj1);
    }

    public boolean remove(Object obj, Object obj1)
    {
        obj = (Collection)asMap().get(obj);
        return obj != null && ((Collection) (obj)).remove(obj1);
    }

    public String toString()
    {
        return asMap().toString();
    }
}
