// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.syncadapters.timely.sql;


// Referenced classes of package com.google.android.apps.calendar.syncadapters.timely.sql:
//            SQLiteDatabaseUtils

public final class ColumnConstants
{

    public static final String WHERE_ACCOUNT_AND_COLOR = SQLiteDatabaseUtils.makeWhere(new String[] {
        WHERE_ACCOUNT_AND_COLOR_TYPE, "color_index=?"
    });
    public static final String WHERE_ACCOUNT_AND_COLOR_TYPE = SQLiteDatabaseUtils.makeWhere(new String[] {
        WHERE_ACCOUNT_AND_TYPE, "color_type=?"
    });
    public static final String WHERE_ACCOUNT_AND_SYNC = SQLiteDatabaseUtils.makeWhere(new String[] {
        WHERE_ACCOUNT_AND_TYPE, "sync_events=?"
    });
    public static final String WHERE_ACCOUNT_AND_TYPE = SQLiteDatabaseUtils.makeWhere(new String[] {
        "account_name=?", "account_type=?"
    });
    public static final String WHERE_ACCOUNT_AND_TYPE_AND_CALENDAR_SYNC_ID = SQLiteDatabaseUtils.makeWhere(new String[] {
        "account_name=?", "account_type=?", "cal_sync1=?"
    });
    public static final String WHERE_ACCOUNT_AND_TYPE_AND_IS_PRIMARY_TRUE = SQLiteDatabaseUtils.makeWhere(new String[] {
        "account_name=?", "account_type=?", "COALESCE(isPrimary,account_name=ownerAccount)=1"
    });
    public static final String WHERE_CALENDARS_ACCOUNT_AND_COLOR = SQLiteDatabaseUtils.makeWhere(new String[] {
        WHERE_ACCOUNT_AND_TYPE, "calendar_color_index=?"
    });
    public static final String WHERE_DT_START_GT_AND_EVENTS_CALENDAR_ID = SQLiteDatabaseUtils.makeWhere(new String[] {
        "dtstart>?", "calendar_id=?"
    });

}
