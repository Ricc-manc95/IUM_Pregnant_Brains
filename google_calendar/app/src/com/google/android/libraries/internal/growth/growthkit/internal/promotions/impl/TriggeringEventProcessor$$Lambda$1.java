// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.PromotionKeysHelper;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            TriggeringEventProcessor

final class arg._cls4
    implements AsyncCallable
{

    private final TriggeringEventProcessor arg$1;
    private final ListenableFuture arg$2;
    private final ListenableFuture arg$3;
    private final Context arg$4;

    public final ListenableFuture call()
    {
        Object obj = arg$1;
        Object obj2 = arg$2;
        ListenableFuture listenablefuture = arg$3;
        Object obj1 = arg$4;
        boolean flag = ((Boolean)((ListenableFuture) (obj2)).get()).booleanValue();
        obj2 = (Optional)listenablefuture.get();
        if (((Optional) (obj2)).isPresent())
        {
            if (flag)
            {
                obj = Boolean.valueOf(true);
                if (obj == null)
                {
                    return com.google.common.util.concurrent.Future.NULL;
                } else
                {
                    return new com.google.common.util.concurrent.Future(obj);
                }
            }
            obj1 = (MessageStore)((TriggeringEventProcessor) (obj)).presentedPromosStore.forAccount(((Context) (obj1)).accountName());
            obj = (com.google.identity.boq.growth.promoprovider.proto.motion)((Optional) (obj2)).get();
            if (((com.google.identity.boq.growth.promoprovider.proto.motion) (obj)).promoId_ == null)
            {
                obj = com.google.identity.boq.growth.promoprovider.proto.EFAULT_INSTANCE;
            } else
            {
                obj = ((com.google.identity.boq.growth.promoprovider.proto.motion) (obj)).promoId_;
            }
            return AbstractTransformFuture.create(((MessageStore) (obj1)).remove(PromotionKeysHelper.of(((com.google.identity.boq.growth.promoprovider.proto.elper.of) (obj)))), ..instance, com.google.common.util.concurrent.CE);
        }
        obj = Boolean.valueOf(false);
        if (obj == null)
        {
            return com.google.common.util.concurrent.Future.NULL;
        } else
        {
            return new com.google.common.util.concurrent.Future(obj);
        }
    }

    Context(TriggeringEventProcessor triggeringeventprocessor, ListenableFuture listenablefuture, ListenableFuture listenablefuture1, Context context)
    {
        arg$1 = triggeringeventprocessor;
        arg$2 = listenablefuture;
        arg$3 = listenablefuture1;
        arg$4 = context;
    }
}
