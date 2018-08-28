// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.habit.FitIntegrationStore;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitUtil;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.calendar.v2a.android.util.provider.Selection;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrooveStore
{
    static final class DatabaseHelper extends SQLiteOpenHelper
    {

        private final Context context;

        private static void dropTable(SQLiteDatabase sqlitedatabase, String s)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "DROP TABLE IF EXISTS ".concat(s);
            } else
            {
                s = new String("DROP TABLE IF EXISTS ");
            }
            sqlitedatabase.execSQL(s);
        }

        public final void onCreate(SQLiteDatabase sqlitedatabase)
        {
            sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS habit (_id INTEGER PRIMARY KEY,_sync_id TEXT NOT NULL,account TEXT NOT NULL COLLATE NOCASE,calendar TEXT NOT NULL COLLATE NOCASE,dirty INTEGER NOT NULL DEFAULT 1,lastSynced INTEGER NOT NULL DEFAULT 0,mutators TEXT,fingerprint TEXT,deletionStatus INTEGER NOT NULL DEFAULT 1,untilMillisUtc INTEGER NOT NULL DEFAULT 0,fitIntegrationStatus INTEGER NOT NULL DEFAULT 0,data BLOB, UNIQUE (_sync_id, lastSynced, calendar, account));");
            sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS habitnotifications (_id INTEGER PRIMARY KEY,habitParentSyncId TEXT,eventId TEXT,triggerTimeMs INTEGER,type INTEGER,displayState INTEGER, UNIQUE (eventId, type));");
            sqlitedatabase.execSQL("CREATE TRIGGER IF NOT EXISTS habit_notifications_cleanup_trigger DELETE ON habit BEGIN DELETE FROM habitnotifications WHERE habitParentSyncId=old._sync_id; END");
            sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS _sync_state (_id INTEGER PRIMARY KEY,account_name TEXT NOT NULL,data TEXT, UNIQUE (account_name));");
            GrooveStore.triggerSyncAdapterRestoreGrooveData(context);
        }

        public final void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
            Throwable throwable = null;
            i;
            JVM INSTR tableswitch 1 17: default 88
        //                       1 89
        //                       2 95
        //                       3 95
        //                       4 95
        //                       5 107
        //                       6 119
        //                       7 125
        //                       8 125
        //                       9 137
        //                       10 143
        //                       11 155
        //                       12 161
        //                       13 161
        //                       14 204
        //                       15 216
        //                       16 216
        //                       17 216;
               goto _L1 _L2 _L3 _L3 _L3 _L4 _L5 _L6 _L6 _L7 _L8 _L9 _L10 _L10 _L11 _L12 _L12 _L12
_L12:
            break; /* Loop/switch isn't completed */
_L1:
            return;
_L2:
            dropTable(sqlitedatabase, "habitinstances");
_L3:
            dropTable(sqlitedatabase, "habit");
            sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS habit (_id INTEGER PRIMARY KEY,_sync_id TEXT NOT NULL,account TEXT NOT NULL COLLATE NOCASE,calendar TEXT NOT NULL COLLATE NOCASE,dirty INTEGER NOT NULL DEFAULT 1,lastSynced INTEGER NOT NULL DEFAULT 0,mutators TEXT,fingerprint TEXT,deletionStatus INTEGER NOT NULL DEFAULT 1,untilMillisUtc INTEGER NOT NULL DEFAULT 0,fitIntegrationStatus INTEGER NOT NULL DEFAULT 0,data BLOB, UNIQUE (_sync_id, lastSynced, calendar, account));");
_L4:
            dropTable(sqlitedatabase, "habitnotifications");
            sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS habitnotifications (_id INTEGER PRIMARY KEY,habitParentSyncId TEXT,eventId TEXT,triggerTimeMs INTEGER,type INTEGER,displayState INTEGER, UNIQUE (eventId, type));");
_L5:
            sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS _sync_state (_id INTEGER PRIMARY KEY,account_name TEXT NOT NULL,data TEXT, UNIQUE (account_name));");
