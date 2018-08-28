// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl:
//            GrowthApiClientImpl

final class arg._cls1
    implements AsyncFunction
{

    private final com.google.identity.boq.growth.promoprovider.proto. arg$1;

    public final ListenableFuture apply(Object obj)
    {
        return GrowthApiClientImpl.lambda$getPromos$0$GrowthApiClientImpl(arg$1, (com.google.internal.identity.growth.v1.ServiceFutureStub)obj);
    }

    (com.google.identity.boq.growth.promoprovider.proto. )
    {
        arg$1 = ;
    }
}
