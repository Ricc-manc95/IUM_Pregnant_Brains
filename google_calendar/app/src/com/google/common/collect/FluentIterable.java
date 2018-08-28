// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.util.Iterator;

public abstract class FluentIterable
    implements Iterable
{

    public final Optional iterableDelegate;

    protected FluentIterable()
    {
        iterableDelegate = Absent.INSTANCE;
    }

    FluentIterable(Iterable iterable)
    {
        if (iterable == null)
        {
            throw new NullPointerException();
        }
        if (this == iterable)
        {
            iterable = null;
        }
        if (iterable == null)
        {
            iterable = Absent.INSTANCE;
        } else
        {
            iterable = new Present(iterable);
        }
        iterableDelegate = iterable;
    }

    public static transient FluentIterable concatNoDefensiveCopy(final Iterable inputs[])
    {
        int j = inputs.length;
        for (int i = 0; i < j; i++)
        {
            if (inputs[i] == null)
            {
                throw new NullPointerException();
            }
        }

        return new _cls3();
    }

    public String toString()
    {
        Iterator iterator = ((Iterable)iterableDelegate.or(this)).iterator();
        StringBuilder stringbuilder = new StringBuilder("[");
        boolean flag = true;
        for (; iterator.hasNext(); stringbuilder.append(iterator.next()))
        {
            if (!flag)
            {
                stringbuilder.append(", ");
            }
            flag = false;
        }

        return stringbuilder.append(']').toString();
    }

    private class _cls3 extends FluentIterable
    {

        public final Iterable val$inputs[];

        public final Iterator iterator()
        {
            class _cls1 extends AbstractIndexedListIterator
            {

                private final _cls3 this$0;

                public final Object get(int i)
                {
                    return inputs[i].iterator();
                }

                _cls1(int i)
                {
                    this$0 = _cls3.this;
                    super(i);
                }
            }

            return new Iterators.ConcatenatedIterator(new _cls1(inputs.length));
        }

        _cls3()
        {
            inputs = aiterable;
            super();
        }
    }

}
