// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.storage;

import android.content.Context;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.common.collect.ImmutableList;
import java.util.List;

final class UserNotificationDatabaseHelper extends SQLiteOpenHelper
{

    public static final String WHERE_USER_NOTIFICATON_IS = String.format(null, "(%s=? AND %s=? AND %s=? AND %s=?)", new Object[] {
        "pluginId", "entityFingerprint", "notificationType", "notificationTriggerMillis"
    });

    public UserNotificationDatabaseHelper(Context context)
    {
        super(context, "usernotifications.db", null, 2, new DefaultDatabaseErrorHandler());
    }

    private static String getCreateTableQuery(String s, List list, List list1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("CREATE TABLE ").append(s).append("(").append(TextUtils.join(",", list)).append(", UNIQUE (").append(TextUtils.join(",", list1)).append("));");
        return stringbuilder.toString();
    }

    public static String[] getWhereUserNotificationIsArgs(UserNotification usernotification)
    {
        return (new String[] {
            String.valueOf(usernotification.getPluginId()), String.valueOf(usernotification.getEntityFingerprint()), String.valueOf(usernotification.getType()), String.valueOf(usernotification.getTriggerMillis())
        });
    }

    public final void onCreate(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL(getCreateTableQuery("notificationinstances", ImmutableList.of("id INTEGER PRIMARY KEY", "pluginId INTEGER", "entityFingerprint TEXT", "notificationType INTEGER", "notificationTriggerMillis INTEGER", "notificationExpirationMillis INTEGER", "notificationState INTEGER"), ImmutableList.of("pluginId", "entityFingerprint", "notificationType", "notificationTriggerMillis", "notificationExpirationMillis")));
        sqlitedatabase.execSQL(getCreateTableQuery("notificationcheckschedule", ImmutableList.of("id INTEGER PRIMARY KEY", "pluginId INTEGER", "wakingCheckMillis INTEGER", "nonWakingCheckMillis INTEGER"), ImmutableList.of("pluginId")));
    }

    public final void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        switch (i)
        {
        default:
            return;

        case 1: // '\001'
            sqlitedatabase.execSQL(getCreateTableQuery("notificationcheckschedule", ImmutableList.of("id INTEGER PRIMARY KEY", "pluginId INTEGER", "wakingCheckMillis INTEGER", "nonWakingCheckMillis INTEGER"), ImmutableList.of("pluginId")));
            break;
        }
    }

}
