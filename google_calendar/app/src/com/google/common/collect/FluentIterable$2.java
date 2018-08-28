// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            FluentIterable

public final class nit> extends FluentIterable
{

    private final Iterable val$inputs;

    public final Iterator iterator()
    {
        Iterator iterator1 = val$inputs.iterator();
        nit> nit> = new t>();
        if (nit> == null)
        {
            throw new NullPointerException();
        } else
        {
            return new natedIterator(new >(iterator1, nit>));
        }
    }

    public natedIterator()
    {
        val$inputs = iterable;
        super();
    }
}
