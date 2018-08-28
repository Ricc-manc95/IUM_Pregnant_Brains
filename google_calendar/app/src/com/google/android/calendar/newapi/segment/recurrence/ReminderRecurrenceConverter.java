// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.recurrence;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.time.ByDayRecurrence;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.android.calendar.task.RecurrenceConverter;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.utils.collection.Iterables2;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.MonthlyPattern;
import com.google.android.gms.reminders.model.Recurrence;
import com.google.android.gms.reminders.model.RecurrenceEnd;
import com.google.android.gms.reminders.model.WeeklyPattern;
import com.google.android.gms.reminders.model.YearlyPattern;
import com.google.common.primitives.Ints;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import java.util.TimeZone;

public class ReminderRecurrenceConverter
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/recurrence/ReminderRecurrenceConverter);

    public ReminderRecurrenceConverter()
    {
    }

    static final ByDayRecurrence lambda$addWeekDays$0$ReminderRecurrenceConverter(Integer integer)
    {
        return new ByDayRecurrence(toApiWeekDay(integer.intValue()), null);
    }

    public static com.google.android.calendar.api.event.time.Recurrence toApiRecurrence(Recurrence recurrence)
    {
        byte byte0;
        byte byte1;
        byte1 = 4;
        byte0 = byte1;
        recurrence.getFrequency().intValue();
        JVM INSTR tableswitch 0 3: default 48
    //                   0 299
    //                   1 52
    //                   2 305
    //                   3 311;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_311;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        byte0 = byte1;
_L6:
        com.google.android.calendar.api.event.time.RecurRulePart.Builder builder = new com.google.android.calendar.api.event.time.RecurRulePart.Builder(byte0);
        builder.setInterval(recurrence.getEvery());
        Object obj = recurrence.getRecurrenceEnd();
        Object obj1;
        if (obj != null && !Boolean.TRUE.equals(((RecurrenceEnd) (obj)).getAutoRenew()))
        {
            obj1 = ((RecurrenceEnd) (obj)).getEndDateTime();
            if (obj1 != null)
            {
                obj = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                ((Calendar) (obj)).set(1, ((DateTime) (obj1)).getYear().intValue());
                ((Calendar) (obj)).set(2, ((DateTime) (obj1)).getMonth().intValue() - 1);
                ((Calendar) (obj)).set(5, ((DateTime) (obj1)).getDay().intValue());
                ((Calendar) (obj)).set(11, 0);
                ((Calendar) (obj)).set(12, 0);
                ((Calendar) (obj)).set(13, 0);
                ((Calendar) (obj)).set(14, 0);
                builder.untilDateTimeMillis = Long.valueOf(((Calendar) (obj)).getTimeInMillis());
            } else
            {
                obj1 = ((RecurrenceEnd) (obj)).getNumOccurrences();
                if (obj1 != null && ((Integer) (obj1)).intValue() > 0)
                {
                    builder.setCount(((RecurrenceEnd) (obj)).getNumOccurrences());
                }
            }
        }
        obj = recurrence.getWeeklyPattern();
        if (obj != null)
        {
            obj = ((WeeklyPattern) (obj)).getWeekDay();
            class .Lambda._cls0
                implements Comparator
            {

                public static final Comparator $instance = new .Lambda._cls0();

                public final int compare(Object obj2, Object obj3)
                {
                    int i = ((Integer)obj2).intValue();
                    int j = ((Integer)obj3).intValue();
                    if (i < j)
                    {
                        return -1;
                    }
                    return i <= j ? 0 : 1;
                }


            private .Lambda._cls0()
            {
            }
            }

            Collections.sort(((List) (obj)), .Lambda._cls0..instance);
            class .Lambda._cls1
                implements Function
            {

                public static final Function $instance = new .Lambda._cls1();

                public final Object apply(Object obj2)
                {
                    return ReminderRecurrenceConverter.lambda$addWeekDays$0$ReminderRecurrenceConverter((Integer)obj2);
                }


            private .Lambda._cls1()
            {
            }
            }

            obj1 = .Lambda._cls1..instance;
            if (obj instanceof RandomAccess)
            {
                obj = new com.google.common.collect.Lists.TransformingRandomAccessList(((List) (obj)), ((Function) (obj1)));
            } else
            {
                obj = new com.google.common.collect.Lists.TransformingSequentialList(((List) (obj)), ((Function) (obj1)));
            }
            builder.setByDay((ByDayRecurrence[])((List) (obj)).toArray(new ByDayRecurrence[0]));
        }
        obj = recurrence.getMonthlyPattern();
        if (obj != null)
        {
            obj1 = ((MonthlyPattern) (obj)).getMonthDay();
            if (obj1 != null)
            {
                builder.setByMonthDay(Ints.toArray(((java.util.Collection) (obj1))));
            }
            obj1 = ((MonthlyPattern) (obj)).getWeekDay();
            obj = ((MonthlyPattern) (obj)).getWeekDayNumber();
            if (obj1 != null && obj != null)
            {
                if (((Integer) (obj)).intValue() > 5 || ((Integer) (obj)).intValue() < -1)
                {
                    builder.setByDay(null);
                    LogUtils.e(TAG, "Invalid weekdayNumber: %s", new Object[] {
                        obj
                    });
                } else
                {
                    builder.setByDay(new ByDayRecurrence[] {
                        new ByDayRecurrence(toApiWeekDay(((Integer) (obj1)).intValue()), ((Integer) (obj)))
                    });
                }
            }
        }
        recurrence = recurrence.getYearlyPattern();
        if (recurrence != null)
        {
            builder.setByMonth(Ints.toArray(recurrence.getYearMonth()));
        }
        return new com.google.android.calendar.api.event.time.Recurrence(new RecurRulePart[] {
            builder.build()
        });
_L2:
        byte0 = 3;
          goto _L6
_L4:
        byte0 = 5;
          goto _L6
        byte0 = 6;
          goto _L6
    }

    private static int toApiWeekDay(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder(42)).append("Invalid week day in recurrence:").append(i).toString());

        case 1: // '\001'
            return 1;

        case 2: // '\002'
            return 2;

        case 3: // '\003'
            return 3;

        case 4: // '\004'
            return 4;

        case 5: // '\005'
            return 5;

        case 6: // '\006'
            return 6;

        case 7: // '\007'
            return 7;
        }
    }

    public static Recurrence toReminderRecurrence(com.google.android.calendar.api.event.time.Recurrence recurrence, DateTime datetime, DateTimeService datetimeservice)
    {
        if (recurrence == null || Iterables2.isNullOrEmpty(recurrence.rrules))
        {
            return null;
        } else
        {
            return RecurrenceConverter.fromRfcRecurrenceString(((RecurRulePart)recurrence.rrules.get(0)).toRfc5545String(), datetime, datetimeservice);
        }
    }

}
