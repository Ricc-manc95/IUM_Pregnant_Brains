// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import java.util.Iterator;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            Subscription

public final class arg._cls1
    implements Subscription
{

    private final Iterable arg$1;

    public final void cancel(boolean flag)
    {
        for (Iterator iterator = arg$1.iterator(); iterator.hasNext(); ((Subscription)iterator.next()).cancel(flag)) { }
    }

    public (Iterable iterable)
    {
        arg$1 = iterable;
    }
}
