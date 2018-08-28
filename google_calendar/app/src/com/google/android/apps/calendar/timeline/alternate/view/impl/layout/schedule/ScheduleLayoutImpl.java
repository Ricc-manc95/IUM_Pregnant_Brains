// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutItemParams;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutUpdater;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.base.Supplier;
import com.google.common.collect.Iterators;
import com.google.common.collect.Range;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleLayout, ScheduleDay, ScheduleItem, ScheduleCache, 
//            MonthLabelThresholdEvaluator

public final class ScheduleLayoutImpl extends ScheduleLayout
{

    public int bottomJulianDay;
    private final ObservableReference currentTime;
    private Supplier dayLoadedOffsetUpdater;
    private final int dayTopMarginPx;
    private final LayoutItemParams defaultParams;
    private boolean focusDayHeaderOnNextLayout;
    private Boolean forceAnimateNextLayout;
    public final TimelineHostView hostView;
    private final ObservableReference isA11yEnabled;
    private final ObservableReference isTalkBackEnabled;
    private final int monthBannerTextBottomPx;
    private final LayoutItemParams nowLineParams;
    public int offsetPx;
    private final ScheduleCache scheduleCache;
    private final ObservableReference screenType;
    public final ObservableReference selectedRangeObservable;
    private SettableFuture showFuture;
    private boolean shown;
    private final MonthLabelThresholdEvaluator thresholdEvaluator;
    private final TimeUtils timeUtils;
    private final com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarTitleHelper toolbarTitleHelper;
    public int topJulianDay;
    public final ViewportAnimator viewportAnimator;
    private final ObservableReference viewportObservable;
    private final LayoutItemParams virtualTimedEventsParams;

    public ScheduleLayoutImpl(TimelineHostView timelinehostview, ScheduleCache schedulecache, TimeUtils timeutils, ObservableReference observablereference, ObservableReference observablereference1, ObservableReference observablereference2, ViewportAnimator viewportanimator, 
            LayoutDimens layoutdimens, ObservableReference observablereference3, ObservableReference observablereference4, com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarTitleHelper toolbartitlehelper, ObservableReference observablereference5, MonthLabelThresholdEvaluator monthlabelthresholdevaluator)
    {
        LayoutItemParams layoutitemparams = new LayoutItemParams();
        layoutitemparams.type = com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutItemParams.Type.VIRTUAL_ONLY;
        virtualTimedEventsParams = layoutitemparams;
        layoutitemparams = new LayoutItemParams();
        layoutitemparams.viewMode = ViewMode.SCHEDULE;
        layoutitemparams.type = com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutItemParams.Type.DISPLAY_ONLY;
        nowLineParams = layoutitemparams;
        layoutitemparams = new LayoutItemParams();
        layoutitemparams.viewMode = ViewMode.SCHEDULE;
        defaultParams = layoutitemparams;
        focusDayHeaderOnNextLayout = false;
        hostView = timelinehostview;
        scheduleCache = schedulecache;
        timeUtils = timeutils;
        currentTime = observablereference;
        viewportObservable = observablereference1;
        selectedRangeObservable = observablereference2;
        viewportAnimator = viewportanimator;
        isTalkBackEnabled = observablereference3;
        isA11yEnabled = observablereference4;
        toolbarTitleHelper = toolbartitlehelper;
        screenType = observablereference5;
        thresholdEvaluator = monthlabelthresholdevaluator;
        dayTopMarginPx = layoutdimens.converter.getPixelSize(10F);
        float f1 = layoutdimens.converter.spToPx(21F);
        timelinehostview = layoutdimens.converter;
        float f;
        if (layoutdimens.scheduleProvider.shouldShowMonthImages())
        {
            f = 36F;
        } else
        {
            f = 55F;
        }
        monthBannerTextBottomPx = Math.round(timelinehostview.dpToPx(f) + f1);
    }

