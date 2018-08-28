// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Arrays;
import java.util.Collection;

// Referenced classes of package com.google.common.collect:
//            CollectPreconditions, ImmutableCollection

public class size extends size
{

    public Object contents[];
    public boolean forceCopy;
    public int size;

    private final void getReadyToExpandTo(int i)
    {
        if (contents.length < i)
        {
            contents = Arrays.copyOf(contents, expandedCapacity(contents.length, i));
            forceCopy = false;
        } else
        if (forceCopy)
        {
            contents = (Object[])((Object []) (contents)).clone();
            forceCopy = false;
            return;
        }
    }

    public forceCopy add(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            getReadyToExpandTo(size + 1);
            Object aobj[] = contents;
            int i = size;
            size = i + 1;
            aobj[i] = obj;
            return this;
        }
    }

    public volatile size add(Object obj)
    {
        return add(obj);
    }

    public add addAll(Iterable iterable)
    {
        if (iterable instanceof Collection)
        {
            Collection collection = (Collection)iterable;
            getReadyToExpandTo(size + collection.size());
            if (collection instanceof ImmutableCollection)
            {
                size = ((ImmutableCollection)collection).copyIntoArray(contents, size);
                return this;
            }
        }
        super.size(iterable);
        return this;
    }

    (int i)
    {
        CollectPreconditions.checkNonnegative(i, "initialCapacity");
        contents = new Object[i];
        size = 0;
    }
}
