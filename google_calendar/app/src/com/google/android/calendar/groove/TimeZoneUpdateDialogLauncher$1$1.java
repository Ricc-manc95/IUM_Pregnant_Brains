// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.accounts.Account;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitFilterOptions;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.groove:
//            TimeZoneUpdateDialogLauncher

final class this._cls1 extends AsyncTaskLoader
{

    private final ount this$1;

    public final Object loadInBackground()
    {
        ArrayList arraylist;
        Account aaccount[];
        int i;
        int j;
        arraylist = new ArrayList();
        LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "Loading accounts for timezone dialog in background.", new Object[0]);
        aaccount = AccountsUtil.getGoogleAccounts(activity);
        j = aaccount.length;
        i = 0;
_L4:
        Account account;
        com.google.android.calendar.api.settings. ;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_425;
        }
        account = aaccount[i];
         = (com.google.android.calendar.api.settings.vity)CalendarApi.Settings.read(account).await();
        com.google.android.calendar.api.settings.Settings settings;
        boolean flag;
        if (.us().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        settings = .ings();
        GoogleSettings googlesettings;
        if (settings instanceof GoogleSettings)
        {
            googlesettings = (GoogleSettings)settings;
        } else
        {
            googlesettings = null;
        }
        if (googlesettings != null) goto _L3; else goto _L2
_L2:
        LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "No readSettingsResult for account %s", new Object[] {
            account.name
        });
_L5:
        i++;
          goto _L4
_L3:
        Object obj = .ings();
        if (obj instanceof GoogleSettings)
        {
            obj = (GoogleSettings)obj;
        } else
        {
            obj = null;
        }
        obj = ((GoogleSettings) (obj)).getTimezoneId();
        if (obj == null)
        {
            LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "Server timezone for account %s is null", new Object[] {
                account.name
            });
            arraylist.add(account);
        } else
        {
            long l = TimeZone.getTimeZone(((String) (obj))).getOffset(now);
            LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "Account: %s, Server timezone: %s (%d), Current timezone %s (%d)", new Object[] {
                account.name, obj, Long.valueOf(l), currentTimeZoneId, Long.valueOf(currentDisplayOffset)
            });
            if (currentDisplayOffset != l)
            {
                Object obj1 = CalendarApi.Habits;
                HabitFilterOptions habitfilteroptions = new HabitFilterOptions(account.name);
                long l1;
                if (Clock.mockedTimestamp > 0L)
                {
                    l1 = Clock.mockedTimestamp;
                } else
                {
                    l1 = System.currentTimeMillis();
                }
                habitfilteroptions.activeAfterFilter = Long.valueOf(l1);
                obj1 = (com.google.android.calendar.api.habit.ter)((HabitClient) (obj1)).count(habitfilteroptions).await();
                LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "Active habits on account %s: %d", new Object[] {
                    account.name, Integer.valueOf(((com.google.android.calendar.api.habit.ter) (obj1)).t())
                });
                if (((com.google.android.calendar.api.habit.t) (obj1)).t() > 0)
                {
                    arraylist.add(account);
                }
            }
        }
          goto _L5
        return arraylist;
          goto _L5
    }

    I(Context context)
    {
        this$1 = this._cls1.this;
        super(context);
    }
}
