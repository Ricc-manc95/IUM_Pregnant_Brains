// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.HashMap;

// Referenced classes of package com.android.datetimepicker.date:
//            DatePickerController, MonthView

public abstract class MonthAdapter extends BaseAdapter
    implements MonthView.OnDayClickListener
{

    private final Context context;
    public final DatePickerController mController;
    public boolean rtlEnabled;
    public CalendarDay selectedDay;

    public MonthAdapter(Context context1, DatePickerController datepickercontroller)
    {
        rtlEnabled = false;
        context = context1;
        mController = datepickercontroller;
        selectedDay = new CalendarDay(System.currentTimeMillis());
        selectedDay = mController.getSelectedDay();
        notifyDataSetChanged();
    }

    public abstract MonthView createMonthView(Context context1);

    public int getCount()
    {
        return ((mController.getMaxYear() - mController.getMinYear()) + 1) * 12;
    }

    public Object getItem(int i)
    {
        return null;
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        boolean flag = true;
        int j = -1;
        viewgroup = null;
        Object obj;
        int k;
        int l;
        if (view != null)
        {
            viewgroup = (MonthView)view;
            view = (HashMap)viewgroup.getTag();
        } else
        {
            MonthView monthview = createMonthView(context);
            monthview.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, -1));
            monthview.setClickable(true);
            monthview.mOnDayClickListener = this;
            monthview.setRtlEnabled(rtlEnabled);
            view = viewgroup;
            viewgroup = monthview;
        }
        obj = view;
        if (view == null)
        {
            obj = new HashMap();
        }
        ((HashMap) (obj)).clear();
        k = i % 12;
        l = i / 12 + mController.getMinYear();
        if (selectedDay.year == l && selectedDay.month == k)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            j = selectedDay.day;
        }
        viewgroup.reuse();
        ((HashMap) (obj)).put("selected_day", Integer.valueOf(j));
        ((HashMap) (obj)).put("year", Integer.valueOf(l));
        ((HashMap) (obj)).put("month", Integer.valueOf(k));
        ((HashMap) (obj)).put("week_start", Integer.valueOf(mController.getFirstDayOfWeek()));
        viewgroup.setMonthParams(((HashMap) (obj)));
        viewgroup.invalidate();
        return viewgroup;
    }

    public boolean hasStableIds()
    {
        return true;
    }

    public final void onDayClick(MonthView monthview, CalendarDay calendarday)
    {
        if (calendarday != null)
        {
            mController.tryVibrate();
            mController.onDayOfMonthSelected(calendarday.year, calendarday.month, calendarday.day);
            selectedDay = calendarday;
            notifyDataSetChanged();
        }
    }

    private class CalendarDay
        implements Comparable
    {

        private Calendar calendar;
        public int day;
        public int month;
        private Time time;
        public int year;

        private final void setTime(long l)
        {
            if (calendar == null)
            {
                calendar = Calendar.getInstance();
            }
            calendar.setTimeInMillis(l);
            month = calendar.get(2);
            year = calendar.get(1);
            day = calendar.get(5);
        }

        public final int compareTo(CalendarDay calendarday)
        {
            int j = Integer.compare(year, calendarday.year);
            int i = j;
            if (j == 0)
            {
                int k = Integer.compare(month, calendarday.month);
                i = k;
                if (k == 0)
                {
                    i = Integer.compare(day, calendarday.day);
                }
            }
            return i;
        }

        public final volatile int compareTo(Object obj)
        {
            return compareTo((CalendarDay)obj);
        }

        public final void setJulianDay(int i)
        {
            this;
            JVM INSTR monitorenter ;
            if (time == null)
            {
                time = new Time();
            }
            time.setJulianDay(i);
            setTime(time.toMillis(false));
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public CalendarDay()
        {
            setTime(System.currentTimeMillis());
        }

        public CalendarDay(int i, int j, int k)
        {
            year = i;
            month = j;
            day = k;
        }

        public CalendarDay(long l)
        {
            setTime(l);
        }

        public CalendarDay(Calendar calendar1)
        {
            year = calendar1.get(1);
            month = calendar1.get(2);
            day = calendar1.get(5);
        }
    }

}
