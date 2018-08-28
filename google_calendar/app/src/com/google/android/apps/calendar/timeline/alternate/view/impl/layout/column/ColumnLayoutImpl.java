// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.animation.ValueAnimator;
import android.view.ViewConfiguration;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutUpdater;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnLayout, ColumnLayoutUpdater, ColumnViewport, ColumnViewportController, 
//            AllDayManager

public final class ColumnLayoutImpl extends ColumnLayout
{

    private int accumulatedHorizontalScroll;
    private int accumulatedVerticalScroll;
    private final AllDayManager allDayManager;
    private boolean clipOnLayout;
    private final ColumnLayoutUpdater columnLayoutUpdater;
    private final ObservableReference currentTime;
    private boolean focusDayHeaderOnNextLayout;
    private Boolean forceAnimateNextLayout;
    private boolean lockTopMs;
    private final int minFlingVelocity;
    private int scaleOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0;
    private final ObservableReference screenType;
    private int scrollOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0;
    private int scrollStartDay;
    private int scrollState$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0;
    public final ObservableReference shouldShowWeekNumbers;
    private SettableFuture showFuture;
    private boolean shown;
    private final TimeUtils timeUtils;
    private final TimelineHostView view;
    private final ViewConfiguration viewConfiguration;
    public final ColumnViewport viewport;
    private final ColumnViewportController viewportController;
    private final VisibleRangeUpdater visibleRangeUpdater;

    ColumnLayoutImpl(ColumnLayoutUpdater columnlayoutupdater, TimelineHostView timelinehostview, AllDayManager alldaymanager, ColumnViewport columnviewport, ColumnViewportController columnviewportcontroller, ObservableReference observablereference, ObservableReference observablereference1, 
            LayoutDimens layoutdimens, ObservableReference observablereference2, TimeUtils timeutils, ObservableReference observablereference3, ObservableReference observablereference4)
    {
        scrollOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 = 0;
        scaleOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 = 0;
        focusDayHeaderOnNextLayout = false;
        viewConfiguration = ViewConfiguration.get(timelinehostview.getContext());
        columnLayoutUpdater = columnlayoutupdater;
        view = timelinehostview;
        allDayManager = alldaymanager;
        viewport = columnviewport;
        viewportController = columnviewportcontroller;
        screenType = observablereference2;
        timeUtils = timeutils;
        currentTime = observablereference3;
        shouldShowWeekNumbers = observablereference4;
        minFlingVelocity = Math.round(layoutdimens.converter.dpToPx(400F));
        visibleRangeUpdater = new VisibleRangeUpdater(columnviewport, observablereference, observablereference1);
        columnlayoutupdater.layoutObservable.subscribe(visibleRangeUpdater, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE, false);
        class .Lambda._cls0
            implements Consumer
        {

            private final TimelineHostView arg$1;

            public final void accept(Object obj)
            {
                ColumnLayoutImpl.lambda$new$0$ColumnLayoutImpl$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBQKD5MMAR39DPIKGRRJEHB6IPBN7D666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBRCC5SMUTBK5THMUR3LDLN2UGRFDHQMQRIMD5INES3FE9Q3MAAM0(arg$1);
            }

            .Lambda._cls0(TimelineHostView timelinehostview)
            {
                arg$1 = timelinehostview;
            }
        }

        columnviewport.changeObservable.subscribe(new .Lambda._cls0(timelinehostview), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE, false);
    }

    static final void lambda$new$0$ColumnLayoutImpl$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBQKD5MMAR39DPIKGRRJEHB6IPBN7D666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBRCC5SMUTBK5THMUR3LDLN2UGRFDHQMQRIMD5INES3FE9Q3MAAM0(TimelineHostView timelinehostview)
    {
        timelinehostview.requestLayout();
    }

    public final boolean canScrollHorizontally()
    {
        return true;
    }

    public final boolean canScrollVertically()
    {
        return true;
    }

