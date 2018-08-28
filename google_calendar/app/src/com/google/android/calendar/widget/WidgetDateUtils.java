// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import java.util.Formatter;
import java.util.Locale;

public final class WidgetDateUtils
{

    public static WidgetDateUtils instance;
    private final Context context;
    private final Formatter formatter;
    private final StringBuilder sb = new StringBuilder();

    private WidgetDateUtils(Context context1)
    {
        formatter = new Formatter(sb, Locale.getDefault());
        context = context1;
    }

    public static void initialize(Context context1)
    {
        com/google/android/calendar/widget/WidgetDateUtils;
        JVM INSTR monitorenter ;
        WidgetDateUtils widgetdateutils = instance;
        if (widgetdateutils == null) goto _L2; else goto _L1
_L1:
        com/google/android/calendar/widget/WidgetDateUtils;
        JVM INSTR monitorexit ;
        return;
_L2:
        instance = new WidgetDateUtils(context1);
        if (true) goto _L1; else goto _L3
_L3:
        context1;
        throw context1;
    }

    public final String getWidgetDayDividerText(int i, Time time, int j)
    {
        this;
        JVM INSTR monitorenter ;
        String s;
        time.writeFieldsToImpl();
        time.impl.setJulianDay(i);
        time.copyFieldsFromImpl();
        time.writeFieldsToImpl();
        long l = time.impl.toMillis(true);
        l = time.gmtoff * 1000L + l;
        time = context;
        s = Utils.tZUtils.formatDateRange(time, l, l, 0x82012);
        time = new StringBuilder();
        if (i != j + 1) goto _L2; else goto _L1
_L1:
        time.append(context.getString(0x7f1304c6, new Object[] {
            s
        }));
_L3:
        j = PreferencesConstants.getAlternateCalendarPref(context);
        if (j == 0)
        {
            break MISSING_BLOCK_LABEL_142;
        }
        s = AlternateCalendarUtils.getAlternateMonthDayString(i, context.getResources(), j);
        time.append(", ").append(s);
        time = time.toString();
        this;
        JVM INSTR monitorexit ;
        return time;
_L2:
        if (i != j)
        {
            break MISSING_BLOCK_LABEL_187;
        }
        time.append(context.getString(0x7f1304c5, new Object[] {
            s
        }));
          goto _L3
        time;
        throw time;
        time.append(s);
          goto _L3
    }

    public final String getWidgetItemTimeText(TimelineItem timelineitem, int i)
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        sb.setLength(0);
        if (!timelineitem.isAllDay()) goto _L2; else goto _L1
_L1:
        Object obj;
        String s;
        long l1;
        obj = new Time("UTC");
        s = Utils.getTimeZoneId(context);
        l1 = Utils.convertAlldayUtcToLocal(((Time) (obj)), timelineitem.getStartMillis(), s);
        if (timelineitem.getStartDay() != timelineitem.getEndDay()) goto _L4; else goto _L3
_L3:
        long l = l1;
_L5:
        stringbuilder.append(Utils.formatDateRange(context, formatter, l1, l, 0x80010));
_L6:
        timelineitem = stringbuilder.toString();
        this;
        JVM INSTR monitorexit ;
        return timelineitem;
_L4:
        l = Utils.convertAlldayUtcToLocal(((Time) (obj)), timelineitem.getEndMillis(), s);
          goto _L5
_L2:
        l1 = timelineitem.getStartMillis();
        l = timelineitem.getEndMillis();
        int j;
        if (timelineitem.showEndTime() && TextUtils.isEmpty(timelineitem.getLocation()))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (timelineitem.getEndDay() <= timelineitem.getStartDay())
        {
            flag = false;
        }
        break MISSING_BLOCK_LABEL_248;
_L7:
        timelineitem = context;
        obj = formatter;
        if (i == 0)
        {
            l = l1;
        }
        stringbuilder.append(Utils.formatDateRange(timelineitem, ((Formatter) (obj)), l1, l, j));
          goto _L6
        timelineitem;
        throw timelineitem;
        j = 0x80001;
        if (flag)
        {
            j = 0x80011;
        }
          goto _L7
    }
}
