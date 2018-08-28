// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timely.contract.TimelyContract;
import com.google.common.base.Platform;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.apps.calendar.timely.store:
//            AccountSettings, AccountSettingsLogStore, TimelyStoreUtils

public final class AccountSettingsStore
{

    private static final String ACCOUNT_SETTINGS_COLUMNS[] = {
        "accountName", "tasksselected", "taskscolor", "autoAddHangouts", "timezone", "qualityOfService", "smartMailDelivery", "holidayscolor", "defaultNoEndTime", "defaultEventLength", 
        "settingBirthdayVisibility", "settingBirthdayIncludeGplus"
    };
    public final Map cache;
    private final Context context;
    public final SQLiteDatabase database;
    private final AccountSettingsLogStore logStore;
    private final ContentObservable taskSettingsObservable = new ContentObservable();

    AccountSettingsStore(Context context1, SQLiteDatabase sqlitedatabase, AccountSettingsLogStore accountsettingslogstore, Map map)
    {
        context = context1;
        database = sqlitedatabase;
        logStore = accountsettingslogstore;
        cache = map;
    }

    static Map createCache(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase = sqlitedatabase.query("timelysettings", ACCOUNT_SETTINGS_COLUMNS, null, null, null, null, null);
        HashMap hashmap = new HashMap();
        if (sqlitedatabase != null) goto _L2; else goto _L1
_L1:
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
_L4:
        return hashmap;
_L2:
        boolean flag;
        if (sqlitedatabase.moveToFirst())
        {
            do
            {
                hashmap.put(sqlitedatabase.getString(sqlitedatabase.getColumnIndex("accountName")), AccountSettings.createFromCursor(sqlitedatabase));
                flag = sqlitedatabase.moveToNext();
            } while (flag);
        }
        if (sqlitedatabase == null) goto _L4; else goto _L3
_L3:
        sqlitedatabase.close();
        return hashmap;
        Exception exception;
        exception;
        sqlitedatabase = null;
_L6:
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        throw exception;
        exception;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private final void updateCache(String s)
    {
        Cursor cursor = database.query("timelysettings", ACCOUNT_SETTINGS_COLUMNS, "accountName = ?", new String[] {
            s
        }, null, null, null);
        if (cursor != null) goto _L2; else goto _L1
_L1:
        if (cursor != null)
        {
            cursor.close();
        }
_L4:
        return;
_L2:
        AccountSettings accountsettings;
        AccountSettings accountsettings1;
        if (!cursor.moveToFirst())
        {
            continue; /* Loop/switch isn't completed */
        }
        accountsettings = AccountSettings.createFromCursor(cursor);
        accountsettings1 = (AccountSettings)cache.get(s);
        if (accountsettings1 == null)
        {
            break MISSING_BLOCK_LABEL_210;
        }
        if (accountsettings1.tasksVisible != accountsettings.tasksVisible || accountsettings1.taskColor != accountsettings.taskColor)
        {
            break MISSING_BLOCK_LABEL_210;
        }
        Object obj;
        Intent intent;
        for (boolean flag = true; flag; flag = false)
        {
            break MISSING_BLOCK_LABEL_187;
        }

        obj = TimelyContract.TASKS_CALENDAR_URI.buildUpon().appendPath(s).build();
        taskSettingsObservable.dispatchChange(false, ((Uri) (obj)));
        context.getContentResolver().notifyChange(TimelyContract.TASKS_CALENDAR_URI, null);
        obj = context;
        intent = (Intent)(new Intent("com.google.android.timely.intent.action.TASK_SETTINGS_CHANGED")).clone();
        intent.setPackage(((Context) (obj)).getPackageName());
        ((Context) (obj)).sendBroadcast(intent);
        cache.put(s, accountsettings);
        if (cursor == null) goto _L4; else goto _L3
_L3:
        cursor.close();
        return;
        s;
        cursor = null;
_L6:
        if (cursor != null)
        {
            cursor.close();
        }
        throw s;
        s;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private final boolean updateDatabase(String s, ContentValues contentvalues, boolean flag, String s1)
    {
        database.beginTransaction();
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_133;
        }
        AccountSettingsLogStore accountsettingslogstore = logStore;
        ContentValues contentvalues1;
        for (Iterator iterator = contentvalues.keySet().iterator(); iterator.hasNext(); accountsettingslogstore.database.insert("timelysettingslog", null, contentvalues1))
        {
            String s2 = (String)iterator.next();
            contentvalues1 = new ContentValues();
            contentvalues1.put("accountName", s);
            contentvalues1.put("id", s2);
            contentvalues1.put("value", contentvalues.getAsString(s2));
            if (!TextUtils.isEmpty(s1))
            {
                contentvalues1.put("flags", s1);
            }
        }

        break MISSING_BLOCK_LABEL_159;
        s;
        database.endTransaction();
        throw s;
        if (!Platform.stringIsNullOrEmpty(s1))
        {
            LogUtils.w("AccountSettingsStore", "Log flags dropped: %s", new Object[] {
                s1
            });
        }
        if (database.update("timelysettings", contentvalues, "accountName = ?", new String[] {
    s
}) <= 0)
        {
            break MISSING_BLOCK_LABEL_212;
        }
        database.setTransactionSuccessful();
        context.getContentResolver().notifyChange(TimelyContract.ACCOUNT_SETTINGS_URI, null);
        database.endTransaction();
        return true;
        contentvalues.put("accountName", s);
        if (database.insert("timelysettings", null, contentvalues) == -1L)
        {
            break MISSING_BLOCK_LABEL_267;
        }
        database.setTransactionSuccessful();
        context.getContentResolver().notifyChange(TimelyContract.ACCOUNT_SETTINGS_URI, null);
        database.endTransaction();
        return true;
        database.endTransaction();
        return false;
    }

    public final AccountSettings getSettings(String s)
    {
        if (!cache.containsKey(s))
        {
            return new AccountSettings();
        } else
        {
            return (AccountSettings)cache.get(s);
        }
    }

    public final Bundle updateFromClient(Account account, UpdateBuilder updatebuilder)
    {
        String s = account.name;
        boolean flag = updateDatabase(s, updatebuilder.values, true, updatebuilder.jsonFlags);
        if (flag)
        {
            updateCache(s);
        }
        if (!flag)
        {
            return null;
        }
        if (updatebuilder.triggerSyncAdapterUpdate)
        {
            return TimelyStoreUtils.triggerSyncAdapterSettingUpdate(account);
        } else
        {
            return new Bundle();
        }
    }

    public final boolean updateFromSync(Account account, ContentValues contentvalues)
    {
        account = account.name;
        boolean flag = updateDatabase(account, contentvalues, false, null);
        if (flag)
        {
            updateCache(account);
        }
        return flag;
    }


    private class UpdateBuilder
    {

        public String jsonFlags;
        public boolean triggerSyncAdapterUpdate;
        public final ContentValues values = new ContentValues();

        public final UpdateBuilder setBirthdaySetting(BirthdaySetting birthdaysetting)
        {
            boolean flag = false;
            ContentValues contentvalues = values;
            int i;
            if (birthdaysetting.visibility)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            contentvalues.put("settingBirthdayVisibility", Integer.valueOf(i));
            if (birthdaysetting.visibility)
            {
                ContentValues contentvalues1 = values;
                i = ((flag) ? 1 : 0);
                if (birthdaysetting.includeGplusBirthday)
                {
                    i = 1;
                }
                contentvalues1.put("settingBirthdayIncludeGplus", Integer.valueOf(i));
            }
            triggerSyncAdapterUpdate = true;
            return this;
        }

        public UpdateBuilder()
        {
            triggerSyncAdapterUpdate = false;
        }
    }

}
