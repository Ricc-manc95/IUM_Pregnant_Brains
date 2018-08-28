// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.syncadapters.timely.groovesync;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.content.SyncResult;
import android.content.SyncStats;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.AuthenticationException;
import com.google.android.apiary.ParseException;
import com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.syncadapters.timely.sql.ColumnConstants;
import com.google.android.apps.calendar.timely.store.GrooveStore;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.CalendarApiFactory;
import com.google.android.calendar.api.CalendarApiFactoryImpl;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitApiStoreImpl;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitFilterOptions;
import com.google.android.calendar.api.habit.HabitIdTypeUtil;
import com.google.android.calendar.api.habit.HabitSyncUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.syncadapters.calendar.AnalyticsLoggerExtensionFactory;
import com.google.android.syncadapters.calendar.CalendarRequestExecutorBase;
import com.google.android.syncadapters.calendar.ProviderHelper;
import com.google.android.syncadapters.calendar.SyncAnalyticsLoggerExtension;
import com.google.android.syncadapters.calendar.SyncHooksContext;
import com.google.android.syncadapters.calendar.SyncLog;
import com.google.android.syncadapters.calendar.SyncProgressTracker;
import com.google.android.syncadapters.calendar.Utilities;
import com.google.android.syncadapters.calendar.timely.contract.SyncHooks;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventHabitInstance;
import com.google.api.services.calendar.model.HabitInstanceData;
import com.google.api.services.calendar.model.Habits;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import com.google.calendar.v2a.android.util.metric.SyncOperation;
import com.google.common.base.Platform;
import com.google.common.base.Supplier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.syncadapters.timely.groovesync:
//            SyncLoggingHabitApi

