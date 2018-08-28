// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.sync;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.syncadapters.calendar.SyncProgressTracker;

public class SyncUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/sync/SyncUtils);

    public SyncUtils()
    {
    }

    public static void enableAutomaticSyncAndSyncNow(Context context, Account account, boolean flag, final Consumer onSyncCompleted)
    {
        if (flag)
        {
            SyncProgressTracker.getInstance().addPendingSync(account, new Bundle());
        }
        if (onSyncCompleted != null)
        {
            SyncProgressTracker.getInstance().addPendingIndividualSync(account, new _cls1());
        }
        ContentResolver.setSyncAutomatically(account, "com.android.calendar", true);
    }

    public static boolean getSyncAutomatically(Account account)
    {
        return ContentResolver.getSyncAutomatically(account, "com.android.calendar");
    }

    public static boolean isSyncable(Account account)
    {
        return ContentResolver.getIsSyncable(account, "com.android.calendar") > 0;
    }


    private class _cls1
        implements com.google.android.syncadapters.calendar.SyncProgressTracker.SyncProgressCallback
    {

        private final Consumer val$onSyncCompleted;

        public final void onSyncCompleted(boolean flag)
        {
            onSyncCompleted.accept(Boolean.valueOf(flag));
        }

        public final void onSyncPending()
        {
        }

        _cls1()
        {
            onSyncCompleted = consumer;
            super();
        }
    }

}
