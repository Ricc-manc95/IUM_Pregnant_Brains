// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.calendar.CalendarController;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.newevent.NewEventTimeChangedListenerHolder;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.gridviews.DndEventHandler;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.gridviews.GridDndController;
import com.google.android.calendar.timely.gridviews.GridHourDrawable;
import com.google.android.calendar.timely.gridviews.GridHourView;
import com.google.android.calendar.timely.gridviews.GridViewFrame;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            BaseCalendarFragment, DataFactory, WeekRecyclerAdapter, WeekRecyclerView, 
//            PagedScrollView

public class ThreeDayViewFragment extends BaseCalendarFragment
    implements com.google.android.calendar.newevent.NewEventTimeChangedListenerHolder.OnCreateNewEventTimeChangedListener, DataFactory.OnAllEventsDataReadyListener
{
    final class DndControllerDelegate
        implements com.google.android.calendar.timely.gridviews.GridDndController.Delegate
    {

        private final GridHourDrawable hourDrawable;
        private final ThreeDayViewFragment this$0;

        public final GridHourDrawable getCurrentHourDrawable()
        {
            return hourDrawable;
        }

        public final String getViewMode()
        {
            return "preference_value_3_day_view";
        }

        public final void scrollHorizontally(int i)
        {
            recyclerView.smoothScrollBy((recyclerAdapter.pageWidth / 7) * i, 0);
        }

        public final void scrollVertically(int i)
        {
            boolean flag;
            if (layoutManager.getChildCount() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            }
            View view = layoutManager.getChildAt(0).findViewById(0x7f10022f);
            if (!(view instanceof PagedScrollView))
            {
                throw new IllegalStateException();
            } else
            {
                ((PagedScrollView)view).verticalScrollBy(i);
                return;
            }
        }

        public DndControllerDelegate(GridHourDrawable gridhourdrawable)
        {
            this$0 = ThreeDayViewFragment.this;
            super();
            hourDrawable = gridhourdrawable;
        }
    }

    final class OnScrollListener extends android.support.v7.widget.RecyclerView.OnScrollListener
    {

        private final ThreeDayViewFragment this$0;

        public final void onScrollStateChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4IILG_0(int i)
        {
            if (i == 0)
            {
                WeekRecyclerView weekrecyclerview = recyclerView;
                boolean flag = RtlUtils.isLayoutDirectionRtl(weekrecyclerview.getContext());
                i = 0x7fffffff;
                int ai[] = new int[2];
                weekrecyclerview.getLocationOnScreen(ai);
                int j;
                int j1;
                if (flag)
                {
                    j = ai[0] + weekrecyclerview.getWidth();
                } else
                {
                    j = ai[0];
                }
                j1 = weekrecyclerview.getChildCount();
                for (int k = 0; k < j1; k++)
                {
                    GridViewFrame gridviewframe = ((WeekRecyclerAdapter.ViewHolder)weekrecyclerview.getChildViewHolder(weekrecyclerview.getChildAt(k))).daysContent;
                    int k1 = gridviewframe.getChildCount();
                    int l1 = gridviewframe.getChildrenBeforeGridDayViews();
                    int l = 0;
                    while (l < k1 - l1) 
                    {
                        GridDayView griddayview = (GridDayView)gridviewframe.getChildAt(gridviewframe.getChildrenBeforeGridDayViews() + l);
                        griddayview.getLocationOnScreen(ai);
                        int i1;
                        if (flag)
                        {
                            i1 = ai[0];
                            i1 = (griddayview.getWidth() + i1) - 1;
                        } else
                        {
                            i1 = ai[0] + 1;
                        }
                        if (Math.abs(i1 - j) < Math.abs(i))
                        {
                            i = i1 - j;
                        }
                        l++;
                    }
                }

                if (i == 0x7fffffff)
                {
                    i = 0;
                }
                if (i != 0)
                {
                    weekrecyclerview.smoothScrollBy(i, 0);
                }
                updateAccessibility();
            }
        }

        public final void onScrolled$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(RecyclerView recyclerview, int i)
        {
            if (recyclerView.getChildCount() != 0)
            {
                if ((i = getCurrentJulianDay()) != currentJulianDay)
                {
                    currentJulianDay = i;
                    mLastSelectedTime.setJulianDaySafe(i);
                    CalendarController calendarcontroller = mController;
                    Time time = mLastSelectedTime;
                    time.writeFieldsToImpl();
                    calendarcontroller.setTime(time.impl.toMillis(true));
                    allDayArrowView.setJulianDay(i);
                    updateMiniMonth(i);
                    updateTitle();
                    if (recyclerview.getScrollState() == 0)
                    {
                        updateAccessibility();
                        return;
                    }
                }
            }
        }

        OnScrollListener()
        {
            this$0 = ThreeDayViewFragment.this;
            super();
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/ThreeDayViewFragment);
    public AllDayHeaderArrow allDayArrowView;
    private View container;
    public int currentJulianDay;
    private int lastAccJulianDay;
    public LinearLayoutManager layoutManager;
    public WeekRecyclerAdapter recyclerAdapter;
    public WeekRecyclerView recyclerView;

    public ThreeDayViewFragment()
    {
        super(0x7f05016e);
    }

    private final void updateContentDescription()
    {
        Object obj1 = null;
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (!AccessibilityUtils.isAccessibilityEnabled(((Context) (obj))))
        {
            return;
        }
        StringBuilder stringbuilder;
        int i;
        int k;
        int l;
        int i1;
        if (CalendarProperties.getBooleanProperty(7).booleanValue())
        {
            i = currentJulianDay;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            i = com.android.datetimepicker.Utils.getWeekNumberInYear(i, Utils.getFirstDayOfWeekAsTime(((Context) (obj))));
        } else
        {
            i = -1;
        }
        k = mLastSelectedTime.year;
        l = mLastSelectedTime.month;
        i1 = mLastSelectedTime.monthDay;
        stringbuilder = new StringBuilder();
        obj = DateTimeFormatHelper.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        }
        stringbuilder.append(((DateTimeFormatHelper)obj).getDateRangeText(new int[] {
            k, l, i1
        }, 3, true, i));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (AlternateCalendarUtils.isAlternateCalendarEnabled(((Context) (obj))))
        {
            stringbuilder.append(", ");
            int j = Utils.getJulianDay(mLastSelectedTime.year, mLastSelectedTime.month, mLastSelectedTime.monthDay);
            android.content.res.Resources resources = requireContext().getResources();
            if (super.mHost == null)
            {
                obj = obj1;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            stringbuilder.append(AlternateCalendarUtils.getAlternateAccessibilityDateRange(j, (j + 3) - 1, resources, PreferencesConstants.getAlternateCalendarPref(((Context) (obj)))));
        }
        container.setContentDescription(stringbuilder);
        container.announceForAccessibility(container.getContentDescription());
    }

    protected final void eventsChanged()
    {
        while (layoutManager != null && layoutManager.getChildCount() == 0 || mDataFactory == null) 
        {
            return;
        }
        Object obj1 = mDataFactory;
        Object obj;
        WeekRecyclerAdapter weekrecycleradapter;
        int i;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        ((DataFactory) (obj1)).setHideDeclinedEvents(((Context) (obj)));
        mDataFactory.updateToday();
        obj = mDataFactory;
        i = Utils.getJulianFirstDayFromWeeksSinceEpoch(layoutManager.findFirstVisibleItemPosition(), Utils.getFirstDayOfWeekAsTime(recyclerAdapter.context));
        obj1 = recyclerView;
        weekrecycleradapter = (WeekRecyclerAdapter)((RecyclerView) (obj1)).mAdapter;
        ((DataFactory) (obj)).refreshDataAroundDay((((RecyclerView) (obj1)).computeHorizontalScrollOffset() % weekrecycleradapter.pageWidth) / (weekrecycleradapter.pageWidth / 7) + i, true, true);
    }

    final int getCurrentJulianDay()
    {
        int i = Utils.getJulianFirstDayFromWeeksSinceEpoch(layoutManager.findFirstVisibleItemPosition(), Utils.getFirstDayOfWeekAsTime(recyclerAdapter.context));
        WeekRecyclerView weekrecyclerview = recyclerView;
        WeekRecyclerAdapter weekrecycleradapter = (WeekRecyclerAdapter)((RecyclerView) (weekrecyclerview)).mAdapter;
        return (weekrecyclerview.computeHorizontalScrollOffset() % weekrecycleradapter.pageWidth) / (weekrecycleradapter.pageWidth / 7) + i;
    }

    protected final String getPrimesLogTag()
    {
        return String.format(null, "%sDayView", new Object[] {
            Integer.valueOf(3)
        });
    }

    protected final void goTo(Time time, boolean flag, boolean flag1)
    {
        LinearLayoutManager linearlayoutmanager = null;
        int i = 0;
        if (recyclerView == null)
        {
            LogUtils.d(TAG, "Tried to goto a time but recyclerView hadn't been created yet!", new Object[0]);
        } else
        {
            super.goTo(time, flag, flag1);
            time.writeFieldsToImpl();
            int j = android.text.format.Time.getJulianDay(time.impl.toMillis(false), time.gmtoff);
            currentJulianDay = j;
            mDataFactory.setTimePassage(0, j);
            mDataFactory.refreshDataAroundDay(j, false, false);
            Time time1 = mLastSelectedTime;
            Object obj;
            boolean flag2;
            int k;
            int l;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            k = Utils.getWeekNumberFromTime(time1, ((Context) (obj)));
            if (super.mHost == null)
            {
                obj = linearlayoutmanager;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            l = Utils.getJulianFirstDayFromWeeksSinceEpoch(k, Utils.getFirstDayOfWeekAsTime(((Context) (obj)))) - j;
            obj = recyclerView;
            flag2 = i;
            if (flag1)
            {
                flag2 = i;
                if (!mMinimonthToggledOpen)
                {
                    flag2 = true;
                }
            }
            linearlayoutmanager = (LinearLayoutManager)((RecyclerView) (obj)).mLayout;
            i = ((WeekRecyclerAdapter)((RecyclerView) (obj)).mAdapter).pageWidth / 7;
            if (i == 0)
            {
                obj.positionToScroll = k;
                obj.offsetDay = l;
                linearlayoutmanager.scrollToPosition(k);
            } else
            {
                linearlayoutmanager.scrollToPositionWithOffset(k, i * l);
            }
            if (linearlayoutmanager.getChildCount() > 0 && linearlayoutmanager.findFirstVisibleItemPosition() <= k && k <= linearlayoutmanager.findLastVisibleItemPosition())
            {
                obj = linearlayoutmanager.findViewByPosition(k);
                if (flag2)
                {
                    obj = (GridViewFrame)((View) (obj)).findViewById(0x7f100230);
                    ((GridViewFrame) (obj)).post(new com.google.android.calendar.timely.gridviews.GridViewFrame._cls1(((GridViewFrame) (obj))));
                } else
                {
                    obj = (GridViewFrame)((View) (obj)).findViewById(0x7f100230);
                    ((GridViewFrame) (obj)).post(new com.google.android.calendar.timely.gridviews.GridViewFrame._cls2(((GridViewFrame) (obj)), new Time(time)));
                }
            }
            allDayArrowView.setJulianDay(j);
            updateAccessibility();
            updateTitle();
            if (!mMinimonthToggledOpen)
            {
                obj = recyclerAdapter;
                obj.autoScrollItem = k;
                obj.autoScrollIgnoreTime = flag1;
                obj.autoScrollTime = time;
                return;
            }
        }
    }

    public final boolean hasMiniMonth()
    {
        return mIsPortrait;
    }

    public final boolean isMiniMonthToggleable()
    {
        return mIsPortrait;
    }

    public final void onAllEventsDataReady()
    {
        WeekRecyclerAdapter weekrecycleradapter = recyclerAdapter;
        weekrecycleradapter.getClass();
        class .Lambda._cls0
            implements Runnable
        {

            private final WeekRecyclerAdapter arg$1;

            public final void run()
            {
                ((android.support.v7.widget.RecyclerView.Adapter) (arg$1)).mObservable.notifyChanged();
            }

            .Lambda._cls0(WeekRecyclerAdapter weekrecycleradapter)
            {
                arg$1 = weekrecycleradapter;
            }
        }

        runWhenNotInteracting(new .Lambda._cls0(weekrecycleradapter));
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        super.onCalendarPropertyChanged(i, obj);
        if (i == 5 || i == 0)
        {
            obj = recyclerAdapter;
            WeekRecyclerAdapter.ViewHolder viewholder;
            for (Iterator iterator = ((WeekRecyclerAdapter) (obj)).holders.iterator(); iterator.hasNext(); ((WeekRecyclerAdapter) (obj)).init(viewholder))
            {
                viewholder = (WeekRecyclerAdapter.ViewHolder)iterator.next();
                ((WeekRecyclerAdapter) (obj)).clean(viewholder);
            }

        } else
        if (i == 7)
        {
            updateContentDescription();
        }
    }

    public final void onCreateNewEventTimeChanged$5152ILG_0()
    {
        for (int i = 0; i < layoutManager.getChildCount(); i++)
        {
            GridViewFrame gridviewframe = (GridViewFrame)layoutManager.getChildAt(i).findViewById(0x7f100230);
            for (int j = 0; j < 7; j++)
            {
                ((GridDayView)gridviewframe.getChildAt(gridviewframe.getChildrenBeforeGridDayViews() + j)).updateCreateNewEventView();
            }

        }

    }

    public final void onInitView(LayoutInflater layoutinflater, ViewGroup viewgroup, Recycler recycler)
    {
        super.onInitView(layoutinflater, viewgroup, recycler);
        Object obj;
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        obj = new GridDndController(layoutinflater, new DndControllerDelegate(((GridHourView)viewgroup.findViewById(0x7f10001d)).gridHourDrawable));
        NewEventTimeChangedListenerHolder.INSTANCE.createNewEventTimeChangedListeners.add(this);
        mDataFactory.registerOnAllEventsDataReadyListener(this);
        obj = DndEventHandler.create(((com.google.android.calendar.timely.gridviews.DndEventHandler.Delegate) (obj)), viewgroup);
        recyclerAdapter = new WeekRecyclerAdapter(layoutinflater, viewgroup, recycler, mDataFactory, ((DndEventHandler) (obj)));
        layoutManager = new LinearLayoutManager(0, false);
        container = viewgroup.findViewById(0x7f100373);
        allDayArrowView = (AllDayHeaderArrow)viewgroup.findViewById(0x7f100374);
        recyclerView = (WeekRecyclerView)viewgroup.findViewById(0x7f100375);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setOnScrollListener(new OnScrollListener());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setImportantForAccessibility(2);
        layoutinflater = VisualElementHolder.instance;
        if (layoutinflater == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)layoutinflater).attachThreeDayView(container);
            return;
        }
    }

    public final void onMiniMonthVisibilityChanged(boolean flag)
    {
        super.onMiniMonthVisibilityChanged(flag);
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
        visualelementattacher.recordImpression(((Context) (obj)), container);
    }

    public final void setViewTranslationY(float f)
    {
        super.setViewTranslationY(f);
        container.setTranslationY(f);
    }

    public final void updateAccessibility()
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        while (!AccessibilityUtils.isAccessibilityEnabled(((Context) (obj))) || currentJulianDay == lastAccJulianDay) 
        {
            return;
        }
        lastAccJulianDay = currentJulianDay;
        for (int i = 0; i < layoutManager.getChildCount(); i++)
        {
            GridViewFrame gridviewframe = (GridViewFrame)layoutManager.getChildAt(i).findViewById(0x7f100230);
            int l = gridviewframe.firstJulianDay;
            int j = 0;
            while (j < 7) 
            {
                GridDayView griddayview = (GridDayView)gridviewframe.getChildAt(gridviewframe.getChildrenBeforeGridDayViews() + j);
                int k;
                if (l + j >= currentJulianDay && l + j < currentJulianDay + 3)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (k != 0)
                {
                    k = 1;
                } else
                {
                    k = 4;
                }
                griddayview.setImportantForAccessibility(k);
                j++;
            }
        }

        updateContentDescription();
    }

    final void updateTitle()
    {
        int i = currentJulianDay;
        Object obj;
        Object obj1;
        int k;
        boolean flag;
        if (mLastSelectedTime.year == mTodayYear)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        k = DateTimeFormatHelper.getToolbarFormatFlags(false, flag);
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj1 = Utils.getTimeZoneId(((Context) (obj)));
        obj = new Time(((String) (obj1)));
        ((Time) (obj)).setJulianDaySafe(i);
        obj1 = new Time(((String) (obj1)));
        ((Time) (obj1)).setJulianDaySafe((i + 3) - 1);
        mController.updateVisibleRange(this, ((Time) (obj)), ((Time) (obj1)), null, false, k);
        if (mMinimonthToggledOpen)
        {
            obj = new Time("UTC");
            int j = Utils.getJulianFirstDayFromMonth(super.mLastSelectedTime.month, super.mLastSelectedTime.year);
            ((Time) (obj)).writeFieldsToImpl();
            ((Time) (obj)).impl.setJulianDay(j);
            ((Time) (obj)).copyFieldsFromImpl();
            obj1 = new Time("UTC");
            j = getMonthEndJulianDay();
            ((Time) (obj1)).writeFieldsToImpl();
            ((Time) (obj1)).impl.setJulianDay(j);
            ((Time) (obj1)).copyFieldsFromImpl();
            CalendarController calendarcontroller = mController;
            com.google.android.calendar.CalendarController.Command command = new com.google.android.calendar.CalendarController.Command(8192L);
            command.startTime = ((Time) (obj));
            command.endTime = ((Time) (obj1));
            calendarcontroller.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(command);
            return;
        } else
        {
            CalendarController calendarcontroller1 = mController;
            com.google.android.calendar.CalendarController.Command command1 = new com.google.android.calendar.CalendarController.Command(8192L);
            command1.startTime = ((Time) (obj));
            command1.endTime = ((Time) (obj1));
            calendarcontroller1.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(command1);
            return;
        }
    }

    protected final void updateTopValues(boolean flag)
    {
    }

}
