// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.format.Time;
import com.android.calendarcommon2.LogUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar:
//            Utils

public class DateTimeFormatHelper
{

    private static final String TAG = com/google/android/calendar/DateTimeFormatHelper.getSimpleName();
    public static DateTimeFormatHelper instance;
    private final Calendar calendar = Calendar.getInstance();
    public final Context context;
    private final Formatter formatter;
    private final List hoursStrings = new ArrayList();
    private boolean is24HourMode;
    private final StringBuilder sb = new StringBuilder();
    private final String weekHeaderTemplate;
    private final String weekHeaderTemplateWeekNumber;

    private DateTimeFormatHelper(Context context1)
    {
        formatter = new Formatter(sb, Locale.getDefault());
        context = context1;
        context1 = context1.getResources();
        weekHeaderTemplate = context1.getString(0x7f1304bc);
        weekHeaderTemplateWeekNumber = context1.getString(0x7f1304bd);
    }

    public static int getToolbarFormatFlags(boolean flag, boolean flag1)
    {
        if (flag)
        {
            return 52;
        }
        return !flag1 ? 0x10034 : 48;
    }

    public static void initialize(Context context1)
    {
        com/google/android/calendar/DateTimeFormatHelper;
        JVM INSTR monitorenter ;
        DateTimeFormatHelper datetimeformathelper = instance;
        if (datetimeformathelper == null) goto _L2; else goto _L1
_L1:
        com/google/android/calendar/DateTimeFormatHelper;
        JVM INSTR monitorexit ;
        return;
_L2:
        instance = new DateTimeFormatHelper(context1);
        if (true) goto _L1; else goto _L3
_L3:
        context1;
        throw context1;
    }

    public final String getDateRangeText(long l, long l1, int i)
    {
        this;
        JVM INSTR monitorenter ;
        Context context1;
        Formatter formatter1;
        sb.setLength(0);
        context1 = context;
        formatter1 = formatter;
        com.google.android.calendar.time.TimeUtils.TimeZoneUtils timezoneutils = Utils.tZUtils;
        if ((i & 0x2000) == 0) goto _L2; else goto _L1
_L1:
        String s = "UTC";
_L4:
        s = com.google.android.calendar.time.TimeUtils.TimeZoneUtils.formatDateRange(context1, formatter1, l, l1, i, s);
        this;
        JVM INSTR monitorexit ;
        return s;
_L2:
        if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.firstTZRequest)
        {
            com.google.android.calendar.time.TimeUtils.TimeZoneUtils.getTimeZone(context1, null, false);
        }
        if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.useHomeTZ)
        {
            s = com.google.android.calendar.time.TimeUtils.TimeZoneUtils.homeTZ;
            continue; /* Loop/switch isn't completed */
        }
        s = Time.getCurrentTimezone();
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final String getDateRangeText(int ai[], int i, boolean flag, int j)
    {
        boolean flag1 = false;
        this;
        JVM INSTR monitorenter ;
        Object obj;
        Formatter formatter1;
        long l;
        long l1;
        calendar.clear();
        calendar.setTimeZone(Utils.getTimeZone(context));
        calendar.set(ai[0], ai[1], ai[2]);
        l = calendar.getTimeInMillis();
        calendar.add(5, i);
        calendar.add(12, -1);
        l1 = calendar.getTimeInMillis();
        LogUtils.d(TAG, "getWeekRangeText start time: %d", new Object[] {
            Long.valueOf(l)
        });
        LogUtils.d(TAG, "getWeekRangeText end time: %d", new Object[] {
            Long.valueOf(l1)
        });
        sb.setLength(0);
        obj = context;
        formatter1 = formatter;
        if (flag)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 0x10000;
        }
        ai = Utils.tZUtils;
        if ((i & 0x2000) == 0) goto _L2; else goto _L1
_L1:
        ai = "UTC";
_L8:
        obj = com.google.android.calendar.time.TimeUtils.TimeZoneUtils.formatDateRange(((Context) (obj)), formatter1, l, l1, i, ai);
        if (j == -1) goto _L4; else goto _L3