_L6:
            dropTable(sqlitedatabase, "habit");
            sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS habit (_id INTEGER PRIMARY KEY,_sync_id TEXT NOT NULL,account TEXT NOT NULL COLLATE NOCASE,calendar TEXT NOT NULL COLLATE NOCASE,dirty INTEGER NOT NULL DEFAULT 1,lastSynced INTEGER NOT NULL DEFAULT 0,mutators TEXT,fingerprint TEXT,deletionStatus INTEGER NOT NULL DEFAULT 1,untilMillisUtc INTEGER NOT NULL DEFAULT 0,fitIntegrationStatus INTEGER NOT NULL DEFAULT 0,data BLOB, UNIQUE (_sync_id, lastSynced, calendar, account));");
_L7:
            sqlitedatabase.execSQL("UPDATE habitnotifications SET displayState=2 WHERE displayState=4");
_L8:
            Object obj;
            if (i > 8)
            {
                try
                {
                    sqlitedatabase.execSQL("ALTER TABLE habit ADD COLUMN untilMillisUtc INTEGER NOT NULL DEFAULT 0");
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    LogUtils.e(GrooveStore.TAG, "Can not add column %s on upgrade from %d to %d", new Object[] {
                        "untilMillisUtc", Integer.valueOf(i), Integer.valueOf(j)
                    });
                }
            }
_L9:
            sqlitedatabase.execSQL("CREATE TRIGGER IF NOT EXISTS habit_notifications_cleanup_trigger DELETE ON habit BEGIN DELETE FROM habitnotifications WHERE habitParentSyncId=old._sync_id; END");
_L10:
            if (GrooveStore.countHabits(sqlitedatabase, null, null) > 0)
            {
                sqlitedatabase.delete("habitnotifications", null, null);
                sqlitedatabase.delete("habit", null, null);
                sqlitedatabase.delete("_sync_state", null, null);
                GrooveStore.triggerSyncAdapterRestoreGrooveData(context);
            }
