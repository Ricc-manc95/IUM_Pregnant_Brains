// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            ObservableReference, SubscriptionManager, Subscription

public final class val.initialValue
    implements ObservableReference
{

    private final SubscriptionManager subscriptionManager = new SubscriptionManager();
    private final Object val$initialValue;
    private volatile Object value;

    public final Object get()
    {
        return value;
    }

    public final void set(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            value = obj;
            obj = (Collection)subscriptionManager.apply(value);
            return;
        }
    }

    public final void set(Object obj, Runnable runnable, Executor executor)
    {
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            value = obj;
            (new com.google.common.util.concurrent.ture(ImmutableList.copyOf((Collection)subscriptionManager.apply(value)), true)).addListener(runnable, executor);
            return;
        }
    }

    public final Subscription subscribe(Consumer consumer, Executor executor, boolean flag)
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final Consumer arg$1;
            private final Object arg$2;

            public final void run()
            {
                arg$1.accept(arg$2);
            }

            .Lambda._cls0(Consumer consumer, Object obj)
            {
                arg$1 = consumer;
                arg$2 = obj;
            }
        }

        if (flag)
        {
            executor.execute(new .Lambda._cls0(consumer, value));
        }
        return subscriptionManager.subscribeFunction(new mbda._cls0(consumer), executor);
    }

    public mbda._cls0()
    {
        val$initialValue = obj;
        super();
        value = val$initialValue;
    }
}
