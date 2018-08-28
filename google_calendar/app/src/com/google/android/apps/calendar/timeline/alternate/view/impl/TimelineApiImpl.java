// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.ScaleGestureDetector;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.CreationMode;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.Layout;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutManager;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.ColumnLayout;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month.MonthLayout;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule.ScheduleLayout;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import dagger.Lazy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            RecyclerViewAccessibilityDelegateHelper

public class TimelineApiImpl
    implements TimelineApi
{

    public static final Lazy NOOP_ON_DRAG_LISTENER;
    public static final Lazy NOOP_ON_TOUCH_LISTENER;
    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/timeline/alternate/view/impl/TimelineApiImpl);
    private final Lazy columnBackground;
    public final Lazy columnLayout;
    private final Lazy columnOnDragListener;
    private final Lazy columnOnTapListener;
    public final ObservableReference createEventPhantom;
    public final CreationMode creationMode;
    private final ObservableReference currentTime;
    private final Point dragOffset;
    private int julianDay;
    public Long lastCurrentTime;
    private Lazy layout;
    public final LayoutManager layoutManager;
    private final Lazy monthBackground;
    private final Lazy monthLayout;
    public android.view.View.OnDragListener onDragListener;
    public android.view.View.OnTouchListener onTapListener;
    public final Set pendingDisableClipRequests = new HashSet();
    public final RecyclerView recyclerView;
    public final Lazy scheduleLayout;
    private final Subscription subscriptions;
    public final TimeUtils timeUtils;
    private FluentFuture transitionAnimation;

    public TimelineApiImpl(RecyclerView recyclerview, android.support.v7.widget.RecyclerView.LayoutManager layoutmanager, final LayoutManager layoutManager, TimeUtils timeutils, ObservableReference observablereference, ObservableReference observablereference1, Lazy lazy, 
            Lazy lazy1, Lazy lazy2, Lazy lazy3, ObservableReference observablereference2, CreationMode creationmode, ObservableReference observablereference3, CalendarContentStore calendarcontentstore, 
            Lazy lazy4, Lazy lazy5, Lazy lazy6, android.support.v7.widget.RecyclerView.Adapter adapter, Point point, RecyclerViewAccessibilityDelegateHelper recyclerviewaccessibilitydelegatehelper, ObservableReference observablereference4)
    {
        layout = null;
        Object obj = new Object();
        class .Lambda._cls4
            implements android.view.View.OnTouchListener
        {

            private final TimelineApiImpl arg$1;
            private final ScaleGestureDetector arg$2;
            private final RecyclerView arg$3;
            private final GestureDetector arg$4;

            public final boolean onTouch(View view, MotionEvent motionevent)
            {
                TimelineApiImpl timelineapiimpl = arg$1;
                ScaleGestureDetector scalegesturedetector = arg$2;
                RecyclerView recyclerview1 = arg$3;
                GestureDetector gesturedetector = arg$4;
                scalegesturedetector.onTouchEvent(motionevent);
                if (recyclerview1.getScrollState() == 0 && gesturedetector.onTouchEvent(motionevent))
                {
                    timelineapiimpl.onTapListener.onTouch(recyclerview1, motionevent);
                    view.performClick();
                    return true;
                }
                if (timelineapiimpl.creationMode.tapToDismiss && ((Optional)timelineapiimpl.createEventPhantom.get()).isPresent())
                {
                    timelineapiimpl.createEventPhantom.set(Absent.INSTANCE);
                }
                return false;
            }

            .Lambda._cls4(ScaleGestureDetector scalegesturedetector, RecyclerView recyclerview, GestureDetector gesturedetector)
            {
                arg$1 = TimelineApiImpl.this;
                arg$2 = scalegesturedetector;
                arg$3 = recyclerview;
                arg$4 = gesturedetector;
            }
        }

        class .Lambda._cls0
            implements Consumer
        {

            private final TimelineApiImpl arg$1;
            private final ObservableReference arg$2;
            private final ObservableReference arg$3;

            public final void accept(Object obj1)
            {
                obj1 = arg$1;
                ObservableReference observablereference5 = arg$2;
                ObservableReference observablereference6 = arg$3;
                if (((Boolean)observablereference5.get()).booleanValue() && !((TimelineApiImpl) (obj1)).lastCurrentTime.equals(observablereference6.get()))
                {
                    obj1.lastCurrentTime = (Long)observablereference6.get();
                    ((TimelineApiImpl) (obj1)).layoutManager.getLayout().invalidateCache();
                    ((TimelineApiImpl) (obj1)).recyclerView.mAdapter.mObservable.notifyChanged();
                    ((TimelineApiImpl) (obj1)).recyclerView.invalidate();
                    ((TimelineApiImpl) (obj1)).recyclerView.requestLayout();
                }
            }

            .Lambda._cls0(ObservableReference observablereference, ObservableReference observablereference1)
            {
                arg$1 = TimelineApiImpl.this;
                arg$2 = observablereference;
                arg$3 = observablereference1;
            }
        }

        class .Lambda._cls1
            implements Consumer
        {

            private final TimelineApiImpl arg$1;

            public final void accept(Object obj1)
            {
                TimelineApiImpl timelineapiimpl = arg$1;
                if (((Boolean)obj1).booleanValue())
                {
                    timelineapiimpl.recyclerView.requestLayout();
                }
            }

            .Lambda._cls1()
            {
                arg$1 = TimelineApiImpl.this;
            }
        }

        if (obj == null)
        {
            obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
        }
        if (obj instanceof FluentFuture)
        {
            obj = (FluentFuture)obj;
        } else
        {
            obj = new ForwardingFluentFuture(((com.google.common.util.concurrent.ListenableFuture) (obj)));
        }
        transitionAnimation = ((FluentFuture) (obj));
        recyclerView = recyclerview;
        this.layoutManager = layoutManager;
        timeUtils = timeutils;
        scheduleLayout = lazy4;
        columnLayout = lazy;
        columnBackground = lazy1;
        columnOnDragListener = lazy2;
        columnOnTapListener = lazy3;
        createEventPhantom = observablereference2;
        creationMode = creationmode;
        monthLayout = lazy5;
        monthBackground = lazy6;
        dragOffset = point;
        currentTime = observablereference;
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setAdapter(adapter);
        recyclerview.setOnTouchListener(new .Lambda._cls4(new ScaleGestureDetector(recyclerview.getContext(), new _cls2()), recyclerview, new GestureDetector(recyclerview.getContext(), new _cls3())));
        recyclerview.mAccessibilityDelegate = recyclerviewaccessibilitydelegatehelper;
        ViewCompat.setAccessibilityDelegate(recyclerview, recyclerview.mAccessibilityDelegate);
        recyclerview.mOnFlingListener = new _cls1();
        lastCurrentTime = (Long)observablereference.get();
        recyclerview = new .Lambda._cls0(observablereference1, observablereference);
        observablereference.subscribe(recyclerview, new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), false);
        observablereference1.subscribe(recyclerview, new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), false);
        julianDay = timeutils.msToJulianDate(((Long)observablereference.get()).longValue());
        subscriptions = new com.google.android.apps.calendar.util.concurrent.Subscription..Lambda._cls0(Arrays.asList(new Subscription[] {
            calendarcontentstore.subscribeToViewport(observablereference3), observablereference4.subscribe(new .Lambda._cls1(), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), false)
        }));
    }

    static final void lambda$disableChildClipping$10$TimelineApiImpl(Runnable runnable)
    {
        (new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN)).execute(runnable);
    }

    static final boolean lambda$static$0$TimelineApiImpl$51662RJ4E9NMIP1FEPKMATPFAPKMATPR9HGMSP3IDTKM8BRMD5INEBQ4E9GMEHBMCLN78EP9B8______0()
    {
        return false;
    }

    static final android.view.View.OnDragListener lambda$static$1$TimelineApiImpl()
    {
        class .Lambda._cls8
            implements android.view.View.OnDragListener
        {

            public static final android.view.View.OnDragListener $instance = new .Lambda._cls8();

            public final boolean onDrag(View view, DragEvent dragevent)
            {
                return TimelineApiImpl.lambda$static$0$TimelineApiImpl$51662RJ4E9NMIP1FEPKMATPFAPKMATPR9HGMSP3IDTKM8BRMD5INEBQ4E9GMEHBMCLN78EP9B8______0();
            }


            private .Lambda._cls8()
            {
            }
        }

        return .Lambda._cls8..instance;
    }

    static final boolean lambda$static$2$TimelineApiImpl$51662RJ4E9NMIP1FEPKMATPFAPKMATPR9HGMSP3IDTKM8BRMD5INEBQDDTQ6IRRE8LR6ARJK7CKLK___0()
    {
        return false;
    }

    static final android.view.View.OnTouchListener lambda$static$3$TimelineApiImpl()
    {
        class .Lambda._cls7
            implements android.view.View.OnTouchListener
        {

            public static final android.view.View.OnTouchListener $instance = new .Lambda._cls7();

            public final boolean onTouch(View view, MotionEvent motionevent)
            {
                return TimelineApiImpl.lambda$static$2$TimelineApiImpl$51662RJ4E9NMIP1FEPKMATPFAPKMATPR9HGMSP3IDTKM8BRMD5INEBQDDTQ6IRRE8LR6ARJK7CKLK___0();
            }


            private .Lambda._cls7()
            {
            }
        }

        return .Lambda._cls7..instance;
    }

    public final Runnable disableChildClipping()
    {
        _cls4 _lcls4 = new _cls4();
        pendingDisableClipRequests.add(_lcls4);
        recyclerView.setClipChildren(false);
        class .Lambda._cls6
            implements Runnable
        {

            private final Runnable arg$1;

            public final void run()
            {
                TimelineApiImpl.lambda$disableChildClipping$10$TimelineApiImpl(arg$1);
            }

            .Lambda._cls6(Runnable runnable)
            {
                arg$1 = runnable;
            }
        }

        return new .Lambda._cls6(_lcls4);
    }

    public final android.view.View.OnDragListener getOnDragListener()
    {
        class .Lambda._cls5
            implements android.view.View.OnDragListener
        {

            private final TimelineApiImpl arg$1;

            public final boolean onDrag(View view, DragEvent dragevent)
            {
                return arg$1.onDragListener.onDrag(view, dragevent);
            }

            .Lambda._cls5()
            {
                arg$1 = TimelineApiImpl.this;
            }
        }

        return new .Lambda._cls5();
    }

    public final View getView()
    {
        return recyclerView;
    }

    public final ViewMode getViewMode()
    {
        return layoutManager.getLayout().getViewMode();
    }

    public final void goToDay(int i)
    {
        recyclerView.stopScroll();
        julianDay = i;
        layoutManager.getLayout().goToDay(i);
    }

    public final void goToNow(boolean flag)
    {
        recyclerView.stopScroll();
        long l = ((Long)currentTime.get()).longValue();
        julianDay = timeUtils.msToJulianDate(l);
        layoutManager.getLayout().goToNow(flag);
    }

    public final void goToTime(long l)
    {
        recyclerView.stopScroll();
        julianDay = timeUtils.msToJulianDate(l);
        layoutManager.getLayout().goToTime(l);
    }

    public final void onDestroy()
    {
        subscriptions.cancel(false);
    }

    public final void setDragOffset(int i, int j)
    {
        dragOffset.set(i, j);
    }

    final void setLayout(Lazy lazy, Lazy lazy1, Lazy lazy2, Lazy lazy3)
    {
        layout = lazy;
        layoutManager.setLayout((Layout)lazy.get());
        RecyclerView recyclerview = recyclerView;
        if (lazy1 == null)
        {
            lazy = null;
        } else
        {
            lazy = (Drawable)lazy1.get();
        }
        recyclerview.setBackground(lazy);
        onDragListener = (android.view.View.OnDragListener)lazy2.get();
        onTapListener = (android.view.View.OnTouchListener)lazy3.get();
    }

    public final void showGridLayout(int i, int j, boolean flag)
    {
        julianDay = j;
        recyclerView.stopScroll();
        if (layout == null)
        {
            setLayout(columnLayout, columnBackground, columnOnDragListener, columnOnTapListener);
            FluentFuture fluentfuture = ((ColumnLayout)columnLayout.get()).onShow(julianDay, i, flag);
            transitionAnimation.cancel(true);
            transitionAnimation = fluentfuture;
            return;
        }
        if (layout == columnLayout)
        {
            if (flag)
            {
                ((ColumnLayout)columnLayout.get()).setNumDaysAndStart(i, j);
                return;
            } else
            {
                ((ColumnLayout)columnLayout.get()).onHide();
                FluentFuture fluentfuture1 = ((ColumnLayout)columnLayout.get()).onShow(j, i, flag);
                transitionAnimation.cancel(true);
                transitionAnimation = fluentfuture1;
                return;
            }
        }
        if (layout == scheduleLayout)
        {
            ((ScheduleLayout)scheduleLayout.get()).onHide();
            setLayout(columnLayout, columnBackground, columnOnDragListener, columnOnTapListener);
            if (flag)
            {
                class .Lambda._cls3
                    implements AsyncFunction
                {

                    private final TimelineApiImpl arg$1;
                    private final int arg$2;
                    private final int arg$3;

                    public final ListenableFuture apply(Object obj)
                    {
                        obj = arg$1;
                        int k = arg$2;
                        int l = arg$3;
                        return ((ColumnLayout)((TimelineApiImpl) (obj)).columnLayout.get()).setNumDaysAndStart(k, l);
                    }

            .Lambda._cls3(int i, int j)
            {
                arg$1 = TimelineApiImpl.this;
                arg$2 = i;
                arg$3 = j;
            }
                }

                FluentFuture fluentfuture2 = (FluentFuture)AbstractTransformFuture.create(((ColumnLayout)columnLayout.get()).onShow(j, 1, flag), new .Lambda._cls3(i, j), CalendarExecutor.MAIN);
                transitionAnimation.cancel(true);
                transitionAnimation = fluentfuture2;
                return;
            } else
            {
                FluentFuture fluentfuture3 = ((ColumnLayout)columnLayout.get()).onShow(j, i, flag);
                transitionAnimation.cancel(true);
                transitionAnimation = fluentfuture3;
                return;
            }
        }
        if (layout == monthLayout)
        {
            ((MonthLayout)monthLayout.get()).onHide();
            setLayout(columnLayout, columnBackground, columnOnDragListener, columnOnTapListener);
            FluentFuture fluentfuture4 = ((ColumnLayout)columnLayout.get()).onShow(julianDay, i, flag);
            transitionAnimation.cancel(true);
            transitionAnimation = fluentfuture4;
            return;
        } else
        {
            LogUtils.wtf(TAG, "Illegal layout: %s", new Object[] {
                layout
            });
            return;
        }
    }

    public final void showMonthLayout(Integer integer, boolean flag)
    {
        if (integer != null)
        {
            julianDay = integer.intValue();
        }
        if (layout == columnLayout)
        {
            ((ColumnLayout)columnLayout.get()).onHide();
            recyclerView.requestLayout();
        } else
        if (layout == scheduleLayout)
        {
            ((ScheduleLayout)scheduleLayout.get()).onHide();
            recyclerView.requestLayout();
        } else
        {
            String s = TAG;
            if (layout == monthLayout || layout == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            LogUtils.checkStateOrWtf(s, flag);
        }
        if (layout == monthLayout)
        {
            ((MonthLayout)monthLayout.get()).goToDay(integer.intValue());
            return;
        } else
        {
            integer = ((MonthLayout)monthLayout.get()).onShow$514LKAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQ6DHQMARJK8PQN8TBICKTG____0(julianDay);
            transitionAnimation.cancel(true);
            transitionAnimation = integer;
            setLayout(monthLayout, monthBackground, NOOP_ON_DRAG_LISTENER, NOOP_ON_TOUCH_LISTENER);
            return;
        }
    }

    public final void showScheduleLayout(int i, boolean flag)
    {
        julianDay = i;
        if (layout == null)
        {
            setLayout(scheduleLayout, null, NOOP_ON_DRAG_LISTENER, NOOP_ON_TOUCH_LISTENER);
            FluentFuture fluentfuture = ((ScheduleLayout)scheduleLayout.get()).onShow(timeUtils.julianDateToMs(julianDay), flag);
            transitionAnimation.cancel(true);
            transitionAnimation = fluentfuture;
            return;
        }
        if (layout == columnLayout)
        {
            recyclerView.stopScroll();
            if (flag)
            {
                class .Lambda._cls2
                    implements AsyncFunction
                {

                    private final TimelineApiImpl arg$1;
                    private final int arg$2;
                    private final boolean arg$3;

                    public final ListenableFuture apply(Object obj)
                    {
                        obj = arg$1;
                        int j = arg$2;
                        boolean flag1 = arg$3;
                        ((ColumnLayout)((TimelineApiImpl) (obj)).columnLayout.get()).onHide();
                        ((TimelineApiImpl) (obj)).setLayout(((TimelineApiImpl) (obj)).scheduleLayout, null, TimelineApiImpl.NOOP_ON_DRAG_LISTENER, TimelineApiImpl.NOOP_ON_TOUCH_LISTENER);
                        return ((ScheduleLayout)((TimelineApiImpl) (obj)).scheduleLayout.get()).onShow(((TimelineApiImpl) (obj)).timeUtils.julianDateToMs(j), flag1);
                    }

            .Lambda._cls2(int i, boolean flag)
            {
                arg$1 = TimelineApiImpl.this;
                arg$2 = i;
                arg$3 = flag;
            }
                }

                FluentFuture fluentfuture1 = (FluentFuture)AbstractTransformFuture.create(((ColumnLayout)columnLayout.get()).setNumDaysAndStart(1, i), new .Lambda._cls2(i, flag), CalendarExecutor.MAIN);
                transitionAnimation.cancel(true);
                transitionAnimation = fluentfuture1;
                return;
            } else
            {
                ((ColumnLayout)columnLayout.get()).onHide();
                setLayout(scheduleLayout, null, NOOP_ON_DRAG_LISTENER, NOOP_ON_TOUCH_LISTENER);
                FluentFuture fluentfuture2 = ((ScheduleLayout)scheduleLayout.get()).onShow(timeUtils.julianDateToMs(i), flag);
                transitionAnimation.cancel(true);
                transitionAnimation = fluentfuture2;
                return;
            }
        }
        if (layout == monthLayout)
        {
            ((MonthLayout)monthLayout.get()).onHide();
            FluentFuture fluentfuture3 = ((ScheduleLayout)scheduleLayout.get()).onShow(timeUtils.julianDateToMs(julianDay), flag);
            transitionAnimation.cancel(true);
            transitionAnimation = fluentfuture3;
            setLayout(scheduleLayout, null, NOOP_ON_DRAG_LISTENER, NOOP_ON_TOUCH_LISTENER);
            recyclerView.requestLayout();
            return;
        }
        String s = TAG;
        if (layout == scheduleLayout)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        LogUtils.checkStateOrWtf(s, flag);
        ((ScheduleLayout)scheduleLayout.get()).goToDay(i);
    }

    static 
    {
        class .Lambda._cls9
            implements Lazy
        {

            public static final Lazy $instance = new .Lambda._cls9();

            public final Object get()
            {
                return TimelineApiImpl.lambda$static$1$TimelineApiImpl();
            }


            private .Lambda._cls9()
            {
            }
        }

        NOOP_ON_DRAG_LISTENER = .Lambda._cls9..instance;
        class .Lambda._cls10
            implements Lazy
        {

            public static final Lazy $instance = new .Lambda._cls10();

            public final Object get()
            {
                return TimelineApiImpl.lambda$static$3$TimelineApiImpl();
            }


            private .Lambda._cls10()
            {
            }
        }

        NOOP_ON_TOUCH_LISTENER = .Lambda._cls10..instance;
    }

    private class _cls2
        implements android.view.ScaleGestureDetector.OnScaleGestureListener
    {

        private final LayoutManager val$layoutManager;

        public final boolean onScale(ScaleGestureDetector scalegesturedetector)
        {
            Layout layout1 = layoutManager.getLayout();
            float f = scalegesturedetector.getCurrentSpanX();
            float f1 = scalegesturedetector.getCurrentSpanY();
            scalegesturedetector.getPreviousSpanX();
            return layout1.onScale$5134CHI655D0____0(f, f1, scalegesturedetector.getPreviousSpanY());
        }

        public final boolean onScaleBegin(ScaleGestureDetector scalegesturedetector)
        {
            return layoutManager.getLayout().onScaleBegin();
        }

        public final void onScaleEnd(ScaleGestureDetector scalegesturedetector)
        {
            layoutManager.getLayout().onScaleEnd();
        }

        _cls2()
        {
            layoutManager = layoutmanager;
            super();
        }
    }


    private class _cls3 extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public final boolean onSingleTapUp(MotionEvent motionevent)
        {
            return true;
        }

        _cls3()
        {
        }
    }


    private class _cls1 extends android.support.v7.widget.RecyclerView.OnFlingListener
    {

        private final LayoutManager val$layoutManager;

        public final boolean onFling(int i, int j)
        {
            return layoutManager.getLayout().fling(i, j);
        }

        _cls1()
        {
            layoutManager = layoutmanager;
            super();
        }
    }


    private class _cls4
        implements Runnable
    {

        private final TimelineApiImpl this$0;

        public final void run()
        {
            if (!pendingDisableClipRequests.remove(this))
            {
                LogUtils.wtf(TimelineApiImpl.TAG, "Clip request cancelled multipled times", new Object[0]);
            }
            if (pendingDisableClipRequests.isEmpty())
            {
                recyclerView.setClipChildren(true);
            }
        }

        _cls4()
        {
            this$0 = TimelineApiImpl.this;
            super();
        }
    }

}