_L11:
            if (i <= 8)
            {
                break; /* Loop/switch isn't completed */
            }
            Context context1;
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE habit ADD COLUMN fitIntegrationStatus INTEGER NOT NULL DEFAULT 0");
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                LogUtils.e(GrooveStore.TAG, "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "fitIntegrationStatus", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
            context1 = context;
            obj = sqlitedatabase.query("habit", new String[] {
                "account", "calendar", "_sync_id", "fitIntegrationStatus"
            }, null, null, null, null, null);
            if (obj == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                sqlitedatabase = new FitIntegrationStore(context1);
                for (; ((Cursor) (obj)).moveToNext(); sqlitedatabase.setIntegration(((Cursor) (obj)).getString(0), ((Cursor) (obj)).getString(1), ((Cursor) (obj)).getString(2), HabitUtil.checkFitIntegrationStatus(((Cursor) (obj)).getInt(3)))) { }
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Throwable throwable) { }
            finally { }
            throw throwable;
            sqlitedatabase;
            if (obj != null)
            {
                if (throwable != null)
                {
                    try
                    {
                        ((Cursor) (obj)).close();
                    }
                    // Misplaced declaration of an exception variable
                    catch (Object obj)
                    {
                        ThrowableExtension.STRATEGY.addSuppressed(throwable, ((Throwable) (obj)));
                    }
                } else
                {
                    ((Cursor) (obj)).close();
                }
            }
            throw sqlitedatabase;
            if (obj == null) goto _L1; else goto _L13
_L13:
            ((Cursor) (obj)).close();
            return;
        }

        public DatabaseHelper(Context context1, int i)
        {
            super(context1, "groovedata.db", null, 18, new DefaultDatabaseErrorHandler());
            context = context1;
        }
    }


    private static final String HABIT_COUNTS_PROJECTION[] = {
        "IFNULL(COUNT(_id), 0) AS _id", "IFNULL(SUM(dirty), 0) AS dirty", "IFNULL(SUM(lastSynced), 0) AS lastSynced"
    };
    private static final String HABIT_COUNT_PROJECTION[] = {
        "IFNULL(COUNT(_id), 0) AS _count"
    };
    public static final String HABIT_NOTIFICATION_PROJECTION[] = {
        "_id", "habitParentSyncId", "eventId", "displayState", "triggerTimeMs", "type"
    };
    private static final String HABIT_PROJECTION[] = null;
    private static final Object STORE_HOLDER_LOCK = new Object();
    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/timely/store/GrooveStore);
    public static final String WHERE_HABIT_DESCRIPTOR;
    private static final Selection WHERE_HABIT_DESCRIPTOR_SELECTION;
    private static final String WHERE_HABIT_LAST_SYNCED_AND_DESCRIPTOR;
    private static final String WHERE_HABIT_NOT_LAST_SYNCED_AND_DESCRIPTOR;
    public static GrooveStore store;
    public final Context context;
    public final SQLiteDatabase database;

    private GrooveStore(Context context1)
    {
        database = (new DatabaseHelper(context1, 18)).getWritableDatabase();
        context = context1;
    }

    static int countHabits(SQLiteDatabase sqlitedatabase, String s, String as[])
    {
        sqlitedatabase = sqlitedatabase.query("habit", HABIT_COUNT_PROJECTION, s, as, null, null, null);
        sqlitedatabase.moveToFirst();
        int i = sqlitedatabase.getInt(0);
        sqlitedatabase.close();
        return i;
    }

    public static Uri createInstanceContentUri(HabitDescriptor habitdescriptor)
    {
        return android.provider.CalendarContract.Events.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", habitdescriptor.calendar.account.name).appendQueryParameter("account_type", habitdescriptor.calendar.account.type).build();
    }

    public static void forceNotifyChange(Context context1, Uri uri)
    {
        if (context1 == null)
        {
            return;
        } else
        {
            context1.getContentResolver().notifyChange(uri, null, true);
            uri = (Intent)(new Intent(String.valueOf(context1.getPackageName()).concat(".APPWIDGET_CALLER_IS_SYNCADAPTER"))).clone();
            uri.setPackage(context1.getPackageName());
            context1.sendBroadcast(uri);
            return;
        }
    }

    public static void initialize(Context context1)
    {
        synchronized (STORE_HOLDER_LOCK)
        {
            if (store == null)
            {
                store = new GrooveStore(context1);
            }
        }
        return;
        context1;
        obj;
        JVM INSTR monitorexit ;
        throw context1;
    }

    public static void triggerSyncAdapterRestoreGrooveData(Context context1)
    {
        context1 = AccountsUtil.getGoogleAccounts(context1);
        int j = context1.length;
        for (int i = 0; i < j; i++)
        {
            Account account = context1[i];
            Bundle bundle = new Bundle(2);
            bundle.putBoolean("only_groove", true);
            bundle.putString("feed_internal", account.name);
            LogUtils.d(TAG, "Requesting restore of Groove data", new Object[0]);
            FeatureConfig featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).fishfood_debug();
            ContentResolver.requestSync(account, "com.android.calendar", bundle);
        }

    }

    public final void cleanIntegrationStore()
    {
        Object obj = database.query("habit", new String[] {
            "account", "calendar", "_sync_id"
        }, null, null, null, null, null);
        if (obj == null)
        {
            return;
        }
        com.google.android.calendar.api.habit.FitIntegrationStore.Cleaner cleaner = new com.google.android.calendar.api.habit.FitIntegrationStore.Cleaner(new FitIntegrationStore(context));
        String s;
        String s1;
        String s3;
        for (; ((Cursor) (obj)).moveToNext(); cleaner.toKeep.add(FitIntegrationStore.toKey(s, s1, s3)))
        {
            s = ((Cursor) (obj)).getString(0);
            s1 = ((Cursor) (obj)).getString(1);
            s3 = ((Cursor) (obj)).getString(2);
        }

        obj = cleaner.this$0.sharedPreferences.edit();
        Iterator iterator = cleaner.this$0.sharedPreferences.getAll().keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s2 = (String)iterator.next();
            if (!cleaner.toKeep.contains(s2))
            {
                ((android.content.SharedPreferences.Editor) (obj)).remove(s2);
            }
        } while (true);
        ((android.content.SharedPreferences.Editor) (obj)).apply();
    }

    public final Entity getHabit(Account account, String s, String s1)
    {
        account = database.query("habit", null, WHERE_HABIT_NOT_LAST_SYNCED_AND_DESCRIPTOR, new String[] {
            account.name, s, s1
        }, null, null, null);
        int i = account.getCount();
        if (i != 1)
        {
            LogUtils.d(TAG, "Unexpected number - %d - of habits for id=\"%s\"", new Object[] {
                Integer.valueOf(i), s1
            });
        }
        if (i == 0)
        {
            account.close();
            return null;
        } else
        {
            account.moveToFirst();
            s = new ContentValues(account.getColumnCount());
            DatabaseUtils.cursorRowToContentValues(account, s);
            s = new Entity(s);
            account.close();
            return s;
        }
    }

    public final List getHabits(HabitDescriptor ahabitdescriptor[])
    {
        Throwable throwable1;
        ArrayList arraylist;
        ArrayList arraylist1;
        int i;
        throwable1 = null;
        if (ahabitdescriptor == null || ahabitdescriptor.length == 0)
        {
            return ImmutableList.of();
        }
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        i = 0;
_L4:
        if (i * 50 >= ahabitdescriptor.length) goto _L2; else goto _L1
_L1:
        Object obj;
        int k = Math.min(50, ahabitdescriptor.length - i * 50);
        com.google.calendar.v2a.android.util.provider.Selection.Builder builder = Selection.nTimesOr(k, WHERE_HABIT_DESCRIPTOR_SELECTION);
        Selection selection = new Selection(builder.filterString.toString(), builder.argsCount);
        arraylist1.clear();
        for (int j = 0; j < k; j++)
        {
            arraylist1.add(ahabitdescriptor[i * 50 + j].calendar.account.name);
            arraylist1.add(ahabitdescriptor[i * 50 + j].calendar.calendarId);
            arraylist1.add(ahabitdescriptor[i * 50 + j].habitId);
        }

        obj = database;
        String s = selection.filterString;
        Object aobj[] = new Iterable[0];
        aobj = FluentIterable.concatNoDefensiveCopy(new Iterable[] {
            arraylist1, FluentIterable.concatNoDefensiveCopy((Iterable[])Arrays.copyOf(aobj, aobj.length))
        });
        Object aobj1[] = (Object[])Array.newInstance(java/lang/String, 0);
        boolean flag;
        if (aobj instanceof Collection)
        {
            aobj = (Collection)aobj;
        } else
        {
            Iterator iterator = ((Iterable) (aobj)).iterator();
            aobj = new ArrayList();
            Iterators.addAll(((Collection) (aobj)), iterator);
        }
        aobj = (String[])((Collection) (aobj)).toArray(aobj1);
        if (aobj.length == selection.argsCount)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Invalid number of arguments"));
        }
        if (selection.argsCount == 0)
        {
            aobj = null;
        }
        obj = ((SQLiteDatabase) (obj)).query("habit", null, s, ((String []) (aobj)), null, null, null);
        ((Cursor) (obj)).moveToPosition(-1);
        ContentValues contentvalues;
        for (; ((Cursor) (obj)).moveToNext(); arraylist.add(new Entity(contentvalues)))
        {
            contentvalues = new ContentValues(((Cursor) (obj)).getColumnCount());
            DatabaseUtils.cursorRowToContentValues(((Cursor) (obj)), contentvalues);
        }

          goto _L3
        Throwable throwable;
        throwable;
        throw throwable;
        ahabitdescriptor;
