// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl;

import io.grpc.ManagedChannel;
import java.io.Closeable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl:
//            GrowthApiClientImpl

final class arg._cls2
    implements Closeable
{

    private final GrowthApiClientImpl arg$1;
    private final ManagedChannel arg$2;

    public final void close()
    {
        GrowthApiClientImpl growthapiclientimpl = arg$1;
        arg$2.shutdown();
    }

    (GrowthApiClientImpl growthapiclientimpl, ManagedChannel managedchannel)
    {
        arg$1 = growthapiclientimpl;
        arg$2 = managedchannel;
    }
}
