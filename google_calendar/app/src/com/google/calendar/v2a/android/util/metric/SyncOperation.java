// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;

import com.google.android.apps.calendar.config.remote.PrimesApiLoggingFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.gms.phenotype.PhenotypeFlag;

public final class SyncOperation extends Enum
    implements MetricUtils.Operation
{

    private static final SyncOperation $VALUES[];
    public static final SyncOperation APIARY;
    public static final SyncOperation HABITS_SYNC;
    public static final SyncOperation PROVIDER_SYNC;
    private static final SyncOperation PROVIDER_SYNC_CLEANUP;
    public static final SyncOperation SETTINGS_SYNC_DOWN;
    public static final SyncOperation SETTINGS_SYNC_UP;
    public static final SyncOperation UNIFIED_SYNC;
    public static final SyncOperation UNIFIED_SYNC_COALESCED;
    private static boolean loggingEnabled = false;
    private final String action;

    private SyncOperation(String s, int i, String s1)
    {
        super(s, i);
        action = s1;
    }

    public static void setupLogging(boolean flag)
    {
        loggingEnabled = flag;
    }

    public static SyncOperation[] values()
    {
        return (SyncOperation[])$VALUES.clone();
    }

    public final String getAction()
    {
        return action;
    }

    public final String getCategory()
    {
        return "Sync";
    }

    public final String getFullName()
    {
        return MetricUtils.Operation..CC.getFullName(this);
    }

    public final double getSampling()
    {
        if (loggingEnabled)
        {
            return ((Double)RemoteFeatureConfig.PRIMES_API_LOGGING.mSamplingSyncFlag.get()).doubleValue();
        } else
        {
            return 0.0D;
        }
    }

    static 
    {
        UNIFIED_SYNC = new SyncOperation("UNIFIED_SYNC", 0, "Unified");
        UNIFIED_SYNC_COALESCED = new SyncOperation("UNIFIED_SYNC_COALESCED", 1, "Unified.Coalesced");
        HABITS_SYNC = new SyncOperation("HABITS_SYNC", 2, "Habits");
        SETTINGS_SYNC_UP = new SyncOperation("SETTINGS_SYNC_UP", 3, "Settings.Up");
        SETTINGS_SYNC_DOWN = new SyncOperation("SETTINGS_SYNC_DOWN", 4, "Settings.Down");
        APIARY = new SyncOperation("APIARY", 5, "Apiary");
        PROVIDER_SYNC = new SyncOperation("PROVIDER_SYNC", 6, "Provider");
        PROVIDER_SYNC_CLEANUP = new SyncOperation("PROVIDER_SYNC_CLEANUP", 7, "Provider.Cleanup");
        $VALUES = (new SyncOperation[] {
            UNIFIED_SYNC, UNIFIED_SYNC_COALESCED, HABITS_SYNC, SETTINGS_SYNC_UP, SETTINGS_SYNC_DOWN, APIARY, PROVIDER_SYNC, PROVIDER_SYNC_CLEANUP
        });
    }
}