_L5:
        if (obj != null)
        {
            if (throwable != null)
            {
                try
                {
                    ((Cursor) (obj)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Throwable throwable1)
                {
                    ThrowableExtension.STRATEGY.addSuppressed(throwable, throwable1);
                }
            } else
            {
                ((Cursor) (obj)).close();
            }
        }
        throw ahabitdescriptor;
_L3:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        i++;
          goto _L4
_L2:
        return arraylist;
        ahabitdescriptor;
        throwable = throwable1;
          goto _L5
    }

    public final long insertHabit(Entity entity, boolean flag)
    {
        entity = entity.getEntityValues();
        if (entity.containsKey("_id"))
        {
            throw new IllegalArgumentException("Entity contains forbidden column: _id.");
        }
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        entity.put("dirty", Integer.valueOf(i));
        return database.insertWithOnConflict("habit", null, entity, 5);
    }

    public final Entity[] queryHabitNotifications(String s, String as[], String s1, String s2)
    {
        s = database.query("habitnotifications", HABIT_NOTIFICATION_PROJECTION, s, as, null, null, s1, s2);
        as = new Entity[s.getCount()];
        int i = 0;
_L2:
        if (i >= as.length)
        {
            break; /* Loop/switch isn't completed */
        }
        s.moveToPosition(i);
        s1 = new ContentValues(s.getColumnCount());
        DatabaseUtils.cursorRowToContentValues(s, s1);
        as[i] = new Entity(s1);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        s.close();
        return as;
        as;
        s.close();
        throw as;
    }

    public final int updateHabit(Entity entity, boolean flag)
    {
        String as[];
        int i = 1;
        Object obj = entity.getEntityValues();
        if (((ContentValues) (obj)).containsKey("_id"))
        {
            throw new IllegalArgumentException("Entity contains forbidden column: _id.");
        }
        database.beginTransaction();
        ContentValues contentvalues;
        if (!flag)
        {
            i = 0;
        }
        ((ContentValues) (obj)).put("dirty", Integer.valueOf(i));
        ((ContentValues) (obj)).put("lastSynced", Integer.valueOf(0));
        as = new String[3];
        as[0] = ((ContentValues) (obj)).getAsString("account");
        as[1] = ((ContentValues) (obj)).getAsString("calendar");
        as[2] = ((ContentValues) (obj)).getAsString("_sync_id");
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_316;
        }
        obj = database.query("habit", HABIT_COUNTS_PROJECTION, WHERE_HABIT_DESCRIPTOR, as, null, null, null);
        ((Cursor) (obj)).moveToFirst();
        contentvalues = new ContentValues(HABIT_COUNTS_PROJECTION.length);
        DatabaseUtils.cursorRowToContentValues(((Cursor) (obj)), contentvalues);
        ((Cursor) (obj)).close();
        if (contentvalues.getAsInteger("dirty").intValue() == 0)
        {
            if (contentvalues.getAsInteger("lastSynced").intValue() != 0)
            {
                database.delete("habit", WHERE_HABIT_LAST_SYNCED_AND_DESCRIPTOR, as);
            }
            obj = getHabit(new Account(as[0], "com.google"), as[1], as[2]);
            contentvalues = ((Entity) (obj)).getEntityValues();
            contentvalues.remove("_id");
            contentvalues.put("lastSynced", Integer.valueOf(1));
            contentvalues.put("dirty", Integer.valueOf(0));
            insertHabit(((Entity) (obj)), false);
        }
