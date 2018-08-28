// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.sync.SyncUtils;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.calendarlist:
//            AccountSyncState, DrawerListAdapter

public class DrawerSyncUIManager
    implements DrawerFragment.OnDrawerClosedListener
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/calendarlist/DrawerSyncUIManager);
    public final DrawerListAdapter adapter;
    public final Context context;
    private boolean loggedDisplay;
    public boolean showedSyncOff;
    public Map stateMap;

    public DrawerSyncUIManager(Context context1, DrawerListAdapter drawerlistadapter)
    {
        stateMap = new HashMap();
        loggedDisplay = false;
        showedSyncOff = false;
        context = context1;
        if (drawerlistadapter == null)
        {
            LogUtils.wtf(TAG, "Null adapter passed to DrawerSyncUIManager.initialize()", new Object[0]);
        }
        adapter = drawerlistadapter;
    }

    public final AccountSyncState getSyncState(Account account)
    {
        AccountSyncState accountsyncstate = (AccountSyncState)stateMap.get(account);
        if (accountsyncstate != null)
        {
            return accountsyncstate;
        }
        if (SyncUtils.isSyncable(account) && !SyncUtils.getSyncAutomatically(account))
        {
            return AccountSyncState.DISABLED;
        } else
        {
            return AccountSyncState.ENABLED;
        }
    }

    public final void onDrawerClosed()
    {
        stateMap.clear();
        if (!loggedDisplay && showedSyncOff)
        {
            loggedDisplay = true;
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "sync_warnings", "sync_off_status_in_drawer", "displayed", null);
        }
    }

    final void updateDrawerOnUiThread()
    {
        Handler handler = new Handler(Looper.getMainLooper());
        DrawerListAdapter drawerlistadapter = adapter;
        drawerlistadapter.getClass();
        class .Lambda._cls1
            implements Runnable
        {

            private final DrawerListAdapter arg$1;

            public final void run()
            {
                arg$1.updateItemList();
            }

            .Lambda._cls1(DrawerListAdapter drawerlistadapter)
            {
                arg$1 = drawerlistadapter;
            }
        }

        handler.post(new .Lambda._cls1(drawerlistadapter));
    }

}
