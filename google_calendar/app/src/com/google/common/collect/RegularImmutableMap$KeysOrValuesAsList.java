// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Preconditions;

// Referenced classes of package com.google.common.collect:
//            ImmutableList

final class size extends ImmutableList
{

    private final transient Object alternatingKeysAndValues[];
    private final transient int offset;
    private final transient int size;

    public final Object get(int i)
    {
        Preconditions.checkElementIndex(i, size);
        return alternatingKeysAndValues[i * 2 + offset];
    }

    final boolean isPartialView()
    {
        return true;
    }

    public final int size()
    {
        return size;
    }

    (Object aobj[], int i, int j)
    {
        alternatingKeysAndValues = aobj;
        offset = i;
        size = j;
    }
}
