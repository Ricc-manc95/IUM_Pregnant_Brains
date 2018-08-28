// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import android.graphics.Rect;
import com.google.android.apps.calendar.timeline.alternate.util.AutoValue_YearMonth;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterMonth;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterMonthDay;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutItemParams;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutUpdater;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.YearMonthHelper;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthLayout, MonthViewport, MonthViewportController

public final class MonthLayoutImpl extends MonthLayout
{

    public final TimelineAdapter adapter;
    private final int chipHeight;
    private final int chipHorizontalPadding;
    private final LayoutItemParams chipParams;
    private final com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect chipRect = new com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect();
    private final int chipVerticalPadding;
    private final ObservableReference currentTime;
    private final int dayMinHeight;
    private final com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect dayRect = new com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect();
    private int dayWidth;
    private final ObservableReference eventsPerDay;
    private Boolean forceAnimateNextLayout;
    private int heightPerChipRow;
    private float heightPerWeek;
    private final TimelineHostView hostView;
    private final ObservableReference isRtl;
    private final ItemAdapter itemAdapter;
    private final int minFlingVelocity;
    private final LayoutItemParams monthDayHeaderParams;
    private final Rect projected = new Rect();
    private SettableFuture showFuture;
    public final TimeUtils timeUtils;
    private int topOffset;
    private int viewHeightPx;
    private int viewWidthPx;
    public final MonthViewport viewport;
    private final MonthViewportController viewportController;
    private final VisibleRangeUpdater visibleRangeUpdater;
    private int weekHeight;
    private float widthPerDay;
    public final YearMonthHelper yearMonthHelper;

    public MonthLayoutImpl(TimelineHostView timelinehostview, TimelineAdapter timelineadapter, MonthViewport monthviewport, MonthViewportController monthviewportcontroller, ObservableReference observablereference, ObservableReference observablereference1, ObservableReference observablereference2, 
            ItemAdapter itemadapter, YearMonthHelper yearmonthhelper, TimeUtils timeutils, LayoutDimens layoutdimens, DimensConverter dimensconverter, ObservableReference observablereference3, ObservableReference observablereference4, 
            LayoutDimens layoutdimens1, ObservableReference observablereference5)
    {
        LayoutItemParams layoutitemparams = new LayoutItemParams();
        layoutitemparams.viewMode = ViewMode.MONTH;
        monthDayHeaderParams = layoutitemparams;
        layoutitemparams = new LayoutItemParams();
        layoutitemparams.viewMode = ViewMode.MONTH;
        layoutitemparams.type = com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutItemParams.Type.DISPLAY_ONLY;
        chipParams = layoutitemparams;
        hostView = timelinehostview;
        adapter = timelineadapter;
        viewport = monthviewport;
        viewportController = monthviewportcontroller;
        eventsPerDay = observablereference2;
        itemAdapter = itemadapter;
        yearMonthHelper = yearmonthhelper;
        isRtl = observablereference3;
        timeUtils = timeutils;
        currentTime = observablereference5;
        class .Lambda._cls0
            implements Consumer
        {

            private final TimelineHostView arg$1;

            public final void accept(Object obj)
            {
                MonthLayoutImpl.lambda$new$0$MonthLayoutImpl$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBQKD5MMAR39DPIKGRRJEHB6IPBN7D666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBRCC5SMUTBK5TMMURJKD0NKQRREEHK5CQB5ETO6USJK7CKLC___0(arg$1);
            }

            .Lambda._cls0(TimelineHostView timelinehostview)
            {
                arg$1 = timelinehostview;
            }
        }

        float f;
        boolean flag;
        if ((ScreenType)observablereference4.get() == ScreenType.PHONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            f = 22F;
        } else
        {
            f = 14F;
        }
        chipHeight = dimensconverter.getPixelSize(f);
        if ((ScreenType)observablereference4.get() == ScreenType.PHONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            f = 4F;
        } else
        {
            f = 2.0F;
        }
        chipHorizontalPadding = dimensconverter.getPixelOffset(f);
        chipVerticalPadding = layoutdimens.converter.getPixelOffset(2.0F);
        dayMinHeight = chipHeight;
        minFlingVelocity = Math.round(layoutdimens1.converter.dpToPx(400F));
        visibleRangeUpdater = new VisibleRangeUpdater(observablereference, observablereference1);
        monthviewport.changeObservable.subscribe(new .Lambda._cls0(timelinehostview), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE, false);
    }

