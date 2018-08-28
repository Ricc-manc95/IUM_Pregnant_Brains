// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.RectF;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.CreationMode;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterDay;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterWeek;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry.PositionOnGrid;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutItemParams;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutUpdater;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.FluentFuture;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnDragState, ColumnDimens, ColumnViewport, AllDayManager, 
//            ColumnViewportController

final class ColumnLayoutUpdater
{

    private final TimelineAdapter adapter;
    private final LayoutItemParams allDayClickGuardParams;
    private final Rect allDayClip = new Rect();
    private final LayoutItemParams allDayEventParams;
    private final LayoutItemParams allDayExpanderParams = new LayoutItemParams();
    private final AllDayManager allDayManager;
    private final LayoutItemParams allDayOverflowParams = new LayoutItemParams();
    private final Rect allDayRect = new Rect();
    private final ObservableReference createEventPhantom;
    private final CreationMode creationMode;
    private final ObservableReference currentTime;
    private final LayoutItemParams dateHeaderParams = new LayoutItemParams();
    private final ColumnDimens dimens;
    private final DimensConverter dimensConverter;
    private final LayoutItemParams dragPaintParams;
    private final ObservableReference dragStateObservable;
    private final LayoutItemParams eventParams = new LayoutItemParams();
    private final Rect gridClip = new Rect();
    private final Point gridOffset;
    private final LayoutItemParams hoursParams;
    private final ObservableReference isA11yEnabled;
    private final ObservableReference isRtl;
    private final ItemAdapter itemAdapter;
    private Optional lastDragState;
    private final LayoutDimens layoutDimens;
    private final LayoutItemParams layoutItemParams[];
    public final ObservableReference layoutObservable = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(this);
    private final LayoutItemParams nowLineParams;
    private final LayoutItemParams phantomPaintParams = new LayoutItemParams();
    private final ScheduleProvider scheduleProvider;
    private final ObservableReference screenType;
    private final ObservableReference shouldShowWeekNumbers;
    private final TimeUtils timeUtils;
    public final Rect timedRect = new Rect();
    private final LayoutItemParams timesEventsLayoutItemParams[];
    private final TimelineHostView view;
    public final ColumnViewport viewport;
    public final ColumnViewportController viewportController;
    private final LayoutItemParams virtualTimedEventsParams;
    private final LayoutItemParams weekNumberParams = new LayoutItemParams();

    public ColumnLayoutUpdater(TimelineHostView timelinehostview, ObservableReference observablereference, ObservableReference observablereference1, DimensConverter dimensconverter, TimeUtils timeutils, ObservableReference observablereference2, ObservableReference observablereference3, 
            ObservableReference observablereference4, TimelineAdapter timelineadapter, AllDayManager alldaymanager, ColumnViewport columnviewport, ColumnViewportController columnviewportcontroller, Point point, ItemAdapter itemadapter, 
            ColumnDimens columndimens, LayoutDimens layoutdimens, ObservableReference observablereference5, ObservableReference observablereference6, ScheduleProvider scheduleprovider, CreationMode creationmode)
    {
        lastDragState = Absent.INSTANCE;
        LayoutItemParams layoutitemparams = new LayoutItemParams();
        layoutitemparams.type = com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutItemParams.Type.DISPLAY_ONLY;
        hoursParams = layoutitemparams;
        layoutitemparams = new LayoutItemParams();
        layoutitemparams.hasZOrder = true;
        layoutitemparams.zOrder = 1000;
        allDayEventParams = layoutitemparams;
        layoutitemparams = new LayoutItemParams();
        layoutitemparams.type = com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutItemParams.Type.DISPLAY_ONLY;
        nowLineParams = layoutitemparams;
        layoutitemparams = new LayoutItemParams();
        layoutitemparams.hasZOrder = true;
        layoutitemparams.zOrder = 998;
        dragPaintParams = layoutitemparams;
        layoutitemparams = new LayoutItemParams();
        layoutitemparams.hasZOrder = true;
        layoutitemparams.zOrder = 999;
        layoutitemparams.type = com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutItemParams.Type.DISPLAY_ONLY;
        allDayClickGuardParams = layoutitemparams;
        layoutitemparams = new LayoutItemParams();
        layoutitemparams.type = com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutItemParams.Type.VIRTUAL_ONLY;
        virtualTimedEventsParams = layoutitemparams;
        layoutItemParams = (new LayoutItemParams[] {
            hoursParams, allDayExpanderParams, allDayOverflowParams, allDayEventParams, allDayClickGuardParams, dateHeaderParams, nowLineParams, eventParams, dragPaintParams, phantomPaintParams
        });
        timesEventsLayoutItemParams = (new LayoutItemParams[] {
            eventParams, dragPaintParams, phantomPaintParams
        });
        view = timelinehostview;
        isRtl = observablereference;
        isA11yEnabled = observablereference1;
        dimensConverter = dimensconverter;
        adapter = timelineadapter;
        viewport = columnviewport;
        viewportController = columnviewportcontroller;
        createEventPhantom = observablereference3;
        dragStateObservable = observablereference4;
        allDayManager = alldaymanager;
        gridOffset = point;
        itemAdapter = itemadapter;
        timeUtils = timeutils;
        currentTime = observablereference2;
        dimens = columndimens;
        layoutDimens = layoutdimens;
        screenType = observablereference5;
        shouldShowWeekNumbers = observablereference6;
        scheduleProvider = scheduleprovider;
        creationMode = creationmode;
        observablereference = dragPaintParams;
        float f = timelinehostview.getContext().getResources().getDimension(0x7f0e011b);
        observablereference.hasElevation = true;
        observablereference.elevation = f;
    }

    private final boolean isDragEvent(AdapterEvent adapterevent)
    {
label0:
        {
            Object obj = (Optional)dragStateObservable.get();
            if (!((Optional) (obj)).isPresent())
            {
                break label0;
            }
            obj = ((ColumnDragState)((Optional) (obj)).get()).events().iterator();
            do
            {
                if (!((Iterator) (obj)).hasNext())
                {
                    break label0;
                }
            } while (((AdapterEvent)((Iterator) (obj)).next()).getPosition() != adapterevent.getPosition());
            return true;
        }
        return false;
    }

    private final void placeAllDayEvent(int i, int j, int k)
    {
        Object obj = viewport;
        int l = dimens.oneDayGridStartMarginPx;
        int i1 = Math.round(((ColumnViewport) (obj)).oneDayRatio * (float)(l - 0) + (float)0) + 0 + dimens.columnStartMarginPx + dimens.eventStartPaddingPx;
        int j1 = gridOffset.x + viewport.julianDayToGridStartPx(j) + i1;
        l = startToLeft(j1);
        obj = viewport;
        int k1 = layoutDimens.scheduleChipEndMargin();
        k1 = Math.round(((ColumnViewport) (obj)).oneDayRatio * (float)(k1 - 0) + (float)0);
        obj = viewport;
        j = startToLeft((((int)(((long)((ColumnViewport) (obj)).gridWidthPx * (long)(k - j) << 32) / ((ColumnViewport) (obj)).widthDaysFp16) >> 16) - (i1 + (k1 + 0))) + j1);
        obj = viewport;
        k = dimens.allDayTopPx;
        i1 = layoutDimens.converter.getPixelSize(10F);
        i = Math.round(((ColumnViewport) (obj)).oneDayRatio * (float)(i1 - k) + (float)k) + 0 + dimens.allDayRowHeightPx * i;
        k = dimens.allDayItemHeightPx;
        obj = allDayRect;
        i1 = Math.min(l, j);
        j = Math.max(l, j);
        obj.left = i1;
        obj.top = i;
        obj.right = j;
        obj.bottom = k + i;
    }

