// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.Lens;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Equivalence;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            SubscriptionManager

public final class arg._cls4
    implements Consumer
{

    private final Lens arg$1;
    private final Equivalence arg$2;
    private final AtomicReference arg$3;
    private final SubscriptionManager arg$4;

    public final void accept(Object obj)
    {
        Lens lens = arg$1;
        Equivalence equivalence = arg$2;
        Object obj1 = arg$3;
        SubscriptionManager subscriptionmanager = arg$4;
        obj = lens.get(obj);
        obj1 = ((AtomicReference) (obj1)).getAndSet(obj);
        boolean flag;
        if (obj1 == obj)
        {
            flag = true;
        } else
        if (obj1 == null || obj == null)
        {
            flag = false;
        } else
        {
            flag = equivalence.doEquivalent(obj1, obj);
        }
        if (!flag)
        {
            obj = (Collection)subscriptionmanager.apply(obj);
        }
    }

    public (Lens lens, Equivalence equivalence, AtomicReference atomicreference, SubscriptionManager subscriptionmanager)
    {
        arg$1 = lens;
        arg$2 = equivalence;
        arg$3 = atomicreference;
        arg$4 = subscriptionmanager;
    }
}