    private final void animateTo(int i)
    {
        Object obj = yearMonthHelper;
        obj = ((YearMonthHelper) (obj)).createForMs(((YearMonthHelper) (obj)).timeUtils.julianDateToMs(i));
        int j = ((YearMonth) (obj)).getYear();
        float f = (float)((double)(((YearMonth) (obj)).getMonth() + j * 12) - viewport.startFraction);
        if (Math.abs(f) <= 3F)
        {
            viewportController.animateDelta(f);
            return;
        } else
        {
            MonthViewport monthviewport = viewport;
            Object obj1 = monthviewport.yearMonthHelper;
            obj1 = ((YearMonthHelper) (obj1)).createForMs(((YearMonthHelper) (obj1)).timeUtils.julianDateToMs(i));
            i = ((YearMonth) (obj1)).getYear();
            double d = ((YearMonth) (obj1)).getMonth() + i * 12;
            monthviewport.startFraction = d;
            i = (int)Math.floor(d);
            monthviewport.start = new AutoValue_YearMonth(i / 12, i % 12);
            i = (int)Math.ceil(d);
            monthviewport.end = new AutoValue_YearMonth(i / 12, i % 12);
            monthviewport.changeObservable.set(monthviewport);
            forceAnimateNextLayout = Boolean.valueOf(true);
            return;
        }
    }

    private final boolean hasObviousOverflow(int i, ImmutableList immutablelist)
    {
        return ((AdapterMonthDay)immutablelist.get(i)).getNumRows() > ((Integer)eventsPerDay.get()).intValue();
    }

    static final void lambda$new$0$MonthLayoutImpl$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBQKD5MMAR39DPIKGRRJEHB6IPBN7D666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBRCC5SMUTBK5TMMURJKD0NKQRREEHK5CQB5ETO6USJK7CKLC___0(TimelineHostView timelinehostview)
    {
        timelinehostview.requestLayout();
    }

