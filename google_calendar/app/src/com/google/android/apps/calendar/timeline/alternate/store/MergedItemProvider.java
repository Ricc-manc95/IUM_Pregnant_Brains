// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.android.apps.calendar.timeline.alternate.view.api.ItemProvider;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import java.util.List;

public final class MergedItemProvider
    implements ItemProvider
{

    private final List providers;

    public MergedItemProvider(List list)
    {
        providers = ImmutableList.copyOf(list);
    }

    public final FluentFuture loadItems(int i, int j)
    {
        Object obj = providers;
        class .Lambda._cls0
            implements Function
        {

            private final int arg$1;
            private final int arg$2;

            public final Object apply(Object obj1)
            {
                int k = arg$1;
                int l = arg$2;
                return ((ItemProvider)obj1).loadItems(k, l);
            }

            .Lambda._cls0(int i, int j)
            {
                arg$1 = i;
                arg$2 = j;
            }
        }

        .Lambda._cls0 _lcls0 = new .Lambda._cls0(i, j);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (_lcls0 == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(new com.google.common.collect.Iterables._cls5(((Iterable) (obj)), _lcls0)), true);
        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj1)
            {
                obj1 = (List)obj1;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return ImmutableList.copyOf(new com.google.common.collect.FluentIterable._cls2(((Iterable) (obj1))));
                }
            }


            private .Lambda._cls1()
            {
            }
        }

        if (obj instanceof FluentFuture)
        {
            obj = (FluentFuture)obj;
        } else
        {
            obj = new ForwardingFluentFuture(((com.google.common.util.concurrent.ListenableFuture) (obj)));
        }
        return (FluentFuture)AbstractTransformFuture.create(((com.google.common.util.concurrent.ListenableFuture) (obj)), .Lambda._cls1..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }
}
