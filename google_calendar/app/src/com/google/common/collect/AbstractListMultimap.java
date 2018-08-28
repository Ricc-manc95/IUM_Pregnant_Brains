// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.common.collect:
//            AbstractMapBasedMultimap, ListMultimap

abstract class AbstractListMultimap extends AbstractMapBasedMultimap
    implements ListMultimap
{

    public static final long serialVersionUID = 0x5b6e85fc5d362ea5L;

    protected AbstractListMultimap(Map map)
    {
        super(map);
    }

    volatile Collection createCollection()
    {
        return createCollection();
    }

    abstract List createCollection();

    final Collection createUnmodifiableEmptyCollection()
    {
        return Collections.emptyList();
    }

    public volatile Collection get(Object obj)
    {
        return get(obj);
    }

    public List get(Object obj)
    {
        return (List)super.get(obj);
    }

    public volatile Collection removeAll(Object obj)
    {
        return removeAll(obj);
    }

    public List removeAll(Object obj)
    {
        return (List)super.removeAll(obj);
    }

    final Collection unmodifiableCollectionSubclass(Collection collection)
    {
        return Collections.unmodifiableList((List)collection);
    }

    final Collection wrapCollection(Object obj, Collection collection)
    {
        return wrapList(obj, (List)collection, null);
    }
}
