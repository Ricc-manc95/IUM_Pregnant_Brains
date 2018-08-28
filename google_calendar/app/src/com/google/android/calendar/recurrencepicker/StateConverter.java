// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;

import com.google.android.calendar.api.event.time.ByDayRecurrence;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.common.collect.ImmutableSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class StateConverter
{

    public static RecurrencePickerState.Frequency RecurRuleFreqToStateFrequency(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder(31)).append("RecurRulePart.Freq: ").append(i).toString());

        case 3: // '\003'
            return RecurrencePickerState.Frequency.DAILY;

        case 4: // '\004'
            return RecurrencePickerState.Frequency.WEEKLY;

        case 5: // '\005'
            return RecurrencePickerState.Frequency.MONTHLY;

        case 6: // '\006'
            return RecurrencePickerState.Frequency.YEARLY;
        }
    }

    static int calendarWeekdayToApiWeekday(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder(19)).append("Weekday ").append(i).toString());

        case 1: // '\001'
            return 7;

        case 2: // '\002'
            return 1;

        case 3: // '\003'
            return 2;

        case 4: // '\004'
            return 3;

        case 5: // '\005'
            return 4;

        case 6: // '\006'
            return 5;

        case 7: // '\007'
            return 6;
        }
    }

    public static ImmutableSet recurRuleByDayToStateByDay(RecurRulePart recurrulepart)
    {
        HashSet hashset;
        hashset = new HashSet();
        recurrulepart = recurrulepart.byDay.iterator();
_L10:
        int i;
        if (!recurrulepart.hasNext())
        {
            break MISSING_BLOCK_LABEL_161;
        }
        i = ((ByDayRecurrence)recurrulepart.next()).weekday;
        i;
        JVM INSTR tableswitch 1 7: default 84
    //                   1 129
    //                   2 134
    //                   3 139
    //                   4 144
    //                   5 149
    //                   6 155
    //                   7 113;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L7:
        break MISSING_BLOCK_LABEL_155;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder(19)).append("Weekday ").append(i).toString());
_L8:
        i = 1;
_L11:
        hashset.add(Integer.valueOf(i));
        if (true) goto _L10; else goto _L9
_L9:
        i = 2;
          goto _L11
_L3:
        i = 3;
          goto _L11
_L4:
        i = 4;
          goto _L11
_L5:
        i = 5;
          goto _L11
_L6:
        i = 6;
          goto _L11
        i = 7;
          goto _L11
        return ImmutableSet.copyOf(hashset);
    }
}
