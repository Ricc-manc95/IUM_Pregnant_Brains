// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.view.ViewGroup;
import com.android.datetimepicker.date.MonthView;
import com.google.android.calendar.utils.recycler.Recycler;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.google.android.calendar.timely:
//            CalendarMonthView, TimelyMonthPagerAdapter, MonthViewFrame

public final class MonthPagerAdapter extends ViewPagerFragment.ViewPagerAdapter
{

    public final Activity activity;
    private final Recycler chipRecycler;
    public final Handler handler = new Handler(Looper.getMainLooper());
    private final ArrayList itemsAdded = new ArrayList();
    private final ArrayList itemsToAdd = new ArrayList();
    private final Recycler monthRecycler = new Recycler(new MonthViewRecyclerManager(), 6);
    public com.android.datetimepicker.date.MonthView.OnDayClickListener onDayClickListener;
    public CalendarMonthView primaryMonthView;
    public final Runnable requestAccessibilityFocus = new _cls1();

    public MonthPagerAdapter(Activity activity1, Recycler recycler)
    {
        activity = activity1;
        chipRecycler = recycler;
    }

    private static void init(CalendarMonthView calendarmonthview)
    {
        com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday = TimelyMonthPagerAdapter.getDayFromPosition(((Integer)calendarmonthview.getTag()).intValue());
        HashMap hashmap = new HashMap();
        hashmap.put("year", Integer.valueOf(calendarday.year));
        hashmap.put("month", Integer.valueOf(calendarday.month));
        calendarmonthview.monthViewFrame.setMonthParams(hashmap);
        calendarmonthview.monthViewFrame.owner = calendarmonthview;
        calendarmonthview.monthViewFrame.invalidate();
        calendarmonthview.updateView();
    }

    public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        itemsToAdd.remove(obj);
        itemsAdded.remove(obj);
        if (obj == primaryMonthView)
        {
            primaryMonthView = null;
        }
        obj = (CalendarMonthView)obj;
        viewgroup.removeView(((android.view.View) (obj)));
        monthRecycler.recycle(obj);
    }

    public final void finishUpdate(ViewGroup viewgroup)
    {
        Trace.beginSection("MonthPagerAdapter-finishUpdate");
        ArrayList arraylist = (ArrayList)itemsToAdd;
        int j = arraylist.size();
        for (int i = 0; i < j; i++)
        {
            CalendarMonthView calendarmonthview = (CalendarMonthView)arraylist.get(i);
            calendarmonthview.dataFactory = mDataFactory;
            calendarmonthview.chipRecycler = chipRecycler;
            Object obj = onDayClickListener;
            calendarmonthview.monthViewFrame.mOnDayClickListener = ((com.android.datetimepicker.date.MonthView.OnDayClickListener) (obj));
            obj = TimelyMonthPagerAdapter.getDayFromPosition(((Integer)calendarmonthview.getTag()).intValue());
            HashMap hashmap = new HashMap();
            hashmap.put("year", Integer.valueOf(((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj)).year));
            hashmap.put("month", Integer.valueOf(((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj)).month));
            calendarmonthview.monthViewFrame.setMonthParams(hashmap);
            calendarmonthview.monthViewFrame.owner = calendarmonthview;
            calendarmonthview.monthViewFrame.invalidate();
            calendarmonthview.updateView();
            viewgroup.addView(calendarmonthview);
        }

        viewgroup.requestLayout();
        Trace.endSection();
    }

    public final int getCount()
    {
        return 804;
    }

    public final Object instantiateItem(ViewGroup viewgroup, int i)
    {
        Trace.beginSection("MonthPagerAdapter-instantiateItem");
        viewgroup = (CalendarMonthView)monthRecycler.getOrCreateObject();
        viewgroup.setTag(Integer.valueOf(i));
        itemsToAdd.add(viewgroup);
        itemsAdded.add(viewgroup);
        Trace.endSection();
        return viewgroup;
    }

    public final void setPrimaryItem(ViewGroup viewgroup, int i, Object obj)
    {
        primaryMonthView = (CalendarMonthView)obj;
    }

    public final void startUpdate(ViewGroup viewgroup)
    {
        itemsToAdd.clear();
    }

    public final void updateVisibleViews()
    {
        ArrayList arraylist = (ArrayList)itemsAdded;
        int j = arraylist.size();
        for (int i = 0; i < j;)
        {
            Object obj = arraylist.get(i);
            i++;
            init((CalendarMonthView)obj);
        }

    }

    private class _cls1
        implements Runnable
    {

        private final MonthPagerAdapter this$0;

        public final void run()
        {
            if (primaryMonthView != null)
            {
                Object obj = primaryMonthView;
                if (AccessibilityUtils.isAccessibilityEnabled(((CalendarMonthView) (obj)).getContext()))
                {
                    obj = ((CalendarMonthView) (obj)).monthViewFrame;
                    int i;
                    if (((MonthViewFrame) (obj)).mHasToday)
                    {
                        i = (((MonthViewFrame) (obj)).todayJulianDay - ((MonthViewFrame) (obj)).mFirstJulianDay) + 1;
                    } else
                    {
                        i = 1;
                    }
                    ((MonthViewFrame) (obj)).getAccessibilityNodeProvider().performAction(i, 64, null);
                }
            }
        }

        _cls1()
        {
            this$0 = MonthPagerAdapter.this;
            super();
        }
    }


    private class MonthViewRecyclerManager
        implements com.google.android.calendar.utils.recycler.Recycler.RecyclerManager
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
            obj1.disabled = true;
            MonthData monthdata = ((CalendarMonthView) (obj)).dataFactory.getDataAllowNull(j);
            if (monthdata != null)
            {
                monthdata.unregisterListener(j, ((CalendarMonthView.OnMonthlyUpdateListener) (obj1)).getListenerTagType());
            }
            obj1 = ((CalendarMonthView) (obj)).monthViewFrame;
            if (j != ((MonthViewFrame) (obj1)).mFirstJulianDay - ((MonthView) (obj1)).findDayOffset())
            {
                j--;
                obj1 = ((CalendarMonthView) (obj)).previousMonthListener;
                obj1.disabled = true;
                MonthData monthdata1 = ((CalendarMonthView) (obj)).dataFactory.getDataAllowNull(j);
                if (monthdata1 != null)
                {
                    monthdata1.unregisterListener(j, ((CalendarMonthView.OnMonthlyUpdateListener) (obj1)).getListenerTagType());
                }
            }
            obj1 = ((CalendarMonthView) (obj)).monthViewFrame;
            j = ((MonthViewFrame) (obj1)).mFirstJulianDay;
            int k = ((MonthView) (obj1)).findDayOffset();
            if (i != (((MonthViewFrame) (obj1)).mNumRows * 7 + (j - k)) - 1)
            {
                i++;
                CalendarMonthView.OnMonthlyUpdateListener onmonthlyupdatelistener = ((CalendarMonthView) (obj)).nextMonthListener;
                onmonthlyupdatelistener.disabled = true;
                MonthData monthdata2 = ((CalendarMonthView) (obj)).dataFactory.getDataAllowNull(i);
                if (monthdata2 != null)
                {
                    monthdata2.unregisterListener(i, onmonthlyupdatelistener.getListenerTagType());
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

        MonthViewRecyclerManager()
        {
            this$0 = MonthPagerAdapter.this;
            super();
        }
    }

}
