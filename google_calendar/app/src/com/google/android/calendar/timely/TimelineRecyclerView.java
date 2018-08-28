// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.SystemClock;
import android.os.Trace;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.common.view.NinjaLinearLayoutManager;
import com.google.android.calendar.sharedprefs.SharedPrefs;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.animations.TimelineAgendaGridAnimationStatus;
import com.google.android.calendar.timely.interaction.InteractionTracker;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.timely:
//            AgendaScrollCallback, DayViewConfig, TimelineAdapter, DualTimelineGridFragment, 
//            TimelyDayView, OnTimelineModeChangedListener, TimelyDayHeaderView, StickyAllDayEventsView, 
//            DataFactory, MonthData

public class TimelineRecyclerView extends RecyclerView
    implements android.animation.Animator.AnimatorListener, android.animation.ValueAnimator.AnimatorUpdateListener, android.support.v7.widget.RecyclerView.RecyclerListener, com.google.android.calendar.newevent.NewEventTimeChangedListenerHolder.OnCreateNewEventTimeChangedListener, AgendaScrollCallback, DataFactory.OnAllEventsDataReadyListener, DayViewConfig, TimelineAgendaGridAnimationStatus, com.google.android.calendar.timely.interaction.InteractionTracker.Listener
{
    static final class ClearSelectedHourListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public final boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            if (Math.abs(f1) > 3000F)
            {
                class .Lambda._cls0
                    implements Runnable
                {

                    public static final Runnable $instance = new .Lambda._cls0();

                    public final void run()
                    {
                        CreateNewEventView.removeSelectedTime();
                    }


                private .Lambda._cls0()
                {
                }
                }

                motionevent = (FluentFuture)CalendarExecutor.MAIN.submit(.Lambda._cls0..instance);
                return true;
            } else
            {
                return false;
            }
        }

        ClearSelectedHourListener()
        {
        }
    }

    public static interface HeaderViewDescriptor
    {

        public abstract CharSequence getAnnounceableDescription();
    }

    public static interface OnTimelinePositionChangedListener
    {

        public abstract void onTimelinePositionChanged(int i);
    }


    private final Runnable accessibilityAnnounce = new _cls1();
    public int agendaScrollOffsetNow;
    public int allDayEventsHeight;
    public int allDayEventsScroll;
    private boolean canChipBeClickable;
    private boolean canDrawBackgroundImage;
    private GestureDetector clearSelectedHourDetector;
    public int currentItemPosition;
    public DataFactory dataFactory;
    private RangedData.OnUpdateListener dataFactoryListener;
    private int firstVisiblePosition;
    private boolean hasMonthHeaderImage;
    private boolean interactionInProgress;
    public boolean isAnimating;
    public boolean isPositioningToNow;
    private boolean lastAutoScroll;
    private Time lastGotoTime;
    public final LinearLayoutManager layoutManager;
    private android.support.v7.widget.RecyclerView.OnScrollListener listener;
    private boolean needsUpdateWhenIdle;
    public int newOffsetFromTop;
    public int oldOffsetFromTop;
    public float originalRatio;
    public int position;
    public Time recycleTime;
    private int scrollState;
    private int scrollingDirection;
    public boolean scrollingEnabled;
    public float sparseBusyRatio;
    public TimelineAdapter timelineAdapter;
    public OnTimelineModeChangedListener timelineModeChangedListener;
    public OnTimelinePositionChangedListener timelinePositionChangedListener;
    public int viewMode;
    private boolean waitingForToday;

    public TimelineRecyclerView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        scrollingEnabled = true;
        viewMode = -1;
        canDrawBackgroundImage = true;
        canChipBeClickable = true;
        hasMonthHeaderImage = true;
        interactionInProgress = false;
        currentItemPosition = -1;
        attributeset = super.mRecycler;
        if (((android.support.v7.widget.RecyclerView.Recycler) (attributeset)).mRecyclerPool == null)
        {
            attributeset.mRecyclerPool = new android.support.v7.widget.RecyclerView.RecycledViewPool();
        }
        ((android.support.v7.widget.RecyclerView.Recycler) (attributeset)).mRecyclerPool.setMaxRecycledViews(0, 40);
        clearSelectedHourDetector = new GestureDetector(context, new ClearSelectedHourListener());
        scrollState = 0;
        scrollingDirection = 0;
        needsUpdateWhenIdle = false;
        waitingForToday = false;
        recycleTime = new Time();
        agendaScrollOffsetNow = 0x7fffffff;
        layoutManager = new NinjaLinearLayoutManager(context, 1, false);
        setLayoutManager(layoutManager);
        timelineAdapter = new TimelineAdapter(context);
        super.mRecyclerListener = this;
        if (super.mItemAnimator != null)
        {
            super.mItemAnimator.endAnimations();
            super.mItemAnimator.mListener = null;
        }
        super.mItemAnimator = null;
        if (super.mItemAnimator != null)
        {
            super.mItemAnimator.mListener = super.mItemAnimatorListener;
        }
    }

    public static int getPosition(Time time)
    {
        time.writeFieldsToImpl();
        return DualTimelineGridFragment.getPositionFromJulianDay(android.text.format.Time.getJulianDay(time.impl.toMillis(false), time.gmtoff));
    }

    public final boolean canDrawBackgroundImage()
    {
        return canDrawBackgroundImage;
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if (!scrollingEnabled && motionevent.getAction() == 2)
        {
            return true;
        } else
        {
            clearSelectedHourDetector.onTouchEvent(motionevent);
            return super.dispatchTouchEvent(motionevent);
        }
    }

    public final AgendaScrollCallback getAgendaScrollCallback()
    {
        return this;
    }

    public final float getGridModeRatio()
    {
        return sparseBusyRatio;
    }

    public final int getScrollState()
    {
        return scrollState;
    }

    public final void goTo(Time time, boolean flag)
    {
        Trace.beginSection("TimelyListView goTo");
        if (super.mAdapter != null)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        lastGotoTime = time;
        lastAutoScroll = flag;
        Trace.endSection();
        return;
        final int position;
        time.writeFieldsToImpl();
        position = DualTimelineGridFragment.getPositionFromJulianDay(android.text.format.Time.getJulianDay(time.impl.toMillis(false), time.gmtoff));
        setScrollingDirection(0, position);
        timelineAdapter.refresh(position, false);
        if (getScrollState() == 2)
        {
            long l = SystemClock.uptimeMillis();
            dispatchTouchEvent(MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0));
        }
        time = recycleTime;
        if (Clock.mockedTimestamp <= 0L) goto _L2; else goto _L1
