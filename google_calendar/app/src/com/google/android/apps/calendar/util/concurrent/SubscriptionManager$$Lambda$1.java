// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.function.Consumer;
import java.util.Set;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            SubscriptionInstance, SubscriptionManager

final class arg._cls1
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

    (SubscriptionManager subscriptionmanager)
    {
        arg$1 = subscriptionmanager;
    }
}
