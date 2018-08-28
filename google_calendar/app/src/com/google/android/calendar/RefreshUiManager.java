// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import com.google.android.calendar.v2a.UnifiedSyncTracker;
import com.google.android.syncadapters.calendar.SyncProgressTracker;
import java.util.Set;

public final class RefreshUiManager
    implements com.google.android.syncadapters.calendar.SyncProgressTracker.SyncProgressCallback
{

    public static RefreshUiManager instance;
    private boolean apiarySyncPending;
    public String finishRefreshLabel;
    public String startRefreshLabel;
    public SwipeRefreshLayout swipeRefreshLayout;
    private boolean syncError;
    private UnifiedSyncTracker unifiedSyncTracker;

    public RefreshUiManager()
    {
    }

    private final void onSyncStopped()
    {
        this;
        JVM INSTR monitorenter ;
        if (apiarySyncPending) goto _L2; else goto _L1
_L1:
        boolean flag = unifiedSyncTracker.isPendingOrRunning();
        if (!flag) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        boolean flag1 = syncError;
        syncError = false;
        class .Lambda._cls1
            implements Runnable
        {

            private final RefreshUiManager arg$1;
            private final boolean arg$2;

            public final void run()
            {
                RefreshUiManager refreshuimanager = arg$1;
                boolean flag2 = arg$2;
                refreshuimanager.swipeRefreshLayout.announceForAccessibility(refreshuimanager.finishRefreshLabel);
                refreshuimanager.swipeRefreshLayout.setRefreshing(false);
                if (refreshuimanager.swipeRefreshLayout == null || refreshuimanager.swipeRefreshLayout.getContext() == null)
                {
                    return;
                }
                int i;
                if (flag2)
                {
                    i = 0x7f130192;
                } else
                {
                    i = 0x7f130190;
                }
                Toast.makeText(refreshuimanager.swipeRefreshLayout.getContext(), i, 0).show();
            }

            .Lambda._cls1(boolean flag)
            {
                arg$1 = RefreshUiManager.this;
                arg$2 = flag;
            }
        }

        if (swipeRefreshLayout != null && swipeRefreshLayout.getHandler() != null && swipeRefreshLayout.mRefreshing)
        {
            swipeRefreshLayout.getHandler().post(new .Lambda._cls1(flag1));
        }
        if (true) goto _L2; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    public final void initialize(Context context, SwipeRefreshLayout swiperefreshlayout)
    {
        this;
        JVM INSTR monitorenter ;
        Resources resources = context.getResources();
        startRefreshLabel = resources.getString(0x7f130465);
        finishRefreshLabel = resources.getString(0x7f130222);
        swipeRefreshLayout = swiperefreshlayout;
        swipeRefreshLayout.setColorSchemeResources(new int[] {
            0x7f0d02fa, 0x7f0d02fb, 0x7f0d02fc, 0x7f0d02fd
        });
        synchronized (SyncProgressTracker.callbacks)
        {
            SyncProgressTracker.callbacks.add(this);
        }
        if (unifiedSyncTracker == null)
        {
            unifiedSyncTracker = new UnifiedSyncTracker(context, new _cls1());
        }
        if (SyncProgressTracker.getInstance().hasPendingSyncs())
        {
            swipeRefreshLayout.setRefreshing(true);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        context;
        swiperefreshlayout;
        JVM INSTR monitorexit ;
        throw context;
        context;
        this;
        JVM INSTR monitorexit ;
        throw context;
    }

    public final void onSyncCompleted(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        apiarySyncPending = false;
        syncError = syncError | flag;
        onSyncStopped();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void onSyncPending()
    {
        this;
        JVM INSTR monitorenter ;
        onSyncStarted();
        apiarySyncPending = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final void onSyncStarted()
    {
        this;
        JVM INSTR monitorenter ;
        if (apiarySyncPending || unifiedSyncTracker.isPendingOrRunning() || swipeRefreshLayout == null || swipeRefreshLayout.getHandler() == null) goto _L2; else goto _L1
_L1:
        boolean flag = swipeRefreshLayout.mRefreshing;
        if (!flag) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        class .Lambda._cls0
            implements Runnable
        {

            private final RefreshUiManager arg$1;

            public final void run()
            {
                RefreshUiManager refreshuimanager = arg$1;
                refreshuimanager.swipeRefreshLayout.announceForAccessibility(refreshuimanager.startRefreshLabel);
                refreshuimanager.swipeRefreshLayout.setRefreshing(true);
            }

            .Lambda._cls0()
            {
                arg$1 = RefreshUiManager.this;
            }
        }

        swipeRefreshLayout.getHandler().post(new .Lambda._cls0());
        if (true) goto _L2; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    private class _cls1
        implements com.google.android.calendar.v2a.UnifiedSyncTracker.Listener
    {

        private final RefreshUiManager this$0;

        _cls1()
        {
            this$0 = RefreshUiManager.this;
            super();
        }
    }

}
