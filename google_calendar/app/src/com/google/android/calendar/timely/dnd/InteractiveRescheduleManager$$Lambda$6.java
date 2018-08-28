// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.timely.dnd:
//            InteractiveRescheduleManager

final class arg._cls2
    implements Supplier
{

    private final ListenableFuture arg$1;
    private final ListenableFuture arg$2;

    public final Object get()
    {
        return InteractiveRescheduleManager.lambda$reschedule$6$InteractiveRescheduleManager(arg$1, arg$2);
    }

    (ListenableFuture listenablefuture, ListenableFuture listenablefuture1)
    {
        arg$1 = listenablefuture;
        arg$2 = listenablefuture1;
    }
}
