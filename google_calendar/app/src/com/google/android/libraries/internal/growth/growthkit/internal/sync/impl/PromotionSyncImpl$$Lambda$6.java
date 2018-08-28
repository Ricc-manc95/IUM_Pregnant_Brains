// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.PromotionKeysHelper;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

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
        com.google.identity.boq.growth.promoprovider.proto.nse nse = (com.google.identity.boq.growth.promoprovider.proto.nse)obj;
        if (((Boolean)promotionsyncimpl.setWriteDebugInfo.get()).booleanValue())
        {
            obj = PromotionSyncImpl.logger;
            obj = nse.debugInfoKey_;
        }
        HashMap hashmap = new HashMap(nse.promo_.promo_());
        Iterator iterator = nse.promo_.iterator();
        while (iterator.hasNext()) 
        {
            com.google.identity.boq.growth.promoprovider.proto.nse.Promotion promotion = (com.google.identity.boq.growth.promoprovider.proto.nse.Promotion)iterator.next();
            if (promotion.promoId_ == null)
            {
                obj = com.google.identity.boq.growth.promoprovider.proto.ation.DEFAULT_INSTANCE;
            } else
            {
                obj = promotion.promoId_;
            }
            hashmap.put(PromotionKeysHelper.of(((com.google.identity.boq.growth.promoprovider.proto.ation) (obj))), promotion);
        }
        return AbstractTransformFuture.create(((MessageStore)promotionsyncimpl.promotionStoreProvider.forAccount(s)).clearAndPutAll(hashmap), new (nse), com.google.common.util.concurrent..INSTANCE);
    }

    (PromotionSyncImpl promotionsyncimpl, String s)
    {
        arg$1 = promotionsyncimpl;
        arg$2 = s;
    }
}
