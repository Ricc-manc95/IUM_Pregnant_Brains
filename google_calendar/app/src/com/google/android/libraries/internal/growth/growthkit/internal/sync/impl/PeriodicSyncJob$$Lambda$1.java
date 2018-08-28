// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.sync.PromotionSync;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            PeriodicSyncJob

final class arg._cls1
    implements AsyncFunction
{

    private final PeriodicSyncJob arg$1;

    public final ListenableFuture apply(Object obj)
    {
        return arg$1.promotionSync.syncAllAccounts();
    }

    i(PeriodicSyncJob periodicsyncjob)
    {
        arg$1 = periodicsyncjob;
    }
}
