// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.util.SparseArray;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.YearMonthHelper;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.EventViewPositionHelper;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterMonth, AdapterMonthDay, AdapterWeek, AdapterDay, 
//            MonthDayViewPositionHelper, AdapterEvent

final class MonthAdapter
{

    private final SparseArray dayPositionToDay = new SparseArray();
    public final TimeUtils timeUtils;
    private final int weeksInMonth;
    public final YearMonthHelper yearMonthHelper;
    private final HashMap yearMonthToMonth = new HashMap();

    MonthAdapter(TimeUtils timeutils, YearMonthHelper yearmonthhelper, int i)
    {
        timeUtils = timeutils;
        yearMonthHelper = yearmonthhelper;
        weeksInMonth = i;
    }

    public final AdapterMonth getMonth(YearMonth yearmonth)
    {
        this;
        JVM INSTR monitorenter ;
        AdapterMonth adaptermonth = (AdapterMonth)yearMonthToMonth.get(yearmonth);
        Object obj;
        obj = adaptermonth;
        if (adaptermonth != null)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        Object obj1;
        int i;
        int j;
        int k;
        obj = ImmutableList.builder();
        obj1 = yearMonthHelper.getMonthRange(yearmonth);
        YearMonthHelper yearmonthhelper = yearMonthHelper;
        class .Lambda._cls1
            implements Consumer
        {

            private final MonthAdapter arg$1;
            private final com.google.common.collect.ImmutableList.Builder arg$2;
            private final Range arg$3;

            public final void accept(Object obj2)
            {
                Object obj3 = arg$1;
                obj3 = arg$2;
                Range range = arg$3;
                int l = ((Integer)obj2).intValue();
                l = MonthDayViewPositionHelper.fromJulianDay(l, range.contains(Integer.valueOf(l)));
                obj2 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).add((new .AutoValue_AdapterMonthDay.Builder()).setLoaded(false).setMonthDayHeaderPosition(l).setEvents(ImmutableList.of()).build());
            }

            .Lambda._cls1(com.google.common.collect.ImmutableList.Builder builder, Range range)
            {
                arg$1 = MonthAdapter.this;
                arg$2 = builder;
                arg$3 = range;
            }
        }

