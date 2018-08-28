// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.Lens;
import com.google.android.apps.calendar.util.function.Consumer;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            ObservableReference, SubscriptionManager, Subscription

public final class val.subscriptionManager
    implements ObservableReference
{

    private final Lens val$lens;
    private final ObservableReference val$observable;
    private final SubscriptionManager val$subscriptionManager;

    public final Object get()
    {
        return val$lens.get(val$observable.get());
    }

    public final void set(Object obj)
    {
        val$observable.set(val$lens.update(val$observable.get(), obj));
    }

    public final void set(Object obj, Runnable runnable, Executor executor)
    {
        val$observable.set(val$lens.update(val$observable.get(), obj), runnable, executor);
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
            executor.execute(new .Lambda._cls0(consumer, val$lens.get(val$observable.get())));
        }
        return val$subscriptionManager.subscribeFunction(new mbda._cls0(consumer), executor);
    }

    public mbda._cls0()
    {
        val$lens = lens1;
        val$observable = observablereference;
        val$subscriptionManager = subscriptionmanager;
        super();
    }
}
