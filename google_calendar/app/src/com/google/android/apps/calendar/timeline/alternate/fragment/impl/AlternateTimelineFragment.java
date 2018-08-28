// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.time.clock.Clock;
import com.google.common.base.Absent;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.android.support.AndroidSupportInjection;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            AutoValue_AlternateTimelineFragment_State, TimeUpdater

public class AlternateTimelineFragment extends Fragment
    implements CalendarFragment
{
    static abstract class State
        implements Parcelable
    {

        abstract Integer requestedJulianDay();

        abstract com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewType();

        State()
        {
        }
    }


    public TimelineApi controller;
    public final Point dragOffset;
    public ObservableReference isPortrait;
    public ObservableReference phantom;
    public ObservableReference screenType;
    private State state;
    private Long stopTime;
    public TimeUpdater timeUpdater;

    public AlternateTimelineFragment()
    {
        dragOffset = new Point();
    }

    public AlternateTimelineFragment(com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewtype, int i)
    {
        dragOffset = new Point();
        state = new AutoValue_AlternateTimelineFragment_State(viewtype, Integer.valueOf(i));
    }

    private final void showView(com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewtype, int i, boolean flag)
    {
        switch (viewtype.ordinal())
        {
        default:
            return;

        case 3: // '\003'
            controller.showScheduleLayout(i, flag);
            return;

        case 0: // '\0'
            controller.showGridLayout(1, i, flag);
            return;

        case 1: // '\001'
            controller.showGridLayout(3, i, flag);
            return;

        case 2: // '\002'
            controller.showGridLayout(7, i, flag);
            return;

        case 4: // '\004'
            controller.showMonthLayout(Integer.valueOf(i), flag);
            break;
        }
    }

    public final void clearCreatePhantom()
    {
        phantom.set(Absent.INSTANCE);
    }

    public final com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType getViewType()
    {
        return state.viewType();
    }

    public final void goToDay(int i)
    {
        if (controller != null)
        {
            controller.goToDay(i);
        }
    }

    public final void goToNow()
    {
        if (controller != null)
        {
            controller.goToNow(true);
        }
    }

    public final void goToTime(long l)
    {
        if (controller != null)
        {
            controller.goToTime(l);
        }
    }

    public final void onAttach(Context context)
    {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (state == null)
        {
            if (bundle == null)
            {
                throw new NullPointerException();
            }
            state = (State)((Bundle)bundle).getParcelable("STATE");
        }
        if (bundle != null && bundle.containsKey("STOP_TIME"))
        {
            stopTime = Long.valueOf(bundle.getLong("STOP_TIME"));
        }
        class .Lambda._cls0
            implements android.view.View.OnLayoutChangeListener
        {

            private final AlternateTimelineFragment arg$1;
            private final ViewGroup arg$2;

            public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
                    int k1, int l1)
            {
                AlternateTimelineFragment alternatetimelinefragment = arg$1;
                ViewGroup viewgroup = arg$2;
                alternatetimelinefragment.dragOffset.set(0, 0);
                view = alternatetimelinefragment.controller.getView();
                if (ViewCompat.isAttachedToWindow(view))
                {
                    for (; view != viewgroup; view = (View)view.getParent())
                    {
                        alternatetimelinefragment.dragOffset.offset(view.getLeft(), view.getTop());
                    }

                    alternatetimelinefragment.controller.setDragOffset(alternatetimelinefragment.dragOffset.x, alternatetimelinefragment.dragOffset.y);
                }
            }

            .Lambda._cls0(ViewGroup viewgroup)
            {
                arg$1 = AlternateTimelineFragment.this;
                arg$2 = viewgroup;
            }
        }

        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        bundle = (ViewGroup)bundle.getWindow().getDecorView();
        bundle.setOnDragListener(controller.getOnDragListener());
        bundle.addOnLayoutChangeListener(new .Lambda._cls0(bundle));
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        PerformanceMetricCollector performancemetriccollector;
        viewgroup = state.viewType();
        if (screenType.get() == ScreenType.PHONE && !((Boolean)isPortrait.get()).booleanValue())
        {
            viewgroup = com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.WEEK;
        }
        if (bundle != null)
        {
            bundle = "Recreated";
        } else
        {
            bundle = "Created";
        }
        layoutinflater = PerformanceMetricCollectorHolder.instance;
        if (layoutinflater == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        }
        performancemetriccollector = (PerformanceMetricCollector)layoutinflater;
        viewgroup.ordinal();
        JVM INSTR tableswitch 0 4: default 124
    //                   0 188
    //                   1 195
    //                   2 202
    //                   3 134
    //                   4 209;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        throw new IllegalStateException("Unknown view type");
_L5:
        layoutinflater = "ScheduleView";
_L8:
        performancemetriccollector.recordMemory(String.format(null, "%s.%s", new Object[] {
            layoutinflater, bundle
        }));
        showView(viewgroup, state.requestedJulianDay().intValue(), false);
        return controller.getView();
_L2:
        layoutinflater = "1DayView";
        continue; /* Loop/switch isn't completed */
_L3:
        layoutinflater = "3DayView";
        continue; /* Loop/switch isn't completed */
_L4:
        layoutinflater = "WeekView";
        continue; /* Loop/switch isn't completed */
_L6:
        layoutinflater = "MonthView";
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final void onDestroy()
    {
        super.onDestroy();
        controller.onDestroy();
    }

    public final void onDestroyView()
    {
        Object obj;
        PerformanceMetricCollector performancemetriccollector;
        obj = state.viewType();
        performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        }
        performancemetriccollector = (PerformanceMetricCollector)performancemetriccollector;
        ((com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType) (obj)).ordinal();
        JVM INSTR tableswitch 0 4: default 72
    //                   0 116
    //                   1 123
    //                   2 130
    //                   3 82
    //                   4 137;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        throw new IllegalStateException("Unknown view type");
_L5:
        obj = "ScheduleView";
_L8:
        performancemetriccollector.recordMemory(String.format(null, "%s.%s", new Object[] {
            obj, "Destroyed"
        }));
        super.onDestroyView();
        return;
_L2:
        obj = "1DayView";
        continue; /* Loop/switch isn't completed */
_L3:
        obj = "3DayView";
        continue; /* Loop/switch isn't completed */
_L4:
        obj = "WeekView";
        continue; /* Loop/switch isn't completed */
_L6:
        obj = "MonthView";
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final void onPause()
    {
        TimeUpdater timeupdater = timeUpdater;
        timeupdater.future.cancel(true);
        timeupdater.future = null;
        super.onPause();
    }

    public final void onResume()
    {
        super.onResume();
        TimeUpdater timeupdater = timeUpdater;
        boolean flag;
        if (timeupdater.future == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Needs to be stopped before it can be started."));
        } else
        {
            CalendarExecutor calendarexecutor = CalendarExecutor.BACKGROUND;
            TimeUpdater..Lambda._cls0 _lcls0 = new TimeUpdater..Lambda._cls0(timeupdater);
            long l = TimeUpdater.UPDATE_PERIOD;
            TimeUnit timeunit = TimeUnit.MILLISECONDS;
            timeupdater.future = calendarexecutor.getDelegate().scheduleAtFixedRate(_lcls0, 0L, l, timeunit);
            return;
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("STATE", state);
        if (stopTime != null)
        {
            bundle.putLong("STOP_TIME", stopTime.longValue());
        }
    }

    public final void onStart()
    {
        super.onStart();
        if (stopTime != null)
        {
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            if (l - stopTime.longValue() >= TimeUnit.MINUTES.toMillis(15L))
            {
                controller.goToNow(false);
            }
            stopTime = null;
        }
    }

    public final void onStop()
    {
        super.onStop();
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        stopTime = Long.valueOf(l);
    }

    public final CalendarFragment onSwitchView(com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewtype, int i, boolean flag)
    {
        state = new AutoValue_AlternateTimelineFragment_State(viewtype, Integer.valueOf(i));
        if (controller == null || super.mView == null) goto _L2; else goto _L1
_L1:
        PerformanceMetricCollector performancemetriccollector1;
        PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        }
        performancemetriccollector1 = (PerformanceMetricCollector)performancemetriccollector;
        viewtype.ordinal();
        JVM INSTR tableswitch 0 4: default 100
    //                   0 151
    //                   1 159
    //                   2 167
    //                   3 110
    //                   4 175;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        throw new IllegalStateException("Unknown view type");
_L7:
        String s = "ScheduleView";
_L10:
        performancemetriccollector1.recordMemory(String.format(null, "%s.%s", new Object[] {
            s, "Transitioned"
        }));
        showView(viewtype, i, flag);
_L2:
        return this;
_L4:
        s = "1DayView";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "3DayView";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "WeekView";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "MonthView";
        if (true) goto _L10; else goto _L9
_L9:
    }
}
