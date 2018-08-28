// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Trace;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.calendar.CalendarController;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.event.DelayedActionDescription;
import com.google.android.calendar.event.DelayedActionPerformer;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.newevent.NewEventTimeChangedListenerHolder;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.gridviews.DndEventHandler;
import com.google.android.calendar.timely.gridviews.GridDndController;
import com.google.android.calendar.timely.gridviews.GridHourDrawable;
import com.google.android.calendar.timely.gridviews.GridViewFrame;
import com.google.android.calendar.timely.interaction.SwipeInteractionController;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.Calendar;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            BaseCalendarFragment, MonthLabelProvider, TimelineRecyclerView, TimelineAdapter, 
//            DataFactory, TimelyDayViewPager, DayViewPagerAdapter, OnTimelineGestureListener, 
//            AnimatedToolbarTitleHelper, MonthLabelThresholdEvaluator, TimelyDayView, OnTimelineModeChangedListener, 
//            PagedDayView, PagedScrollView

public class DualTimelineGridFragment extends BaseCalendarFragment
    implements DelayedActionPerformer, AnimatedToolbarTitleHelper.AnimatedActionbarTitleListener
{
    final class DndControllerDelegate
        implements com.google.android.calendar.timely.gridviews.GridDndController.Delegate
    {

        private final DualTimelineGridFragment this$0;

        public final GridHourDrawable getCurrentHourDrawable()
        {
            Object obj = dayPager;
            obj = ((TimelyDayViewPager) (obj)).getChildAtPosition(((ViewPager) (obj)).getCurrentItem());
            if (((PagedDayView) (obj)).gridViewFrame == null)
            {
                return null;
            } else
            {
                return ((PagedDayView) (obj)).gridViewFrame.gridHourDrawable;
            }
        }

        public final String getViewMode()
        {
            return "preference_value_hourly_view";
        }

        public final void scrollHorizontally(int i)
        {
            int j = i;
            if (RtlUtils.isLayoutDirectionRtl(getContext()))
            {
                j = -i;
            }
            dayPager.setCurrentItem(dayPager.getCurrentItem() + j);
        }

        public final void scrollVertically(int i)
        {
            Object obj = dayPager;
            obj = ((TimelyDayViewPager) (obj)).getChildAtPosition(((ViewPager) (obj)).getCurrentItem()).pagedScrollView;
            PagedScrollView.ScrollManager scrollmanager = ((PagedScrollView) (obj)).scrollManager;
            i = ((PagedScrollView) (obj)).getVerticalScrollPositionFromBottom() + i;
            if (i != scrollmanager.verticalScrollPositionFromBottom)
            {
                scrollmanager.verticalScrollPositionFromBottom = i;
                scrollmanager.notifyListeners(null);
            }
        }

        DndControllerDelegate()
        {
            this$0 = DualTimelineGridFragment.this;
            super();
        }
    }

    public static interface SimpleOnScrollListener
    {

        public abstract void onScrolled(int i);
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/DualTimelineGridFragment);
    public AnimatedToolbarTitleHelper animatedToolbarTitleHelper;
    private View contentContainer;
    public TimelyDayViewPager dayPager;
    public boolean forceShowInitialDay;
    private Handler handler;
    public int julianDayOnTop;
    public TimelineRecyclerView list;
    private MonthLabelProvider monthLabelProvider;
    public int pendingModeChangePosition;
    private final Calendar recycleCalendar = Calendar.getInstance();
    public final OnTimelineModeChangedListener timelineModeChangedListener = new _cls5();
    private final TimelineRecyclerView.OnTimelinePositionChangedListener timelinePositionChangedListener = new _cls6();

    public DualTimelineGridFragment()
    {
        super(0x7f050059);
        handler = new Handler();
        julianDayOnTop = -1;
        pendingModeChangePosition = -1;
    }

    public static Bundle createArgs(long l, boolean flag)
    {
        Bundle bundle = new Bundle();
        bundle.putLong("args_selected_time_millis", l);
        bundle.putBoolean("args_in_grid_mode", flag);
        return bundle;
    }

    public static int getJulianDayFromPosition(int i)
    {
        return 0x253d8c + i;
    }

    public static int getPositionFromJulianDay(int i)
    {
        return i - 0x253d8c;
    }

    private final void updateAlternateMonthRange()
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj == null || !AlternateCalendarUtils.isAlternateCalendarEnabled(((Context) (obj))))
        {
            return;
        }
        if (mMinimonthToggledOpen || !isMiniMonthToggleable())
        {
            Time time = new Time("UTC");
            int i = Utils.getJulianFirstDayFromMonth(super.mLastSelectedTime.month, super.mLastSelectedTime.year);
            time.writeFieldsToImpl();
            time.impl.setJulianDay(i);
            time.copyFieldsFromImpl();
            Time time1 = new Time("UTC");
            i = getMonthEndJulianDay();
            time1.writeFieldsToImpl();
            time1.impl.setJulianDay(i);
            time1.copyFieldsFromImpl();
            CalendarController calendarcontroller1 = mController;
            com.google.android.calendar.CalendarController.Command command = new com.google.android.calendar.CalendarController.Command(8192L);
            command.startTime = time;
            command.endTime = time1;
            calendarcontroller1.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(command);
            return;
        } else
        {
            CalendarController calendarcontroller = mController;
            Time time2 = mLastSelectedTime;
            Time time3 = mLastSelectedTime;
            com.google.android.calendar.CalendarController.Command command1 = new com.google.android.calendar.CalendarController.Command(8192L);
            command1.startTime = time2;
            command1.endTime = time3;
            calendarcontroller.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(command1);
            return;
        }
    }

    private final void updateTitle()
    {
        Object obj = mLastSelectedTime;
        ((Time) (obj)).writeFieldsToImpl();
        long l = ((Time) (obj)).impl.toMillis(false);
        recycleCalendar.setTimeInMillis(l);
        obj = monthLabelProvider;
        int i = mLastSelectedTime.year;
        boolean flag1 = ((MonthLabelProvider) (obj)).isTablet;
        boolean flag;
        if (i == ((MonthLabelProvider) (obj)).todayYear)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i = DateTimeFormatHelper.getToolbarFormatFlags(flag1, flag);
        mController.updateVisibleRange(this, mLastSelectedTime, mLastSelectedTime, null, false, i);
        updateAlternateMonthRange();
    }

    public final void annotateVisualElement(boolean flag)
    {
        if (contentContainer == null)
        {
            LogUtils.wtf(TAG, "Content container not found in dual timeline grid fragment!", new Object[0]);
            return;
        }
        if (flag)
        {
            VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
            if (visualelementattacher == null)
            {
                throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
            } else
            {
                ((VisualElementAttacher)visualelementattacher).attachDayView(contentContainer);
                return;
            }
        }
        VisualElementAttacher visualelementattacher1 = VisualElementHolder.instance;
        if (visualelementattacher1 == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)visualelementattacher1).attachScheduleView(contentContainer);
            return;
        }
    }

    protected final void eventsChanged()
    {
        if (list != null)
        {
            Object obj1 = list;
            Object obj = ((TimelineRecyclerView) (obj1)).timelineAdapter;
            obj1 = ((TimelineRecyclerView) (obj1)).getContext();
            if (((TimelineAdapter) (obj)).dataFactory == null)
            {
                LogUtils.e(TimelineAdapter.TAG, "DataFactory is null, unable to set Hide Declined preference.", new Object[0]);
            } else
            {
                ((TimelineAdapter) (obj)).dataFactory.setHideDeclinedEvents(((Context) (obj1)));
            }
            if (super.mFragmentManager.getBackStackEntryCount() == 0)
            {
                obj = list;
                obj1 = ((TimelineRecyclerView) (obj)).getContext();
                AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                analyticslogger = (AnalyticsLogger)analyticslogger;
                int i;
                if (((TimelineRecyclerView) (obj)).viewMode == 1)
                {
                    obj = "day_grid";
                } else
                {
                    obj = "agenda";
                }
                analyticslogger.trackView(((Context) (obj1)), ((String) (obj)));
            }
        }
        if (inGridMode())
        {
            if (dayPager != null)
            {
                obj = dayPager;
                i = TimelyDayViewPager.getPosition(mLastSelectedTime);
                ((TimelyDayViewPager) (obj)).dayViewPagerAdapter.refresh(i, true);
            }
        } else
        if (list != null)
        {
            TimelineRecyclerView timelinerecyclerview = list;
            int j = TimelineRecyclerView.getPosition(mLastSelectedTime);
            timelinerecyclerview.timelineAdapter.refresh(j, true);
            return;
        }
    }

    protected final String getPrimesLogTag()
    {
        if (inGridMode())
        {
            return "1DayView";
        } else
        {
            return "ScheduleView";
        }
    }

    protected final void goTo(Time time, boolean flag, boolean flag1)
    {
        TimelyDayViewPager timelydayviewpager;
        int j;
        int i = 1;
        if (list == null || dayPager == null)
        {
            LogUtils.d(TAG, "Tried to goto a time but Views haven't been created yet!", new Object[0]);
            return;
        }
        super.goTo(time, flag, flag1);
        updateTitle();
        Time time1;
        String s;
        long l;
        boolean flag2;
        if (!mMinimonthToggledOpen && flag1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        list.goTo(time, flag2);
        if (mMinimonthToggledOpen)
        {
            i = 0;
        } else
        if (flag1)
        {
            i = 2;
        }
        timelydayviewpager = dayPager;
        j = TimelyDayViewPager.getPosition(time);
        timelydayviewpager.dayViewPagerAdapter.mDataFactory.setTimePassage(0, 0x253d8c + j);
        timelydayviewpager.dayViewPagerAdapter.refresh(j, false);
        if (timelydayviewpager.timeToday == null)
        {
            timelydayviewpager.timeToday = new Time();
        }
        time1 = timelydayviewpager.timeToday;
        s = Utils.getTimeZoneId(timelydayviewpager.getContext());
        time1.writeFieldsToImpl();
        time1.impl.switchTimezone(s);
        time1.copyFieldsFromImpl();
        time1 = timelydayviewpager.timeToday;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        time1.set(l);
        i;
        JVM INSTR tableswitch 0 2: default 228
    //                   0 242
    //                   1 345
    //                   2 275;
           goto _L1 _L2 _L3 _L4
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        LogUtils.wtf(TimelyDayViewPager.TAG, "Unknown scroll type.", new Object[0]);
_L6:
        timelydayviewpager.setCurrentItem(j, flag);
        return;
_L4:
        if (TimelyDayViewPager.getPosition(timelydayviewpager.timeToday) == j)
        {
            time = timelydayviewpager.timeToday;
            Object obj = timelydayviewpager.getChildAtPosition(j);
            if (obj == null)
            {
                obj = timelydayviewpager.dayViewPagerAdapter;
                obj.manualScrollPosition = j;
                obj.manualScrollPositionTime = time;
            } else
            {
                timelydayviewpager.dayViewPagerAdapter.manuallySetScrollPosition(((PagedDayView) (obj)), time);
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        Object obj1 = timelydayviewpager.getChildAtPosition(j);
        if (obj1 == null)
        {
            obj1 = timelydayviewpager.dayViewPagerAdapter;
            obj1.manualScrollPosition = j;
            obj1.manualScrollPositionTime = time;
        } else
        {
            timelydayviewpager.dayViewPagerAdapter.manuallySetScrollPosition(((PagedDayView) (obj1)), time);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final boolean hasMiniMonth()
    {
        return true;
    }

    public final boolean inGridMode()
    {
        return list != null && list.viewMode == 1;
    }

    final boolean isActionbarTitleAnimated()
    {
        return !mIsTabletConfig && animatedToolbarTitleHelper != null && !inGridMode() && !list.isAnimating();
    }

    public final boolean isMiniMonthDraggable()
    {
        return !mIsTabletConfig || mIsPortrait;
    }

    public final boolean isMiniMonthToggleable()
    {
        return !mIsTabletConfig || mIsPortrait || isTargetingGridMode();
    }

    public final boolean isTargetingGridMode()
    {
        Bundle bundle = getArguments();
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        return bundle.getBoolean("args_in_grid_mode", fragmentactivity.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_key_grid_mode", false));
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        super.onCalendarPropertyChanged(i, obj);
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            break;
        }
        if (mLastSelectedTime.allDay)
        {
            mLastSelectedTime.second = 0;
            mLastSelectedTime.minute = 0;
            mLastSelectedTime.hour = 0;
        }
        mLastSelectedTime.normalizeSafe();
        goTo(mLastSelectedTime, false, true);
    }

    public final void onDayChanged(int i)
    {
        onNewDayOnTop(i);
    }

    public final void onDayOfMonthSelected(com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday)
    {
        Trace.beginSection("onDayOfMonthSelected");
        Time time = mLastSelectedTime;
        int i = calendarday.day;
        int j = calendarday.month;
        int k = calendarday.year;
        time.writeFieldsToImpl();
        time.impl.set(i, j, k);
        time.copyFieldsFromImpl();
        mLastSelectedTime.normalizeSafe();
        calendarday = mController;
        time = mLastSelectedTime;
        time.writeFieldsToImpl();
        calendarday.setTime(time.impl.toMillis(false));
        list.setForceShowPosition(TimelineRecyclerView.getPosition(mLastSelectedTime));
        goTo(mLastSelectedTime, false, true);
        Trace.endSection();
    }

    public final void onInitView(LayoutInflater layoutinflater, ViewGroup viewgroup, Recycler recycler)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        OnTimelineModeChangedListener ontimelinemodechangedlistener;
        TimelineRecyclerView.OnTimelinePositionChangedListener ontimelinepositionchangedlistener;
        TimelineAdapter timelineadapter;
        int i;
        boolean flag;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj2 = SwipeInteractionController.getInstance(((Activity) (obj)));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj3 = new GridDndController(((Activity) (obj)), new DndControllerDelegate());
        list = (TimelineRecyclerView)viewgroup.findViewById(0x7f100183);
        obj = NewEventTimeChangedListenerHolder.INSTANCE;
        obj1 = list;
        ((NewEventTimeChangedListenerHolder) (obj)).createNewEventTimeChangedListeners.add(obj1);
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj instanceof OnTimelineGestureListener)
        {
            obj1 = (OnTimelineGestureListener)obj;
        } else
        {
            obj1 = null;
        }
        obj4 = list;
        obj5 = mDataFactory;
        ontimelinemodechangedlistener = timelineModeChangedListener;
        ontimelinepositionchangedlistener = timelinePositionChangedListener;
        obj4.agendaScrollOffsetNow = 0x7fffffff;
        obj4.dataFactory = ((DataFactory) (obj5));
        timelineadapter = ((TimelineRecyclerView) (obj4)).timelineAdapter;
        timelineadapter.dataFactory = ((DataFactory) (obj5));
        timelineadapter.dayViewConfig = ((DayViewConfig) (obj4));
        timelineadapter.animationStatus = ((com.google.android.calendar.timely.animations.TimelineAgendaGridAnimationStatus) (obj4));
        timelineadapter.timelineModeChangedListener = ontimelinemodechangedlistener;
        timelineadapter.onTimelineGestureListener = ((OnTimelineGestureListener) (obj1));
        timelineadapter.chipRecycler = recycler;
        timelineadapter.swipeDelegate = ((TimelyDayView.SwipeGestureDelegate) (obj2));
        ((DataFactory) (obj5)).registerOnAllEventsDataReadyListener(((DataFactory.OnAllEventsDataReadyListener) (obj4)));
        obj4.timelineModeChangedListener = ontimelinemodechangedlistener;
        obj4.timelinePositionChangedListener = ontimelinepositionchangedlistener;
        if (forceShowInitialDay)
        {
            list.setForceShowPosition(TimelineRecyclerView.getPosition(mLastSelectedTime));
        }
        list.setOnScrollListener(new _cls1());
        list.scrollingEnabled = true;
        dayPager = (TimelyDayViewPager)viewgroup.findViewById(0x7f100184);
        dayPager.setOnPageChangeListener(dayPager);
        obj1 = NewEventTimeChangedListenerHolder.INSTANCE;
        obj2 = dayPager;
        ((NewEventTimeChangedListenerHolder) (obj1)).createNewEventTimeChangedListeners.add(obj2);
        obj1 = DndEventHandler.create(((com.google.android.calendar.timely.gridviews.DndEventHandler.Delegate) (obj3)), viewgroup);
        obj2 = dayPager;
        obj3 = mDataFactory;
        obj4 = timelineModeChangedListener;
        obj5 = ((TimelyDayViewPager) (obj2)).dayViewPagerAdapter;
        obj5.dndHandler = ((DndEventHandler) (obj1));
        obj5.mDataFactory = ((DataFactory) (obj3));
        obj5.timelineModeChangedListener = ((OnTimelineModeChangedListener) (obj4));
        obj5.chipRecycler = recycler;
        ((DataFactory) (obj3)).registerOnAllEventsDataReadyListener(((DataFactory.OnAllEventsDataReadyListener) (obj2)));
        obj2.accessibilityEnabled = AccessibilityUtils.isAccessibilityEnabled(((TimelyDayViewPager) (obj2)).getContext());
        dayPager.setOnPageChangeListener(new _cls2());
        obj2 = dayPager;
        if (super.mHost == null)
        {
            obj1 = null;
        } else
        {
            obj1 = (FragmentActivity)super.mHost.mActivity;
        }
        ((ViewPager) (obj2)).setPageMargin(((FragmentActivity) (obj1)).getResources().getDimensionPixelOffset(0x7f0e03da));
        dayPager.setActive(true);
        flag = isTargetingGridMode();
        obj1 = list;
        if (flag)
        {
            i = 8;
        } else
        {
            i = 0;
        }
        ((TimelineRecyclerView) (obj1)).setVisibility(i);
        list.setGridMode(flag);
        obj1 = dayPager;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 8;
        }
        ((TimelyDayViewPager) (obj1)).setVisibility(i);
        if (flag)
        {
            dayPager.dayViewPagerAdapter.setNextScrollPositionManually = true;
        }
        obj2 = recycleCalendar;
        if (super.mHost == null)
        {
            obj1 = null;
        } else
        {
            obj1 = (FragmentActivity)super.mHost.mActivity;
        }
        ((Calendar) (obj2)).setTimeZone(Utils.getTimeZone(((Context) (obj1))));
        super.onInitView(layoutinflater, viewgroup, recycler);
        monthLabelProvider = new MonthLabelProvider(((Context) (obj)), mIsTabletConfig, mTodayYear);
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        recycler = monthLabelProvider;
        obj = layoutinflater.findViewById(0x7f100101);
        if (obj != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            layoutinflater = null;
        } else
        {
            obj1 = (TextView)((View) (obj)).findViewById(0x7f100102);
            View view = layoutinflater.findViewById(0x7f100105);
            TextView textview = (TextView)view.findViewById(0x7f100106);
            if (Material.robotoMedium != null)
            {
                layoutinflater = Material.robotoMedium;
            } else
            {
                layoutinflater = Typeface.create("sans-serif-medium", 0);
                Material.robotoMedium = layoutinflater;
            }
            textview.setTypeface(layoutinflater);
            layoutinflater = new AnimatedToolbarTitleHelper(((View) (obj)), view, new MonthLabelThresholdEvaluator(((TextView) (obj1)), recycler), recycler, this);
        }
        animatedToolbarTitleHelper = layoutinflater;
        contentContainer = viewgroup.findViewById(0x7f100182);
        annotateVisualElement(inGridMode());
    }

    public final void onMiniMonthVisibilityChanged(boolean flag)
    {
        super.onMiniMonthVisibilityChanged(flag);
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null)
        {
            return;
        }
        if (!isMiniMonthToggleable())
        {
            LogUtils.wtf(TAG, "Minimonth visibility changed, but it's not actually toggleable.", new Object[0]);
            return;
        }
        if (flag)
        {
            list.scrollingEnabled = false;
            dayPager.setActive(false);
            handler.postDelayed(new _cls3(), 15L);
        } else
        {
            handler.postDelayed(new _cls4(), 15L);
            list.scrollingEnabled = true;
            dayPager.setActive(true);
        }
        updateAlternateMonthRange();
    }

    final void onNewDayOnTop(int i)
    {
        julianDayOnTop = i;
        updateMiniMonth(julianDayOnTop);
        mLastSelectedTime.setJulianDaySafe(julianDayOnTop);
        CalendarController calendarcontroller = mController;
        Time time = mLastSelectedTime;
        time.writeFieldsToImpl();
        calendarcontroller.setTime(time.impl.toMillis(true));
        updateTitle();
    }

    public final void onResume()
    {
        super.onResume();
        Object obj = VisualElementHolder.instance;
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
        visualelementattacher.recordImpression(((Context) (obj)), contentContainer);
    }

    public final void onStart()
    {
        super.onStart();
        Object obj;
        TimelineRecyclerView timelinerecyclerview;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = SwipeInteractionController.getInstance(((Activity) (obj)));
        timelinerecyclerview = list;
        if (timelinerecyclerview != null)
        {
            obj.scheduleView = timelinerecyclerview;
        }
    }

    public final void onStop()
    {
        super.onStop();
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = SwipeInteractionController.getInstance(((Activity) (obj)));
        if (list == ((SwipeInteractionController) (obj)).scheduleView)
        {
            obj.scheduleView = null;
        }
    }

    public final void performDelayedAction(DelayedActionDescription delayedactiondescription)
    {
        if (!inGridMode())
        {
            for (int i = 0; i < list.getChildCount(); i++)
            {
                TimelyDayView timelydayview = (TimelyDayView)list.getChildAt(i);
                if (timelydayview != null && timelydayview.shouldDelayAction(delayedactiondescription))
                {
                    timelydayview.performDelayedAction(delayedactiondescription);
                    return;
                }
            }

        }
        LogUtils.wtf(TAG, "Failing to perform delayed action due to not finding day view with chip", new Object[0]);
    }

    public final void setViewTranslationX(float f)
    {
        if (inGridMode())
        {
            dayPager.setTranslationX(f);
        }
    }

    public final void setViewTranslationY(float f)
    {
        super.setViewTranslationY(f);
        Object obj;
        if (inGridMode())
        {
            obj = dayPager;
        } else
        {
            obj = list;
        }
        ((View) (obj)).setTranslationY(f);
    }

    public final boolean shouldDelayAction(DelayedActionDescription delayedactiondescription)
    {
        boolean flag;
        boolean flag1;
        flag1 = false;
        flag = flag1;
        if (inGridMode()) goto _L2; else goto _L1
_L1:
        int i = 0;
_L7:
        flag = flag1;
        if (i >= list.getChildCount()) goto _L2; else goto _L3
_L3:
        TimelyDayView timelydayview = (TimelyDayView)list.getChildAt(i);
        if (timelydayview == null || !timelydayview.shouldDelayAction(delayedactiondescription)) goto _L5; else goto _L4
_L4:
        flag = true;
_L2:
        return flag;
_L5:
        i++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    protected final void updateTopValues(boolean flag)
    {
        int i;
        int j;
        if (flag)
        {
            Resources resources = requireContext().getResources();
            if (mIsPortrait)
            {
                i = 0x7f0e043e;
            } else
            {
                i = 0x7f0e03db;
            }
            i = resources.getDimensionPixelOffset(i);
            j = resources.getDimensionPixelOffset(0x7f0e0445);
        } else
        {
            j = 0;
            i = 0;
        }
        super.mView.findViewById(0x7f100182).setPadding(0, i, 0, 0);
        if (list != null)
        {
            ((android.view.ViewGroup.MarginLayoutParams)list.getLayoutParams()).topMargin = j;
            list.requestLayout();
        }
        if (dayPager != null)
        {
            ((android.view.ViewGroup.MarginLayoutParams)dayPager.getLayoutParams()).topMargin = j;
            dayPager.requestLayout();
        }
    }


    private class _cls5
        implements OnTimelineModeChangedListener
    {

        private final DualTimelineGridFragment this$0;

        public final void onModeChangeFinished()
        {
            if (list.viewMode == 1)
            {
                list.setVisibility(8);
                dayPager.setVisibility(0);
                hasMiniMonth();
                if (isMiniMonthToggleable())
                {
                    mMonthViewPagerContainer.animate().translationZ(mMiniMonthElevation).start();
                }
            }
        }

        public final void onModeChanged(int i)
        {
            int i1 = 0;
            hasMiniMonth();
            if (mTimelyMonthViewPager != null && mMinimonthToggledOpen)
            {
                pendingModeChangePosition = i;
                mController.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(new com.google.android.calendar.CalendarController.Command(4096L));
                return;
            }
            if (inGridMode())
            {
                Object obj = dayPager;
                obj = ((TimelyDayViewPager) (obj)).getChildAtPosition(((ViewPager) (obj)).getCurrentItem());
                int j;
                int k;
                int l;
                if (obj != null)
                {
                    k = -((PagedDayView) (obj)).pagedScrollView.getScrollY();
                    l = ((PagedDayView) (obj)).getGridHourStartOffset();
                    i1 = ((PagedDayView) (obj)).stickyAllDayEventsView.getHeight();
                    int j1;
                    if (((PagedDayView) (obj)).collapseButton.getVisibility() == 8)
                    {
                        j = 0;
                    } else
                    {
                        j = ((PagedDayView) (obj)).collapseButton.getHeight();
                    }
                    j1 = j + i1;
                    i1 = ((PagedDayView) (obj)).stickyAllDayEventsView.getScrollY();
                    j = l;
                    l = j1;
                } else
                {
                    l = 0;
                    j = 0;
                    k = 0;
                }
                j1 = k;
                k = j;
                j = j1;
            } else
            {
                obj = (TimelyDayView)list.getChildAt(0);
                if (obj != null)
                {
                    j = ((TimelyDayView) (obj)).getTop();
                    k = ((TimelyDayView) (obj)).getGridHourStartOffset();
                    i1 = 0;
                    l = 0;
                } else
                {
                    i1 = 0;
                    l = 0;
                    k = 0;
                    j = 0;
                }
            }
            onModeChanged(i, j, k, l, i1);
        }

        public final void onModeChanged(int i, int j, int k, int l, int i1)
        {
            boolean flag1 = true;
            Object obj;
            Object obj1;
            boolean flag;
            if (list.viewMode == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                flag1 = false;
            }
            getArguments().putBoolean("args_in_grid_mode", flag1);
            if (!flag1)
            {
                list.setVisibility(0);
                dayPager.setVisibility(8);
            } else
            {
                obj = dayPager;
                obj1 = ((TimelyDayViewPager) (obj)).getChildAtPosition(i);
                if (obj1 == null)
                {
                    obj = ((TimelyDayViewPager) (obj)).dayViewPagerAdapter;
                    obj.manualScrollPosition = i;
                    obj.manualScrollPositionTime = null;
                } else
                {
                    ((TimelyDayViewPager) (obj)).dayViewPagerAdapter.manuallySetScrollPosition(((PagedDayView) (obj1)), null);
                }
                dayPager.setCurrentItem(i, false);
            }
            obj1 = list;
            if (!((TimelineRecyclerView) (obj1)).isAnimating && ((TimelineRecyclerView) (obj1)).isEnabled())
            {
                obj1.position = i;
                obj1.oldOffsetFromTop = j;
                obj1.allDayEventsHeight = l;
                obj1.allDayEventsScroll = i1;
                obj1.originalRatio = ((TimelineRecyclerView) (obj1)).sparseBusyRatio;
                if (((TimelineRecyclerView) (obj1)).originalRatio >= 0.5F)
                {
                    k = 0;
                }
                obj1.newOffsetFromTop = k;
                if (((TimelineRecyclerView) (obj1)).originalRatio < 0.5F)
                {
                    obj = ValueAnimator.ofFloat(new float[] {
                        0.0F, 1.0F
                    }).setDuration(300L);
                    ((ValueAnimator) (obj)).setInterpolator(new AccelerateInterpolator());
                } else
                {
                    obj = ValueAnimator.ofFloat(new float[] {
                        1.0F, 0.0F
                    }).setDuration(300L);
                    ((ValueAnimator) (obj)).setInterpolator(new DecelerateInterpolator());
                }
                ((ValueAnimator) (obj)).addUpdateListener(((android.animation.ValueAnimator.AnimatorUpdateListener) (obj1)));
                ((ValueAnimator) (obj)).addListener(((android.animation.Animator.AnimatorListener) (obj1)));
                ((ValueAnimator) (obj)).start();
            }
            obj = DualTimelineGridFragment.this;
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            SharedPrefs.setSharedPreference(((Context) (obj)), "preference_key_back_to_month", false);
            onNewDayOnTop(DualTimelineGridFragment.getJulianDayFromPosition(i));
        }

        _cls5()
        {
            this$0 = DualTimelineGridFragment.this;
            super();
        }
    }


    private class _cls6
        implements TimelineRecyclerView.OnTimelinePositionChangedListener
    {

        private final DualTimelineGridFragment this$0;

        public final void onTimelinePositionChanged(int i)
        {
            if (list != null)
            {
                list.updateCurrentHeaderPosition(i);
            }
        }

        _cls6()
        {
            this$0 = DualTimelineGridFragment.this;
            super();
        }
    }


    private class _cls1 extends android.support.v7.widget.RecyclerView.OnScrollListener
    {

        private final DualTimelineGridFragment this$0;

        public final void onScrollStateChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4IILG_0(int i)
        {
            DualTimelineGridFragment dualtimelinegridfragment = DualTimelineGridFragment.this;
            boolean flag;
            if (i == 2)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            BitmapCacheHolder.getEventImageCache().setBlocking(flag);
            BitmapCacheHolder.getMonthHeaderBitmapCache().setBlocking(flag);
            ContactPhotoCacheHolder.getContactPhotoCache().setBlocking(flag);
            dualtimelinegridfragment.list.setTimelineScrollState(i);
        }

        public final void onScrolled$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(RecyclerView recyclerview, int i)
        {
            AnimatedToolbarTitleHelper animatedtoolbartitlehelper;
            int i1;
            boolean flag2;
            int l = -1;
            boolean flag = false;
            flag2 = true;
            boolean flag1 = true;
            DualTimelineGridFragment dualtimelinegridfragment = DualTimelineGridFragment.this;
            recyclerview = dualtimelinegridfragment.list;
            int k;
            if (recyclerview.getChildCount() > 0)
            {
                k = ((TimelineRecyclerView) (recyclerview)).layoutManager.findFirstVisibleItemPosition();
            } else
            {
                k = -1;
            }
            i1 = 0x253d8c + k;
            if (dualtimelinegridfragment.julianDayOnTop != i1)
            {
                TimelineRecyclerView timelinerecyclerview;
                int j;
                if (dualtimelinegridfragment.julianDayOnTop == -1)
                {
                    j = 0;
                } else
                if (i1 > dualtimelinegridfragment.julianDayOnTop)
                {
                    j = 1;
                } else
                {
                    j = -1;
                }
                dualtimelinegridfragment.list.setScrollingDirection(j, k);
                if (!dualtimelinegridfragment.isActionbarTitleAnimated())
                {
                    dualtimelinegridfragment.onNewDayOnTop(i1);
                } else
                {
                    dualtimelinegridfragment.julianDayOnTop = i1;
                }
            }
            if (!dualtimelinegridfragment.isActionbarTitleAnimated()) goto _L2; else goto _L1
_L1:
            animatedtoolbartitlehelper = dualtimelinegridfragment.animatedToolbarTitleHelper;
            timelinerecyclerview = dualtimelinegridfragment.list;
            if (animatedtoolbartitlehelper.animationHelper.animating) goto _L2; else goto _L3
_L3:
            animatedtoolbartitlehelper.time.setJulianDaySafe(i1);
            j = l;
            if (timelinerecyclerview.getChildCount() > 0)
            {
                j = timelinerecyclerview.layoutManager.findFirstVisibleItemPosition();
            }
            recyclerview = timelinerecyclerview.findViewHolderForAdapterPosition(j);
            if (!(((android.support.v7.widget.RecyclerView.ViewHolder) (recyclerview)).itemView instanceof TimelyDayView))
            {
                recyclerview = null;
            } else
            {
                recyclerview = (TimelyDayView)((android.support.v7.widget.RecyclerView.ViewHolder) (recyclerview)).itemView;
            }
            if (recyclerview == null) goto _L2; else goto _L4
_L4:
            if (animatedtoolbartitlehelper.dayView == null || animatedtoolbartitlehelper.time.monthDay == 1)
            {
                animatedtoolbartitlehelper.dayView = recyclerview;
            }
            recyclerview = ((RecyclerView) (timelinerecyclerview)).mLayout;
            recyclerview = animatedtoolbartitlehelper.dayView;
            j = recyclerview.getTop();
            k = ((android.support.v7.widget.RecyclerView.LayoutParams)recyclerview.getLayoutParams()).mDecorInsets.top;
            l = animatedtoolbartitlehelper.dayView.getMonthLabelBottom();
            recyclerview = new MonthLabelThresholdEvaluator.State(animatedtoolbartitlehelper.time, i1, i, j - k, l);
            if (animatedtoolbartitlehelper.thresholdEvaluator.canAnimate(recyclerview)) goto _L6; else goto _L5
_L5:
            if (((MonthLabelThresholdEvaluator.State) (recyclerview)).time.monthDay == 1)
            {
                i = ((flag1) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                animatedtoolbartitlehelper.listener.onDayChanged(i1);
            }
_L2:
            if (dualtimelinegridfragment.list != null)
            {
                for (i = ((flag) ? 1 : 0); i < dualtimelinegridfragment.list.getChildCount(); i++)
                {
                    recyclerview = dualtimelinegridfragment.list.getChildAt(i);
                    if (recyclerview instanceof SimpleOnScrollListener)
                    {
                        ((SimpleOnScrollListener)recyclerview).onScrolled(i);
                    }
                }

            }
            break; /* Loop/switch isn't completed */
_L6:
            if (i1 != animatedtoolbartitlehelper.firstDayOfMonthJulianDay)
            {
                animatedtoolbartitlehelper.firstDayOfMonthJulianDay = i1;
            }
            if (animatedtoolbartitlehelper.thresholdEvaluator.isAnimationThresholdMet(recyclerview))
            {
                if (((MonthLabelThresholdEvaluator.State) (recyclerview)).scrollDeltaY >= 0.0F)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    flag2 = false;
                }
                animatedtoolbartitlehelper.animationHelper.startAnimation(flag2);
            }
            if (true) goto _L2; else goto _L7
_L7:
        }

        _cls1()
        {
            this$0 = DualTimelineGridFragment.this;
            super();
        }
    }


    private class _cls2
        implements android.support.v4.view.ViewPager.OnPageChangeListener
    {

        private final DualTimelineGridFragment this$0;

        public final void onPageScrollStateChanged(int i)
        {
            if (i == 0)
            {
                i = dayPager.getCurrentItem();
                onNewDayOnTop(DualTimelineGridFragment.getJulianDayFromPosition(i));
            }
        }

        public final void onPageScrolled(int i, float f, int j)
        {
        }

        public final void onPageSelected(int i)
        {
            if (inGridMode())
            {
                list.setForceShowPosition(i);
            }
        }

        _cls2()
        {
            this$0 = DualTimelineGridFragment.this;
            super();
        }
    }


    private class _cls3
        implements Runnable
    {

        private final DualTimelineGridFragment this$0;

        public final void run()
        {
            list.invalidate();
            list.setEnabled(false);
            dayPager.setEnabled(false);
        }

        _cls3()
        {
            this$0 = DualTimelineGridFragment.this;
            super();
        }
    }


    private class _cls4
        implements Runnable
    {

        private final DualTimelineGridFragment this$0;

        public final void run()
        {
            list.setEnabled(true);
            dayPager.setEnabled(true);
            if (pendingModeChangePosition != -1)
            {
                int i = pendingModeChangePosition;
                pendingModeChangePosition = -1;
                timelineModeChangedListener.onModeChanged(i);
            }
        }

        _cls4()
        {
            this$0 = DualTimelineGridFragment.this;
            super();
        }
    }

}
