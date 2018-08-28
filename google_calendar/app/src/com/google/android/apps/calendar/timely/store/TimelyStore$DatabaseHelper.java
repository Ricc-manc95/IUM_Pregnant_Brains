// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.content.Context;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.apps.calendar.timely.store:
//            TimelyStoreUtils

final class setWriteAheadLoggingEnabled extends SQLiteOpenHelper
{

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
        sqlitedatabase.execSQL("CREATE TABLE timelydata (_id INTEGER PRIMARY KEY,syncId TEXT,calendarId INTEGER,structuredLocation TEXT,smartmail TEXT,eventBackgroundUrl TEXT,attachments TEXT,associatedContacts TEXT,titleContacts TEXT,eventGadget TEXT,eventSource TEXT,conferenceData TEXT,responseSummary TEXT,participantStatus TEXT,attendees TEXT, UNIQUE (syncId, calendarId));");
        sqlitedatabase.execSQL("CREATE INDEX calendar_event_index ON timelydata (syncId, calendarId)");
        sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
        sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
        sqlitedatabase.execSQL("CREATE TABLE timelysettingslog (_id INTEGER PRIMARY KEY,accountName TEXT,id TEXT,value TEXT,flags TEXT);");
        sqlitedatabase.execSQL("CREATE INDEX settings_log_account_ordered_index ON timelysettingslog(accountName,_id)");
        sqlitedatabase.execSQL("CREATE TABLE preferrednotifications (_id INTEGER PRIMARY KEY,lookupKey TEXT,accountName TEXT,category INTEGER,allday INTEGER,minutes INTEGER,method INTEGER,timestamp INTEGER,noNotifications INTEGER DEFAULT 0, UNIQUE (lookupKey, accountName, category, allday, minutes, method));");
        sqlitedatabase.execSQL("CREATE INDEX notifications_index ON preferrednotifications(lookupKey, category)");
        sqlitedatabase.execSQL("CREATE TABLE calendar_settings (_id INTEGER PRIMARY KEY,calendar_sync_id TEXT,account_name TEXT,account_type TEXT,conference_properties TEXT, UNIQUE (calendar_sync_id, account_name, account_type));");
        sqlitedatabase.execSQL("CREATE INDEX calendar_settings_index ON calendar_settings(calendar_sync_id, account_name, account_type)");
        TimelyStoreUtils.triggerSyncAdapterRestoreTimelyData();
    }

    public final void onOpen(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.setMaxSqlCacheSize(25);
    }

    public final void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        i;
        JVM INSTR tableswitch 1 36: default 160
    //                   1 169
    //                   2 181
    //                   3 193
    //                   4 211
    //                   5 235
    //                   6 253
    //                   7 264
    //                   8 282
    //                   9 299
    //                   10 310
    //                   11 321
    //                   12 321
    //                   13 339
    //                   14 351
    //                   15 362
    //                   16 386
    //                   17 397
    //                   18 415
    //                   19 427
    //                   20 451
    //                   21 451
    //                   22 462
    //                   23 473
    //                   24 485
    //                   25 497
    //                   26 503
    //                   27 503
    //                   28 509
    //                   29 527
    //                   30 1266
    //                   31 1266
    //                   32 1266
    //                   33 1266
    //                   34 1260
    //                   35 1254
    //                   36 1248;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L20 _L21 _L22 _L23 _L24 _L25 _L25 _L26 _L27 _L28 _L28 _L28 _L28 _L29 _L30 _L31
_L28:
        break MISSING_BLOCK_LABEL_1266;
_L1:
        boolean flag1 = false;
_L33:
        SQLiteException sqliteexception12;
        boolean flag;
        if (!flag1)
        {
            return;
        } else
        {
            sqlitedatabase = new Bundle(3);
            sqlitedatabase.putBoolean("sync_extra_get_settings", true);
            sqlitedatabase.putBoolean("sync_extra_get_recent_notifications", true);
            sqlitedatabase.putBoolean("sync_extra_get_default_notifications", true);
            sqlitedatabase.putBoolean("metafeedonly", true);
            TimelyStoreUtils.triggerSyncAdapterSyncWithExtras(null, null, false, sqlitedatabase);
            return;
        }
_L2:
        sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
        sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
