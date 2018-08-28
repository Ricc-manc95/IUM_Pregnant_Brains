// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.cp;


public final class internStrings extends Enum
{

    private static final DIRTY $VALUES[];
    public static final DIRTY ACCESS_LEVEL;
    public static final DIRTY ACCOUNT_NAME;
    public static final DIRTY ACCOUNT_TYPE;
    public static final DIRTY ALL_DAY;
    public static final DIRTY BEGIN;
    public static final DIRTY CALENDAR_ACCESS_LEVEL;
    public static final DIRTY CALENDAR_ID;
    public static final DIRTY DIRTY;
    public static final DIRTY DISPLAY_COLOR;
    public static final DIRTY END;
    public static final DIRTY END_DAY;
    public static final DIRTY END_MINUTE;
    public static final DIRTY EVENT_EXTRAS_FLAGS;
    public static final DIRTY EVENT_ID;
    public static final DIRTY EVENT_LOCATION;
    public static final DIRTY HABIT_ID_AND_TYPE;
    public static final DIRTY ORGANIZER;
    public static final DIRTY ORIGINAL_ID;
    public static final DIRTY ORIGINAL_INSTANCE_TIME;
    public static final DIRTY OWNER_ACCOUNT;
    public static final DIRTY RRULE;
    public static final DIRTY SELF_ATTENDEE_STATUS;
    public static final DIRTY START_DAY;
    public static final DIRTY START_MINUTE;
    public static final DIRTY SYNC_ID;
    public static final DIRTY TITLE;
    public final String column;
    public final boolean internStrings;

    public static internStrings[] values()
    {
        return (internStrings[])$VALUES.clone();
    }

    static 
    {
        START_DAY = new <init>("START_DAY", 0, "startDay", false);
        END_DAY = new <init>("END_DAY", 1, "endDay", false);
        ALL_DAY = new <init>("ALL_DAY", 2, "allDay", false);
        START_MINUTE = new <init>("START_MINUTE", 3, "startMinute", false);
        END_MINUTE = new <init>("END_MINUTE", 4, "endMinute", false);
        BEGIN = new <init>("BEGIN", 5, "begin", false);
        ORIGINAL_INSTANCE_TIME = new <init>("ORIGINAL_INSTANCE_TIME", 6, "originalInstanceTime", false);
        END = new <init>("END", 7, "end", false);
        DISPLAY_COLOR = new <init>("DISPLAY_COLOR", 8, "displayColor", false);
        TITLE = new <init>("TITLE", 9, "title", false);
        EVENT_LOCATION = new <init>("EVENT_LOCATION", 10, "eventLocation", false);
        SELF_ATTENDEE_STATUS = new <init>("SELF_ATTENDEE_STATUS", 11, "selfAttendeeStatus", false);
        EVENT_ID = new <init>("EVENT_ID", 12, "event_id", false);
        CALENDAR_ID = new <init>("CALENDAR_ID", 13, "calendar_id", false);
        ORIGINAL_ID = new <init>("ORIGINAL_ID", 14, "original_id", false);
        ORGANIZER = new <init>("ORGANIZER", 15, "organizer", true);
        OWNER_ACCOUNT = new <init>("OWNER_ACCOUNT", 16, "ownerAccount", true);
        ACCESS_LEVEL = new <init>("ACCESS_LEVEL", 17, "accessLevel", false);
        CALENDAR_ACCESS_LEVEL = new <init>("CALENDAR_ACCESS_LEVEL", 18, "calendar_access_level", false);
        ACCOUNT_TYPE = new <init>("ACCOUNT_TYPE", 19, "account_type", true);
        ACCOUNT_NAME = new <init>("ACCOUNT_NAME", 20, "account_name", true);
        EVENT_EXTRAS_FLAGS = new <init>("EVENT_EXTRAS_FLAGS", 21, "sync_data9 as sync_data9", false);
        HABIT_ID_AND_TYPE = new <init>("HABIT_ID_AND_TYPE", 22, "sync_data8 as sync_data8", true);
        RRULE = new <init>("RRULE", 23, "rrule", false);
        SYNC_ID = new <init>("SYNC_ID", 24, "_sync_id", false);
        DIRTY = new <init>("DIRTY", 25, "dirty as dirty", false);
        $VALUES = (new .VALUES[] {
            START_DAY, END_DAY, ALL_DAY, START_MINUTE, END_MINUTE, BEGIN, ORIGINAL_INSTANCE_TIME, END, DISPLAY_COLOR, TITLE, 
            EVENT_LOCATION, SELF_ATTENDEE_STATUS, EVENT_ID, CALENDAR_ID, ORIGINAL_ID, ORGANIZER, OWNER_ACCOUNT, ACCESS_LEVEL, CALENDAR_ACCESS_LEVEL, ACCOUNT_TYPE, 
            ACCOUNT_NAME, EVENT_EXTRAS_FLAGS, HABIT_ID_AND_TYPE, RRULE, SYNC_ID, DIRTY
        });
    }

    private (String s, int i, String s1, boolean flag)
    {
        super(s, i);
        column = s1;
        internStrings = flag;
    }
}