public class GrooveSync
    implements SyncHooks
{
    public static class AccountSyncState extends GenericJson
    {

        public Map calendars;
        public int version;

        public AccountSyncState()
        {
            version = 1;
            calendars = new HashMap();
            super.jsonFactory = GrooveSync.JSON_FACTORY;
        }
    }

    public static final class AccountSyncState.CalendarSyncState extends GenericJson
    {

        public Long lastSynced;
        public String nextSyncToken;
        public int version;

        public AccountSyncState.CalendarSyncState()
        {
            version = 1;
            super.jsonFactory = GrooveSync.JSON_FACTORY;
        }
    }


    public static final JsonFactory JSON_FACTORY = new AndroidJsonFactory();
    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/syncadapters/timely/groovesync/GrooveSync);
    private SyncAnalyticsLoggerExtension analyticsLogger;
    private Context context;
    private final HashSet deferredEventIds = new HashSet();
    private final SyncLoggingHabitApi habitAPI = new SyncLoggingHabitApi();
    private final HashSet habitIdsAffectedBySync = new HashSet();
    private final HashSet habitIdsOfSyncedInstances = new HashSet();
    private final ArrayList habitIdsOfSyncedParents = new ArrayList();
    private CalendarRequestExecutorBase requestExecutor;
    private GrooveStore store;
    private Long trackedSyncStartMillis;

    public GrooveSync()
    {
    }

    private final void broadcastHabitSyncCompleteIfNecessary(ContentProviderClient contentproviderclient, Account account, Bundle bundle, String s)
    {
        if (isCalendarPrimary(contentproviderclient, account, s))
        {
            if ((contentproviderclient = bundle.getString("force_sync_tracking_groove_id")) != null || !habitIdsAffectedBySync.isEmpty() || !deferredEventIds.isEmpty())
            {
                account = (new Intent("com.google.android.calendar.intent.action.GROOVE_SYNCED")).putExtra("account", account).putExtra("calendarId", s).putExtra("idsOfParentsAffected", (String[])habitIdsAffectedBySync.toArray(new String[habitIdsAffectedBySync.size()])).putExtra("parentIdsOfInstancesSynced", (String[])habitIdsOfSyncedInstances.toArray(new String[habitIdsOfSyncedInstances.size()])).putExtra("idsOfDeferredEvents", (String[])deferredEventIds.toArray(new String[deferredEventIds.size()])).putExtra("groove_operation", bundle.getInt("groove_operation", 0));
                if (contentproviderclient != null && bundle.getBoolean("force_sync_log_time", false))
                {
                    account.putExtra("force_sync_log_time", true);
                    account.putExtra("force_sync_tracking_groove_id", contentproviderclient);
                    account.putExtra("force_sync_create_success", habitIdsOfSyncedInstances.contains(contentproviderclient));
                    if (trackedSyncStartMillis != null)
                    {
                        long l;
                        if (Clock.mockedTimestamp > 0L)
                        {
                            l = Clock.mockedTimestamp;
                        } else
                        {
                            l = System.currentTimeMillis();
                        }
                        account.putExtra("tracked_sync_duration", l - trackedSyncStartMillis.longValue());
                        trackedSyncStartMillis = null;
                    }
                    contentproviderclient = bundle.getString("force_sync_instance_tracking_id");
                    account.putExtra("force_sync_instance_tracking_id", contentproviderclient);
                    account.putExtra("force_sync_defer_success", deferredEventIds.contains(contentproviderclient));
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(account);
                return;
            }
        }
    }

    private final void broadcastUpSyncCompleteIfNecessary(Account account, String s, Bundle bundle)
    {
        String s1 = bundle.getString("upsync_tracking_id");
        if (s1 != null)
        {
            Intent intent = new Intent("com.google.android.calendar.intent.action.GROOVE_REQUEST_UPSYNCED");
            intent.putExtra("upsync_tracking_id", s1).putExtra("account", account).putExtra("calendarId", s);
            account = bundle.getString("upsync_instance_tracking_id");
            if (account != null)
            {
                intent.putExtra("upsync_instance_tracking_id", account);
            }
            if (trackedSyncStartMillis != null)
            {
                long l;
                if (Clock.mockedTimestamp > 0L)
                {
                    l = Clock.mockedTimestamp;
                } else
                {
                    l = System.currentTimeMillis();
                }
                intent.putExtra("tracked_sync_duration", l - trackedSyncStartMillis.longValue());
                trackedSyncStartMillis = null;
            }
            intent.putExtra("groove_operation", bundle.getInt("groove_operation", 0));
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    private final AccountSyncState getAccountSyncState(Account account)
    {
        Object obj = null;
        Object obj1 = store.database;
        account = account.name;
        obj1 = ((SQLiteDatabase) (obj1)).query("_sync_state", new String[] {
            "data"
        }, "account_name=?", new String[] {
            account
        }, null, null, null);
        if (((Cursor) (obj1)).getCount() == 0)
        {
            ((Cursor) (obj1)).close();
            account = obj;
        } else
        {
            ((Cursor) (obj1)).moveToFirst();
            account = ((Cursor) (obj1)).getString(0);
            ((Cursor) (obj1)).close();
        }
        if (account == null)
        {
            return new AccountSyncState();
        }
        try
        {
            account = (AccountSyncState)JSON_FACTORY.createJsonParser(account).parse(com/google/android/apps/calendar/syncadapters/timely/groovesync/GrooveSync$AccountSyncState, false, null);
        }
        // Misplaced declaration of an exception variable
        catch (Account account)
        {
            LogUtils.e(TAG, account, "Error loading account sync state", new Object[0]);
            return new AccountSyncState();
        }
        return account;
    }

    private static String getPrimarySyncId(ContentProviderClient contentproviderclient, Account account)
    {
        Object obj;
        String s;
        Object obj2;
        obj2 = null;
        s = null;
        obj = obj2;
        ProviderHelper providerhelper = ProviderHelper.asClient();
        obj = obj2;
        Uri uri = android.provider.CalendarContract.Calendars.CONTENT_URI;
        obj = obj2;
        String s1 = ColumnConstants.WHERE_ACCOUNT_AND_TYPE_AND_IS_PRIMARY_TRUE;
        obj = obj2;
        String s2 = account.name;
        obj = obj2;
        String s3 = account.type;
        obj = obj2;
        contentproviderclient = providerhelper.query(contentproviderclient, uri, new String[] {
            "cal_sync1"
        }, s1, new String[] {
            s2, s3
        }, null);
        if (contentproviderclient == null) goto _L2; else goto _L1
_L1:
        if (!contentproviderclient.moveToFirst() || contentproviderclient.isNull(0)) goto _L2; else goto _L3
_L3:
        obj = contentproviderclient.getString(0);
        account = ((Account) (obj));
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
            account = ((Account) (obj));
        }
_L5:
        return account;
_L2:
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return account.name;
        contentproviderclient;
        contentproviderclient = null;
_L8:
        obj = contentproviderclient;
        s = account.name;
        account = s;
        if (contentproviderclient == null) goto _L5; else goto _L4
_L4:
        contentproviderclient.close();
        return s;
        contentproviderclient;
_L7:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw contentproviderclient;
        account;
        obj = contentproviderclient;
        contentproviderclient = account;
        if (true) goto _L7; else goto _L6
_L6:
        contentproviderclient;
        contentproviderclient = s;
          goto _L8
        Object obj1;
        obj1;
          goto _L8
        obj1;
          goto _L8
    }

    private final boolean hasAnyHabitParentsOrInstances(Account account, ContentProviderClient contentproviderclient)
        throws IOException
    {
        LogUtils.d(TAG, "Checking for parents or instances", new Object[0]);
        if (habitAPI.count(new HabitFilterOptions(account.name)) > 0)
        {
            LogUtils.d(TAG, " ... parents found", new Object[0]);
            return true;
        }
        LogUtils.d(TAG, " ... no parents found", new Object[0]);
        if (HabitSyncUtils.countHabitInstances(contentproviderclient, ColumnConstants.WHERE_ACCOUNT_AND_TYPE, new String[] {
            account.name, "com.google"
        }) <= 0) goto _L2; else goto _L1
_L1:
        LogUtils.d(TAG, " ... instances found", new Object[0]);
        return true;
_L4:
        return false;
_L2:
        try
        {
            LogUtils.d(TAG, " ... no instances found", new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch (Account account)
        {
            LogUtils.d(TAG, " ... could not count the instances", new Object[0]);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static boolean isCalendarPrimary(ContentProviderClient contentproviderclient, Account account, String s)
    {
        Object obj;
        Object obj1;
        obj1 = null;
        obj = null;
        if (!TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        ProviderHelper providerhelper = ProviderHelper.asClient();
        Uri uri = android.provider.CalendarContract.Calendars.CONTENT_URI;
        String s1 = ColumnConstants.WHERE_ACCOUNT_AND_TYPE_AND_CALENDAR_SYNC_ID;
        String s2 = account.name;
        account = account.type;
        contentproviderclient = providerhelper.query(contentproviderclient, uri, new String[] {
            "COALESCE(isPrimary,account_name=ownerAccount) AS isPrimary"
        }, s1, new String[] {
            s2, account, s
        }, null);
        if (contentproviderclient == null) goto _L4; else goto _L3
_L3:
        if (!contentproviderclient.moveToFirst() || contentproviderclient.isNull(0)) goto _L4; else goto _L5
_L5:
        int i = contentproviderclient.getInt(0);
        if (i != 1) goto _L4; else goto _L6
_L6:
        boolean flag = true;
_L8:
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        return flag;
_L4:
        flag = false;
        if (true) goto _L8; else goto _L7
_L7:
        contentproviderclient;
        contentproviderclient = obj;
_L12:
        if (contentproviderclient == null) goto _L1; else goto _L9
_L9:
        contentproviderclient.close();
        return false;
        contentproviderclient;
        account = obj1;
_L11:
        if (account != null)
        {
            account.close();
        }
        throw contentproviderclient;
        s;
        account = contentproviderclient;
        contentproviderclient = s;
        if (true) goto _L11; else goto _L10
_L10:
        account;
          goto _L12
        contentproviderclient;
        contentproviderclient = null;
          goto _L12
        account;
          goto _L12
    }

    private final void registerException(Exception exception, String s, SyncResult syncresult)
    {
        String s1 = String.valueOf(s);
        if (s1.length() != 0)
        {
            s1 = "Exception in ".concat(s1);
        } else
        {
            s1 = new String("Exception in ");
        }
        SyncLog.logError(exception, s1);
        if (exception instanceof HttpResponseException)
        {
            analyticsLogger.logSyncError(s, ((HttpResponseException)exception).statusCode);
        } else
        {
            SyncAnalyticsLoggerExtension syncanalyticsloggerextension = analyticsLogger;
            String s2 = exception.getClass().getSimpleName();
            syncanalyticsloggerextension.addSyncSpecificCustomDimensions();
            syncanalyticsloggerextension.trackEvent("Sync", s, s2, 0L, null);
        }
        if (exception instanceof AuthenticationException)
        {
            exception = syncresult.stats;
            exception.numAuthExceptions = ((SyncStats) (exception)).numAuthExceptions + 1L;
            return;
        }
        if (exception instanceof IOException)
        {
            exception = syncresult.stats;
            exception.numIoExceptions = ((SyncStats) (exception)).numIoExceptions + 1L;
            return;
        } else
        {
            exception = syncresult.stats;
            exception.numParseExceptions = ((SyncStats) (exception)).numParseExceptions + 1L;
            return;
        }
    }

    private final void scheduleHabitsSync(ContentProviderClient contentproviderclient, Account account, Bundle bundle)
    {
        bundle = new Bundle(bundle);
        bundle.remove("feed");
        bundle.remove("force_sync_tracking_groove_id");
        bundle.remove("force_sync_instance_tracking_id");
        bundle.remove("groove_operation");
        bundle.putBoolean("only_groove", true);
        bundle.putString("feed_internal", getPrimarySyncId(contentproviderclient, account));
        if (SyncProgressTracker.getInstance().isPendingAccountSync(account))
        {
            SyncProgressTracker.getInstance().addPendingSync(account, bundle);
        }
        contentproviderclient = Features.instance;
        if (contentproviderclient == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)contentproviderclient).fishfood_debug();
            ContentResolver.requestSync(account, "com.android.calendar", bundle);
            return;
        }
    }

    private final void sendHabitToServer(com.google.api.services.calendar.Calendar.Habits habits, Habit habit, SyncResult syncresult)
    {
        boolean flag = Platform.stringIsNullOrEmpty(habit.getFingerprint());
        if (!flag) goto _L2; else goto _L1
_L1:
        Object obj = habit.getDescriptor();
        Object obj1 = requestExecutor;
        com.google.api.services.calendar.Calendar.Habits.Insert insert = new com.google.api.services.calendar.Calendar.Habits.Insert(habits, ((HabitDescriptor) (obj)).calendar.calendarId, HabitSyncUtils.apiToServerHabit(habit));
        habits.this$0.initialize(insert);
        obj1 = (com.google.api.services.calendar.model.Habit)((CalendarRequestExecutorBase) (obj1)).execute("API: calendar.habits.insert", insert);
        obj = ((HabitDescriptor) (obj)).calendar;
        habitAPI.update(HabitSyncUtils.serverHabitToApi(((CalendarDescriptor) (obj)), ((com.google.api.services.calendar.model.Habit) (obj1))), null, false);
        obj = syncresult.stats;
        obj.numInserts = ((SyncStats) (obj)).numInserts + 1L;
_L5:
        habits = syncresult.stats;
        habits.numEntries = ((SyncStats) (habits)).numEntries + 1L;
        return;
        HttpResponseException httpresponseexception;
        httpresponseexception;
        if (httpresponseexception.statusCode != 409) goto _L4; else goto _L3
_L3:
        LogUtils.w(TAG, httpresponseexception, "tried to insert already exiting entry, updating instead", new Object[0]);
        updateRemoteHabitThenUpdateLocal(habits, habit);
        habits = syncresult.stats;
        habits.numUpdates = ((SyncStats) (habits)).numUpdates + 1L;
          goto _L5
        habits;
_L6:
        registerException(habits, "sendHabitToServer", syncresult);
        return;
_L4:
        throw httpresponseexception;
_L2:
        updateRemoteHabitThenUpdateLocal(habits, habit);
        habits = syncresult.stats;
        habits.numUpdates = ((SyncStats) (habits)).numUpdates + 1L;
          goto _L5
        habits;
          goto _L6
    }

    private final void updateRemoteHabitThenUpdateLocal(com.google.api.services.calendar.Calendar.Habits habits, Habit habit)
        throws IOException
    {
        HabitDescriptor habitdescriptor = habit.getDescriptor();
        habit = new com.google.api.services.calendar.Calendar.Habits.Patch(habits, habitdescriptor.calendar.calendarId, habitdescriptor.habitId, HabitSyncUtils.apiToServerHabit(habit));
        habits.this$0.initialize(habit);
        habits = (com.google.api.services.calendar.model.Habit)requestExecutor.execute("API: calendar.habits.update", habit);
        habit = habitdescriptor.calendar;
        habitAPI.update(HabitSyncUtils.serverHabitToApi(habit, habits), null, false);
    }

    public final String extractCalendarIdFromSubscriptionUrl(String s)
    {
        boolean flag;
        if (s != null && s.startsWith("http://calendar.google.com/") && s.endsWith("/habits"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return null;
        } else
        {
            return Uri.decode(s.substring(27, s.length() - 7));
        }
    }

    public final String generateSubscriptionUrl(ContentValues contentvalues)
    {
        if (!Integer.valueOf(1).equals(contentvalues.getAsInteger("isPrimary")))
        {
            return null;
        } else
        {
            contentvalues = Uri.encode(contentvalues.getAsString("cal_sync1"));
            return (new StringBuilder(String.valueOf(contentvalues).length() + 34)).append("http://calendar.google.com/").append(contentvalues).append("/habits").toString();
        }
    }

    public final String getHookSyncTypePrefix()
    {
        return "GROOVE";
    }

    public final void initialize(SyncHooksContext synchookscontext)
    {
        context = synchookscontext.context;
        CalendarApiFactory.instance = new CalendarApiFactoryImpl(false, false, false, false);
        CalendarApi.initialize(context);
        GrooveStore.initialize(context);
        GrooveStore groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        store = (GrooveStore)groovestore;
        AnalyticsLoggerExtensionFactory.initialize(context, synchookscontext.analyticsLogger);
        if (AnalyticsLoggerExtensionFactory.analyticsLogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLoggerExtensionFactory#initialize() must be called first"));
        } else
        {
            analyticsLogger = AnalyticsLoggerExtensionFactory.analyticsLogger;
            requestExecutor = synchookscontext.calendarRequestExecutor;
            return;
        }
    }

    public final boolean isHookSpecificSync$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357CKLK___0(Bundle bundle)
    {
label0:
        {
            boolean flag1 = false;
            if (!bundle.getBoolean("only_groove", false))
            {
                boolean flag;
                if (bundle.containsKey("feed"))
                {
                    bundle = bundle.getString("feed");
                } else
                {
                    bundle = bundle.getString("feed_internal");
                }
                if (bundle != null && bundle.startsWith("http://calendar.google.com/") && bundle.endsWith("/habits"))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public final void onAfterConvertEntityToEvent(Entity entity, Event event, boolean flag)
    {
        ContentValues contentvalues = entity.getEntityValues();
        entity = HabitIdTypeUtil.parseHabitIdAndType(contentvalues.getAsString("sync_data8"));
        if (entity == null || entity.length == 0)
        {
            entity = null;
        } else
        {
            entity = entity[0];
        }
        if (!TextUtils.isEmpty(entity))
        {
            HabitInstanceData habitinstancedata = new HabitInstanceData();
            EventHabitInstance eventhabitinstance = new EventHabitInstance();
            eventhabitinstance.data = habitinstancedata;
            eventhabitinstance.parentId = entity;
            entity = EventExtrasFlags.fromExisting(contentvalues, "sync_data9");
            boolean flag1;
            boolean flag3;
            if ((((EventExtrasFlags) (entity)).flags & 0x80) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                habitinstancedata.status = "complete";
            } else
            {
                boolean flag2;
                if ((((EventExtrasFlags) (entity)).flags & 0x100) != 0)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (flag2)
                {
                    habitinstancedata.status = "deferralRequested";
                } else
                {
                    habitinstancedata.status = "active";
                }
            }
            if ((((EventExtrasFlags) (entity)).flags & 0x200) != 0)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
            habitinstancedata.statusInferred = Boolean.valueOf(flag3);
            event.habitInstance = eventhabitinstance;
            if (!flag)
            {
                com.google.api.services.calendar.model.Event.Reminders reminders = event.reminders;
                entity = reminders;
                if (reminders == null)
                {
                    entity = new com.google.api.services.calendar.model.Event.Reminders();
                    entity.overrides = new ArrayList();
                    event.reminders = entity;
                }
                entity.useDefault = Boolean.valueOf(false);
                return;
            }
        }
    }

    public final void onAfterDownSync$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6ARJKA1P6UTJ9CHIN4GRCD5IMST1R9HHMUR9FCTNMUPRCCKNM2S395TPMASJMD5HMASPFCDGMOPBECHGN4BQ3C5M6ARJ4C5P3MJ31DPI74RR9CGNM6RREEHIMST1FADSMSOQICLPNAR3K7CKLC___0(Account account, Bundle bundle, ContentProviderClient contentproviderclient, SyncResult syncresult)
    {
        String s;
        boolean flag;
        if (bundle.containsKey("feed"))
        {
            s = bundle.getString("feed");
        } else
        {
            s = bundle.getString("feed_internal");
        }
        s = Utilities.parseFeedId(s);
        broadcastHabitSyncCompleteIfNecessary(contentproviderclient, account, bundle, s);
        habitIdsOfSyncedInstances.clear();
        habitIdsAffectedBySync.clear();
        habitIdsOfSyncedParents.clear();
        deferredEventIds.clear();
        if (s == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        SyncLog.start("Check for local habits");
        LogUtils.d(TAG, "Determining whether to sync for %s", new Object[] {
            account.name
        });
        if (!hasAnyHabitParentsOrInstances(account, contentproviderclient)) goto _L4; else goto _L3
_L3:
        LogUtils.d(TAG, "Scheduling the hook specific sync", new Object[0]);
        scheduleHabitsSync(contentproviderclient, account, bundle);
_L6:
        SyncLog.stop("Check for local habits");
_L2:
        return;
_L4:
        LogUtils.d(TAG, "Not scheduling the hook specific sync", new Object[0]);
        if (true) goto _L6; else goto _L5
_L5:
        account;
        registerException(account, "hasAnyHabitParentsOrInstances", syncresult);
        SyncLog.stop("Check for local habits");
        return;
        account;
        SyncLog.stop("Check for local habits");
        throw account;
    }

    public final void onAfterUpSync$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6ARJKA1P6UTJ9CHIN4GRCD5IMST1R9HHMUR9FCTNMUPRCCKNM2S395TPMASJMD5HMASPFCDGMOPBECHGN4BQ3C5M6ARJ4C5P3MJ31DPI74RR9CGNM6RREEHIMST1FADSMSOQICLPNAR3K7CKLC___0(Account account, Bundle bundle)
    {
        String s;
        boolean flag;
        if (bundle.containsKey("feed"))
        {
            s = bundle.getString("feed");
        } else
        {
            s = bundle.getString("feed_internal");
        }
        if (s != null && s.startsWith("http://calendar.google.com/") && s.endsWith("/habits"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            s = extractCalendarIdFromSubscriptionUrl(s);
        } else
        {
            s = Utilities.parseFeedId(s);
        }
        broadcastUpSyncCompleteIfNecessary(account, s, bundle);
        habitIdsOfSyncedInstances.clear();
        habitIdsAffectedBySync.clear();
        habitIdsOfSyncedParents.clear();
        deferredEventIds.clear();
    }

    public final void onBeforeApplyEventToEntity(Event event, Entity entity, ContentValues contentvalues)
    {
        String s;
        if (event == null || event.habitInstance == null)
        {
            s = null;
        } else
        {
            s = event.habitInstance.parentId;
        }
        if (s != null)
        {
            habitIdsOfSyncedInstances.add(s);
            habitIdsAffectedBySync.add(s);
            Object obj = EventExtrasFlags.fromExisting(contentvalues, "sync_data9");
            if (event != null && event.habitInstance != null)
            {
                EventHabitInstance eventhabitinstance = event.habitInstance;
                contentvalues.put("sync_data8", HabitSyncUtils.getHabitIdAndTypeString(eventhabitinstance.parentId, eventhabitinstance.data.type));
                obj = new com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder(((EventExtrasFlags) (obj)).flags);
                HabitSyncUtils.setHabitExtrasFlags(((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj)), eventhabitinstance.data.statusInferred, eventhabitinstance.data.status);
                contentvalues.put("sync_data9", Integer.valueOf((new EventExtrasFlags(((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj)).flags)).flags));
            }
            if (event != null && event.habitInstance != null && event.habitInstance.data != null && event.habitInstance.data.status != null && "active".equals(event.habitInstance.data.status) && entity != null)
            {
                boolean flag;
                if ((EventExtrasFlags.fromExisting(entity.getEntityValues(), "sync_data9").flags & 0x100) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    deferredEventIds.add(event.id);
                }
            }
        } else
        if (entity != null && entity.getEntityValues().getAsString("sync_data8") != null)
        {
            contentvalues.put("sync_data8", null);
            return;
        }
    }

    public final void onBeforeDownSync$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6ARJKA1P6UTJ9CHIN4GRCD5IMST1R9HHMUR9FCTNMUPRCCKNM2S395TPMASJMD5HMASPFCDGMOPBECHGN4BQ3C5M6ARJ4C5P3MJ31DPI74RR9CGNM6RREEHIMST1FADSMSOQICLPNAR3K7CKLC___0()
    {
        habitIdsOfSyncedInstances.clear();
        habitIdsAffectedBySync.clear();
        habitIdsOfSyncedParents.clear();
        deferredEventIds.clear();
    }

    public final void onBeforeUpSync$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6ARJKA1P6UTJ9CHIN4GRCD5IMST1R9HHMUR9FCTNMUPRCCKNM2S395TPMASJMD5HMASPFCDGMOPBECHGN4BQ3C5M6ARJ4C5P3MJ31DPI74RR9CGNM6RREEHIMST1FADSMSOQICLPNAR3K7CKLC___0()
    {
        habitIdsOfSyncedInstances.clear();
        habitIdsAffectedBySync.clear();
        habitIdsOfSyncedParents.clear();
        deferredEventIds.clear();
    }

    public final void onInitializeSync(Account account, ContentProviderClient contentproviderclient)
    {
        scheduleHabitsSync(contentproviderclient, account, new Bundle(0));
    }

    public final void onSyncInitiated$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6ARJKA1P6UTJ9CHIN4GRCD5IMST1R9HHMUR9FCTNMUPRCCKNM2S395TPMASJMD5HMASPFCDGMOPBECHGN4BQ3C5M6ARJ4C5P3MJ31DPI74RR9CGNM6RREEHIMST1FADSMSOQICLPNAR3K7CKLC___0(Bundle bundle)
    {
        if (bundle.containsKey("upsync_tracking_id") || bundle.containsKey("force_sync_tracking_groove_id"))
        {
            Intent intent;
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            trackedSyncStartMillis = Long.valueOf(l);
            intent = new Intent("com.google.android.calendar.intent.action.TRACKING_SYNC_INITIATED");
            intent.putExtras(bundle);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    public final void performHookSpecificSync(Account account, Bundle bundle, ContentProviderClient contentproviderclient, Calendar calendar, SyncResult syncresult)
    {
        SyncOperation syncoperation = SyncOperation.HABITS_SYNC;
        class .Lambda._cls0
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls0();

            public final boolean apply(Object obj)
            {
                return ((SyncResult)obj).hasError();
            }


            private .Lambda._cls0()
            {
            }
        }

        class .Lambda._cls1
            implements Supplier
        {

            private final GrooveSync arg$1;
            private final Account arg$2;
            private final Bundle arg$3;
            private final ContentProviderClient arg$4;
            private final Calendar arg$5;
            private final SyncResult arg$6;

            public final Object get()
            {
                return arg$1.syncInternal(arg$2, arg$3, arg$4, arg$5, arg$6);
            }

            .Lambda._cls1(Account account, Bundle bundle, ContentProviderClient contentproviderclient, Calendar calendar, SyncResult syncresult)
            {
                arg$1 = GrooveSync.this;
                arg$2 = account;
                arg$3 = bundle;
                arg$4 = contentproviderclient;
                arg$5 = calendar;
                arg$6 = syncresult;
            }
        }

        MetricUtils.withMetrics(new com.google.common.base.Predicates.NotPredicate(.Lambda._cls0..instance), new .Lambda._cls1(account, bundle, contentproviderclient, calendar, syncresult), syncoperation).get();
    }

    final SyncResult syncInternal(Account account, Bundle bundle, ContentProviderClient contentproviderclient, Calendar calendar, SyncResult syncresult)
    {
        String s;
        boolean flag2;
        LogUtils.d(TAG, "Hook specific sync with extras %s", new Object[] {
            bundle
        });
        boolean flag;
        if (bundle.containsKey("feed"))
        {
            s = bundle.getString("feed");
        } else
        {
            s = bundle.getString("feed_internal");
        }
        if (s != null && s.startsWith("http://calendar.google.com/") && s.endsWith("/habits"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            s = extractCalendarIdFromSubscriptionUrl(s);
        } else
        {
            s = Utilities.parseFeedId(s);
        }
        flag2 = bundle.getBoolean("upload", false);
        habitIdsOfSyncedInstances.clear();
        habitIdsAffectedBySync.clear();
        habitIdsOfSyncedParents.clear();
        deferredEventIds.clear();
        if (isCalendarPrimary(contentproviderclient, account, s)) goto _L2; else goto _L1
_L1:
        LogUtils.e(TAG, "Habits-only sync with not supported options", new Object[0]);
_L6:
        return syncresult;
_L2:
        Object obj2;
        Object obj3;
        obj2 = CalendarDescriptor.createWithoutLocalId(account, s);
        obj3 = new com.google.api.services.calendar.Calendar.Habits(calendar);
        SyncLog.start("Upload Habits to server");
        Object obj1;
        Object obj4;
        LogUtils.v(TAG, "Up-sync Habits for %s/%s", new Object[] {
            ((CalendarDescriptor) (obj2)).account.name, ((CalendarDescriptor) (obj2)).calendarId
        });
        obj4 = new HabitFilterOptions(((CalendarDescriptor) (obj2)).account.name);
        obj4.dirtyFilter = Boolean.valueOf(true);
        obj1 = ((CalendarDescriptor) (obj2)).calendarId;
        Object obj = obj1;
        if (Platform.stringIsNullOrEmpty(((String) (obj1))))
        {
            obj = null;
        }
        Habit ahabit[];
        int k;
        obj4.calendarId = ((String) (obj));
        ahabit = habitAPI.list(((HabitFilterOptions) (obj4)));
        k = ahabit.length;
        int i = 0;
_L4:
        if (i >= k)
        {
            break; /* Loop/switch isn't completed */
        }
        sendHabitToServer(((com.google.api.services.calendar.Calendar.Habits) (obj3)), ahabit[i], syncresult);
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        SyncLog.stop("Upload Habits to server");
_L12:
        broadcastUpSyncCompleteIfNecessary(((CalendarDescriptor) (obj2)).account, ((CalendarDescriptor) (obj2)).calendarId, bundle);
        if (flag2) goto _L6; else goto _L5
_L5:
        ahabit = new com.google.api.services.calendar.Calendar.Habits(calendar);
        SyncLog.start("Download Habits to the local store");
        obj1 = getAccountSyncState(((CalendarDescriptor) (obj2)).account);
        obj3 = ((CalendarDescriptor) (obj2)).calendarId;
        calendar = (AccountSyncState.CalendarSyncState)((AccountSyncState) (obj1)).calendars.get(obj3);
        if (calendar == null)
        {
            calendar = new AccountSyncState.CalendarSyncState();
            ((AccountSyncState) (obj1)).calendars.put(obj3, calendar);
        }
        LogUtils.v(TAG, "Down-sync Habits for %s/%s %s", new Object[] {
            ((CalendarDescriptor) (obj2)).account.name, ((CalendarDescriptor) (obj2)).calendarId, calendar.toString()
        });
        obj3 = new com.google.api.services.calendar.Calendar.Habits.List(ahabit, ((CalendarDescriptor) (obj2)).calendarId);
        ((com.google.api.services.calendar.Calendar.Habits) (ahabit)).this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj3)));
        obj3.syncToken = ((AccountSyncState.CalendarSyncState) (calendar)).nextSyncToken;
_L18:
        ahabit = (Habits)requestExecutor.execute("API: calendar.habits.list", ((com.google.api.services.calendar.CalendarRequest) (obj3)));
_L14:
        obj4 = ((Habits) (ahabit)).items.iterator();
_L11:
        if (!((Iterator) (obj4)).hasNext()) goto _L8; else goto _L7
_L7:
        Object obj5;
        obj5 = (com.google.api.services.calendar.model.Habit)((Iterator) (obj4)).next();
        habitIdsOfSyncedParents.add(((com.google.api.services.calendar.model.Habit) (obj5)).id);
        habitIdsAffectedBySync.add(((com.google.api.services.calendar.model.Habit) (obj5)).id);
        if (((com.google.api.services.calendar.model.Habit) (obj5)).deleted == null || !((com.google.api.services.calendar.model.Habit) (obj5)).deleted.booleanValue()) goto _L10; else goto _L9
_L9:
        obj5 = new HabitDescriptor(((CalendarDescriptor) (obj2)), ((com.google.api.services.calendar.model.Habit) (obj5)).id);
        habitAPI.removeDeleted(((HabitDescriptor) (obj5)));
        obj5 = syncresult.stats;
        obj5.numDeletes = ((SyncStats) (obj5)).numDeletes + 1L;
_L16:
        obj5 = syncresult.stats;
        obj5.numEntries = ((SyncStats) (obj5)).numEntries + 1L;
          goto _L11
        ahabit;
_L19:
        registerException(ahabit, "downSyncHabits", syncresult);
        long l1;
        SyncLog.stop("Download Habits to the local store");
        HabitFilterOptions habitfilteroptions;
        Habit ahabit1[];
        String s1;
        int j;
        boolean flag1;
        int l;
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        calendar.lastSynced = Long.valueOf(l1);
        calendar = ((CalendarDescriptor) (obj2)).account;
        ahabit = store;
        obj1 = ((GenericJson) (obj1)).toString();
        obj2 = new ContentValues(2);
        ((ContentValues) (obj2)).put("account_name", ((Account) (calendar)).name);
        ((ContentValues) (obj2)).put("data", ((String) (obj1)));
        ((GrooveStore) (ahabit)).database.insertWithOnConflict("_sync_state", null, ((ContentValues) (obj2)), 5);
        broadcastHabitSyncCompleteIfNecessary(contentproviderclient, account, bundle, s);
        habitIdsOfSyncedInstances.clear();
        habitIdsAffectedBySync.clear();
        habitIdsOfSyncedParents.clear();
        deferredEventIds.clear();
        return syncresult;
        ahabit;
        registerException(ahabit, "upSyncHabits", syncresult);
        SyncLog.stop("Upload Habits to server");
          goto _L12
        account;
        SyncLog.stop("Upload Habits to server");
        throw account;
        ahabit;
        if (((HttpResponseException) (ahabit)).statusCode != 410)
        {
            break MISSING_BLOCK_LABEL_983;
        }
        LogUtils.w(TAG, ahabit, "Full sync required.", new Object[0]);
        obj3.syncToken = null;
        ahabit = (Habits)requestExecutor.execute("API: calendar.habits.list", ((com.google.api.services.calendar.CalendarRequest) (obj3)));
        habitfilteroptions = new HabitFilterOptions();
        ahabit1 = habitAPI.list(habitfilteroptions);
        l = ahabit1.length;
        j = 0;
_L15:
        if (j >= l) goto _L14; else goto _L13
_L13:
        obj5 = ahabit1[j].getDescriptor();
        habitAPI.removeDeleted(((HabitDescriptor) (obj5)));
        j++;
          goto _L15
          goto _L14
        throw ahabit;
_L10:
        s1 = ((com.google.api.services.calendar.model.Habit) (obj5)).id;
        if (habitAPI.read(new HabitDescriptor(((CalendarDescriptor) (obj2)), s1)) != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_1161;
        }
        habitAPI.create(HabitSyncUtils.serverHabitToApi(((CalendarDescriptor) (obj2)), ((com.google.api.services.calendar.model.Habit) (obj5))), false);
        obj5 = syncresult.stats;
        obj5.numInserts = ((SyncStats) (obj5)).numInserts + 1L;
          goto _L16
        account;
        SyncLog.stop("Download Habits to the local store");
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        calendar.lastSynced = Long.valueOf(l1);
        bundle = ((CalendarDescriptor) (obj2)).account;
        contentproviderclient = store;
        calendar = ((GenericJson) (obj1)).toString();
        syncresult = new ContentValues(2);
        syncresult.put("account_name", ((Account) (bundle)).name);
        syncresult.put("data", calendar);
        ((GrooveStore) (contentproviderclient)).database.insertWithOnConflict("_sync_state", null, syncresult, 5);
        throw account;
        habitAPI.update(HabitSyncUtils.serverHabitToApi(((CalendarDescriptor) (obj2)), ((com.google.api.services.calendar.model.Habit) (obj5))), null, false);
        obj5 = syncresult.stats;
        obj5.numUpdates = ((SyncStats) (obj5)).numUpdates + 1L;
          goto _L16
_L8:
        obj3.pageToken = ((Habits) (ahabit)).nextPageToken;
        if (((com.google.api.services.calendar.Calendar.Habits.List) (obj3)).pageToken != null) goto _L18; else goto _L17
_L17:
        calendar.nextSyncToken = ((Habits) (ahabit)).nextSyncToken;
        SyncLog.stop("Download Habits to the local store");
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        calendar.lastSynced = Long.valueOf(l1);
        calendar = ((CalendarDescriptor) (obj2)).account;
        ahabit = store;
        obj1 = ((GenericJson) (obj1)).toString();
        obj2 = new ContentValues(2);
        ((ContentValues) (obj2)).put("account_name", ((Account) (calendar)).name);
        ((ContentValues) (obj2)).put("data", ((String) (obj1)));
        ((GrooveStore) (ahabit)).database.insertWithOnConflict("_sync_state", null, ((ContentValues) (obj2)), 5);
        break MISSING_BLOCK_LABEL_792;
        ahabit;
          goto _L19
    }

}
