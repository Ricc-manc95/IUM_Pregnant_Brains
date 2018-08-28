// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.util.Iterator;

final class val.value
    implements Iterable
{

    public final Object val$value;

    public final Iterator iterator()
    {
        class _cls1
            implements Iterator
        {

            private int index;
            private final int length;
            private final Types._cls1 this$0;

            public final boolean hasNext()
            {
                return index < length;
            }

            public final Object next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                } else
                {
                    Object obj = value;
                    int i = index;
                    index = i + 1;
                    return Array.get(obj, i);
                }
            }

            public final void remove()
            {
                throw new UnsupportedOperationException();
            }

            _cls1()
            {
                this$0 = Types._cls1.this;
                super();
                length = Array.getLength(value);
                index = 0;
            }
        }

        return new _cls1();
    }

    _cls1()
    {
        val$value = obj;
        super();
    }
}
