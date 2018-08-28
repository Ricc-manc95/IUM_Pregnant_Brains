// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.AbstractSet;
import java.util.Iterator;

// Referenced classes of package com.google.protobuf:
//            SmallSortedMap

class this._cls0 extends AbstractSet
{

    private final SmallSortedMap this$0;

    public boolean add(Object obj)
    {
        obj = (java.util..EntrySet)obj;
        if (!contains(obj))
        {
            put((Comparable)((java.util..put) (obj)).put(), ((java.util..put) (obj)).put());
            return true;
        } else
        {
            return false;
        }
    }

    public void clear()
    {
        SmallSortedMap.this.clear();
    }

    public boolean contains(Object obj)
    {
        Object obj1 = (java.util..EntrySet)obj;
        obj = get(((java.util..get) (obj1)).get());
        obj1 = ((java.util..get) (obj1)).get();
        return obj == obj1 || obj != null && obj.equals(obj1);
    }

    public Iterator iterator()
    {
        return new ator(SmallSortedMap.this);
    }

    public boolean remove(Object obj)
    {
        obj = (java.util..EntrySet)obj;
        if (contains(obj))
        {
            SmallSortedMap.this.remove(((java.util.) (obj)).());
            return true;
        } else
        {
            return false;
        }
    }

    public int size()
    {
        return SmallSortedMap.this.size();
    }

    ator()
    {
        this$0 = SmallSortedMap.this;
        super();
    }
}
