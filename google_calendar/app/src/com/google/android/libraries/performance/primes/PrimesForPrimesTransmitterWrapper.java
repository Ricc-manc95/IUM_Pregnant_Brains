// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.util.Log;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import logs.proto.wireless.performance.mobile.nano.PrimesForPrimes;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            Supplier

final class PrimesForPrimesTransmitterWrapper
    implements PrimesForPrimesLogger.Queue, MetricTransmitter
{

    private final Supplier delegateSupplier;
    private final BlockingQueue primesForPrimesQueue;

    PrimesForPrimesTransmitterWrapper(Supplier supplier)
    {
        this(supplier, ((BlockingQueue) (new ArrayBlockingQueue(5))));
    }

    private PrimesForPrimesTransmitterWrapper(Supplier supplier, BlockingQueue blockingqueue)
    {
        delegateSupplier = supplier;
        primesForPrimesQueue = blockingqueue;
    }

    public final void enqueueMessage(Supplier supplier)
    {
        if (!primesForPrimesQueue.offer(supplier))
        {
            supplier = "Queue overflow";
            Object aobj[] = new Object[0];
            if (Log.isLoggable("PrimesForPrimes", 5))
            {
                if (aobj.length != 0)
                {
                    supplier = String.format(Locale.US, "Queue overflow", aobj);
                }
                Log.println(5, "PrimesForPrimes", supplier);
            }
        }
    }

    public final void send(SystemHealthMetric systemhealthmetric)
    {
        if (systemhealthmetric.primesForPrimes == null)
        {
            Supplier supplier = (Supplier)primesForPrimesQueue.poll();
            if (supplier != null)
            {
                systemhealthmetric.primesForPrimes = (PrimesForPrimes)supplier.get();
            }
        }
        MetricTransmitter metrictransmitter = (MetricTransmitter)delegateSupplier.get();
        if (metrictransmitter == null)
        {
            throw new NullPointerException();
        } else
        {
            ((MetricTransmitter)metrictransmitter).send(systemhealthmetric);
            return;
        }
    }
}
