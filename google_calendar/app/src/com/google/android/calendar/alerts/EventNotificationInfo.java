// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventKey;

final class EventNotificationInfo
{

    public final boolean allDay;
    public final long endTime;
    public final boolean endTimeUnspecified;
    public final EventKey eventKey;
    public final String eventName;
    public final boolean everyoneDeclinedAndNotDismissed;
    public final String location;
    public final boolean newAlert;
    public final long startTime;
    public final String tag;
    public final UserNotification userNotification;

    EventNotificationInfo(long l, String s, String s1, long l1, boolean flag, 
            long l2, long l3, boolean flag1, boolean flag2, boolean flag3)
    {
        this(((EventKey) (CpEventKey.newInstance(l, l1))), s, s1, l1, flag, l2, flag1, flag2, flag3, String.format(null, "%s-%s-%s-%s", new Object[] {
            Long.valueOf(l), Long.valueOf(l1), Long.valueOf(l2), Long.valueOf(l3)
        }), null);
    }

    EventNotificationInfo(EventKey eventkey, String s, String s1, long l, boolean flag, long l1, boolean flag1, boolean flag2, UserNotification usernotification)
    {
        this(eventkey, s, s1, l, flag, l1, flag1, false, flag2, getNotificationTag(usernotification), usernotification);
    }

    private EventNotificationInfo(EventKey eventkey, String s, String s1, long l, boolean flag, long l1, boolean flag1, boolean flag2, boolean flag3, String s2, UserNotification usernotification)
    {
        eventKey = eventkey;
        eventName = s;
        location = s1;
        startTime = l;
        endTimeUnspecified = flag;
        endTime = l1;
        newAlert = flag2;
        allDay = flag1;
        everyoneDeclinedAndNotDismissed = flag3;
        tag = s2;
        userNotification = usernotification;
    }

    static String getNotificationTag(UserNotification usernotification)
    {
        return String.format(null, "%s-%s-%s", new Object[] {
            usernotification.getEntityFingerprint(), Integer.valueOf(usernotification.getPluginId()), Integer.valueOf(usernotification.getType())
        });
    }
}
