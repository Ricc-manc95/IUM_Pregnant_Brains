// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class Iterators
{

    public static boolean addAll(Collection collection, Iterator iterator)
    {
        if (collection == null)
        {
            throw new NullPointerException();
        }
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        boolean flag;
        for (flag = false; iterator.hasNext(); flag |= collection.add(iterator.next())) { }
        return flag;
    }

    static void clear(Iterator iterator)
    {
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        for (; iterator.hasNext(); iterator.remove())
        {
            iterator.next();
        }

    }

    public static Object find(Iterator iterator, Predicate predicate)
    {
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        if (predicate == null)
        {
            throw new NullPointerException();
        }
        while (iterator.hasNext()) 
        {
            Object obj = iterator.next();
            if (predicate.apply(obj))
            {
                return obj;
            }
        }
        throw new NoSuchElementException();
    }

    public static int indexOf(Iterator iterator, Predicate predicate)
    {
        if (predicate == null)
        {
            throw new NullPointerException(String.valueOf("predicate"));
        }
        for (int i = 0; iterator.hasNext(); i++)
        {
            if (predicate.apply(iterator.next()))
            {
                return i;
            }
        }

        return -1;
    }
}
