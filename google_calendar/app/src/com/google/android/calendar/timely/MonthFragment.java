// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.datetimepicker.date.MonthView;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.CalendarController;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.android.calendar.utils.viewpager.LayoutDirectionAwareViewPager;
import java.util.Calendar;

// Referenced classes of package com.google.android.calendar.timely:
//            ViewPagerFragment, TimelyMonthPagerAdapter, MonthPagerAdapter, CalendarMonthView

public final class MonthFragment extends ViewPagerFragment
    implements android.support.v4.view.ViewPager.OnPageChangeListener, com.android.datetimepicker.date.MonthView.OnDayClickListener
{

    private LayoutDirectionAwareViewPager monthViewPager;
    private MonthPagerAdapter monthViewPagerAdapter;

    public MonthFragment()
    {
        super(0x7f0500b5);
    }

    protected final int getFirstJulianDay(int i)
    {
        com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday = TimelyMonthPagerAdapter.getDayFromPosition(i);
        return Utils.getJulianFirstDayFromMonth(calendarday.month, calendarday.year);
    }

    protected final int getItemPositionFromTime(Time time, Activity activity)
    {
        activity = new com.android.datetimepicker.date.MonthAdapter.CalendarDay();
        int i = time.year;
        int j = time.month;
        int k = time.monthDay;
        activity.year = i;
        activity.month = j;
        activity.day = k;
        i = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (activity)).year;
        return ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (activity)).month + (i - 1970) * 12;
    }

    protected final String getPrimesLogTag()
    {
        return "MonthView";
    }

    protected final ViewPager getViewPager()
    {
        return monthViewPager;
    }

    protected final ViewPagerFragment.ViewPagerAdapter getViewPagerAdapter()
    {
        return monthViewPagerAdapter;
    }

    public final boolean hasMiniMonth()
    {
        return false;
    }

    public final boolean isMiniMonthToggleable()
    {
        return false;
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        super.onCalendarPropertyChanged(i, obj);
        if (i == 7 || i == 13)
        {
            obj = monthViewPagerAdapter;
            if (obj != null)
            {
                ((ViewPagerFragment.ViewPagerAdapter) (obj)).updateVisibleViews();
            }
        }
    }

    public final void onDayClick(MonthView monthview, com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday)
    {
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity instanceof OnLaunchDayDetailsHandler)
        {
            monthview.playSoundEffect(0);
            if (super.mHost == null)
            {
                monthview = null;
            } else
            {
                monthview = (FragmentActivity)super.mHost.mActivity;
            }
            ((OnLaunchDayDetailsHandler)monthview).onLaunchDayDetails(calendarday.year, calendarday.month, calendarday.day);
        }
    }

    public final void onInitView(LayoutInflater layoutinflater, ViewGroup viewgroup, Recycler recycler)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        monthViewPagerAdapter = new MonthPagerAdapter(((Activity) (obj)), recycler);
        monthViewPagerAdapter.onDayClickListener = this;
        monthViewPager = (LayoutDirectionAwareViewPager)viewgroup.findViewById(0x7f1001ed);
        super.onInitView(layoutinflater, viewgroup, recycler);
        layoutinflater = VisualElementHolder.instance;
        if (layoutinflater == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)layoutinflater).attachMonthView(monthViewPager);
            return;
        }
    }

    public final void onPageScrollStateChanged(int i)
    {
        super.onPageScrollStateChanged(i);
        if (i == 0)
        {
            MonthPagerAdapter monthpageradapter = monthViewPagerAdapter;
            monthpageradapter.handler.post(monthpageradapter.requestAccessibilityFocus);
        }
    }

    public final void onResume()
    {
        super.onResume();
        Object obj = monthViewPagerAdapter;
        ((MonthPagerAdapter) (obj)).handler.post(((MonthPagerAdapter) (obj)).requestAccessibilityFocus);
        obj = VisualElementHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        }
        VisualElementAttacher visualelementattacher = (VisualElementAttacher)obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        visualelementattacher.recordImpression(((android.content.Context) (obj)), monthViewPager);
    }

    protected final void updatePage(View view)
    {
        ((CalendarMonthView)view).updateView();
    }

    public final void updateTitle(int i)
    {
        Time time = null;
        Object obj2 = TimelyMonthPagerAdapter.getDayFromPosition(i);
        Object obj;
        Time time1;
        Object obj1;
        int j;
        int k;
        long l;
        boolean flag;
        boolean flag1;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        time1 = new Time(Utils.getTimeZoneId(((android.content.Context) (obj))));
        i = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj2)).month;
        j = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj2)).year;
        time1.writeFieldsToImpl();
        time1.impl.set(1, i, j);
        time1.copyFieldsFromImpl();
        time1.writeFieldsToImpl();
        time1.impl.normalize(false);
        time1.copyFieldsFromImpl();
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj1 = new Time(Utils.getTimeZoneId(((android.content.Context) (obj))));
        i = com.android.datetimepicker.Utils.getDaysInMonth(((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj2)).month, ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj2)).year);
        j = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj2)).month;
        k = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj2)).year;
        ((Time) (obj1)).writeFieldsToImpl();
        ((Time) (obj1)).impl.set(i, j, k);
        ((Time) (obj1)).copyFieldsFromImpl();
        ((Time) (obj1)).writeFieldsToImpl();
        ((Time) (obj1)).impl.normalize(false);
        ((Time) (obj1)).copyFieldsFromImpl();
        flag1 = mIsTabletConfig;
        if (mLastSelectedTime.year == mTodayYear)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        l = DateTimeFormatHelper.getToolbarFormatFlags(flag1, flag);
        mController.updateVisibleRange(this, time1, ((Time) (obj1)), null, false, l);
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        j = PreferenceUtils.getFirstDayOfWeekAsCalendar(((android.content.Context) (obj)));
        if (j == 1)
        {
            i = 7;
        } else
        {
            i = j - 1;
        }
        if (super.mHost == null)
        {
            obj = time;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj2 = Calendar.getInstance(Utils.getTimeZone(((android.content.Context) (obj))));
        time1.writeFieldsToImpl();
        ((Calendar) (obj2)).setTimeInMillis(time1.impl.toMillis(false));
        if (((Calendar) (obj2)).get(7) >= j)
        {
            j = ((Calendar) (obj2)).get(7) - j;
        } else
        {
            j = (((Calendar) (obj2)).get(7) + 7) - j;
        }
        ((Calendar) (obj2)).add(5, -j);
        obj = new Time();
        ((Time) (obj)).set(((Calendar) (obj2)).getTimeInMillis());
        time = new Time();
        if (mIsTabletConfig)
        {
            ((Time) (obj1)).writeFieldsToImpl();
            ((Calendar) (obj2)).setTimeInMillis(((Time) (obj1)).impl.toMillis(false));
            CalendarController calendarcontroller;
            if (i >= ((Calendar) (obj2)).get(7))
            {
                i -= ((Calendar) (obj2)).get(7);
            } else
            {
                i = (i + 7) - ((Calendar) (obj2)).get(7);
            }
            ((Calendar) (obj2)).add(5, i);
        } else
        {
            ((Calendar) (obj2)).add(5, 41);
        }
        time.set(((Calendar) (obj2)).getTimeInMillis());
        calendarcontroller = mController;
        obj1 = new com.google.android.calendar.CalendarController.Command(8192L);
        obj1.startTime = ((Time) (obj));
        obj1.endTime = time;
        calendarcontroller.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(((com.google.android.calendar.CalendarController.Command) (obj1)));
    }

    private class OnLaunchDayDetailsHandler
    {

        public abstract void onLaunchDayDetails(int i, int j, int k);
    }

}
