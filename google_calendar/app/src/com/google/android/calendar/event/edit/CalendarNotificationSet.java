// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.edit;

import com.google.calendar.v2.client.service.api.common.Reminder;
import com.google.calendar.v2.client.service.api.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class CalendarNotificationSet
    implements Comparator
{

    public List allDayNotifications;
    public List allDayOptions;
    public List timedNotifications;
    public List timedOptions;

    public CalendarNotificationSet()
    {
        allDayNotifications = new ArrayList();
        allDayOptions = new ArrayList();
        timedNotifications = new ArrayList();
        timedOptions = new ArrayList();
    }

    public final int compare(Object obj, Object obj1)
    {
        obj = (Reminder)obj;
        obj1 = (Reminder)obj1;
        long l = ((Reminder) (obj)).before.millis;
        for (long l1 = ((Reminder) (obj1)).before.millis; l < l1 || l <= l1 && ((Reminder) (obj)).deliveryMethod.ordinal() < ((Reminder) (obj1)).deliveryMethod.ordinal();)
        {
            return -1;
        }

        return 1;
    }
}
