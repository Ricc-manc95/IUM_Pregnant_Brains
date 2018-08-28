// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            AbstractMapBasedMultimap, SetMultimap, AbstractMultimap

abstract class AbstractSetMultimap extends AbstractMapBasedMultimap
    implements SetMultimap
{

    public static final long serialVersionUID = 0x67226fd4cd0928d8L;

    protected AbstractSetMultimap(Map map)
    {
        super(map);
    }

    volatile Collection createCollection()
    {
        return createCollection();
    }

    abstract Set createCollection();

    final Collection createUnmodifiableEmptyCollection()
    {
        return Collections.emptySet();
    }

    public volatile Collection entries()
    {
        return entries();
    }

    public Set entries()
    {
        return (Set)super.entries();
    }

    public volatile Collection get(Object obj)
    {
        return get(obj);
    }

    public Set get(Object obj)
    {
        return (Set)super.get(obj);
    }

    public volatile Collection removeAll(Object obj)
    {
        return removeAll(obj);
    }

    public Set removeAll(Object obj)
    {
        return (Set)super.removeAll(obj);
    }

    final Collection unmodifiableCollectionSubclass(Collection collection)
    {
        return Collections.unmodifiableSet((Set)collection);
    }

    final Collection wrapCollection(Object obj, Collection collection)
    {
        return new AbstractMapBasedMultimap.WrappedSet(this, obj, (Set)collection);
    }
}
