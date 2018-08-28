// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.List;

// Referenced classes of package com.google.common.collect:
//            ImmutableList

final class length extends ImmutableList
{

    private final transient int length;
    private final transient int offset;
    private final ImmutableList this$0;

    public final Object get(int i)
    {
        Preconditions.checkElementIndex(i, length);
        return ImmutableList.this.get(offset + i);
    }

    final boolean isPartialView()
    {
        return true;
    }

    public final int size()
    {
        return length;
    }

    public final ImmutableList subList(int i, int j)
    {
        Preconditions.checkPositionIndexes(i, j, length);
        return (ImmutableList)ImmutableList.this.subList(offset + i, offset + j);
    }

    public final volatile List subList(int i, int j)
    {
        return subList(i, j);
    }

    (int i, int j)
    {
        this$0 = ImmutableList.this;
        super();
        offset = i;
        length = j;
    }
}
