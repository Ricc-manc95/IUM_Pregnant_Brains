// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.AbstractSet;
import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            CompactHashMap

final class this._cls0 extends AbstractSet
{

    private final CompactHashMap this$0;

    public final void clear()
    {
        CompactHashMap.this.clear();
    }

    public final boolean contains(Object obj)
    {
        return containsKey(obj);
    }

    public final Iterator iterator()
    {
        return new (CompactHashMap.this);
    }

    public final boolean remove(Object obj)
    {
        int i = indexOf(obj);
        if (i == -1)
        {
            return false;
        } else
        {
            obj = CompactHashMap.this;
            ((CompactHashMap) (obj)).remove(((CompactHashMap) (obj)).keys[i], (int)(((CompactHashMap) (obj)).entries[i] >>> 32));
            return true;
        }
    }

    public final int size()
    {
        return CompactHashMap.this.size;
    }

    _cls9()
    {
        this$0 = CompactHashMap.this;
        super();
    }
}
