// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            CompactHashMap

final class this._cls0 extends AbstractCollection
{

    private final CompactHashMap this$0;

    public final void clear()
    {
        CompactHashMap.this.clear();
    }

    public final Iterator iterator()
    {
        return new this._cls0(CompactHashMap.this);
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
