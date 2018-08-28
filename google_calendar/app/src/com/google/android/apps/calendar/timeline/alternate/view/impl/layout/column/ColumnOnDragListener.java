// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Pair;
import android.view.DragEvent;
import android.view.View;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.android.apps.calendar.timeline.alternate.util.FP16;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.CreationHandler;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry.PositionOnGrid;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.timeline.dnd.DragScrollPageController;
import com.google.android.calendar.timeline.dnd.DragScrollPageControllerFactory;
import com.google.android.calendar.utils.NumberUtils;
import com.google.common.base.Absent;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewport, ColumnDimens, ColumnDragState, DragGuideManager, 
//            ColumnViewportController

public final class ColumnOnDragListener
    implements android.view.View.OnDragListener
{

    private static final PositionOnGrid DRAG_EVENT_POSITION_ON_GRID = new PositionOnGrid(0.0F, 1.0F, 0);
    private static final long MIN_CHIP_HEIGHT_FP16 = (1L << 16) / 48L;
    private static long QUARTER_HOUR_FP32 = 0x2aaaaaaL;
    private final ColumnDimens columnDimens;
    private final CreationHandler creationHandler;
    public int curX;
    public int curY;
    private final DragGuideManager dragGuideManager;
    public com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode dragMode;
    private final Point dragOffset;
    private final Point gridOffset;
    private final TimelineHostView hostView;
    private com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker.OnGoingEvent idleEvent;
    private final IdleTracker idleTracker;
    public final ObservableReference isRtl;
    public final ItemAdapter itemAdapter;
    private AdapterEvent originalEvent;
    private Function rescheduleFinalizer;
    private com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.RescheduleManager rescheduleManager;
    private DragScrollPageController scrollPageController;
    private final DragScrollPageControllerFactory scrollPageControllerFactory;
    public final ObservableReference stateObservable;
    private final CalendarContentStore store;
    public SettableFuture storeBlockingFuture;
    private final TimeUtils timeUtils;
    private final TimelineAdapter timelineAdapter;
    private final ColumnViewport viewport;
    public final ColumnViewportController viewportController;
    private Subscription viewportSubscription;

    public ColumnOnDragListener(ObservableReference observablereference, TimelineHostView timelinehostview, TimelineAdapter timelineadapter, TimeUtils timeutils, ObservableReference observablereference1, Point point, Point point1, 
            ColumnViewport columnviewport, ColumnDimens columndimens, ColumnViewportController columnviewportcontroller, ItemAdapter itemadapter, com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.RescheduleManager reschedulemanager, DragGuideManager dragguidemanager, DragScrollPageControllerFactory dragscrollpagecontrollerfactory, 
            IdleTracker idletracker, CalendarContentStore calendarcontentstore, CreationHandler creationhandler)
    {
        stateObservable = observablereference;
        hostView = timelinehostview;
        timelineAdapter = timelineadapter;
        viewport = columnviewport;
        columnDimens = columndimens;
        viewportController = columnviewportcontroller;
        timeUtils = timeutils;
        isRtl = observablereference1;
        dragOffset = point;
        gridOffset = point1;
        itemAdapter = itemadapter;
        rescheduleManager = reschedulemanager;
        dragGuideManager = dragguidemanager;
        scrollPageControllerFactory = dragscrollpagecontrollerfactory;
        idleTracker = idletracker;
        store = calendarcontentstore;
        creationHandler = creationhandler;
    }

    private final long adjustDeltaFp16ForEnd(long l)
    {
        long l1;
        if (dragMode == com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode.START)
        {
            l1 = 0L;
        } else
        {
            l1 = l;
            if (dragMode == com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode.END)
            {
                return Math.max(l, (originalEvent.getDisplayStartFp16() - originalEvent.getDisplayEndFp16()) + MIN_CHIP_HEIGHT_FP16);
            }
        }
        return l1;
    }

    private final long adjustDeltaFp16ForStart(long l)
    {
        if (dragMode == com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode.START)
        {
            l = Math.min(l, originalEvent.getDisplayEndFp16() - originalEvent.getDisplayStartFp16() - MIN_CHIP_HEIGHT_FP16);
        } else
        if (dragMode == com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode.END)
        {
            return 0L;
        }
        return l;
    }

    private final long pxToFp16(int i, int j)
    {
        int k = viewport.gridWidthPx;
        k = gridOffset.x + k;
        Optional optional;
        if (((Boolean)isRtl.get()).booleanValue())
        {
            i = NumberUtils.clamp(i - dragOffset.x, 0, k - gridOffset.x);
        } else
        {
            i = NumberUtils.clamp(i - dragOffset.x, gridOffset.x, k);
        }
        k = i;
        if (!((Boolean)isRtl.get()).booleanValue())
        {
            k = i - gridOffset.x;
        }
        i = NumberUtils.clamp(j - dragOffset.y, gridOffset.y, viewport.gridHeightPx + gridOffset.y);
        j = gridOffset.y;
        optional = viewport.gridPxToFp16(k, i - j);
        if (!optional.isPresent())
        {
            throw new VerifyException(Strings.lenientFormat("Adjusted coordinates must be inside of the grid.", new Object[0]));
        } else
        {
            return ((Long)optional.get()).longValue();
        }
    }

    public final boolean onDrag(View view, DragEvent dragevent)
    {
        dragevent.getAction();
        JVM INSTR tableswitch 1 6: default 44
    //                   1 46
    //                   2 1059
    //                   3 1228
    //                   4 1244
    //                   5 1043
    //                   6 1212;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        return false;
_L2:
        if (!idleTracker.isIdle())
        {
            return false;
        }
        view = ((View) (dragevent.getLocalState()));
        if (view instanceof Pair) goto _L9; else goto _L8
_L8:
        view = null;
_L11:
        if (view == null)
        {
            return false;
        }
        break; /* Loop/switch isn't completed */
_L9:
        view = (Pair)view;
        if (!(((Pair) (view)).first instanceof com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode) || !(((Pair) (view)).second instanceof AdapterEvent))
        {
            view = null;
        }
        if (true) goto _L11; else goto _L10
_L10:
        Object obj = (AdapterEvent)((Pair) (view)).second;
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = timelineAdapter.getEvent(((AdapterEvent) (obj)).getPosition());
        }
        if (obj == null)
        {
            return false;
        }
        if (!((AdapterEvent) (obj)).getIsTimedEvent())
        {
            view = hostView.getContext();
            dragevent = AnalyticsLoggerHolder.instance;
            if (dragevent == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            } else
            {
                ((AnalyticsLogger)dragevent).trackEvent(view, "dnd", "dnd_pickup_failed", "long_press_timeline_chip_grid_allday", Long.valueOf(0L));
                return false;
            }
        }
        Object obj3 = CalendarViewType.CREATE_EVENT;
        int i = ((AdapterEvent) (obj)).getPosition();
        class .Lambda._cls0
            implements Consumer
        {

            private final ColumnOnDragListener arg$1;

            public final void accept(Object obj5)
            {
                ColumnOnDragListener columnondraglistener = arg$1;
                obj5 = (com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction)obj5;
                if (columnondraglistener.storeBlockingFuture != null)
                {
                    ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj5)).blockUpdates(columnondraglistener.storeBlockingFuture);
                }
            }

            .Lambda._cls0()
            {
                arg$1 = ColumnOnDragListener.this;
            }
        }

        class .Lambda._cls1
            implements Consumer
        {

            private final ColumnOnDragListener arg$1;

            public final void accept(Object obj5)
            {
                obj5 = arg$1;
                Object obj6 = (Optional)((ColumnOnDragListener) (obj5)).stateObservable.get();
                if (((Optional) (obj6)).isPresent())
                {
                    obj6 = ((ColumnDragState)((Optional) (obj6)).get()).toBuilder();
                    ((ColumnOnDragListener) (obj5)).onDragLocationUpdated(((ColumnDragState.Builder) (obj6)), ((ColumnOnDragListener) (obj5)).curX, ((ColumnOnDragListener) (obj5)).curY);
                    ((ColumnOnDragListener) (obj5)).updateDraggedEvents(((ColumnDragState.Builder) (obj6)));
                    obj5 = ((ColumnOnDragListener) (obj5)).stateObservable;
                    obj6 = ((ColumnDragState.Builder) (obj6)).build();
                    if (obj6 == null)
                    {
                        throw new NullPointerException();
                    }
                    ((ObservableReference) (obj5)).set(new Present(obj6));
                }
            }

            .Lambda._cls1()
            {
                arg$1 = ColumnOnDragListener.this;
            }
        }

        int k;
        int l;
        int j1;
        int k1;
        int l1;
        if (i >= ((CalendarViewType) (obj3)).minPosition && i <= ((CalendarViewType) (obj3)).maxPosition)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            rescheduleFinalizer = null;
        } else
        {
            Object obj4;
            if (((Pair) (view)).first == com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode.MOVE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            obj4 = String.valueOf(((Pair) (view)).first);
            obj4 = (new StringBuilder(String.valueOf(obj4).length() + 23)).append("Unsupported drag mode: ").append(((String) (obj4))).toString();
            if (i == 0)
            {
                throw new IllegalStateException(String.valueOf(obj4));
            }
            obj4 = rescheduleManager.start(((AdapterEvent) (obj)).getItem(), viewport.snappedDays);
            if (!((Optional) (obj4)).isPresent())
            {
                return false;
            }
            rescheduleFinalizer = (Function)((Optional) (obj4)).get();
        }
        originalEvent = ((AdapterEvent) (obj));
        dragMode = (com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode)((Pair) (view)).first;
        idleEvent = idleTracker.onEventBegin();
        storeBlockingFuture = new SettableFuture();
        store.updateStore(new .Lambda._cls0());
        view = (new AutoValue_ColumnDragState.Builder()).pagingDirection(0).scrolling(false).startDragTimeFp16(pxToFp16((int)dragevent.getX(), (int)dragevent.getY()));
        onDragLocationUpdated(view, (int)dragevent.getX(), (int)dragevent.getY());
        updateDraggedEvents(view);
        dragevent = CalendarViewType.CREATE_EVENT;
        i = originalEvent.getPosition();
        if (i >= ((CalendarViewType) (dragevent)).minPosition && i <= ((CalendarViewType) (dragevent)).maxPosition)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            view.phantoms(Collections.emptyList());
        } else
        {
            long l2 = originalEvent.getDisplayStartFp16();
            long l5 = originalEvent.getDisplayEndFp16();
            k = (int)(l2 >> 16);
            int i1 = (int)(FP16.fp16Ceil(l5) >> 16);
            dragevent = new ArrayList((i1 - k) + 1);
            for (i = CalendarViewType.DRAG_EVENT.minPosition; k < i1; i++)
            {
                long l7 = Math.max((long)k << 16, l2);
                long l9 = Math.min((long)(k + 1) << 16, l5);
                dragevent.add(originalEvent.toBuilder().setPosition(i).setJulianDay(k).setDisplayStartFp16(l7).setDisplayEndFp16(l9).build());
                k++;
            }

            view.phantoms(dragevent);
        }
        l = gridOffset.y;
        j1 = gridOffset.y;
        k1 = viewport.gridHeightPx;
        i = columnDimens.columnStartInset(viewport, true);
        l1 = columnDimens.columnEndInset(viewport);
        if (((Boolean)isRtl.get()).booleanValue())
        {
            i = hostView.getMeasuredWidth() - gridOffset.x - i;
            k = (hostView.getMeasuredWidth() - gridOffset.x - viewport.gridWidthPx) + l1;
        } else
        {
            k = i + gridOffset.x;
            i = (gridOffset.x + viewport.gridWidthPx) - l1;
        }
        dragevent = scrollPageControllerFactory;
        obj = new Rect(k, l, i, j1 + k1);
        i = hostView.getContext().getResources().getDimensionPixelOffset(0x7f0e011c);
        obj3 = new _cls1();
        scrollPageController = new DragScrollPageController((Context)DragScrollPageControllerFactory.checkNotNull((Context)((DragScrollPageControllerFactory) (dragevent)).contextProvider.get(), 1), (Rect)DragScrollPageControllerFactory.checkNotNull(obj, 2), i, (com.google.android.calendar.timeline.dnd.DragScrollPageController.Delegate)DragScrollPageControllerFactory.checkNotNull(obj3, 4));
        scrollPageController.disableAreasAroundNextPosition = true;
        viewportSubscription = viewport.changeObservable.subscribe(new .Lambda._cls1(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE, false);
        dragevent = stateObservable;
        view = view.build();
        if (view == null)
        {
            throw new NullPointerException();
        } else
        {
            dragevent.set(new Present(view));
            return true;
        }
_L6:
        return ((Optional)stateObservable.get()).isPresent();
_L3:
        view = (Optional)stateObservable.get();
        if (!view.isPresent())
        {
            return false;
        }
        Object obj1 = ((ColumnDragState)view.get()).toBuilder();
        onDragLocationUpdated(((ColumnDragState.Builder) (obj1)), (int)dragevent.getX(), (int)dragevent.getY());
        updateDraggedEvents(((ColumnDragState.Builder) (obj1)));
        view = stateObservable;
        obj1 = ((ColumnDragState.Builder) (obj1)).build();
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        view.set(new Present(obj1));
        scrollPageController.onDrag((int)dragevent.getX() - dragOffset.x, (int)dragevent.getY() - dragOffset.y);
        if (idleTracker.eventCount() == 1)
        {
            hostView.requestLayout();
            hostView.invalidate();
        }
        return true;
_L7:
        return ((Optional)stateObservable.get()).isPresent();
_L4:
        return ((Optional)stateObservable.get()).isPresent();
_L5:
        Object obj2 = (Optional)stateObservable.get();
        if (!((Optional) (obj2)).isPresent())
        {
            return false;
        }
        if (CalendarViewType.forPosition(originalEvent.getPosition()) == CalendarViewType.CREATE_EVENT)
        {
            obj2 = (ColumnDragState)((Optional) (obj2)).get();
            view = storeBlockingFuture;
            dragevent = idleEvent;
            long l3 = ((((ColumnDragState) (obj2)).dragTimeFp16() - ((ColumnDragState) (obj2)).startDragTimeFp16() << 16) / QUARTER_HOUR_FP32) * QUARTER_HOUR_FP32 >> 16;
            int j;
            long l6;
            if (dragMode == com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode.MOVE)
            {
                l6 = timeUtils.fp16ToMs(l3 + originalEvent.getDisplayStartFp16());
                l3 = (originalEvent.getEndTimeMs() - originalEvent.getStartTimeMs()) + l6;
            } else
            {
                l6 = timeUtils.fp16ToMs(originalEvent.getDisplayStartFp16() + adjustDeltaFp16ForStart(l3));
                obj2 = timeUtils;
                long l8 = originalEvent.getDisplayEndFp16();
                l3 = ((TimeUtils) (obj2)).fp16ToMs(adjustDeltaFp16ForEnd(l3) + l8);
            }
            creationHandler.onDrag(l6, l3);
            view.set(null);
            dragevent.onComplete();
        } else
        {
            view = ((View) (originalEvent.getItem()));
            dragevent = rescheduleFinalizer;
            ColumnDragState columndragstate = (ColumnDragState)((Optional) (obj2)).get();
            obj2 = idleEvent;
            SettableFuture settablefuture = storeBlockingFuture;
            long l4 = ((columndragstate.dragTimeFp16() - columndragstate.startDragTimeFp16() << 16) / QUARTER_HOUR_FP32) * QUARTER_HOUR_FP32 >> 16;
            if (l4 == 0L)
            {
                settablefuture.set(dragevent.apply(Absent.INSTANCE));
                ((com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker.OnGoingEvent) (obj2)).onComplete();
            } else
            {
                l4 = timeUtils.fp16ToMs(l4 + originalEvent.getDisplayStartFp16());
                class .Lambda._cls2
                    implements Consumer
                {

                    private final ColumnOnDragListener arg$1;
                    private final Object arg$2;
                    private final long arg$3;
                    private final SettableFuture arg$4;
                    private final Function arg$5;

                    public final void accept(Object obj5)
                    {
                        ColumnOnDragListener columnondraglistener = arg$1;
                        Object obj6 = arg$2;
                        long l10 = arg$3;
                        SettableFuture settablefuture1 = arg$4;
                        Function function = arg$5;
                        obj5 = (com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction)obj5;
                        ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj5)).removeItem(obj6);
                        ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj5)).addItem(columnondraglistener.itemAdapter.moveTime(obj6, l10));
                        obj5 = Long.valueOf(l10);
                        if (obj5 == null)
                        {
                            throw new NullPointerException();
                        } else
                        {
                            settablefuture1.setFuture((ListenableFuture)function.apply(new Present(obj5)));
                            return;
                        }
                    }

            .Lambda._cls2(Object obj, long l, SettableFuture settablefuture, Function function)
            {
                arg$1 = ColumnOnDragListener.this;
                arg$2 = obj;
                arg$3 = l;
                arg$4 = settablefuture;
                arg$5 = function;
            }
                }

                view = store.updateStore(new .Lambda._cls2(view, l4, settablefuture, dragevent));
                obj2.getClass();
                class .Lambda._cls3
                    implements Runnable
                {

                    private final com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker.OnGoingEvent arg$1;

                    public final void run()
                    {
                        arg$1.onComplete();
                    }

            .Lambda._cls3(com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker.OnGoingEvent ongoingevent)
            {
                arg$1 = ongoingevent;
            }
                }

                view.addListener(new .Lambda._cls3(((com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker.OnGoingEvent) (obj2))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            }
        }
        stateObservable.set(Absent.INSTANCE);
        idleEvent = null;
        storeBlockingFuture = null;
        rescheduleFinalizer = null;
        dragGuideManager.update(null);
        view = scrollPageController;
        j = 0;
        do
        {
            if (j >= ((DragScrollPageController) (view)).areas.length)
            {
                break;
            }
            dragevent = ((DragScrollPageController) (view)).areas[j];
            boolean flag;
            boolean flag1;
            if (0.0F > 0.0F)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (((com.google.android.calendar.timeline.dnd.DragScrollPageController.AreaState) (dragevent)).currentDepth > 0.0F)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag && !flag1)
            {
                dragevent.enterTime = SystemClock.uptimeMillis();
                com.google.android.calendar.timeline.dnd.DragScrollPageController.AreaState.HANDLER.postDelayed(((com.google.android.calendar.timeline.dnd.DragScrollPageController.AreaState) (dragevent)).callbackRunner, ((com.google.android.calendar.timeline.dnd.DragScrollPageController.AreaState) (dragevent)).callbackInterval);
                ((com.google.android.calendar.timeline.dnd.DragScrollPageController.AreaState) (dragevent)).callback.enterArea$514IILG_0();
            } else
            if (!flag && flag1)
            {
                com.google.android.calendar.timeline.dnd.DragScrollPageController.AreaState.HANDLER.removeCallbacks(((com.google.android.calendar.timeline.dnd.DragScrollPageController.AreaState) (dragevent)).callbackRunner);
                ((com.google.android.calendar.timeline.dnd.DragScrollPageController.AreaState) (dragevent)).callback.leaveArea$514IILG_0();
            }
            dragevent.currentDepth = 0.0F;
            j++;
        } while (true);
        viewportSubscription.cancel(false);
        viewportSubscription = null;
        originalEvent = null;
        return true;
    }

    final void onDragLocationUpdated(ColumnDragState.Builder builder, int i, int j)
    {
        curX = i;
        curY = j;
        builder.dragTimeFp16(pxToFp16(curX, curY));
        long l = (builder.dragTimeFp16() - builder.startDragTimeFp16() << 16) / QUARTER_HOUR_FP32;
        long l1 = QUARTER_HOUR_FP32;
        dragGuideManager.update(Long.valueOf((l * l1 >> 16) + originalEvent.getDisplayStartFp16()));
    }

    final void updateDraggedEvents(ColumnDragState.Builder builder)
    {
        long l2 = builder.dragTimeFp16() - builder.startDragTimeFp16();
        long l1 = l2;
        if (!builder.scrolling())
        {
            l1 = ((l2 << 16) / QUARTER_HOUR_FP32) * QUARTER_HOUR_FP32 >> 16;
        }
        l2 = originalEvent.getDisplayStartFp16();
        l2 = adjustDeltaFp16ForStart(l1) + l2;
        l1 = originalEvent.getDisplayEndFp16() + adjustDeltaFp16ForEnd(l1);
        int k = (int)(l2 >> 16);
        int l = (int)(FP16.fp16Ceil(l1) >> 16);
        ArrayList arraylist = new ArrayList((l - k) + 1);
        int i = k;
        while (i < l) 
        {
            long l3 = Math.min(Math.max((long)i << 16, l2), ((long)(i + 1) << 16) - MIN_CHIP_HEIGHT_FP16);
            long l4 = Math.max(Math.min((long)(i + 1) << 16, l1), ((long)i << 16) + MIN_CHIP_HEIGHT_FP16);
            CalendarViewType calendarviewtype = CalendarViewType.CREATE_EVENT;
            int j = originalEvent.getPosition();
            if (j >= calendarviewtype.minPosition && j <= calendarviewtype.maxPosition)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                j = originalEvent.getPosition();
            } else
            {
                j = timelineAdapter.getEventPosition(originalEvent, i - k);
            }
            arraylist.add(originalEvent.toBuilder().setGridTimedPosition(DRAG_EVENT_POSITION_ON_GRID).setPosition(j).setJulianDay(i).setDisplayStartFp16(l3).setDisplayEndFp16(l4).build());
            i++;
        }
        builder.events(arraylist);
    }


    private class _cls1
        implements com.google.android.calendar.timeline.dnd.DragScrollPageController.Delegate
    {

        private ListenableFuture pageFuture;
        private final ColumnOnDragListener this$0;

        public final void onPage(int i)
        {
            Object obj;
            if (dragMode == com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode.MOVE)
            {
                if (((Optional) (obj = (Optional)stateObservable.get())).isPresent() && pageFuture.isDone())
                {
                    Object obj1 = viewportController;
                    byte byte0;
                    if (((Boolean)isRtl.get()).booleanValue())
                    {
                        byte0 = -1;
                    } else
                    {
                        byte0 = 1;
                    }
                    pageFuture = ((ColumnViewportController) (obj1)).animateDragPage(byte0 * i);
                    obj1 = stateObservable;
                    obj = ((ColumnDragState)((Optional) (obj)).get()).toBuilder().pagingDirection(i).build();
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    } else
                    {
                        ((ObservableReference) (obj1)).set(new Present(obj));
                        return;
                    }
                }
            }
        }

        public final void onPageModeEnd()
        {
            Object obj;
            if (dragMode == com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode.MOVE)
            {
                if (((Optional) (obj = (Optional)stateObservable.get())).isPresent())
                {
                    ObservableReference observablereference = stateObservable;
                    obj = ((ColumnDragState)((Optional) (obj)).get()).toBuilder().pagingDirection(0).build();
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    } else
                    {
                        observablereference.set(new Present(obj));
                        return;
                    }
                }
            }
        }

        public final void onPageModeStart()
        {
        }

        public final void onScroll(int i)
        {
            ColumnViewportController columnviewportcontroller = viewportController;
            i = -i;
            ColumnViewport columnviewport = columnviewportcontroller.viewport;
            int j = columnviewportcontroller.viewport.gridTopMsOfDay;
            columnviewport.setGridTopMsOfDay(columnviewportcontroller.viewport.gridMsPerVerticalPixel * i + j);
        }

        public final void onScrollModeEnd()
        {
            Object obj = (Optional)stateObservable.get();
            if (((Optional) (obj)).isPresent())
            {
                ObservableReference observablereference = stateObservable;
                obj = ((ColumnDragState)((Optional) (obj)).get()).toBuilder().scrolling(false).build();
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                observablereference.set(new Present(obj));
            }
        }

        public final void onScrollModeStart()
        {
            Object obj = (Optional)stateObservable.get();
            if (((Optional) (obj)).isPresent())
            {
                ObservableReference observablereference = stateObservable;
                obj = ((ColumnDragState)((Optional) (obj)).get()).toBuilder().scrolling(true).build();
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                observablereference.set(new Present(obj));
            }
        }

        _cls1()
        {
            this$0 = ColumnOnDragListener.this;
            super();
            columnondraglistener = ((ColumnOnDragListener) (new Object()));
            if (ColumnOnDragListener.this == null)
            {
                columnondraglistener = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                columnondraglistener = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(ColumnOnDragListener.this);
            }
            pageFuture = ColumnOnDragListener.this;
        }
    }

}
