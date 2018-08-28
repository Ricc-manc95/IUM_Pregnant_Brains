// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.accounts.Account;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.sync.SyncUtils;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.calendarlist:
//            SelectCalendarsAdapter, DrawerSyncUIManager, AccountSyncState

final class val.account
    implements android.view.st.SelectCalendarsAdapter._cls1
{

    private final SelectCalendarsAdapter this$0;
    private final Account val$account;

    public final void onClick(View view)
    {
        view = getDrawerSyncUIManager();
        Object obj = val$account;
        ((DrawerSyncUIManager) (view)).stateMap.put(obj, AccountSyncState.SYNCING);
        Object obj1 = new Handler(Looper.getMainLooper());
        DrawerListAdapter drawerlistadapter = ((DrawerSyncUIManager) (view)).adapter;
        drawerlistadapter.getClass();
        ((Handler) (obj1)).post(new da._cls1(drawerlistadapter));
        obj1 = new da._cls0(view, ((Account) (obj)));
        if (SyncUtils.isSyncable(((Account) (obj))))
        {
            SyncUtils.enableAutomaticSyncAndSyncNow(((DrawerSyncUIManager) (view)).context, ((Account) (obj)), false, ((com.google.android.apps.calendar.util.function.Consumer) (obj1)));
        } else
        {
            LogUtils.e(DrawerSyncUIManager.TAG, "Cannot start a sync for a non-syncable account...", new Object[0]);
        }
        view = SelectCalendarsAdapter.this;
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)obj).trackEvent(((SelectCalendarsAdapter) (view)).mContext, "sync_warnings", "sync_off_status_in_drawer", "enabled", null);
            return;
        }
    }

    da._cls0()
    {
        this$0 = final_selectcalendarsadapter;
        val$account = Account.this;
        super();
    }
}
