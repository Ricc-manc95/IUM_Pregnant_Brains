// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import java.lang.reflect.Array;

public final class LoadDetailsConstants
{

    public static final String ATTENDEES_PROJECTION[] = {
        "attendeeName", "attendeeEmail", "attendeeRelationship", "attendeeStatus", "attendeeIdentity", "attendeeIdNamespace", "attendeeType"
    };
    public static final String CALENDARS_PROJECTION[] = {
        "_id", "name", "calendar_displayName", "ownerAccount", "calendar_color", "calendar_color_index", "canOrganizerRespond", "calendar_access_level", "visible", "sync_events", 
        "maxReminders", "allowedReminders", "allowedAttendeeTypes", "allowedAvailability", "account_name", "account_type", "calendar_location", "calendar_timezone", "(COALESCE(isPrimary, ownerAccount=account_name)) AS isPrimarySelection", "cal_sync9", 
        "cal_sync7", "deleted"
    };
    public static final String CALENDAR_LOCAL_ID_PROJECTION[] = {
        "_id"
    };
    private static final String COMMON_EVENT_PROJECTION[];
    public static final String EVENT_PROJECTION[];
    public static final String EXTENDED_PROPERTIES_PROJECTION[] = {
        "name", "value"
    };
    public static final String INSTANCE_PROJECTION[];
    public static final String REMINDERS_PROJECTION[] = {
        "_id", "minutes", "method"
    };
    public static final String SINGLE_CALENDAR_SELECTION = String.format("%s=? AND %s=? AND %s=?", new Object[] {
        "ownerAccount", "account_name", "account_type"
    });

    static 
    {
        String as[] = new String[41];
        as[0] = "dtstart";
        as[1] = "dtend";
        as[2] = "title";
        as[3] = "description";
        as[4] = "eventLocation";
        as[5] = "allDay";
        as[6] = "hasAlarm";
        as[7] = "calendar_id";
        as[8] = "duration";
        as[9] = "eventTimezone";
        as[10] = "rrule";
        as[11] = "rdate";
        as[12] = "exrule";
        as[13] = "exdate";
        as[14] = "_sync_id";
        as[15] = "availability";
        as[16] = "accessLevel";
        as[17] = "ownerAccount";
        as[18] = "hasAttendeeData";
        as[19] = "original_sync_id";
        as[20] = "organizer";
        as[21] = "guestsCanModify";
        as[22] = "guestsCanInviteOthers";
        as[23] = "original_id";
        as[24] = "originalInstanceTime";
        as[25] = "eventStatus";
        as[26] = "eventColor_index";
        as[27] = "calendar_access_level";
        as[28] = "customAppPackage";
        as[29] = "customAppUri";
        as[30] = "sync_data9 as sync_data9";
        as[31] = "eventEndTimezone";
        as[32] = "sync_data8 as sync_data8";
        as[33] = "account_name";
        as[34] = "account_type";
        as[35] = "deleted";
        as[36] = "guestsCanSeeGuests";
        as[37] = "sync_data2 as sync_data2";
        as[38] = "sync_data4 as sync_data4";
        as[39] = "sync_data1 as sync_data1";
        as[40] = "dirty as dirty";
        COMMON_EVENT_PROJECTION = as;
        String as1[] = new String[2];
        as1[0] = "_id";
        as1[1] = "sync_data6";
        Object aobj[] = (Object[])Array.newInstance(java/lang/String, as.length + as1.length);
        System.arraycopy(as, 0, ((Object) (aobj)), 0, as.length);
        System.arraycopy(as1, 0, ((Object) (aobj)), as.length, as1.length);
        EVENT_PROJECTION = (String[])aobj;
        as = COMMON_EVENT_PROJECTION;
        as1 = new String[3];
        as1[0] = "event_id";
        as1[1] = "begin";
        as1[2] = "end";
        aobj = (Object[])Array.newInstance(java/lang/String, as.length + as1.length);
        System.arraycopy(as, 0, ((Object) (aobj)), 0, as.length);
        System.arraycopy(as1, 0, ((Object) (aobj)), as.length, as1.length);
        INSTANCE_PROJECTION = (String[])aobj;
    }
}
