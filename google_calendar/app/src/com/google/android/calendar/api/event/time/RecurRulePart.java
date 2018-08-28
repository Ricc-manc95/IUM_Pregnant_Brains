// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.calendar.api.common.CopyHelper;
import com.google.android.calendar.api.common.ParcelHelper;
import com.google.calendar.v2a.android.util.time.TimeFormatUtil;
import com.google.calendar.v2a.android.util.time.TimestampUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            ByDayRecurrence, RRulePrinter

public class RecurRulePart
    implements Parcelable
{
    public static final class Builder
    {

        public List byDay;
        public List byHour;
        public List byMinute;
        public List byMonth;
        public List byMonthDay;
        public List bySecond;
        public List bySetPos;
        public List byWeekNo;
        public List byYearDay;
        public Integer count;
        private int freq;
        public Integer interval;
        public Long untilDateMillis;
        public Long untilDateTimeMillis;
        public Integer wkst;

        public final RecurRulePart build()
        {
            return new RecurRulePart(freq, untilDateMillis, untilDateTimeMillis, count, interval, bySecond, byMinute, byHour, byDay, byMonthDay, byYearDay, byWeekNo, byMonth, bySetPos, wkst);
        }

        public final Builder setByDay(List list)
        {
            list = new ArrayList(list);
            if (!RecurRulePart.areAllByDayOffsetsInAbsRange(list, 1, 53))
            {
                throw new IllegalArgumentException(String.valueOf("all offsets in byDay must be within 1-53, can be negative"));
            } else
            {
                byDay = list;
                return this;
            }
        }

        public final Builder setByDay(ByDayRecurrence abydayrecurrence[])
        {
            if (abydayrecurrence == null || abydayrecurrence.length == 0)
            {
                abydayrecurrence = Collections.emptyList();
            } else
            {
                abydayrecurrence = Arrays.asList(Arrays.copyOf(abydayrecurrence, abydayrecurrence.length));
            }
            if (!RecurRulePart.areAllByDayOffsetsInAbsRange(abydayrecurrence, 1, 53))
            {
                throw new IllegalArgumentException(String.valueOf("all offsets in byDay must be within 1-53, can be negative"));
            } else
            {
                byDay = abydayrecurrence;
                return this;
            }
        }

        public final Builder setByMonth(int ai[])
        {
            ai = CopyHelper.copyArrayToList(ai);
            if (!RecurRulePart.areAllValuesInRange(ai, 1, 12, false))
            {
                throw new IllegalArgumentException(String.valueOf("all byMonth values must be within 1-12"));
            } else
            {
                byMonth = ai;
                return this;
            }
        }

        public final Builder setByMonthDay(List list)
        {
            list = new ArrayList(list);
            if (!RecurRulePart.areAllValuesInRange(list, 1, 31, true))
            {
                throw new IllegalArgumentException(String.valueOf("all byMonthDay values must be within 1-31, can be negative"));
            } else
            {
                byMonthDay = list;
                return this;
            }
        }

        public final Builder setByMonthDay(int ai[])
        {
            ai = CopyHelper.copyArrayToList(ai);
            if (!RecurRulePart.areAllValuesInRange(ai, 1, 31, true))
            {
                throw new IllegalArgumentException(String.valueOf("all byMonthDay values must be within 1-31, can be negative"));
            } else
            {
                byMonthDay = ai;
                return this;
            }
        }

        public final Builder setCount(Integer integer)
        {
            boolean flag;
            if (integer == null || integer.intValue() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("count has to be positive"));
            } else
            {
                count = integer;
                return this;
            }
        }

        public final Builder setInterval(Integer integer)
        {
            boolean flag;
            if (integer == null || integer.intValue() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("interval has to be positive"));
            } else
            {
                interval = integer;
                return this;
            }
        }

        public final Builder setUntilDateMillis(Long long1)
        {
            boolean flag;
            if (long1 == null || TimestampUtil.isTimestampUtcMidnight(long1.longValue()))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("untilDateMillis has to be UTC midnight"));
            } else
            {
                untilDateMillis = long1;
                return this;
            }
        }

        public Builder(int i)
        {
            bySecond = Collections.emptyList();
            byMinute = Collections.emptyList();
            byHour = Collections.emptyList();
            byDay = Collections.emptyList();
            byMonthDay = Collections.emptyList();
            byYearDay = Collections.emptyList();
            byWeekNo = Collections.emptyList();
            byMonth = Collections.emptyList();
            bySetPos = Collections.emptyList();
            freq = i;
        }

        public Builder(RecurRulePart recurrulepart)
        {
            bySecond = Collections.emptyList();
            byMinute = Collections.emptyList();
            byHour = Collections.emptyList();
            byDay = Collections.emptyList();
            byMonthDay = Collections.emptyList();
            byYearDay = Collections.emptyList();
            byWeekNo = Collections.emptyList();
            byMonth = Collections.emptyList();
            bySetPos = Collections.emptyList();
            freq = recurrulepart.freq;
            untilDateMillis = recurrulepart.untilDateMillis;
            untilDateTimeMillis = recurrulepart.untilDateTimeMillis;
            count = recurrulepart.count;
            interval = recurrulepart.interval;
            bySecond = recurrulepart.bySecond;
            byMinute = recurrulepart.byMinute;
            byHour = recurrulepart.byHour;
            byDay = recurrulepart.byDay;
            byMonthDay = recurrulepart.byMonthDay;
            byYearDay = recurrulepart.byYearDay;
            byWeekNo = recurrulepart.byWeekNo;
            byMonth = recurrulepart.byMonth;
            bySetPos = recurrulepart.bySetPos;
            wkst = recurrulepart.wkst;
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final List byDay;
    public final List byHour;
    public final List byMinute;
    public final List byMonth;
    public final List byMonthDay;
    public final List bySecond;
    public final List bySetPos;
    public final List byWeekNo;
    public final List byYearDay;
    public final Integer count;
    public final int freq;
    public final Integer interval;
    public final Long untilDateMillis;
    public final Long untilDateTimeMillis;
    public final Integer wkst;

    RecurRulePart(int i, Long long1, Long long2, Integer integer, Integer integer1, List list, List list1, 
            List list2, List list3, List list4, List list5, List list6, List list7, List list8, 
            Integer integer2)
    {
        boolean flag;
        if (long1 == null || long2 == null && integer == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("at most one of untilDateMillis, untilDateTimeMillis, count fields can be present"));
        }
        if (long2 == null || long1 == null && integer == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("at most one of untilDateMillis, untilDateTimeMillis, count fields can be present"));
        }
        if (integer == null || long1 == null && long2 == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("at most one of untilDateMillis, untilDateTimeMillis, count fields can be present"));
        }
        if ((i == 5 || i == 6) && list6.isEmpty()) goto _L2; else goto _L1
