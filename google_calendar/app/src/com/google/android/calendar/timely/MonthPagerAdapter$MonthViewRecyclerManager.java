// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import com.android.datetimepicker.date.MonthView;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.timely:
//            CalendarMonthView, MonthViewFrame, DataFactory, MonthData, 
//            MonthPagerAdapter

final class this._cls0
    implements com.google.android.calendar.utils.recycler.
{

    private final MonthPagerAdapter this$0;

    public final void clean(Object obj)
    {
        obj = (CalendarMonthView)obj;
        ((CalendarMonthView) (obj)).daysToItems.clear();
        ((CalendarMonthView) (obj)).partitionItemsByWeek.clear();
        ((CalendarMonthView) (obj)).chips.clear();
        ((CalendarMonthView) (obj)).clearChips(1);
        int j = ((CalendarMonthView) (obj)).monthViewFrame.mFirstJulianDay;
        Object obj1 = ((CalendarMonthView) (obj)).monthViewFrame;
        int i = ((MonthViewFrame) (obj1)).mFirstJulianDay;
        i = (((MonthViewFrame) (obj1)).mNumCells + i) - 1;
        obj1 = ((CalendarMonthView) (obj)).currentMonthListener;
        obj1.isabled = true;
        MonthData monthdata = ((CalendarMonthView) (obj)).dataFactory.getDataAllowNull(j);
        if (monthdata != null)
        {
            monthdata.unregisterListener(j, ((isabled) (obj1)).etListenerTagType());
        }
        obj1 = ((CalendarMonthView) (obj)).monthViewFrame;
        if (j != ((MonthViewFrame) (obj1)).mFirstJulianDay - ((MonthView) (obj1)).findDayOffset())
        {
            j--;
            obj1 = ((CalendarMonthView) (obj)).previousMonthListener;
            obj1.isabled = true;
            MonthData monthdata1 = ((CalendarMonthView) (obj)).dataFactory.getDataAllowNull(j);
            if (monthdata1 != null)
            {
                monthdata1.unregisterListener(j, ((isabled) (obj1)).etListenerTagType());
            }
        }
        obj1 = ((CalendarMonthView) (obj)).monthViewFrame;
        j = ((MonthViewFrame) (obj1)).mFirstJulianDay;
        int k = ((MonthView) (obj1)).findDayOffset();
        if (i != (((MonthViewFrame) (obj1)).mNumRows * 7 + (j - k)) - 1)
        {
            i++;
            this._cls0 _lcls0 = ((CalendarMonthView) (obj)).nextMonthListener;
            _lcls0.isabled = true;
            MonthData monthdata2 = ((CalendarMonthView) (obj)).dataFactory.getDataAllowNull(i);
            if (monthdata2 != null)
            {
                monthdata2.unregisterListener(i, _lcls0.etListenerTagType());
            }
        }
        obj = ((CalendarMonthView) (obj)).monthViewFrame;
        obj.numHiddenChips = null;
        obj.owner = null;
        ((MonthViewFrame) (obj)).alternateDateStrings.clear();
    }

    public final Object createObject()
    {
        return (CalendarMonthView)activity.getLayoutInflater().inflate(0x7f05017b, null);
    }

    public final void prepareToReuse(Object obj)
    {
        ((CalendarMonthView)obj).monthViewFrame.reuse();
    }

    ()
    {
        this$0 = MonthPagerAdapter.this;
        super();
    }
}
