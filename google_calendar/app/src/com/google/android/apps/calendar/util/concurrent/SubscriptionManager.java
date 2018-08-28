// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            SubscriptionInstance, Subscription

public final class SubscriptionManager
    implements Function
{

    public final Set subscriptions = Collections.newSetFromMap(new IdentityHashMap());

    public SubscriptionManager()
    {
    }

    public final volatile Object apply(Object obj)
    {
        return apply(obj);
    }

    public final Collection apply(Object obj)
    {
        ArrayList arraylist;
        synchronized (subscriptions)
        {
            arraylist = new ArrayList(subscriptions);
        }
        obj1 = new ArrayList(arraylist.size());
        arraylist = (ArrayList)arraylist;
        int j = arraylist.size();
        for (int i = 0; i < j;)
        {
            Object obj2 = arraylist.get(i);
            i++;
            ((List) (obj1)).add(((SubscriptionInstance)obj2).apply(obj));
        }

        break MISSING_BLOCK_LABEL_94;
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
        return ((Collection) (obj1));
    }

    public final Subscription subscribeFunction(Function function, Executor executor)
    {
        class .Lambda._cls1
            implements Consumer
        {

            private final SubscriptionManager arg$1;

            public final void accept(Object obj)
            {
                SubscriptionManager subscriptionmanager = arg$1;
                SubscriptionInstance subscriptioninstance = (SubscriptionInstance)obj;
                synchronized (subscriptionmanager.subscriptions)
                {
                    subscriptionmanager.subscriptions.remove(subscriptioninstance);
                }
                return;
                exception;
                obj;
                JVM INSTR monitorexit ;
                throw exception;
            }

            .Lambda._cls1()
            {
                arg$1 = SubscriptionManager.this;
            }
        }

        executor = new SubscriptionInstance(function, executor, new .Lambda._cls1());
        synchronized (subscriptions)
        {
            subscriptions.add(executor);
            executor.getClass();
            class .Lambda._cls2
                implements Subscription
            {

                private final SubscriptionInstance arg$1;

                public final void cancel(boolean flag)
                {
                    arg$1.cancel(flag);
                }

            .Lambda._cls2(SubscriptionInstance subscriptioninstance)
            {
                arg$1 = subscriptioninstance;
            }
            }

            executor = new .Lambda._cls2(executor);
        }
        return executor;
        executor;
        function;
        JVM INSTR monitorexit ;
        throw executor;
    }
}
