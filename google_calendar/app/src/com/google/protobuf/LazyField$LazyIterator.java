// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Iterator;

// Referenced classes of package com.google.protobuf:
//            LazyField

final class iterator
    implements Iterator
{

    private Iterator iterator;

    public final boolean hasNext()
    {
        return iterator.hasNext();
    }

    public final Object next()
    {
        java.util.yIterator yiterator = (java.util.yIterator)iterator.next();
        Object obj = yiterator;
        if (yiterator.iterator() instanceof LazyField)
        {
            obj = new it>(yiterator);
        }
        return obj;
    }

    public final void remove()
    {
        iterator.remove();
    }

    public (Iterator iterator1)
    {
        iterator = iterator1;
    }
}
