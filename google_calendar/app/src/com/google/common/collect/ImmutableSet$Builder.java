// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            ImmutableSet, RegularImmutableSet, SingletonImmutableSet

public final class rrayBasedBuilder extends rrayBasedBuilder
{

    private Object hashTable[];

    public final volatile rrayBasedBuilder add(Object obj)
    {
        return (rrayBasedBuilder)add(obj);
    }

    public final uilder add(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            hashTable = null;
            super.add(obj);
            return this;
        }
    }

    public final uilder addAll(Iterable iterable)
    {
        if (iterable == null)
        {
            throw new NullPointerException();
        } else
        {
            super.addAll(iterable);
            return this;
        }
    }

    public final uilder addAll(Iterator iterator)
    {
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        uilder uilder;
        while (iterator.hasNext()) 
        {
            uilder = (uilder)add(iterator.next());
        }
        return this;
    }

    public final ImmutableSet build()
    {
        switch (size)
        {
        default:
            ImmutableSet immutableset = ImmutableSet.construct(size, contents);
            size = immutableset.size();
            forceCopy = true;
            hashTable = null;
            return immutableset;

        case 0: // '\0'
            return RegularImmutableSet.EMPTY;

        case 1: // '\001'
            return new SingletonImmutableSet(contents[0]);
        }
    }

    public uilder()
    {
        super(4);
    }
}
