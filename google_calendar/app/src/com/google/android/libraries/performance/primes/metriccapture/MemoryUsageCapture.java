// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;
import android.os.PowerManager;
import com.google.android.libraries.performance.primes.PrimesLog;
import com.google.android.libraries.stitch.util.ThreadUtil;
import java.lang.reflect.Method;
import java.util.Map;
import logs.proto.wireless.performance.mobile.nano.AndroidMemoryStats;
import logs.proto.wireless.performance.mobile.nano.DeviceStats;
import logs.proto.wireless.performance.mobile.nano.MemoryStats;
import logs.proto.wireless.performance.mobile.nano.MemoryUsageMetric;
import logs.proto.wireless.performance.mobile.nano.ProcessStats;

// Referenced classes of package com.google.android.libraries.performance.primes.metriccapture:
//            ProcessStats, ProcessStatsCapture

public final class MemoryUsageCapture
{

    private static Method otherPssGetter;
    private static volatile boolean otherPssGetterInitialized;

    private MemoryUsageCapture()
    {
    }

    public static MemoryUsageMetric getMemoryUsageMetric(int i, int j, String s, Context context, String s1, boolean flag)
    {
        if (ThreadUtil.sMainThread == null)
        {
            ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
        }
        boolean flag1;
        if (Thread.currentThread() == ThreadUtil.sMainThread)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            throw new RuntimeException("Must be called on a background thread");
        }
        if (context == null)
        {
            throw new NullPointerException();
        } else
        {
            MemoryUsageMetric memoryusagemetric = new MemoryUsageMetric();
            memoryusagemetric.memoryStats = new MemoryStats();
            android.os.Debug.MemoryInfo amemoryinfo[] = ProcessStats.getActivityManager(context).getProcessMemoryInfo(new int[] {
                j
            });
            android.app.ActivityManager.MemoryInfo memoryinfo = new android.app.ActivityManager.MemoryInfo();
            ProcessStats.getActivityManager(context).getMemoryInfo(memoryinfo);
            memoryusagemetric.memoryStats.androidMemoryStats = toAndroidMemoryStats(amemoryinfo[0], memoryinfo, flag);
            memoryusagemetric.processStats = new ProcessStats();
            memoryusagemetric.processStats.androidProcessStats = ProcessStatsCapture.getAndroidProcessStats(s, context);
            memoryusagemetric.deviceStats = new DeviceStats();
            memoryusagemetric.deviceStats.isScreenOn = Boolean.valueOf(((PowerManager)context.getSystemService("power")).isInteractive());
            memoryusagemetric.memoryEventCode = i;
            memoryusagemetric.activityName = s1;
            return memoryusagemetric;
        }
    }

    private static int getOtherGraphicsPss(android.os.Debug.MemoryInfo memoryinfo)
    {
        Method method = getOtherPssGetter();
        if (method == null) goto _L2; else goto _L1
_L1:
        int i = ((Integer)method.invoke(memoryinfo, new Object[] {
            Integer.valueOf(14)
        })).intValue();
        return i;
        memoryinfo;
_L4:
        otherPssGetter = null;
        PrimesLog.log(6, "PrimesMemoryCapture", memoryinfo, "MemoryInfo.getOtherPss(which) invocation failure", new Object[0]);
_L2:
        return -1;
        memoryinfo;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static Method getOtherPssGetter()
    {
        if (otherPssGetterInitialized) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/metriccapture/MemoryUsageCapture;
        JVM INSTR monitorenter ;
        boolean flag = otherPssGetterInitialized;
        if (flag) goto _L4; else goto _L3
_L3:
        otherPssGetter = android/os/Debug$MemoryInfo.getDeclaredMethod("getOtherPss", new Class[] {
            Integer.TYPE
        });
_L5:
        otherPssGetterInitialized = true;
_L4:
        com/google/android/libraries/performance/primes/metriccapture/MemoryUsageCapture;
        JVM INSTR monitorexit ;
_L2:
        return otherPssGetter;
        Object obj;
        obj;
        PrimesLog.log(3, "PrimesMemoryCapture", ((Throwable) (obj)), "MemoryInfo.getOtherPss(which) not found", new Object[0]);
          goto _L5
        obj;
        com/google/android/libraries/performance/primes/metriccapture/MemoryUsageCapture;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
_L6:
        PrimesLog.log(6, "PrimesMemoryCapture", ((Throwable) (obj)), "MemoryInfo.getOtherPss(which) failure", new Object[0]);
          goto _L5
        obj;
          goto _L6
    }

    private static AndroidMemoryStats toAndroidMemoryStats(android.os.Debug.MemoryInfo memoryinfo, android.app.ActivityManager.MemoryInfo memoryinfo1, boolean flag)
    {
        AndroidMemoryStats androidmemorystats;
        androidmemorystats = new AndroidMemoryStats();
        androidmemorystats.dalvikPssKb = Integer.valueOf(memoryinfo.dalvikPss);
        androidmemorystats.nativePssKb = Integer.valueOf(memoryinfo.nativePss);
        androidmemorystats.otherPssKb = Integer.valueOf(memoryinfo.otherPss);
        androidmemorystats.dalvikPrivateDirtyKb = Integer.valueOf(memoryinfo.dalvikPrivateDirty);
        androidmemorystats.nativePrivateDirtyKb = Integer.valueOf(memoryinfo.nativePrivateDirty);
        androidmemorystats.otherPrivateDirtyKb = Integer.valueOf(memoryinfo.otherPrivateDirty);
        androidmemorystats.totalPssByMemInfoKb = Integer.valueOf(memoryinfo.getTotalPss());
        androidmemorystats.totalPrivateCleanKb = Integer.valueOf(memoryinfo.getTotalPrivateClean());
        androidmemorystats.totalSwappablePssKb = Integer.valueOf(memoryinfo.getTotalSwappablePss());
        androidmemorystats.totalSharedDirtyKb = Integer.valueOf(memoryinfo.getTotalSharedDirty());
        int i = getOtherGraphicsPss(memoryinfo);
        if (i != -1)
        {
            androidmemorystats.otherGraphicsPssKb = Integer.valueOf(i);
        }
        if (android.os.Build.VERSION.SDK_INT < 23 || flag) goto _L2; else goto _L1
_L1:
        Map map;
        map = memoryinfo.getMemoryStats();
        memoryinfo = (String)map.get("summary.code");
        if (memoryinfo != null) goto _L4; else goto _L3
_L3:
        memoryinfo = null;
_L15:
        androidmemorystats.summaryCodeKb = memoryinfo;
        memoryinfo = (String)map.get("summary.stack");
        if (memoryinfo != null) goto _L6; else goto _L5
_L5:
        memoryinfo = null;
_L16:
        androidmemorystats.summaryStackKb = memoryinfo;
        memoryinfo = (String)map.get("summary.graphics");
        if (memoryinfo != null) goto _L8; else goto _L7
_L7:
        memoryinfo = null;
_L17:
        androidmemorystats.summaryGraphicsKb = memoryinfo;
        memoryinfo = (String)map.get("summary.system");
        if (memoryinfo != null) goto _L10; else goto _L9
_L9:
        memoryinfo = null;
_L18:
        androidmemorystats.summarySystemKb = memoryinfo;
        memoryinfo = (String)map.get("summary.java-heap");
        if (memoryinfo != null) goto _L12; else goto _L11
_L11:
        memoryinfo = null;
_L19:
        androidmemorystats.summaryJavaHeapKb = memoryinfo;
        memoryinfo = (String)map.get("summary.private-other");
        if (memoryinfo != null) goto _L14; else goto _L13
_L13:
        memoryinfo = null;
_L20:
        int j;
        try
        {
            androidmemorystats.summaryPrivateOtherKb = memoryinfo;
        }
        // Misplaced declaration of an exception variable
        catch (android.os.Debug.MemoryInfo memoryinfo)
        {
            PrimesLog.log(6, "PrimesMemoryCapture", "failed to collect memory summary stats", new Object[0]);
        }
_L2:
        androidmemorystats.availableMemoryKb = Integer.valueOf((int)(memoryinfo1.availMem >> 10));
        androidmemorystats.totalMemoryMb = Integer.valueOf((int)(memoryinfo1.totalMem >> 20));
        return androidmemorystats;
_L4:
        memoryinfo = Integer.valueOf(Integer.parseInt(memoryinfo));
          goto _L15
_L6:
        memoryinfo = Integer.valueOf(Integer.parseInt(memoryinfo));
          goto _L16
_L8:
        memoryinfo = Integer.valueOf(Integer.parseInt(memoryinfo));
          goto _L17
_L10:
        memoryinfo = Integer.valueOf(Integer.parseInt(memoryinfo));
          goto _L18
_L12:
        memoryinfo = Integer.valueOf(Integer.parseInt(memoryinfo));
          goto _L19
_L14:
        j = Integer.parseInt(memoryinfo);
        memoryinfo = Integer.valueOf(j);
          goto _L20
    }
}
