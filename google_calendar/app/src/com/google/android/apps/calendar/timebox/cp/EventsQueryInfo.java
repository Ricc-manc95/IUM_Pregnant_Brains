// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.cp;


public final class EventsQueryInfo
{

    public static final String PROJECTION[];

    public static String getInstancesSelection(boolean flag, boolean flag1)
    {
        String s;
        String s1;
        if (flag)
        {
            s = " AND selfAttendeeStatus!=2";
        } else
        {
            s = "";
        }
        if (flag1)
        {
            s1 = " AND account_type!='com.google'";
        } else
        {
            s1 = "";
        }
        return (new StringBuilder(String.valueOf(s).length() + 86 + String.valueOf(s1).length())).append("visible=1 AND (account_type!='com.google' OR sync_data9 & 256=0 OR sync_data9 IS NULL)").append(s).append(s1).toString();
    }

    static 
    {
        PROJECTION = new String[Column.values().length];
        Column acolumn[] = Column.values();
        int j = acolumn.length;
        for (int i = 0; i < j; i++)
        {
            Column column = acolumn[i];
            PROJECTION[column.ordinal()] = column.column;
        }

    }

    private class Column extends Enum
    {

        private static final Column $VALUES[];
        public static final Column ACCESS_LEVEL;
        public static final Column ACCOUNT_NAME;
        public static final Column ACCOUNT_TYPE;
        public static final Column ALL_DAY;
        public static final Column BEGIN;
        public static final Column CALENDAR_ACCESS_LEVEL;
        public static final Column CALENDAR_ID;
        public static final Column DIRTY;
        public static final Column DISPLAY_COLOR;
        public static final Column END;
        public static final Column END_DAY;
        public static final Column END_MINUTE;
        public static final Column EVENT_EXTRAS_FLAGS;
        public static final Column EVENT_ID;
        public static final Column EVENT_LOCATION;
        public static final Column HABIT_ID_AND_TYPE;
        public static final Column ORGANIZER;
        public static final Column ORIGINAL_ID;
        public static final Column ORIGINAL_INSTANCE_TIME;
        public static final Column OWNER_ACCOUNT;
        public static final Column RRULE;
        public static final Column SELF_ATTENDEE_STATUS;
        public static final Column START_DAY;
        public static final Column START_MINUTE;
        public static final Column SYNC_ID;
        public static final Column TITLE;
        public final String column;
        public final boolean internStrings;

        public static Column[] values()
        {
            return (Column[])$VALUES.clone();
        }

        static 
        {
            START_DAY = new Column("START_DAY", 0, "startDay", false);
            END_DAY = new Column("END_DAY", 1, "endDay", false);
            ALL_DAY = new Column("ALL_DAY", 2, "allDay", false);
            START_MINUTE = new Column("START_MINUTE", 3, "startMinute", false);
            END_MINUTE = new Column("END_MINUTE", 4, "endMinute", false);
            BEGIN = new Column("BEGIN", 5, "begin", false);
            ORIGINAL_INSTANCE_TIME = new Column("ORIGINAL_INSTANCE_TIME", 6, "originalInstanceTime", false);
            END = new Column("END", 7, "end", false);
            DISPLAY_COLOR = new Column("DISPLAY_COLOR", 8, "displayColor", false);
            TITLE = new Column("TITLE", 9, "title", false);
            EVENT_LOCATION = new Column("EVENT_LOCATION", 10, "eventLocation", false);
            SELF_ATTENDEE_STATUS = new Column("SELF_ATTENDEE_STATUS", 11, "selfAttendeeStatus", false);
            EVENT_ID = new Column("EVENT_ID", 12, "event_id", false);
            CALENDAR_ID = new Column("CALENDAR_ID", 13, "calendar_id", false);
            ORIGINAL_ID = new Column("ORIGINAL_ID", 14, "original_id", false);
            ORGANIZER = new Column("ORGANIZER", 15, "organizer", true);
            OWNER_ACCOUNT = new Column("OWNER_ACCOUNT", 16, "ownerAccount", true);
            ACCESS_LEVEL = new Column("ACCESS_LEVEL", 17, "accessLevel", false);
            CALENDAR_ACCESS_LEVEL = new Column("CALENDAR_ACCESS_LEVEL", 18, "calendar_access_level", false);
            ACCOUNT_TYPE = new Column("ACCOUNT_TYPE", 19, "account_type", true);
            ACCOUNT_NAME = new Column("ACCOUNT_NAME", 20, "account_name", true);
            EVENT_EXTRAS_FLAGS = new Column("EVENT_EXTRAS_FLAGS", 21, "sync_data9 as sync_data9", false);
            HABIT_ID_AND_TYPE = new Column("HABIT_ID_AND_TYPE", 22, "sync_data8 as sync_data8", true);
            RRULE = new Column("RRULE", 23, "rrule", false);
            SYNC_ID = new Column("SYNC_ID", 24, "_sync_id", false);
            DIRTY = new Column("DIRTY", 25, "dirty as dirty", false);
            $VALUES = (new Column[] {
                START_DAY, END_DAY, ALL_DAY, START_MINUTE, END_MINUTE, BEGIN, ORIGINAL_INSTANCE_TIME, END, DISPLAY_COLOR, TITLE, 
                EVENT_LOCATION, SELF_ATTENDEE_STATUS, EVENT_ID, CALENDAR_ID, ORIGINAL_ID, ORGANIZER, OWNER_ACCOUNT, ACCESS_LEVEL, CALENDAR_ACCESS_LEVEL, ACCOUNT_TYPE, 
                ACCOUNT_NAME, EVENT_EXTRAS_FLAGS, HABIT_ID_AND_TYPE, RRULE, SYNC_ID, DIRTY
            });
        }

        private Column(String s, int i, String s1, boolean flag)
        {
            super(s, i);
            column = s1;
            internStrings = flag;
        }
    }

}
