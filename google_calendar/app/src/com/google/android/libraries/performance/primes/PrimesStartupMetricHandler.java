// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import android.os.Looper;
import com.google.android.libraries.performance.primes.sampling.PrimesSampling;
import com.google.android.libraries.performance.primes.tracing.SpanEvent;
import com.google.android.libraries.performance.primes.tracing.SpanProtoGenerator;
import com.google.android.libraries.performance.primes.tracing.TraceData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            ShutdownListener, AppLifecycleMonitor, PrimesStartupTracer, PrimesTraceConfigurations, 
//            PrimesStartupMeasure, PrimesLog, Supplier, TimerMetricService, 
//            TimerEvent, AbstractMetricService, MetricRecorder, NoPiiString, 
//            PrimesToken, TraceMetricService

final class PrimesStartupMetricHandler
    implements AppLifecycleListener.OnAppToBackground, ShutdownListener
{

    private final AppLifecycleMonitor appLifecycleMonitor;
    private final PrimesStartupTracer startupTracer;
    private final Supplier timerServiceSupplier;
    private final Supplier traceServiceSupplier;

    PrimesStartupMetricHandler(AppLifecycleMonitor applifecyclemonitor, Supplier supplier, Supplier supplier1, boolean flag, PrimesTraceConfigurations primestraceconfigurations)
    {
        appLifecycleMonitor = applifecyclemonitor;
        appLifecycleMonitor.register(this);
        timerServiceSupplier = supplier;
        traceServiceSupplier = supplier1;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_237;
        }
        startupTracer = new PrimesStartupTracer(primestraceconfigurations.minSpanDurationMs);
        supplier = PrimesStartupMeasure.instance;
        supplier1 = startupTracer;
        applifecyclemonitor = ((AppLifecycleMonitor) (((PrimesStartupMeasure) (supplier)).onActivityInitListenerLock));
        applifecyclemonitor;
        JVM INSTR monitorenter ;
        long l = ((PrimesStartupMeasure) (supplier)).firstOnActivityInitAt;
        if (l <= 0L) goto _L2; else goto _L1
_L1:
        supplier1.onActivityInit();
_L5:
        applifecyclemonitor;
        JVM INSTR monitorexit ;
        supplier = PrimesStartupMeasure.instance;
        supplier1 = startupTracer;
        applifecyclemonitor = ((AppLifecycleMonitor) (((PrimesStartupMeasure) (supplier)).onDrawListenerLock));
        applifecyclemonitor;
        JVM INSTR monitorenter ;
        l = ((PrimesStartupMeasure) (supplier)).firstDrawnAt;
        if (l <= 0L) goto _L4; else goto _L3
_L3:
        supplier1.onDraw();
_L6:
        applifecyclemonitor;
        JVM INSTR monitorexit ;
        return;
        supplier;
        PrimesLog.log(3, "PrimesStartupMeasure", supplier, "Error running onActivityInit listener", new Object[0]);
          goto _L5
        supplier;
        applifecyclemonitor;
        JVM INSTR monitorexit ;
        throw supplier;
_L2:
        if (((PrimesStartupMeasure) (supplier)).onActivityInitListeners == PrimesStartupMeasure.ON_ACTIVITY_INIT_EMPTY_LIST)
        {
            supplier.onActivityInitListeners = new ArrayList();
        }
        ((PrimesStartupMeasure) (supplier)).onActivityInitListeners.add(supplier1);
          goto _L5
        supplier;
        PrimesLog.log(3, "PrimesStartupMeasure", supplier, "Error running onDraw listener", new Object[0]);
          goto _L6
        supplier;
        applifecyclemonitor;
        JVM INSTR monitorexit ;
        throw supplier;
_L4:
        if (((PrimesStartupMeasure) (supplier)).onDrawListeners == PrimesStartupMeasure.ON_DRAW_EMPTY_LIST)
        {
            supplier.onDrawListeners = new ArrayList();
        }
        ((PrimesStartupMeasure) (supplier)).onDrawListeners.add(supplier1);
          goto _L6
        startupTracer = null;
        return;
          goto _L5
    }

    private final void recordTimer(PrimesStartupMeasure primesstartupmeasure, long l, long l1, String s)
    {
        if (l1 >= l)
        {
            TimerMetricService timermetricservice = (TimerMetricService)timerServiceSupplier.get();
            TimerEvent timerevent = new TimerEvent(l, l1);
            primesstartupmeasure = primesstartupmeasure.startupType;
            boolean flag;
            if (primesstartupmeasure == null)
            {
                primesstartupmeasure = null;
            } else
            {
                primesstartupmeasure = primesstartupmeasure.toString();
            }
            if (!((AbstractMetricService) (timermetricservice)).metricRecorder.instrumentationSampling.isSampleRateExceeded())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                primesstartupmeasure = TimerMetricService.getMetric(timerevent, primesstartupmeasure);
                ((ScheduledExecutorService)((AbstractMetricService) (timermetricservice)).executorServiceSupplier.get()).submit(new TimerMetricService._cls1(timermetricservice, s, true, primesstartupmeasure, null));
            }
        }
    }

    public final void onAppToBackground(Activity activity)
    {
        PrimesStartupMeasure primesstartupmeasure;
        long l;
        appLifecycleMonitor.unregister(this);
        primesstartupmeasure = PrimesStartupMeasure.instance;
        boolean flag;
        if (!primesstartupmeasure.firstActivityPausedBeforeDraw && primesstartupmeasure.firstDrawnAt > 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        if (timerServiceSupplier.get() == null) goto _L4; else goto _L3
_L3:
        if (primesstartupmeasure.startedByUser)
        {
            l = primesstartupmeasure.appClassLoadedAt;
        } else
        {
            l = primesstartupmeasure.firstOnActivityInitAt;
        }
        if (l > 0L) goto _L5; else goto _L4
_L4:
        if (traceServiceSupplier.get() == null) goto _L2; else goto _L6
_L6:
        if (primesstartupmeasure.startedByUser)
        {
            l = primesstartupmeasure.appClassLoadedAt;
        } else
        {
            l = primesstartupmeasure.firstOnActivityInitAt;
        }
        if (l > 0L) goto _L7; else goto _L2
_L2:
        return;
_L5:
        long l1 = primesstartupmeasure.firstDrawnAt;
        boolean flag1 = primesstartupmeasure.startedByUser;
        if (primesstartupmeasure.startedByUser)
        {
            l = primesstartupmeasure.appClassLoadedAt;
        } else
        {
            l = primesstartupmeasure.firstOnActivityInitAt;
        }
        if (flag1)
        {
            activity = "Cold startup";
        } else
        {
            activity = "Warm startup";
        }
        recordTimer(primesstartupmeasure, l, l1, activity);
        l1 = primesstartupmeasure.firstAppInteractiveAt;
        if (primesstartupmeasure.firstAppInteractiveAt < primesstartupmeasure.firstDrawnAt)
        {
            if (flag1)
            {
                activity = "Cold startup interactive before onDraw";
            } else
            {
                activity = "Warm startup interactive before onDraw";
            }
        } else
        if (flag1)
        {
            activity = "Cold startup interactive";
        } else
        {
            activity = "Warm startup interactive";
        }
        recordTimer(primesstartupmeasure, l, l1, activity);
        l = primesstartupmeasure.firstOnActivityStartedAt;
        l1 = primesstartupmeasure.firstDrawnAt;
        if (!flag1 && l != 0L)
        {
            recordTimer(primesstartupmeasure, l, l1, "Warm startup activity onStart");
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (primesstartupmeasure.startedByUser)
        {
            l = primesstartupmeasure.appClassLoadedAt;
        } else
        {
            l = primesstartupmeasure.firstOnActivityInitAt;
        }
        if (primesstartupmeasure.firstDrawnAt < l) goto _L2; else goto _L8
_L8:
        long l3 = Looper.getMainLooper().getThread().getId();
        ArrayList arraylist = new ArrayList();
        boolean flag2 = primesstartupmeasure.startedByUser;
        PrimesStartupMeasure.StartupActivityInfo astartupactivityinfo[] = primesstartupmeasure.getStartupActivityInfos();
        int i;
        if (flag2)
        {
            arraylist.add(SpanEvent.newSpan$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFA1P6IRB5EDA6UQR5DOTKOQJ1EPGIUR31DPJIUKRKE9KMSPPR9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357D54KIICCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUT3IC5HMIRJ75T9N0OBE8LR6ARJK4H9N0OBEAHSN0P9R55666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFEHP62OR9DPJIUKRGC5N4ATJ5DPQ3M___0(PrimesToken.PRIMES_TOKEN, "App create", android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0, primesstartupmeasure.appClassLoadedAt, primesstartupmeasure.appOnCreateAt, l3, android.support.v4.content.ModernAsyncTask.Status.CHILD_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0));
            Object obj1 = PrimesToken.PRIMES_TOKEN;
            activity = String.valueOf(astartupactivityinfo[0].activityName);
            String s = String.valueOf(": onCreate");
            if (s.length() != 0)
            {
                activity = activity.concat(s);
            } else
            {
                activity = new String(activity);
            }
            obj1 = SpanEvent.newSpan$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFA1P6IRB5EDA6UQR5DOTKOQJ1EPGIUR31DPJIUKRKE9KMSPPR9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357D54KIICCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUT3IC5HMIRJ75T9N0OBE8LR6ARJK4H9N0OBEAHSN0P9R55666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFEHP62OR9DPJIUKRGC5N4ATJ5DPQ3M___0(((PrimesToken) (obj1)), activity, android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0, primesstartupmeasure.appOnCreateAt, astartupactivityinfo[0].onActivityCreatedAt, l3, android.support.v4.content.ModernAsyncTask.Status.CHILD_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0);
            arraylist.add(obj1);
            if (primesstartupmeasure.firstOnActivityInitAt > 0L)
            {
                PrimesToken primestoken3 = PrimesToken.PRIMES_TOKEN;
                PrimesToken primestoken4 = PrimesToken.PRIMES_TOKEN;
                activity = String.valueOf(astartupactivityinfo[0].activityName);
                String s4 = String.valueOf(": init");
                if (s4.length() != 0)
                {
                    activity = activity.concat(s4);
                } else
                {
                    activity = new String(activity);
                }
                activity = SpanEvent.newSpan$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFA1P6IRB5EDA6UQR5DOTKOQJ1EPGIUR31DPJIUKRKE9KMSPPR9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357D54KIICCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUT3IC5HMIRJ75T9N0OBE8LR6ARJK4H9N0OBEAHSN0P9R55666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFEHP62OR9DPJIUKRGC5N4ATJ5DPQ3M___0(primestoken4, activity, android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0, primesstartupmeasure.appOnCreateAt, primesstartupmeasure.firstOnActivityInitAt, l3, android.support.v4.content.ModernAsyncTask.Status.CHILD_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0);
                if (primestoken3 == null)
                {
                    throw new NullPointerException();
                }
                ((SpanEvent) (obj1)).addChildSpan(activity);
            }
        } else
        {
            PrimesToken primestoken1 = PrimesToken.PRIMES_TOKEN;
            activity = String.valueOf(astartupactivityinfo[0].activityName);
            String s2 = String.valueOf(": onCreate");
            if (s2.length() != 0)
            {
                activity = activity.concat(s2);
            } else
            {
                activity = new String(activity);
            }
            arraylist.add(SpanEvent.newSpan$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFA1P6IRB5EDA6UQR5DOTKOQJ1EPGIUR31DPJIUKRKE9KMSPPR9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357D54KIICCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUT3IC5HMIRJ75T9N0OBE8LR6ARJK4H9N0OBEAHSN0P9R55666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFEHP62OR9DPJIUKRGC5N4ATJ5DPQ3M___0(primestoken1, activity, android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0, primesstartupmeasure.firstOnActivityInitAt, astartupactivityinfo[0].onActivityCreatedAt, l3, android.support.v4.content.ModernAsyncTask.Status.CHILD_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0));
        }
        l = astartupactivityinfo[0].onActivityCreatedAt;
        i = 1;
        while (i < astartupactivityinfo.length) 
        {
            long l2 = astartupactivityinfo[i].onActivityCreatedAt;
            PrimesToken primestoken = PrimesToken.PRIMES_TOKEN;
            activity = String.valueOf(astartupactivityinfo[i].activityName);
            String s1 = String.valueOf(": onCreate");
            if (s1.length() != 0)
            {
                activity = activity.concat(s1);
            } else
            {
                activity = new String(activity);
            }
            arraylist.add(SpanEvent.newSpan$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFA1P6IRB5EDA6UQR5DOTKOQJ1EPGIUR31DPJIUKRKE9KMSPPR9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357D54KIICCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUT3IC5HMIRJ75T9N0OBE8LR6ARJK4H9N0OBEAHSN0P9R55666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFEHP62OR9DPJIUKRGC5N4ATJ5DPQ3M___0(primestoken, activity, android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0, l, l2, l3, android.support.v4.content.ModernAsyncTask.Status.CHILD_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0));
            i++;
            l = l2;
        }
        l = astartupactivityinfo[astartupactivityinfo.length - 1].onActivityCreatedAt;
        Object obj = astartupactivityinfo[astartupactivityinfo.length - 1].activityName;
        PrimesToken primestoken2 = PrimesToken.PRIMES_TOKEN;
        activity = String.valueOf(obj);
        String s3 = String.valueOf(": onStart");
        if (s3.length() != 0)
        {
            activity = activity.concat(s3);
        } else
        {
            activity = new String(activity);
        }
        arraylist.add(SpanEvent.newSpan$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFA1P6IRB5EDA6UQR5DOTKOQJ1EPGIUR31DPJIUKRKE9KMSPPR9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357D54KIICCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUT3IC5HMIRJ75T9N0OBE8LR6ARJK4H9N0OBEAHSN0P9R55666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFEHP62OR9DPJIUKRGC5N4ATJ5DPQ3M___0(primestoken2, activity, android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0, l, primesstartupmeasure.firstOnActivityStartedAt, l3, android.support.v4.content.ModernAsyncTask.Status.CHILD_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0));
        primestoken2 = PrimesToken.PRIMES_TOKEN;
        activity = String.valueOf(obj);
        s3 = String.valueOf(": onResume");
        if (s3.length() != 0)
        {
            activity = activity.concat(s3);
        } else
        {
            activity = new String(activity);
        }
        arraylist.add(SpanEvent.newSpan$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFA1P6IRB5EDA6UQR5DOTKOQJ1EPGIUR31DPJIUKRKE9KMSPPR9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357D54KIICCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUT3IC5HMIRJ75T9N0OBE8LR6ARJK4H9N0OBEAHSN0P9R55666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFEHP62OR9DPJIUKRGC5N4ATJ5DPQ3M___0(primestoken2, activity, android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0, primesstartupmeasure.firstOnActivityStartedAt, primesstartupmeasure.firstOnActivityResumedAt, l3, android.support.v4.content.ModernAsyncTask.Status.CHILD_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0));
        primestoken2 = PrimesToken.PRIMES_TOKEN;
        activity = String.valueOf(obj);
        obj = String.valueOf(": onDraw");
        if (((String) (obj)).length() != 0)
        {
            activity = activity.concat(((String) (obj)));
        } else
        {
            activity = new String(activity);
        }
        arraylist.add(SpanEvent.newSpan$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFA1P6IRB5EDA6UQR5DOTKOQJ1EPGIUR31DPJIUKRKE9KMSPPR9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357D54KIICCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUT3IC5HMIRJ75T9N0OBE8LR6ARJK4H9N0OBEAHSN0P9R55666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFEHP62OR9DPJIUKRGC5N4ATJ5DPQ3M___0(primestoken2, activity, android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0, primesstartupmeasure.firstOnActivityResumedAt, primesstartupmeasure.firstDrawnAt, l3, android.support.v4.content.ModernAsyncTask.Status.CHILD_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0));
        obj = PrimesToken.PRIMES_TOKEN;
        if (primesstartupmeasure.startedByUser)
        {
            activity = "Cold startup";
        } else
        {
            activity = "Warm startup";
        }
        obj = SpanEvent.newSpan$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFA1P6IRB5EDA6UQR5DOTKOQJ1EPGIUR31DPJIUKRKE9KMSPPR9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357D54KIICCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUT3IC5HMIRJ75T9N0OBE8LR6ARJK4H9N0OBEAHSN0P9R55666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFEHP62OR9DPJIUKRGC5N4ATJ5DPQ3M___0(((PrimesToken) (obj)), activity, android.support.v4.content.ModernAsyncTask.Status.CONSTANT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI4ATJ5DPQ4SOBDCLA7IS357C______0, ((SpanEvent)arraylist.get(0)).startMs, -1L, l3, android.support.v4.content.ModernAsyncTask.Status.ROOT_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0);
        if (PrimesToken.PRIMES_TOKEN == null)
        {
            throw new NullPointerException();
        }
        ((SpanEvent) (obj)).addChildSpans(arraylist);
        activity = null;
        if (startupTracer != null)
        {
            activity = startupTracer.startupTraceData;
        }
        if (activity != null)
        {
            if (PrimesToken.PRIMES_TOKEN == null)
            {
                throw new NullPointerException();
            }
            activity.linkTraceAndGetRootSpan();
            ((TraceData) (activity)).rootSpan.spanType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0 = android.support.v4.content.ModernAsyncTask.Status.CHILD_SPAN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BRKE9GM6QBECSNL6S31DP2NCPBEEGI56S31DPA7IS357C______0;
            ((TraceData) (activity)).rootSpan.endMs = ((TraceData) (activity)).rootSpan.startMs;
            activity = ((TraceData) (activity)).rootSpan;
            if (PrimesToken.PRIMES_TOKEN == null)
            {
                throw new NullPointerException();
            }
            ((SpanEvent) (obj)).addChildSpan(activity);
        }
        if (PrimesToken.PRIMES_TOKEN == null)
        {
            throw new NullPointerException();
        }
        activity = new SpanProtoGenerator(((SpanEvent) (obj)));
        if (PrimesToken.PRIMES_TOKEN == null)
        {
            throw new NullPointerException();
        }
        logs.proto.wireless.performance.mobile.nano.Span aspan[] = activity.generate();
        activity = primesstartupmeasure.startupType;
        if (activity == null)
        {
            activity = null;
        } else
        {
            activity = activity.toString();
        }
        ((TraceMetricService)traceServiceSupplier.get()).record(aspan, activity);
        return;
        if (true) goto _L4; else goto _L9
_L9:
    }

    public final void onShutdown()
    {
        appLifecycleMonitor.unregister(this);
        if (startupTracer != null)
        {
            startupTracer.shutdown();
        }
    }
}