_L1:
        int j;
        if (list3 == null)
        {
            throw new NullPointerException();
        }
        j = 0;
_L9:
        if (j >= list3.size()) goto _L4; else goto _L3
_L3:
        ByDayRecurrence bydayrecurrence;
        bydayrecurrence = (ByDayRecurrence)list3.get(j);
        if (bydayrecurrence == null)
        {
            throw new NullPointerException();
        }
        if (((ByDayRecurrence)bydayrecurrence).offset == null) goto _L6; else goto _L5
_L5:
        j = 0;
_L7:
        if (j == 0)
        {
            throw new IllegalArgumentException(String.valueOf("byDay can only have offsets for MONTHLY and YEARLY rules and byWeekNo has to be empty"));
        }
        break; /* Loop/switch isn't completed */
_L6:
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        j = 1;
        if (true) goto _L7; else goto _L2
_L2:
        if ((i == 5 || i == 6 && !list7.isEmpty()) && !areAllByDayOffsetsInAbsRange(list3, 1, 5))
        {
            throw new IllegalArgumentException(String.valueOf("For monthly context (MONTHLY rule or YEARLY with byMonth set) all offsets in byDay must be within 1-5, can be negative."));
        }
        boolean flag1;
        if (list4.isEmpty() || i != 4)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf("byMonthDay cannot be used in a WEEKLY rule"));
        }
        if (list5.isEmpty() || i == 6)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf("byYearDay can only be used for YEARLY rules"));
        }
        if (list6.isEmpty() || i == 6)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf("byWeekNo can only be used for YEARLY rules"));
        }
        if (!list8.isEmpty())
        {
            boolean flag2;
            if (list.isEmpty() && list1.isEmpty() && list2.isEmpty() && list3.isEmpty() && list4.isEmpty() && list5.isEmpty() && list6.isEmpty() && list7.isEmpty())
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (!flag2)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (!flag2)
            {
                throw new IllegalArgumentException(String.valueOf("bySetPos rule requires that at least one other by*** rule is present as well"));
            }
        }
        freq = i;
        untilDateMillis = long1;
        untilDateTimeMillis = long2;
        count = integer;
        interval = integer1;
        bySecond = checkedListToUnmodifiableList(list);
        byMinute = checkedListToUnmodifiableList(list1);
        byHour = checkedListToUnmodifiableList(list2);
        byDay = checkedListToUnmodifiableList(list3);
        byMonthDay = checkedListToUnmodifiableList(list4);
        byYearDay = checkedListToUnmodifiableList(list5);
        byWeekNo = checkedListToUnmodifiableList(list6);
        byMonth = checkedListToUnmodifiableList(list7);
        bySetPos = checkedListToUnmodifiableList(list8);
        wkst = integer2;
        return;
        if (true) goto _L9; else goto _L8
