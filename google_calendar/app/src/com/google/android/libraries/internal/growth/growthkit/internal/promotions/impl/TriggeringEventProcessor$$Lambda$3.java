// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import java.util.Map;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            TriggeringEventProcessor

final class arg._cls1
    implements Callable
{

    private final Map arg$1;

    public final Object call()
    {
        return TriggeringEventProcessor.lambda$getActionIntentMapFuture$4$TriggeringEventProcessor(arg$1);
    }

    (Map map)
    {
        arg$1 = map;
    }
}
