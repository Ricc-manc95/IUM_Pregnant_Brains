// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            ImmutableSet, ImmutableList, ImmutableCollection, UnmodifiableIterator

public final class SingletonImmutableSet extends ImmutableSet
{

    private transient int cachedHashCode;
    private final transient Object element;

    public SingletonImmutableSet(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            element = obj;
            return;
        }
    }

    SingletonImmutableSet(Object obj, int i)
    {
        element = obj;
        cachedHashCode = i;
    }

    public final boolean contains(Object obj)
    {
        return element.equals(obj);
    }

    final int copyIntoArray(Object aobj[], int i)
    {
        aobj[i] = element;
        return i + 1;
    }

    final ImmutableList createAsList()
    {
        return ImmutableList.of(element);
    }

    public final int hashCode()
    {
        int j = cachedHashCode;
        int i = j;
        if (j == 0)
        {
            i = element.hashCode();
            cachedHashCode = i;
        }
        return i;
    }

    final boolean isHashCodeFast()
    {
        return cachedHashCode != 0;
    }

    final boolean isPartialView()
    {
        return false;
    }

    public final UnmodifiableIterator iterator()
    {
        return new Iterators._cls9(element);
    }

    public final volatile Iterator iterator()
    {
        return iterator();
    }

    public final int size()
    {
        return 1;
    }

    public final String toString()
    {
        String s = element.toString();
        return (new StringBuilder(String.valueOf(s).length() + 2)).append('[').append(s).append(']').toString();
    }
}
