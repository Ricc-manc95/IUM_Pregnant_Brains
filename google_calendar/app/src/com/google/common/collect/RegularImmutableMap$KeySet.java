// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            ImmutableSet, ImmutableMap, ImmutableCollection, UnmodifiableIterator, 
//            ImmutableList

final class list extends ImmutableSet
{

    private final transient ImmutableList list;
    private final transient ImmutableMap map;

    public final ImmutableList asList()
    {
        return list;
    }

    public final boolean contains(Object obj)
    {
        return map.get(obj) != null;
    }

    final int copyIntoArray(Object aobj[], int i)
    {
        return asList().copyIntoArray(aobj, i);
    }

    final boolean isPartialView()
    {
        return true;
    }

    public final UnmodifiableIterator iterator()
    {
        return (UnmodifiableIterator)asList().iterator();
    }

    public final volatile Iterator iterator()
    {
        return iterator();
    }

    public final int size()
    {
        return map.size();
    }

    (ImmutableMap immutablemap, ImmutableList immutablelist)
    {
        map = immutablemap;
        list = immutablelist;
    }
}
