// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Iterator;

// Referenced classes of package com.google.protobuf:
//            UnmodifiableLazyStringList, LazyStringList

final class st
    implements Iterator
{

    private Iterator iter;
    private final UnmodifiableLazyStringList this$0;

    public final boolean hasNext()
    {
        return iter.hasNext();
    }

    public final Object next()
    {
        return (String)iter.next();
    }

    public final void remove()
    {
        throw new UnsupportedOperationException();
    }

    ()
    {
        this$0 = UnmodifiableLazyStringList.this;
        super();
        iter = list.iterator();
    }
}
