// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.DateUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.time.ByDayRecurrence;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.newapi.model.TimeZoneHolder;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.model.edit.MoreTimeOptionsStatusHolder;
import com.google.android.calendar.time.CalendarUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public final class TimeUtils
{

    public static String getRecurrenceString(Resources resources, RecurRulePart recurrulepart, boolean flag, DisplayForm displayform)
    {
        StringBuilder stringbuilder;
        int j;
        stringbuilder = new StringBuilder();
        if (recurrulepart.interval == null)
        {
            j = 1;
        } else
        {
            j = recurrulepart.interval.intValue();
        }
        recurrulepart.freq;
        JVM INSTR tableswitch 3 6: default 52
    //                   3 66
    //                   4 204
    //                   5 668
    //                   6 947;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return null;
_L2:
        stringbuilder.append(resources.getQuantityString(0x7f12000e, j, new Object[] {
            Integer.valueOf(j)
        }));
_L6:
        if (flag)
        {
            if (recurrulepart.untilDateTimeMillis != null || recurrulepart.untilDateMillis != null)
            {
                Object obj;
                Object obj1;
                Calendar calendar;
                int i;
                int k;
                if (recurrulepart.untilDateTimeMillis != null)
                {
                    displayform = DateUtils.formatDateTime(null, recurrulepart.untilDateTimeMillis.longValue(), 0x20000);
                } else
                {
                    displayform = Utils.formatDateTime(null, recurrulepart.untilDateMillis.longValue(), 0x20000, "UTC");
                }
                stringbuilder.append(resources.getString(0x7f1301ca, new Object[] {
                    displayform
                }));
            }
            if (recurrulepart.count != null && recurrulepart.count.intValue() > 0)
            {
                stringbuilder.append(resources.getQuantityString(0x7f120013, recurrulepart.count.intValue(), new Object[] {
                    recurrulepart.count
                }));
            }
        }
        return stringbuilder.toString();
_L3:
        if (recurrulepart.freq != 4 || recurrulepart.byDay.size() != 5)
        {
            i = 0;
        } else
        {
label0:
            {
                obj = recurrulepart.byDay.iterator();
                do
                {
                    if (!((Iterator) (obj)).hasNext())
                    {
                        break label0;
                    }
                    obj1 = (ByDayRecurrence)((Iterator) (obj)).next();
                } while (((ByDayRecurrence) (obj1)).weekday != 6 && ((ByDayRecurrence) (obj1)).weekday != 7);
                i = 0;
            }
        }
_L7:
        if (!i)
        {
            break MISSING_BLOCK_LABEL_313;
        }
        stringbuilder.append(resources.getString(0x7f1301dc));
          goto _L6
        i = 1;
          goto _L7
        if (!recurrulepart.byDay.isEmpty()) goto _L9; else goto _L8
_L8:
        displayform = resources.getString(0x7f1304c4);
_L25:
        stringbuilder.append(displayform);
          goto _L6
_L9:
        obj = recurrulepart.byDay;
        displayform.ordinal();
        JVM INSTR tableswitch 0 1: default 376
    //                   0 522
    //                   1 528;
           goto _L10 _L11 _L12
_L10:
        if (((List) (obj)).size() == 1)
        {
            displayform = "EEEE";
        } else
        {
            displayform = "EEE";
        }
        displayform = new SimpleDateFormat(displayform, Locale.getDefault());
        obj1 = new StringBuilder();
        calendar = Calendar.getInstance();
        k = 0;
_L23:
        if (k >= ((List) (obj)).size()) goto _L14; else goto _L13
_L13:
        i = ((ByDayRecurrence)((List) (obj)).get(k)).weekday;
        i;
        JVM INSTR tableswitch 1 7: default 492
    //                   1 598
    //                   2 604
    //                   3 610
    //                   4 616
    //                   5 622
    //                   6 629
    //                   7 540;
           goto _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22
_L15:
        throw new IllegalArgumentException((new StringBuilder(39)).append("Received unexpected weekday ").append(i).toString());
_L11:
        displayform = "EEE";
        break MISSING_BLOCK_LABEL_390;
_L12:
        displayform = "EEEE";
        break MISSING_BLOCK_LABEL_390;
_L22:
        i = 1;
_L24:
        calendar.set(7, i);
        ((StringBuilder) (obj1)).append(displayform.format(calendar.getTime()));
        if (k != ((List) (obj)).size() - 1)
        {
            ((StringBuilder) (obj1)).append(", ");
        }
        k++;
          goto _L23
_L16:
        i = 2;
          goto _L24
_L17:
        i = 3;
          goto _L24
_L18:
        i = 4;
          goto _L24
_L19:
        i = 5;
          goto _L24
_L20:
        i = 6;
          goto _L24
_L21:
        i = 7;
          goto _L24
_L14:
        displayform = resources.getQuantityString(0x7f12004d, j, new Object[] {
            Integer.valueOf(j), ((StringBuilder) (obj1)).toString()
        });
          goto _L25
_L4:
        stringbuilder.append(resources.getQuantityString(0x7f12002e, j, new Object[] {
            Integer.valueOf(j)
        }));
        if (!repeatsMonthlyOnDayCount(recurrulepart, true)) goto _L27; else goto _L26
_L26:
        i = ((ByDayRecurrence)recurrulepart.byDay.get(0)).weekday;
        j = ((ByDayRecurrence)recurrulepart.byDay.get(0)).offset.intValue();
        i;
        JVM INSTR tableswitch 1 7: default 784
    //                   1 861
    //                   2 868
    //                   3 875
    //                   4 882
    //                   5 889
    //                   6 896
    //                   7 814;
           goto _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35
_L28:
        throw new IllegalArgumentException((new StringBuilder(39)).append("Received unexpected weekday ").append(i).toString());
_L35:
        i = 0x7f0b0050;
_L36:
        displayform = resources.getStringArray(i);
        if (j > 0)
        {
            i = j - 1;
        } else
        {
            i = displayform.length - 1;
        }
        stringbuilder.append(String.format(" (%s)", new Object[] {
            displayform[i]
        }));
          goto _L6
_L29:
        i = 0x7f0b004e;
          goto _L36
_L30:
        i = 0x7f0b0052;
          goto _L36
_L31:
        i = 0x7f0b0053;
          goto _L36
_L32:
        i = 0x7f0b0051;
          goto _L36
_L33:
        i = 0x7f0b004d;
          goto _L36
_L34:
        i = 0x7f0b004f;
          goto _L36
_L27:
        if (repeatsMonthlyOnDayCount(recurrulepart, false))
        {
            stringbuilder.append(String.format(" (%s)", new Object[] {
                resources.getString(0x7f1303d8)
            }));
        }
          goto _L6
_L5:
        stringbuilder.append(resources.getQuantityString(0x7f120050, j, new Object[] {
            Integer.valueOf(j)
        }));
          goto _L6
    }

    static Calendar getReminderDefaultStart(Calendar calendar)
    {
        calendar = (Calendar)calendar.clone();
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        int i = calendar.get(11);
        if (i < 8)
        {
            i = 8 - i;
        } else
        if (i < 13)
        {
            i = 13 - i;
        } else
        if (i < 18)
        {
            i = 18 - i;
        } else
        {
            i = 1;
        }
        calendar.add(11, i);
        return calendar;
    }

    public static String getSimplifiedRecurrenceString(Resources resources, Recurrence recurrence, DisplayForm displayform)
    {
        if (displayform == null)
        {
            throw new NullPointerException();
        }
        if (recurrence == null || recurrence.rrules.isEmpty())
        {
            return resources.getString(0x7f1301b7);
        }
        recurrence = (RecurRulePart)recurrence.rrules.get(0);
        boolean flag;
        if (((RecurRulePart) (recurrence)).untilDateMillis == null && ((RecurRulePart) (recurrence)).untilDateTimeMillis == null && ((RecurRulePart) (recurrence)).count == null && (((RecurRulePart) (recurrence)).interval == null || ((RecurRulePart) (recurrence)).interval.intValue() == 1) && (((RecurRulePart) (recurrence)).bySecond == null || ((RecurRulePart) (recurrence)).bySecond.isEmpty()) && (((RecurRulePart) (recurrence)).byMinute == null || ((RecurRulePart) (recurrence)).byMinute.isEmpty()) && (((RecurRulePart) (recurrence)).byHour == null || ((RecurRulePart) (recurrence)).byHour.isEmpty()) && (((RecurRulePart) (recurrence)).byDay == null || ((RecurRulePart) (recurrence)).byDay.isEmpty()) && (((RecurRulePart) (recurrence)).byMonth == null || ((RecurRulePart) (recurrence)).byMonth.isEmpty()) && (((RecurRulePart) (recurrence)).byMonthDay == null || ((RecurRulePart) (recurrence)).byMonthDay.isEmpty()) && (((RecurRulePart) (recurrence)).byYearDay == null || ((RecurRulePart) (recurrence)).byYearDay.isEmpty()) && (((RecurRulePart) (recurrence)).byWeekNo == null || ((RecurRulePart) (recurrence)).byWeekNo.isEmpty()) && (((RecurRulePart) (recurrence)).bySetPos == null || ((RecurRulePart) (recurrence)).bySetPos.isEmpty()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        ((RecurRulePart) (recurrence)).freq;
        JVM INSTR tableswitch 3 6: default 300
    //                   3 326
    //                   4 334
    //                   5 342
    //                   6 350;
           goto _L2 _L3 _L4 _L5 _L6
_L2:
        return resources.getString(0x7f1303eb, new Object[] {
            getRecurrenceString(resources, recurrence, true, displayform)
        });
_L3:
        return resources.getString(0x7f1303e9);
_L4:
        return resources.getString(0x7f1303ec);
_L5:
        return resources.getString(0x7f1303ea);
_L6:
        return resources.getString(0x7f1303ed);
    }

    static Calendar readCalendarFromBundle(Bundle bundle, String s)
    {
        if (!bundle.containsKey(s))
        {
            return null;
        }
        Object obj = String.valueOf(s);
        String s1 = String.valueOf("_timezone");
        long l;
        if (s1.length() != 0)
        {
            obj = ((String) (obj)).concat(s1);
        } else
        {
            obj = new String(((String) (obj)));
        }
        obj = TimeZone.getTimeZone(bundle.getString(((String) (obj))));
        l = bundle.getLong(s, 0L);
        bundle = Calendar.getInstance(((TimeZone) (obj)));
        bundle.setTimeInMillis(l);
        return bundle;
    }

    private static boolean repeatsMonthlyOnDayCount(RecurRulePart recurrulepart, boolean flag)
    {
        if (recurrulepart.freq != 5)
        {
            return false;
        }
        if (flag)
        {
            if (recurrulepart.byDay.size() != 1 || recurrulepart.byMonthDay.size() != 0)
            {
                return false;
            }
            recurrulepart = ((ByDayRecurrence)recurrulepart.byDay.get(0)).offset;
            return recurrulepart != null && (recurrulepart.intValue() > 0 || recurrulepart.intValue() == -1);
        }
        if (recurrulepart.byDay.size() != 0 || recurrulepart.byMonthDay.size() != 1)
        {
            return false;
        }
        return ((Integer)recurrulepart.byMonthDay.get(0)).intValue() == -1;
    }

    public static boolean shouldExpandMoreTimeOptions(Context context, MoreTimeOptionsStatusHolder moretimeoptionsstatusholder)
    {
        return moretimeoptionsstatusholder.isMoreTimeOptionsButtonClicked() || ((EventModificationsHolder)moretimeoptionsstatusholder).getEventModifications().isRecurring() || !((TimeZoneHolder)moretimeoptionsstatusholder).getTimeZoneId(context).equals(((TimeZoneHolder)moretimeoptionsstatusholder).getDefaultTimeZoneId(context));
    }

    public static long toMidnight(Calendar calendar, boolean flag)
    {
        boolean flag2 = false;
        Calendar calendar1 = CalendarUtils.createTimeInNewTimeZoneRetainingFields(calendar.getTimeInMillis(), calendar.getTimeZone(), TimeZone.getTimeZone("UTC"));
        calendar1.set(11, 0);
        calendar1.set(12, 0);
        calendar1.set(13, 0);
        calendar1.set(14, 0);
        if (flag)
        {
            boolean flag1 = flag2;
            if (calendar.get(14) == 0)
            {
                flag1 = flag2;
                if (calendar.get(13) == 0)
                {
                    flag1 = flag2;
                    if (calendar.get(12) == 0)
                    {
                        flag1 = flag2;
                        if (calendar.get(11) == 0)
                        {
                            flag1 = true;
                        }
                    }
                }
            }
            if (!flag1)
            {
                calendar1.add(5, 1);
            }
        }
        return calendar1.getTimeInMillis();
    }

    static void writeCalendarToBundle(Bundle bundle, String s, Calendar calendar)
    {
        bundle.putLong(s, calendar.getTimeInMillis());
        s = String.valueOf(s);
        String s1 = String.valueOf("_timezone");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        bundle.putString(s, calendar.getTimeZone().getID());
    }

    private class DisplayForm extends Enum
    {

        private static final DisplayForm $VALUES[];
        public static final DisplayForm HEURISTIC;
        public static final DisplayForm LONG;
        public static final DisplayForm SHORT;

        public static DisplayForm[] values()
        {
            return (DisplayForm[])$VALUES.clone();
        }

        static 
        {
            SHORT = new DisplayForm("SHORT", 0);
            LONG = new DisplayForm("LONG", 1);
            HEURISTIC = new DisplayForm("HEURISTIC", 2);
            $VALUES = (new DisplayForm[] {
                SHORT, LONG, HEURISTIC
            });
        }

        private DisplayForm(String s, int i)
        {
            super(s, i);
        }
    }

}