    public final boolean fling(int i, int j)
    {
        lockTopMs = false;
        if (Math.abs(i) > Math.abs(j) && Math.abs(i) > minFlingVelocity)
        {
            view.stopScroll();
            Object obj = viewportController;
            int l = scrollStartDay;
            Optional optional;
            int k;
            long l1;
            long l2;
            long l3;
            if (((Boolean)((ColumnViewportController) (obj)).isRtl.get()).booleanValue())
            {
                j = -1;
            } else
            {
                j = 1;
            }
            if (i < 0)
            {
                k = -1;
            } else
            {
                k = 1;
            }
            j *= k;
            k = ((ColumnViewportController) (obj)).viewport.snappedDays;
            if (k == 7)
            {
                j = (((2 - ((Integer)((ColumnViewportController) (obj)).timeUtils.firstDayOfWeek.get()).intValue()) + l) / 7 + j) * 7 - (2 - ((Integer)((ColumnViewportController) (obj)).timeUtils.firstDayOfWeek.get()).intValue());
            } else
            {
                j = k * j + l;
            }
            l1 = ((ColumnViewportController) (obj)).viewport.startDayFp16;
            l2 = (long)l << 16;
            l3 = (long)j << 16;
            if (l1 < l2 && l2 < l3 || l3 < l2 && l2 < l1)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (k != 0)
            {
                j = l;
            }
            k = ((ColumnViewportController) (obj)).viewport.julianDayToGridStartPx(j);
            optional = ((ColumnViewportController) (obj)).viewportAnimator.animateFling(i, k, new ColumnViewportController..Lambda._cls5(((ColumnViewportController) (obj))));
            if (optional.isPresent())
            {
                obj = (FluentFuture)AbstractTransformFuture.create((FluentFuture)optional.get(), new ColumnViewportController..Lambda._cls6(((ColumnViewportController) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            } else
            {
                ((ColumnViewportController) (obj)).animateSetStartDay(j);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public final ViewMode getViewMode()
    {
        if (columnLayoutUpdater.viewport.snappedDays == 1)
        {
            return ViewMode.ONE_DAY_GRID;
        } else
        {
            return ViewMode.MULTI_DAY_GRID;
        }
    }

    public final void goToDay(int i)
    {
        boolean flag;
        if ((ScreenType)screenType.get() == ScreenType.PHONE)
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
        if (flag && viewport.snappedDays == 1)
        {
            viewport.onShow(i, viewport.snappedDays);
            return;
        } else
        {
            viewportController.animateGoToDay(i);
            return;
        }
    }

    public final void goToNow(boolean flag)
    {
        if (flag)
        {
            Object obj = viewportController;
            long l = ((Long)currentTime.get()).longValue();
            obj = (FluentFuture)AbstractTransformFuture.create(((ColumnViewportController) (obj)).animateGoToDay(((ColumnViewportController) (obj)).timeUtils.msToJulianDate(l)), new ColumnViewportController..Lambda._cls0(((ColumnViewportController) (obj)), l), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            return;
        } else
        {
            viewportController.goToTime(((Long)currentTime.get()).longValue());
            return;
        }
    }

    public final void goToTime(long l)
    {
        Object obj = viewportController;
        obj = (FluentFuture)AbstractTransformFuture.create(((ColumnViewportController) (obj)).animateGoToDay(((ColumnViewportController) (obj)).timeUtils.msToJulianDate(l)), new ColumnViewportController..Lambda._cls0(((ColumnViewportController) (obj)), l), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    public final void onHide()
    {
        if (!shown)
        {
            throw new IllegalStateException();
        }
        shown = false;
        Object obj = allDayManager;
        ((AllDayManager) (obj)).isExpandedSubscription.cancel(false);
        obj.isExpandedSubscription = null;
        if (((AllDayManager) (obj)).allDayAnimator != null)
        {
            ((AllDayManager) (obj)).allDayAnimator.cancel();
            obj.allDayAnimator = null;
        }
        obj = visibleRangeUpdater;
        obj.lastStart = 0;
        obj.lastEnd = 0;
        if (showFuture != null)
        {
            showFuture.cancel(true);
            showFuture = null;
        }
    }

    public final void onLayoutChildren(LayoutUpdater layoutupdater, boolean flag)
    {
        SettableFuture settablefuture;
        boolean flag2;
        boolean flag3;
        flag2 = true;
        settablefuture = showFuture;
        showFuture = null;
        flag3 = clipOnLayout;
        clipOnLayout = true;
        if (forceAnimateNextLayout == null) goto _L2; else goto _L1
_L1:
        boolean flag1 = forceAnimateNextLayout.booleanValue();
_L4:
        forceAnimateNextLayout = null;
        layoutupdater = columnLayoutUpdater.layoutItems(layoutupdater, flag1, flag3, lockTopMs);
        if (settablefuture != null)
        {
            settablefuture.setFuture(layoutupdater);
        }
        if (focusDayHeaderOnNextLayout)
        {
            focusDayHeaderOnNextLayout = false;
            class .Lambda._cls2
                implements Supplier
            {

                private final ColumnLayoutImpl arg$1;

                public final Object get()
                {
                    return Integer.valueOf((int)(arg$1.viewport.startDayFp16 >> 16) + CalendarViewType.DAY_HEADER.minPosition + 100);
                }

            .Lambda._cls2()
            {
                arg$1 = ColumnLayoutImpl.this;
            }
            }

            view.requestFocusAfterLayout(layoutupdater, new .Lambda._cls2());
        }
        return;
_L2:
        flag1 = flag2;
        if (!flag)
        {
            flag1 = flag2;
            if (settablefuture == null)
            {
                flag1 = false;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final boolean onScale$5134CHI655D0____0(float f, float f1, float f2)
    {
        if (scaleOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 == 0)
        {
            int i = viewConfiguration.getScaledTouchSlop();
            if (f1 > (float)i && f1 > f)
            {
                scaleOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 = android.support.v4.content.ModernAsyncTask.Status.VERTICAL$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0;
                return true;
            }
            if (f > (float)i && f > f1)
            {
                scaleOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 = android.support.v4.content.ModernAsyncTask.Status.HORIZONTAL$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0;
                return false;
            }
        }
        if (scaleOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 == android.support.v4.content.ModernAsyncTask.Status.VERTICAL$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0)
        {
            ColumnViewportController columnviewportcontroller = viewportController;
            double d = f2 / f1;
            int j = columnviewportcontroller.viewport.gridTopMsOfDay;
            int k = (columnviewportcontroller.viewport.gridHeightPx * columnviewportcontroller.viewport.gridMsPerVerticalPixel) / 2;
            int l = (int)(d * (double)columnviewportcontroller.viewport.gridMsPerVerticalPixel);
            l = Math.max(columnviewportcontroller.minMsPerPixel, Math.min(columnviewportcontroller.maxMsPerPixel, l));
            columnviewportcontroller.viewport.setGridMsPerVerticalPx(l);
            columnviewportcontroller.gridMsPerVerticalPx.set(Integer.valueOf(l));
            l = columnviewportcontroller.viewport.gridTopMsOfDay;
            int i1 = (columnviewportcontroller.viewport.gridHeightPx * columnviewportcontroller.viewport.gridMsPerVerticalPixel) / 2;
            columnviewportcontroller.viewport.setGridTopMsOfDay(columnviewportcontroller.viewport.gridTopMsOfDay + ((j + k) - (l + i1)));
            return true;
        } else
        {
            return false;
        }
    }

    public final boolean onScaleBegin()
    {
        lockTopMs = false;
        return true;
    }

    public final void onScaleEnd()
    {
        scaleOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 = 0;
    }

    public final int onScrollStateChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBRCC5SMUTBK5T9M6SJFDHM56T31EHIJMAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UT39DLIMOQBECKNM2R3KCLP6SOBKCKNNCQB5ESNMIRBGDGNMOOBPDTQN8BQJCDP6UR3CADQ62T357C______0(int i)
    {
        lockTopMs = false;
        scrollState$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0 = i;
        if (i == android.support.v4.content.ModernAsyncTask.Status.DRAGGING$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0)
        {
            scrollStartDay = (int)(viewport.startDayFp16 >> 16);
            accumulatedHorizontalScroll = 0;
            accumulatedVerticalScroll = 0;
            scrollOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 = 0;
            ViewportAnimator viewportanimator = viewportController.viewportAnimator;
            if (viewportanimator.currentAnimator != null)
            {
                viewportanimator.currentAnimator.cancel(true);
                viewportanimator.currentAnimator = null;
            }
        } else
        if (i == android.support.v4.content.ModernAsyncTask.Status.IDLE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0)
        {
            ColumnViewportController columnviewportcontroller = viewportController;
            columnviewportcontroller.animateSetStartDay(columnviewportcontroller.viewport.getSnappedToClosestJulianDay());
            return i;
        }
        return i;
    }

    public final FluentFuture onShow(int i, int j, boolean flag)
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
        if (showFuture == null)
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
        shown = true;
        lockTopMs = true;
        Object obj;
        boolean flag3;
        if (!flag)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        clipOnLayout = flag3;
        viewport.onShow(i, j);
        i = timeUtils.msToJulianDate(((Long)currentTime.get()).longValue());
        if (i >= (int)(viewport.startDayFp16 >> 16) && i <= viewport.getRightVisibleJulianDay())
        {
            viewportController.goToTime(((Long)currentTime.get()).longValue());
        }
        obj = allDayManager;
        i = ((flag2) ? 1 : 0);
        if (((AllDayManager) (obj)).isExpandedSubscription == null)
        {
            i = 1;
        }
        if (i == 0)
        {
            throw new IllegalStateException();
        }
        obj.isExpandedSubscription = ((AllDayManager) (obj)).isExpanded.subscribe(new AllDayManager..Lambda._cls0(((AllDayManager) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE, true);
        obj.firstShow = true;
        showFuture = new SettableFuture();
        focusDayHeaderOnNextLayout = true;
        forceAnimateNextLayout = Boolean.valueOf(flag);
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
        lockTopMs = false;
        Object obj = viewportController;
        class .Lambda._cls1
            implements Function
        {

            private final ColumnLayoutImpl arg$1;

            public final Object apply(Object obj1)
            {
                obj1 = arg$1;
                int j;
                if (((Boolean)((ColumnLayoutImpl) (obj1)).shouldShowWeekNumbers.get()).booleanValue())
                {
                    j = CalendarViewType.WEEK_NUMBER.minPosition;
                } else
                {
                    j = (int)(((ColumnLayoutImpl) (obj1)).viewport.startDayFp16 >> 16) + CalendarViewType.DAY_HEADER.minPosition + 100;
                }
                return Integer.valueOf(j);
            }

            .Lambda._cls1()
            {
                arg$1 = ColumnLayoutImpl.this;
            }
        }

        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = -1;
        }
        obj = (FluentFuture)AbstractTransformFuture.create(((ColumnViewportController) (obj)).animateDragPage(i), new .Lambda._cls1(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
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
        lockTopMs = false;
        if (scrollOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 == 0)
        {
            accumulatedHorizontalScroll = accumulatedHorizontalScroll + i;
            if (Math.abs(accumulatedHorizontalScroll) >= viewConfiguration.getScaledPagingTouchSlop())
            {
                scrollOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 = android.support.v4.content.ModernAsyncTask.Status.HORIZONTAL$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0;
            }
        }
        if (scrollOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 == android.support.v4.content.ModernAsyncTask.Status.HORIZONTAL$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0)
        {
            viewportController.scrollHorizontallyPx(i);
            columnLayoutUpdater.layoutItems(layoutupdater, false, true, lockTopMs);
        }
        return i;
    }

    public final int scrollVerticallyBy(int i, LayoutUpdater layoutupdater)
    {
        lockTopMs = false;
        if (scrollOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 == 0)
        {
            accumulatedVerticalScroll = accumulatedVerticalScroll + i;
            if (Math.abs(accumulatedVerticalScroll) >= viewConfiguration.getScaledTouchSlop())
            {
                scrollOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 = android.support.v4.content.ModernAsyncTask.Status.VERTICAL$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0;
            }
        }
        if (scrollOrientation$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0 == android.support.v4.content.ModernAsyncTask.Status.VERTICAL$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT29DLO6O92FE9KMARJKC5Q6IRRE7C______0)
        {
            ColumnViewportController columnviewportcontroller = viewportController;
            ColumnViewport columnviewport = columnviewportcontroller.viewport;
            int j = columnviewportcontroller.viewport.gridTopMsOfDay;
            boolean flag = columnviewport.setGridTopMsOfDay(columnviewportcontroller.viewport.gridMsPerVerticalPixel * i + j);
            columnLayoutUpdater.layoutItems(layoutupdater, false, true, lockTopMs);
            if (flag && scrollState$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.SETTLING$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0)
            {
                view.stopScroll();
            }
        }
        return i;
    }

    public final FluentFuture setNumDaysAndStart(int i, int j)
    {
        focusDayHeaderOnNextLayout = true;
        lockTopMs = true;
        ColumnViewportController columnviewportcontroller = viewportController;
        columnviewportcontroller.viewport.snappedDays = i;
        ColumnViewport columnviewport = columnviewportcontroller.viewport;
        i = j;
        if (columnviewport.snappedDays == 7)
        {
            TimeUtils timeutils = columnviewport.timeUtils;
            i = (((2 - ((Integer)columnviewport.timeUtils.firstDayOfWeek.get()).intValue()) + j) / 7) * 7 - (2 - ((Integer)timeutils.firstDayOfWeek.get()).intValue());
        }
        return columnviewportcontroller.animateSetStartDay(i);
    }

    private class VisibleRangeUpdater
        implements Consumer
    {

        public int lastEnd;
        public int lastStart;
        private final ObservableReference selectedRangeObservable;
        private final ColumnViewport viewport;
        private final ObservableReference viewportObservable;

        public final void accept(Object obj)
        {
            int i = (int)(viewport.startDayFp16 >> 16);
            int j = viewport.getRightVisibleJulianDay();
            if (lastStart != i || lastEnd != j)
            {
                lastStart = i;
                lastEnd = j;
                obj = Range.closed(Integer.valueOf(i), Integer.valueOf(j));
                viewportObservable.set(obj);
                selectedRangeObservable.set(obj);
            }
        }

        VisibleRangeUpdater(ColumnViewport columnviewport, ObservableReference observablereference, ObservableReference observablereference1)
        {
            viewport = columnviewport;
            viewportObservable = observablereference;
            selectedRangeObservable = observablereference1;
        }
    }

}