    private static int getBottomPxOfFirstView(ScheduleDay scheduleday, CalendarViewType calendarviewtype, int i)
    {
        List list = scheduleday.getLayout();
        class .Lambda._cls6
            implements Predicate
        {

            private final CalendarViewType arg$1;

            public final boolean apply(Object obj)
            {
                return ScheduleLayoutImpl.lambda$getBottomPxOfFirstView$3$ScheduleLayoutImpl(arg$1, (ScheduleItem)obj);
            }

            .Lambda._cls6(CalendarViewType calendarviewtype)
            {
                arg$1 = calendarviewtype;
            }
        }

        calendarviewtype = new .Lambda._cls6(calendarviewtype);
        i = Iterators.indexOf(list.iterator(), calendarviewtype);
        if (i < 0)
        {
            return 0;
        } else
        {
            return -((ScheduleItem)scheduleday.getLayout().get(i)).getBottom();
        }
    }

    private final int getTodayOffsetPx()
    {
        long l = timeUtils.julianDateToMs(topJulianDay);
        ScheduleDay scheduleday = scheduleCache.getDay(timeUtils.msToJulianDate(l));
        int i = getBottomPxOfFirstView(scheduleday, CalendarViewType.WEEK_BANNER, 0);
        if (i != 0)
        {
            i = dayTopMarginPx + i;
        } else
        {
            int j = getBottomPxOfFirstView(scheduleday, CalendarViewType.MONTH_BANNER, 0);
            i = j;
            if (j == 0)
            {
                return dayTopMarginPx;
            }
        }
        return i;
    }

    static final boolean lambda$getBottomPxOfFirstView$3$ScheduleLayoutImpl(CalendarViewType calendarviewtype, ScheduleItem scheduleitem)
    {
        return CalendarViewType.forPosition(scheduleitem.getPosition()) == calendarviewtype;
    }

    static final boolean lambda$getNowOffsetPx$2$ScheduleLayoutImpl(ScheduleItem scheduleitem)
    {
        CalendarViewType calendarviewtype = CalendarViewType.NOW_LINE;
        int i = scheduleitem.getPosition();
        return i >= calendarviewtype.minPosition && i <= calendarviewtype.maxPosition;
    }

    static final boolean lambda$getTimeOffsetPx$4$ScheduleLayoutImpl(long l, ScheduleItem scheduleitem)
    {
        return scheduleitem.getEndTimeMs() != null && scheduleitem.getEndTimeMs().longValue() > l;
    }

    static final boolean lambda$isFirstDayOfTheMonth$7$ScheduleLayoutImpl(ScheduleItem scheduleitem)
    {
        CalendarViewType calendarviewtype = CalendarViewType.MONTH_BANNER;
        int i = scheduleitem.getPosition();
        return i >= calendarviewtype.minPosition && i <= calendarviewtype.maxPosition;
    }

    static final Integer lambda$scroll$6$ScheduleLayoutImpl$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T4MST35CTIN4EO_0()
    {
        return null;
    }

