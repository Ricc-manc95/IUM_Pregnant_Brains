// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;

final class MonthDayViewPositionHelper
{

    private static final int MAX_POS;
    private static final int MIN_POS;
    private static final int POS_BUCKET;

    static int fromJulianDay(int i, boolean flag)
    {
        if (flag)
        {
            return MIN_POS + i;
        } else
        {
            return MIN_POS + POS_BUCKET + i;
        }
    }

    static boolean isInCurrentMonth(int i)
    {
        return i - MIN_POS < POS_BUCKET;
    }

    static int toJulianDay(int i)
    {
        return (i - MIN_POS) % POS_BUCKET;
    }

    static 
    {
        MIN_POS = CalendarViewType.MONTH_VIEW_DAY_HEADER.minPosition;
        int i = CalendarViewType.MONTH_VIEW_DAY_HEADER.maxPosition;
        MAX_POS = i;
        POS_BUCKET = (i - MIN_POS) / 2;
    }
}
