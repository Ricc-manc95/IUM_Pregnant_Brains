// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.libraries.flashmanagement.storagestats.DataPartitionSize;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.version.PrimesVersion;
import java.io.File;
import java.util.Locale;
import logs.proto.wireless.performance.mobile.nano.AccountableComponent;
import logs.proto.wireless.performance.mobile.nano.ApplicationInfo;
import logs.proto.wireless.performance.mobile.nano.DeviceInfo;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            Supplier, NoPiiString

public final class MetricStamper
{

    private final String applicationPackage;
    private final Supplier componentNameSupplier;
    private final DataPartitionSize dataPartitionSize;
    private final int hardwareVariant;
    public final Long primesVersion;
    private final String shortProcessName;
    private final long totalDiskSizeKb;
    public final String versionName;

    private MetricStamper(String s, String s1, String s2, int i, Long long1, DataPartitionSize datapartitionsize, Supplier supplier)
    {
        applicationPackage = s;
        shortProcessName = s1;
        versionName = s2;
        hardwareVariant = i;
        primesVersion = long1;
        dataPartitionSize = datapartitionsize;
        totalDiskSizeKb = datapartitionsize.getDataPartition().getTotalSpace() / 1024L;
        componentNameSupplier = supplier;
    }

    static MetricStamper createMetricStamper(Context context, Supplier supplier)
    {
        Object obj = null;
        boolean flag = true;
        if (context == null)
        {
            throw new NullPointerException();
        }
        String s4 = ((Context)context).getPackageName();
        String s2 = context.getPackageName();
        String s1 = ProcessStats.getProcessNameFromProcFile(Process.myPid());
        String s = s1;
        PackageManager packagemanager;
        int i;
        if (s1 != null)
        {
            if (s2 == null)
            {
                s = s1;
            } else
            {
                s = s1;
                if (s1.startsWith(s2))
                {
                    i = s2.length();
                    if (s1.length() == i)
                    {
                        s = null;
                    } else
                    {
                        s = s1.substring(i + 1);
                    }
                }
            }
        }
        packagemanager = context.getPackageManager();
        try
        {
            s1 = packagemanager.getPackageInfo(s4, 0).versionName;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            String s3 = "Failed to get PackageInfo for: %s";
            Object aobj[] = new Object[1];
            aobj[0] = s4;
            namenotfoundexception = obj;
            if (Log.isLoggable("MetricStamper", 5))
            {
                if (aobj.length == 0)
                {
                    namenotfoundexception = s3;
                } else
                {
                    namenotfoundexception = String.format(Locale.US, "Failed to get PackageInfo for: %s", aobj);
                }
                Log.println(5, "MetricStamper", namenotfoundexception);
                namenotfoundexception = obj;
            }
        }
        i = ((flag) ? 1 : 0);
        if (android.os.Build.VERSION.SDK_INT >= 20)
        {
            if (packagemanager.hasSystemFeature("android.hardware.type.watch"))
            {
                i = 2;
            } else
            {
                i = ((flag) ? 1 : 0);
                if (android.os.Build.VERSION.SDK_INT >= 21)
                {
                    i = ((flag) ? 1 : 0);
                    if (packagemanager.hasSystemFeature("android.software.leanback"))
                    {
                        i = 3;
                    }
                }
            }
        }
        return new MetricStamper(s4, s, s1, i, PrimesVersion.getPrimesVersion(context), new DataPartitionSize(context), supplier);
    }

    public final SystemHealthMetric stamp(SystemHealthMetric systemhealthmetric)
    {
        if (systemhealthmetric == null)
        {
            String s = "Unexpected null metric to stamp, Stamping has been skipped.";
            Object aobj[] = new Object[0];
            if (Log.isLoggable("MetricStamper", 5))
            {
                if (aobj.length != 0)
                {
                    s = String.format(Locale.US, "Unexpected null metric to stamp, Stamping has been skipped.", aobj);
                }
                Log.println(5, "MetricStamper", s);
            }
        } else
        {
            systemhealthmetric.applicationInfo = new ApplicationInfo();
            systemhealthmetric.applicationInfo.applicationPackage = applicationPackage;
            systemhealthmetric.applicationInfo.hardwareVariant = hardwareVariant;
            systemhealthmetric.applicationInfo.primesVersion = primesVersion;
            if (versionName != null)
            {
                systemhealthmetric.applicationInfo.applicationVersionName = versionName;
            }
            systemhealthmetric.applicationInfo.shortProcessName = shortProcessName;
            systemhealthmetric.deviceInfo = new DeviceInfo();
            systemhealthmetric.deviceInfo.availableDiskSizeKb = Long.valueOf(dataPartitionSize.getDataPartition().getFreeSpace() / 1024L);
            systemhealthmetric.deviceInfo.totalDiskSizeKb = Long.valueOf(totalDiskSizeKb);
            Object obj;
            if (componentNameSupplier == null)
            {
                obj = null;
            } else
            {
                obj = ((NoPiiString)componentNameSupplier.get()).toString();
            }
            if (!TextUtils.isEmpty(((CharSequence) (obj))))
            {
                if (systemhealthmetric.accountableComponent == null)
                {
                    systemhealthmetric.accountableComponent = new AccountableComponent();
                }
                if (TextUtils.isEmpty(systemhealthmetric.accountableComponent.customName))
                {
                    systemhealthmetric.accountableComponent.customName = ((String) (obj));
                    return systemhealthmetric;
                } else
                {
                    systemhealthmetric.accountableComponent.customName = (new StringBuilder(((String) (obj)))).append("::").append(systemhealthmetric.accountableComponent.customName).toString();
                    return systemhealthmetric;
                }
            }
        }
        return systemhealthmetric;
    }
}
