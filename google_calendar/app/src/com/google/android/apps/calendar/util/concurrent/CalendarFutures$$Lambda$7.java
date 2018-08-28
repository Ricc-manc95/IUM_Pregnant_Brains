// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.apps.calendar.util.handler.Handler;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            CalendarFutures

final class arg._cls6
    implements Runnable
{

    private final AtomicReference arg$1;
    private final Handler arg$2;
    private final Handler arg$3;
    private final Handler arg$4;
    private final Consumer arg$5;
    private final Runnable arg$6;

    public final void run()
    {
        CalendarFutures.lambda$asyncGet$7$CalendarFutures(arg$1, arg$2, arg$3, arg$4, arg$5, arg$6);
    }

    (AtomicReference atomicreference, Handler handler, Handler handler1, Handler handler2, Consumer consumer, Runnable runnable)
    {
        arg$1 = atomicreference;
        arg$2 = handler;
        arg$3 = handler1;
        arg$4 = handler2;
        arg$5 = consumer;
        arg$6 = runnable;
    }
}
