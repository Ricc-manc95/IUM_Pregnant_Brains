// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.feature.VariantFeatureConfig_release;
import com.google.android.apps.calendar.primes.PerformanceMetricCollectorImpl;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            CalendarSyncAdapterApiary

public class CalendarSyncAdapterService extends Service
{

    private static CalendarSyncAdapterApiary syncAdapter = null;
    private static final Object syncAdapterLock = new Object();

    public CalendarSyncAdapterService()
    {
    }

    private static CalendarSyncAdapterApiary getOrMakeSyncAdapter(Context context)
    {
        Object obj = syncAdapterLock;
        obj;
        JVM INSTR monitorenter ;
        FeatureConfig featureconfig;
        if (syncAdapter != null)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        featureconfig = Features.instance;
        if (featureconfig != null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        ((FeatureConfig)featureconfig).public_release();
        syncAdapter = new CalendarSyncAdapterApiary(context);
        context = syncAdapter;
        obj;
        JVM INSTR monitorexit ;
        return context;
    }

    public IBinder onBind(Intent intent)
    {
        return syncAdapter.getSyncAdapterBinder();
    }

    public void onCreate()
    {
        getOrMakeSyncAdapter(getApplicationContext());
    }

    static 
    {
        Features.instance = new VariantFeatureConfig_release();
        PerformanceMetricCollectorImpl performancemetriccollectorimpl = new PerformanceMetricCollectorImpl();
        if (PerformanceMetricCollectorHolder.instance == null)
        {
            PerformanceMetricCollectorHolder.instance = performancemetriccollectorimpl;
        }
    }
}
