// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.function.BiFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            CalendarFutures

final class arg._cls3
    implements Callable
{

    private final BiFunction arg$1;
    private final ListenableFuture arg$2;
    private final ListenableFuture arg$3;

    public final Object call()
    {
        return CalendarFutures.lambda$transform$0$CalendarFutures(arg$1, arg$2, arg$3);
    }

    (BiFunction bifunction, ListenableFuture listenablefuture, ListenableFuture listenablefuture1)
    {
        arg$1 = bifunction;
        arg$2 = listenablefuture;
        arg$3 = listenablefuture1;
    }
}
