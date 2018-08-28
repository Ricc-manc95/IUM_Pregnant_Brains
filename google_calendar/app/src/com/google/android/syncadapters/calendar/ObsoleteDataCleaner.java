// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.RemoteException;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.ParseException;
import com.google.android.apps.calendar.config.remote.OrphanEventsDataCleanupFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.syncadapters.timely.sql.SQLiteDatabaseUtils;
import com.google.android.apps.calendar.timely.store.GrooveStore;
import com.google.android.apps.calendar.timely.store.StoreUtils;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.apps.calendar.timely.store.TimelyStoreUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.calendar.utils.permission.PermissionsUtil;
import com.google.android.calendar.utils.version.MncUtil;
import com.google.android.gms.phenotype.PhenotypeFlag;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            ObsoleteDataCleanerContract, CalendarProviderContentHelper

public class ObsoleteDataCleaner
    implements ObsoleteDataCleanerContract
{
    public static final class DataCleaningResult
    {

        public static final DataCleaningResult NO_FEATURE_RESULT = new DataCleaningResult(-1, -1, 0, 0);
        public static final DataCleaningResult NO_PERMISSIONS_RESULT = new DataCleaningResult(-2, -2, 0, 0);
        public final int eventsDeleted;
        public final int fromStage;
        public final int habitNotificationsDeleted;
        public final int toStage;


        DataCleaningResult(int i, int j, int k, int l)
        {
            fromStage = i;
            toStage = j;
            eventsDeleted = k;
            habitNotificationsDeleted = l;
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/ObsoleteDataCleaner);

    public ObsoleteDataCleaner()
    {
    }

    private static Map getCalendars(ContentProviderClient contentproviderclient, Account aaccount[])
    {
        HashMap hashmap;
        int i;
        int j;
        hashmap = new HashMap();
        if (aaccount == null)
        {
            return hashmap;
        }
        j = aaccount.length;
        i = 0;
_L2:
        Account account;
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        account = aaccount[i];
        hashmap.put(account, CalendarProviderContentHelper.getStoredCalendarsForAccount(contentproviderclient, account));
_L3:
        i++;
        if (true) goto _L2; else goto _L1
        Object obj;
        obj;
_L4:
        LogUtils.e(TAG, ((Throwable) (obj)), "Failed to query calendars account %s.", new Object[] {
            LogUtils.sanitizeAccountName(TAG, account.name)
        });
          goto _L3
_L1:
        return hashmap;
        obj;
          goto _L4
    }

    static final Void lambda$maybeRemoveAllObsoleteData$0$ObsoleteDataCleaner(Context context)
        throws Exception
    {
        ContentProviderClient contentproviderclient = context.getContentResolver().acquireContentProviderClient("com.android.calendar");
        (new ObsoleteDataCleaner()).removeAllObsoleteData(context, contentproviderclient);
        if (contentproviderclient != null)
        {
            contentproviderclient.release();
        }
        return null;
        context;
        if (contentproviderclient != null)
        {
            contentproviderclient.release();
        }
        throw context;
    }

    static final DataCleaningResult lambda$maybeRemoveDataForDeletedEvents$1$ObsoleteDataCleaner(Context context)
        throws Exception
    {
        ContentProviderClient contentproviderclient = context.getContentResolver().acquireContentProviderClient("com.android.calendar");
        context = maybeRemoveDataForDeletedEventsInternal(context, contentproviderclient);
        if (contentproviderclient != null)
        {
            contentproviderclient.release();
        }
        return context;
        context;
        if (contentproviderclient != null)
        {
            contentproviderclient.release();
        }
        throw context;
    }

    public static ListenableFuture maybeRemoveAllObsoleteData(Context context)
    {
        boolean flag;
        flag = true;
        if (!RemoteFeatureConfig.ORPHAN_EVENTS_DATA_CLEANUP.enabled())
        {
            if (true)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
            }
        }
        break MISSING_BLOCK_LABEL_28;
        if (MncUtil.isMnc() && (PermissionsUtil.checkSelfPermission(context, "android.permission.READ_CALENDAR") != 0 || PermissionsUtil.checkSelfPermission(context, "android.permission.WRITE_CALENDAR") != 0))
        {
            flag = false;
        }
        class .Lambda._cls0
            implements Callable
        {

            private final Context arg$1;

            public final Object call()
            {
                return ObsoleteDataCleaner.lambda$maybeRemoveAllObsoleteData$0$ObsoleteDataCleaner(arg$1);
            }

            .Lambda._cls0(Context context)
            {
                arg$1 = context;
            }
        }

        if (!flag)
        {
            if (true)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
            }
        } else
        {
            return (FluentFuture)CalendarExecutor.DISK.submit(new .Lambda._cls0(context));
        }
    }

    public static ListenableFuture maybeRemoveDataForDeletedEvents(Context context)
    {
        class .Lambda._cls1
            implements Callable
        {

            private final Context arg$1;

            public final Object call()
            {
                return ObsoleteDataCleaner.lambda$maybeRemoveDataForDeletedEvents$1$ObsoleteDataCleaner(arg$1);
            }

            .Lambda._cls1(Context context)
            {
                arg$1 = context;
            }
        }

        if (!RemoteFeatureConfig.ORPHAN_EVENTS_DATA_CLEANUP.enabled())
        {
            context = DataCleaningResult.NO_FEATURE_RESULT;
            if (context == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(context);
            }
        } else
        {
            return (FluentFuture)CalendarExecutor.DISK.submit(new .Lambda._cls1(context));
        }
    }

    private static DataCleaningResult maybeRemoveDataForDeletedEventsInternal(Context context, ContentProviderClient contentproviderclient)
    {
        boolean flag;
        if (!MncUtil.isMnc())
        {
            flag = true;
        } else
        if (PermissionsUtil.checkSelfPermission(context, "android.permission.READ_CALENDAR") == 0 && PermissionsUtil.checkSelfPermission(context, "android.permission.WRITE_CALENDAR") == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return DataCleaningResult.NO_PERMISSIONS_RESULT;
        }
        if (!RemoteFeatureConfig.ORPHAN_EVENTS_DATA_CLEANUP.enabled())
        {
            return DataCleaningResult.NO_FEATURE_RESULT;
        }
        int i = ((Integer)RemoteFeatureConfig.ORPHAN_EVENTS_DATA_CLEANUP.stageFlag.get()).intValue();
        int j = context.getSharedPreferences("sync_adapter_prefs.xml", 0).getInt("orphan_data_last_applied_cleanup_stage", 0);
        if (i <= j)
        {
            return new DataCleaningResult(j, i, 0, 0);
        }
        Object obj = TimelyStore.acquire(context).database;
        android.net.Uri uri = android.provider.CalendarContract.Events.CONTENT_URI;
        int k = StoreUtils.deleteObsoleteRowsFromDatabase(((SQLiteDatabase) (obj)), "timelydata", "_id", new String[] {
            "syncId", "calendarId"
        }, contentproviderclient, uri, "_id", new String[] {
            "_sync_id", "calendar_id"
        });
        GrooveStore.initialize(context);
        obj = GrooveStore.store;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            Object obj1 = (GrooveStore)obj;
            obj = String.format(null, "CAST(%s as INTEGER)", new Object[] {
                "eventId"
            });
            obj1 = ((GrooveStore) (obj1)).database;
            android.net.Uri uri1 = android.provider.CalendarContract.Events.CONTENT_URI;
            int l = StoreUtils.deleteObsoleteRowsFromDatabase(((SQLiteDatabase) (obj1)), "habitnotifications", "_id", new String[] {
                obj
            }, contentproviderclient, uri1, "_id", new String[] {
                "_id"
            });
            context.getSharedPreferences("sync_adapter_prefs.xml", 0).edit().putInt("orphan_data_last_applied_cleanup_stage", i).apply();
            return new DataCleaningResult(j, i, k, l);
        }
    }

    private final void removeAllObsoleteData(Context context, ContentProviderClient contentproviderclient)
    {
        Account aaccount[] = AccountsUtil.cachedGoogleAccounts;
        if (aaccount != null)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        aaccount = AccountManager.get(context).getAccountsByType("com.google");
        TimelyStore timelystore;
        GrooveStore groovestore;
        if (AccountsUtil.cacheGoogleAccounts)
        {
            AccountsUtil.cachedGoogleAccounts = aaccount;
        }
        timelystore = TimelyStore.acquire(context);
        GrooveStore.initialize(context);
        groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            try
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                LogUtils.e(TAG, context, "Obsolete data removal skipped: error getting Google accounts.", new Object[0]);
            }
            return;
        }
        removeObsoleteData(timelystore, (GrooveStore)groovestore, contentproviderclient, aaccount);
        int i;
        if (aaccount != null)
        {
            break MISSING_BLOCK_LABEL_126;
        }
        i = 0;
