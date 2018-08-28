// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.hprof.HprofSerializer;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.protobuf.nano.MessageNano;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;
import logs.proto.wireless.performance.mobile.nano.HeapDumpContext;
import logs.proto.wireless.performance.mobile.nano.PrimesHeapDump;
import logs.proto.wireless.performance.mobile.nano.PrimesHeapDumpEvent;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            MetricStamper, PrimesLog

final class HeapDumpProcessor
{

    private final MetricStamper metricStamper;
    public final HprofSerializer serializer;

    HeapDumpProcessor(HprofSerializer hprofserializer, MetricStamper metricstamper)
    {
        serializer = hprofserializer;
        metricStamper = metricstamper;
    }

    final PrimesHeapDumpEvent executeSerializer(Callable callable, HeapDumpContext heapdumpcontext, File file)
    {
        PrimesHeapDumpEvent primesheapdumpevent;
        primesheapdumpevent = new PrimesHeapDumpEvent();
        primesheapdumpevent.error = 4;
        callable = (PrimesHeapDump)callable.call();
        callable.context = heapdumpcontext;
        heapdumpcontext = metricStamper;
        SystemHealthMetric systemhealthmetric = new SystemHealthMetric();
        systemhealthmetric.primesHeapDump = callable;
        heapdumpcontext.stamp(systemhealthmetric);
        int i = systemhealthmetric.computeSerializedSize();
        systemhealthmetric.cachedSize = i;
        heapdumpcontext = new byte[i];
        MessageNano.toByteArray(systemhealthmetric, heapdumpcontext, 0, heapdumpcontext.length);
        primesheapdumpevent.serializedSizeKb = Integer.valueOf(heapdumpcontext.length / 1024);
        if (primesheapdumpevent.serializedSizeKb.intValue() <= 10000)
        {
            break MISSING_BLOCK_LABEL_122;
        }
        primesheapdumpevent.error = 3;
        return primesheapdumpevent;
        Object obj = new FileOutputStream(file);
        callable = null;
        ((FileOutputStream) (obj)).write(heapdumpcontext);
        ((FileOutputStream) (obj)).close();
_L1:
        return primesheapdumpevent;
        heapdumpcontext;
        callable = heapdumpcontext;
        throw heapdumpcontext;
        heapdumpcontext;
        if (callable == null)
        {
            break MISSING_BLOCK_LABEL_228;
        }
        ((FileOutputStream) (obj)).close();
_L2:
        throw heapdumpcontext;
        callable;
        try
        {
            PrimesLog.log(3, "HeapDumpProcessor", callable, "Failed to write mini heap dump to file.", new Object[0]);
            if (file.exists())
            {
                file.delete();
            }
        }
        // Misplaced declaration of an exception variable
        catch (Callable callable)
        {
            primesheapdumpevent.error = 2;
        }
        // Misplaced declaration of an exception variable
        catch (Callable callable)
        {
            primesheapdumpevent.error = 0;
        }
          goto _L1
        obj;
        ThrowableExtension.STRATEGY.addSuppressed(callable, ((Throwable) (obj)));
          goto _L2
        ((FileOutputStream) (obj)).close();
          goto _L2
    }
}
