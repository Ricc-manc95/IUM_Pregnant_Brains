// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;


// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            Subscription, SubscriptionInstance

final class arg._cls1
    implements Subscription
{

    private final SubscriptionInstance arg$1;

    public final void cancel(boolean flag)
    {
        arg$1.cancel(flag);
    }

    (SubscriptionInstance subscriptioninstance)
    {
        arg$1 = subscriptioninstance;
    }
}