    private final FluentFuture showItems(LayoutUpdater layoutupdater, boolean flag)
    {
        int i = topJulianDay;
        if (dayLoadedOffsetUpdater != null)
        {
            long l2 = timeUtils.julianDateToMs(topJulianDay);
            if (scheduleCache.getDay(timeUtils.msToJulianDate(l2)).getLoaded())
            {
                offsetPx = ((Integer)dayLoadedOffsetUpdater.get()).intValue();
                dayLoadedOffsetUpdater = null;
            }
        }
        ScheduleDay scheduleday;
        for (; offsetPx > dayTopMarginPx; offsetPx = offsetPx - scheduleday.getHeightPx())
        {
            i--;
            scheduleday = scheduleCache.getDay(i);
        }

        do
        {
            ScheduleDay scheduleday1 = scheduleCache.getDay(i);
            if (-offsetPx <= scheduleday1.getHeightPx())
            {
                break;
            }
            int j = offsetPx;
            offsetPx = scheduleday1.getHeightPx() + j;
            i++;
        } while (true);
        int k = offsetPx;
        boolean flag3 = true;
        final int scrollBackwardPx = i;
        while (k < hostView.getMeasuredHeight()) 
        {
            ScheduleCache schedulecache = scheduleCache;
            int i1 = scrollBackwardPx + 1;
            ScheduleDay scheduleday2 = schedulecache.getDay(scrollBackwardPx);
            if (!scheduleday2.getLoaded())
            {
                flag3 = false;
            }
            int j1 = CalendarViewType.VIRTUAL_TIMED_EVENTS.minPosition + i1;
            int k1 = scheduleday2.getLayout().size();
            int l = 0;
            while (l < k1) 
            {
                final ScheduleItem item = (ScheduleItem)scheduleday2.getLayout().get(l);
                CalendarViewType calendarviewtype = CalendarViewType.forPosition(item.getPosition());
                LayoutItemParams layoutitemparams;
                boolean flag2;
                int l1;
                if (calendarviewtype == CalendarViewType.DAY_HEADER)
                {
                    layoutitemparams = defaultParams;
                    layoutitemparams.hasParentId = false;
                    scrollBackwardPx = Math.min(Math.max(0, -(item.getTop() + k) + dayTopMarginPx), scheduleday2.getHeightPx() - item.getBottom());
                } else
                if (calendarviewtype == CalendarViewType.NOW_LINE)
                {
                    layoutitemparams = nowLineParams;
                    layoutitemparams.parentId = j1;
                    layoutitemparams.hasParentId = true;
                    scrollBackwardPx = 0;
                } else
                {
                    layoutitemparams = defaultParams;
                    layoutitemparams.parentId = j1;
                    layoutitemparams.hasParentId = true;
                    scrollBackwardPx = 0;
                }
                l1 = k + scrollBackwardPx;
                if (((Boolean)isTalkBackEnabled.get()).booleanValue() && l == k1 - 1 && calendarviewtype != CalendarViewType.DAY_HEADER)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if ((flag2 || item.getBottom() + l1 >= 0) && item.getTop() + l1 <= hostView.getMeasuredHeight())
                {
                    if (((Boolean)isTalkBackEnabled.get()).booleanValue())
                    {
                        if (flag2)
                        {
                            layoutitemparams.setVirtualRect(item.getLeft(), item.getTop() + l1, item.getRight(), scheduleday2.getHeightPx() + l1);
                        } else
                        {
                            layoutitemparams.hasVirtualRect = false;
                        }
                        layoutitemparams.virtualActionHandler = new _cls2();
                    }
                    layoutitemparams.animate = flag;
                    layoutitemparams.position = item.getPosition();
                    layoutupdater.addItem(layoutitemparams.setRect(item.getLeft(), item.getTop() + l1, item.getRight(), item.getBottom() + l1));
                }
                l++;
            }
            if (((Boolean)isA11yEnabled.get()).booleanValue() && scheduleday2.getHeightPx() != 0)
            {
                LayoutItemParams layoutitemparams1;
                final int scrollForwardPx;
                boolean flag4;
                if (k < 0)
                {
                    scrollBackwardPx = -k;
                } else
                {
                    scrollBackwardPx = 0;
                }
                scrollForwardPx = scheduleday2.getHeightPx() + k;
                if (scrollForwardPx > hostView.getMeasuredHeight())
                {
                    scrollForwardPx = -Math.min(hostView.getMeasuredHeight(), scrollForwardPx - hostView.getMeasuredHeight());
                } else
                {
                    scrollForwardPx = 0;
                }
                layoutitemparams1 = virtualTimedEventsParams;
                layoutitemparams1.virtualActionHandler = new _cls1();
                if (scrollForwardPx != 0)
                {
                    flag4 = true;
                } else
                {
                    flag4 = false;
                }
                layoutitemparams1.canScrollForward = flag4;
                if (scrollBackwardPx != 0)
                {
                    flag4 = true;
                } else
                {
                    flag4 = false;
                }
                layoutitemparams1.canScrollBackward = flag4;
                layoutitemparams1 = virtualTimedEventsParams;
                layoutitemparams1.position = j1;
                layoutupdater.addItem(layoutitemparams1.setRect(0, k, hostView.getMeasuredWidth(), scheduleday2.getHeightPx() + k));
            }
            k = scheduleday2.getHeightPx() + k;
            scrollBackwardPx = i1;
        }
        if (i != topJulianDay || scrollBackwardPx != bottomJulianDay)
        {
            topJulianDay = i;
            bottomJulianDay = scrollBackwardPx;
            viewportObservable.set(Range.closed(Integer.valueOf(topJulianDay), Integer.valueOf(bottomJulianDay)));
            boolean flag1;
            if ((ScreenType)screenType.get() == ScreenType.PHONE)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                selectedRangeObservable.set(Range.closed(Integer.valueOf(topJulianDay), Integer.valueOf(bottomJulianDay)));
            }
        }
        return layoutupdater.finish(flag, flag3);
    }

    public final boolean canScrollVertically()
    {
        return true;
    }

    final int getNowOffsetPx()
    {
        ScheduleDay scheduleday;
        boolean flag1;
        boolean flag2;
        int k;
        flag2 = true;
        flag1 = false;
        long l = timeUtils.julianDateToMs(topJulianDay);
        scheduleday = scheduleCache.getDay(timeUtils.msToJulianDate(l));
        List list = scheduleday.getLayout();
        class .Lambda._cls4
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls4();

            public final boolean apply(Object obj)
            {
                return ScheduleLayoutImpl.lambda$getNowOffsetPx$2$ScheduleLayoutImpl((ScheduleItem)obj);
            }


            private .Lambda._cls4()
            {
            }
        }

        Predicate predicate = .Lambda._cls4..instance;
        k = Iterators.indexOf(list.iterator(), predicate);
        if (k < 0) goto _L2; else goto _L1
