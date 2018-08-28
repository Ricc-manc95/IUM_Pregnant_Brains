// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.Closeable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.concurrent:
//            AsyncCloseable

public final class arg._cls2
    implements AsyncFunction
{

    private final ist arg$1;
    private final ListenableFuture arg$2;

    public final ListenableFuture apply(Object obj)
    {
        return AsyncCloseable.lambda$fromCloseableFuture$0$AsyncCloseable(arg$1, arg$2, (Closeable)obj);
    }

    public ist(ist ist, ListenableFuture listenablefuture)
    {
        arg$1 = ist;
        arg$2 = listenablefuture;
    }
}
