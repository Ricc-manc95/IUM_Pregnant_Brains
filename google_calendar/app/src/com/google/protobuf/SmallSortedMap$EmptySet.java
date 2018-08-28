// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Iterator;

final class _cls2
{

    public static final Iterable ITERABLE = new _cls2();
    public static final Iterator ITERATOR = new _cls1();


    class _cls1
        implements Iterator
    {

        public final boolean hasNext()
        {
            return false;
        }

        public final Object next()
        {
            throw new NoSuchElementException();
        }

        public final void remove()
        {
            throw new UnsupportedOperationException();
        }

            _cls1()
            {
            }
    }


    class _cls2
        implements Iterable
    {

        public final Iterator iterator()
        {
            return SmallSortedMap.EmptySet.ITERATOR;
        }

            _cls2()
            {
            }
    }

}
