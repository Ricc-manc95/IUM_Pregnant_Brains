// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.ListIterator;

// Referenced classes of package com.google.common.collect:
//            TransformedIterator

abstract class TransformedListIterator extends TransformedIterator
    implements ListIterator
{

    TransformedListIterator(ListIterator listiterator)
    {
        super(listiterator);
    }

    public void add(Object obj)
    {
        throw new UnsupportedOperationException();
    }

    public final boolean hasPrevious()
    {
        return ((ListIterator)backingIterator).hasPrevious();
    }

    public final int nextIndex()
    {
        return ((ListIterator)backingIterator).nextIndex();
    }

    public final Object previous()
    {
        return transform(((ListIterator)backingIterator).previous());
    }

    public final int previousIndex()
    {
        return ((ListIterator)backingIterator).previousIndex();
    }

    public void set(Object obj)
    {
        throw new UnsupportedOperationException();
    }
}