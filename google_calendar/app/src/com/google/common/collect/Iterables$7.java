// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            FluentIterable

public final class le extends FluentIterable
{

    private final Iterable val$iterable;
    private final int val$limitSize;

    public final Iterator iterator()
    {
        Iterator iterator1 = val$iterable.iterator();
        int i = val$limitSize;
        if (iterator1 == null)
        {
            throw new NullPointerException();
        }
        boolean flag;
        if (i >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("limit is negative"));
        } else
        {
            return new <init>(i, iterator1);
        }
    }

    public le()
    {
        val$iterable = iterable1;
        val$limitSize = i;
        super();
    }
}
