// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;

import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.PackageStats;
import android.os.Looper;
import android.os.Process;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import com.google.android.libraries.performance.primes.PrimesLog;
import com.google.android.libraries.stitch.util.ThreadUtil;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

final class PackageStatsCaptureO
{

    static PackageStats getPackageStats(Context context)
    {
        Object obj;
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
        obj = (StorageManager)context.getSystemService(android/os/storage/StorageManager);
        if (obj == null)
        {
            PrimesLog.log(6, "PackageStatsO", "StorageManager is not available", new Object[0]);
            return null;
        }
        StorageStatsManager storagestatsmanager;
        String s;
        PackageStats packagestats;
        storagestatsmanager = (StorageStatsManager)context.getSystemService(android/app/usage/StorageStatsManager);
        s = context.getPackageName();
        packagestats = new PackageStats(s);
        obj = ((StorageManager) (obj)).getStorageVolumes().iterator();
_L4:
        if (!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
        context = (StorageVolume)((Iterator) (obj)).next();
        if (!context.getState().equals("mounted")) goto _L4; else goto _L3
_L3:
        context = getUuid(context);
        if (context == null) goto _L4; else goto _L5
_L5:
        StorageStats storagestats = storagestatsmanager.queryStatsForPackage(context, s, Process.myUserHandle());
        if (!StorageManager.UUID_DEFAULT.equals(context)) goto _L7; else goto _L6
_L6:
        packagestats.codeSize = packagestats.codeSize + storagestats.getAppBytes();
        packagestats.dataSize = packagestats.dataSize + (storagestats.getDataBytes() - storagestats.getCacheBytes());
        packagestats.cacheSize = packagestats.cacheSize + storagestats.getCacheBytes();
          goto _L4
        context;
_L8:
        PrimesLog.log(6, "PackageStatsO", context, "queryStatsForPackage() call failed", new Object[0]);
          goto _L4
        context;
_L9:
        PrimesLog.log(5, "PackageStatsO", context, "StorageStatsManager is not available", new Object[0]);
        return null;
_L7:
        packagestats.externalCodeSize = packagestats.externalCodeSize + storagestats.getAppBytes();
        packagestats.externalDataSize = packagestats.externalDataSize + (storagestats.getDataBytes() - storagestats.getCacheBytes());
        packagestats.externalCacheSize = packagestats.externalCacheSize + storagestats.getCacheBytes();
          goto _L4
        context;
          goto _L8
_L2:
        return packagestats;
        context;
          goto _L9
        context;
          goto _L8
    }

    private static UUID getUuid(StorageVolume storagevolume)
    {
        storagevolume = storagevolume.getUuid();
        UUID uuid;
        try
        {
            PrimesLog.log(3, "PackageStatsO", "UUID for %s", new Object[] {
                storagevolume
            });
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            PrimesLog.log(6, "PackageStatsO", "Invalid UUID format: '%s'", new Object[] {
                storagevolume
            });
            return null;
        }
        if (storagevolume != null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        return StorageManager.UUID_DEFAULT;
        uuid = UUID.fromString(storagevolume);
        return uuid;
    }
}