_L1:
        long l1 = Clock.mockedTimestamp;
_L7:
        time.set(l1);
        time = recycleTime;
        time.writeFieldsToImpl();
        Time time1;
        int i;
        int j;
        boolean flag1;
        if (position == DualTimelineGridFragment.getPositionFromJulianDay(android.text.format.Time.getJulianDay(time.impl.toMillis(false), time.gmtoff)))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        isPositioningToNow = flag1;
        flag1 = isPositioningToNow;
        if (viewMode != 1) goto _L4; else goto _L3
_L3:
        i = 0;
_L22:
        if (getChildCount() <= 0)
        {
            break MISSING_BLOCK_LABEL_520;
        }
        j = LinearLayoutManager.getPosition(getChildAt(0));
_L27:
        if (Math.abs(j - position) > 7) goto _L6; else goto _L5
_L5:
        layoutManager.scrollToPositionWithOffset(position, i);
        getViewTreeObserver().addOnPreDrawListener(new _cls4());
_L24:
        Trace.endSection();
        return;
_L2:
        l1 = System.currentTimeMillis();
          goto _L7
_L4:
        j = 0;
        i = j;
        if (!flag) goto _L9; else goto _L8
_L8:
        i = j;
        if (!flag1) goto _L9; else goto _L10
_L10:
        j = agendaScrollOffsetNow;
        if (j != 0x7fffffff) goto _L12; else goto _L11
