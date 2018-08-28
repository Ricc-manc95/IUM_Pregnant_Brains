// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            CalendarFutures, FutureReferenceCache

public final class arg._cls3
    implements Supplier
{

    private final FutureReferenceCache arg$1;
    private final Supplier arg$2;
    private final Object arg$3;

    public final Object get()
    {
        FutureReferenceCache futurereferencecache = arg$1;
        Supplier supplier = arg$2;
        Object obj = arg$3;
        return CalendarFutures.withSideEffect((ListenableFuture)supplier.get(), new <init>(futurereferencecache, obj), com.google.common.util.concurrent.STANCE);
    }

    public (FutureReferenceCache futurereferencecache, Supplier supplier, Object obj)
    {
        arg$1 = futurereferencecache;
        arg$2 = supplier;
        arg$3 = obj;
    }
}