_L8:
    }

    RecurRulePart(Parcel parcel)
    {
        int i = parcel.readInt();
        switch (i)
        {
        default:
            throw new IllegalStateException("Invalid FREQ value");

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
            freq = i;
            break;
        }
        untilDateMillis = (Long)parcel.readValue(java/lang/Long.getClassLoader());
        untilDateTimeMillis = (Long)parcel.readValue(java/lang/Long.getClassLoader());
        count = (Integer)parcel.readValue(java/lang/Integer.getClassLoader());
        interval = (Integer)parcel.readValue(java/lang/Integer.getClassLoader());
        bySecond = ParcelHelper.unparcelUnmodifiableIntegerList(parcel);
        byMinute = ParcelHelper.unparcelUnmodifiableIntegerList(parcel);
        byHour = ParcelHelper.unparcelUnmodifiableIntegerList(parcel);
        Object obj = parcel.createTypedArrayList(ByDayRecurrence.CREATOR);
        if (((List) (obj)).isEmpty())
        {
            obj = Collections.emptyList();
        } else
        {
            obj = Collections.unmodifiableList(((List) (obj)));
        }
        byDay = ((List) (obj));
        byMonthDay = ParcelHelper.unparcelUnmodifiableIntegerList(parcel);
        byYearDay = ParcelHelper.unparcelUnmodifiableIntegerList(parcel);
        byWeekNo = ParcelHelper.unparcelUnmodifiableIntegerList(parcel);
        byMonth = ParcelHelper.unparcelUnmodifiableIntegerList(parcel);
        bySetPos = ParcelHelper.unparcelUnmodifiableIntegerList(parcel);
        parcel = (Integer)parcel.readValue(java/lang/Integer.getClassLoader());
        if (parcel == null)
        {
            parcel = null;
        } else
        {
            parcel = Integer.valueOf(checkWeekday(parcel.intValue()));
        }
        wkst = parcel;
    }

    public static boolean areAllByDayOffsetsInAbsRange(List list, int i, int j)
    {
        if (list == null)
        {
            throw new NullPointerException();
        }
        for (int k = 0; k < list.size(); k++)
        {
            ByDayRecurrence bydayrecurrence = (ByDayRecurrence)list.get(k);
            if (bydayrecurrence == null)
            {
                throw new NullPointerException();
            }
            bydayrecurrence = (ByDayRecurrence)bydayrecurrence;
            if (bydayrecurrence.offset == null)
            {
                continue;
            }
            int l = Math.abs(bydayrecurrence.offset.intValue());
            if (l < i || l > j)
            {
                return false;
            }
        }

        return true;
    }

    public static boolean areAllValuesInRange(List list, int i, int j, boolean flag)
    {
        if (list == null)
        {
            throw new NullPointerException();
        }
        for (int k = 0; k < list.size(); k++)
        {
            Integer integer = (Integer)list.get(k);
            if (integer == null)
            {
                throw new NullPointerException();
            }
            int i1 = ((Integer)integer).intValue();
            int l = i1;
            if (flag)
            {
                l = Math.abs(i1);
            }
            if (l < i || l > j)
            {
                return false;
            }
        }

        return true;
    }

    static int checkWeekday(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException("Invalid WEEKDAY value");

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
            return i;
        }
    }

    private static List checkedListToUnmodifiableList(List list)
    {
        if (list == null)
        {
            throw new NullPointerException();
        }
        if (list.isEmpty())
        {
            return Collections.emptyList();
        }
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i) == null)
            {
                throw new NullPointerException();
            }
        }

        return Collections.unmodifiableList(list);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (RecurRulePart)obj;
            if (freq != ((RecurRulePart) (obj)).freq)
            {
                return false;
            }
            if (untilDateMillis == null ? ((RecurRulePart) (obj)).untilDateMillis != null : !untilDateMillis.equals(((RecurRulePart) (obj)).untilDateMillis))
            {
                return false;
            }
            if (untilDateTimeMillis == null ? ((RecurRulePart) (obj)).untilDateTimeMillis != null : !untilDateTimeMillis.equals(((RecurRulePart) (obj)).untilDateTimeMillis))
            {
                return false;
            }
            if (count == null ? ((RecurRulePart) (obj)).count != null : !count.equals(((RecurRulePart) (obj)).count))
            {
                return false;
            }
            int i;
            int j;
            if (interval == null)
            {
                i = 1;
            } else
            {
                i = interval.intValue();
            }
            if (((RecurRulePart) (obj)).interval == null)
            {
                j = 1;
            } else
            {
                j = ((RecurRulePart) (obj)).interval.intValue();
            }
            if (i != j)
            {
                return false;
            }
            if (bySecond == null ? ((RecurRulePart) (obj)).bySecond != null : !bySecond.equals(((RecurRulePart) (obj)).bySecond))
            {
                return false;
            }
            if (byMinute == null ? ((RecurRulePart) (obj)).byMinute != null : !byMinute.equals(((RecurRulePart) (obj)).byMinute))
            {
                return false;
            }
            if (byHour == null ? ((RecurRulePart) (obj)).byHour != null : !byHour.equals(((RecurRulePart) (obj)).byHour))
            {
                return false;
            }
            if (byDay == null ? ((RecurRulePart) (obj)).byDay != null : !byDay.equals(((RecurRulePart) (obj)).byDay))
            {
                return false;
            }
            if (byMonthDay == null ? ((RecurRulePart) (obj)).byMonthDay != null : !byMonthDay.equals(((RecurRulePart) (obj)).byMonthDay))
            {
                return false;
            }
            if (byYearDay == null ? ((RecurRulePart) (obj)).byYearDay != null : !byYearDay.equals(((RecurRulePart) (obj)).byYearDay))
            {
                return false;
            }
            if (byWeekNo == null ? ((RecurRulePart) (obj)).byWeekNo != null : !byWeekNo.equals(((RecurRulePart) (obj)).byWeekNo))
            {
                return false;
            }
            if (byMonth == null ? ((RecurRulePart) (obj)).byMonth != null : !byMonth.equals(((RecurRulePart) (obj)).byMonth))
            {
                return false;
            }
            if (bySetPos == null ? ((RecurRulePart) (obj)).bySetPos != null : !bySetPos.equals(((RecurRulePart) (obj)).bySetPos))
            {
                return false;
            }
            if (wkst == null)
            {
                i = 1;
            } else
            {
                i = wkst.intValue();
            }
            if (((RecurRulePart) (obj)).wkst == null)
            {
                j = 1;
            } else
            {
                j = ((RecurRulePart) (obj)).wkst.intValue();
            }
            if (i != j)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        int j3 = 0;
        int k3 = freq;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        if (untilDateMillis != null)
        {
            i = untilDateMillis.hashCode();
        } else
        {
            i = 0;
        }
        if (untilDateTimeMillis != null)
        {
            j = untilDateTimeMillis.hashCode();
        } else
        {
            j = 0;
        }
        if (count != null)
        {
            k = count.hashCode();
        } else
        {
            k = 0;
        }
        if (interval != null)
        {
            l = interval.hashCode();
        } else
        {
            l = 0;
        }
        if (bySecond != null)
        {
            i1 = bySecond.hashCode();
        } else
        {
            i1 = 0;
        }
        if (byMinute != null)
        {
            j1 = byMinute.hashCode();
        } else
        {
            j1 = 0;
        }
        if (byHour != null)
        {
            k1 = byHour.hashCode();
        } else
        {
            k1 = 0;
        }
        if (byDay != null)
        {
            l1 = byDay.hashCode();
        } else
        {
            l1 = 0;
        }
        if (byMonthDay != null)
        {
            i2 = byMonthDay.hashCode();
        } else
        {
            i2 = 0;
        }
        if (byYearDay != null)
        {
            j2 = byYearDay.hashCode();
        } else
        {
            j2 = 0;
        }
        if (byWeekNo != null)
        {
            k2 = byWeekNo.hashCode();
        } else
        {
            k2 = 0;
        }
        if (byMonth != null)
        {
            l2 = byMonth.hashCode();
        } else
        {
            l2 = 0;
        }
        if (bySetPos != null)
        {
            i3 = bySetPos.hashCode();
        } else
        {
            i3 = 0;
        }
        if (wkst != null)
        {
            j3 = wkst.hashCode();
        }
        return (i3 + (l2 + (k2 + (j2 + (i2 + (l1 + (k1 + (j1 + (i1 + (l + (k + (j + (i + k3 * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31 + j3;
    }

    public final String toRfc5545String()
    {
        StringBuilder stringbuilder;
        StringBuilder stringbuilder1;
        stringbuilder = new StringBuilder();
        stringbuilder1 = stringbuilder.append("FREQ=");
        freq;
        JVM INSTR tableswitch 0 6: default 60
    //                   0 70
    //                   1 131
    //                   2 137
    //                   3 143
    //                   4 149
    //                   5 155
    //                   6 162;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new IllegalStateException("Unknown recurrence rule frequency");
_L2:
        String s = "SECONDLY";
_L10:
        long l;
        stringbuilder1.append(s);
        s = null;
        if (untilDateTimeMillis != null)
        {
            s = TimeFormatUtil.formatDateTimeRfc5545(untilDateTimeMillis.longValue());
        }
        if (untilDateMillis == null)
        {
            break MISSING_BLOCK_LABEL_175;
        }
        l = untilDateMillis.longValue();
        if (!TimestampUtil.isTimestampUtcMidnight(l))
        {
            throw new IllegalArgumentException();
        }
        break; /* Loop/switch isn't completed */
_L3:
        s = "MINUTELY";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "HOURLY";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "DAILY";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "WEEKLY";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "MONTHLY";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "YEARLY";
        if (true) goto _L10; else goto _L9
_L9:
        s = TimeFormatUtil.formatDateRfc5545(l);
        if (s != null)
        {
            stringbuilder.append(';').append("UNTIL=").append(s);
        }
        if (count != null)
        {
            stringbuilder.append(';').append("COUNT=").append(count);
        }
        if (interval != null)
        {
            stringbuilder.append(';').append("INTERVAL=").append(interval);
        }
        RRulePrinter.appendByXxxField("BYSECOND", bySecond, stringbuilder);
        RRulePrinter.appendByXxxField("BYMINUTE", byMinute, stringbuilder);
        RRulePrinter.appendByXxxField("BYHOUR", byHour, stringbuilder);
        List list = byDay;
        if (!list.isEmpty())
        {
            String as[] = new String[list.size()];
            int i = 0;
            while (i < list.size()) 
            {
                Object obj = (ByDayRecurrence)list.get(i);
                String s1;
                if (((ByDayRecurrence) (obj)).offset == null)
                {
                    s1 = RRulePrinter.getWeekdayString(((ByDayRecurrence) (obj)).weekday);
                } else
                {
                    s1 = String.valueOf(((ByDayRecurrence) (obj)).offset);
                    obj = RRulePrinter.getWeekdayString(((ByDayRecurrence) (obj)).weekday);
                    s1 = (new StringBuilder(String.valueOf(s1).length() + String.valueOf(obj).length())).append(s1).append(((String) (obj))).toString();
                }
                as[i] = s1;
                i++;
            }
            stringbuilder.append(';').append("BYDAY=").append(TextUtils.join(",", as));
        }
        RRulePrinter.appendByXxxField("BYMONTHDAY", byMonthDay, stringbuilder);
        RRulePrinter.appendByXxxField("BYYEARDAY", byYearDay, stringbuilder);
        RRulePrinter.appendByXxxField("BYWEEKNO", byWeekNo, stringbuilder);
        RRulePrinter.appendByXxxField("BYMONTH", byMonth, stringbuilder);
        RRulePrinter.appendByXxxField("BYSETPOS", bySetPos, stringbuilder);
        if (wkst != null)
        {
            stringbuilder.append(';').append("WKST=").append(RRulePrinter.getWeekdayString(wkst.intValue()));
        }
        return stringbuilder.toString();
    }

    public String toString()
    {
        int i = freq;
        String s = String.valueOf(untilDateMillis);
        String s1 = String.valueOf(untilDateTimeMillis);
        String s2 = String.valueOf(count);
        String s3 = String.valueOf(interval);
        String s4 = String.valueOf(bySecond);
        String s5 = String.valueOf(byMinute);
        String s6 = String.valueOf(byHour);
        String s7 = String.valueOf(byDay);
        String s8 = String.valueOf(byMonthDay);
        String s9 = String.valueOf(byYearDay);
        String s10 = String.valueOf(byWeekNo);
        String s11 = String.valueOf(byMonth);
        String s12 = String.valueOf(bySetPos);
        String s13 = String.valueOf(wkst);
        return (new StringBuilder(String.valueOf(s).length() + 193 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length() + String.valueOf(s7).length() + String.valueOf(s8).length() + String.valueOf(s9).length() + String.valueOf(s10).length() + String.valueOf(s11).length() + String.valueOf(s12).length() + String.valueOf(s13).length())).append("RecurRulePart{freq=").append(i).append(", untilDateMillis=").append(s).append(", untilDateTimeMillis=").append(s1).append(", count=").append(s2).append(", interval=").append(s3).append(", bySecond=").append(s4).append(", byMinute=").append(s5).append(", byHour=").append(s6).append(", byDay=").append(s7).append(", byMonthDay=").append(s8).append(", byYearDay=").append(s9).append(", byWeekNo=").append(s10).append(", byMonth=").append(s11).append(", bySetPos=").append(s12).append(", wkst=").append(s13).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(freq);
        parcel.writeValue(untilDateMillis);
        parcel.writeValue(untilDateTimeMillis);
        parcel.writeValue(count);
        parcel.writeValue(interval);
        parcel.writeList(bySecond);
        parcel.writeList(byMinute);
        parcel.writeList(byHour);
        parcel.writeTypedList(byDay);
        parcel.writeList(byMonthDay);
        parcel.writeList(byYearDay);
        parcel.writeList(byWeekNo);
        parcel.writeList(byMonth);
        parcel.writeList(bySetPos);
        parcel.writeValue(wkst);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new RecurRulePart(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new RecurRulePart[i];
        }

        _cls1()
        {
        }
    }

}
