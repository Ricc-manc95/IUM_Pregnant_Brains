// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            AbstractMultimap, Multimap

class this._cls0 extends this._cls0
{

    private final AbstractMultimap this$0;

    public Iterator iterator()
    {
        return entryIterator();
    }

    final Multimap multimap()
    {
        return AbstractMultimap.this;
    }

    ()
    {
        this$0 = AbstractMultimap.this;
        super();
    }
}
