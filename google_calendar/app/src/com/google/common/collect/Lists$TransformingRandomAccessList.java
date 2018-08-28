// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Function;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class function extends AbstractList
    implements Serializable, RandomAccess
{

    public static final long serialVersionUID = 0L;
    private final List fromList;
    public final Function function;

    public final void clear()
    {
        fromList.clear();
    }

    public final Object get(int i)
    {
        return function.apply(fromList.get(i));
    }

    public final boolean isEmpty()
    {
        return fromList.isEmpty();
    }

    public final Iterator iterator()
    {
        return listIterator();
    }

    public final ListIterator listIterator(int i)
    {
        class _cls1 extends TransformedListIterator
        {

            private final Lists.TransformingRandomAccessList this$0;

            final Object transform(Object obj)
            {
                return function.apply(obj);
            }

            _cls1(ListIterator listiterator)
            {
                this$0 = Lists.TransformingRandomAccessList.this;
                super(listiterator);
            }
        }

        return new _cls1(fromList.listIterator(i));
    }

    public final Object remove(int i)
    {
        return function.apply(fromList.remove(i));
    }

    public final int size()
    {
        return fromList.size();
    }

    public _cls1(List list, Function function1)
    {
        if (list == null)
        {
            throw new NullPointerException();
        }
        fromList = (List)list;
        if (function1 == null)
        {
            throw new NullPointerException();
        } else
        {
            function = (Function)function1;
            return;
        }
    }
}
