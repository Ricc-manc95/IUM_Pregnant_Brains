// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.RemoteException;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.api.util.account.CalendarAccountsUtil;
import com.google.android.apps.calendar.config.remote.HtcMailIssueResyncFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.syncadapters.timely.sql.ColumnConstants;
import com.google.android.apps.calendar.syncadapters.timely.sql.SQLiteDatabaseUtils;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            SyncLog, CalendarSyncState, AnalyticsLoggerExtensionFactory, SyncAnalyticsLoggerExtension

class HtcMailIssueResyncTrigger
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/HtcMailIssueResyncTrigger);

    HtcMailIssueResyncTrigger()
    {
    }

    private static boolean hasDesiredPackage(Context context)
    {
        context = context.getPackageManager();
        int i;
        int j;
        try
        {
            context = context.getPackageInfo("com.htc.android.mail", 0);
            logInfo(String.format(null, "Package (v: %s, min: %s)", new Object[] {
                Integer.valueOf(((PackageInfo) (context)).versionCode), Integer.valueOf(RemoteFeatureConfig.RESYNC.minVersionCode())
            }));
            i = ((PackageInfo) (context)).versionCode;
            j = RemoteFeatureConfig.RESYNC.minVersionCode();
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return false;
        }
        return i >= j;
    }

    private static boolean hasOldCalendars(Account account, ContentProviderClient contentproviderclient)
    {
        Object obj = null;
        String s = String.valueOf(String.format(null, "CAST(%s as INTEGER)", new Object[] {
            "cal_sync8"
        })).concat(" < ?");
        android.net.Uri uri = android.provider.CalendarContract.Calendars.CONTENT_URI;
        s = SQLiteDatabaseUtils.makeWhere(new String[] {
            ColumnConstants.WHERE_ACCOUNT_AND_TYPE, s
        });
        String s1 = account.name;
        account = account.type;
        com.google.android.apps.calendar.config.remote.HtcMailIssueResyncFeature.Memoized memoized = RemoteFeatureConfig.RESYNC.minTime;
        if (memoized.value == null)
        {
            memoized.value = memoized.provider.provide();
        }
        long l = ((Long)memoized.value).longValue();
        contentproviderclient = contentproviderclient.query(uri, new String[] {
            "_count"
        }, s, new String[] {
            s1, account, String.valueOf(l)
        }, null);
        if (contentproviderclient != null)
        {
            break MISSING_BLOCK_LABEL_168;
        }
        account = contentproviderclient;
        logInfo("Error getting cursor");
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return true;
        account = contentproviderclient;
        if (contentproviderclient.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_198;
        }
        account = contentproviderclient;
        logInfo("Unexpected empty cursor");
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return true;
        account = contentproviderclient;
        if (contentproviderclient.getInt(0) <= 0)
        {
            break MISSING_BLOCK_LABEL_250;
        }
        account = contentproviderclient;
        logInfo(String.format(null, "Found old calendars: %s", new Object[] {
            Integer.valueOf(contentproviderclient.getInt(0))
        }));
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return true;
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return false;
        obj;
        contentproviderclient = null;
_L4:
        account = contentproviderclient;
        logError(((Throwable) (obj)), "Error getting cursor");
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return true;
        account;
        contentproviderclient = ((ContentProviderClient) (obj));
_L2:
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        throw account;
        obj;
        contentproviderclient = account;
        account = ((Account) (obj));
        if (true) goto _L2; else goto _L1
_L1:
        obj;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static boolean hasOldEventsOnPrimary(Account account, ContentProviderClient contentproviderclient)
    {
        Object obj = null;
        android.net.Uri uri = android.provider.CalendarContract.Events.CONTENT_URI;
        String s = SQLiteDatabaseUtils.makeWhere(new String[] {
            ColumnConstants.WHERE_ACCOUNT_AND_TYPE, "ownerAccount = ?", "sync_data5 < ?"
        });
        String s1 = account.name;
        String s2 = account.type;
        account = account.name;
        Object obj1 = RemoteFeatureConfig.RESYNC.minUpdated;
        if (((com.google.android.apps.calendar.config.remote.HtcMailIssueResyncFeature.Memoized) (obj1)).value == null)
        {
            obj1.value = ((com.google.android.apps.calendar.config.remote.HtcMailIssueResyncFeature.Memoized) (obj1)).provider.provide();
        }
        obj1 = (String)((com.google.android.apps.calendar.config.remote.HtcMailIssueResyncFeature.Memoized) (obj1)).value;
        contentproviderclient = contentproviderclient.query(uri, new String[] {
            "_count"
        }, s, new String[] {
            s1, s2, account, obj1
        }, null);
        if (contentproviderclient != null)
        {
            break MISSING_BLOCK_LABEL_153;
        }
        account = contentproviderclient;
        logInfo("Error getting cursor");
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return true;
        account = contentproviderclient;
        if (contentproviderclient.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_183;
        }
        account = contentproviderclient;
        logInfo("Unexpected empty cursor");
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return true;
        account = contentproviderclient;
        if (contentproviderclient.getInt(0) <= 0)
        {
            break MISSING_BLOCK_LABEL_235;
        }
        account = contentproviderclient;
        logInfo(String.format(null, "Found old events: %s", new Object[] {
            Integer.valueOf(contentproviderclient.getInt(0))
        }));
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return true;
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return false;
        obj;
        contentproviderclient = null;
_L4:
        account = contentproviderclient;
        logError(((Throwable) (obj)), "Error getting cursor");
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return true;
        account;
        contentproviderclient = ((ContentProviderClient) (obj));
_L2:
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        throw account;
        obj;
        contentproviderclient = account;
        account = ((Account) (obj));
        if (true) goto _L2; else goto _L1
_L1:
        obj;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static boolean hasUnaffectedPcSyncCalendar(ContentProviderClient contentproviderclient)
    {
        Object obj;
        obj = android.provider.CalendarContract.Calendars.CONTENT_URI;
        String s = SQLiteDatabaseUtils.makeWhere(new String[] {
            "account_type=?", "cal_sync1 IS NULL"
        });
        obj = contentproviderclient.query(((android.net.Uri) (obj)), new String[] {
            "_count"
        }, s, new String[] {
            "com.htc.pcsc"
        }, null);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        contentproviderclient = ((ContentProviderClient) (obj));
        logInfo("Error getting cursor");
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        return true;
        contentproviderclient = ((ContentProviderClient) (obj));
        if (((Cursor) (obj)).moveToFirst())
        {
            break MISSING_BLOCK_LABEL_101;
        }
        contentproviderclient = ((ContentProviderClient) (obj));
        logInfo("Unexpected empty cursor");
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        return true;
        contentproviderclient = ((ContentProviderClient) (obj));
        if (((Cursor) (obj)).getInt(0) <= 0)
        {
            break MISSING_BLOCK_LABEL_153;
        }
        contentproviderclient = ((ContentProviderClient) (obj));
        logInfo(String.format(null, "Found unaffected pc sync calendars: %s", new Object[] {
            Integer.valueOf(((Cursor) (obj)).getInt(0))
        }));
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        return true;
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        return false;
        RemoteException remoteexception;
        remoteexception;
        obj = null;
_L4:
        contentproviderclient = ((ContentProviderClient) (obj));
        logError(remoteexception, "Error getting cursor");
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        return true;
        Exception exception;
        exception;
        contentproviderclient = null;
_L2:
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        throw exception;
        exception;
        if (true) goto _L2; else goto _L1
_L1:
        remoteexception;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static void logError(Throwable throwable, String s)
    {
        String s1 = TAG;
        if (SyncLog.WRITE_SYNC_HISTORY_TO_FILES)
        {
            SyncLog.logInternal(SyncLog.syncHistorySessionLog, new String[] {
                s1, " ", s
            });
        }
        SyncLog.logError(throwable, s);
    }

    private static void logInfo(String s)
    {
        String s1 = TAG;
        if (SyncLog.WRITE_SYNC_HISTORY_TO_FILES)
        {
            SyncLog.logInternal(SyncLog.syncHistorySessionLog, new String[] {
                s1, " ", s
            });
        }
        SyncLog.logToSyncLog(new String[] {
            TAG, " ", s
        });
    }

    public static void process(CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
    {
        if (calendarsyncstate.getHtcMailIssueRecoveryStage() >= RemoteFeatureConfig.RESYNC.stage())
        {
            break MISSING_BLOCK_LABEL_379;
        }
        if (!RemoteFeatureConfig.RESYNC.log())
        {
            break MISSING_BLOCK_LABEL_119;
        }
        String s;
        s = "check";
        try
        {
            if (AnalyticsLoggerExtensionFactory.analyticsLogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLoggerExtensionFactory#initialize() must be called first"));
            }
        }
        // Misplaced declaration of an exception variable
        catch (CalendarSyncState calendarsyncstate)
        {
            logError(calendarsyncstate, "Failed to process, skipping");
            context = calendarsyncstate.getClass().getSimpleName();
            if (AnalyticsLoggerExtensionFactory.analyticsLogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLoggerExtensionFactory#initialize() must be called first"));
            }
            break MISSING_BLOCK_LABEL_342;
        }
        SyncAnalyticsLoggerExtension syncanalyticsloggerextension;
        long l;
        syncanalyticsloggerextension = AnalyticsLoggerExtensionFactory.analyticsLogger;
        l = RemoteFeatureConfig.RESYNC.stage();
        if ("check" == null)
        {
            s = "";
        }
        syncanalyticsloggerextension.trackEvent("Consistency", "b38085245", s, l, null);
        if (!shouldTrigger(context, account, contentproviderclient))
        {
            break MISSING_BLOCK_LABEL_283;
        }
        LogUtils.d(TAG, "Trigger active", new Object[0]);
        if (!RemoteFeatureConfig.RESYNC.log())
        {
            break MISSING_BLOCK_LABEL_225;
        }
        LogUtils.d(TAG, "Logging...", new Object[0]);
        context = "trigger";
        if (AnalyticsLoggerExtensionFactory.analyticsLogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLoggerExtensionFactory#initialize() must be called first"));
        }
        account = AnalyticsLoggerExtensionFactory.analyticsLogger;
        l = RemoteFeatureConfig.RESYNC.stage();
        if ("trigger" == null)
        {
            context = "";
        }
        account.trackEvent("Consistency", "b38085245", context, l, null);
        context = RemoteFeatureConfig.RESYNC.reset;
        if (((com.google.android.apps.calendar.config.remote.HtcMailIssueResyncFeature.Memoized) (context)).value == null)
        {
            context.value = ((com.google.android.apps.calendar.config.remote.HtcMailIssueResyncFeature.Memoized) (context)).provider.provide();
        }
        if (((Boolean)((com.google.android.apps.calendar.config.remote.HtcMailIssueResyncFeature.Memoized) (context)).value).booleanValue())
        {
            LogUtils.d(TAG, "Resetting...", new Object[0]);
            calendarsyncstate.reset();
        }
        int i = RemoteFeatureConfig.RESYNC.stage();
        calendarsyncstate.data.put("b38085245", i);
_L1:
        android.provider.SyncStateContract.Helpers.update(contentproviderclient, calendarsyncstate.uri, calendarsyncstate.data.toString().getBytes());
        return;
        context;
        LogUtils.e("CalendarSyncAdapter", context, "Failed to set stage.", new Object[0]);
          goto _L1
        contentproviderclient = AnalyticsLoggerExtensionFactory.analyticsLogger;
        long l1 = RemoteFeatureConfig.RESYNC.stage();
        calendarsyncstate = context;
        if (context == null)
        {
            calendarsyncstate = "";
        }
        contentproviderclient.trackEvent("Consistency", "b38085245", calendarsyncstate, l1, null);
    }

    private static boolean shouldTrigger(Context context, Account account, ContentProviderClient contentproviderclient)
    {
        Account aaccount[];
        int i;
        int j;
        if (!RemoteFeatureConfig.RESYNC.enabled())
        {
            logInfo("Feature disabled, skipping");
            return false;
        }
        if (android.os.Build.VERSION.SDK_INT < RemoteFeatureConfig.RESYNC.minAffectedSdk())
        {
            logInfo(String.format(null, "SDK too low, skipping (%s < %s)", new Object[] {
                Integer.valueOf(android.os.Build.VERSION.SDK_INT), Integer.valueOf(RemoteFeatureConfig.RESYNC.minAffectedSdk())
            }));
            return false;
        }
        if (android.os.Build.VERSION.SDK_INT > RemoteFeatureConfig.RESYNC.maxAffectedSdk())
        {
            logInfo(String.format(null, "SDK too high, skipping (%s > %s)", new Object[] {
                Integer.valueOf(android.os.Build.VERSION.SDK_INT), Integer.valueOf(RemoteFeatureConfig.RESYNC.maxAffectedSdk())
            }));
            return false;
        }
        aaccount = CalendarAccountsUtil.getSyncableAccounts(context);
        j = aaccount.length;
        i = 0;
_L8:
        if (i >= j) goto _L2; else goto _L1
_L1:
        if (!"com.htc.android.mail.eas".equals(aaccount[i].type)) goto _L4; else goto _L3
_L3:
        i = 1;
_L6:
        if (i == 0)
        {
            logInfo(String.format(null, "No required account found, skipping (type: %s)", new Object[] {
                "com.htc.android.mail.eas"
            }));
            return false;
        }
        break; /* Loop/switch isn't completed */
_L4:
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        i = 0;
        if (true) goto _L6; else goto _L5
_L5:
        if (hasUnaffectedPcSyncCalendar(contentproviderclient))
        {
            logInfo("Has unaffected pc sync calendar, skipping");
            return false;
        }
        if (!hasDesiredPackage(context))
        {
            logInfo(String.format(null, "No required package found, skipping (id: %s)", new Object[] {
                "com.htc.android.mail"
            }));
            return false;
        }
        if (hasOldCalendars(account, contentproviderclient))
        {
            logInfo("Has old synced calendars, skipping");
            return false;
        }
        if (hasOldEventsOnPrimary(account, contentproviderclient))
        {
            logInfo("Has old synced events, skipping");
            return false;
        }
        return true;
        if (true) goto _L8; else goto _L7
_L7:
    }

}
