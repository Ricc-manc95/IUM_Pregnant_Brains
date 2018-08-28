// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            TriggeringEventProcessor

final class arg._cls3
    implements Callable
{

    private final TriggeringEventProcessor arg$1;
    private final PromoContext arg$2;
    private final com.google.identity.boq.growth.promoprovider.proto.otion arg$3;

    public final Object call()
    {
        return arg$1.lambda$renderPromotion$7$TriggeringEventProcessor(arg$2, arg$3);
    }

    y(TriggeringEventProcessor triggeringeventprocessor, PromoContext promocontext, com.google.identity.boq.growth.promoprovider.proto.otion otion)
    {
        arg$1 = triggeringeventprocessor;
        arg$2 = promocontext;
        arg$3 = otion;
    }
}