_L3:
        ai = String.format(weekHeaderTemplateWeekNumber, new Object[] {
            Integer.valueOf(j), obj
        });
_L6:
        this;
        JVM INSTR monitorexit ;
        return ai;
_L2:
        if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.firstTZRequest)
        {
            com.google.android.calendar.time.TimeUtils.TimeZoneUtils.getTimeZone(((Context) (obj)), null, false);
        }
        if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.useHomeTZ)
        {
            ai = com.google.android.calendar.time.TimeUtils.TimeZoneUtils.homeTZ;
            continue; /* Loop/switch isn't completed */
        }
        ai = Time.getCurrentTimezone();
        continue; /* Loop/switch isn't completed */
_L4:
        ai = ((int []) (obj));
        if (!flag) goto _L6; else goto _L5
_L5:
        ai = String.format(weekHeaderTemplate, new Object[] {
            obj
        });
          goto _L6
        ai;
        throw ai;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final List getHoursStrings()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        flag = DateFormat.is24HourFormat(context);
        if (!hoursStrings.isEmpty() && is24HourMode == flag)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        hoursStrings.clear();
        calendar.clear();
        calendar.setTimeZone(TimeZone.getDefault());
        int i = 1;
_L2:
        if (i > 24)
        {
            break; /* Loop/switch isn't completed */
        }
        calendar.set(11, i);
        hoursStrings.add(DateUtils.formatDateTime(context, calendar.getTimeInMillis(), 16385));
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        is24HourMode = flag;
        List list = hoursStrings;
        this;
        JVM INSTR monitorexit ;
        return list;
        Exception exception;
        exception;
        throw exception;
    }

    public final String getMonthText(int ai[])
    {
        this;
        JVM INSTR monitorenter ;
        calendar.clear();
        calendar.setTimeZone(Utils.getTimeZone(context));
        calendar.set(ai[0], ai[1], 2);
        LogUtils.d(TAG, "getMonthText time: %d", new Object[] {
            Long.valueOf(calendar.getTimeInMillis())
        });
        ai = getDateRangeText(calendar.getTimeInMillis(), calendar.getTimeInMillis(), 52);
        this;
        JVM INSTR monitorexit ;
        return ai;
        ai;
        throw ai;
    }

    public final String getShortMinuteString(int i, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        TimeZone timezone;
        calendar.clear();
        timezone = TimeZone.getDefault();
        calendar.setTimeZone(timezone);
        calendar.set(11, i / 60);
        calendar.set(12, i % 60);
        Object obj;
        if (!DateFormat.is24HourFormat(context))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!flag || i == 0)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        if (calendar.get(12) != 0) goto _L2; else goto _L1
_L1:
        obj = new SimpleDateFormat("h");
_L3:
        ((SimpleDateFormat) (obj)).setTimeZone(timezone);
        obj = ((SimpleDateFormat) (obj)).format(calendar.getTime());
_L4:
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
_L2:
        obj = new SimpleDateFormat("h:mm");
          goto _L3
        obj;
        throw obj;
        obj = DateUtils.formatDateTime(context, calendar.getTimeInMillis(), 16385);
          goto _L4
    }

    public final String getTimeRangeText(long l, long l1, int i)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        sb.setLength(0);
        obj = context;
        obj = Utils.tZUtils.formatDateRange(((Context) (obj)), l, l1, i | 0x4001);
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public final String getWeekRangeText(int ai[], boolean flag, int i)
    {
        this;
        JVM INSTR monitorenter ;
        ai = getDateRangeText(ai, 7, flag, i);
        this;
        JVM INSTR monitorexit ;
        return ai;
        ai;
        throw ai;
    }

    public final String getYearText(int i)
    {
        this;
        JVM INSTR monitorenter ;
        String s;
        calendar.set(i, 1, 1);
        s = (new SimpleDateFormat("yyyy", Locale.getDefault())).format(calendar.getTime());
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

}
