// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Optional;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ImmutableCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class > extends >
{

    public final Object combine(List list)
    {
        int i = list.size();
        CollectPreconditions.checkNonnegative(i, "initialArraySize");
        ArrayList arraylist = new ArrayList(i);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) 
        {
            list = (Optional)iterator.next();
            if (list != null)
            {
                list = ((List) (list.orNull()));
            } else
            {
                list = null;
            }
            arraylist.add(list);
        }
        return Collections.unmodifiableList(arraylist);
    }

    ( , ImmutableCollection immutablecollection, boolean flag)
    {
        super(, immutablecollection, flag);
    }
}
