// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.SyncResult;
import android.content.SyncStats;
import android.os.Bundle;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;

public final class SyncUtil
{
    public static final class SyncStatsBackup
    {

        public long numDeletes;
        public long numEntries;
        public long numInserts;
        public long numSkippedEntries;
        public long numUpdates;

        public SyncStatsBackup()
        {
        }
    }


    public static SyncStatsBackup backUpSyncStats(SyncResult syncresult)
    {
        SyncStatsBackup syncstatsbackup = new SyncStatsBackup();
        syncstatsbackup.numEntries = syncresult.stats.numEntries;
        syncstatsbackup.numDeletes = syncresult.stats.numDeletes;
        syncstatsbackup.numInserts = syncresult.stats.numInserts;
        syncstatsbackup.numSkippedEntries = syncresult.stats.numSkippedEntries;
        syncstatsbackup.numUpdates = syncresult.stats.numUpdates;
        return syncstatsbackup;
    }

    public static void requestSync(Account account, String s, Bundle bundle)
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).fishfood_debug();
            ContentResolver.requestSync(account, s, bundle);
            return;
        }
    }

    public static void restoreSyncStats(SyncResult syncresult, SyncStatsBackup syncstatsbackup, boolean flag)
    {
        if (flag)
        {
            syncresult.stats.numEntries = syncstatsbackup.numEntries;
        }
        syncresult.stats.numDeletes = syncstatsbackup.numDeletes;
        syncresult.stats.numInserts = syncstatsbackup.numInserts;
        syncresult.stats.numSkippedEntries = syncstatsbackup.numSkippedEntries;
        syncresult.stats.numUpdates = syncstatsbackup.numUpdates;
    }
}
