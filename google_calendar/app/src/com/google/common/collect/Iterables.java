// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public final class Iterables
{

    public static boolean isEmpty(Iterable iterable)
    {
        if (iterable instanceof Collection)
        {
            return ((Collection)iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }

    public static boolean removeIf(Iterable iterable, Predicate predicate)
    {
        if (!(iterable instanceof RandomAccess) || !(iterable instanceof List)) goto _L2; else goto _L1
_L1:
        boolean flag1;
        iterable = (List)iterable;
        if (predicate == null)
        {
            throw new NullPointerException();
        }
        flag1 = removeIfFromRandomAccessList(iterable, (Predicate)predicate);
_L4:
        return flag1;
_L2:
        iterable = iterable.iterator();
        if (predicate == null)
        {
            throw new NullPointerException();
        }
        boolean flag = false;
        do
        {
            flag1 = flag;
            if (!iterable.hasNext())
            {
                continue;
            }
            if (predicate.apply(iterable.next()))
            {
                iterable.remove();
                flag = true;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static boolean removeIfFromRandomAccessList(List list, Predicate predicate)
    {
        int i;
        int j;
        boolean flag;
        flag = false;
        j = 0;
        i = 0;
_L2:
        Object obj;
        int k;
        if (i >= list.size())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = list.get(i);
        k = j;
        if (predicate.apply(obj))
        {
            break MISSING_BLOCK_LABEL_62;
        }
        if (i <= j)
        {
            break MISSING_BLOCK_LABEL_56;
        }
        list.set(j, obj);
        k = j + 1;
        i++;
        j = k;
        if (true) goto _L2; else goto _L1
        Object obj1;
        obj1;
        slowRemoveIfForRemainingElements(list, predicate, j, i);
        flag = true;
_L4:
        return flag;
        obj1;
        slowRemoveIfForRemainingElements(list, predicate, j, i);
        return true;
_L1:
        list.subList(j, list.size()).clear();
        if (i != j)
        {
            return true;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static void slowRemoveIfForRemainingElements(List list, Predicate predicate, int i, int j)
    {
        for (int k = list.size() - 1; k > j; k--)
        {
            if (predicate.apply(list.get(k)))
            {
                list.remove(k);
            }
        }

        for (j--; j >= i; j--)
        {
            list.remove(j);
        }

    }
}