_L1:
        i = database.update("habit", entity.getEntityValues(), WHERE_HABIT_NOT_LAST_SYNCED_AND_DESCRIPTOR, as);
        database.setTransactionSuccessful();
        database.endTransaction();
        return i;
        database.delete("habit", WHERE_HABIT_LAST_SYNCED_AND_DESCRIPTOR, as);
          goto _L1
        entity;
        database.endTransaction();
        throw entity;
    }

    static 
    {
        Object obj = Selection.where("account").this$0;
        obj.argsCount = ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).argsCount + 1;
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append("=?");
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append(" AND ");
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append("calendar");
        obj = (new com.google.calendar.v2a.android.util.provider.Selection.Builder.ColumnExpression(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)))).this$0;
        obj.argsCount = ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).argsCount + 1;
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append("=?");
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append(" AND ");
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append("_sync_id");
        obj = (new com.google.calendar.v2a.android.util.provider.Selection.Builder.ColumnExpression(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)))).this$0;
        obj.argsCount = ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).argsCount + 1;
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append("=?");
        obj = new Selection(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.toString(), ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).argsCount);
        WHERE_HABIT_DESCRIPTOR_SELECTION = ((Selection) (obj));
        WHERE_HABIT_DESCRIPTOR = ((Selection) (obj)).filterString;
        obj = String.valueOf("lastSynced=0 AND ");
        String s = String.valueOf(WHERE_HABIT_DESCRIPTOR);
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        WHERE_HABIT_NOT_LAST_SYNCED_AND_DESCRIPTOR = ((String) (obj));
        obj = String.valueOf("lastSynced=1 AND ");
        s = String.valueOf(WHERE_HABIT_DESCRIPTOR);
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        WHERE_HABIT_LAST_SYNCED_AND_DESCRIPTOR = ((String) (obj));
    }
}
