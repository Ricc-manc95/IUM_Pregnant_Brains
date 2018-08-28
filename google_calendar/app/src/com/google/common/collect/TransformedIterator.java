// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;

abstract class TransformedIterator
    implements Iterator
{

    public final Iterator backingIterator;

    TransformedIterator(Iterator iterator)
    {
        if (iterator == null)
        {
            throw new NullPointerException();
        } else
        {
            backingIterator = (Iterator)iterator;
            return;
        }
    }

    public final boolean hasNext()
    {
        return backingIterator.hasNext();
    }

    public final Object next()
    {
        return transform(backingIterator.next());
    }

    public final void remove()
    {
        backingIterator.remove();
    }

    abstract Object transform(Object obj);
}
