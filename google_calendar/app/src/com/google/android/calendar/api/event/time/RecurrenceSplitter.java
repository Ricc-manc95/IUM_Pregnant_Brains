// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import com.google.android.calendar.api.common.CopyHelper;
import com.google.calendar.v2a.android.util.time.TimestampUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            Recurrence, RecurRulePart, RecurrenceExpander

public final class RecurrenceSplitter
{

    private static DateSplit splitDates(List list, long l)
    {
        list = new ArrayList(list);
        Collections.sort(list);
        int i;
        for (i = 0; i < list.size() && ((Long)list.get(i)).longValue() < l; i++) { }
        return new DateSplit(CopyHelper.copyListToArrayOrNull(list.subList(0, i)), CopyHelper.copyListToArrayOrNull(list.subList(i, list.size())));
    }

    public static RecurrenceLegacySplit splitRecurrence(Recurrence recurrence, long l, String s, long l1, boolean flag)
    {
        if (!recurrence.exrules.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        Object obj1 = recurrence.rrules;
        ArrayList arraylist = new ArrayList(((List) (obj1)).size());
        ArrayList arraylist1 = new ArrayList(((List) (obj1)).size());
        int i = 0;
        while (i < ((List) (obj1)).size()) 
        {
            Object obj = (RecurRulePart)((List) (obj1)).get(i);
            if (((RecurRulePart) (obj)).count == null)
            {
                if (((RecurRulePart) (obj)).untilDateTimeMillis != null && ((RecurRulePart) (obj)).untilDateTimeMillis.longValue() < l1 || ((RecurRulePart) (obj)).untilDateMillis != null && ((RecurRulePart) (obj)).untilDateMillis.longValue() < l1)
                {
                    obj = new RecurRulePartSplit(((RecurRulePart) (obj)), null);
                } else
                {
                    RecurRulePart.Builder builder = new RecurRulePart.Builder(((RecurRulePart) (obj)));
                    long l2;
                    if (flag)
                    {
                        l2 = TimestampUtil.roundToMidnight(l1 - 1000L, "UTC", true, "UTC");
                    } else
                    {
                        l2 = TimestampUtil.roundToMidnight(l1, s, true, s) - 1000L;
                    }
                    if (flag)
                    {
                        builder.setUntilDateMillis(Long.valueOf(l2)).untilDateTimeMillis = null;
                    } else
                    {
                        builder.setUntilDateMillis(null).untilDateTimeMillis = Long.valueOf(l2);
                    }
                    obj = new RecurRulePartSplit(builder.build(), ((RecurRulePart) (obj)));
                }
            } else
            {
                long al3[] = RecurrenceExpander.expandRule(((RecurRulePart) (obj)), l, s, l1);
                if (al3.length == 0)
                {
                    obj = new RecurRulePartSplit(null, ((RecurRulePart) (obj)));
                } else
                if (al3.length == ((RecurRulePart) (obj)).count.intValue())
                {
                    obj = new RecurRulePartSplit(((RecurRulePart) (obj)), null);
                } else
                {
                    obj = new RecurRulePartSplit((new RecurRulePart.Builder(((RecurRulePart) (obj)))).setCount(Integer.valueOf(al3.length)).build(), (new RecurRulePart.Builder(((RecurRulePart) (obj)))).setCount(Integer.valueOf(((RecurRulePart) (obj)).count.intValue() - al3.length)).build());
                }
            }
            if (((RecurRulePartSplit) (obj)).original != null)
            {
                arraylist.add(((RecurRulePartSplit) (obj)).original);
            }
            if (((RecurRulePartSplit) (obj)).continuation != null)
            {
                arraylist1.add(((RecurRulePartSplit) (obj)).continuation);
            }
            i++;
        }
        obj1 = new RRulesSplit(arraylist, arraylist1);
        s = splitDates(recurrence.rdates, l1);
        DateSplit datesplit = splitDates(recurrence.exdates, l1);
        recurrence = ((RRulesSplit) (obj1)).original;
        long al1[] = ((DateSplit) (s)).original;
        long al2[] = datesplit.original;
        long al[];
        RecurRulePart arecurrulepart[];
        if (recurrence == null && al1 == null)
        {
            recurrence = null;
        } else
        {
            recurrence = new Recurrence(recurrence, al1, null, al2);
        }
        arecurrulepart = ((RRulesSplit) (obj1)).continuation;
        s = ((DateSplit) (s)).continuation;
        al = datesplit.continuation;
        if (arecurrulepart == null && s == null)
        {
            s = null;
        } else
        {
            s = new Recurrence(arecurrulepart, s, null, al);
        }
        return new RecurrenceLegacySplit(recurrence, s);
    }

    private class DateSplit
    {

        public final long continuation[];
        public final long original[];

        DateSplit(long al[], long al1[])
        {
            original = al;
            continuation = al1;
        }
    }


    private class RecurRulePartSplit
    {

        public final RecurRulePart continuation;
        public final RecurRulePart original;

        RecurRulePartSplit(RecurRulePart recurrulepart, RecurRulePart recurrulepart1)
        {
            original = recurrulepart;
            continuation = recurrulepart1;
        }
    }


    private class RRulesSplit
    {

        public final RecurRulePart continuation[];
        public final RecurRulePart original[];

        RRulesSplit(List list, List list1)
        {
            if (!list.isEmpty())
            {
                list = (RecurRulePart[])list.toArray(new RecurRulePart[0]);
            } else
            {
                list = null;
            }
            original = list;
            if (!list1.isEmpty())
            {
                list = (RecurRulePart[])list1.toArray(new RecurRulePart[0]);
            } else
            {
                list = null;
            }
            continuation = list;
        }
    }


    private class RecurrenceLegacySplit
    {

        public final Recurrence newSeries;
        public final Recurrence originalSeries;

        RecurrenceLegacySplit(Recurrence recurrence, Recurrence recurrence1)
        {
            originalSeries = recurrence;
            newSeries = recurrence1;
        }
    }

}
