// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.ContentResolver;
import android.os.AsyncTask;
import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.api.util.account.CalendarAccountsUtil;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.syncadapters.calendar.SyncProgressTracker;

// Referenced classes of package com.google.android.calendar:
//            RefreshUiManager, Utils, AllInOneCalendarActivity

final class val.authority extends AsyncTask
{

    private final AllInOneCalendarActivity this$0;
    private final String val$authority;

    protected final Object doInBackground(Object aobj[])
    {
        aobj = CalendarAccountsUtil.getSyncableAccounts(AllInOneCalendarActivity.this);
        LogUtils.d("AllInOneCalendarAct", "Refreshing %d accounts", new Object[] {
            Integer.valueOf(aobj.length)
        });
        int j = aobj.length;
        for (int i = 0; i < j; i++)
        {
            Object obj = aobj[i];
            LogUtils.d("AllInOneCalendarAct", "Refreshing calendars for: %s", new Object[] {
                obj
            });
            Bundle bundle = new Bundle();
            bundle.putBoolean("force", true);
            bundle.putBoolean("sync_only_visible", true);
            bundle.putBoolean("expedited", true);
            bundle.putBoolean("do_not_retry", true);
            if (RefreshUiManager.instance == null)
            {
                RefreshUiManager.instance = new RefreshUiManager();
            }
            SyncProgressTracker.getInstance().addPendingSync(((android.accounts.Account) (obj)), bundle);
            if (AccountUtil.isGoogleAccount(((android.accounts.Account) (obj))))
            {
                Utils.appendSyncFlags(bundle);
            }
            String s = val$authority;
            FeatureConfig featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).fishfood_debug();
            ContentResolver.requestSync(((android.accounts.Account) (obj)), s, bundle);
        }

        return null;
    }

    Config()
    {
        this$0 = final_allinonecalendaractivity;
        val$authority = String.this;
        super();
    }
}
