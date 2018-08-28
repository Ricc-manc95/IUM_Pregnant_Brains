// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.text.TextUtils;
import com.android.calendarcommon2.EventRecurrence;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.TimeZoneImpl;
import com.google.android.gms.reminders.model.DailyPattern;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.MonthlyPattern;
import com.google.android.gms.reminders.model.Recurrence;
import com.google.android.gms.reminders.model.RecurrenceEnd;
import com.google.android.gms.reminders.model.RecurrenceStart;
import com.google.android.gms.reminders.model.WeeklyPattern;
import com.google.android.gms.reminders.model.YearlyPattern;
import com.google.android.gms.reminders.model.zzad;
import com.google.android.gms.reminders.model.zzaj;
import com.google.android.gms.reminders.model.zzal;
import com.google.android.gms.reminders.model.zzan;
import com.google.android.gms.reminders.model.zzj;
import com.google.android.gms.reminders.model.zzv;
import com.google.android.gms.reminders.model.zzy;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskDateTimeInCalendar

public final class RecurrenceConverter
{

    public static Recurrence fromRfcRecurrenceString(String s, DateTime datetime, DateTimeService datetimeservice)
    {
        com.google.android.gms.reminders.model.Recurrence.Builder builder;
        EventRecurrence eventrecurrence;
        eventrecurrence = new EventRecurrence();
        eventrecurrence.parse(s);
        builder = new com.google.android.gms.reminders.model.Recurrence.Builder();
        eventrecurrence.freq;
        JVM INSTR tableswitch 4 7: default 60
    //                   4 125
    //                   5 131
    //                   6 137
    //                   7 143;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_143;
_L1:
        byte byte0 = -1;
_L6:
        s = Integer.valueOf(byte0);
        if (s == null || s.intValue() == 0 || s.intValue() == 1 || s.intValue() == 2 || s.intValue() == 3)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        if (byte0 == 0)
        {
            throw new IllegalArgumentException(String.valueOf("Invalid constant for Frequency. Use value in ModelConstants"));
        }
        break MISSING_BLOCK_LABEL_155;
_L2:
        byte0 = 0;
          goto _L6
_L3:
        byte0 = 1;
          goto _L6
_L4:
        byte0 = 2;
          goto _L6
        byte0 = 3;
          goto _L6
        Object obj;
        builder.zzciA = s;
        if (eventrecurrence.interval > 0)
        {
            builder.zzciB = Integer.valueOf(eventrecurrence.interval);
        }
        obj = new com.google.android.gms.reminders.model.RecurrenceStart.Builder();
        long l1;
        boolean flag3;
        if (datetime != null)
        {
            s = (DateTime)datetime.freeze();
        } else
        {
            s = null;
        }
        obj.zzciZ = s;
        s = new zzad(((com.google.android.gms.reminders.model.RecurrenceStart.Builder) (obj)).zzciZ, true);
        if (s != null)
        {
            s = (RecurrenceStart)s.freeze();
        } else
        {
            s = null;
        }
        builder.zzciC = s;
        flag3 = datetime.getAllDay().booleanValue();
        if (eventrecurrence.count > 0)
        {
            s = new com.google.android.gms.reminders.model.RecurrenceEnd.Builder();
            s.zzciJ = Integer.valueOf(eventrecurrence.count);
            s = new zzy(((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (s)).zzciI, ((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (s)).zzciJ, ((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (s)).zzciK, ((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (s)).zzciL, true);
        } else
        if (!TextUtils.isEmpty(eventrecurrence.until))
        {
            s = new Time();
            obj = eventrecurrence.until;
            ((Time) (s)).impl.timezone = ((Time) (s)).timezone;
            ((Time) (s)).impl.parse(((String) (obj)));
            ((Time) (s)).impl.parse(((String) (obj)));
            s.copyFieldsFromImpl();
            obj = new com.google.android.gms.reminders.model.DateTime.Builder();
            obj.zzchX = Integer.valueOf(((Time) (s)).monthDay);
            obj.zzchW = Integer.valueOf(((Time) (s)).month + 1);
            obj.zzchV = Integer.valueOf(((Time) (s)).year);
            Object obj1 = new com.google.android.gms.reminders.model.Time.Builder();
            obj1.zzcjG = Integer.valueOf(((Time) (s)).hour);
            obj1.zzcjH = Integer.valueOf(((Time) (s)).minute);
            obj1.zzcjI = Integer.valueOf(((Time) (s)).second);
            s = new zzaj(((com.google.android.gms.reminders.model.Time.Builder) (obj1)).zzcjG, ((com.google.android.gms.reminders.model.Time.Builder) (obj1)).zzcjH, ((com.google.android.gms.reminders.model.Time.Builder) (obj1)).zzcjI);
            if (s != null)
            {
                s = (com.google.android.gms.reminders.model.Time)s.freeze();
            } else
            {
                s = null;
            }
            obj.zzchY = s;
            obj.zzchT = Boolean.valueOf(flag3);
            obj1 = new com.google.android.gms.reminders.model.RecurrenceEnd.Builder();
            s = ((com.google.android.gms.reminders.model.DateTime.Builder) (obj)).build();
            if (s != null)
            {
                s = (DateTime)s.freeze();
            } else
            {
                s = null;
            }
            obj1.zzciI = s;
            s = new zzy(((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (obj1)).zzciI, ((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (obj1)).zzciJ, ((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (obj1)).zzciK, ((com.google.android.gms.reminders.model.RecurrenceEnd.Builder) (obj1)).zzciL, true);
        } else
        {
            s = null;
        }
        if (s != null)
        {
            s = (RecurrenceEnd)s.freeze();
        } else
        {
            s = null;
        }
        builder.zzciD = s;
        if (datetime.getAllDay().booleanValue() || datetime.getPeriod() == null && datetime.getTime() == null)
        {
            s = null;
        } else
        {
            obj = new com.google.android.gms.reminders.model.DailyPattern.Builder();
            s = datetime.getPeriod();
            boolean flag;
            if (s == null || s.intValue() == 1 || s.intValue() == 2 || s.intValue() == 3 || s.intValue() == 4)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("Invalid constant for Period. Use value in ModelConstants"));
            }
            obj.zzchS = s;
            obj.zzchT = datetime.getAllDay();
            s = datetime.getTime();
            if (s != null)
            {
                s = (com.google.android.gms.reminders.model.Time)s.freeze();
            } else
            {
                s = null;
            }
            obj.zzchR = s;
            s = new zzj(((com.google.android.gms.reminders.model.DailyPattern.Builder) (obj)).zzchR, ((com.google.android.gms.reminders.model.DailyPattern.Builder) (obj)).zzchS, ((com.google.android.gms.reminders.model.DailyPattern.Builder) (obj)).zzchT, true);
        }
        if (s != null)
        {
            s = (DailyPattern)s.freeze();
        } else
        {
            s = null;
        }
        builder.zzciE = s;
        obj = new Time(datetimeservice.calendarTimeZone.timeZone.getID());
        l1 = ArpTaskDateTimeInCalendar.getDueTimeMillis(datetime, datetimeservice);
        ((Time) (obj)).impl.timezone = ((Time) (obj)).timezone;
        ((Time) (obj)).impl.set(l1);
        ((Time) (obj)).impl.toMillis(true);
        ((Time) (obj)).copyFieldsFromImpl();
        ((Time) (obj)).writeFieldsToImpl();
        ((Time) (obj)).impl.normalize(false);
        ((Time) (obj)).copyFieldsFromImpl();
        eventrecurrence.freq;
        JVM INSTR tableswitch 5 7: default 492
    //                   5 961
    //                   6 1123
    //                   7 1420;
           goto _L7 _L8 _L9 _L10
_L7:
        return builder.build();
_L8:
        if (eventrecurrence.bydayCount <= 0)
        {
            s = null;
        } else
        {
            s = new com.google.android.gms.reminders.model.WeeklyPattern.Builder();
            i = 0;
            while (i < eventrecurrence.bydayCount) 
            {
                s.addWeekDay(new Integer[] {
                    Integer.valueOf(toRecurrenceWeekday(eventrecurrence.byday[i]))
                });
                i++;
            }
        }
        datetime = s;
        if (s == null)
        {
            s = new com.google.android.gms.reminders.model.WeeklyPattern.Builder();
            int i;
            if (((Time) (obj)).weekDay == 0)
            {
                i = 7;
            } else
            {
                i = ((Time) (obj)).weekDay;
            }
            datetime = s.addWeekDay(new Integer[] {
                Integer.valueOf(i)
            });
        }
        s = new zzal(((com.google.android.gms.reminders.model.WeeklyPattern.Builder) (datetime)).zzcjJ, true);
        if (s != null)
        {
            s = (WeeklyPattern)s.freeze();
        } else
        {
            s = null;
        }
        builder.zzciF = s;
        continue; /* Loop/switch isn't completed */
_L9:
        if (eventrecurrence.bydayCount <= 0 && eventrecurrence.bymonthdayCount <= 1)
        {
            s = null;
        } else
        {
            s = new com.google.android.gms.reminders.model.MonthlyPattern.Builder();
            if (eventrecurrence.bydayCount <= 0)
            {
                continue;
            }
            datetime = Integer.valueOf(toRecurrenceWeekday(eventrecurrence.byday[0]));
            boolean flag1;
            if (datetime == null || datetime.intValue() == 1 || datetime.intValue() == 2 || datetime.intValue() == 3 || datetime.intValue() == 4 || datetime.intValue() == 5 || datetime.intValue() == 6 || datetime.intValue() == 7)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalArgumentException(String.valueOf("Invalid constant for Weekday. Use value in ModelConstants"));
            }
            s.zzciy = datetime;
            s.zzciz = Integer.valueOf(eventrecurrence.bydayNum[0]);
        }
        do
        {
            do
            {
                datetime = s;
                if (s == null)
                {
                    datetime = (new com.google.android.gms.reminders.model.MonthlyPattern.Builder()).addMonthDay(new Integer[] {
                        Integer.valueOf(((Time) (obj)).monthDay)
                    });
                }
                s = new zzv(((com.google.android.gms.reminders.model.MonthlyPattern.Builder) (datetime)).zzcix, ((com.google.android.gms.reminders.model.MonthlyPattern.Builder) (datetime)).zzciy, ((com.google.android.gms.reminders.model.MonthlyPattern.Builder) (datetime)).zzciz, true);
                int j;
                int l;
                if (s != null)
                {
                    s = (MonthlyPattern)s.freeze();
                } else
                {
                    s = null;
                }
                builder.zzciG = s;
                continue; /* Loop/switch isn't completed */
            } while (eventrecurrence.bymonthdayCount <= 0);
            datetime = eventrecurrence.bymonthday;
            l = datetime.length;
            j = 0;
            while (j < l) 
            {
                s.addMonthDay(new Integer[] {
                    Integer.valueOf(datetime[j])
                });
                j++;
            }
        } while (true);
_L10:
        datetime = new com.google.android.gms.reminders.model.YearlyPattern.Builder();
        s = new Integer[1];
        s[0] = Integer.valueOf(((Time) (obj)).month + 1);
        if (((com.google.android.gms.reminders.model.YearlyPattern.Builder) (datetime)).zzcjK == null)
        {
            datetime.zzcjK = new ArrayList();
        }
        int i1 = s.length;
        for (int k = 0; k < i1; k++)
        {
            datetimeservice = s[k];
            boolean flag2;
            if (datetimeservice == null || datetimeservice.intValue() == 1 || datetimeservice.intValue() == 2 || datetimeservice.intValue() == 3 || datetimeservice.intValue() == 4 || datetimeservice.intValue() == 5 || datetimeservice.intValue() == 6 || datetimeservice.intValue() == 7 || datetimeservice.intValue() == 8 || datetimeservice.intValue() == 9 || datetimeservice.intValue() == 10 || datetimeservice.intValue() == 11 || datetimeservice.intValue() == 12)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (!flag2)
            {
                throw new IllegalArgumentException(String.valueOf("Invalid constant for Month. Use value in ModelConstants"));
            }
            ((com.google.android.gms.reminders.model.YearlyPattern.Builder) (datetime)).zzcjK.add(datetimeservice);
        }

        s = (new com.google.android.gms.reminders.model.MonthlyPattern.Builder()).addMonthDay(new Integer[] {
            Integer.valueOf(((Time) (obj)).monthDay)
        });
        s = new zzv(((com.google.android.gms.reminders.model.MonthlyPattern.Builder) (s)).zzcix, ((com.google.android.gms.reminders.model.MonthlyPattern.Builder) (s)).zzciy, ((com.google.android.gms.reminders.model.MonthlyPattern.Builder) (s)).zzciz, true);
        if (s != null)
        {
            s = (MonthlyPattern)s.freeze();
        } else
        {
            s = null;
        }
        datetime.zzciG = s;
        s = new zzan(((com.google.android.gms.reminders.model.YearlyPattern.Builder) (datetime)).zzciG, ((com.google.android.gms.reminders.model.YearlyPattern.Builder) (datetime)).zzcjK, true);
        if (s != null)
        {
            s = (YearlyPattern)s.freeze();
        } else
        {
            s = null;
        }
        builder.zzciH = s;
        if (true) goto _L7; else goto _L11
_L11:
    }

    private static int toRecurrenceWeekday(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder(47)).append("Invalid week day in EventRecurrence:").append(i).toString());

        case 65536: 
            return 7;

        case 131072: 
            return 1;

        case 262144: 
            return 2;

        case 524288: 
            return 3;

        case 1048576: 
            return 4;

        case 2097152: 
            return 5;

        case 4194304: 
            return 6;
        }
    }
}