_L1:
        boolean flag;
        List list1 = scheduleday.getLayout();
        class .Lambda._cls5
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls5();

            public final boolean apply(Object obj)
            {
                return ((ScheduleItem)obj).isTimedItem();
            }


            private .Lambda._cls5()
            {
            }
        }

        Predicate predicate1 = .Lambda._cls5..instance;
        if (Iterators.indexOf(list1.iterator(), predicate1) != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L3
_L3:
        flag = flag2;
_L5:
        if (flag)
        {
            return getTodayOffsetPx();
        }
        break; /* Loop/switch isn't completed */
_L2:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        int j = dayTopMarginPx;
        int i;
        if (k <= 0)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = -((ScheduleItem)scheduleday.getLayout().get(k - 1)).getTop();
        }
        return Math.min(i + j, getTodayOffsetPx());
    }

    final int getTimeOffsetPx(long l)
    {
        ScheduleDay scheduleday = scheduleCache.getDay(timeUtils.msToJulianDate(l));
        List list = scheduleday.getLayout();
        class .Lambda._cls7
            implements Predicate
        {

            private final long arg$1;

            public final boolean apply(Object obj)
            {
                return ScheduleLayoutImpl.lambda$getTimeOffsetPx$4$ScheduleLayoutImpl(arg$1, (ScheduleItem)obj);
            }

            .Lambda._cls7(long l)
            {
                arg$1 = l;
            }
        }

        .Lambda._cls7 _lcls7 = new .Lambda._cls7(l);
        int i = Iterators.indexOf(list.iterator(), _lcls7);
        int j = dayTopMarginPx;
        if (i == -1)
        {
            i = 0;
        } else
        {
            i = -((ScheduleItem)scheduleday.getLayout().get(i)).getTop();
        }
        return i + j;
    }

    public final ViewMode getViewMode()
    {
        return ViewMode.SCHEDULE;
    }

    public final void goToDay(int i)
    {
        scheduleCache.setSelectedDay(Integer.valueOf(i));
        topJulianDay = i;
        offsetPx = getTodayOffsetPx();
        dayLoadedOffsetUpdater = null;
        hostView.requestLayout();
        selectedRangeObservable.set(Range.singleton(Integer.valueOf(topJulianDay)));
    }

    public final void goToNow(boolean flag)
    {
        topJulianDay = timeUtils.msToJulianDate(((Long)currentTime.get()).longValue());
        offsetPx = getNowOffsetPx();
        class .Lambda._cls2
            implements Supplier
        {

            private final ScheduleLayoutImpl arg$1;

            public final Object get()
            {
                return Integer.valueOf(arg$1.getNowOffsetPx());
            }

            .Lambda._cls2()
            {
                arg$1 = ScheduleLayoutImpl.this;
            }
        }

        dayLoadedOffsetUpdater = new .Lambda._cls2();
        hostView.requestLayout();
        selectedRangeObservable.set(Range.singleton(Integer.valueOf(topJulianDay)));
    }

    public final void goToTime(long l)
    {
        topJulianDay = timeUtils.msToJulianDate(l);
        offsetPx = getTimeOffsetPx(l);
        class .Lambda._cls1
            implements Supplier
        {

            private final ScheduleLayoutImpl arg$1;
            private final long arg$2;

            public final Object get()
            {
                return Integer.valueOf(arg$1.getTimeOffsetPx(arg$2));
            }

            .Lambda._cls1(long l)
            {
                arg$1 = ScheduleLayoutImpl.this;
                arg$2 = l;
            }
        }

        dayLoadedOffsetUpdater = new .Lambda._cls1(l);
        hostView.requestLayout();
        selectedRangeObservable.set(Range.singleton(Integer.valueOf(topJulianDay)));
    }

    public final void invalidateCache()
    {
        scheduleCache.cache.clear();
    }

    public final void onHide()
    {
        if (!shown)
        {
            throw new IllegalStateException();
        }
        shown = false;
        topJulianDay = 0;
        bottomJulianDay = 0;
        dayLoadedOffsetUpdater = null;
        if (showFuture != null)
        {
            showFuture.cancel(true);
            showFuture = null;
        }
    }

    public final void onLayoutChildren(LayoutUpdater layoutupdater, boolean flag)
    {
        SettableFuture settablefuture = showFuture;
        showFuture = null;
        if (forceAnimateNextLayout != null)
        {
            flag = forceAnimateNextLayout.booleanValue();
        } else
        if (flag || settablefuture != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        forceAnimateNextLayout = null;
        layoutupdater = showItems(layoutupdater, flag);
        if (settablefuture != null)
        {
            settablefuture.set(layoutupdater);
        }
        if (focusDayHeaderOnNextLayout)
        {
            focusDayHeaderOnNextLayout = false;
            class .Lambda._cls3
                implements Supplier
            {

                private final ScheduleLayoutImpl arg$1;

                public final Object get()
                {
                    return Integer.valueOf(arg$1.topJulianDay + CalendarViewType.DAY_HEADER.minPosition + 100);
                }

            .Lambda._cls3()
            {
                arg$1 = ScheduleLayoutImpl.this;
            }
            }

            hostView.requestFocusAfterLayout(settablefuture, new .Lambda._cls3());
        }
    }

    public final FluentFuture onShow(long l, boolean flag)
    {
        boolean flag2 = false;
        boolean flag1;
        if (!shown)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalStateException();
        }
        flag1 = flag2;
        if (showFuture == null)
        {
            flag1 = true;
        }
        if (!flag1)
        {
            throw new IllegalStateException();
        }
        shown = true;
        topJulianDay = timeUtils.msToJulianDate(l);
        offsetPx = getNowOffsetPx();
        class .Lambda._cls0
            implements Supplier
        {

            private final ScheduleLayoutImpl arg$1;

            public final Object get()
            {
                return Integer.valueOf(arg$1.getNowOffsetPx());
            }

            .Lambda._cls0()
            {
                arg$1 = ScheduleLayoutImpl.this;
            }
        }

        dayLoadedOffsetUpdater = new .Lambda._cls0();
        scheduleCache.setSelectedDay(Integer.valueOf(topJulianDay));
        selectedRangeObservable.set(Range.singleton(Integer.valueOf(topJulianDay)));
        showFuture = new SettableFuture();
        forceAnimateNextLayout = Boolean.valueOf(flag);
        focusDayHeaderOnNextLayout = true;
        SettableFuture settablefuture = showFuture;
        if (settablefuture instanceof FluentFuture)
        {
            return (FluentFuture)settablefuture;
        } else
        {
            return new ForwardingFluentFuture(settablefuture);
        }
    }

    public final Optional scroll(boolean flag)
    {
        dayLoadedOffsetUpdater = null;
        int i = hostView.getMeasuredHeight();
        class .Lambda._cls9
            implements Function
        {

            public static final Function $instance = new .Lambda._cls9();

            public final Object apply(Object obj)
            {
                return ScheduleLayoutImpl.lambda$scroll$6$ScheduleLayoutImpl$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T4MST35CTIN4EO_0();
            }


            private .Lambda._cls9()
            {
            }
        }

        FluentFuture fluentfuture;
        byte byte0;
        if (flag)
        {
            byte0 = -1;
        } else
        {
            byte0 = 1;
        }
        fluentfuture = (FluentFuture)AbstractTransformFuture.create(viewportAnimator.animateViewportChange(new _cls3()), .Lambda._cls9..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        if (fluentfuture == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Present(fluentfuture);
        }
    }

    public final int scrollVerticallyBy(int i, LayoutUpdater layoutupdater)
    {
        int j;
        int k;
        boolean flag = true;
        dayLoadedOffsetUpdater = null;
        offsetPx = offsetPx - i;
        showItems(layoutupdater, false);
        class .Lambda._cls10
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls10();

            public final boolean apply(Object obj2)
            {
                return ScheduleLayoutImpl.lambda$isFirstDayOfTheMonth$7$ScheduleLayoutImpl((ScheduleItem)obj2);
            }


            private .Lambda._cls10()
            {
            }
        }

        class .Lambda._cls11
            implements Consumer
        {

            private final ScheduleLayoutImpl arg$1;

            public final void accept(Object obj2)
            {
                ScheduleLayoutImpl schedulelayoutimpl = arg$1;
                int l = ((Integer)obj2).intValue();
                if (l != ((Integer)((Range)schedulelayoutimpl.selectedRangeObservable.get()).lowerBound.endpoint()).intValue())
                {
                    schedulelayoutimpl.selectedRangeObservable.set(Range.closed(Integer.valueOf(l), Integer.valueOf(Math.max(l, schedulelayoutimpl.bottomJulianDay))));
                }
            }

            .Lambda._cls11()
            {
                arg$1 = ScheduleLayoutImpl.this;
            }
        }

        Object obj;
        Object obj1;
        if ((ScreenType)screenType.get() == ScreenType.PHONE)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L2; else goto _L1
_L1:
        layoutupdater = new AutoValue_MonthLabelThresholdEvaluator_State.Builder();
        j = topJulianDay;
        obj = scheduleCache.getDay(j).getLayout();
        obj1 = .Lambda._cls10..instance;
        boolean flag1;
        if (Iterators.indexOf(((Iterable) (obj)).iterator(), ((Predicate) (obj1))) != -1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        layoutupdater = layoutupdater.setFirstDayOfMonth(flag1).setCurrentOffsetPx(-offsetPx).setCurrentScrollDeltaYPx(i).setSwitchPointOffsetPx(monthBannerTextBottomPx).build();
        obj = toolbarTitleHelper;
        obj1 = thresholdEvaluator;
        k = topJulianDay;
        if (com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarAnimation.NONE.equals(((MonthLabelThresholdEvaluator) (obj1)).getAnimation(layoutupdater))) goto _L4; else goto _L3
_L3:
        if (layoutupdater.getCurrentScrollDeltaYPx() > 0)
        {
            j = ((flag) ? 1 : 0);
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            j = k;
        } else
        {
            j = k - 1;
        }
_L6:
        ((com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarTitleHelper) (obj)).onVerticalScroll(j, thresholdEvaluator.getAnimation(layoutupdater), new .Lambda._cls11());
_L2:
        return i;
_L4:
        j = k;
        if (layoutupdater.isFirstDayOfMonth())
        {
            j = k;
            if (layoutupdater.getCurrentOffsetPx() <= layoutupdater.getSwitchPointOffsetPx())
            {
                j = k - 1;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    private class _cls2
        implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.AccessibilityVirtualView.ActionHandler
    {

        private final ScheduleLayoutImpl this$0;
        private final ScheduleItem val$item;
        private final int val$offset;

        public final boolean focus()
        {
            return false;
        }

        public final Optional scroll(boolean flag, Integer integer)
        {
            return Absent.INSTANCE;
        }

        public final boolean showOnScreen()
        {
            final int deltaPx = item.getTop() + offset;
            if (deltaPx < 0)
            {
                ScheduleLayoutImpl schedulelayoutimpl = ScheduleLayoutImpl.this;
                deltaPx = -deltaPx;
                schedulelayoutimpl.viewportAnimator.animateViewportChange(schedulelayoutimpl. new _cls3());
                return true;
            }
            deltaPx = item.getBottom() + offset;
            if (deltaPx > hostView.getMeasuredHeight())
            {
                ScheduleLayoutImpl schedulelayoutimpl1 = ScheduleLayoutImpl.this;
                int i = hostView.getMeasuredHeight();
                schedulelayoutimpl1.viewportAnimator.animateViewportChange(schedulelayoutimpl1. new _cls3());
                return true;
            } else
            {
                return false;
            }
        }

        _cls2()
        {
            this$0 = ScheduleLayoutImpl.this;
            item = scheduleitem;
            offset = i;
            super();
        }
    }


    private class _cls1
        implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.AccessibilityVirtualView.ActionHandler
    {

        private final ScheduleLayoutImpl this$0;
        private final int val$scrollBackwardPx;
        private final int val$scrollForwardPx;

        public final boolean focus()
        {
            return false;
        }

        public final Optional scroll(boolean flag, Integer integer)
        {
            final int deltaPx;
            if (flag)
            {
                deltaPx = scrollForwardPx;
            } else
            {
                deltaPx = scrollBackwardPx;
            }
            if (deltaPx != 0)
            {
                integer = ScheduleLayoutImpl.this;
                class .Lambda._cls0
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls0();

                    public final Object apply(Object obj)
                    {
                        return null;
                    }


                private .Lambda._cls0()
                {
                }
                }

                integer = (FluentFuture)AbstractTransformFuture.create(((ScheduleLayoutImpl) (integer)).viewportAnimator.animateViewportChange(integer. new _cls3()), .Lambda._cls0..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                if (integer == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return new Present(integer);
                }
            } else
            {
                return Absent.INSTANCE;
            }
        }

        public final boolean showOnScreen()
        {
            return false;
        }

        _cls1()
        {
            this$0 = ScheduleLayoutImpl.this;
            scrollForwardPx = i;
            scrollBackwardPx = j;
            super();
        }
    }


    private class _cls3
        implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator.ChangeAnimationListener
    {

        private int consumed;
        private final ScheduleLayoutImpl this$0;
        private final int val$deltaPx;

        public final void onStep(float f)
        {
            int i = (int)((float)deltaPx * f - (float)consumed);
            consumed = consumed + i;
            ScheduleLayoutImpl schedulelayoutimpl = ScheduleLayoutImpl.this;
            schedulelayoutimpl.offsetPx = i + schedulelayoutimpl.offsetPx;
            hostView.requestLayout();
        }

        _cls3()
        {
            this$0 = ScheduleLayoutImpl.this;
            deltaPx = i;
            super();
            consumed = 0;
        }
    }

}
