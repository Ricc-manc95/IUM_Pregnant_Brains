// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            AbstractMapBasedMultimap

final class this._cls0 extends this._cls0
{

    public final AbstractMapBasedMultimap this$0;

    public final void clear()
    {
        Iterator iterator1 = _mth0();
        if (iterator1 == null)
        {
            throw new NullPointerException();
        }
        for (; iterator1.hasNext(); iterator1.remove())
        {
            iterator1.next();
        }

    }

    public final boolean containsAll(Collection collection)
    {
        return super._fld0.keySet().containsAll(collection);
    }

    public final boolean equals(Object obj)
    {
        return this == obj || super._fld0.keySet().equals(obj);
    }

    public final int hashCode()
    {
        return super._fld0.keySet().hashCode();
    }

    public final Iterator iterator()
    {
        class _cls1
            implements Iterator
        {

            private java.util.Map.Entry entry;
            private final AbstractMapBasedMultimap.KeySet this$1;
            private final Iterator val$entryIterator;

            public final boolean hasNext()
            {
                return entryIterator.hasNext();
            }

            public final Object next()
            {
                entry = (java.util.Map.Entry)entryIterator.next();
                return entry.getKey();
            }

            public final void remove()
            {
                boolean flag;
                if (entry != null)
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
                    Collection collection = (Collection)entry.getValue();
                    entryIterator.remove();
                    AbstractMapBasedMultimap abstractmapbasedmultimap = this$0;
                    int i = collection.size();
                    abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - i;
                    collection.clear();
                    entry = null;
                    return;
                }
            }

            _cls1()
            {
                this$1 = AbstractMapBasedMultimap.KeySet.this;
                entryIterator = iterator1;
                super();
            }
        }

        return new _cls1();
    }

    public final boolean remove(Object obj)
    {
        obj = (Collection)super._fld1.remove(obj);
        int i;
        if (obj != null)
        {
            i = ((Collection) (obj)).size();
            ((Collection) (obj)).clear();
            obj = AbstractMapBasedMultimap.this;
            obj.totalSize = ((AbstractMapBasedMultimap) (obj)).totalSize - i;
        } else
        {
            i = 0;
        }
        return i > 0;
    }

    _cls1(Map map)
    {
        this$0 = AbstractMapBasedMultimap.this;
        super(map);
    }
}
