// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.sync.PromotionSync;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            TriggeringEventProcessor

final class arg._cls1
    implements AsyncFunction
{

    private final TriggeringEventProcessor arg$1;

    public final ListenableFuture apply(Object obj)
    {
        TriggeringEventProcessor triggeringeventprocessor = arg$1;
        if (((Boolean)obj).booleanValue() && ((Boolean)triggeringeventprocessor.syncAfterPromoShownProvider.get()).booleanValue())
        {
            return triggeringeventprocessor.promotionSync.syncAllAccounts();
        }
        if (true)
        {
            return com.google.common.util.concurrent.Future.NULL;
        } else
        {
            return new com.google.common.util.concurrent.Future(null);
        }
    }

    (TriggeringEventProcessor triggeringeventprocessor)
    {
        arg$1 = triggeringeventprocessor;
    }
}
