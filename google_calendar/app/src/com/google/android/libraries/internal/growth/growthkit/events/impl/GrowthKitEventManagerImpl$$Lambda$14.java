// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.events.impl;

import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogEventBuilder;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.CellFieldTuple;
import com.google.android.libraries.streamz.GcoreClearcutStreamzLogger;
import com.google.android.libraries.streamz.GenericMetric;
import com.google.android.libraries.streamz.StreamzMessageProducer;
import com.google.common.base.Receiver;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.events.impl:
//            GrowthKitEventManagerImpl

final class arg._cls1
    implements Receiver
{

    private final GrowthKitEventManagerImpl arg$1;

    public final void accept(Object obj)
    {
        GrowthKitEventManagerImpl growthkiteventmanagerimpl = arg$1;
        obj = growthkiteventmanagerimpl.streamzIncrements;
        growthkiteventmanagerimpl.loggingCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
            growthkiteventmanagerimpl.appPackageName, "Clearcut", "OK"
        }));
        StreamzIncrements.incrementCounts++;
        if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (obj)).incrementsToFlush.get()).longValue())
        {
            GcoreClearcutStreamzLogger gcoreclearcutstreamzlogger = ((StreamzIncrements) (obj)).streamzLogger;
            obj = new com.google.android.libraries.streamz.geProducer(((StreamzIncrements) (obj)).metricFactory);
            boolean flag;
            if (((StreamzMessageProducer) (obj)).incrementRequest.mentRequest.batch_() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                gcoreclearcutstreamzlogger.gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj))).setLogSourceName(gcoreclearcutstreamzlogger.logSourceName).logAsync();
            }
            StreamzIncrements.incrementCounts = 0;
        }
    }

    (GrowthKitEventManagerImpl growthkiteventmanagerimpl)
    {
        arg$1 = growthkiteventmanagerimpl;
    }
}