        obj1 = new .Lambda._cls1(((com.google.common.collect.ImmutableList.Builder) (obj)), ((Range) (obj1)));
        i = yearmonthhelper.getFirstVisibleJulianDay(yearmonth);
        j = yearmonthhelper.getFirstVisibleJulianDay(yearmonth);
        k = yearmonthhelper.weeksInMonth;
_L2:
        if (i > (k * 7 + j) - 1)
        {
            break; /* Loop/switch isn't completed */
        }
        ((Consumer) (obj1)).accept(Integer.valueOf(i));
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        AutoValue_AdapterMonth.Builder builder = new AutoValue_AdapterMonth.Builder();
        obj.forceCopy = true;
        obj = builder.setDays(ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj)).size)).build();
        yearMonthToMonth.put(yearmonth, obj);
        this;
        JVM INSTR monitorexit ;
        return ((AdapterMonth) (obj));
        yearmonth;
        throw yearmonth;
    }

    public final AdapterMonthDay getMonthDay(int i)
    {
        this;
        JVM INSTR monitorenter ;
        AdapterMonthDay adaptermonthday1 = (AdapterMonthDay)dayPositionToDay.get(i);
        AdapterMonthDay adaptermonthday;
        adaptermonthday = adaptermonthday1;
        if (adaptermonthday1 != null)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        adaptermonthday = (new .AutoValue_AdapterMonthDay.Builder()).setLoaded(false).setMonthDayHeaderPosition(i).setEvents(ImmutableList.of()).build();
        dayPositionToDay.put(i, adaptermonthday);
        this;
        JVM INSTR monitorexit ;
        return adaptermonthday;
        Exception exception;
        exception;
        throw exception;
    }

    final void update(YearMonth yearmonth, AdapterWeek adapterweek)
    {
        this;
        JVM INSTR monitorenter ;
        Range range;
        com.google.common.collect.ImmutableList.Builder builder1;
        Range range1;
        ImmutableList immutablelist;
        ImmutableList immutablelist1;
        int l;
        int i1;
        int j1;
        int k1;
        l = yearMonthHelper.getFirstVisibleJulianDay(yearmonth);
        Object obj = yearMonthHelper;
        i1 = ((YearMonthHelper) (obj)).getFirstVisibleJulianDay(yearmonth);
        j1 = ((YearMonthHelper) (obj)).weeksInMonth;
        range = yearMonthHelper.getMonthRange(yearmonth);
        obj = timeUtils;
        k1 = adapterweek.getJulianWeek() * 7 - (2 - ((Integer)((TimeUtils) (obj)).firstDayOfWeek.get()).intValue());
        range1 = Range.closedOpen(Integer.valueOf(k1), Integer.valueOf(k1 + 7));
        immutablelist = getMonth(yearmonth).getDays();
        immutablelist1 = adapterweek.getDays();
        builder1 = ImmutableList.builderWithExpectedSize(weeksInMonth * 7);
        int i = l;
_L3:
        if (i > (j1 * 7 + i1) - 1)
        {
            break MISSING_BLOCK_LABEL_419;
        }
        Object obj1;
        int l1;
        boolean flag;
        if (!range1.contains(Integer.valueOf(i)))
        {
            break MISSING_BLOCK_LABEL_394;
        }
        obj1 = (AdapterDay)immutablelist1.get(i - k1);
        int j = ((AdapterDay) (obj1)).getJulianDay();
        flag = range.contains(Integer.valueOf(j));
        l1 = MonthDayViewPositionHelper.fromJulianDay(j, flag);
        adapterweek = ImmutableList.builder();
        com.google.common.collect.ImmutableList.Builder builder2 = (com.google.common.collect.ImmutableList.Builder)adapterweek.addAll(((AdapterDay) (obj1)).getAllDayEvents());
        obj1 = (com.google.common.collect.ImmutableList.Builder)adapterweek.addAll(((AdapterDay) (obj1)).getTimedEvents());
        adapterweek.forceCopy = true;
        obj1 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (adapterweek)).contents, ((com.google.common.collect.ImmutableList.Builder) (adapterweek)).size);
        adapterweek = ((AdapterWeek) (obj1));
        if (flag)
        {
            break MISSING_BLOCK_LABEL_345;
        }
        int i2;
        adapterweek = ImmutableList.builder();
        obj1 = (ImmutableList)obj1;
        i2 = ((ImmutableList) (obj1)).size();
        int k = 0;
        Object obj2 = (UnmodifiableIterator)null;
_L1:
        if (k >= i2)
        {
            break MISSING_BLOCK_LABEL_328;
        }
        obj2 = ((ImmutableList) (obj1)).get(k);
        k++;
        obj2 = (AdapterEvent)obj2;
        obj2 = (com.google.common.collect.ImmutableList.Builder)adapterweek.add(((AdapterEvent) (obj2)).toBuilder().setPosition(((AdapterEvent) (obj2)).getPosition() + EventViewPositionHelper.POS_BUCKET).build());
          goto _L1
        adapterweek.forceCopy = true;
        adapterweek = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (adapterweek)).contents, ((com.google.common.collect.ImmutableList.Builder) (adapterweek)).size);
        adapterweek = (new .AutoValue_AdapterMonthDay.Builder()).setLoaded(true).setMonthDayHeaderPosition(l1).setEvents(adapterweek).build();
        dayPositionToDay.put(adapterweek.getMonthDayHeaderPosition(), adapterweek);
        adapterweek = (com.google.common.collect.ImmutableList.Builder)builder1.add(adapterweek);
        break MISSING_BLOCK_LABEL_472;
        adapterweek = (com.google.common.collect.ImmutableList.Builder)builder1.add((AdapterMonthDay)immutablelist.get(i - l));
        break MISSING_BLOCK_LABEL_472;
        adapterweek = yearMonthToMonth;
        AutoValue_AdapterMonth.Builder builder = new AutoValue_AdapterMonth.Builder();
        builder1.forceCopy = true;
        adapterweek.put(yearmonth, builder.setDays(ImmutableList.asImmutableList(builder1.contents, builder1.size)).build());
        this;
        JVM INSTR monitorexit ;
        return;
        yearmonth;
        throw yearmonth;
        i++;
        if (true) goto _L3; else goto _L2
_L2:
    }
}