_L11:
        time = getContext().getSharedPreferences("com.google.android.calendar_preferences", 0);
        i = time.getInt("preferences_last_day_opened", -1);
        time1 = new Time();
        if (Clock.mockedTimestamp <= 0L) goto _L14; else goto _L13
_L13:
        l1 = Clock.mockedTimestamp;
_L20:
        time1.set(l1);
        if (i == time1.yearDay) goto _L16; else goto _L15
_L15:
        time.edit().putInt("preferences_last_day_opened", time1.yearDay).apply();
        i = 1;
          goto _L17
_L25:
        waitingForToday = flag;
        i = 0;
_L9:
        time = timelineAdapter.getHeaderView(position);
        if (time != null) goto _L19; else goto _L18
_L18:
        throw null;
        time;
        Trace.endSection();
        throw time;
_L14:
        l1 = System.currentTimeMillis();
          goto _L20
_L19:
        time = ((Time) (((View)time).getTag()));
        if (time == null) goto _L22; else goto _L21
_L21:
        if (!(time instanceof Integer)) goto _L22; else goto _L23
_L23:
        i = -((Integer)time).intValue();
          goto _L22
_L6:
        layoutManager.scrollToPositionWithOffset(position, i);
        getViewTreeObserver().addOnPreDrawListener(new _cls4());
          goto _L24
