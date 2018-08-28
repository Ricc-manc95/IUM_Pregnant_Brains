// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.android.libraries.stitch.util.ThreadUtil;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, PrimesStartupListener, AppLifecycleMonitor, Supplier

final class PackageMetricService extends AbstractMetricService
    implements AppLifecycleListener.OnAppToBackground, PrimesStartupListener
{

    private static final long CONSIDER_RECENT_DURATION_MS;
    private final AppLifecycleMonitor appLifecycleMonitor;
    public final boolean captureDirStats;
    public final Pattern listFilesPatterns[];
    public final int maxFolderDepth;
    public final SharedPreferences sharedPrefs;

    transient PackageMetricService(MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1, SharedPreferences sharedpreferences, boolean flag, int i, 
            Pattern apattern[])
    {
        super(metrictransmitter, application, supplier, supplier1, android.support.v4.content.ModernAsyncTask.Status.SAME_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0);
        sharedPrefs = sharedpreferences;
        captureDirStats = flag;
        maxFolderDepth = i;
        listFilesPatterns = apattern;
        appLifecycleMonitor = AppLifecycleMonitor.getInstance(application);
    }

    static boolean skipPackageMetric(SharedPreferences sharedpreferences)
    {
        long l2 = CONSIDER_RECENT_DURATION_MS;
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
        long l1 = sharedpreferences.getLong("primes.packageMetric.lastSendTime", -1L);
        long l3 = SystemClock.elapsedRealtime();
        long l = l1;
        if (l3 < l1)
        {
            boolean flag1;
            if (!sharedpreferences.edit().remove("primes.packageMetric.lastSendTime").commit())
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                sharedpreferences = "Failure storing timestamp to SharedPreferences";
                Object aobj[] = new Object[0];
                if (Log.isLoggable("SamplingUtil", 3))
                {
                    if (aobj.length != 0)
                    {
                        sharedpreferences = String.format(Locale.US, "Failure storing timestamp to SharedPreferences", aobj);
                    }
                    Log.println(3, "SamplingUtil", sharedpreferences);
                }
            }
            l = -1L;
        }
        return l != -1L && l3 <= l + l2;
    }

    public final void onAppToBackground(Activity activity)
    {
        appLifecycleMonitor.unregister(this);
        ((ScheduledExecutorService)super.executorServiceSupplier.get()).submit(new _cls1());
    }

    public final void onFirstActivityCreated()
    {
    }

    public final void onPrimesInitialize()
    {
        appLifecycleMonitor.register(this);
    }

    final void shutdownService()
    {
        appLifecycleMonitor.unregister(this);
    }

    static 
    {
        CONSIDER_RECENT_DURATION_MS = TimeUnit.HOURS.toMillis(12L);
    }

    private class _cls1
        implements Runnable
    {

        private final PackageMetricService this$0;

        public final void run()
        {
            boolean flag = true;
            if (!PackageMetricService.skipPackageMetric(sharedPrefs))
            {
                Object obj = PackageMetricService.this;
                PackageStats packagestats = PackageStatsCapture.getPackageStats(((AbstractMetricService) (obj)).application);
                if (packagestats != null)
                {
                    SystemHealthMetric systemhealthmetric = new SystemHealthMetric();
                    if (packagestats == null)
                    {
                        throw new NullPointerException();
                    }
                    PackageMetric packagemetric = new PackageMetric();
                    packagemetric.cacheSize = Long.valueOf(packagestats.cacheSize);
                    packagemetric.codeSize = Long.valueOf(packagestats.codeSize);
                    packagemetric.dataSize = Long.valueOf(packagestats.dataSize);
                    packagemetric.externalCacheSize = Long.valueOf(packagestats.externalCacheSize);
                    packagemetric.externalCodeSize = Long.valueOf(packagestats.externalCodeSize);
                    packagemetric.externalDataSize = Long.valueOf(packagestats.externalDataSize);
                    packagemetric.externalMediaSize = Long.valueOf(packagestats.externalMediaSize);
                    packagemetric.externalObbSize = Long.valueOf(packagestats.externalObbSize);
                    systemhealthmetric.packageMetric = packagemetric;
                    if (((PackageMetricService) (obj)).captureDirStats)
                    {
                        systemhealthmetric.packageMetric.dirStats = DirStatsCapture.getDirStats(((AbstractMetricService) (obj)).application, ((PackageMetricService) (obj)).maxFolderDepth, ((PackageMetricService) (obj)).listFilesPatterns);
                    }
                    ((AbstractMetricService) (obj)).recordSystemHealthMetric(null, true, systemhealthmetric, null);
                    obj = ((PackageMetricService) (obj)).sharedPrefs;
                    long l = SystemClock.elapsedRealtime();
                    if (((SharedPreferences) (obj)).edit().putLong("primes.packageMetric.lastSendTime", l).commit())
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        String s = "Failure storing timestamp persistently";
                        Object aobj[] = new Object[0];
                        if (Log.isLoggable("PackageMetricService", 3))
                        {
                            if (aobj.length != 0)
                            {
                                s = String.format(Locale.US, "Failure storing timestamp persistently", aobj);
                            }
                            Log.println(3, "PackageMetricService", s);
                        }
                    }
                } else
                {
                    String s1 = "PackageStats capture failed.";
                    Object aobj1[] = new Object[0];
                    if (Log.isLoggable("PackageMetricService", 5))
                    {
                        if (aobj1.length != 0)
                        {
                            s1 = String.format(Locale.US, "PackageStats capture failed.", aobj1);
                        }
                        Log.println(5, "PackageMetricService", s1);
                        return;
                    }
                }
            }
        }

        _cls1()
        {
            this$0 = PackageMetricService.this;
            super();
        }
    }

}
