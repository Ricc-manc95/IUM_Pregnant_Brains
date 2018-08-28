// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            BatteryMetricService, PrimesLog

final class arg._cls2
    implements Runnable
{

    private final BatteryMetricService arg$1;
    private final List arg$2;

    public final void run()
    {
        com.google.android.libraries.performance.primes.battery. ;
        BatteryMetricService batterymetricservice;
        Iterator iterator;
        batterymetricservice = arg$1;
        List list = arg$2;
         = batterymetricservice.fromStorage();
        iterator = list.iterator();
_L2:
        Object obj;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (Future)iterator.next();
        obj = ((com.google.android.libraries.performance.primes.battery.)((Future) (obj)).get()).Record();
        if ( == null)
        {
            break MISSING_BLOCK_LABEL_70;
        }
        batterymetricservice.log(, ((com.google.android.libraries.performance.primes.battery.Record) (obj)));
         = ((com.google.android.libraries.performance.primes.battery.Record) (obj));
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        obj = ;
_L3:
        PrimesLog.log(6, "BatteryMetricService", exception, "unpexpected failure", new Object[0]);
         = ((com.google.android.libraries.performance.primes.battery.Record) (obj));
        if (true) goto _L2; else goto _L1
_L1:
        batterymetricservice.toStorage();
        return;
        exception;
          goto _L3
    }

    (BatteryMetricService batterymetricservice, List list)
    {
        arg$1 = batterymetricservice;
        arg$2 = list;
    }
}
