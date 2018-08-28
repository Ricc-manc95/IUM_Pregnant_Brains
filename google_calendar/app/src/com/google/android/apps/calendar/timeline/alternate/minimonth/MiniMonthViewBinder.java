// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.util.SparseArray;
import com.google.android.apps.calendar.timeline.alternate.minimonth.data.MiniMonthDayData;
import com.google.android.apps.calendar.timeline.alternate.minimonth.data.MiniMonthDayDataFactory;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.collect.ImmutableList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthView, MiniMonthDrawable, MiniMonthDataAdapter

final class MiniMonthViewBinder
{

    private final ObservableReference currentTime;
    private final MiniMonthDataAdapter dataAdapter;
    public final Provider miniMonthViewProvider;
    private final TimeUtils timeUtils;

    MiniMonthViewBinder(Provider provider, ObservableReference observablereference, TimeUtils timeutils, MiniMonthDataAdapter minimonthdataadapter)
    {
        miniMonthViewProvider = provider;
        currentTime = observablereference;
        timeUtils = timeutils;
        dataAdapter = minimonthdataadapter;
    }

    final void updateView(MiniMonthView minimonthview, YearMonth yearmonth, int i, boolean flag)
    {
        Object obj = timeUtils;
        long l2 = ((Long)currentTime.get()).longValue();
        int j = TimeUtils.getJulianDay(l2, TimeUnit.MILLISECONDS.toSeconds(((TimeZone)((TimeUtils) (obj)).timeZone.get()).getOffset(l2)));
        minimonthview.yearMonth = yearmonth;
        minimonthview.timeUtils.initCalendar(minimonthview.calendar);
        minimonthview.calendar.set(yearmonth.getYear(), yearmonth.getMonth(), 1);
        obj = minimonthview.timeUtils;
        l2 = minimonthview.calendar.getTimeInMillis();
        minimonthview.firstJulianDay = TimeUtils.getJulianDay(l2, TimeUnit.MILLISECONDS.toSeconds(((TimeZone)((TimeUtils) (obj)).timeZone.get()).getOffset(l2)));
        minimonthview.totalDays = minimonthview.calendar.getActualMaximum(5);
        obj = minimonthview.timeUtils;
        int i1 = minimonthview.firstJulianDay;
        minimonthview.firstDayIndex = ((2 - ((Integer)((TimeUtils) (obj)).firstDayOfWeek.get()).intValue()) + i1) % 7;
        minimonthview.todayJulianDay = j;
        minimonthview.selectedJulianDay = i;
        obj = minimonthview.drawable;
        YearMonth yearmonth2 = minimonthview.yearMonth;
        List list1 = minimonthview.monthDayData;
        i = minimonthview.todayJulianDay;
        j = minimonthview.selectedJulianDay;
        MiniMonthGeometry minimonthgeometry = minimonthview.geometry;
        obj.yearMonth = yearmonth2;
        obj.dayDataList = list1;
        obj.todayJulianDay = i;
        obj.selectedJulianDay = j;
        obj.geometry = minimonthgeometry;
        ((MiniMonthDrawable) (obj)).invalidateSelf();
        if (flag)
        {
            Object obj1 = dataAdapter;
            com.google.common.collect.ImmutableList.Builder builder1 = ImmutableList.builder();
            ((MiniMonthDataAdapter) (obj1)).timeUtils.initCalendar(((MiniMonthDataAdapter) (obj1)).calendar);
            ((MiniMonthDataAdapter) (obj1)).calendar.set(yearmonth.getYear(), yearmonth.getMonth(), 1);
            int k1 = ((MiniMonthDataAdapter) (obj1)).calendar.getActualMaximum(5);
            yearmonth = ((MiniMonthDataAdapter) (obj1)).timeUtils;
            long l3 = ((MiniMonthDataAdapter) (obj1)).calendar.getTimeInMillis();
            int j1 = TimeUtils.getJulianDay(l3, TimeUnit.MILLISECONDS.toSeconds(((TimeZone)((TimeUtils) (yearmonth)).timeZone.get()).getOffset(l3)));
            for (i = j1; i <= (k1 + j1) - 1; i++)
            {
                int l1 = ((2 - ((Integer)((MiniMonthDataAdapter) (obj1)).timeUtils.firstDayOfWeek.get()).intValue()) + i) / 7;
                int i2 = ((Integer)((MiniMonthDataAdapter) (obj1)).timeUtils.firstDayOfWeek.get()).intValue();
                List list = (List)((MiniMonthDataAdapter) (obj1)).weeks.get(l1);
                yearmonth = list;
                if (list == null)
                {
                    yearmonth = ImmutableList.builder();
                    int j2 = ((Integer)((MiniMonthDataAdapter) (obj1)).timeUtils.firstDayOfWeek.get()).intValue();
                    for (int k = 0; k < 7; k++)
                    {
                        com.google.common.collect.ImmutableList.Builder builder = (com.google.common.collect.ImmutableList.Builder)yearmonth.add(((MiniMonthDataAdapter) (obj1)).dayDataFactory.createData((l1 * 7 - (2 - j2)) + k, Collections.emptyList()));
                    }

                    yearmonth.forceCopy = true;
                    yearmonth = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (yearmonth)).contents, ((com.google.common.collect.ImmutableList.Builder) (yearmonth)).size);
                    ((MiniMonthDataAdapter) (obj1)).weeks.put(l1, yearmonth);
                }
                yearmonth = (com.google.common.collect.ImmutableList.Builder)builder1.add((MiniMonthDayData)yearmonth.get(i - (l1 * 7 - (2 - i2))));
            }

            builder1.forceCopy = true;
            minimonthview.monthDayData = ImmutableList.asImmutableList(builder1.contents, builder1.size);
            yearmonth = minimonthview.drawable;
            YearMonth yearmonth1 = minimonthview.yearMonth;
            obj1 = minimonthview.monthDayData;
            i = minimonthview.todayJulianDay;
            int l = minimonthview.selectedJulianDay;
            minimonthview = minimonthview.geometry;
            yearmonth.yearMonth = yearmonth1;
            yearmonth.dayDataList = ((List) (obj1));
            yearmonth.todayJulianDay = i;
            yearmonth.selectedJulianDay = l;
            yearmonth.geometry = minimonthview;
            yearmonth.invalidateSelf();
        }
    }
}