    private final FluentFuture layoutItems(LayoutUpdater layoutupdater, boolean flag)
    {
        monthDayHeaderParams.animate = flag;
        chipParams.animate = flag;
        viewWidthPx = hostView.getMeasuredWidth();
        viewHeightPx = hostView.getMeasuredHeight();
        Object obj = viewport;
        int i = viewWidthPx;
        int j = viewHeightPx;
        obj.width = i;
        obj.height = j;
        viewport.projectYearMonth(viewport.start, projected);
        topOffset = projected.top;
        heightPerWeek = (float)projected.height() / 6F;
        weekHeight = (int)heightPerWeek;
        heightPerChipRow = chipHeight + chipVerticalPadding;
        widthPerDay = (float)projected.width() / 7F;
        dayWidth = (int)widthPerDay;
        i = (int)((heightPerWeek - (float)dayMinHeight) / (float)(chipHeight + chipVerticalPadding));
        if (((Integer)eventsPerDay.get()).intValue() != i)
        {
            eventsPerDay.set(Integer.valueOf(i));
        }
        obj = viewport.start;
        i = ((YearMonth) (obj)).getYear();
        j = ((YearMonth) (obj)).getMonth();
        obj = viewport.end;
        int k2 = ((YearMonth) (obj)).getYear();
        int l2 = ((YearMonth) (obj)).getMonth();
        int l = j + i * 12;
        boolean flag1 = true;
        while (l <= k2 * 12 + l2) 
        {
            obj = new AutoValue_YearMonth(l / 12, l % 12);
            i = 1;
            int i3 = yearMonthHelper.getFirstVisibleJulianDay(((YearMonth) (obj)));
            viewport.projectYearMonth(((YearMonth) (obj)), projected);
            if (projected.isEmpty())
            {
                i = 1;
            } else
            {
                obj = adapter.getMonth(((YearMonth) (obj))).getDays();
                int i1 = 0;
                while (i1 < 6) 
                {
                    j = 1;
                    int j3 = i3 + i1 * 7;
                    int j1;
                    int k1;
                    if (((Boolean)isRtl.get()).booleanValue())
                    {
                        j1 = viewWidthPx - projected.right;
                    } else
                    {
                        j1 = projected.left;
                    }
                    for (k1 = 0; k1 < 7; k1++)
                    {
                        int l1 = j1 + Math.round((float)k1 * widthPerDay);
                        if (((Boolean)isRtl.get()).booleanValue())
                        {
                            l1 = viewWidthPx - l1 - dayWidth;
                        }
                        int i2 = topOffset + Math.round((float)i1 * heightPerWeek);
                        Object obj1 = dayRect;
                        int k3 = dayWidth;
                        int l3 = weekHeight;
                        obj1.left = l1;
                        obj1.top = i2;
                        obj1.right = k3 + l1;
                        obj1.bottom = l3 + i2;
                        k3 = i1 * 7 + k1;
                        obj1 = (AdapterMonthDay)((ImmutableList) (obj)).get(k3);
                        if (!((AdapterMonthDay) (obj1)).getLoaded())
                        {
                            j = 0;
                        }
                        if (dayRect.right >= 0 && dayRect.left <= viewWidthPx)
                        {
                            LayoutItemParams layoutitemparams = monthDayHeaderParams;
                            layoutitemparams.position = ((AdapterMonthDay) (obj1)).getMonthDayHeaderPosition();
                            com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect rect = dayRect;
                            layoutitemparams.rect.set(rect);
                            layoutupdater.addItem(layoutitemparams);
                        }
                        obj1 = (ImmutableList)((AdapterMonthDay) (obj1)).getEvents();
                        l3 = ((ImmutableList) (obj1)).size();
                        l1 = 0;
                        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
label0:
                        while (l1 < l3) 
                        {
                            Object obj2 = (AdapterEvent)((ImmutableList) (obj1)).get(l1);
                            int i4 = ((AdapterEvent) (obj2)).getMonthSlot();
                            boolean flag2 = hasObviousOverflow(k3, ((ImmutableList) (obj)));
                            int l4 = ((Integer)eventsPerDay.get()).intValue();
                            int j2;
                            int i5;
                            if (flag2)
                            {
                                j2 = 1;
                            } else
                            {
                                j2 = 0;
                            }
                            i5 = l4 - j2;
                            if (i4 >= i5)
                            {
                                continue;
                            }
                            l4 = Math.max(itemAdapter.getStartDay(((AdapterEvent) (obj2)).getItem()), j3);
                            if (j3 + k1 != l4)
                            {
                                continue;
                            }
                            if (((AdapterEvent) (obj2)).getIsTimedEvent())
                            {
                                j2 = itemAdapter.getStartDay(((AdapterEvent) (obj2)).getItem());
                            } else
                            {
                                j2 = itemAdapter.getEndDay(((AdapterEvent) (obj2)).getItem());
                            }
                            l4 = (Math.min(j2, (j3 + 7) - 1) - l4) + 1;
                            if (!flag2 && i4 == i5 - 1)
                            {
                                for (j2 = 1; j2 < l4; j2++)
                                {
                                    if (hasObviousOverflow(k3 + j2, ((ImmutableList) (obj))))
                                    {
                                        continue label0;
                                    }
                                }

                            }
                            j2 = ((Integer)eventsPerDay.get()).intValue();
                            i5 = dayRect.bottom;
                            int l5 = heightPerChipRow;
                            j2 = chipVerticalPadding + (i5 - (j2 - i4) * l5);
                            if (((Boolean)isRtl.get()).booleanValue())
                            {
                                com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect rect1 = chipRect;
                                int j4 = dayRect.right;
                                l4 = Math.round((float)l4 * widthPerDay);
                                int j5 = chipHorizontalPadding;
                                int i6 = dayRect.right;
                                int k6 = chipHorizontalPadding;
                                int i7 = chipHeight;
                                rect1.left = (j4 - l4) + j5;
                                rect1.top = j2;
                                rect1.right = i6 - k6;
                                rect1.bottom = i7 + j2;
                            } else
                            {
                                com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect rect2 = chipRect;
                                int k4 = dayRect.left;
                                int k5 = chipHorizontalPadding;
                                int j6 = dayRect.left;
                                l4 = Math.round((float)l4 * widthPerDay);
                                int l6 = chipHorizontalPadding;
                                int j7 = chipHeight;
                                rect2.left = k4 + k5;
                                rect2.top = j2;
                                rect2.right = (j6 + l4) - l6;
                                rect2.bottom = j7 + j2;
                            }
                            if (chipRect.right >= 0 && chipRect.left <= viewWidthPx)
                            {
                                LayoutItemParams layoutitemparams1 = chipParams;
                                layoutitemparams1.position = ((AdapterEvent) (obj2)).getPosition();
                                obj2 = chipRect;
                                layoutitemparams1.rect.set(((com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect) (obj2)));
                                layoutupdater.addItem(layoutitemparams1);
                            }
                            l1++;
                        }
                    }

                    i &= j;
                    i1++;
                }
            }
            l++;
            flag1 &= i;
        }
        obj = visibleRangeUpdater;
        YearMonth yearmonth = ((VisibleRangeUpdater) (obj))._fld0.viewport.start;
        i = yearmonth.getYear();
        j = yearmonth.getMonth() + i * 12;
        yearmonth = ((VisibleRangeUpdater) (obj))._fld0.viewport.end;
        i = yearmonth.getYear();
        l = yearmonth.getMonth() + i * 12;
        i = (int)Math.round(((VisibleRangeUpdater) (obj))._fld0.viewport.startFraction);
        if (((VisibleRangeUpdater) (obj)).lastStart != j || ((VisibleRangeUpdater) (obj)).lastEnd != l)
        {
            obj.lastStart = j;
            obj.lastEnd = l;
            ObservableReference observablereference = ((VisibleRangeUpdater) (obj)).viewportObservable;
            MonthViewport monthviewport = ((VisibleRangeUpdater) (obj))._fld0.viewport;
            j = monthviewport.yearMonthHelper.getFirstVisibleJulianDay(monthviewport.start);
            monthviewport = ((VisibleRangeUpdater) (obj))._fld0.viewport;
            YearMonthHelper yearmonthhelper1 = monthviewport.yearMonthHelper;
            observablereference.set(Range.closed(Integer.valueOf(j), Integer.valueOf((yearmonthhelper1.getFirstVisibleJulianDay(monthviewport.end) + yearmonthhelper1.weeksInMonth * 7) - 1)));
        }
        if (i != ((VisibleRangeUpdater) (obj)).lastMain)
        {
            obj.lastMain = i;
            int k = ((VisibleRangeUpdater) (obj))._fld0.timeUtils.msToJulianDate(((VisibleRangeUpdater) (obj))._fld0.yearMonthHelper.getStartMonthMs(new AutoValue_YearMonth(i / 12, i % 12)));
            TimeUtils timeutils = ((VisibleRangeUpdater) (obj))._fld0.timeUtils;
            YearMonthHelper yearmonthhelper = ((VisibleRangeUpdater) (obj))._fld0.yearMonthHelper;
            i++;
            i = timeutils.msToJulianDate(yearmonthhelper.getStartMonthMs(new AutoValue_YearMonth(i / 12, i % 12)));
            ((VisibleRangeUpdater) (obj)).selectedRangeObservable.set(Range.closed(Integer.valueOf(k), Integer.valueOf(i - 1)));
        }
        return layoutupdater.finish(flag, flag1);
    }

