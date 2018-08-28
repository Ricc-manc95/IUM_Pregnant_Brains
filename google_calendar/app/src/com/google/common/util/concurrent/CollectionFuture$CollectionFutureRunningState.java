// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Absent;
import com.google.common.base.Present;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture, CollectionFuture

abstract class values extends values
{

    private final CollectionFuture this$0;
    private List values;

    final void collectOneValue(boolean flag, int i, Object obj)
    {
        List list = values;
        if (list != null)
        {
            if (obj == null)
            {
                obj = Absent.INSTANCE;
            } else
            {
                obj = new Present(obj);
            }
            list.set(i, obj);
        } else
        {
            if (flag || isCancelled())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                throw new IllegalStateException(String.valueOf("Future was done before all dependencies completed"));
            }
        }
    }

    abstract Object combine(List list);

    final void handleAllCompleted()
    {
        List list = values;
        if (list != null)
        {
            set(combine(list));
        } else
        if (!isDone())
        {
            throw new IllegalStateException();
        }
    }

    final void releaseResourcesAfterFailure()
    {
        super.fterFailure();
        values = null;
    }

    (ImmutableCollection immutablecollection, boolean flag)
    {
        this$0 = CollectionFuture.this;
        super(CollectionFuture.this, immutablecollection, flag, true);
        if (immutablecollection.isEmpty())
        {
            collectionfuture = ImmutableList.of();
        } else
        {
            int j = immutablecollection.size();
            CollectPreconditions.checkNonnegative(j, "initialArraySize");
            collectionfuture = new ArrayList(j);
        }
        values = CollectionFuture.this;
        for (int i = 0; i < immutablecollection.size(); i++)
        {
            values.add(null);
        }

    }
}
