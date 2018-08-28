// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.activity.inject;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarStoreInvalidator;
import com.google.android.apps.calendar.timeline.alternate.store.MergedItemProvider;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemProvider;
import com.google.android.apps.calendar.timeline.alternate.view.timebox.TaskItemProvider;
import com.google.android.apps.calendar.timeline.alternate.view.timebox.TimeBoxItemProvider;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Provider;

public class AlternateTimelineActivityImplModule
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/timeline/alternate/activity/inject/AlternateTimelineActivityImplModule);

    public AlternateTimelineActivityImplModule()
    {
    }

    static final FluentFuture lambda$providesItemProviders$0$AlternateTimelineActivityImplModule$514KIAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQ6DHQMARJK8PQN8TBICKTG____0()
    {
        Object obj = Collections.emptyList();
        if (obj == null)
        {
            obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
        }
        if (obj instanceof FluentFuture)
        {
            return (FluentFuture)obj;
        } else
        {
            return new ForwardingFluentFuture(((com.google.common.util.concurrent.ListenableFuture) (obj)));
        }
    }

    static final void lambda$providesItemProviders$1$AlternateTimelineActivityImplModule(LatencyLogger latencylogger, int i)
    {
        latencylogger.markAt(47, i);
    }

    static final FluentFuture lambda$providesItemProviders$2$AlternateTimelineActivityImplModule(AtomicInteger atomicinteger, LatencyLogger latencylogger, ItemProvider itemprovider, int i, int j)
    {
        int k = atomicinteger.incrementAndGet();
        latencylogger.markAt(46, k);
        atomicinteger = itemprovider.loadItems(i, j);
        class .Lambda._cls2
            implements Runnable
        {

            private final LatencyLogger arg$1;
            private final int arg$2;

            public final void run()
            {
                AlternateTimelineActivityImplModule.lambda$providesItemProviders$1$AlternateTimelineActivityImplModule(arg$1, arg$2);
            }

            .Lambda._cls2(LatencyLogger latencylogger, int i)
            {
                arg$1 = latencylogger;
                arg$2 = i;
            }
        }

        atomicinteger.addListener(new .Lambda._cls2(latencylogger, k), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        return atomicinteger;
    }

    static Optional optionalCalendarContentStore(boolean flag, Provider provider)
    {
        if (flag)
        {
            provider = (CalendarContentStore)provider.get();
            if (provider == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Present(provider);
            }
        } else
        {
            return Absent.INSTANCE;
        }
    }

    static Optional optionalMiniMonthController(boolean flag, Provider provider)
    {
        if (flag)
        {
            provider = (MiniMonthController)provider.get();
            if (provider == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Present(provider);
            }
        } else
        {
            return Absent.INSTANCE;
        }
    }

    static Optional providesCalendarStoreInvalidator(boolean flag, Provider provider)
    {
        if (flag)
        {
            provider = (CalendarStoreInvalidator)provider.get();
            if (provider == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Present(provider);
            }
        } else
        {
            return Absent.INSTANCE;
        }
    }

    static ItemProvider providesItemProviders(boolean flag, TimeBoxItemProvider timeboxitemprovider, TaskItemProvider taskitemprovider, LatencyLogger latencylogger)
    {
        if (!flag)
        {
            LogUtils.e(TAG, "alternate timeline not enabled", new Object[0]);
            class .Lambda._cls0
                implements ItemProvider
            {

                public static final ItemProvider $instance = new .Lambda._cls0();

                public final FluentFuture loadItems(int i, int j)
                {
                    return AlternateTimelineActivityImplModule.lambda$providesItemProviders$0$AlternateTimelineActivityImplModule$514KIAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQ6DHQMARJK8PQN8TBICKTG____0();
                }


            private .Lambda._cls0()
            {
            }
            }

            return .Lambda._cls0..instance;
        } else
        {
            timeboxitemprovider = new MergedItemProvider(ImmutableList.of(timeboxitemprovider, taskitemprovider));
            class .Lambda._cls1
                implements ItemProvider
            {

                private final AtomicInteger arg$1;
                private final LatencyLogger arg$2;
                private final ItemProvider arg$3;

                public final FluentFuture loadItems(int i, int j)
                {
                    return AlternateTimelineActivityImplModule.lambda$providesItemProviders$2$AlternateTimelineActivityImplModule(arg$1, arg$2, arg$3, i, j);
                }

            .Lambda._cls1(AtomicInteger atomicinteger, LatencyLogger latencylogger, ItemProvider itemprovider)
            {
                arg$1 = atomicinteger;
                arg$2 = latencylogger;
                arg$3 = itemprovider;
            }
            }

            return new .Lambda._cls1(new AtomicInteger(), latencylogger, timeboxitemprovider);
        }
    }

    static com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor providesStoreExecutor(boolean flag)
    {
        if (!flag)
        {
            LogUtils.e(TAG, "alternate timeline not enabled", new Object[0]);
        }
        return CalendarExecutors.serialExecutor(CalendarExecutor.BACKGROUND);
    }

}
