// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            PromotionSyncImpl

final class arg._cls1
    implements AsyncFunction
{

    private final PromotionSyncImpl arg$1;

    public final ListenableFuture apply(Object obj)
    {
        PromotionSyncImpl promotionsyncimpl = arg$1;
        obj = (com.google.identity.boq.growth.promoprovider.proto.nse)obj;
        HashMap hashmap = new HashMap(((com.google.identity.boq.growth.promoprovider.proto.nse) (obj)).cappedPromo_.cappedPromo_());
        com.google.identity.boq.growth.promoprovider.proto.n n;
        for (Iterator iterator = ((com.google.identity.boq.growth.promoprovider.proto.nse) (obj)).cappedPromo_.iterator(); iterator.hasNext(); hashmap.put(String.valueOf(n.impressionCappingId_), n))
        {
            n = (com.google.identity.boq.growth.promoprovider.proto.n)iterator.next();
        }

        return AbstractTransformFuture.create(promotionsyncimpl.cappedPromotionStoreProvider.putAll(hashmap), new (((com.google.identity.boq.growth.promoprovider.proto.nse) (obj))), com.google.common.util.concurrent..INSTANCE);
    }

    (PromotionSyncImpl promotionsyncimpl)
    {
        arg$1 = promotionsyncimpl;
    }
}
