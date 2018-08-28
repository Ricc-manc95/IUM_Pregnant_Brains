// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.streamz;

import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogEventBuilder;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;
import com.google.android.libraries.streamz.CellFieldTuple;
import com.google.android.libraries.streamz.Counter2;
import com.google.android.libraries.streamz.GcoreClearcutStreamzLogger;
import com.google.android.libraries.streamz.GenericMetric;
import com.google.android.libraries.streamz.MetricFactory;
import com.google.android.libraries.streamz.StreamzMessageProducer;
import javax.inject.Provider;

public final class StreamzIncrements
{

    public static int incrementCounts = 0;
    public final Provider incrementsToFlush;
    public final MetricFactory metricFactory;
    public final GcoreClearcutStreamzLogger streamzLogger;

    public StreamzIncrements(Provider provider, GcoreClearcutStreamzLogger gcoreclearcutstreamzlogger, MetricFactory metricfactory)
    {
        incrementsToFlush = provider;
        streamzLogger = gcoreclearcutstreamzlogger;
        metricFactory = metricfactory;
    }

    public final void increment(Counter2 counter2, Object obj, Object obj1)
    {
        counter2.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
            obj, obj1
        }));
        incrementCounts++;
        if ((long)incrementCounts >= ((Long)incrementsToFlush.get()).longValue())
        {
            counter2 = streamzLogger;
            obj = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(metricFactory);
            boolean flag;
            if (((StreamzMessageProducer) (obj)).incrementRequest.batch_.size() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                ((GcoreClearcutStreamzLogger) (counter2)).gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj))).setLogSourceName(((GcoreClearcutStreamzLogger) (counter2)).logSourceName).logAsync();
            }
            incrementCounts = 0;
        }
    }

}