    public final boolean canScrollHorizontally()
    {
        return true;
    }

    public final boolean fling(int i, int j)
    {
        if (Math.abs(i) > Math.abs(j) && Math.abs(i) > minFlingVelocity)
        {
            hostView.stopScroll();
            Object obj = viewportController;
            Optional optional;
            int k;
            if (i < 0)
            {
                j = -1;
            } else
            {
                j = 1;
            }
            k = (int)(((MonthViewportController) (obj)).viewport.projectSnapDistance(j) * (float)((MonthViewportController) (obj)).viewport.width);
            optional = ((MonthViewportController) (obj)).viewportAnimator.animateFling(i, k, new MonthViewportController..Lambda._cls2(((MonthViewportController) (obj))));
            if (optional.isPresent())
            {
                obj = (FluentFuture)AbstractTransformFuture.create((FluentFuture)optional.get(), new MonthViewportController..Lambda._cls3(((MonthViewportController) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                return true;
            } else
            {
                ((MonthViewportController) (obj)).animateDelta(((MonthViewportController) (obj)).viewport.projectSnapDistance(j));
                return true;
            }
        } else
        {
            return false;
        }
    }

    public final ViewMode getViewMode()
    {
        return ViewMode.MONTH;
    }

    public final void goToDay(int i)
    {
        animateTo(i);
    }

    public final void goToNow(boolean flag)
    {
        int i = timeUtils.msToJulianDate(((Long)currentTime.get()).longValue());
        if (flag)
        {
            animateTo(i);
            return;
        } else
        {
            MonthViewport monthviewport = viewport;
            Object obj = monthviewport.yearMonthHelper;
            obj = ((YearMonthHelper) (obj)).createForMs(((YearMonthHelper) (obj)).timeUtils.julianDateToMs(i));
            i = ((YearMonth) (obj)).getYear();
            double d = ((YearMonth) (obj)).getMonth() + i * 12;
            monthviewport.startFraction = d;
            i = (int)Math.floor(d);
            monthviewport.start = new AutoValue_YearMonth(i / 12, i % 12);
            i = (int)Math.ceil(d);
            monthviewport.end = new AutoValue_YearMonth(i / 12, i % 12);
            monthviewport.changeObservable.set(monthviewport);
            return;
        }
    }

    public final void goToTime(long l)
    {
        animateTo(timeUtils.msToJulianDate(l));
    }

    public final void onHide()
    {
        VisibleRangeUpdater visiblerangeupdater = visibleRangeUpdater;
        visiblerangeupdater.lastStart = 0;
        visiblerangeupdater.lastEnd = 0;
        visiblerangeupdater.lastMain = 0;
        if (showFuture != null)
        {
            showFuture.cancel(true);
            showFuture = null;
        }
    }

    public final void onLayoutChildren(LayoutUpdater layoutupdater, boolean flag)
    {
label0:
        {
            boolean flag2 = false;
            SettableFuture settablefuture = showFuture;
            showFuture = null;
            boolean flag1;
            if (forceAnimateNextLayout != null)
            {
                flag1 = forceAnimateNextLayout.booleanValue();
            } else
            if (flag || settablefuture != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            forceAnimateNextLayout = null;
            if (!flag1)
            {
                flag1 = flag2;
                if (!flag)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        layoutupdater = layoutItems(layoutupdater, flag1);
        if (settablefuture != null)
        {
            settablefuture.setFuture(layoutupdater);
        }
    }

    public final int onScrollStateChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBRCC5SMUTBK5T9M6SJFDHM56T31EHIJMAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UT39DLIMOQBECKNM2R3KCLP6SOBKCKNNCQB5ESNMIRBGDGNMOOBPDTQN8BQJCDP6UR3CADQ62T357C______0(int i)
    {
        if (i == android.support.v4.content.ModernAsyncTask.Status.DRAGGING$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0)
        {
            ViewportAnimator viewportanimator = viewportController.viewportAnimator;
            if (viewportanimator.currentAnimator != null)
            {
                viewportanimator.currentAnimator.cancel(true);
                viewportanimator.currentAnimator = null;
            }
        } else
        if (i == android.support.v4.content.ModernAsyncTask.Status.IDLE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0)
        {
            MonthViewportController monthviewportcontroller = viewportController;
            MonthViewport monthviewport = monthviewportcontroller.viewport;
            monthviewportcontroller.animateDelta((float)((double)Math.round(monthviewport.startFraction) - monthviewport.startFraction));
            return i;
        }
        return i;
    }

    public final FluentFuture onShow$514LKAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQ6DHQMARJK8PQN8TBICKTG____0(int i)
    {
        Object obj = viewport;
        Object obj1 = ((MonthViewport) (obj)).yearMonthHelper;
        obj1 = ((YearMonthHelper) (obj1)).createForMs(((YearMonthHelper) (obj1)).timeUtils.julianDateToMs(i));
        i = ((YearMonth) (obj1)).getYear();
        double d = ((YearMonth) (obj1)).getMonth() + i * 12;
        obj.startFraction = d;
        i = (int)Math.floor(d);
        obj.start = new AutoValue_YearMonth(i / 12, i % 12);
        i = (int)Math.ceil(d);
        obj.end = new AutoValue_YearMonth(i / 12, i % 12);
        ((MonthViewport) (obj)).changeObservable.set(obj);
        showFuture = new SettableFuture();
        forceAnimateNextLayout = Boolean.valueOf(false);
        obj = showFuture;
        if (obj instanceof FluentFuture)
        {
            return (FluentFuture)obj;
        } else
        {
            return new ForwardingFluentFuture(((com.google.common.util.concurrent.ListenableFuture) (obj)));
        }
    }

    public final Optional scroll(boolean flag)
    {
        Object obj = viewportController;
        class .Lambda._cls1
            implements Function
        {

            private final MonthLayoutImpl arg$1;

            public final Object apply(Object obj1)
            {
                obj1 = arg$1;
                return Integer.valueOf(((AdapterMonthDay)((MonthLayoutImpl) (obj1)).adapter.getMonth(((MonthLayoutImpl) (obj1)).viewport.start).getDays().get(0)).getMonthDayHeaderPosition());
            }

            .Lambda._cls1()
            {
                arg$1 = MonthLayoutImpl.this;
            }
        }

        float f;
        if (flag)
        {
            f = 1.0F;
        } else
        {
            f = -1F;
        }
        obj = (FluentFuture)AbstractTransformFuture.create(((MonthViewportController) (obj)).animateDelta(f), new .Lambda._cls1(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Present(obj);
        }
    }

    public final int scrollHorizontallyBy(int i, LayoutUpdater layoutupdater)
    {
        float f = (float)i / (float)hostView.getMeasuredWidth();
        MonthViewport monthviewport = viewport;
        double d = monthviewport.startFraction;
        int j;
        if (monthviewport.isRtl)
        {
            j = -1;
        } else
        {
            j = 1;
        }
        d = (double)((float)j * f) + d;
        monthviewport.startFraction = d;
        j = (int)Math.floor(d);
        monthviewport.start = new AutoValue_YearMonth(j / 12, j % 12);
        j = (int)Math.ceil(d);
        monthviewport.end = new AutoValue_YearMonth(j / 12, j % 12);
        monthviewport.changeObservable.set(monthviewport);
        layoutItems(layoutupdater, false);
        return i;
    }

    private class VisibleRangeUpdater
    {

        public int lastEnd;
        public int lastMain;
        public int lastStart;
        public final ObservableReference selectedRangeObservable;
        public final MonthLayoutImpl this$0;
        public final ObservableReference viewportObservable;

        VisibleRangeUpdater(ObservableReference observablereference, ObservableReference observablereference1)
        {
            this$0 = MonthLayoutImpl.this;
            super();
            viewportObservable = observablereference;
            selectedRangeObservable = observablereference1;
        }
    }

}
