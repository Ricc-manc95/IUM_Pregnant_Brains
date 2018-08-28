// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.util.Log;
import java.util.Locale;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            NoopPrimesApi, PrimesApi, ApiProvider, TimerEvent

public final class Primes
{

    public static final Primes DEFAULT_PRIMES;
    public static volatile Primes primes;
    public static volatile boolean warningNotYetLogged = true;
    public final PrimesApi primesApi;

    private Primes(PrimesApi primesapi)
    {
        if (primesapi == null)
        {
            throw new NullPointerException();
        } else
        {
            primesApi = (PrimesApi)primesapi;
            return;
        }
    }

    public static Primes initialize(ApiProvider apiprovider)
    {
        boolean flag = false;
        com/google/android/libraries/performance/primes/Primes;
        JVM INSTR monitorenter ;
        if (primes != DEFAULT_PRIMES)
        {
            flag = true;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        apiprovider = "Primes.initialize() is called more than once. This call will be ignored.";
        Object aobj[] = new Object[0];
        if (!Log.isLoggable("Primes", 3)) goto _L4; else goto _L3
_L3:
        if (aobj.length != 0) goto _L6; else goto _L5
_L5:
        Log.println(3, "Primes", apiprovider);
_L4:
        apiprovider = primes;
_L7:
        com/google/android/libraries/performance/primes/Primes;
        JVM INSTR monitorexit ;
        return apiprovider;
_L6:
        apiprovider = String.format(Locale.US, "Primes.initialize() is called more than once. This call will be ignored.", aobj);
          goto _L5
_L2:
        apiprovider = new Primes(apiprovider.getPrimesApi());
        primes = apiprovider;
          goto _L7
        apiprovider;
        throw apiprovider;
        apiprovider;
        com/google/android/libraries/performance/primes/Primes;
        JVM INSTR monitorexit ;
        throw apiprovider;
          goto _L5
    }

    public final void stopTimer(TimerEvent timerevent, String s, MetricExtension metricextension)
    {
        this;
        JVM INSTR monitorenter ;
        primesApi.stopTimer$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK7D66KOBMC4NMOOBECSNL6T3ID5N6EEQQ9HM6UPRJ5TO74RRKDSNNEQBICLM6ASRJ5TO6ASJ6DTP6QOBECDIIURBFC9KMOP9FDPGMSRPF9LIN8SJ9CD2NGT35DPPMIRRE7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK4HA6IRB5E99N8OBKELPJMAAM0(timerevent, s, false, metricextension, android.support.v4.content.ModernAsyncTask.Status.UNKNOWN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0);
        this;
        JVM INSTR monitorexit ;
        return;
        timerevent;
        throw timerevent;
    }

    static 
    {
        DEFAULT_PRIMES = new Primes(new NoopPrimesApi());
        primes = DEFAULT_PRIMES;
    }
}
