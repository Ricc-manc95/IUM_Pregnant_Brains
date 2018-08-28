// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.rpc.GrowthApiClient;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            PromotionSyncImpl

final class arg._cls2
    implements AsyncFunction
{

    private final PromotionSyncImpl arg$1;
    private final String arg$2;

    public final ListenableFuture apply(Object obj)
    {
        PromotionSyncImpl promotionsyncimpl = arg$1;
        String s = arg$2;
        obj = (com.google.identity.boq.growth.promoprovider.proto.st)obj;
        return promotionsyncimpl.growthApiClient.getPromos(((com.google.identity.boq.growth.promoprovider.proto.st) (obj)), s);
    }

    (PromotionSyncImpl promotionsyncimpl, String s)
    {
        arg$1 = promotionsyncimpl;
        arg$2 = s;
    }
}
