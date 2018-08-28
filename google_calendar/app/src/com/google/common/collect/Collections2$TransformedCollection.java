// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Function;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public final class function extends AbstractCollection
{

    private final Collection fromCollection;
    private final Function function;

    public final void clear()
    {
        fromCollection.clear();
    }

    public final boolean isEmpty()
    {
        return fromCollection.isEmpty();
    }

    public final Iterator iterator()
    {
        Iterator iterator1 = fromCollection.iterator();
        Function function1 = function;
        if (function1 == null)
        {
            throw new NullPointerException();
        } else
        {
            return new function(iterator1, function1);
        }
    }

    public final int size()
    {
        return fromCollection.size();
    }

    public (Collection collection, Function function1)
    {
        if (collection == null)
        {
            throw new NullPointerException();
        }
        fromCollection = (Collection)collection;
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
