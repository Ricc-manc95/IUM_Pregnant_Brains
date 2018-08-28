// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            PromotionSyncImpl

final class arg._cls2
    implements AsyncFunction
{

    private final PromotionSyncImpl arg$1;
    private final String arg$2;

    public final ListenableFuture apply(Object obj)
    {
        obj = arg$1;
        String s = arg$2;
        return ((MessageStore)((PromotionSyncImpl) (obj)).presentedPromoStoreProvider.forAccount(s)).clearAndPutAll(Collections.emptyMap());
    }

    (PromotionSyncImpl promotionsyncimpl, String s)
    {
        arg$1 = promotionsyncimpl;
        arg$2 = s;
    }
}
