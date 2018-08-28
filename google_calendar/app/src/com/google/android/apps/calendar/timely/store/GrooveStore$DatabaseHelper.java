// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.content.Context;
import android.database.Cursor;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.habit.FitIntegrationStore;
import com.google.android.calendar.api.habit.HabitUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

// Referenced classes of package com.google.android.apps.calendar.timely.store:
//            GrooveStore

static final class context extends SQLiteOpenHelper
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
    //                   1 89
    //                   2 95
    //                   3 95
    //                   4 95
    //                   5 107
    //                   6 119
    //                   7 125
    //                   8 125
    //                   9 137
    //                   10 143
    //                   11 155
    //                   12 161
    //                   13 161
    //                   14 204
    //                   15 216
    //                   16 216
    //                   17 216;
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

    public ractDesugaringStrategy(Context context1, int i)
    {
        super(context1, "groovedata.db", null, 18, new DefaultDatabaseErrorHandler());
        context = context1;
    }
}
