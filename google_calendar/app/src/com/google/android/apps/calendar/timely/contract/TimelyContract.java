// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.contract;

import android.net.Uri;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TimelyContract
{

    public static final Uri ACCOUNT_SETTINGS_URI = Uri.parse("content://com.google.android.timely/accountsettings");
    public static final Uri AUTHORITY_URI = Uri.parse("content://com.google.android.timely");
    public static final Map DEFAULT_METHOD_LABELS_TO_VALUES;
    public static final Map DEFAULT_METHOD_VALUES_TO_LABELS;
    public static final String EVENT_EXTRAS_PROJECTION[] = {
        "structuredLocation", "smartmail", "eventBackgroundUrl", "associatedContacts", "eventGadget", "titleContacts", "eventSource", "attachments", "conferenceData", "responseSummary", 
        "participantStatus", "attendees"
    };
    public static final Uri EVENT_EXTRAS_URI = Uri.parse("content://com.google.android.timely/eventextras");
    public static final Map METHOD_LABELS_TO_VALUES;
    public static final Map METHOD_VALUES_TO_LABELS;
    public static final String NOTIFICATIONS_PROJECTION[] = {
        "allday", "method", "minutes", "timestamp", "lookupKey", "category", "noNotifications"
    };
    public static final Uri TASKS_CALENDAR_URI = Uri.parse("content://com.google.android.timely/tasks");
    public static final Uri TIMELY_BASE_PROVIDER_URI = Uri.parse("content://com.google.android.timely");
    public static final List VIRTUAL_FEED_SUFFIXES;

    public static String apiaryToLocalSettingField(String s)
    {
        if ("smartMailDelivery".equals(s) || "defaultEventLength".equals(s) || "defaultNoEndTime".equals(s) || "autoAddHangouts".equals(s) || "timezone".equals(s) || "qualityOfService".equals(s))
        {
            return s;
        }
        if ("goocal.taskscolor".equals(s))
        {
            return "taskscolor";
        }
        if ("goocal.holidayscolor".equals(s))
        {
            return "holidayscolor";
        } else
        {
            return null;
        }
    }

    public static String apiaryToLocalSettingValue(String s, String s1)
    {
label0:
        {
            String s2;
label1:
            {
                if (!"defaultNoEndTime".equals(s))
                {
                    s2 = s1;
                    if (!"autoAddHangouts".equals(s))
                    {
                        break label1;
                    }
                }
                if (!"true".equals(s1))
                {
                    break label0;
                }
                s2 = "1";
            }
            return s2;
        }
        return "0";
    }

    public static String localSettingValueToApiary(String s, String s1)
    {
label0:
        {
            String s2 = s1;
            if ("defaultNoEndTime".equals(s))
            {
                if (!"1".equals(s1))
                {
                    break label0;
                }
                s2 = "true";
            }
            return s2;
        }
        return "false";
    }

    static 
    {
        ArrayList arraylist = new ArrayList();
        VIRTUAL_FEED_SUFFIXES = arraylist;
        arraylist.add("_settings");
        METHOD_LABELS_TO_VALUES = new HashMap();
        METHOD_VALUES_TO_LABELS = new HashMap();
        METHOD_LABELS_TO_VALUES.put("ALERT", Integer.valueOf(1));
        METHOD_LABELS_TO_VALUES.put("EMAIL", Integer.valueOf(2));
        METHOD_LABELS_TO_VALUES.put("SMS", Integer.valueOf(3));
        METHOD_VALUES_TO_LABELS.put(Integer.valueOf(1), "ALERT");
        METHOD_VALUES_TO_LABELS.put(Integer.valueOf(2), "EMAIL");
        METHOD_VALUES_TO_LABELS.put(Integer.valueOf(3), "SMS");
        DEFAULT_METHOD_LABELS_TO_VALUES = new HashMap();
        DEFAULT_METHOD_VALUES_TO_LABELS = new HashMap();
        DEFAULT_METHOD_LABELS_TO_VALUES.put("popup", Integer.valueOf(1));
        DEFAULT_METHOD_LABELS_TO_VALUES.put("email", Integer.valueOf(2));
        DEFAULT_METHOD_LABELS_TO_VALUES.put("sms", Integer.valueOf(3));
        DEFAULT_METHOD_VALUES_TO_LABELS.put(Integer.valueOf(1), "popup");
        DEFAULT_METHOD_VALUES_TO_LABELS.put(Integer.valueOf(2), "email");
        DEFAULT_METHOD_VALUES_TO_LABELS.put(Integer.valueOf(3), "sms");
    }
}
