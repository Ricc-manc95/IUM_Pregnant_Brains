// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.android.datetimepicker.date.MonthView;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.CalendarController;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.ActivitySingletonCache;
import com.google.android.calendar.utils.a11y.A11yHelper;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyColorMonthView, DataFactory

public final class TimelyMonthPagerAdapter extends PagerAdapter
    implements com.android.datetimepicker.date.MonthView.OnDayClickListener
{

    private static com.android.datetimepicker.date.MonthAdapter.CalendarDay recycleDay;
    private Context context;
    private CalendarController controller;
    private DataFactory dataFactory;
    public boolean datePickerVisibility;
    public int firstDayOfWeek;
    private ArrayList itemsAdded;
    private ArrayList itemsToAdd;
    private ArrayList itemsToRemove;
    private OnDayOfMonthSelectedListener listener;
    private ArrayList recycledViews;
    public final com.android.datetimepicker.date.MonthAdapter.CalendarDay selectedDay;
    private String timezone;

    public TimelyMonthPagerAdapter(Activity activity, DataFactory datafactory, OnDayOfMonthSelectedListener ondayofmonthselectedlistener)
    {
        datePickerVisibility = false;
        context = activity;
        controller = (CalendarController)CalendarController.instances.getInstance(activity);
        dataFactory = datafactory;
        recycledViews = new ArrayList();
        firstDayOfWeek = PreferenceUtils.getFirstDayOfWeekAsCalendar(activity);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        selectedDay = new com.android.datetimepicker.date.MonthAdapter.CalendarDay(l);
        timezone = Utils.getTimeZoneId(activity);
        listener = ondayofmonthselectedlistener;
        itemsToAdd = new ArrayList();
        itemsAdded = new ArrayList();
        itemsToRemove = new ArrayList();
    }

    public static com.android.datetimepicker.date.MonthAdapter.CalendarDay getDayFromPosition(int i)
    {
        int j = i / 12 + 1970;
        i %= 12;
        if (recycleDay == null)
        {
            recycleDay = new com.android.datetimepicker.date.MonthAdapter.CalendarDay(j, i, 1);
        } else
        {
            com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday = recycleDay;
            calendarday.year = j;
            calendarday.month = i;
            calendarday.day = 1;
        }
        return recycleDay;
    }

    private final void init(TimelyColorMonthView timelycolormonthview, HashMap hashmap, int i)
    {
        int k = i % 12;
        int l = i / 12 + 1970;
        int j = -1;
        boolean flag;
        if (selectedDay.year == l && selectedDay.month == k)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            j = selectedDay.day;
        }
        timelycolormonthview.reuse();
        hashmap.put("selected_day", Integer.valueOf(j));
        hashmap.put("year", Integer.valueOf(l));
        hashmap.put("month", Integer.valueOf(k));
        hashmap.put("page_position", Integer.valueOf(i));
        hashmap.put("week_start", Integer.valueOf(firstDayOfWeek));
        if (CalendarProperties.getBooleanProperty(7).booleanValue())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        hashmap.put("show_wk_num", Integer.valueOf(i));
        timelycolormonthview.setMonthParams(hashmap);
        timelycolormonthview.invalidate();
    }

    public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        viewgroup = (MonthView)obj;
        recycledViews.add(viewgroup);
        itemsToRemove.add(viewgroup);
        itemsAdded.remove(viewgroup);
    }

    public final void finishUpdate(ViewGroup viewgroup)
    {
        boolean flag = false;
        ArrayList arraylist = (ArrayList)itemsToRemove;
        int k = arraylist.size();
        for (int i = 0; i < k;)
        {
            Object obj = arraylist.get(i);
            i++;
            viewgroup.removeView((View)obj);
        }

        arraylist = (ArrayList)itemsToAdd;
        k = arraylist.size();
        for (int j = ((flag) ? 1 : 0); j < k;)
        {
            Object obj1 = arraylist.get(j);
            j++;
            viewgroup.addView((View)obj1);
        }

    }

    public final int getCount()
    {
        return 804;
    }

    public final int getItemPosition(Object obj)
    {
        if (obj != null && (obj instanceof MonthView))
        {
            MonthView monthview = (MonthView)obj;
            int i = monthview.mYear;
            int j = monthview.mMonth;
            boolean flag;
            if (selectedDay.year == i && selectedDay.month == j)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                monthview.setSelectedDay(selectedDay.day);
            } else
            {
                monthview.setSelectedDay(-1);
            }
            monthview.invalidate();
        }
        return super.getItemPosition(obj);
    }

    public final Object instantiateItem(ViewGroup viewgroup, int i)
    {
        viewgroup = null;
        TimelyColorMonthView timelycolormonthview;
        Object obj;
        if (recycledViews.size() > 0)
        {
            timelycolormonthview = (TimelyColorMonthView)recycledViews.remove(0);
            viewgroup = (HashMap)timelycolormonthview.getTag();
        } else
        {
            timelycolormonthview = new TimelyColorMonthView(context, dataFactory);
            timelycolormonthview.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, -1));
            timelycolormonthview.setClickable(true);
            timelycolormonthview.mOnDayClickListener = this;
            timelycolormonthview.timezone = timezone;
        }
        obj = viewgroup;
        if (viewgroup == null)
        {
            obj = new HashMap();
        }
        ((HashMap) (obj)).clear();
        init(timelycolormonthview, ((HashMap) (obj)), i);
        itemsToAdd.add(timelycolormonthview);
        itemsAdded.add(timelycolormonthview);
        return timelycolormonthview;
    }

    public final boolean isViewFromObject(View view, Object obj)
    {
        return view == obj;
    }

    public final void onDayClick(MonthView monthview, com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday)
    {
        if (calendarday != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "menu_item", "jump_to_date");
            monthview.playSoundEffect(0);
            listener.onDayOfMonthSelected(calendarday);
            controller.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(new com.google.android.calendar.CalendarController.Command(4096L));
        }
    }

    public final void setPrimaryItem(ViewGroup viewgroup, int i, Object obj)
    {
        super.setPrimaryItem(viewgroup, i, obj);
        if (datePickerVisibility)
        {
            viewgroup = (TimelyColorMonthView)obj;
            A11yHelper.getInstance();
            if (A11yHelper.isAccessibilityEnabled(viewgroup.getContext()))
            {
                viewgroup.post(new TimelyColorMonthView._cls2(viewgroup));
            }
        }
    }

    public final void startUpdate(ViewGroup viewgroup)
    {
        itemsToAdd.clear();
        itemsToRemove.clear();
    }

    public final void updateVisibleMonths()
    {
        ArrayList arraylist = (ArrayList)itemsAdded;
        int k = arraylist.size();
        int i = 0;
        while (i < k) 
        {
            TimelyColorMonthView timelycolormonthview = (TimelyColorMonthView)arraylist.get(i);
            HashMap hashmap = (HashMap)timelycolormonthview.getTag();
            int j;
            if (!(timelycolormonthview instanceof TimelyColorMonthView))
            {
                j = -1;
            } else
            {
                j = ((Integer)((HashMap)timelycolormonthview.getTag()).get("page_position")).intValue();
            }
            init(timelycolormonthview, hashmap, j);
            i++;
        }
    }

    private class OnDayOfMonthSelectedListener
    {

        public abstract void onDayOfMonthSelected(com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday);
    }

}
