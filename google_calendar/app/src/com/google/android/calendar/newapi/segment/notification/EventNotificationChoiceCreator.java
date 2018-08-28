// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.notification;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.util.Pair;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.event.ReminderUtils;
import com.google.android.calendar.newapi.segment.common.ChoiceCreator;
import java.util.ArrayList;

public final class EventNotificationChoiceCreator extends ChoiceCreator
{

    private static final Notification CUSTOM_CHOICE = new Notification(1, -1);
    private final boolean allDay;
    private final String customNotificationString;
    private final ReminderUtils reminderUtils;

    public EventNotificationChoiceCreator(Context context, boolean flag)
    {
        allDay = flag;
        customNotificationString = context.getResources().getString(0x7f1301a4);
        reminderUtils = new ReminderUtils(context);
    }

    public static boolean isCustomNotification(Notification notification)
    {
        Notification notification1 = CUSTOM_CHOICE;
        return notification == notification1 || notification != null && notification.equals(notification1);
    }

    public final int compare(Object obj, Object obj1)
    {
        boolean flag;
        byte byte0;
        flag = false;
        byte0 = -1;
        obj = (Notification)obj;
        obj1 = (Notification)obj1;
        if (obj != null && obj1 != null) goto _L2; else goto _L1
_L1:
        if (obj != null) goto _L4; else goto _L3
_L3:
        int i = -1;
_L6:
        return i;
_L4:
        return 1;
_L2:
        i = ((Notification) (obj)).minutesBefore;
        int j = ((Notification) (obj1)).minutesBefore;
        if (i < j)
        {
            i = -1;
        } else
        if (i > j)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            return i;
        }
        j = ((Notification) (obj)).method;
        int k = ((Notification) (obj1)).method;
        i = byte0;
        if (j >= k)
        {
            i = ((flag) ? 1 : 0);
            if (j > k)
            {
                return 1;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected final Pair createFooter()
    {
        return new Pair(customNotificationString, CUSTOM_CHOICE);
    }

    protected final String createLabel(Object obj)
    {
        obj = (Notification)obj;
        return reminderUtils.constructLabel(((Notification) (obj)).minutesBefore, ((Notification) (obj)).method, allDay);
    }

    public final com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList createList(ArrayList arraylist, Notification notification)
    {
        arraylist = new ArrayList(arraylist);
        if (!arraylist.contains(notification) && notification != null)
        {
            arraylist.add(notification);
        }
        arraylist = super.createList(arraylist);
        arraylist.selectedPosition = ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (arraylist)).values.indexOf(notification);
        return arraylist;
    }

}
