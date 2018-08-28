// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Looper;
import android.os.Process;
import com.google.android.libraries.performance.primes.PrimesLog;
import com.google.android.libraries.stitch.util.ThreadUtil;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.libraries.performance.primes.metriccapture:
//            PackageStatsCaptureO

public final class PackageStatsCapture
{
    static final class PackageStatsCallback extends android.content.pm.IPackageStatsObserver.Stub
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

        PackageStatsCallback()
        {
        }
    }

    static abstract class PackageStatsInvocation
    {

        private final String methodName;
        private final Class paramTypes[];

        boolean invoke(PackageManager packagemanager, String s, int i, IPackageStatsObserver ipackagestatsobserver)
        {
            packagemanager.getClass().getMethod(methodName, paramTypes).invoke(packagemanager, params(s, i, ipackagestatsobserver));
            return true;
            packagemanager;
            PrimesLog.log(3, "PackageStatsCapture", packagemanager, "PackageStats getter not found", new Object[0]);
_L2:
            return false;
            packagemanager;
_L3:
            s = new StringBuilder();
            s.append(packagemanager.getClass().getSimpleName()).append(" for ").append(methodName).append('(').append(Arrays.asList(paramTypes)).append(") invocation");
            PrimesLog.log(4, "PackageStatsCapture", s.toString(), new Object[0]);
            if (true) goto _L2; else goto _L1
_L1:
            packagemanager;
              goto _L3
        }

        abstract Object[] params(String s, int i, IPackageStatsObserver ipackagestatsobserver);

        PackageStatsInvocation(String s, Class aclass[])
        {
            methodName = s;
            paramTypes = aclass;
        }
    }


    public static final PackageStatsInvocation GETTER_INVOCATIONS[];

    private PackageStatsCapture()
    {
    }

    public static PackageStats getPackageStats(Context context)
    {
        boolean flag2 = false;
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
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        throw new RuntimeException("Must be called on a background thread");
        context;
        throw context;
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            return PackageStatsCaptureO.getPackageStats(context);
        }
        if (context.getPackageManager().checkPermission("android.permission.GET_PACKAGE_SIZE", context.getPackageName()) == 0) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (context.checkCallingOrSelfPermission("android.permission.GET_PACKAGE_SIZE") != 0) goto _L3; else goto _L2
_L3:
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        return getPackageStatsUsingInternalAPI(context, 15000L, GETTER_INVOCATIONS);
        PrimesLog.log(5, "PackageStatsCapture", "android.permission.GET_PACKAGE_SIZE required", new Object[0]);
        return null;
_L2:
        flag1 = true;
        if (true) goto _L3; else goto _L4
_L4:
    }

    static transient PackageStats getPackageStatsUsingInternalAPI(Context context, long l, PackageStatsInvocation apackagestatsinvocation[])
    {
        int i;
        i = 0;
        if (!isCallbackPresent())
        {
            PrimesLog.log(5, "PackageStatsCapture", "Callback implementation stripped by proguard.", new Object[0]);
            return null;
        }
        PackageStatsCallback packagestatscallback = new PackageStatsCallback();
        PackageManager packagemanager;
        int j;
        int k;
        try
        {
            packagestatscallback.semaphore.acquire();
            packagemanager = context.getPackageManager();
            context = context.getPackageName();
            j = Process.myUid();
            k = apackagestatsinvocation.length;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            Thread.currentThread().interrupt();
            return null;
        }
_L2:
        if (i >= k)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!apackagestatsinvocation[i].invoke(packagemanager, context, j, packagestatscallback))
        {
            break MISSING_BLOCK_LABEL_140;
        }
        PrimesLog.log(4, "PackageStatsCapture", "Success invoking PackageStats capture.", new Object[0]);
        if (packagestatscallback.semaphore.tryAcquire(l, TimeUnit.MILLISECONDS))
        {
            return packagestatscallback.packageStats;
        }
        PrimesLog.log(5, "PackageStatsCapture", "Timeout while waiting for PackageStats callback", new Object[0]);
        return null;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        PrimesLog.log(5, "PackageStatsCapture", "Couldn't capture PackageStats.", new Object[0]);
        return null;
    }

    private static boolean isCallbackPresent()
    {
        boolean flag = Modifier.isAbstract(com/google/android/libraries/performance/primes/metriccapture/PackageStatsCapture$PackageStatsCallback.getMethod("onGetStatsCompleted", new Class[] {
            android/content/pm/PackageStats, Boolean.TYPE
        }).getModifiers());
        return !flag;
        Object obj;
        obj;
_L2:
        PrimesLog.log(3, "PackageStatsCapture", ((Throwable) (obj)), "failure", new Object[0]);
        return false;
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static 
    {
        GETTER_INVOCATIONS = (new PackageStatsInvocation[] {
            new _cls1("getPackageSizeInfo", new Class[] {
                java/lang/String, android/content/pm/IPackageStatsObserver
            }), new _cls2("getPackageSizeInfo", new Class[] {
                java/lang/String, Integer.TYPE, android/content/pm/IPackageStatsObserver
            }), new _cls3("getPackageSizeInfoAsUser", new Class[] {
                java/lang/String, Integer.TYPE, android/content/pm/IPackageStatsObserver
            })
        });
    }

    private class _cls1 extends PackageStatsInvocation
    {

        final Object[] params(String s, int i, IPackageStatsObserver ipackagestatsobserver)
        {
            return (new Object[] {
                s, ipackagestatsobserver
            });
        }

        _cls1(String s, Class aclass[])
        {
            super(s, aclass);
        }
    }


    private class _cls2 extends PackageStatsInvocation
    {

        final Object[] params(String s, int i, IPackageStatsObserver ipackagestatsobserver)
        {
            return (new Object[] {
                s, Integer.valueOf(i), ipackagestatsobserver
            });
        }

        _cls2(String s, Class aclass[])
        {
            super(s, aclass);
        }
    }


    private class _cls3 extends PackageStatsInvocation
    {

        final Object[] params(String s, int i, IPackageStatsObserver ipackagestatsobserver)
        {
            return (new Object[] {
                s, Integer.valueOf(i), ipackagestatsobserver
            });
        }

        _cls3(String s, Class aclass[])
        {
            super(s, aclass);
        }
    }

}
