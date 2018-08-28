// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            ObsoleteDataCleaner

public class ObsoleteDataCleanerBroadcastReceiver extends BroadcastReceiver
{

    public ObsoleteDataCleanerBroadcastReceiver()
    {
    }

    public void onReceive(Context context, final Intent pendingResult)
    {
        if (!RemoteFeatureConfig.ORPHAN_EVENTS_DATA_CLEANUP.enabled())
        {
            LogUtils.v("ObsoleteDataCleanerBR", "Received broadcast message. Feature disabled. Exiting early..", new Object[0]);
            return;
        }
        pendingResult = goAsync();
        context = ObsoleteDataCleaner.maybeRemoveAllObsoleteData(context);
        pendingResult = new _cls1();
        CalendarExecutor calendarexecutor = CalendarExecutor.BACKGROUND;
        if (pendingResult == null)
        {
            throw new NullPointerException();
        } else
        {
            context.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(context, pendingResult), calendarexecutor);
            return;
        }
    }

    private class _cls1
        implements FutureCallback
    {

        private final android.content.BroadcastReceiver.PendingResult val$pendingResult;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.e("ObsoleteDataCleanerBR", throwable, "Obsolete data removal failed.", new Object[0]);
            pendingResult.finish();
        }

        public final void onSuccess(Object obj)
        {
            LogUtils.d("ObsoleteDataCleanerBR", "Obsolete data removal succeeded.", new Object[0]);
            pendingResult.finish();
        }

        _cls1()
        {
            pendingResult = pendingresult;
            super();
        }
    }

}
