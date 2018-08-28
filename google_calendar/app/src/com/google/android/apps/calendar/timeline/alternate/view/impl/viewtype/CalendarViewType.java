// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype:
//            CalendarViewPositionAllocator

public final class CalendarViewType extends Enum
{

    private static final CalendarViewType $VALUES[];
    public static final CalendarViewType ALL_DAY_CLICK_GUARD;
    public static final CalendarViewType ALL_DAY_EXPAND;
    public static final CalendarViewType ALL_DAY_MORE_HEADER;
    public static final CalendarViewType CREATE_EVENT;
    public static final CalendarViewType DAY_HEADER;
    public static final CalendarViewType DRAG_EVENT;
    public static final CalendarViewType EVENT;
    public static final CalendarViewType HOURS;
    public static final CalendarViewType MONTH_BANNER;
    public static final CalendarViewType MONTH_VIEW_DAY_HEADER;
    public static final CalendarViewType NOTHING_PLANNED_BANNER;
    public static final CalendarViewType NOW_LINE;
    public static final CalendarViewType VIRTUAL_TIMED_EVENTS;
    public static final CalendarViewType WEEK_BANNER;
    public static final CalendarViewType WEEK_NUMBER;
    private static final CalendarViewType values[] = values();
    public final int defaultZOrder;
    public final int maxPosition;
    public final int minPosition;

    private CalendarViewType(String s, int i, int j, int k)
    {
        super(s, i);
        minPosition = CalendarViewPositionAllocator.nextAvailablePosition;
        if (j >= 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException();
        }
        i = CalendarViewPositionAllocator.nextAvailablePosition;
        int l = i + j;
        if (((i ^ l) & (j ^ l)) < 0)
        {
            throw new ArithmeticException("int overflow");
        } else
        {
            CalendarViewPositionAllocator.nextAvailablePosition = l;
            maxPosition = CalendarViewPositionAllocator.nextAvailablePosition - 1;
            defaultZOrder = k;
            return;
        }
    }

    public static CalendarViewType forPosition(int i)
    {
        CalendarViewType acalendarviewtype[] = values;
        int k = acalendarviewtype.length;
        boolean flag;
        for (int j = 0; j < k; j++)
        {
            CalendarViewType calendarviewtype = acalendarviewtype[j];
            if (i >= calendarviewtype.minPosition && i <= calendarviewtype.maxPosition)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return calendarviewtype;
            }
        }

        throw new IllegalArgumentException((new StringBuilder(34)).append("Unknown position type: ").append(i).toString());
    }

    public static CalendarViewType[] values()
    {
        return (CalendarViewType[])$VALUES.clone();
    }

    static 
    {
        ALL_DAY_EXPAND = new CalendarViewType("ALL_DAY_EXPAND", 0, 100, 1000);
        DRAG_EVENT = new CalendarViewType("DRAG_EVENT", 1, 2, 0);
        CREATE_EVENT = new CalendarViewType("CREATE_EVENT", 2, 1, 900);
        NOW_LINE = new CalendarViewType("NOW_LINE", 3, 1, -1);
        WEEK_NUMBER = new CalendarViewType("WEEK_NUMBER", 4, 1, 0);
        ALL_DAY_CLICK_GUARD = new CalendarViewType("ALL_DAY_CLICK_GUARD", 5, 1, 999);
        HOURS = new CalendarViewType("HOURS", 6, 100, -2);
        DAY_HEADER = new CalendarViewType("DAY_HEADER", 7, 0x2dc6c0, 1000);
        ALL_DAY_MORE_HEADER = new CalendarViewType("ALL_DAY_MORE_HEADER", 8, 0x2dc6c0, 1000);
        MONTH_VIEW_DAY_HEADER = new CalendarViewType("MONTH_VIEW_DAY_HEADER", 9, 0x5b8d80, -1);
        MONTH_BANNER = new CalendarViewType("MONTH_BANNER", 10, 0x2dc6c0, 0);
        WEEK_BANNER = new CalendarViewType("WEEK_BANNER", 11, 0x2dc6c0, 0);
        VIRTUAL_TIMED_EVENTS = new CalendarViewType("VIRTUAL_TIMED_EVENTS", 12, 0x2dc6c0, 0);
        NOTHING_PLANNED_BANNER = new CalendarViewType("NOTHING_PLANNED_BANNER", 13, 0x2dc6c0, 0);
        EVENT = new CalendarViewType("EVENT", 14, 0x5f5e100, 0);
        $VALUES = (new CalendarViewType[] {
            ALL_DAY_EXPAND, DRAG_EVENT, CREATE_EVENT, NOW_LINE, WEEK_NUMBER, ALL_DAY_CLICK_GUARD, HOURS, DAY_HEADER, ALL_DAY_MORE_HEADER, MONTH_VIEW_DAY_HEADER, 
            MONTH_BANNER, WEEK_BANNER, VIRTUAL_TIMED_EVENTS, NOTHING_PLANNED_BANNER, EVENT
        });
    }
}
