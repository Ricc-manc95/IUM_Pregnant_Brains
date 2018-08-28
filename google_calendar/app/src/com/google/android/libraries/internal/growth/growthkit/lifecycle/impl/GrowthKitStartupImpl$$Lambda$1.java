// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.common.base.Receiver;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl:
//            GrowthKitStartupImpl

final class arg._cls1
    implements Receiver
{

    private final GrowthKitStartupImpl arg$1;

    public final void accept(Object obj)
    {
        obj = arg$1;
        ((GrowthKitStartupImpl) (obj)).streamzIncrements.increment(((GrowthKitStartupImpl) (obj)).growthkitStartedCounter, ((GrowthKitStartupImpl) (obj)).packageName, "OK");
    }

    (GrowthKitStartupImpl growthkitstartupimpl)
    {
        arg$1 = growthkitstartupimpl;
    }
}
