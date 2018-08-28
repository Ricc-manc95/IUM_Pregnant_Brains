// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Trace;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.android.datetimepicker.Utils;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.CalendarController;
import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.MiniMonthInteractionController;
import com.google.android.calendar.newevent.CreateNewEventView;
import com.google.android.calendar.newevent.NewEventTimeChangedListenerHolder;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.gridviews.ChipRecyclerManager;
import com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.ActivitySingletonCache;
import com.google.android.calendar.utils.a11y.A11yHelper;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyMonthViewPager, TimelyColorMonthView, TimelyMonthPagerAdapter, DataFactory, 
//            EventRangedQueryHandler

public abstract class BaseCalendarFragment extends Fragment
    implements com.google.android.calendar.CalendarController.Command.Handler, MiniMonthInteractionController, TimelyMonthPagerAdapter.OnDayOfMonthSelectedListener, OnPropertyChangedListener
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/BaseCalendarFragment);
    private DelayedUpdateHelper delayedUpdateHelper;
    private View divider;
    private boolean inCreate;
    private CalendarProperties mCalendarProperties;
    public CalendarController mController;
    public DataFactory mDataFactory;
    private View mDragUpView;
    public boolean mIsPortrait;
    public boolean mIsTabletConfig;
    public Time mLastSelectedTime;
    public float mMiniMonthElevation;
    public boolean mMinimonthToggledOpen;
    public View mMonthViewPagerContainer;
    private com.android.datetimepicker.date.MonthAdapter.CalendarDay mMonthViewSelectedDay;
    public TimelyMonthViewPager mTimelyMonthViewPager;
    public int mTodayYear;
    private Time recycleTime;
    private long timeLeftApp;
    private final int viewResource;

    protected BaseCalendarFragment(int i)
    {
        inCreate = false;
        mMonthViewSelectedDay = new com.android.datetimepicker.date.MonthAdapter.CalendarDay();
        recycleTime = new Time("UTC");
        mLastSelectedTime = new Time();
        Time time = mLastSelectedTime;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        time.set(l);
        viewResource = i;
    }

    private final boolean actionBarIsNull()
    {
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity instanceof EventFragmentHostActivity)
        {
            if (super.mHost == null)
            {
                fragmentactivity = null;
            } else
            {
                fragmentactivity = (FragmentActivity)super.mHost.mActivity;
            }
            return ((EventFragmentHostActivity)fragmentactivity).getSupportActionBar() == null;
        }
        return fragmentactivity.getActionBar() == null;
    }

    public static Bundle createArgs(long l)
    {
        Bundle bundle = new Bundle();
        bundle.putLong("args_selected_time_millis", l);
        return bundle;
    }

    protected abstract void eventsChanged();

    public final int getCurrentMonthHeight()
    {
        TimelyMonthViewPager timelymonthviewpager = mTimelyMonthViewPager;
        if (!timelymonthviewpager.animatedMonthHeightChanges)
        {
            return timelymonthviewpager.getHeight();
        }
        View view;
        int j;
        for (int i = 0; i < timelymonthviewpager.getChildCount(); i++)
        {
            view = timelymonthviewpager.getChildAt(i);
            if (!(view instanceof TimelyColorMonthView))
            {
                j = -1;
            } else
            {
                j = ((Integer)((HashMap)view.getTag()).get("page_position")).intValue();
            }
            if (j == timelymonthviewpager.getCurrentItem())
            {
                return view.getHeight();
            }
        }

        LogUtils.e(TimelyMonthViewPager.TAG, "Unable to find visible child while calculating month height.", new Object[0]);
        return timelymonthviewpager.getHeight();
    }

    public final ViewPager getDatePicker()
    {
        return mTimelyMonthViewPager;
    }

    public final View getDatePickerContainer()
    {
        return mMonthViewPagerContainer;
    }

    public final View getDragUpView()
    {
        return mDragUpView;
    }

    public final int getMonthEndJulianDay()
    {
        return com.google.android.calendar.Utils.getJulianDay(mLastSelectedTime.year, mLastSelectedTime.month, Utils.getDaysInMonth(mLastSelectedTime.month, mLastSelectedTime.year));
    }

    public abstract String getPrimesLogTag();

    public final long getSupportedCommands()
    {
        return 160L;
    }

    public void goTo(Time time, boolean flag, boolean flag1)
    {
        mLastSelectedTime.set(time);
        CalendarController calendarcontroller = mController;
        Time time1 = mLastSelectedTime;
        time1.writeFieldsToImpl();
        calendarcontroller.setTime(time1.impl.toMillis(false));
        if (hasMiniMonth() && mTimelyMonthViewPager != null)
        {
            Object obj = mMonthViewSelectedDay;
            int i = time.year;
            int j = time.month;
            int k = time.monthDay;
            obj.year = i;
            obj.month = j;
            obj.day = k;
            obj = mTimelyMonthViewPager;
            time = mMonthViewSelectedDay;
            com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday = ((TimelyMonthViewPager) (obj)).currentDay;
            calendarday.year = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (time)).year;
            calendarday.month = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (time)).month;
            calendarday.day = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (time)).day;
            ((ViewPager) (obj)).setCurrentItem((((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (time)).year - 1970) * 12 + ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (time)).month);
            obj = ((TimelyMonthViewPager) (obj)).adapter;
            if (((TimelyMonthPagerAdapter) (obj)).selectedDay.compareTo(time) != 0)
            {
                com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday1 = ((TimelyMonthPagerAdapter) (obj)).selectedDay;
                calendarday1.year = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (time)).year;
                calendarday1.month = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (time)).month;
                calendarday1.day = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (time)).day;
                ((PagerAdapter) (obj)).notifyDataSetChanged();
            }
        }
    }

    public final void handleCommand(com.google.android.calendar.CalendarController.Command command)
    {
        boolean flag1 = false;
        if (command.type == 32L)
        {
            boolean flag;
            if ((command.extraLong & 8L) != 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag || (command.extraLong & 2L) != 0L)
            {
                flag1 = true;
            }
            goTo(command.selectedTime, flag, flag1);
        } else
        if (command.type == 128L)
        {
            eventsChanged();
            return;
        }
    }

    public abstract boolean hasMiniMonth();

    public final boolean isFragmentAttached()
    {
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        return fragmentactivity != null;
    }

    public boolean isMiniMonthDraggable()
    {
        return isMiniMonthToggleable();
    }

    public abstract boolean isMiniMonthToggleable();

    public final boolean isMiniMonthVisible()
    {
        return mMinimonthToggledOpen;
    }

    public final void onAttach(Activity activity)
    {
        super.onAttach(activity);
        Object obj = CalendarProperties.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        mCalendarProperties = (CalendarProperties)obj;
        mCalendarProperties.registerListener(0, this);
        mCalendarProperties.registerListener(7, this);
        mCalendarProperties.registerListener(5, this);
        mCalendarProperties.registerListener(13, this);
        obj = mLastSelectedTime;
        String s = com.google.android.calendar.Utils.getTimeZoneId(activity);
        ((Time) (obj)).writeFieldsToImpl();
        ((Time) (obj)).impl.switchTimezone(s);
        ((Time) (obj)).copyFieldsFromImpl();
        mController = (CalendarController)CalendarController.instances.getInstance(activity);
        mIsTabletConfig = activity.getResources().getBoolean(0x7f0c0016);
        boolean flag;
        if (requireContext().getResources().getConfiguration().orientation == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mIsPortrait = flag;
    }

    public void onCalendarPropertyChanged(int i, Object obj)
    {
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   0: 115
    //                   5: 66
    //                   7: 37;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L4:
        if (hasMiniMonth() && mTimelyMonthViewPager != null)
        {
            mTimelyMonthViewPager.updateWeekNumber(((Boolean)obj).booleanValue());
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (hasMiniMonth() && mTimelyMonthViewPager != null)
        {
            TimelyMonthViewPager timelymonthviewpager = mTimelyMonthViewPager;
            i = ((Integer)obj).intValue();
            if (timelymonthviewpager.adapter != null)
            {
                obj = timelymonthviewpager.adapter;
                obj.firstDayOfWeek = i;
                ((TimelyMonthPagerAdapter) (obj)).updateVisibleMonths();
                return;
            }
        }
        if (true) goto _L1; else goto _L2
_L2:
        Time time = mLastSelectedTime;
        obj = (String)obj;
        time.writeFieldsToImpl();
        time.impl.switchTimezone(((String) (obj)));
        time.copyFieldsFromImpl();
        return;
    }

    public void onCreate(Bundle bundle)
    {
        Trace.beginSection("CalendarFragment.onCreate");
        PerformanceMetricCollector performancemetriccollector;
        super.onCreate(bundle);
        if (TextUtils.isEmpty(getPrimesLogTag()))
        {
            break MISSING_BLOCK_LABEL_92;
        }
        performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector != null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        bundle;
        Trace.endSection();
        throw bundle;
        String s;
        performancemetriccollector = (PerformanceMetricCollector)performancemetriccollector;
        s = getPrimesLogTag();
        long l;
        if (bundle != null)
        {
            bundle = "Recreated";
        } else
        {
            bundle = "Created";
        }
        performancemetriccollector.recordMemory(String.format(null, "%s.%s", new Object[] {
            s, bundle
        }));
        l = getArguments().getLong("args_selected_time_millis", 0L);
        if (l == 0L)
        {
            break MISSING_BLOCK_LABEL_120;
        }
        mLastSelectedTime.set(l);
        Trace.endSection();
        return;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        viewgroup = null;
        Trace.beginSection("CalendarFragment.onCreateView");
        CreateNewEventView.removeSelectedTime();
        if (bundle == null) goto _L2; else goto _L1
_L1:
        long l1 = bundle.getLong("key_time_left_app", timeLeftApp);
        if (Clock.mockedTimestamp <= 0L) goto _L4; else goto _L3
_L3:
        long l = Clock.mockedTimestamp;
_L9:
        if (l - l1 < 0xdbba0L) goto _L6; else goto _L5
_L5:
        bundle = mLastSelectedTime;
        if (Clock.mockedTimestamp <= 0L) goto _L8; else goto _L7
_L7:
        l = Clock.mockedTimestamp;
_L10:
        bundle.set(l);
_L2:
        mDataFactory = (DataFactory)super.mFragmentManager.findFragmentByTag("data_factory");
        bundle = (ViewGroup)layoutinflater.inflate(viewResource, null);
        if (super.mHost != null)
        {
            break MISSING_BLOCK_LABEL_195;
        }
_L11:
        onInitView(layoutinflater, bundle, ChipRecyclerManager.getOrCreateRecycler(viewgroup));
        goTo(mLastSelectedTime, false, true);
        delayedUpdateHelper = new DelayedUpdateHelper(bundle);
        inCreate = true;
        Trace.endSection();
        return bundle;
_L4:
        l = System.currentTimeMillis();
          goto _L9
_L8:
        l = System.currentTimeMillis();
          goto _L10
_L6:
        mLastSelectedTime.set(bundle.getLong("selected_time_millis"));
          goto _L2
        layoutinflater;
        Trace.endSection();
        throw layoutinflater;
        viewgroup = (FragmentActivity)super.mHost.mActivity;
          goto _L11
    }

    public void onDayOfMonthSelected(com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday)
    {
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
        goTo(mLastSelectedTime, false, true);
    }

    public void onDestroy()
    {
        if (!TextUtils.isEmpty(getPrimesLogTag()))
        {
            PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
            if (performancemetriccollector == null)
            {
                throw new NullPointerException(String.valueOf("PrimesLogger not set"));
            }
            ((PerformanceMetricCollector)performancemetriccollector).recordMemory(String.format(null, "%s.Destroyed", new Object[] {
                getPrimesLogTag()
            }));
        }
        super.onDestroy();
    }

    public final void onDestroyView()
    {
        Object obj = (DataFactory)super.mFragmentManager.findFragmentByTag("data_factory");
        if (obj != null)
        {
            obj = ((DataFactory) (obj)).eventQueryHandler;
            if (((EventRangedQueryHandler) (obj)).allDataReadyListeners != null)
            {
                ((EventRangedQueryHandler) (obj)).allDataReadyListeners.clear();
            }
        }
        NewEventTimeChangedListenerHolder.INSTANCE.createNewEventTimeChangedListeners.clear();
        super.onDestroyView();
    }

    public final void onDetach()
    {
        super.onDetach();
        if (mCalendarProperties != null)
        {
            mCalendarProperties.unregisterListener(0, this);
            mCalendarProperties.unregisterListener(7, this);
            mCalendarProperties.unregisterListener(5, this);
            mCalendarProperties.unregisterListener(13, this);
        }
    }

    public void onInitView(LayoutInflater layoutinflater, ViewGroup viewgroup, Recycler recycler)
    {
        recycler = null;
        long l;
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        layoutinflater = new Time(com.google.android.calendar.Utils.getTimeZoneId(layoutinflater, null));
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        layoutinflater.set(l);
        layoutinflater.writeFieldsToImpl();
        ((Time) (layoutinflater)).impl.normalize(true);
        layoutinflater.copyFieldsFromImpl();
        mTodayYear = ((Time) (layoutinflater)).year;
        divider = viewgroup.findViewById(0x7f100185);
        if (!mIsTabletConfig)
        {
            if (divider != null)
            {
                divider.setVisibility(8);
            }
            if (!actionBarIsNull())
            {
                if (super.mHost == null)
                {
                    layoutinflater = null;
                } else
                {
                    layoutinflater = (FragmentActivity)super.mHost.mActivity;
                }
                if (layoutinflater instanceof EventFragmentHostActivity)
                {
                    EventRangedQueryHandler eventrangedqueryhandler;
                    int i;
                    int j;
                    int k;
                    if (super.mHost == null)
                    {
                        layoutinflater = null;
                    } else
                    {
                        layoutinflater = (FragmentActivity)super.mHost.mActivity;
                    }
                    ((EventFragmentHostActivity)layoutinflater).getSupportActionBar().setElevation(0.0F);
                }
            }
        }
        if (hasMiniMonth())
        {
            layoutinflater = mMonthViewSelectedDay;
            i = mLastSelectedTime.year;
            j = mLastSelectedTime.month;
            k = mLastSelectedTime.monthDay;
            layoutinflater.year = i;
            layoutinflater.month = j;
            layoutinflater.day = k;
            mMonthViewPagerContainer = viewgroup.findViewById(0x7f100241);
            mDragUpView = viewgroup.findViewById(0x7f100240);
            mTimelyMonthViewPager = (TimelyMonthViewPager)viewgroup.findViewById(0x7f100242);
            layoutinflater = mTimelyMonthViewPager;
            viewgroup = mDataFactory;
            layoutinflater.animatedMonthHeightChanges = layoutinflater.getResources().getBoolean(0x7f0c0003);
            eventrangedqueryhandler = ((DataFactory) (viewgroup)).eventQueryHandler;
            if (eventrangedqueryhandler.allDataReadyListeners == null)
            {
                eventrangedqueryhandler.allDataReadyListeners = new ArrayList();
            }
            eventrangedqueryhandler.allDataReadyListeners.add(layoutinflater);
            layoutinflater.adapter = new TimelyMonthPagerAdapter((Activity)layoutinflater.getContext(), viewgroup, this);
            layoutinflater.setAdapter(((TimelyMonthViewPager) (layoutinflater)).adapter);
            mTimelyMonthViewPager.addOnPageChangeListener(new _cls1());
            if (isMiniMonthToggleable())
            {
                layoutinflater = requireContext().getResources();
                onMiniMonthVisibilityChanged(false);
                if (!mIsTabletConfig && !actionBarIsNull())
                {
                    mMiniMonthElevation = layoutinflater.getDimension(0x7f0e004d);
                    mMonthViewPagerContainer.setTranslationZ(mMiniMonthElevation);
                }
                if (layoutinflater.getConfiguration().orientation == 1)
                {
                    mMonthViewPagerContainer.setTranslationY(-layoutinflater.getDimension(0x7f0e00c7));
                } else
                if (mIsTabletConfig)
                {
                    float f = layoutinflater.getDimension(0x7f0e00cb) + layoutinflater.getDimension(0x7f0e00d5);
                    if (super.mHost == null)
                    {
                        layoutinflater = recycler;
                    } else
                    {
                        layoutinflater = (FragmentActivity)super.mHost.mActivity;
                    }
                    if (!RtlUtils.isLayoutDirectionRtl(layoutinflater))
                    {
                        f = -f;
                    }
                    mMonthViewPagerContainer.setTranslationX(f);
                    return;
                }
            }
        }
    }

    public void onMiniMonthVisibilityChanged(boolean flag)
    {
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!isMiniMonthToggleable())
        {
            LogUtils.wtf(TAG, "Minimonth visibility changed, but it's not  actually toggleable.", new Object[0]);
            return;
        }
        mMinimonthToggledOpen = flag;
        if (mTimelyMonthViewPager != null)
        {
            TimelyMonthViewPager timelymonthviewpager = mTimelyMonthViewPager;
            timelymonthviewpager.adapter.datePickerVisibility = flag;
            if (flag)
            {
                int i = 0;
                while (i < timelymonthviewpager.getChildCount()) 
                {
                    Object obj = timelymonthviewpager.getChildAt(i);
                    int j;
                    if (!(obj instanceof TimelyColorMonthView))
                    {
                        j = -1;
                    } else
                    {
                        j = ((Integer)((HashMap)((View) (obj)).getTag()).get("page_position")).intValue();
                    }
                    if (j == timelymonthviewpager.getCurrentItem())
                    {
                        obj = (TimelyColorMonthView)obj;
                        A11yHelper.getInstance();
                        if (A11yHelper.isAccessibilityEnabled(((TimelyColorMonthView) (obj)).getContext()))
                        {
                            ((TimelyColorMonthView) (obj)).post(new TimelyColorMonthView._cls2(((TimelyColorMonthView) (obj))));
                        }
                    }
                    i++;
                }
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public final void onMiniMonthVisibilityChanging(boolean flag)
    {
    }

    public final void onPause()
    {
        super.onPause();
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        timeLeftApp = l;
    }

    public void onResume()
    {
        Object obj1 = null;
        super.onResume();
        Object obj;
        if (!inCreate)
        {
            eventsChanged();
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            if (l - timeLeftApp >= 0xdbba0L)
            {
                timeLeftApp = l;
                obj = mLastSelectedTime;
                if (Clock.mockedTimestamp > 0L)
                {
                    l = Clock.mockedTimestamp;
                } else
                {
                    l = System.currentTimeMillis();
                }
                ((Time) (obj)).set(l);
                goTo(mLastSelectedTime, false, true);
            }
        }
        inCreate = false;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (((Context) (obj)).getResources().getBoolean(0x7f0c0016))
        {
            boolean flag;
            if (super.mHost == null)
            {
                obj = obj1;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            if (!((Context) (obj)).getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_showMoreEvents", false))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            updateTopValues(flag);
        }
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        Time time = mLastSelectedTime;
        time.writeFieldsToImpl();
        bundle.putLong("selected_time_millis", time.impl.toMillis(false));
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        bundle.putLong("key_time_left_app", l);
        super.onSaveInstanceState(bundle);
    }

    public final void pointTo(int i)
    {
    }

    public final void refreshState()
    {
    }

    protected final void runWhenNotInteracting(Runnable runnable)
    {
        DelayedUpdateHelper delayedupdatehelper = delayedUpdateHelper;
        CalendarExecutor.MAIN.checkOnThread();
        DelayedUpdateHelper.logFishfoodInfo("Attempt Update: %s", new Object[] {
            delayedupdatehelper.view
        });
        delayedupdatehelper.delayedUpdate = runnable;
        delayedupdatehelper.updateIfIdleAndNeeded();
    }

    public void setViewTranslationX(float f)
    {
    }

    public void setViewTranslationY(float f)
    {
        if (divider != null)
        {
            divider.setTranslationY(f);
        }
    }

    public final void updateMiniMonth(int i)
    {
        if (hasMiniMonth())
        {
            recycleTime.setJulianDaySafe(i);
            com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday = mMonthViewSelectedDay;
            i = recycleTime.year;
            int j = recycleTime.month;
            int k = recycleTime.monthDay;
            calendarday.year = i;
            calendarday.month = j;
            calendarday.day = k;
        }
        if (hasMiniMonth() && mTimelyMonthViewPager != null && !mMinimonthToggledOpen)
        {
            Object obj = mTimelyMonthViewPager;
            com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday1 = mMonthViewSelectedDay;
            com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday2 = ((TimelyMonthViewPager) (obj)).currentDay;
            calendarday2.year = calendarday1.year;
            calendarday2.month = calendarday1.month;
            calendarday2.day = calendarday1.day;
            ((ViewPager) (obj)).setCurrentItem((calendarday1.year - 1970) * 12 + calendarday1.month);
            obj = ((TimelyMonthViewPager) (obj)).adapter;
            if (((TimelyMonthPagerAdapter) (obj)).selectedDay.compareTo(calendarday1) != 0)
            {
                com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday3 = ((TimelyMonthPagerAdapter) (obj)).selectedDay;
                calendarday3.year = calendarday1.year;
                calendarday3.month = calendarday1.month;
                calendarday3.day = calendarday1.day;
                ((PagerAdapter) (obj)).notifyDataSetChanged();
            }
        }
    }

    protected abstract void updateTopValues(boolean flag);


    private class _cls1
        implements android.support.v4.view.ViewPager.OnPageChangeListener
    {

        private final BaseCalendarFragment this$0;
        private boolean wasDragging;

        public final void onPageScrollStateChanged(int i)
        {
            if (i == 1)
            {
                wasDragging = true;
            } else
            if (i == 0 && wasDragging)
            {
                wasDragging = false;
                com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday = TimelyMonthPagerAdapter.getDayFromPosition(mTimelyMonthViewPager.getCurrentItem());
                com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday1 = mTimelyMonthViewPager.currentDay;
                if (calendarday1.month == calendarday.month && calendarday1.year == calendarday.year)
                {
                    calendarday = calendarday1;
                }
                onDayOfMonthSelected(calendarday);
                return;
            }
        }

        public final void onPageScrolled(int i, float f, int j)
        {
        }

        public final void onPageSelected(int i)
        {
        }

        _cls1()
        {
            this$0 = BaseCalendarFragment.this;
            super();
        }
    }

}
