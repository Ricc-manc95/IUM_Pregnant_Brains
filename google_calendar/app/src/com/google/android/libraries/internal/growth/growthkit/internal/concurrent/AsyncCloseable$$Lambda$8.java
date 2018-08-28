// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.concurrent:
//            AsyncCloseable, AsyncCloseableFunction

final class arg._cls2
    implements AsyncFunction
{

    private final AsyncCloseableFunction arg$1;
    private final ist arg$2;

    public final ListenableFuture apply(Object obj)
    {
        return AsyncCloseable.lambda$createWrapperAsyncFunction$7$AsyncCloseable(arg$1, arg$2, obj);
    }

    ist(AsyncCloseableFunction asynccloseablefunction, ist ist)
    {
        arg$1 = asynccloseablefunction;
        arg$2 = ist;
    }
}
