// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.content.SharedPreferences;
import android.content.pm.PackageStats;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.libraries.performance.primes.metriccapture.DirStatsCapture;
import com.google.android.libraries.performance.primes.metriccapture.PackageStatsCapture;
import java.util.Locale;
import logs.proto.wireless.performance.mobile.nano.PackageMetric;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PackageMetricService, AbstractMetricService

final class this._cls0
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

    apture()
    {
        this$0 = PackageMetricService.this;
        super();
    }
}