    private final int startToLeft(int i)
    {
        int j = i;
        if (((Boolean)isRtl.get()).booleanValue())
        {
            j = view.getMeasuredWidth() - i;
        }
        return j;
    }

    final ViewMode getViewMode()
    {
        if (viewport.snappedDays == 1)
        {
            return ViewMode.ONE_DAY_GRID;
        } else
        {
            return ViewMode.MULTI_DAY_GRID;
        }
    }

    final FluentFuture layoutItems(LayoutUpdater layoutupdater, boolean flag, boolean flag1, boolean flag2)
    {
        float f;
        int j;
        int k1;
        int i4;
        int j4;
        int l4;
        int i5;
        int j5;
        int k5;
        int l5;
        long l8;
        LayoutItemParams alayoutitemparams[] = layoutItemParams;
        k1 = alayoutitemparams.length;
        for (int i = 0; i < k1; i++)
        {
            LayoutItemParams layoutitemparams = alayoutitemparams[i];
            layoutitemparams.animate = flag;
            layoutitemparams.viewMode = getViewMode();
        }

        l8 = ((Long)currentTime.get()).longValue();
        k5 = view.getMeasuredWidth();
        l5 = view.getMeasuredHeight();
        Object obj = viewport;
        j = dimens.multiDayGridStartMarginPx;
        l4 = Math.round(((ColumnViewport) (obj)).oneDayRatio * (float)(0 - j) + (float)j);
        obj = allDayManager;
        boolean flag6 = ((AllDayManager) (obj)).firstShow;
        obj.firstShow = false;
        j = 0;
        i4 = ((AllDayManager) (obj)).viewport.targetStartDay;
        j4 = ((AllDayManager) (obj)).viewport.snappedDays;
        k1 = i4;
        while (k1 <= (j4 + i4) - 1) 
        {
            int l1;
            if (((AllDayManager) (obj)).viewport.snappedDays == 1)
            {
                l1 = ((AllDayManager) (obj)).adapter.getDay(k1).getAllDayEvents().size();
            } else
            {
                l1 = ((AllDayManager) (obj)).adapter.getDay(k1).getNumAllDayRows();
            }
            j = Math.max(j, l1);
            k1++;
        }
        ColumnViewport columnviewport;
        int i2;
        boolean flag4;
        if (j > 3)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        obj.shouldShowExpandButton = flag4;
        if (!((Boolean)((AllDayManager) (obj)).isExpanded.get()).booleanValue())
        {
            j = Math.min(3, j);
        }
        if (((AllDayManager) (obj)).viewport.snappedDays == 1 && j > 0)
        {
            i2 = 1;
        } else
        {
            i2 = 0;
        }
        if (i2 != 0)
        {
            k1 = ((AllDayManager) (obj)).layoutDimens.converter.getPixelSize(10F);
        } else
        {
            k1 = 0;
        }
        i4 = ((AllDayManager) (obj)).columnDimens.allDayRowHeightPx;
        if (i2 != 0)
        {
            i2 = ((AllDayManager) (obj)).columnDimens.allDayTopPx;
        } else
        {
            i2 = 0;
        }
        j = Math.max(k1 + i4 * j, i2);
        if (flag6 || !((AllDayManager) (obj)).isVisibleSupplier.get())
        {
            obj.animatedHeightPx = j;
        } else
        if (((AllDayManager) (obj)).targetHeightPx != j)
        {
            if (((AllDayManager) (obj)).allDayAnimator != null)
            {
                ((AllDayManager) (obj)).allDayAnimator.cancel();
            }
            obj.allDayAnimator = ValueAnimator.ofInt(new int[] {
                ((AllDayManager) (obj)).animatedHeightPx, j
            });
            ((AllDayManager) (obj)).allDayAnimator.addUpdateListener(new AllDayManager..Lambda._cls1(((AllDayManager) (obj))));
            com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker.OnGoingEvent ongoingevent = ((AllDayManager) (obj)).idleTracker.onEventBegin();
            ((AllDayManager) (obj)).allDayAnimator.addListener(new AllDayManager._cls1(((AllDayManager) (obj)), ongoingevent));
            ((AllDayManager) (obj)).allDayAnimator.start();
        }
        obj.targetHeightPx = j;
        j = allDayManager.animatedHeightPx;
        obj = viewport;
        k1 = dimens.allDayTopPx;
        j4 = Math.round(((ColumnViewport) (obj)).oneDayRatio * (float)(0 - k1) + (float)k1) + j;
        gridOffset.set(l4, j4);
        obj = viewportController;
        k1 = ((ColumnViewportController) (obj)).view.getMeasuredWidth();
        j = ((ColumnViewportController) (obj)).view.getMeasuredHeight();
        columnviewport = ((ColumnViewportController) (obj)).viewport;
        k1 -= ((ColumnViewportController) (obj)).gridOffset.x;
        j -= ((ColumnViewportController) (obj)).gridOffset.y;
        if (columnviewport.gridWidthPx != k1 || columnviewport.gridHeightPx != j)
        {
            columnviewport.gridWidthPx = k1;
            columnviewport.gridHeightPx = j;
            if (columnviewport.clampGridTopOfDay())
            {
                columnviewport.changeObservable.set(columnviewport);
            }
        }
        if (!flag2)
        {
            ColumnViewport columnviewport1;
            if (((ColumnViewportController) (obj)).lastGridY == null)
            {
                j = 0;
            } else
            {
                j = ((ColumnViewportController) (obj)).gridOffset.y - ((ColumnViewportController) (obj)).lastGridY.intValue();
            }
            columnviewport1 = ((ColumnViewportController) (obj)).viewport;
            k1 = ((ColumnViewportController) (obj)).viewport.gridTopMsOfDay;
            columnviewport1.setGridTopMsOfDay(j * ((ColumnViewportController) (obj)).viewport.gridMsPerVerticalPixel + k1);
        }
        obj.lastGridY = Integer.valueOf(((ColumnViewportController) (obj)).gridOffset.y);
        l4 = (int)(viewport.startDayFp16 >> 16);
        i4 = ((2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + l4) / 7;
        i5 = viewport.getRightVisibleJulianDay();
        j5 = ((2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + i5) / 7;
        obj = viewport;
        j = (int)(((TimeUtils.HOUR_FP16 & 65535L) - ((ColumnViewport) (obj)).gridTopFp16OfDay << 16) / ((ColumnViewport) (obj)).gridFp32PerVerticalPixel);
        obj = viewport;
        k1 = j - (int)(((0L & 65535L) - ((ColumnViewport) (obj)).gridTopFp16OfDay << 16) / ((ColumnViewport) (obj)).gridFp32PerVerticalPixel);
        if (k1 < dimensConverter.getPixelSize(56F)) goto _L2; else goto _L1
_L1:
        if ((ScreenType)screenType.get() == ScreenType.PHONE)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0 && viewport.snappedDays != 1) goto _L2; else goto _L3
_L3:
        f = 1.0F;
_L5:
        obj = timesEventsLayoutItemParams;
        k1 = obj.length;
        for (j = 0; j < k1; j++)
        {
            columnviewport1 = obj[j];
            columnviewport1.hasTextScale = true;
            columnviewport1.textScale = f;
        }

        break; /* Loop/switch isn't completed */
_L2:
        if (k1 >= dimensConverter.getPixelSize(40F))
        {
            f = 0.857F;
        } else
        {
            f = 0.714F;
        }
        if (true) goto _L5; else goto _L4
_L4:
        Object obj4;
        obj4 = new RectF();
        Object obj1;
        Rect rect1;
        if (flag1)
        {
            int k = view.getMeasuredWidth() - viewport.gridWidthPx;
            float f1;
            int k2;
            int k6;
            int i7;
            if (((Boolean)isRtl.get()).booleanValue())
            {
                Rect rect = gridClip;
                k1 = gridOffset.y;
                int j2 = startToLeft(gridOffset.x);
                rect.left = 0;
                rect.top = k1;
                rect.right = j2;
                rect.bottom = l5;
                rect = allDayClip;
                k1 = startToLeft(gridOffset.x);
                j2 = gridOffset.y;
                rect.left = 0;
                rect.top = 0;
                rect.right = k1;
                rect.bottom = j2;
                dateHeaderParams.setClip(0, 0, startToLeft(k), dimens.allDayTopPx);
            } else
            {
                obj1 = gridClip;
                k1 = gridOffset.x;
                int l2 = gridOffset.y;
                obj1.left = k1;
                obj1.top = l2;
                obj1.right = k5;
                obj1.bottom = l5;
                obj1 = allDayClip;
                k1 = gridOffset.x;
                l2 = gridOffset.y;
                obj1.left = k1;
                obj1.top = 0;
                obj1.right = k5;
                obj1.bottom = l2;
                dateHeaderParams.setClip(k, 0, k5, dimens.allDayTopPx);
            }
            rect1 = gridClip;
            obj1 = allDayClip;
        } else
        {
            rect1 = null;
            obj1 = null;
            dateHeaderParams.hasClip = false;
        }
        nowLineParams.setClip(rect1);
        allDayOverflowParams.setClip(((Rect) (obj1)));
        allDayEventParams.setClip(((Rect) (obj1)));
        eventParams.setClip(rect1);
        dragPaintParams.setClip(rect1);
        phantomPaintParams.setClip(rect1);
        if (((Boolean)shouldShowWeekNumbers.get()).booleanValue() && ((Boolean)isA11yEnabled.get()).booleanValue() && viewport.snappedDays != 1)
        {
            obj1 = weekNumberParams;
            obj1.type = com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutItemParams.Type.VIRTUAL_ONLY;
            obj1.contentDescription = scheduleProvider.getWeekText(timeUtils.julianDateToDateInfo(l4), Integer.valueOf(timeUtils.getCalendarFieldForJulianDay(l4, 3)));
            obj1.canScrollForward = true;
            obj1.canScrollBackward = true;
            obj1.position = CalendarViewType.WEEK_NUMBER.minPosition;
            int l;
            if (((Boolean)isRtl.get()).booleanValue())
            {
                l = view.getMeasuredWidth() - gridOffset.x;
            } else
            {
                l = 0;
            }
            if (((Boolean)isRtl.get()).booleanValue())
            {
                k1 = view.getMeasuredWidth();
            } else
            {
                k1 = gridOffset.x;
            }
            ((LayoutItemParams) (obj1)).setRect(l, 0, k1, dimens.allDayTopPx);
            layoutupdater.addItem(weekNumberParams);
        }
        int i1;
        if (viewport.snappedDays == 1 && viewport.oneDayRatio == 1.0F)
        {
            k = CalendarViewType.HOURS.minPosition;
            k1 = (int)(viewport.startDayFp16 >> 16);
            for (k5 = viewport.getRightVisibleJulianDay(); k1 <= k5; k1++)
            {
                f1 = viewport.julianDayToGridStartPx(k1);
                l5 = startToLeft((int)f1);
                k6 = startToLeft((int)f1 + dimens.oneDayGridStartMarginPx);
                k2 = Math.min(l5, k6);
                i7 = Math.max(l5, k6);
                if (k2 < view.getMeasuredWidth() && i7 >= 0)
                {
                    obj1 = hoursParams;
                    k2 = k + 1;
                    obj1.position = k;
                    layoutupdater.addItem(((LayoutItemParams) (obj1)).setRect(Math.min(l5, k6), 0, Math.max(l5, k6), view.getMeasuredHeight()));
                    k = k2;
                }
            }

        } else
        {
            obj1 = viewport;
            i1 = gridOffset.x;
            k1 = dimens.oneDayGridStartMarginPx;
            k1 = Math.round(((ColumnViewport) (obj1)).oneDayRatio * (float)(k1 - i1) + (float)i1);
            int i3;
            int i6;
            if (((Boolean)isRtl.get()).booleanValue())
            {
                i1 = view.getMeasuredWidth() - k1;
            } else
            {
                i1 = 0;
            }
            obj1 = hoursParams;
            obj1.position = CalendarViewType.HOURS.minPosition;
            layoutupdater.addItem(((LayoutItemParams) (obj1)).setRect(i1, 0, k1 + i1, view.getMeasuredHeight()));
        }
        if (allDayManager.shouldShowExpandButton)
        {
            if (viewport.snappedDays == 1)
            {
                i1 = 1;
            } else
            {
                i1 = 0;
            }
            if (i1 != 0 || ((Boolean)isA11yEnabled.get()).booleanValue() && ((Boolean)shouldShowWeekNumbers.get()).booleanValue())
            {
                i1 = dimens.allDayTopPx;
            } else
            {
                i1 = 0;
            }
            if (j4 - i1 >= dimensConverter.getPixelSize(24F))
            {
                if (viewport.snappedDays == 1 && viewport.oneDayRatio == 1.0F)
                {
                    k1 = CalendarViewType.ALL_DAY_EXPAND.minPosition;
                    for (i3 = (int)(viewport.startDayFp16 >> 16); i3 <= viewport.getRightVisibleJulianDay();)
                    {
                        i6 = viewport.julianDayToGridStartPx(i3);
                        k5 = startToLeft(i6);
                        i6 = startToLeft(i6 + dimens.oneDayGridStartMarginPx);
                        obj1 = allDayExpanderParams;
                        obj1.position = k1;
                        layoutupdater.addItem(((LayoutItemParams) (obj1)).setRect(Math.min(k5, i6), i1, Math.max(k5, i6), j4));
                        i3++;
                        k1++;
                    }

                } else
                {
                    k1 = startToLeft(0);
                    obj1 = viewport;
                    int j3 = gridOffset.x;
                    k5 = dimens.oneDayGridStartMarginPx;
                    j3 = startToLeft(Math.round(((ColumnViewport) (obj1)).oneDayRatio * (float)(k5 - j3) + (float)j3));
                    obj1 = allDayExpanderParams;
                    obj1.position = CalendarViewType.ALL_DAY_EXPAND.minPosition;
                    layoutupdater.addItem(((LayoutItemParams) (obj1)).setRect(Math.min(k1, j3), i1, Math.max(k1, j3), j4));
                }
            }
        }
        obj1 = allDayRect;
        i1 = viewport.gridWidthPx;
        k1 = gridOffset.x;
        int k3 = gridOffset.y;
        obj1.left = 0;
        obj1.top = 0;
        obj1.right = i1 + k1;
        obj1.bottom = k3;
        obj1 = allDayClickGuardParams;
        obj1.position = CalendarViewType.ALL_DAY_CLICK_GUARD.minPosition;
        rect1 = allDayRect;
        ((LayoutItemParams) (obj1)).rect.set(rect1);
        layoutupdater.addItem(((LayoutItemParams) (obj1)));
        k1 = i4;
        flag1 = true;
_L36:
        if (k1 > j5) goto _L7; else goto _L6
_L6:
        Object obj3;
        Object obj5;
        int l3;
        k5 = k1 * 7 - (2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue());
        obj3 = adapter.getWeek(k1).getDays();
        obj5 = new HashSet();
        l3 = 0;
_L35:
        if (l3 >= 7) goto _L9; else goto _L8
_L8:
        final AdapterDay day = (AdapterDay)((ImmutableList) (obj3)).get(l3);
        float f2;
        Object obj2;
        Object obj6;
        final Object event;
        Object obj7;
        int j1;
        boolean flag3;
        int k4;
        int j6;
        int l6;
        int j7;
        int k7;
        int l7;
        int i8;
        int j8;
        long l9;
        long l10;
        boolean flag5;
        boolean flag7;
        boolean flag9;
        if (!day.getLoaded())
        {
            flag5 = false;
        } else
        {
            flag5 = flag1;
        }
        j6 = (k1 * 7 - (2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue())) + l3;
        if (j6 < l4 || j6 > i5) goto _L11; else goto _L10
_L10:
        l9 = timeUtils.julianDateToMs(j6);
        l10 = TimeUnit.DAYS.toMillis(1L);
        if (l8 >= l9 && l8 < (l10 + l9) - 1L)
        {
            i4 = timeUtils.msToJulianDate(l8);
            j1 = (int)Math.ceil(dimens.nowLineRadius);
            obj2 = viewport;
            k4 = dimens.oneDayGridStartMarginPx;
            k4 = Math.round(((ColumnViewport) (obj2)).oneDayRatio * (float)(k4 - 0) + (float)0);
            obj2 = viewport;
            l6 = j1 / 4;
            j7 = j1 / 2;
            k4 = ((k4 + 0) - j1 - Math.round(((ColumnViewport) (obj2)).oneDayRatio * (float)(j7 - l6) + (float)l6)) + (gridOffset.x + viewport.julianDayToGridStartPx(i4));
            obj2 = viewport;
            l6 = layoutDimens.scheduleChipEndMargin();
            l6 = Math.round(((ColumnViewport) (obj2)).oneDayRatio * (float)(l6 - 0) + (float)0);
            j7 = gridOffset.x;
            i4 = (viewport.julianDayToGridStartPx(i4 + 1) + j7) - (l6 + 0);
            obj2 = viewport;
            l6 = (int)(((timeUtils.msToFp16(l8) & 65535L) - ((ColumnViewport) (obj2)).gridTopFp16OfDay << 16) / ((ColumnViewport) (obj2)).gridFp32PerVerticalPixel) + gridOffset.y;
            obj2 = nowLineParams;
            obj2.position = CalendarViewType.NOW_LINE.minPosition;
            layoutupdater.addItem(((LayoutItemParams) (obj2)).setRect(Math.min(startToLeft(k4), startToLeft(i4)), l6 - j1, Math.max(startToLeft(k4), startToLeft(i4)), j1 + l6));
        }
        k4 = day.getJulianDay();
        l6 = CalendarViewType.DAY_HEADER.minPosition;
        k7 = viewport.julianDayToGridStartPx(j6);
        obj2 = viewport;
        i8 = k7 + ((int)(((long)((ColumnViewport) (obj2)).gridWidthPx * (long)1 << 32) / ((ColumnViewport) (obj2)).widthDaysFp16) >> 16);
        j7 = i8 - k7;
        obj2 = viewport;
        j1 = dimens.multiDayHeaderStartMarginPx;
        i4 = dimens.oneDayHeaderStartMarginPx;
        i4 = Math.round(((ColumnViewport) (obj2)).oneDayRatio * (float)(i4 - j1) + (float)j1);
        if (getViewMode() == ViewMode.ONE_DAY_GRID)
        {
            j1 = dimens.oneDayGridStartMarginPx - i4;
        } else
        {
            j1 = j7 - i4;
        }
        l7 = Math.round((1.0F - viewport.oneDayRatio) * (float)gridOffset.x);
        j8 = l7 + i4;
        if (viewport.snappedDays == 1)
        {
            i4 = 1;
        } else
        {
            i4 = 0;
        }
        if (i4 == 0 && j6 == l4 && (float)j1 > dimensConverter.dpToPx(80F))
        {
            f2 = dimensConverter.dpToPx(60F);
            i4 = (int)Math.min(0.0F, (float)i8 - f2) + j8;
        } else
        {
            i4 = j8 + k7;
        }
        i8 = startToLeft(i4);
        j8 = startToLeft(j1 + i4);
        if (!((Boolean)isA11yEnabled.get()).booleanValue()) goto _L13; else goto _L12
_L12:
        if (viewport.snappedDays == 1)
        {
            j1 = 1;
        } else
        {
            j1 = 0;
        }
        if (j1 != 0) goto _L13; else goto _L14
_L14:
        if (((Boolean)isRtl.get()).booleanValue())
        {
            j1 = view.getMeasuredWidth() - l7 - k7;
            i4 = j1 - j7;
        } else
        {
            i4 = l7 + k7;
            j1 = i4 + j7;
        }
        dateHeaderParams.setVirtualRect(i4, 0, j1, dimens.allDayTopPx);
_L26:
        obj2 = dateHeaderParams;
        obj2.position = k4 + l6 + 100;
        layoutupdater.addItem(((LayoutItemParams) (obj2)).setRect(Math.min(i8, j8), dimens.dayHeaderTopMarginPx, Math.max(i8, j8), dimens.allDayTopPx));
        if (getViewMode() != ViewMode.ONE_DAY_GRID) goto _L16; else goto _L15
_L15:
        obj2 = day.getAllDayEvents();
        i4 = 0;
        if (!((Boolean)allDayManager.isExpanded.get()).booleanValue() && ((ImmutableList) (obj2)).size() > 3)
        {
            j1 = 1;
        } else
        {
            j1 = 0;
        }
        obj2 = (ImmutableList)obj2;
        l6 = ((ImmutableList) (obj2)).size();
        k4 = 0;
        obj6 = (UnmodifiableIterator)null;
_L27:
        if (k4 >= l6) goto _L18; else goto _L17
_L17:
        obj6 = ((ImmutableList) (obj2)).get(k4);
        k4++;
        event = (AdapterEvent)obj6;
        if (j1 == 0 || i4 < 2) goto _L20; else goto _L19
_L19:
        placeAllDayEvent(i4, j6, j6 + 1);
        obj2 = allDayOverflowParams;
        obj2.position = CalendarViewType.ALL_DAY_MORE_HEADER.minPosition + j6;
        obj6 = allDayRect;
        ((LayoutItemParams) (obj2)).rect.set(((Rect) (obj6)));
        layoutupdater.addItem(((LayoutItemParams) (obj2)));
_L18:
        k4 = viewport.julianDayToGridStartPx(day.getJulianDay());
        obj2 = viewport;
        j6 = (int)(((long)((ColumnViewport) (obj2)).gridWidthPx * (long)1 << 32) / ((ColumnViewport) (obj2)).widthDaysFp16);
        l6 = CalendarViewType.VIRTUAL_TIMED_EVENTS.minPosition + day.getJulianDay();
        obj2 = eventParams;
        obj2.parentId = l6;
        obj2.hasParentId = true;
        flag1 = false;
        flag2 = false;
        obj6 = day.getTimedEvents();
        j7 = ((ImmutableList) (obj6)).size();
        i4 = 0;
_L25:
        if (i4 >= j7) goto _L22; else goto _L21
_L21:
        event = (AdapterEvent)((ImmutableList) (obj6)).get(i4);
        flag7 = flag2;
        flag9 = flag1;
        if (isDragEvent(((AdapterEvent) (event)))) goto _L24; else goto _L23
_L23:
        obj2 = eventParams;
        j1 = ((AdapterEvent) (event)).getGridTimedPosition().z;
        obj2.hasZOrder = true;
        obj2.zOrder = j1;
        if (((Optional)dragStateObservable.get()).isPresent() || !lastDragState.isPresent())
        {
            j1 = 0;
        } else
        {
label0:
            {
                obj2 = ((ColumnDragState)lastDragState.get()).events().iterator();
                do
                {
                    if (!((Iterator) (obj2)).hasNext())
                    {
                        break label0;
                    }
                    obj7 = (AdapterEvent)((Iterator) (obj2)).next();
                } while (((AdapterEvent) (event)).getPosition() != ((AdapterEvent) (obj7)).getPosition());
                j1 = 1;
            }
        }
_L34:
        if (j1 != 0)
        {
            obj2 = eventParams;
            obj2.hasElevation = true;
            obj2.elevation = 0.0F;
            obj2.animate = true;
        } else
        {
            eventParams.hasElevation = false;
        }
        j1 = placeTimedEvent$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBR1CHGN0T35E8NK2P31E1Q6ASI5EPIMST1R954LKJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NN8QBDCLM6IRJ55TGMOT35E9N62T355TR6IPBN5TKMQS3C5TQN8QBC5T96AORK7CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQ6IRB5DHKMSP9FC5M78PBIDPGN8P9FEPKMATPFD5MN0R1FDHGNIRRLEGNM6RRCELMMSBQ3DTM7ARBE9HGNIRRLEHAN0P31EHIN4927E9KM8J31F5NNAT2ICLPNAR3K7C______0(((AdapterEvent) (event)), k4, j6 >> 16, false, timedRect);
        if (j1 == android.support.v4.content.ModernAsyncTask.Status.WITHIN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0)
        {
            obj7 = eventParams;
            if (((Boolean)isA11yEnabled.get()).booleanValue())
            {
                obj2 = new _cls1();
            } else
            {
                obj2 = null;
            }
            obj7.virtualActionHandler = ((com.google.android.apps.calendar.timeline.alternate.view.impl.layout.AccessibilityVirtualView.ActionHandler) (obj2));
            obj7.position = ((AdapterEvent) (event)).getPosition();
            obj2 = timedRect;
            ((LayoutItemParams) (obj7)).rect.set(((Rect) (obj2)));
            layoutupdater.addItem(((LayoutItemParams) (obj7)));
            flag9 = flag1;
            flag7 = flag2;
        } else
        {
            flag7 = flag2;
            flag9 = flag1;
            if (((Boolean)isA11yEnabled.get()).booleanValue())
            {
                eventParams.virtualActionHandler = new _cls2();
                if (j1 == android.support.v4.content.ModernAsyncTask.Status.ABOVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0)
                {
                    obj2 = timedRect;
                    obj7 = timedRect;
                    obj2.top = 1 - (((Rect) (obj7)).bottom - ((Rect) (obj7)).top);
                    timedRect.bottom = 1;
                    flag7 = true;
                    flag1 = flag2;
                    flag2 = flag7;
                } else
                if (j1 == android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0)
                {
                    flag7 = true;
                    timedRect.top = view.getMeasuredHeight() - 2;
                    obj2 = timedRect;
                    j1 = view.getMeasuredHeight();
                    obj7 = timedRect;
                    obj2.bottom = (j1 - 2) + (((Rect) (obj7)).bottom - ((Rect) (obj7)).top);
                    flag2 = flag1;
                    flag1 = flag7;
                } else
                {
                    boolean flag8 = flag1;
                    flag1 = flag2;
                    flag2 = flag8;
                }
                obj2 = eventParams;
                obj2.position = ((AdapterEvent) (event)).getPosition();
                event = timedRect;
                ((LayoutItemParams) (obj2)).rect.set(((Rect) (event)));
                layoutupdater.addItem(((LayoutItemParams) (obj2)));
                flag7 = flag1;
                flag9 = flag2;
            }
        }
_L24:
        i4++;
        flag2 = flag7;
        flag1 = flag9;
          goto _L25
_L13:
        dateHeaderParams.hasVirtualRect = false;
          goto _L26
_L20:
        placeAllDayEvent(i4, j6, j6 + 1);
        obj6 = allDayEventParams;
        obj6.position = ((AdapterEvent) (event)).getPosition();
        event = allDayRect;
        ((LayoutItemParams) (obj6)).rect.set(((Rect) (event)));
        layoutupdater.addItem(((LayoutItemParams) (obj6)));
        i4++;
          goto _L27
_L16:
        if (((Boolean)allDayManager.isExpanded.get()).booleanValue()) goto _L29; else goto _L28
_L28:
        j1 = day.getAllDayOverflowPosition();
        if (j1 < 0) goto _L29; else goto _L30
_L30:
        obj2 = viewport;
        k4 = day.getJulianDay();
        i4 = day.getJulianDay();
        k4 = ((ColumnViewport) (obj2)).julianDayToGridStartPx(k4);
        i4 = ((ColumnViewport) (obj2)).julianDayToGridStartPx(i4 + 1);
        obj4.left = Math.min(k4, i4);
        obj4.right = Math.max(k4, i4);
        i4 = startToLeft(gridOffset.x + Math.round(((RectF) (obj4)).left) + dimens.columnStartMarginPx + dimens.eventStartPaddingPx);
        k4 = startToLeft(gridOffset.x + Math.round(((RectF) (obj4)).right));
        obj2 = allDayOverflowParams;
        obj2.position = j1;
        layoutupdater.addItem(((LayoutItemParams) (obj2)).setRect(Math.min(i4, k4), gridOffset.y - dimens.allDayRowHeightPx, Math.max(i4, k4), gridOffset.y));
        i4 = 1;
_L46:
        obj2 = (ImmutableList)day.getAllDayEvents();
        j6 = ((ImmutableList) (obj2)).size();
        j1 = 0;
        obj6 = (UnmodifiableIterator)null;
_L32:
        if (j1 >= j6) goto _L18; else goto _L31
_L31:
        obj6 = ((ImmutableList) (obj2)).get(j1);
        j1++;
        obj6 = (AdapterEvent)obj6;
        event = ((AdapterEvent) (obj6)).getItem();
        obj7 = allDayManager;
        l6 = ((AdapterEvent) (obj6)).getGridAllDaySlot().intValue();
        if (!((Boolean)((AllDayManager) (obj7)).isExpanded.get()).booleanValue())
        {
            if (i4 != 0)
            {
                k4 = 1;
            } else
            {
                k4 = 0;
            }
            if (l6 >= 3 - k4)
            {
                break MISSING_BLOCK_LABEL_4546;
            }
        }
        k4 = 1;
_L33:
        if (k4 != 0 && ((Set) (obj5)).add(itemAdapter.getKey(((AdapterEvent) (obj6)).getItem())))
        {
            placeAllDayEvent(((AdapterEvent) (obj6)).getGridAllDaySlot().intValue(), Math.max(k5, itemAdapter.getStartDay(event)), Math.min(k5 + 7, itemAdapter.getEndDay(event) + 1));
            event = allDayEventParams;
            event.position = ((AdapterEvent) (obj6)).getPosition();
            obj6 = allDayRect;
            ((LayoutItemParams) (event)).rect.set(((Rect) (obj6)));
            layoutupdater.addItem(((LayoutItemParams) (event)));
        }
          goto _L32
          goto _L18
        k4 = 0;
          goto _L33
        j1 = 0;
          goto _L34
_L22:
        if (((Boolean)isA11yEnabled.get()).booleanValue())
        {
            obj2 = virtualTimedEventsParams;
            obj2.virtualActionHandler = new _cls3();
            obj2.canScrollForward = flag2;
            obj2.canScrollBackward = flag1;
            obj2 = virtualTimedEventsParams;
            obj2.position = l6;
            layoutupdater.addItem(((LayoutItemParams) (obj2)).setRect(0, gridOffset.y, view.getMeasuredWidth(), view.getMeasuredHeight()));
        }
_L11:
        l3++;
        flag1 = flag5;
          goto _L35
_L9:
        k1++;
          goto _L36
_L7:
        if (((Optional)createEventPhantom.get()).isPresent() && !isDragEvent((AdapterEvent)((Optional)createEventPhantom.get()).get()))
        {
            obj2 = (AdapterEvent)((Optional)createEventPhantom.get()).get();
            j1 = (int)(((AdapterEvent) (obj2)).getDisplayStartFp16() >> 16);
            j1 = viewport.julianDayToGridStartPx(j1);
            obj3 = viewport;
            if (placeTimedEvent$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBR1CHGN0T35E8NK2P31E1Q6ASI5EPIMST1R954LKJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NN8QBDCLM6IRJ55TGMOT35E9N62T355TR6IPBN5TKMQS3C5TQN8QBC5T96AORK7CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQ6IRB5DHKMSP9FC5M78PBIDPGN8P9FEPKMATPFD5MN0R1FDHGNIRRLEGNM6RRCELMMSBQ3DTM7ARBE9HGNIRRLEHAN0P31EHIN4927E9KM8J31F5NNAT2ICLPNAR3K7C______0(((AdapterEvent) (obj2)), j1, (int)(((long)((ColumnViewport) (obj3)).gridWidthPx * (long)1 << 32) / ((ColumnViewport) (obj3)).widthDaysFp16) >> 16, false, timedRect) == android.support.v4.content.ModernAsyncTask.Status.WITHIN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0)
            {
                if (creationMode.scrim && ((Optional)createEventPhantom.get()).isPresent())
                {
                    obj3 = phantomPaintParams;
                    f2 = dimensConverter.dpToPx(6F);
                    obj3.hasElevation = true;
                    obj3.elevation = f2;
                }
                obj3 = phantomPaintParams;
                obj3.animate = flag;
                obj3.hasZOrder = false;
                obj3.position = ((AdapterEvent) (obj2)).getPosition();
                obj2 = timedRect;
                ((LayoutItemParams) (obj3)).rect.set(((Rect) (obj2)));
                layoutupdater.addItem(((LayoutItemParams) (obj3)));
            }
        }
        k1 = 0;
        obj2 = (Optional)dragStateObservable.get();
        if (!((Optional) (obj2)).isPresent()) goto _L38; else goto _L37
_L37:
        obj3 = (ColumnDragState)((Optional) (obj2)).get();
        if (!lastDragState.isPresent())
        {
            j1 = 1;
        } else
        {
            j1 = 0;
        }
        obj4 = ((ColumnDragState) (obj3)).events();
        if (((List) (obj4)).isEmpty()) goto _L40; else goto _L39
_L39:
        obj5 = CalendarViewType.CREATE_EVENT;
        k1 = ((AdapterEvent)((List) (obj4)).get(0)).getPosition();
        if (k1 >= ((CalendarViewType) (obj5)).minPosition && k1 <= ((CalendarViewType) (obj5)).maxPosition)
        {
            k1 = 1;
        } else
        {
            k1 = 0;
        }
        if (k1 == 0) goto _L40; else goto _L41
_L41:
        k1 = 0;
_L45:
        if (lastDragState.isPresent() && (int)(((ColumnDragState)lastDragState.get()).dragTimeFp16() >> 16) != (int)(((ColumnDragState) (obj3)).dragTimeFp16() >> 16))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (flag3)
        {
            obj5 = dragPaintParams;
            obj5.hasAnimationDuration = true;
            obj5.animationDurationMs = 50L;
        } else
        {
            dragPaintParams.hasAnimationDuration = false;
        }
        obj5 = dragPaintParams;
        if (flag || flag3 || k1 != 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        obj5.animate = flag2;
        obj5 = ((ColumnDragState) (obj3)).events().iterator();
_L43:
        do
        {
            if (!((Iterator) (obj5)).hasNext())
            {
                break MISSING_BLOCK_LABEL_5968;
            }
            day = (AdapterEvent)((Iterator) (obj5)).next();
        } while (day.getJulianDay() != (int)(((ColumnDragState) (obj3)).dragTimeFp16() >> 16));
        j1 = viewport.julianDayToGridStartPx(day.getJulianDay());
        obj6 = viewport;
        placeTimedEvent$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBR1CHGN0T35E8NK2P31E1Q6ASI5EPIMST1R954LKJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NN8QBDCLM6IRJ55TGMOT35E9N62T355TR6IPBN5TKMQS3C5TQN8QBC5T96AORK7CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQ6IRB5DHKMSP9FC5M78PBIDPGN8P9FEPKMATPFD5MN0R1FDHGNIRRLEGNM6RRCELMMSBQ3DTM7ARBE9HGNIRRLEHAN0P31EHIN4927E9KM8J31F5NNAT2ICLPNAR3K7C______0(day, j1, (int)(((long)((ColumnViewport) (obj6)).gridWidthPx * (long)1 << 32) / ((ColumnViewport) (obj6)).widthDaysFp16) >> 16, true, timedRect);
        i4 = dimens.columnStartInset(viewport, true);
        k4 = dimens.columnEndInset(viewport);
        if (((Boolean)isRtl.get()).booleanValue())
        {
            j1 = -1;
        } else
        {
            j1 = 1;
        }
        if (((ColumnDragState) (obj3)).pagingDirection() <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (((Boolean)isRtl.get()).booleanValue())
        {
            i4 = view.getMeasuredWidth() - gridOffset.x - i4;
        } else
        {
            i4 = (gridOffset.x + viewport.gridWidthPx) - k4;
        }
        j1 = (i4 - timedRect.right) * j1;
_L44:
        obj3 = ((List) (obj4)).iterator();
        do
        {
            if (!((Iterator) (obj3)).hasNext())
            {
                break;
            }
            obj4 = (AdapterEvent)((Iterator) (obj3)).next();
            i4 = (int)(((AdapterEvent) (obj4)).getDisplayStartFp16() >> 16);
            i4 = viewport.julianDayToGridStartPx(i4);
            obj5 = viewport;
            if (placeTimedEvent$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBR1CHGN0T35E8NK2P31E1Q6ASI5EPIMST1R954LKJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NN8QBDCLM6IRJ55TGMOT35E9N62T355TR6IPBN5TKMQS3C5TQN8QBC5T96AORK7CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQ6IRB5DHKMSP9FC5M78PBIDPGN8P9FEPKMATPFD5MN0R1FDHGNIRRLEGNM6RRCELMMSBQ3DTM7ARBE9HGNIRRLEHAN0P31EHIN4927E9KM8J31F5NNAT2ICLPNAR3K7C______0(((AdapterEvent) (obj4)), i4 + j1, (int)(((long)((ColumnViewport) (obj5)).gridWidthPx * (long)1 << 32) / ((ColumnViewport) (obj5)).widthDaysFp16) >> 16, true, timedRect) == android.support.v4.content.ModernAsyncTask.Status.WITHIN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0)
            {
                obj5 = dragPaintParams;
                obj5.position = ((AdapterEvent) (obj4)).getPosition();
                obj4 = timedRect;
                ((LayoutItemParams) (obj5)).rect.set(((Rect) (obj4)));
                layoutupdater.addItem(((LayoutItemParams) (obj5)));
            }
        } while (true);
        break MISSING_BLOCK_LABEL_5974;
        if (((ColumnDragState) (obj3)).pagingDirection() >= 0) goto _L43; else goto _L42
_L42:
        if (((Boolean)isRtl.get()).booleanValue())
        {
            i4 = (view.getMeasuredWidth() - gridOffset.x - viewport.gridWidthPx) + k4;
        } else
        {
            i4 = gridOffset.x + i4;
        }
        j1 = (i4 - timedRect.left) * j1;
          goto _L44
        j1 = 0;
          goto _L44
        if (flag3 || k1 != 0)
        {
            j1 = 1;
        } else
        {
            j1 = 0;
        }
        obj3 = ((ColumnDragState)((Optional) (obj2)).get()).phantoms().iterator();
        do
        {
            k1 = j1;
            if (!((Iterator) (obj3)).hasNext())
            {
                break;
            }
            obj4 = (AdapterEvent)((Iterator) (obj3)).next();
            k1 = (int)(((AdapterEvent) (obj4)).getDisplayStartFp16() >> 16);
            k1 = viewport.julianDayToGridStartPx(k1);
            obj5 = viewport;
            if (placeTimedEvent$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBR1CHGN0T35E8NK2P31E1Q6ASI5EPIMST1R954LKJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NN8QBDCLM6IRJ55TGMOT35E9N62T355TR6IPBN5TKMQS3C5TQN8QBC5T96AORK7CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQ6IRB5DHKMSP9FC5M78PBIDPGN8P9FEPKMATPFD5MN0R1FDHGNIRRLEGNM6RRCELMMSBQ3DTM7ARBE9HGNIRRLEHAN0P31EHIN4927E9KM8J31F5NNAT2ICLPNAR3K7C______0(((AdapterEvent) (obj4)), k1, (int)(((long)((ColumnViewport) (obj5)).gridWidthPx * (long)1 << 32) / ((ColumnViewport) (obj5)).widthDaysFp16) >> 16, false, timedRect) == android.support.v4.content.ModernAsyncTask.Status.WITHIN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0)
            {
                obj5 = phantomPaintParams;
                obj5.animate = flag;
                k1 = ((AdapterEvent) (obj4)).getGridTimedPosition().z;
                obj5.hasZOrder = true;
                obj5.zOrder = k1;
                obj5.position = ((AdapterEvent) (obj4)).getPosition();
                obj4 = timedRect;
                ((LayoutItemParams) (obj5)).rect.set(((Rect) (obj4)));
                layoutupdater.addItem(((LayoutItemParams) (obj5)));
            }
        } while (true);
_L38:
        lastDragState = ((Optional) (obj2));
        layoutObservable.set(this);
        if (flag || k1 != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return layoutupdater.finish(flag, flag1);
_L40:
        k1 = j1;
        if (true) goto _L45; else goto _L29
_L29:
        i4 = 0;
          goto _L46
    }

    final int placeTimedEvent$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBR1CHGN0T35E8NK2P31E1Q6ASI5EPIMST1R954LKJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NN8QBDCLM6IRJ55TGMOT35E9N62T355TR6IPBN5TKMQS3C5TQN8QBC5T96AORK7CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQ6IRB5DHKMSP9FC5M78PBIDPGN8P9FEPKMATPFD5MN0R1FDHGNIRRLEGNM6RRCELMMSBQ3DTM7ARBE9HGNIRRLEHAN0P31EHIN4927E9KM8J31F5NNAT2ICLPNAR3K7C______0(AdapterEvent adapterevent, int i, int j, boolean flag, Rect rect)
    {
        long l1 = Math.max((long)adapterevent.getJulianDay() << 16, adapterevent.getDisplayStartFp16());
        long l2 = Math.min((long)(adapterevent.getJulianDay() + 1) << 16, adapterevent.getDisplayEndFp16());
        ColumnViewport columnviewport = viewport;
        int k = (int)(((65535L & l1) - columnviewport.gridTopFp16OfDay << 16) / columnviewport.gridFp32PerVerticalPixel);
        int l = (int)((l2 - l1 << 16) / viewport.gridFp32PerVerticalPixel);
        rect.top = gridOffset.y + k;
        rect.bottom = (l + rect.top) - dimens.eventBottomPaddingPx;
        k = dimens.columnStartInset(viewport, flag);
        l = j - (dimens.columnEndInset(viewport) + k);
        j = gridOffset.x;
        adapterevent = adapterevent.getGridTimedPosition();
        j = k + (j + 0 + i) + Math.round(((PositionOnGrid) (adapterevent)).startFraction * (float)l);
        k = Math.round((float)l * (((PositionOnGrid) (adapterevent)).endFraction - ((PositionOnGrid) (adapterevent)).startFraction)) + j;
        i = j;
        if (!flag)
        {
            i = j + dimens.eventStartPaddingPx;
        }
        rect.left = Math.min(startToLeft(i), startToLeft(k));
        rect.right = Math.max(startToLeft(i), startToLeft(k));
        if (k < gridOffset.x)
        {
            return android.support.v4.content.ModernAsyncTask.Status.BEFORE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0;
        }
        if (i > viewport.gridWidthPx + gridOffset.x)
        {
            return android.support.v4.content.ModernAsyncTask.Status.AFTER$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0;
        }
        if (rect.bottom < gridOffset.y)
        {
            return android.support.v4.content.ModernAsyncTask.Status.ABOVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0;
        }
        if (rect.top >= view.getMeasuredHeight())
        {
            return android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0;
        } else
        {
            return android.support.v4.content.ModernAsyncTask.Status.WITHIN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0;
        }
    }

    private class _cls1
        implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.AccessibilityVirtualView.ActionHandler
    {

        private final ColumnLayoutUpdater this$0;
        private final AdapterEvent val$event;

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
            return viewportController.animateShowRange(event.getJulianDay(), event.getDisplayStartFp16(), event.getDisplayEndFp16()).isPresent();
        }

        _cls1()
        {
            this$0 = ColumnLayoutUpdater.this;
            event = adapterevent;
            super();
        }
    }


    private class _cls2
        implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.AccessibilityVirtualView.ActionHandler
    {

        private final ColumnLayoutUpdater this$0;
        private final AdapterEvent val$event;

        public final boolean focus()
        {
            viewportController.animateShowRange(event.getJulianDay(), event.getDisplayStartFp16(), event.getDisplayEndFp16());
            return false;
        }

        public final Optional scroll(boolean flag, Integer integer)
        {
            return Absent.INSTANCE;
        }

        public final boolean showOnScreen()
        {
            return false;
        }

        _cls2()
        {
            this$0 = ColumnLayoutUpdater.this;
            event = adapterevent;
            super();
        }
    }


    private class _cls3
        implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.AccessibilityVirtualView.ActionHandler
    {

        public final ColumnLayoutUpdater this$0;
        private final AdapterDay val$day;

        public final boolean focus()
        {
            return false;
        }

        public final Optional scroll(boolean flag, Integer integer)
        {
            Object obj = viewportController;
            int j = ((ColumnViewportController) (obj)).viewport.gridTopMsOfDay;
            ColumnViewport columnviewport = ((ColumnViewportController) (obj)).viewport;
            int i = columnviewport.gridTopMsOfDay;
            int k = columnviewport.gridHeightPx;
            i = columnviewport.gridMsPerVerticalPixel * k + i;
            k = ((ColumnViewportController) (obj)).viewport.gridHeightPx * ((ColumnViewportController) (obj)).viewport.gridMsPerVerticalPixel;
            class .Lambda._cls0
                implements Function
            {

                private final _cls3 arg$1;
                private final AdapterDay arg$2;
                private final boolean arg$3;
                private final Integer arg$4;

                public final Object apply(Object obj1)
                {
                    _cls3 _lcls3 = arg$1;
                    AdapterDay adapterday = arg$2;
                    boolean flag1 = arg$3;
                    Integer integer1 = arg$4;
                    class .Lambda._cls1
                        implements Function
                    {

                        private final _cls3 arg$1;
                        private final AdapterDay arg$2;
                        private final boolean arg$3;
                        private final Integer arg$4;

                        public final Object apply(Object obj2)
                        {
                            Object obj3;
                            Integer integer2;
                            ImmutableList immutablelist;
                            int l;
                            int i1;
                            obj3 = arg$1;
                            obj2 = arg$2;
                            boolean flag2 = arg$3;
                            integer2 = arg$4;
                            ColumnLayoutUpdater columnlayoutupdater = ((_cls3) (obj3))._fld0;
                            immutablelist = ((AdapterDay) (obj2)).getTimedEvents();
                            int k1 = columnlayoutupdater.viewport.julianDayToGridStartPx(((AdapterDay) (obj2)).getJulianDay());
                            obj2 = columnlayoutupdater.viewport;
                            int l1 = (int)(((long)((ColumnViewport) (obj2)).gridWidthPx * (long)1 << 32) / ((ColumnViewport) (obj2)).widthDaysFp16);
                            if (integer2 == null)
                            {
                                l = 1;
                            } else
                            {
                                l = 0;
                            }
                            obj2 = null;
                            i1 = 0;
_L5:
                            if (i1 >= immutablelist.size()) goto _L2; else goto _L1
_L1:
                            int j1;
                            if (flag2)
                            {
                                j1 = i1;
                            } else
                            {
                                j1 = immutablelist.size() - i1 - 1;
                            }
                            obj3 = (AdapterEvent)immutablelist.get(j1);
                            if (columnlayoutupdater.placeTimedEvent$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBR1CHGN0T35E8NK2P31E1Q6ASI5EPIMST1R954LKJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NN8QBDCLM6IRJ55TGMOT35E9N62T355TR6IPBN5TKMQS3C5TQN8QBC5T96AORK7CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQ6IRB5DHKMSP9FC5M78PBIDPGN8P9FEPKMATPFD5MN0R1FDHGNIRRLEGNM6RRCELMMSBQ3DTM7ARBE9HGNIRRLEHAN0P31EHIN4927E9KM8J31F5NNAT2ICLPNAR3K7C______0(((AdapterEvent) (obj3)), k1, l1 >> 16, false, columnlayoutupdater.timedRect) != android.support.v4.content.ModernAsyncTask.Status.WITHIN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0)
                            {
                                continue; /* Loop/switch isn't completed */
                            }
                            if (l == 0) goto _L4; else goto _L3
_L3:
                            l = ((AdapterEvent) (obj3)).getPosition();
_L6:
                            return Integer.valueOf(l);
_L4:
                            if (integer2.intValue() == ((AdapterEvent) (obj3)).getPosition())
                            {
                                l = 1;
                            } else
                            if (obj2 == null)
                            {
                                obj2 = obj3;
                            }
                            i1++;
                              goto _L5
_L2:
                            if (obj2 == null)
                            {
                                l = -1;
                            } else
                            {
                                l = ((AdapterEvent) (obj2)).getPosition();
                            }
                              goto _L6
                        }

                            .Lambda._cls1(AdapterDay adapterday, boolean flag, Integer integer)
                            {
                                arg$1 = _cls3.this;
                                arg$2 = adapterday;
                                arg$3 = flag;
                                arg$4 = integer;
                            }
                    }

                    return (FluentFuture)AbstractTransformFuture.create((FluentFuture)obj1, _lcls3. new .Lambda._cls1(adapterday, flag1, integer1), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                }

                .Lambda._cls0(AdapterDay adapterday, boolean flag, Integer integer)
                {
                    arg$1 = _cls3.this;
                    arg$2 = adapterday;
                    arg$3 = flag;
                    arg$4 = integer;
                }
            }

            if (flag)
            {
                i = Math.min(TimeUtils.DAY_MS, k + i) - i;
            } else
            {
                i = Math.max(0, j - k) - j;
            }
            if (i == 0)
            {
                obj = Absent.INSTANCE;
            } else
            {
                obj = ((ColumnViewportController) (obj)).animateVerticalViewportChange(j + i);
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                obj = new Present(obj);
            }
            return ((Optional) (obj)).transform(new .Lambda._cls0(day, flag, integer));
        }

        public final boolean showOnScreen()
        {
            return false;
        }

        _cls3()
        {
            this$0 = ColumnLayoutUpdater.this;
            day = adapterday;
            super();
        }
    }

}