_L3:
        sqlitedatabase.execSQL("CREATE TABLE preferrednotifications (_id INTEGER PRIMARY KEY,lookupKey TEXT,accountName TEXT,category INTEGER,allday INTEGER,minutes INTEGER,method INTEGER,timestamp INTEGER,noNotifications INTEGER DEFAULT 0, UNIQUE (lookupKey, accountName, category, allday, minutes, method));");
        sqlitedatabase.execSQL("CREATE INDEX notifications_index ON preferrednotifications(lookupKey, category)");
_L4:
        dropTable(sqlitedatabase, "timelysettings");
        sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
        sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
_L5:
        dropTable(sqlitedatabase, "timelydata");
        dropTable(sqlitedatabase, "calendar_event_index");
        sqlitedatabase.execSQL("CREATE TABLE timelydata (_id INTEGER PRIMARY KEY,syncId TEXT,calendarId INTEGER,structuredLocation TEXT,smartmail TEXT,eventBackgroundUrl TEXT,attachments TEXT,associatedContacts TEXT,titleContacts TEXT,eventGadget TEXT,eventSource TEXT,conferenceData TEXT,responseSummary TEXT,participantStatus TEXT,attendees TEXT, UNIQUE (syncId, calendarId));");
        sqlitedatabase.execSQL("CREATE INDEX calendar_event_index ON timelydata (syncId, calendarId)");
_L6:
        dropTable(sqlitedatabase, "preferrednotifications");
        sqlitedatabase.execSQL("CREATE TABLE preferrednotifications (_id INTEGER PRIMARY KEY,lookupKey TEXT,accountName TEXT,category INTEGER,allday INTEGER,minutes INTEGER,method INTEGER,timestamp INTEGER,noNotifications INTEGER DEFAULT 0, UNIQUE (lookupKey, accountName, category, allday, minutes, method));");
        sqlitedatabase.execSQL("CREATE INDEX notifications_index ON preferrednotifications(lookupKey, category)");
_L7:
        if (i > 4)
        {
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN associatedContacts TEXT");
            }
            catch (SQLiteException sqliteexception)
            {
                LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "associatedContacts", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
        }
_L8:
        dropTable(sqlitedatabase, "preferrednotifications");
        sqlitedatabase.execSQL("CREATE TABLE preferrednotifications (_id INTEGER PRIMARY KEY,lookupKey TEXT,accountName TEXT,category INTEGER,allday INTEGER,minutes INTEGER,method INTEGER,timestamp INTEGER,noNotifications INTEGER DEFAULT 0, UNIQUE (lookupKey, accountName, category, allday, minutes, method));");
        sqlitedatabase.execSQL("CREATE INDEX notifications_index ON preferrednotifications(lookupKey, category)");
_L9:
        if (i > 3)
        {
            sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN tasksselected INTEGER DEFAULT 1");
            sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN taskscolor TEXT DEFAULT 4184f3");
        }
_L10:
        if (i > 4)
        {
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN eventGadget TEXT");
            }
            catch (SQLiteException sqliteexception1)
            {
                LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "eventGadget", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
        }
_L11:
        if (i > 3)
        {
            sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN defaultEventLength INTEGER DEFAULT 60");
        }
_L12:
        dropTable(sqlitedatabase, "timelysettings");
        sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
        sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
_L13:
        if (i > 12)
        {
            sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN holidayscolor TEXT");
        }
_L14:
        if (i > 4)
        {
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN titleContacts TEXT");
            }
            catch (SQLiteException sqliteexception2)
            {
                LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "titleContacts", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
        }
_L15:
        if (i > 12)
        {
            dropTable(sqlitedatabase, "timelysettings");
            sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
            sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
        }
_L16:
        if (i > 4)
        {
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN eventSource TEXT");
            }
            catch (SQLiteException sqliteexception3)
            {
                LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "eventSource", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
        }
_L17:
        dropTable(sqlitedatabase, "preferrednotifications");
        sqlitedatabase.execSQL("CREATE TABLE preferrednotifications (_id INTEGER PRIMARY KEY,lookupKey TEXT,accountName TEXT,category INTEGER,allday INTEGER,minutes INTEGER,method INTEGER,timestamp INTEGER,noNotifications INTEGER DEFAULT 0, UNIQUE (lookupKey, accountName, category, allday, minutes, method));");
        sqlitedatabase.execSQL("CREATE INDEX notifications_index ON preferrednotifications(lookupKey, category)");
_L18:
        sqlitedatabase.execSQL("CREATE TABLE timelysettingslog (_id INTEGER PRIMARY KEY,accountName TEXT,id TEXT,value TEXT,flags TEXT);");
        sqlitedatabase.execSQL("CREATE INDEX settings_log_account_ordered_index ON timelysettingslog(accountName,_id)");
