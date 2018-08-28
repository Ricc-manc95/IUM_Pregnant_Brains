// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            PromotionSyncImpl

final class arg._cls1
    implements AsyncCallable
{

    private final List arg$1;

    public final ListenableFuture call()
    {
        return PromotionSyncImpl.lambda$sync$12$PromotionSyncImpl(arg$1);
    }

    (List list)
    {
        arg$1 = list;
    }
}
