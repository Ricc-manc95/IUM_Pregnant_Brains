// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.Loader;
import com.android.calendarcommon2.LogUtils;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.groove:
//            TimeZoneUpdateDialogLauncher

public final class val.currentDisplayOffset
    implements android.support.v4.app.gLauncher._cls1
{

    private final TimeZoneUpdateDialogLauncher this$0;
    public final FragmentActivity val$activity;
    public final long val$currentDisplayOffset;
    public final String val$currentTimeZoneId;
    public final long val$now;

    public final Loader onCreateLoader$514KOOBECHP6UQB45TNN6BQ2ELN68R357CKKOOBECHP6UQB45TPNAS3GDTP78BRM6GNM6RREEHIMST1F9HNM2P35E8TG____0(Bundle bundle)
    {
        class _cls1 extends AsyncTaskLoader
        {

            private final TimeZoneUpdateDialogLauncher._cls1 this$1;

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
                com.google.android.calendar.api.settings.SettingsClient.ReadResult readresult;
                if (i >= j)
                {
                    break MISSING_BLOCK_LABEL_425;
                }
                account = aaccount[i];
                readresult = (com.google.android.calendar.api.settings.SettingsClient.ReadResult)CalendarApi.Settings.read(account).await();
                com.google.android.calendar.api.settings.Settings settings;
                boolean flag;
                if (readresult.getStatus().zzaEP <= 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag) goto _L2; else goto _L1
_L1:
                settings = readresult.getSettings();
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
                Object obj = readresult.getSettings();
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
                        obj1 = (com.google.android.calendar.api.habit.HabitClient.GenericResult)((HabitClient) (obj1)).count(habitfilteroptions).await();
                        LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "Active habits on account %s: %d", new Object[] {
                            account.name, Integer.valueOf(((com.google.android.calendar.api.habit.HabitClient.GenericResult) (obj1)).getCount())
                        });
                        if (((com.google.android.calendar.api.habit.HabitClient.GenericResult) (obj1)).getCount() > 0)
                        {
                            arraylist.add(account);
                        }
                    }
                }
                  goto _L5
                return arraylist;
                  goto _L5
            }

            _cls1(Context context)
            {
                this$1 = TimeZoneUpdateDialogLauncher._cls1.this;
                super(context);
            }
        }

        return new _cls1(val$activity);
    }

    public final void onLoadFinished(Loader loader, Object obj)
    {
        loader = (List)obj;
        LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "AccountsUtil to update: %d", new Object[] {
            Integer.valueOf(loader.size())
        });
        if (!loader.isEmpty())
        {
            if (val$activity != null && !onSaveInstanceStateCalled)
            {
                LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "Showing timezone dialog", new Object[0]);
                meZoneUpdateDialog.show(val$activity, (ArrayList)loader, val$currentTimeZoneId);
            }
        } else
        {
            LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "No accounts to update. Saving currentTimezone %s as last displayed.", new Object[] {
                val$currentTimeZoneId
            });
            loader = val$activity;
            obj = val$currentTimeZoneId;
            loader.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().ing("preferences_last_display_tz", ((String) (obj))).ing();
            (new BackupManager(loader)).dataChanged();
        }
        inProgress = false;
    }

    public final void onLoaderReset$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5THMURJKCLN78BQCDTGM8PBI7CKLC___0()
    {
    }

    public meZoneUpdateDialog()
    {
        this$0 = final_timezoneupdatedialoglauncher;
        val$activity = fragmentactivity;
        val$now = l;
        val$currentTimeZoneId = s;
        val$currentDisplayOffset = J.this;
        super();
    }
}
