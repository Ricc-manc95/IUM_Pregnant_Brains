// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import com.android.datetimepicker.Utils;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.time.Time;
import java.util.Calendar;

final class angeText extends angeText
{

    public final String contentDescription;
    public final String weekLabel;

    public final int getLayoutId(ntext ntext)
    {
        return ntext.widgetStyle != 1 ? 0x7f05019c : 0x7f050190;
    }

    ntext(DateTimeFormatHelper datetimeformathelper, Calendar calendar, Time time, boolean flag)
    {
        int ai[] = new int[3];
        ai[0] = calendar.get(1);
        ai[1] = calendar.get(2);
        ai[2] = calendar.get(5);
        int i = -1;
        if (flag)
        {
            i = Utils.getWeekNumberInYear(android.text.format.Time.getJulianDay(calendar.getTimeInMillis(), time.gmtoff), Utils.convertDayOfWeekFromCalendarToTime(calendar.get(7)));
        }
        weekLabel = datetimeformathelper.getWeekRangeText(ai, false, i);
        contentDescription = datetimeformathelper.getWeekRangeText(ai, true, i);
    }
}
