// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.syncadapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.content.SyncStats;
import android.database.SQLException;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Process;

public abstract class LoggingThreadedSyncAdapter extends AbstractThreadedSyncAdapter
{

    public LoggingThreadedSyncAdapter(Context context, boolean flag)
    {
        super(context, false);
    }

    public abstract void onLogSyncDetails$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKKIICC5N68SJFD5I2UORFDPQ6ARJK5T9NIRJ3A9IN6TBCEGTIILG_0(long l, long l1);

    public abstract void onPerformLoggedSync(Account account, Bundle bundle, String s, ContentProviderClient contentproviderclient, SyncResult syncresult);

    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentproviderclient, SyncResult syncresult)
    {
        int i;
        long l;
        long l1;
        i = Process.myUid();
        l = TrafficStats.getUidTxBytes(i);
        l1 = TrafficStats.getUidRxBytes(i);
        onPerformLoggedSync(account, bundle, s, contentproviderclient, syncresult);
        onLogSyncDetails$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKKIICC5N68SJFD5I2UORFDPQ6ARJK5T9NIRJ3A9IN6TBCEGTIILG_0(TrafficStats.getUidTxBytes(i) - l, TrafficStats.getUidRxBytes(i) - l1);
        return;
        account;
        account = syncresult.stats;
        account.numParseExceptions = ((SyncStats) (account)).numParseExceptions + 1L;
        onLogSyncDetails$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKKIICC5N68SJFD5I2UORFDPQ6ARJK5T9NIRJ3A9IN6TBCEGTIILG_0(TrafficStats.getUidTxBytes(i) - l, TrafficStats.getUidRxBytes(i) - l1);
        return;
        account;
        onLogSyncDetails$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKKIICC5N68SJFD5I2UORFDPQ6ARJK5T9NIRJ3A9IN6TBCEGTIILG_0(TrafficStats.getUidTxBytes(i) - l, TrafficStats.getUidRxBytes(i) - l1);
        throw account;
    }
}
