// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            TriggeringEventProcessor

final class arg._cls2
    implements AsyncFunction
{

    private final TriggeringEventProcessor arg$1;
    private final ontext arg$2;

    public final ListenableFuture apply(Object obj)
    {
        TriggeringEventProcessor triggeringeventprocessor = arg$1;
        ontext ontext = arg$2;
        Object obj1 = (ta)obj;
        obj = new ArrayList();
        GrowthKitCallbacks growthkitcallbacks = (GrowthKitCallbacks)triggeringeventprocessor.callbackManager.get();
        if (growthkitcallbacks != null)
        {
            String s;
            for (obj1 = (UnmodifiableIterator)((ta) (obj1)).appStateIds().iterator(); ((Iterator) (obj1)).hasNext(); ((List) (obj)).add(growthkitcallbacks.onAppStateNeeded$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIIJ33DTMIUPRFDTJMOP9FCDNMQRBFDONNAT39DGNM6RRECDQN4SJ5DPQ2UJ39EDQ6ARJ1C9M6AHJLEHQN4P9R0(s)))
            {
                s = (String)((Iterator) (obj1)).next();
                ontext.accountName();
            }

        } else
        {
            obj = TriggeringEventProcessor.logger;
            obj = new HashMap();
            if (obj == null)
            {
                return com.google.common.util.concurrent.uture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.uture(obj);
            }
        }
        return AbstractTransformFuture.create(new com.google.common.util.concurrent.uture(ImmutableList.copyOf(((Iterable) (obj))), true), .instance, triggeringeventprocessor.executor);
    }

    ontext(TriggeringEventProcessor triggeringeventprocessor, ontext ontext)
    {
        arg$1 = triggeringeventprocessor;
        arg$2 = ontext;
    }
}
