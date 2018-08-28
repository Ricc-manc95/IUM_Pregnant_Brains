// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.RandomAccess;

public final class rest extends AbstractList
    implements Serializable, RandomAccess
{

    public static final long serialVersionUID = 0L;
    private final Object first;
    private final Object rest[];

    public final Object get(int i)
    {
        Preconditions.checkElementIndex(i, size());
        if (i == 0)
        {
            return first;
        } else
        {
            return rest[i - 1];
        }
    }

    public final int size()
    {
        return IntMath.saturatedAdd(rest.length, 1);
    }

    public (Object obj, Object aobj[])
    {
        first = obj;
        if (aobj == null)
        {
            throw new NullPointerException();
        } else
        {
            rest = (Object[])aobj;
            return;
        }
    }
}
