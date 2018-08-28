// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.notification;

import android.content.res.Resources;
import android.support.v4.util.Pair;
import com.google.android.calendar.event.ReminderUtils;
import com.google.android.calendar.newapi.segment.common.IntegerChoiceCreator;
import java.util.ArrayList;
import java.util.List;

public final class GrooveNotificationChoiceCreator extends IntegerChoiceCreator
{

    public static final Integer CUSTOM_CHOICE = Integer.valueOf(-2);
    public static final Integer NO_NOTIFICATION_CHOICE = Integer.valueOf(-1);
    private final String customNotificationString;
    private final ReminderUtils reminderUtils;

    GrooveNotificationChoiceCreator(Resources resources, ReminderUtils reminderutils)
    {
        customNotificationString = resources.getString(0x7f1301a4);
        reminderUtils = reminderutils;
    }

    protected final Pair createFooter()
    {
        return new Pair(customNotificationString, CUSTOM_CHOICE);
    }

    protected final String createLabel(Object obj)
    {
        obj = (Integer)obj;
        return reminderUtils.constructLabel(((Integer) (obj)).intValue(), 1, false);
    }

    public final com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList createList(List list, Integer integer)
    {
        list = new ArrayList(list);
        if (integer != null && !list.contains(integer))
        {
            list.add(integer);
        }
        list = super.createList(list);
        if (integer != null)
        {
            list.selectedPosition = ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (list)).values.indexOf(integer);
            return list;
        } else
        {
            list.selectedPosition = 0;
            return list;
        }
    }

}
