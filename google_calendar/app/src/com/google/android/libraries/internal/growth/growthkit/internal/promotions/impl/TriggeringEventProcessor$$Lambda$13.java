// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.common.base.Function;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            TriggeringEventProcessor

final class y
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return TriggeringEventProcessor.lambda$sortPromos$17$TriggeringEventProcessor((Map)obj);
    }


    private y()
    {
    }
}
