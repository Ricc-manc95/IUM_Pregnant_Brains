// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            TriggeringEventProcessor

final class arg._cls1
    implements Callable
{

    private final ListenableFuture arg$1;

    public final Object call()
    {
        return TriggeringEventProcessor.lambda$processEvent$0$TriggeringEventProcessor(arg$1);
    }

    (ListenableFuture listenablefuture)
    {
        arg$1 = listenablefuture;
    }
}