_L2:
        context.getSharedPreferences("sync_adapter_prefs.xml", 0).edit().putInt("orphan_data_last_accounts_list_hash", i).apply();
        return;
        i = (new HashSet(Arrays.asList(aaccount))).hashCode();
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static void removeObsoleteData(TimelyStore timelystore, GrooveStore groovestore, ContentProviderClient contentproviderclient, Account aaccount[])
    {
        Map map;
        Iterator iterator;
        map = getCalendars(contentproviderclient, aaccount);
        contentproviderclient = new ArrayList();
        iterator = map.keySet().iterator();
        boolean flag = false;
_L2:
        boolean flag1;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        Account account = (Account)iterator.next();
        Map map1 = (Map)map.get(account);
        flag1 = SQLiteDatabaseUtils.deleteAllExcept(timelystore.database, "calendar_settings", "calendar_sync_id", map1.values(), "account_name=?", new String[] {
            account.name
        });
        contentproviderclient.addAll(map1.keySet());
        flag = flag1 | flag;
        if (true) goto _L2; else goto _L1
_L1:
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        List list = TimelyStoreUtils.getAccountNames(aaccount);
        SQLiteDatabaseUtils.deleteAllExcept(timelystore.database, "calendar_settings", "calendar_sync_id", new ArrayList(), SQLiteDatabaseUtils.makeNot(SQLiteDatabaseUtils.makeInClause("account_name", aaccount.length)), (String[])list.toArray(new String[0]));
        flag1 = SQLiteDatabaseUtils.deleteAllExcept(timelystore.database, "timelydata", "calendarId", contentproviderclient);
        flag2 = SQLiteDatabaseUtils.deleteAllExcept(timelystore.database, "preferrednotifications", "accountName", list);
        flag3 = SQLiteDatabaseUtils.deleteAllExcept(timelystore.database, "timelysettings", "accountName", list);
        flag4 = SQLiteDatabaseUtils.deleteAllExcept(timelystore.database, "timelysettingslog", "accountName", list);
        contentproviderclient = (Collection)timelystore.conferenceSubscribers.apply(null);
        timelystore = (Collection)timelystore.notificationSubscribers.apply(null);
        timelystore = TimelyStoreUtils.getAccountNames(aaccount);
        flag5 = SQLiteDatabaseUtils.deleteAllExcept(groovestore.database, "_sync_state", "account_name", timelystore);
        flag6 = SQLiteDatabaseUtils.deleteAllExcept(groovestore.database, "habit", "account", timelystore);
        groovestore.cleanIntegrationStore();
        groovestore = TAG;
        if (flag6 | (flag5 | false) | (flag4 | (flag1 | flag | flag2 | flag3)))
        {
            timelystore = "Cleanup succeeded.";
        } else
        {
            timelystore = "No cleanup required.";
        }
        try
        {
            LogUtils.v(groovestore, "%s", new Object[] {
                timelystore
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch (TimelyStore timelystore)
        {
            LogUtils.e(TAG, timelystore, "Cleanup failed.", new Object[0]);
        }
        return;
    }

    public final void removeAllDataForAccount(Account account, Context context, ContentProviderClient contentproviderclient)
    {
        Account aaccount1[] = AccountsUtil.cachedGoogleAccounts;
        Account aaccount[];
        aaccount = aaccount1;
        if (aaccount1 != null)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        aaccount = AccountManager.get(context).getAccountsByType("com.google");
        ArrayList arraylist;
        if (AccountsUtil.cacheGoogleAccounts)
        {
            AccountsUtil.cachedGoogleAccounts = aaccount;
        }
        arraylist = new ArrayList(Arrays.asList(aaccount));
        arraylist.remove(account);
        account = TimelyStore.acquire(context);
        GrooveStore.initialize(context);
        context = GrooveStore.store;
        if (context == null)
        {
            try
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            // Misplaced declaration of an exception variable
            catch (Account account)
            {
                LogUtils.e(TAG, account, "Obsolete data removal skipped: error getting Google accounts.", new Object[0]);
            }
            return;
        }
        removeObsoleteData(account, (GrooveStore)context, contentproviderclient, (Account[])arraylist.toArray(new Account[0]));
        return;
    }

    public final void removeAllObsoleteDataIfAccountsChanged(Context context, ContentProviderClient contentproviderclient)
    {
        Account aaccount[];
        int i;
        Account aaccount1[];
        int j;
        try
        {
            aaccount1 = AccountsUtil.cachedGoogleAccounts;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.e(TAG, context, "Obsolete data removal skipped: error getting Google accounts.", new Object[0]);
            return;
        }
        aaccount = aaccount1;
        if (aaccount1 != null)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        aaccount = AccountManager.get(context).getAccountsByType("com.google");
        if (AccountsUtil.cacheGoogleAccounts)
        {
            AccountsUtil.cachedGoogleAccounts = aaccount;
        }
          goto _L1
_L5:
        j = context.getSharedPreferences("sync_adapter_prefs.xml", 0).getInt("orphan_data_last_accounts_list_hash", 0);
        if (i != j)
        {
            removeAllObsoleteData(context, contentproviderclient);
        }
        return;
_L3:
        i = (new HashSet(Arrays.asList(aaccount))).hashCode();
        continue; /* Loop/switch isn't completed */
_L1:
        if (aaccount != null) goto _L3; else goto _L2
_L2:
        i = 0;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final void removeObsoleteCalendarData(Account account, Context context, long l, String s)
    {
        Object obj = TimelyStore.acquire(context);
        LogUtils.d("TimelyStore", "remove data for calendar %s of account: %s", new Object[] {
            Long.valueOf(l), LogUtils.sanitizeAccountName("TimelyStore", account.name)
        });
        ((TimelyStore) (obj)).database.delete("timelydata", (new StringBuilder(31)).append("calendarId=").append(l).toString(), null);
        ((TimelyStore) (obj)).database.delete("calendar_settings", "calendar_sync_id = ? AND account_name = ? AND account_type = ?", new String[] {
            s, account.name, account.type
        });
        ((TimelyStore) (obj)).database.delete("preferrednotifications", (new StringBuilder(30)).append("lookupKey=").append(l).toString(), null);
        Collection collection = (Collection)((TimelyStore) (obj)).conferenceSubscribers.apply(null);
        obj = (Collection)((TimelyStore) (obj)).notificationSubscribers.apply(null);
        GrooveStore.initialize(context);
        context = GrooveStore.store;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            context = (GrooveStore)context;
            LogUtils.v(GrooveStore.TAG, "Remove data for calendar %s of account: %s", new Object[] {
                LogUtils.sanitizeName(GrooveStore.TAG, s), LogUtils.sanitizeAccountName(GrooveStore.TAG, account.name)
            });
            ((GrooveStore) (context)).database.delete("habit", SQLiteDatabaseUtils.makeWhere(new String[] {
                "account=?", "calendar=?"
            }), new String[] {
                account.name, s
            });
            context.cleanIntegrationStore();
            return;
        }
    }

    public final void removeObsoleteEventsData(Account account, Context context, Map map)
    {
        TimelyStore timelystore = TimelyStore.acquire(context);
        Set set = map.keySet();
        LogUtils.d("TimelyStore", "remove Timely data for calendar(s) of account: %s", new Object[] {
            LogUtils.sanitizeAccountName("TimelyStore", account.name)
        });
        SQLiteDatabaseUtils.deleteAll(timelystore.database, "timelydata", null, null, "calendarId", set);
        GrooveStore.initialize(context);
        context = GrooveStore.store;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            context = (GrooveStore)context;
            map = map.values();
            LogUtils.v(GrooveStore.TAG, "Remove Groove data for calendar(s) of account: %s", new Object[] {
                LogUtils.sanitizeAccountName(GrooveStore.TAG, account.name)
            });
            SQLiteDatabaseUtils.deleteAll(((GrooveStore) (context)).database, "habit", "account=?", new String[] {
                account.name
            }, "calendar", map);
            context.cleanIntegrationStore();
            return;
        }
    }

    public final void removeObsoleteEventsData(Context context, long l, Map map)
    {
        TimelyStore timelystore = TimelyStore.acquire(context);
        Collection collection = map.values();
        SQLiteDatabaseUtils.deleteAll(timelystore.database, "timelydata", "calendarId=?", new String[] {
            String.valueOf(Long.valueOf(l))
        }, "syncId", collection);
        GrooveStore.initialize(context);
        context = GrooveStore.store;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            context = (GrooveStore)context;
            map = map.keySet();
            SQLiteDatabaseUtils.deleteAll(((GrooveStore) (context)).database, "habitnotifications", null, null, "eventId", map);
            return;
        }
    }

}
