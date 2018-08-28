// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;

import android.content.pm.PackageStats;
import com.google.android.libraries.performance.primes.PrimesLog;
import java.util.concurrent.Semaphore;

// Referenced classes of package com.google.android.libraries.performance.primes.metriccapture:
//            PackageStatsCapture

static final class  extends android.content.pm.e.PackageStatsCapture.PackageStatsCallback
{

    public volatile PackageStats packageStats;
    public final Semaphore semaphore = new Semaphore(1);

    public final void onGetStatsCompleted(PackageStats packagestats, boolean flag)
    {
        if (flag)
        {
            String s = String.valueOf(packagestats);
            PrimesLog.log(3, "PackageStatsCapture", (new StringBuilder(String.valueOf(s).length() + 30)).append("Success getting PackageStats: ").append(s).toString(), new Object[0]);
            packageStats = packagestats;
        } else
        {
            PrimesLog.log(5, "PackageStatsCapture", "Failure getting PackageStats", new Object[0]);
        }
        semaphore.release();
    }

    ()
    {
    }
}
