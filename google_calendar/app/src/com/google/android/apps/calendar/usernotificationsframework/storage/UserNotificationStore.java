// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.usernotificationsframework.contracts.AutoValue_UserNotification;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationCheckSchedule;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationState;
import com.google.common.base.Optional;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.storage:
//            UserNotificationDatabaseHelper

public class UserNotificationStore
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/usernotificationsframework/storage/UserNotificationStore);
    public final SQLiteDatabase database;

    public UserNotificationStore(Context context)
    {
        database = (new UserNotificationDatabaseHelper(context)).getWritableDatabase();
    }

    public static void cleanup(Context context)
    {
        try
        {
            context = context.getDatabasePath("usernotifications.db");
            if (context.exists())
            {
                SQLiteDatabase.deleteDatabase(context);
            }
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.e(TAG, context, "Failed to delete the database.", new Object[0]);
        }
    }

    public static boolean exists(Context context)
    {
        return context.getDatabasePath("usernotifications.db").exists();
    }

    public final void deleteOutdatedNotifications(long l, long l1)
    {
        try
        {
            database.delete("notificationinstances", "notificationExpirationMillis<?", new String[] {
                String.valueOf(l - l1)
            });
            return;
        }
        catch (Exception exception)
        {
            LogUtils.e(TAG, exception, "Failed to delete outdated notifications.", new Object[0]);
        }
    }

    public final UserNotificationCheckSchedule getNotificationCheckSchedule(int i)
    {
        Object obj2 = database.query("notificationcheckschedule", null, "pluginId=?", new String[] {
            String.valueOf(i)
        }, null, null, null);
        if (obj2 == null) goto _L2; else goto _L1
_L1:
        if (!((Cursor) (obj2)).moveToFirst()) goto _L2; else goto _L3
_L3:
        Object obj;
        obj = new ContentValues();
        DatabaseUtils.cursorRowToContentValues(((Cursor) (obj2)), ((ContentValues) (obj)));
        obj = UserNotificationCheckSchedule.create(((ContentValues) (obj)).getAsInteger("pluginId").intValue(), ((ContentValues) (obj)).getAsLong("wakingCheckMillis"), ((ContentValues) (obj)).getAsLong("nonWakingCheckMillis"));
        if (obj2 == null) goto _L5; else goto _L4
_L4:
        if (true) goto _L7; else goto _L6
_L6:
        ((Cursor) (obj2)).close();
_L5:
        return ((UserNotificationCheckSchedule) (obj));
        Throwable throwable;
        throwable;
        ThrowableExtension.STRATEGY.addSuppressed(null, throwable);
        return ((UserNotificationCheckSchedule) (obj));
_L9:
        return UserNotificationCheckSchedule.EMPTY;
_L7:
        ((Cursor) (obj2)).close();
        return ((UserNotificationCheckSchedule) (obj));
_L2:
        if (obj2 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (true)
        {
            break MISSING_BLOCK_LABEL_170;
        }
        ((Cursor) (obj2)).close();
        continue; /* Loop/switch isn't completed */
        Object obj1;
        obj1;
        try
        {
            ThrowableExtension.STRATEGY.addSuppressed(null, ((Throwable) (obj1)));
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            LogUtils.e(TAG, ((Throwable) (obj)), "Failed on getting notification check schedule.", new Object[0]);
        }
        continue; /* Loop/switch isn't completed */
        ((Cursor) (obj2)).close();
        if (true) goto _L9; else goto _L8
_L8:
        throwable;
        throw throwable;
        obj1;
_L12:
        if (obj2 == null)
        {
            break MISSING_BLOCK_LABEL_200;
        }
        if (throwable == null)
        {
            break MISSING_BLOCK_LABEL_216;
        }
        ((Cursor) (obj2)).close();
_L10:
        throw obj1;
        obj2;
        ThrowableExtension.STRATEGY.addSuppressed(throwable, ((Throwable) (obj2)));
          goto _L10
        ((Cursor) (obj2)).close();
          goto _L10
        obj1;
        throwable = null;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public final UserNotificationState getNotificationState(UserNotification usernotification)
    {
        if (usernotification != null) goto _L2; else goto _L1
_L1:
        usernotification = UserNotificationState.NOT_FIRED;
_L7:
        return usernotification;
_L2:
        Object obj;
        SQLiteDatabase sqlitedatabase = database;
        obj = UserNotificationDatabaseHelper.WHERE_USER_NOTIFICATON_IS;
        usernotification = UserNotificationDatabaseHelper.getWhereUserNotificationIsArgs(usernotification);
        obj = sqlitedatabase.query("notificationinstances", new String[] {
            "notificationState"
        }, ((String) (obj)), usernotification, null, null, null);
        if (obj == null) goto _L4; else goto _L3
_L3:
        if (!((Cursor) (obj)).moveToFirst()) goto _L4; else goto _L5
_L5:
        UserNotificationState usernotificationstate;
        int i = ((Cursor) (obj)).getInt(0);
        usernotificationstate = UserNotificationState.values()[i];
        usernotification = usernotificationstate;
        if (obj == null) goto _L7; else goto _L6
_L6:
        if (true) goto _L9; else goto _L8
_L8:
        ((Cursor) (obj)).close();
        return usernotificationstate;
        usernotification;
        ThrowableExtension.STRATEGY.addSuppressed(null, usernotification);
        return usernotificationstate;
_L11:
        return UserNotificationState.NOT_FIRED;
_L9:
        ((Cursor) (obj)).close();
        return usernotificationstate;
_L4:
        if (obj == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (true)
        {
            break MISSING_BLOCK_LABEL_159;
        }
        ((Cursor) (obj)).close();
        continue; /* Loop/switch isn't completed */
        usernotification;
        try
        {
            ThrowableExtension.STRATEGY.addSuppressed(null, usernotification);
        }
        // Misplaced declaration of an exception variable
        catch (UserNotification usernotification)
        {
            LogUtils.e(TAG, usernotification, "Failed on obtaining notification state.", new Object[0]);
        }
        continue; /* Loop/switch isn't completed */
        ((Cursor) (obj)).close();
        if (true) goto _L11; else goto _L10
_L10:
        Throwable throwable;
        throwable;
        throw throwable;
        usernotification;
_L14:
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_186;
        }
        if (throwable == null)
        {
            break MISSING_BLOCK_LABEL_200;
        }
        ((Cursor) (obj)).close();
_L12:
        throw usernotification;
        obj;
        ThrowableExtension.STRATEGY.addSuppressed(throwable, ((Throwable) (obj)));
          goto _L12
        ((Cursor) (obj)).close();
          goto _L12
        usernotification;
        throwable = null;
        if (true) goto _L14; else goto _L13
_L13:
    }

    public final Map getNotifications(int i)
    {
        HashMap hashmap = new HashMap();
        Object obj1 = database.query("notificationinstances", null, "pluginId=?", new String[] {
            String.valueOf(i)
        }, null, null, null);
        if (obj1 == null) goto _L2; else goto _L1
_L1:
        AutoValue_UserNotification autovalue_usernotification;
        for (; ((Cursor) (obj1)).moveToNext(); hashmap.put(autovalue_usernotification, UserNotificationState.values()[i]))
        {
            ContentValues contentvalues = new ContentValues();
            DatabaseUtils.cursorRowToContentValues(((Cursor) (obj1)), contentvalues);
            autovalue_usernotification = new AutoValue_UserNotification(contentvalues.getAsInteger("pluginId").intValue(), contentvalues.getAsString("entityFingerprint"), contentvalues.getAsInteger("notificationType").intValue(), contentvalues.getAsLong("notificationTriggerMillis").longValue(), contentvalues.getAsLong("notificationExpirationMillis").longValue());
            i = contentvalues.getAsInteger("notificationState").intValue();
        }

          goto _L2
        Throwable throwable;
        throwable;
        throw throwable;
        Object obj;
        obj;
_L10:
        if (obj1 == null) goto _L4; else goto _L3
_L3:
        if (throwable == null) goto _L6; else goto _L5
_L5:
        ((Cursor) (obj1)).close();
_L4:
        throw obj;
        obj;
        LogUtils.e(TAG, ((Throwable) (obj)), "Failed on getting notifications.", new Object[0]);
_L8:
        return hashmap;
_L2:
        if (obj1 == null) goto _L8; else goto _L7
_L7:
        if (true)
        {
            break MISSING_BLOCK_LABEL_212;
        }
        ((Cursor) (obj1)).close();
        return hashmap;
        obj;
        ThrowableExtension.STRATEGY.addSuppressed(null, ((Throwable) (obj)));
        return hashmap;
        ((Cursor) (obj1)).close();
        return hashmap;
        obj1;
        ThrowableExtension.STRATEGY.addSuppressed(throwable, ((Throwable) (obj1)));
          goto _L4
_L6:
        ((Cursor) (obj1)).close();
          goto _L4
        obj;
        throwable = null;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final void setNotificationCheckSchedule(UserNotificationCheckSchedule usernotificationcheckschedule)
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("pluginId", Integer.valueOf(usernotificationcheckschedule.getPluginId()));
        contentvalues.put("wakingCheckMillis", (Long)usernotificationcheckschedule.getWakingCheckMillis().orNull());
        contentvalues.put("nonWakingCheckMillis", (Long)usernotificationcheckschedule.getNonWakingCheckMillis().orNull());
        try
        {
            database.insertWithOnConflict("notificationcheckschedule", null, contentvalues, 5);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (UserNotificationCheckSchedule usernotificationcheckschedule)
        {
            LogUtils.e(TAG, usernotificationcheckschedule, "Failed on updating notification check schedule.", new Object[0]);
        }
    }

    public final boolean setNotificationState(UserNotification usernotification, UserNotificationState usernotificationstate)
    {
        ContentValues contentvalues;
        if (usernotification == null)
        {
            return false;
        }
        contentvalues = new ContentValues();
        contentvalues.put("pluginId", Integer.valueOf(usernotification.getPluginId()));
        contentvalues.put("entityFingerprint", usernotification.getEntityFingerprint());
        contentvalues.put("notificationType", Integer.valueOf(usernotification.getType()));
        contentvalues.put("notificationTriggerMillis", Long.valueOf(usernotification.getTriggerMillis()));
        contentvalues.put("notificationExpirationMillis", Long.valueOf(usernotification.getExpirationMillis()));
        contentvalues.put("notificationState", Integer.valueOf(usernotificationstate.ordinal()));
        database.beginTransaction();
        database.delete("notificationinstances", UserNotificationDatabaseHelper.WHERE_USER_NOTIFICATON_IS, UserNotificationDatabaseHelper.getWhereUserNotificationIsArgs(usernotification));
        database.insert("notificationinstances", null, contentvalues);
        database.setTransactionSuccessful();
        database.endTransaction();
        return true;
        usernotification;
        LogUtils.e(TAG, usernotification, "Failed on updating notification state.", new Object[0]);
        database.endTransaction();
        return false;
        usernotification;
        database.endTransaction();
        throw usernotification;
    }

}
