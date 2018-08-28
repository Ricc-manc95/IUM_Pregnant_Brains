// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            PromotionSyncImpl

final class arg._cls1
    implements Callable
{

    private final com.google.identity.boq.growth.promoprovider.proto.se arg$1;

    public final Object call()
    {
        return PromotionSyncImpl.lambda$sync$8$PromotionSyncImpl(arg$1);
    }

    (com.google.identity.boq.growth.promoprovider.proto.se se)
    {
        arg$1 = se;
    }
}
