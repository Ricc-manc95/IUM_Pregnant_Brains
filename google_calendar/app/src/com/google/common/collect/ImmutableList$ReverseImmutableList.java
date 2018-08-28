// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.List;

// Referenced classes of package com.google.common.collect:
//            ImmutableList, ImmutableCollection

final class forwardList extends ImmutableList
{

    private final transient ImmutableList forwardList;

    public final boolean contains(Object obj)
    {
        return forwardList.contains(obj);
    }

    public final Object get(int i)
    {
        Preconditions.checkElementIndex(i, size());
        return forwardList.get(size() - 1 - i);
    }

    public final int indexOf(Object obj)
    {
        int i = forwardList.lastIndexOf(obj);
        if (i >= 0)
        {
            return size() - 1 - i;
        } else
        {
            return -1;
        }
    }

    final boolean isPartialView()
    {
        return forwardList.isPartialView();
    }

    public final int lastIndexOf(Object obj)
    {
        int i = forwardList.indexOf(obj);
        if (i >= 0)
        {
            return size() - 1 - i;
        } else
        {
            return -1;
        }
    }

    public final ImmutableList reverse()
    {
        return forwardList;
    }

    public final int size()
    {
        return forwardList.size();
    }

    public final ImmutableList subList(int i, int j)
    {
        Preconditions.checkPositionIndexes(i, j, size());
        return ((ImmutableList)forwardList.subList(size() - j, size() - i)).reverse();
    }

    public final volatile List subList(int i, int j)
    {
        return subList(i, j);
    }

    (ImmutableList immutablelist)
    {
        forwardList = immutablelist;
    }
}
