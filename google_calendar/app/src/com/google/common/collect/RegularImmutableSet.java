// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            ImmutableSet, ImmutableList, ImmutableCollection, UnmodifiableIterator

public final class RegularImmutableSet extends ImmutableSet
{

    public static final RegularImmutableSet EMPTY = new RegularImmutableSet(new Object[0], 0, null, 0, 0);
    private final transient Object elements[];
    private final transient int hashCode;
    private final transient int mask;
    private final transient int size;
    private final transient Object table[];

    RegularImmutableSet(Object aobj[], int i, Object aobj1[], int j, int k)
    {
        elements = aobj;
        table = aobj1;
        mask = j;
        hashCode = i;
        size = k;
    }

    public final boolean contains(Object obj)
    {
        Object aobj[] = table;
        if (obj != null && aobj != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int i;
        Object obj1;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        i = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15));
        i &= mask;
        obj1 = aobj[i];
        if (obj1 != null)
        {
            if (obj1.equals(obj))
            {
                return true;
            }
            i++;
            break MISSING_BLOCK_LABEL_43;
        }
        continue; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L3
_L3:
    }

    final int copyIntoArray(Object aobj[], int i)
    {
        System.arraycopy(((Object) (elements)), 0, ((Object) (aobj)), i, size);
        return size + i;
    }

    final ImmutableList createAsList()
    {
        return ImmutableList.asImmutableList(elements, size);
    }

    public final int hashCode()
    {
        return hashCode;
    }

    final boolean isHashCodeFast()
    {
        return true;
    }

    final boolean isPartialView()
    {
        return false;
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
        return size;
    }

}
