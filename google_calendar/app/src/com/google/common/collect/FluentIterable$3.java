// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            FluentIterable

final class nit> extends FluentIterable
{

    public final Iterable val$inputs[];

    public final Iterator iterator()
    {
        class _cls1 extends AbstractIndexedListIterator
        {

            private final FluentIterable._cls3 this$0;

            public final Object get(int i)
            {
                return inputs[i].iterator();
            }

            _cls1(int i)
            {
                this$0 = FluentIterable._cls3.this;
                super(i);
            }
        }

        return new natedIterator(new _cls1(val$inputs.length));
    }

    _cls1()
    {
        val$inputs = aiterable;
        super();
    }
}
