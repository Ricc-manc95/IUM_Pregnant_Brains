// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Preconditions;

// Referenced classes of package com.google.common.collect:
//            ImmutableList

final class RegularImmutableList extends ImmutableList
{

    public static final ImmutableList EMPTY = new RegularImmutableList(new Object[0], 0);
    private final transient Object array[];
    private final transient int size;

    RegularImmutableList(Object aobj[], int i)
    {
        array = aobj;
        size = i;
    }

    final int copyIntoArray(Object aobj[], int i)
    {
        System.arraycopy(((Object) (array)), 0, ((Object) (aobj)), i, size);
        return size + i;
    }

    public final Object get(int i)
    {
        Preconditions.checkElementIndex(i, size);
        return array[i];
    }

    final boolean isPartialView()
    {
        return false;
    }

    public final int size()
    {
        return size;
    }

}