_L19:
        if (i > 12)
        {
            dropTable(sqlitedatabase, "timelysettings");
            sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
            sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
        }
_L20:
        if (i > 4)
        {
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN attachments TEXT");
            }
            catch (SQLiteException sqliteexception4)
            {
                LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "attachments", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
        }
_L21:
        if (i > 4)
        {
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN conferenceData TEXT");
            }
            catch (SQLiteException sqliteexception5)
            {
                LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "conferenceData", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
        }
_L22:
        if (i > 19)
        {
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN autoAddHangouts TEXT");
            }
            catch (SQLiteException sqliteexception6)
            {
                LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "autoAddHangouts", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
        }
_L23:
        if (i > 19)
        {
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN settingBirthdayIncludeGplus INTEGER DEFAULT 1");
            }
            catch (SQLiteException sqliteexception7)
            {
                LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "settingBirthdayIncludeGplus", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
        }
_L24:
        try
        {
            sqlitedatabase.execSQL("ALTER TABLE preferrednotifications ADD COLUMN noNotifications INTEGER DEFAULT 0");
        }
        catch (SQLiteException sqliteexception8)
        {
            LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                "preferrednotifications", Integer.valueOf(i), Integer.valueOf(j)
            });
        }
_L25:
        try
        {
            sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN timezone TEXT");
        }
        catch (SQLiteException sqliteexception9)
        {
            LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                "timezone", Integer.valueOf(i), Integer.valueOf(j)
            });
        }
_L26:
        if (i >= 27)
        {
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE timelysettings DROP COLUMN isGoogleDomainUser");
            }
            catch (SQLiteException sqliteexception10)
            {
                LogUtils.e("TimelyStore", "Can not drop column isGoogleDomainUser on upgrade from %d to %d", new Object[] {
                    Integer.valueOf(i), Integer.valueOf(j)
                });
            }
        }
        try
        {
            sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN qualityOfService TEXT DEFAULT NULL");
        }
        catch (SQLiteException sqliteexception11)
        {
            LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                "qualityOfService", Integer.valueOf(i), Integer.valueOf(j)
            });
        }
_L27:
        sqlitedatabase.execSQL("CREATE TABLE calendar_settings (_id INTEGER PRIMARY KEY,calendar_sync_id TEXT,account_name TEXT,account_type TEXT,conference_properties TEXT, UNIQUE (calendar_sync_id, account_name, account_type));");
        sqlitedatabase.execSQL("CREATE INDEX calendar_settings_index ON calendar_settings(calendar_sync_id, account_name, account_type)");
        flag1 = true;
_L37:
        flag = flag1;
        if (i <= 4)
        {
            break MISSING_BLOCK_LABEL_561;
        }
        sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN responseSummary TEXT");
        flag = flag1;
_L34:
        flag1 = flag;
        if (i <= 4)
        {
            break MISSING_BLOCK_LABEL_580;
        }
        sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN participantStatus TEXT");
        flag1 = flag;
_L35:
        flag = flag1;
        if (i <= 30)
        {
            break MISSING_BLOCK_LABEL_600;
        }
        sqlitedatabase.execSQL("ALTER TABLE timelydata DROP COLUMN deepLinkData");
        flag = flag1;
_L36:
        flag1 = flag;
        if (i <= 4) goto _L33; else goto _L32
_L32:
        sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN attendees TEXT");
        flag1 = flag;
          goto _L33
        sqlitedatabase;
        LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
            "attendees", Integer.valueOf(i), Integer.valueOf(j)
        });
        flag1 = flag;
          goto _L33
        sqliteexception12;
        LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
            "responseSummary", Integer.valueOf(i), Integer.valueOf(j)
        });
        flag = flag1;
          goto _L34
        sqliteexception12;
        LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
            "participantStatus", Integer.valueOf(i), Integer.valueOf(j)
        });
        flag1 = flag;
          goto _L35
        sqliteexception12;
        LogUtils.e("TimelyStore", "Can not drop column deepLinkData on upgrade from %d to %d", new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        });
        flag = flag1;
          goto _L36
_L31:
        flag = false;
          goto _L36
_L30:
        flag1 = false;
          goto _L35
_L29:
        flag = false;
          goto _L34
        flag1 = false;
          goto _L37
    }

    public (Context context, int i)
    {
        super(context, "timelydata.db", null, 37, new DefaultDatabaseErrorHandler());
        setWriteAheadLoggingEnabled(true);
    }
}
