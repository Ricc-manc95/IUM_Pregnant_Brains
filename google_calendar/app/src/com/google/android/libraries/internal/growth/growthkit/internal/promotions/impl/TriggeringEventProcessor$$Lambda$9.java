// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.ClearcutEventsStore;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            TriggeringEventProcessor

final class arg._cls2
    implements AsyncFunction
{

    private final TriggeringEventProcessor arg$1;
    private final Context arg$2;

    public final ListenableFuture apply(Object obj)
    {
        TriggeringEventProcessor triggeringeventprocessor = arg$1;
        Context context = arg$2;
        obj = (ata)obj;
        return ((ClearcutEventsStore)triggeringeventprocessor.clearcutCountersStore.forAccount(context.accountName())).getEventsCounts(((ata) (obj)).clearcutEvents());
    }

    Context(TriggeringEventProcessor triggeringeventprocessor, Context context)
    {
        arg$1 = triggeringeventprocessor;
        arg$2 = context;
    }
}