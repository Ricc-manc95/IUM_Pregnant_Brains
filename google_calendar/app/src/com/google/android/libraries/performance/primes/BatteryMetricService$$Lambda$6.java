// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.os.Looper;
import android.util.Log;
import com.google.android.libraries.stitch.util.ThreadUtil;
import java.util.Locale;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, BatteryMetricService

final class arg._cls4
    implements Runnable
{

    private final BatteryMetricService arg$1;
    private final int arg$2;
    private final String arg$3;
    private final boolean arg$4;

    public final void run()
    {
        Object obj;
        Object obj1;
        int i;
        boolean flag1;
        obj = arg$1;
        i = arg$2;
        obj1 = arg$3;
        flag1 = arg$4;
        if (ThreadUtil.sMainThread == null)
        {
            ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
        }
        boolean flag;
        if (Thread.currentThread() == ThreadUtil.sMainThread)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            throw new RuntimeException("Must be called on a background thread");
        }
        if (((AbstractMetricService) (obj)).shutdown)
        {
            break MISSING_BLOCK_LABEL_176;
        }
        com.google.android.libraries.performance.primes.battery.StatsStorage statsstorage = ((BatteryMetricService) (obj)).storage;
        statsstorage;
        JVM INSTR monitorenter ;
        com.google.android.libraries.performance.primes.battery. ;
        obj1 = ((BatteryMetricService) (obj)).captureBattery(i, ((String) (obj1)), flag1).Record();
         = ((BatteryMetricService) (obj)).fromStorage();
        if (!((BatteryMetricService) (obj)).toStorage(((com.google.android.libraries.performance.primes.battery.Record) (obj1)))) goto _L2; else goto _L1
_L1:
        ((BatteryMetricService) (obj)).log(, ((com.google.android.libraries.performance.primes.battery.Record) (obj1)));
_L4:
        return;
_L2:
        ((AbstractMetricService) (obj)).shutdownService();
        obj = "Failure storing persistent snapshot and helper data";
        Object aobj1[] = new Object[0];
        if (!Log.isLoggable("BatteryMetricService", 5)) goto _L4; else goto _L3
_L3:
        if (aobj1.length != 0)
        {
            break MISSING_BLOCK_LABEL_163;
        }
_L5:
        Log.println(5, "BatteryMetricService", ((String) (obj)));
          goto _L4
        obj;
        statsstorage;
        JVM INSTR monitorexit ;
        throw obj;
        obj = String.format(Locale.US, "Failure storing persistent snapshot and helper data", aobj1);
          goto _L5
        String s = "shutdown - skipping capture";
        Object aobj[] = new Object[0];
        if (Log.isLoggable("BatteryMetricService", 3))
        {
            if (aobj.length != 0)
            {
                s = String.format(Locale.US, "shutdown - skipping capture", aobj);
            }
            Log.println(3, "BatteryMetricService", s);
            return;
        } else
        {
            return;
        }
    }

    (BatteryMetricService batterymetricservice, int i, String s, boolean flag)
    {
        arg$1 = batterymetricservice;
        arg$2 = i;
        arg$3 = s;
        arg$4 = flag;
    }
}
