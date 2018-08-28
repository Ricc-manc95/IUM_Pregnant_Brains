// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Function;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            SubscriptionInstance

final class arg._cls2
    implements Callable
{

    private final SubscriptionInstance arg$1;
    private final Object arg$2;

    public final Object call()
    {
        SubscriptionInstance subscriptioninstance = arg$1;
        Object obj = arg$2;
        return subscriptioninstance.function.apply(obj);
    }

    (SubscriptionInstance subscriptioninstance, Object obj)
    {
        arg$1 = subscriptioninstance;
        arg$2 = obj;
    }
}
