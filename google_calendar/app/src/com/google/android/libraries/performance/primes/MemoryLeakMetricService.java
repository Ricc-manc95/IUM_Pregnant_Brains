// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.libraries.performance.primes.leak.LeakInfo;
import com.google.android.libraries.performance.primes.leak.LeakListener;
import com.google.android.libraries.performance.primes.leak.LeakWatcher;
import com.google.android.libraries.performance.primes.sampling.PrimesSampling;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import logs.proto.wireless.performance.mobile.nano.MemoryLeakMetric;
import logs.proto.wireless.performance.mobile.nano.ObjectInfo;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, AppLifecycleMonitor, Supplier, MetricRecorder

final class MemoryLeakMetricService extends AbstractMetricService
    implements AppLifecycleListener.OnActivityDestroyed
{
    static final class LeakCounter
    {

        public int leaked;
        public int released;

        LeakCounter()
        {
        }
    }

    final class PrimesLeakListener
        implements LeakListener
    {

        private final Map stats = new HashMap();
        private final MemoryLeakMetricService this$0;

        public final void onBatchComplete(boolean flag)
        {
            ArrayList arraylist = new ArrayList();
            Iterator iterator = stats.entrySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                Object obj1 = (java.util.Map.Entry)iterator.next();
                String s = (String)((java.util.Map.Entry) (obj1)).getKey();
                obj1 = (LeakCounter)((java.util.Map.Entry) (obj1)).getValue();
                if (((LeakCounter) (obj1)).leaked > 0 || ((LeakCounter) (obj1)).released > 0)
                {
                    ObjectInfo objectinfo = new ObjectInfo();
                    objectinfo.className = s;
                    objectinfo.leakedCount = Integer.valueOf(((LeakCounter) (obj1)).leaked);
                    objectinfo.releasedCount = Integer.valueOf(((LeakCounter) (obj1)).released);
                    arraylist.add(objectinfo);
                    obj1.leaked = 0;
                    obj1.released = 0;
                }
            } while (true);
            boolean flag1;
            if (!arraylist.isEmpty())
            {
                SystemHealthMetric systemhealthmetric = new SystemHealthMetric();
                systemhealthmetric.memoryLeakMetric = new MemoryLeakMetric();
                systemhealthmetric.memoryLeakMetric.objectInfo = (ObjectInfo[])arraylist.toArray(new ObjectInfo[arraylist.size()]);
                if (!metricRecorder.instrumentationSampling.isSampleRateExceeded())
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    recordSystemHealthMetric(null, true, systemhealthmetric, null);
                }
            }
            if (flag)
            {
                if (!heapDumpEligible || shutdown || !heapDumpEnabled && !leakDetectionV2Enabled)
                {
                    flag1 = false;
                } else
                {
                    long l = lastSent.get();
                    if (l == 0L || l + 0x2932e00L <= SystemClock.elapsedRealtime())
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                }
                if (flag1 && dumpScheduled.compareAndSet(false, true))
                {
                    Object obj = "Scheduling heap dump %d seconds after the next screen off.";
                    Object aobj[] = new Object[1];
                    aobj[0] = Integer.valueOf(5);
                    if (Log.isLoggable("MemoryLeakService", 3))
                    {
                        if (aobj.length != 0)
                        {
                            obj = String.format(Locale.US, "Scheduling heap dump %d seconds after the next screen off.", aobj);
                        }
                        Log.println(3, "MemoryLeakService", ((String) (obj)));
                    }
                    obj = new IntentFilter("android.intent.action.SCREEN_OFF");
                    ((IntentFilter) (obj)).addAction("android.intent.action.SCREEN_ON");
                    application.registerReceiver(new ScreenOnOffReceiver(), ((IntentFilter) (obj)));
                }
            }
        }

        public final void onHeapDumpResult(List list)
        {
            ArrayList arraylist = new ArrayList();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) 
            {
                LeakInfo leakinfo = (LeakInfo)iterator.next();
                ObjectInfo objectinfo = new ObjectInfo();
                String s1 = leakinfo.path;
                int i = s1.indexOf('\n');
                String s;
                if (i < 0)
                {
                    s = s1;
                } else
                {
                    s = s1.substring(0, i);
                }
                objectinfo.className = s;
                objectinfo.leakPath = s1;
                objectinfo.retainedHeapBytes = Integer.valueOf(leakinfo.retainedHeapSizeBytes);
                objectinfo.leakedCount = Integer.valueOf(1);
                arraylist.add(objectinfo);
            }
            if (!arraylist.isEmpty())
            {
                Object obj = new SystemHealthMetric();
                obj.memoryLeakMetric = new MemoryLeakMetric();
                ((SystemHealthMetric) (obj)).memoryLeakMetric.objectInfo = (ObjectInfo[])arraylist.toArray(new ObjectInfo[arraylist.size()]);
                boolean flag;
                if (!metricRecorder.instrumentationSampling.isSampleRateExceeded())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    recordSystemHealthMetric(null, true, ((SystemHealthMetric) (obj)), null);
                }
            }
            if (!list.isEmpty())
            {
                obj = "Primes found %d leak(s): %s";
                Object aobj[] = new Object[2];
                aobj[0] = Integer.valueOf(list.size());
                aobj[1] = list;
                if (Log.isLoggable("MemoryLeakService", 2))
                {
                    if (aobj.length == 0)
                    {
                        list = ((List) (obj));
                    } else
                    {
                        list = String.format(Locale.US, "Primes found %d leak(s): %s", aobj);
                    }
                    Log.println(2, "MemoryLeakService", list);
                }
            }
        }

        public final void onLeaked(String s)
        {
            LeakCounter leakcounter1 = (LeakCounter)stats.get(s);
            LeakCounter leakcounter = leakcounter1;
            if (leakcounter1 == null)
            {
                leakcounter = new LeakCounter();
                stats.put(s, leakcounter);
            }
            leakcounter.leaked = leakcounter.leaked + 1;
        }

        public final void onReleased(String s)
        {
            LeakCounter leakcounter1 = (LeakCounter)stats.get(s);
            LeakCounter leakcounter = leakcounter1;
            if (leakcounter1 == null)
            {
                leakcounter = new LeakCounter();
                stats.put(s, leakcounter);
            }
            leakcounter.released = leakcounter.released + 1;
        }

        PrimesLeakListener()
        {
            this$0 = MemoryLeakMetricService.this;
            super();
        }
    }

    final class ScreenOnOffReceiver extends BroadcastReceiver
    {

        public final MemoryLeakMetricService this$0;

        public final void onReceive(final Context context, Intent intent)
        {
            if ("android.intent.action.SCREEN_ON".equals(intent.getAction()))
            {
                if (dumpScheduled.get())
                {
                    cancelDumpTaskIfAny();
                }
                return;
            } else
            {
                cancelDumpTaskIfAny();
                class _cls1
                    implements Runnable
                {

                    private final ScreenOnOffReceiver this$1;
                    private final Context val$context;

                    public final void run()
                    {
                        if (dumpScheduled.compareAndSet(true, false))
                        {
                            context.unregisterReceiver(ScreenOnOffReceiver.this);
                            lastSent.set(SystemClock.elapsedRealtime());
                            Object obj1 = leakWatcher;
                            Object obj = PrimesHprofFile.getHprofFile(context);
                            if (((LeakWatcher) (obj1)).leakWatcherThread != null)
                            {
                                obj1 = ((LeakWatcher) (obj1)).leakWatcherThread;
                                if (((LeakWatcherThread) (obj1)).queueForDump.next != null)
                                {
                                    if (obj == null)
                                    {
                                        throw new NullPointerException();
                                    }
                                    obj1.hprofFile = (File)obj;
                                    ((LeakWatcherThread) (obj1)).interrupt();
                                    obj = "Schedule for heap dump";
                                    Object aobj[] = new Object[0];
                                    if (Log.isLoggable("LeakWatcherThread", 3))
                                    {
                                        if (aobj.length != 0)
                                        {
                                            obj = String.format(Locale.US, "Schedule for heap dump", aobj);
                                        }
                                        Log.println(3, "LeakWatcherThread", ((String) (obj)));
                                    }
                                } else
                                {
                                    String s = "Skip heap dump. No leak suspects found.";
                                    Object aobj1[] = new Object[0];
                                    if (Log.isLoggable("LeakWatcherThread", 3))
                                    {
                                        if (aobj1.length != 0)
                                        {
                                            s = String.format(Locale.US, "Skip heap dump. No leak suspects found.", aobj1);
                                        }
                                        Log.println(3, "LeakWatcherThread", s);
                                        return;
                                    }
                                }
                            }
                        }
                    }

                _cls1()
                {
                    this$1 = ScreenOnOffReceiver.this;
                    context = context1;
                    super();
                }
                }

                dumpFutureTask = ((ScheduledExecutorService)executorServiceSupplier.get()).schedule(new _cls1(), 5L, TimeUnit.SECONDS);
                return;
            }
        }

        ScreenOnOffReceiver()
        {
            this$0 = MemoryLeakMetricService.this;
            super();
        }
    }


    public final AppLifecycleMonitor appLifecycleMonitor;
    public final Application application;
    public ScheduledFuture dumpFutureTask;
    public final AtomicBoolean dumpScheduled;
    public final Supplier executorServiceSupplier;
    public final boolean heapDumpEligible;
    public final boolean heapDumpEnabled;
    public final AtomicLong lastSent;
    public final boolean leakDetectionV2Enabled;
    public final LeakWatcher leakWatcher;

    MemoryLeakMetricService(Application application1, boolean flag, boolean flag1, AppLifecycleMonitor applifecyclemonitor, Supplier supplier, Supplier supplier1, LeakWatcher leakwatcher, 
            MetricTransmitter metrictransmitter)
    {
        super(metrictransmitter, application1, supplier, supplier1, android.support.v4.content.ModernAsyncTask.Status.BACKGROUND_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0);
        lastSent = new AtomicLong();
        dumpScheduled = new AtomicBoolean();
        if (application1 == null)
        {
            throw new NullPointerException();
        }
        application = (Application)application1;
        leakDetectionV2Enabled = flag;
        heapDumpEnabled = flag1;
        if (applifecyclemonitor == null)
        {
            throw new NullPointerException();
        }
        appLifecycleMonitor = (AppLifecycleMonitor)applifecyclemonitor;
        if (supplier1 == null)
        {
            throw new NullPointerException();
        }
        executorServiceSupplier = (Supplier)supplier1;
        if (leakwatcher == null)
        {
            throw new NullPointerException();
        }
        leakWatcher = (LeakWatcher)leakwatcher;
        leakWatcher.leakListener = new PrimesLeakListener();
        if (android.os.Build.VERSION.SDK_INT < 23) goto _L2; else goto _L1
_L1:
        application1 = (DevicePolicyManager)application1.getSystemService("device_policy");
        int i;
        if (application1 == null)
        {
            i = 0;
        } else
        {
            i = application1.getStorageEncryptionStatus();
        }
        if (i == 3 || i == 4 || i == 5)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0 || !Build.FINGERPRINT.contains("userdebug")) goto _L2; else goto _L3
_L3:
        flag = true;
_L5:
        heapDumpEligible = flag;
        return;
_L2:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
    }

    final void cancelDumpTaskIfAny()
    {
        if (dumpFutureTask != null)
        {
            if (!dumpFutureTask.isDone())
            {
                dumpFutureTask.cancel(true);
            }
            dumpFutureTask = null;
        }
    }

    public final void onActivityDestroyed(Activity activity)
    {
        if (!super.shutdown)
        {
            leakWatcher.watch(activity, activity.getClass().getName());
        }
    }

    final void shutdownService()
    {
        appLifecycleMonitor.unregister(this);
        leakWatcher.stop();
        cancelDumpTaskIfAny();
    }
}