_L17:
        if (i == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L25
_L16:
        i = 0;
          goto _L17
_L12:
        i = j;
        if (j != 0) goto _L22; else goto _L26
_L26:
        i = j;
          goto _L9
        j = -1;
          goto _L27
    }

    public final boolean inGridMode()
    {
        return viewMode == 1;
    }

    public final boolean inListView()
    {
        return true;
    }

    public final boolean isAnimating()
    {
        return isAnimating;
    }

    public final boolean isChipClickable()
    {
        return canChipBeClickable;
    }

    final void logListModeState()
    {
        Context context = getContext();
        Object obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        AnalyticsLogger analyticslogger = (AnalyticsLogger)obj;
        if (viewMode == 1)
        {
            obj = "day_grid";
        } else
        {
            obj = "agenda";
        }
        analyticslogger.trackView(context, ((String) (obj)));
    }

    public final void onAllEventsDataReady()
    {
        needsUpdateWhenIdle = true;
        updateIfIdleAndNeeded();
    }

    public void onAnimationCancel(Animator animator)
    {
    }

    public void onAnimationEnd(Animator animator)
    {
        int k = getChildCount();
        for (int i = 0; i < k; i++)
        {
            animator = (TimelyDayView)getChildAt(i);
            animator.invalidate();
            animator.invalidateChips();
        }

        animator = getContext();
        int j;
        boolean flag;
        boolean flag1;
        if (sparseBusyRatio > 0.5F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setGridMode(flag);
        flag1 = animator.getResources().getBoolean(0x7f0c0016);
        if (flag)
        {
            j = 0x7f100022;
        } else
        {
            j = 0x7f100004;
        }
        PreferencesUtils.setLastUsedView(animator, flag1, j);
        setEnabled(true);
        isAnimating = false;
        timelineModeChangedListener.onModeChangeFinished();
    }

    public void onAnimationRepeat(Animator animator)
    {
    }

    public void onAnimationStart(Animator animator)
    {
        int k;
        k = 0;
        isAnimating = true;
        setEnabled(false);
        int i1 = getChildCount();
        int i;
        if (viewMode == 1)
        {
            i = -oldOffsetFromTop;
        } else
        {
            i = -newOffsetFromTop;
        }
_L8:
        if (k >= i1) goto _L2; else goto _L1
_L1:
        animator = (TimelyDayView)getChildAt(k);
        if (((TimelyDayView) (animator)).dayHeaderView.position != position) goto _L4; else goto _L3
_L3:
        animator = ((TimelyDayView) (animator)).stickyAllDayEventsView;
        animator.setGridOffset(i);
        if (viewMode == 1)
        {
            animator.setGridHeight(allDayEventsHeight);
            animator.setGridScrollPosition(allDayEventsScroll);
        }
_L6:
        return;
_L4:
        k++;
        continue; /* Loop/switch isn't completed */
_L2:
        if (viewMode != 1) goto _L6; else goto _L5
_L5:
        animator = timelineAdapter;
        int l = position;
        int j1 = allDayEventsHeight;
        int k1 = allDayEventsScroll;
        int j;
        if (viewMode == 1)
        {
            j = -oldOffsetFromTop;
        } else
        {
            j = -newOffsetFromTop;
        }
        animator.animPosition = l;
        animator.animAllDayEventsHeight = j1;
        animator.animAllDayEventsScroll = k1;
        animator.animGridOffset = j;
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void onAnimationUpdate(ValueAnimator valueanimator)
    {
        valueanimator = (Float)valueanimator.getAnimatedValue();
        sparseBusyRatio = valueanimator.floatValue();
        final int position = getChildCount();
        for (int i = 0; i < position; i++)
        {
            ((TimelyDayView)getChildAt(i)).requestLayout();
        }

        float f;
        float f1;
        int j;
        if (originalRatio < 0.5F)
        {
            f = valueanimator.floatValue();
        } else
        {
            f = 1.0F - valueanimator.floatValue();
        }
        f1 = oldOffsetFromTop;
        j = (int)(f * (float)(newOffsetFromTop - oldOffsetFromTop) + f1);
        position = this.position;
        layoutManager.scrollToPositionWithOffset(position, j);
        getViewTreeObserver().addOnPreDrawListener(new _cls4());
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        Object obj = dataFactory;
        if (((DataFactory) (obj)).dataToday.numDays == 0)
        {
            obj = null;
        } else
        {
            obj = ((DataFactory) (obj)).dataToday;
        }
        dataFactoryListener = new _cls2();
        ((MonthData) (obj)).registerListener(((MonthData) (obj)).startDay, dataFactoryListener);
        if (((MonthData) (obj)).isDataReady())
        {
            ((MonthData) (obj)).unregisterListener(dataFactoryListener.getListenerTag(), dataFactoryListener.getListenerTagType());
            onDataReady();
        }
        InteractionTracker.getInstance().addListener(this);
        scrollingEnabled = true;
    }

    public final void onCreateNewEventTimeChanged$5152ILG_0()
    {
        if (timelineAdapter != null)
        {
            ((android.support.v7.widget.RecyclerView.Adapter) (timelineAdapter)).mObservable.notifyChanged();
        }
    }

    final void onDataReady()
    {
label0:
        {
            if (super.mAdapter == null)
            {
                setAdapter(timelineAdapter);
                if (lastGotoTime == null)
                {
                    break label0;
                }
                recycleTime.set(lastGotoTime);
                lastGotoTime = null;
                goTo(recycleTime, lastAutoScroll);
            }
            return;
        }
        Time time = recycleTime;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        time.set(l);
        goTo(recycleTime, true);
    }

    protected void onDetachedFromWindow()
    {
        InteractionTracker.getInstance().removeListener(this);
        super.onDetachedFromWindow();
        Object obj = dataFactory;
        if (((DataFactory) (obj)).dataToday.numDays == 0)
        {
            obj = null;
        } else
        {
            obj = ((DataFactory) (obj)).dataToday;
        }
        ((MonthData) (obj)).unregisterListener(dataFactoryListener.getListenerTag(), dataFactoryListener.getListenerTagType());
    }

    public final void onInteractionEnd()
    {
        interactionInProgress = false;
        class .Lambda._cls0
            implements Runnable
        {

            private final TimelineRecyclerView arg$1;

            public final void run()
            {
                arg$1.updateIfIdleAndNeeded();
            }

            .Lambda._cls0()
            {
                arg$1 = TimelineRecyclerView.this;
            }
        }

        FluentFuture fluentfuture = (FluentFuture)CalendarExecutor.MAIN.submit(new .Lambda._cls0());
    }

    public final void onInteractionStart()
    {
        interactionInProgress = true;
    }

    protected void onMeasure(int i, int j)
    {
        int k = i;
        if (android.view.View.MeasureSpec.getMode(i) == 0x80000000)
        {
            k = android.view.View.MeasureSpec.makeMeasureSpec(getResources().getDisplayMetrics().widthPixels / 2, 0x80000000);
        }
        super.onMeasure(k, j);
    }

    public final void onScrollStateChanged(int i)
    {
        super.onScrollStateChanged(i);
        if (listener != null)
        {
            listener.onScrollStateChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4IILG_0(i);
        }
    }

    public final void onScrolled(int i, int j)
    {
        super.onScrolled(i, j);
        if (scrollingEnabled)
        {
            if (getChildCount() > 0)
            {
                i = LinearLayoutManager.getPosition(getChildAt(0));
            } else
            {
                i = -1;
            }
            updateCurrentHeaderPosition(i);
            if (listener != null)
            {
                listener.onScrolled$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(this, j);
                return;
            }
        }
    }

    public final void onViewRecycled(android.support.v7.widget.RecyclerView.ViewHolder viewholder)
    {
        if (viewholder.itemView instanceof TimelyDayView)
        {
            ((TimelyDayView)viewholder.itemView).recycleDayView();
        }
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        if (!flag)
        {
            return false;
        } else
        {
            return super.requestChildRectangleOnScreen(view, rect, true);
        }
    }

    public void setAgendaScrollOffsetToNow(int i)
    {
        agendaScrollOffsetNow = i;
        if (waitingForToday)
        {
            getViewTreeObserver().addOnPreDrawListener(new _cls3());
        }
        if (waitingForToday || isPositioningToNow)
        {
            waitingForToday = false;
            class .Lambda._cls1
                implements Runnable
            {

                private final TimelineRecyclerView arg$1;

                public final void run()
                {
                    TimelineRecyclerView timelinerecyclerview = arg$1;
                    Time time = timelinerecyclerview.recycleTime;
                    long l;
                    if (Clock.mockedTimestamp > 0L)
                    {
                        l = Clock.mockedTimestamp;
                    } else
                    {
                        l = System.currentTimeMillis();
                    }
                    time.set(l);
                    timelinerecyclerview.goTo(timelinerecyclerview.recycleTime, true);
                    timelinerecyclerview.isPositioningToNow = false;
                }

            .Lambda._cls1()
            {
                arg$1 = TimelineRecyclerView.this;
            }
            }

            FluentFuture fluentfuture = (FluentFuture)CalendarExecutor.MAIN.submit(new .Lambda._cls1());
        }
    }

    public void setForceShowPosition(int i)
    {
        TimelineAdapter timelineadapter = timelineAdapter;
        int j = timelineadapter.forceShowPosition;
        timelineadapter.forceShowPosition = i;
        if (j != -1)
        {
            ((android.support.v7.widget.RecyclerView.Adapter) (timelineadapter)).mObservable.notifyItemRangeChanged(j, 1, null);
        }
        ((android.support.v7.widget.RecyclerView.Adapter) (timelineadapter)).mObservable.notifyItemRangeChanged(i, 1, null);
    }

    public final void setGridMode(boolean flag)
    {
        int i = 0;
        float f;
        boolean flag1;
        if (viewMode == -1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag)
        {
            i = 1;
        }
        if (flag)
        {
            f = 1.0F;
        } else
        {
            f = 0.0F;
        }
        sparseBusyRatio = f;
        if (i != viewMode)
        {
            viewMode = i;
            Context context = getContext();
            if (context != null)
            {
                SharedPrefs.setSharedPreference(context, "preference_key_grid_mode", flag);
            }
            if (!flag1)
            {
                logListModeState();
            }
        }
    }

    public final void setOnScrollListener(android.support.v7.widget.RecyclerView.OnScrollListener onscrolllistener)
    {
        listener = onscrolllistener;
    }

    public final void setScrollingDirection(int i, int j)
    {
        if (scrollingDirection != i || i == 0 && j != firstVisiblePosition)
        {
            scrollingDirection = i;
            firstVisiblePosition = j;
            TimelineAdapter timelineadapter = timelineAdapter;
            timelineadapter.scrollingVelocity = i;
            j = DualTimelineGridFragment.getJulianDayFromPosition(j);
            timelineadapter.dataFactory.setTimePassage(i, j);
        }
    }

    public void setTimelineScrollState(int i)
    {
        scrollState = i;
        if (i == 0)
        {
            TimelineAdapter timelineadapter;
            Object obj;
            TimelyDayView timelydayview;
            MonthData monthdata;
            int k;
            if (getChildCount() > 0)
            {
                i = layoutManager.findFirstVisibleItemPosition();
            } else
            {
                i = -1;
            }
            setScrollingDirection(0, i);
        }
        if (needsUpdateWhenIdle)
        {
            CalendarExecutor.MAIN.checkOnThread();
            if (scrollState == 0 && !interactionInProgress)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                needsUpdateWhenIdle = false;
                i = 0;
                while (i < getChildCount()) 
                {
                    obj = (TimelyDayView)getChildAt(i);
                    timelineadapter = timelineAdapter;
                    int j;
                    if (getChildCount() > 0)
                    {
                        j = LinearLayoutManager.getPosition(getChildAt(0));
                    } else
                    {
                        j = -1;
                    }
                    obj = (TimelineAdapter.ViewHolder)getChildViewHolder(((View) (obj)));
                    Trace.beginSection("ListAdapter updateView");
                    if (((TimelineAdapter.ViewHolder) (obj)).data != null)
                    {
                        timelydayview = (TimelyDayView)((TimelineAdapter.ViewHolder) (obj)).itemView;
                        monthdata = ((TimelineAdapter.ViewHolder) (obj)).data;
                        k = ((TimelineAdapter.ViewHolder) (obj)).julianDay;
                        boolean flag;
                        if (timelineadapter.forceShowPosition == i + j)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        timelydayview.onUpdate(monthdata, k, flag);
                    }
                    Trace.endSection();
                    i++;
                }
            }
        }
    }

    public final boolean shouldDrawDayHeader()
    {
        return true;
    }

    public final boolean shouldDrawExtraHeaders()
    {
        return true;
    }

    public final boolean shouldDrawMonthInDayHeader()
    {
        return false;
    }

    public final boolean shouldDrawNoEventsView()
    {
        return true;
    }

    public final boolean shouldDrawYearHeader()
    {
        return false;
    }

    public final boolean shouldNeverDrawMonthHeader()
    {
        return !hasMonthHeaderImage;
    }

    public final boolean shouldNeverDrawNowLine()
    {
        return false;
    }

    public final boolean supportsSwipe()
    {
        return sparseBusyRatio == 0.0F;
    }

    public final void updateCurrentHeaderPosition(int i)
    {
        if (currentItemPosition == i)
        {
            return;
        } else
        {
            currentItemPosition = i;
            removeCallbacks(accessibilityAnnounce);
            CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
            Runnable runnable = accessibilityAnnounce;
            TimeUnit timeunit = TimeUnit.MILLISECONDS;
            calendarexecutor.getDelegate().schedule(runnable, 300L, timeunit);
            return;
        }
    }

    final void updateIfIdleAndNeeded()
    {
        if (needsUpdateWhenIdle)
        {
            CalendarExecutor.MAIN.checkOnThread();
            int i;
            if (scrollState == 0 && !interactionInProgress)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                needsUpdateWhenIdle = false;
                i = 0;
                while (i < getChildCount()) 
                {
                    Object obj = (TimelyDayView)getChildAt(i);
                    TimelineAdapter timelineadapter = timelineAdapter;
                    int j;
                    if (getChildCount() > 0)
                    {
                        j = LinearLayoutManager.getPosition(getChildAt(0));
                    } else
                    {
                        j = -1;
                    }
                    obj = (TimelineAdapter.ViewHolder)getChildViewHolder(((View) (obj)));
                    Trace.beginSection("ListAdapter updateView");
                    if (((TimelineAdapter.ViewHolder) (obj)).data != null)
                    {
                        TimelyDayView timelydayview = (TimelyDayView)((TimelineAdapter.ViewHolder) (obj)).itemView;
                        MonthData monthdata = ((TimelineAdapter.ViewHolder) (obj)).data;
                        int k = ((TimelineAdapter.ViewHolder) (obj)).julianDay;
                        boolean flag;
                        if (timelineadapter.forceShowPosition == i + j)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        timelydayview.onUpdate(monthdata, k, flag);
                    }
                    Trace.endSection();
                    i++;
                }
            }
        }
    }

    private class _cls1
        implements Runnable
    {

        private final TimelineRecyclerView this$0;

        public final void run()
        {
            HeaderViewDescriptor headerviewdescriptor = timelineAdapter.getHeaderView(currentItemPosition);
            if (headerviewdescriptor != null)
            {
                announceForAccessibility(headerviewdescriptor.getAnnounceableDescription());
            }
        }

        _cls1()
        {
            this$0 = TimelineRecyclerView.this;
            super();
        }
    }


    private class _cls4
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        private final TimelineRecyclerView this$0;
        private final int val$position;

        public final boolean onPreDraw()
        {
            ViewTreeObserver viewtreeobserver = getViewTreeObserver();
            if (!viewtreeobserver.isAlive())
            {
                return true;
            } else
            {
                viewtreeobserver.removeOnPreDrawListener(this);
                timelinePositionChangedListener.onTimelinePositionChanged(position);
                return true;
            }
        }

        _cls4()
        {
            this$0 = TimelineRecyclerView.this;
            position = i;
            super();
        }
    }


    private class _cls2
        implements RangedData.OnUpdateListener
    {

        private int tag;
        public final TimelineRecyclerView this$0;

        public final int getListenerTag()
        {
            return tag;
        }

        public final int getListenerTagType()
        {
            return 0;
        }

        public final void postUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NKQRREEHK48OBKC4TKIMICCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FA9GMSPR5CH262T314HAN0P31EHIKCQBED5PMGPB49HKN6T35DPIN4EP9AO______0(MonthData monthdata, int i, RangedData.UpdateFinishedListener updatefinishedlistener)
        {
            if (monthdata.isDataReady())
            {
                monthdata.unregisterListener(tag, 0);
                class .Lambda._cls0
                    implements Runnable
                {

                    private final _cls2 arg$1;
                    private final RangedData.UpdateFinishedListener arg$2;

                    public final void run()
                    {
                        _cls2 _lcls2 = arg$1;
                        RangedData.UpdateFinishedListener updatefinishedlistener1 = arg$2;
                        _lcls2._fld0.onDataReady();
                        updatefinishedlistener1.notifyUpdateFinished();
                    }

                .Lambda._cls0(RangedData.UpdateFinishedListener updatefinishedlistener)
                {
                    arg$1 = _cls2.this;
                    arg$2 = updatefinishedlistener;
                }
                }

                monthdata = (FluentFuture)CalendarExecutor.MAIN.submit(new .Lambda._cls0(updatefinishedlistener));
                return;
            } else
            {
                updatefinishedlistener.notifyUpdateFinished();
                return;
            }
        }

        public final void setListenerTag(int i)
        {
            tag = i;
        }

        _cls2()
        {
            this$0 = TimelineRecyclerView.this;
            super();
        }
    }


    private class _cls3
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        private final TimelineRecyclerView this$0;

        public final boolean onPreDraw()
        {
            ViewTreeObserver viewtreeobserver = getViewTreeObserver();
            if (!viewtreeobserver.isAlive())
            {
                return true;
            }
            View view = getChildAt(0);
            if (view instanceof TimelyDayView)
            {
                TimelyDayView timelydayview = (TimelyDayView)view;
                if (timelydayview.dayHeaderView.isToday)
                {
                    boolean flag;
                    if (!timelydayview.hasItems && !timelydayview.mDataLoaded)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag && view.getTop() == 0 && agendaScrollOffsetNow != 0)
                    {
                        return false;
                    }
                }
            }
            viewtreeobserver.removeOnPreDrawListener(this);
            return true;
        }

        _cls3()
        {
            this$0 = TimelineRecyclerView.this;
            super();
        }
    }

}
