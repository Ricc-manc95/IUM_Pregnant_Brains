// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            Cancelable, CalendarFutures

final class arg._cls1
    implements Cancelable
{

    private final AtomicReference arg$1;

    public final void cancel(boolean flag)
    {
        CalendarFutures.lambda$asyncGet$8$CalendarFutures(arg$1, flag);
    }

    (AtomicReference atomicreference)
    {
        arg$1 = atomicreference;
    }
}
