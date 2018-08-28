// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

// Referenced classes of package com.google.protobuf:
//            LazyStringList, ByteString

public final class UnmodifiableLazyStringList extends AbstractList
    implements LazyStringList, RandomAccess
{

    public final LazyStringList list;

    public UnmodifiableLazyStringList(LazyStringList lazystringlist)
    {
        list = lazystringlist;
    }

    public final void add(ByteString bytestring)
    {
        throw new UnsupportedOperationException();
    }

    public final Object get(int i)
    {
        return (String)list.get(i);
    }

    public final Object getRaw(int i)
    {
        return list.getRaw(i);
    }

    public final List getUnderlyingElements()
    {
        return list.getUnderlyingElements();
    }

    public final LazyStringList getUnmodifiableView()
    {
        return this;
    }

    public final Iterator iterator()
    {
        return new _cls2();
    }

    public final ListIterator listIterator(final int index)
    {
        return new _cls1();
    }

    public final int size()
    {
        return list.size();
    }

    private class _cls2
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

        _cls2()
        {
            this$0 = UnmodifiableLazyStringList.this;
            super();
            iter = list.iterator();
        }
    }


    private class _cls1
        implements ListIterator
    {

        private ListIterator iter;
        private final UnmodifiableLazyStringList this$0;
        private final int val$index;

        public final void add(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        public final boolean hasNext()
        {
            return iter.hasNext();
        }

        public final boolean hasPrevious()
        {
            return iter.hasPrevious();
        }

        public final Object next()
        {
            return (String)iter.next();
        }

        public final int nextIndex()
        {
            return iter.nextIndex();
        }

        public final Object previous()
        {
            return (String)iter.previous();
        }

        public final int previousIndex()
        {
            return iter.previousIndex();
        }

        public final void remove()
        {
            throw new UnsupportedOperationException();
        }

        public final void set(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        _cls1()
        {
            this$0 = UnmodifiableLazyStringList.this;
            index = i;
            super();
            iter = list.listIterator(index);
        }
    }

}
