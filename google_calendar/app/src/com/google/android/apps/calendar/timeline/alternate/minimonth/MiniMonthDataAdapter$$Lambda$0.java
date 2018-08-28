// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.util.SparseArray;
import com.google.android.apps.calendar.timeline.alternate.minimonth.data.MiniMonthDayDataFactory;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarDay;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarWeek;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthDataAdapter

final class arg._cls1
    implements Consumer
{

    private final MiniMonthDataAdapter arg$1;

    public final void accept(Object obj)
    {
        MiniMonthDataAdapter minimonthdataadapter = arg$1;
        obj = (Collection)obj;
        SparseArray sparsearray;
        com.google.common.collect..CalendarWeek calendarweek;
        int j;
        for (Iterator iterator = ((Collection) (obj)).iterator(); iterator.hasNext(); sparsearray.put(j, ImmutableList.asImmutableList(calendarweek._fld1, calendarweek._fld1)))
        {
            Object obj1 = (CalendarWeek)iterator.next();
            sparsearray = minimonthdataadapter.weeks;
            j = ((CalendarWeek) (obj1)).getJulianWeek();
            calendarweek = ImmutableList.builder();
            obj1 = (ImmutableList)((CalendarWeek) (obj1)).getDays();
            int k = ((ImmutableList) (obj1)).size();
            int i = 0;
            com.google.common.collect.ion ion;
            for (Object obj2 = (UnmodifiableIterator)null; i < k; obj2 = (com.google.common.collect..CalendarWeek.getDays)calendarweek.(minimonthdataadapter.dayDataFactory.createData(((CalendarDay) (obj2)).getJulianDate(), ion)))
            {
                obj2 = ((ImmutableList) (obj1)).get(i);
                i++;
                obj2 = (CalendarDay)obj2;
                ion = new com.google.common.collect.ion(((CalendarDay) (obj2)).getItems(), .instance);
            }

            calendarweek. = true;
        }

        minimonthdataadapter.invalidationObservable.set(obj);
    }

    (MiniMonthDataAdapter minimonthdataadapter)
    {
        arg$1 = minimonthdataadapter;
    }
}
