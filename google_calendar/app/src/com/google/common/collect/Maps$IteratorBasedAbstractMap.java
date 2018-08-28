// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            Iterators

abstract class _cls1 extends AbstractMap
{

    public void clear()
    {
        Iterators.clear(entryIterator());
    }

    abstract Iterator entryIterator();

    public Set entrySet()
    {
        class _cls1 extends Maps.EntrySet
        {

            private final Maps.IteratorBasedAbstractMap this$0;

            public final Iterator iterator()
            {
                return entryIterator();
            }

            final Map map()
            {
                return Maps.IteratorBasedAbstractMap.this;
            }

            _cls1()
            {
                this$0 = Maps.IteratorBasedAbstractMap.this;
                super();
            }
        }

        return new _cls1();
    }

    _cls1()
    {
    }
}
