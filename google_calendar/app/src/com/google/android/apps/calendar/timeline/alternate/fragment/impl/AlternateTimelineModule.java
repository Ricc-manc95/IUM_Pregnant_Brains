// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.app.Activity;
import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.ViewModeChangeListener;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.timeline.chip.ChipFactory;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;

public class AlternateTimelineModule
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/timeline/alternate/fragment/impl/AlternateTimelineModule);

    public AlternateTimelineModule()
    {
    }

    static final void lambda$providesDayHeaderClickListener$1$AlternateTimelineModule(TimelineApi timelineapi, int i, Context context, ViewModeChangeListener viewmodechangelistener, ViewMode viewmode)
    {
        if (viewmode == ViewMode.ONE_DAY_GRID)
        {
            timelineapi.showGridLayout(1, i, true);
            timelineapi = AnalyticsLoggerHolder.instance;
            if (timelineapi == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)timelineapi).trackEvent(context, "menu_item", "day_toggle");
            timelineapi = com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.DAY;
        } else
        if (viewmode == ViewMode.SCHEDULE)
        {
            timelineapi.showScheduleLayout(i, true);
            timelineapi = AnalyticsLoggerHolder.instance;
            if (timelineapi == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)timelineapi).trackEvent(context, "menu_item", "agenda_toggle");
            timelineapi = com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.AGENDA;
        } else
        {
            LogUtils.wtf(TAG, "Unexpected view type: %s", new Object[] {
                viewmode
            });
            return;
        }
        viewmodechangelistener.onViewModeChanged(timelineapi);
        context = PerformanceMetricCollectorHolder.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        }
        context = (PerformanceMetricCollector)context;
        timelineapi.ordinal();
        JVM INSTR tableswitch 0 4: default 204
    //                   0 243
    //                   1 249
    //                   2 255
    //                   3 214
    //                   4 261;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        throw new IllegalStateException("Unknown view type");
_L5:
        timelineapi = "ScheduleView";
_L8:
        context.recordMemory(String.format(null, "%s.%s", new Object[] {
            timelineapi, "Transitioned"
        }));
        return;
_L2:
        timelineapi = "1DayView";
        continue; /* Loop/switch isn't completed */
_L3:
        timelineapi = "3DayView";
        continue; /* Loop/switch isn't completed */
_L4:
        timelineapi = "WeekView";
        continue; /* Loop/switch isn't completed */
_L6:
        timelineapi = "MonthView";
        if (true) goto _L8; else goto _L7
_L7:
    }

    static final void lambda$providesDayHeaderClickListener$2$AlternateTimelineModule(com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier dayheadernextmodesupplier, TimelineApi timelineapi, Context context, ViewModeChangeListener viewmodechangelistener, int i)
    {
        dayheadernextmodesupplier = dayheadernextmodesupplier.nextViewMode();
        class .Lambda._cls2
            implements Consumer
        {

            private final TimelineApi arg$1;
            private final int arg$2;
            private final Context arg$3;
            private final ViewModeChangeListener arg$4;

            public final void accept(Object obj)
            {
                AlternateTimelineModule.lambda$providesDayHeaderClickListener$1$AlternateTimelineModule(arg$1, arg$2, arg$3, arg$4, (ViewMode)obj);
            }

            .Lambda._cls2(TimelineApi timelineapi, int i, Context context, ViewModeChangeListener viewmodechangelistener)
            {
                arg$1 = timelineapi;
                arg$2 = i;
                arg$3 = context;
                arg$4 = viewmodechangelistener;
            }
        }

        timelineapi = new .Lambda._cls2(timelineapi, i, context, viewmodechangelistener);
        context = com.google.android.apps.calendar.util.Optionals..Lambda._cls0.$instance;
        dayheadernextmodesupplier = ((com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier) (dayheadernextmodesupplier.orNull()));
        if (dayheadernextmodesupplier != null)
        {
            timelineapi.accept(dayheadernextmodesupplier);
            return;
        } else
        {
            context.run();
            return;
        }
    }

    static final Optional lambda$providesDayHeaderNextModeSupplier$0$AlternateTimelineModule(ObservableReference observablereference, ObservableReference observablereference1, TimelineApi timelineapi, Context context)
    {
        boolean flag;
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && !((Boolean)observablereference1.get()).booleanValue())
        {
            return Absent.INSTANCE;
        }
        timelineapi = timelineapi.getViewMode();
        if (timelineapi == ViewMode.MULTI_DAY_GRID)
        {
            boolean flag1;
            if ((ScreenType)observablereference.get() == ScreenType.PHONE)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 || ((Boolean)observablereference1.get()).booleanValue())
            {
                if (PreferencesUtils.getPrefersGridMode(context))
                {
                    observablereference = ViewMode.ONE_DAY_GRID;
                    if (observablereference == null)
                    {
                        throw new NullPointerException();
                    } else
                    {
                        return new Present(observablereference);
                    }
                }
                observablereference = ViewMode.SCHEDULE;
                if (observablereference == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return new Present(observablereference);
                }
            }
        } else
        {
            if (timelineapi == ViewMode.ONE_DAY_GRID)
            {
                observablereference = ViewMode.SCHEDULE;
                if (observablereference == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return new Present(observablereference);
                }
            }
            if (timelineapi == ViewMode.SCHEDULE)
            {
                observablereference = ViewMode.ONE_DAY_GRID;
                if (observablereference == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return new Present(observablereference);
                }
            }
        }
        return Absent.INSTANCE;
    }

    static ChipFactory providesChipFactory(Activity activity)
    {
        return ChipFactory.getInstance(activity);
    }

    static com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderClickListener providesDayHeaderClickListener(Context context, TimelineApi timelineapi, ViewModeChangeListener viewmodechangelistener, com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier dayheadernextmodesupplier)
    {
        class .Lambda._cls1
            implements com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderClickListener
        {

            private final com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier arg$1;
            private final TimelineApi arg$2;
            private final Context arg$3;
            private final ViewModeChangeListener arg$4;

            public final void onClick(int i)
            {
                AlternateTimelineModule.lambda$providesDayHeaderClickListener$2$AlternateTimelineModule(arg$1, arg$2, arg$3, arg$4, i);
            }

            .Lambda._cls1(com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier dayheadernextmodesupplier, TimelineApi timelineapi, Context context, ViewModeChangeListener viewmodechangelistener)
            {
                arg$1 = dayheadernextmodesupplier;
                arg$2 = timelineapi;
                arg$3 = context;
                arg$4 = viewmodechangelistener;
            }
        }

        return new .Lambda._cls1(dayheadernextmodesupplier, timelineapi, context, viewmodechangelistener);
    }

    static com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier providesDayHeaderNextModeSupplier(Context context, TimelineApi timelineapi, ObservableReference observablereference, ObservableReference observablereference1)
    {
        class .Lambda._cls0
            implements com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier
        {

            private final ObservableReference arg$1;
            private final ObservableReference arg$2;
            private final TimelineApi arg$3;
            private final Context arg$4;

            public final Optional nextViewMode()
            {
                return AlternateTimelineModule.lambda$providesDayHeaderNextModeSupplier$0$AlternateTimelineModule(arg$1, arg$2, arg$3, arg$4);
            }

            .Lambda._cls0(ObservableReference observablereference, ObservableReference observablereference1, TimelineApi timelineapi, Context context)
            {
                arg$1 = observablereference;
                arg$2 = observablereference1;
                arg$3 = timelineapi;
                arg$4 = context;
            }
        }

        return new .Lambda._cls0(observablereference1, observablereference, timelineapi, context);
    }

}
