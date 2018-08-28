// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.task.TaskData;
import com.google.android.apps.calendar.timebox.task.TaskDataLoader;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.common.collect.Collections2;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            SimpleTaskDataLoader

public class CachedTaskDataLoader
    implements TaskDataLoader
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/task/alternate/CachedTaskDataLoader);
    public final ListenableFutureCache cache;
    public final SimpleTaskDataLoader simpleLoader;
    public final ObservableReference timeZone;

    public CachedTaskDataLoader(ObservableReference observablereference, SimpleTaskDataLoader simpletaskdataloader)
    {
        simpleLoader = simpletaskdataloader;
        timeZone = observablereference;
        class .Lambda._cls0
            implements Supplier
        {

            private final CachedTaskDataLoader arg$1;

            public final Object get()
            {
                Object obj = arg$1;
                Object obj1 = (TimeZone)((CachedTaskDataLoader) (obj)).timeZone.get();
                int i;
                long l;
                if (Clock.mockedTimestamp > 0L)
                {
                    l = Clock.mockedTimestamp;
                } else
                {
                    l = System.currentTimeMillis();
                }
                i = TimeBoxUtil.msToJulianDay(((TimeZone) (obj1)), l);
                obj1 = ((CachedTaskDataLoader) (obj)).simpleLoader;
                obj = ((SimpleTaskDataLoader) (obj1)).settingsCache.getValueAsync();
                if (obj instanceof FluentFuture)
                {
                    obj = (FluentFuture)obj;
                } else
                {
                    obj = new ForwardingFluentFuture(((com.google.common.util.concurrent.ListenableFuture) (obj)));
                }
                return (FluentFuture)AbstractTransformFuture.create(((com.google.common.util.concurrent.ListenableFuture) (obj)), new SimpleTaskDataLoader..Lambda._cls0(((SimpleTaskDataLoader) (obj1)), i - 365, i + 365), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            }

            .Lambda._cls0()
            {
                arg$1 = CachedTaskDataLoader.this;
            }
        }

        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj)
            {
                return CachedTaskDataLoader.lambda$createCache$1$CachedTaskDataLoader$5166KOBMC4NMOOBECSNL4TBEDPGM4R357CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQN8QBC5THMURJ3ELP74PBEEGNL6TB2EDHN4QBGEHKMURHR0();
            }


            private .Lambda._cls1()
            {
            }
        }

        cache = new ListenableFutureCache(TAG, new .Lambda._cls0(), .Lambda._cls1..instance);
    }

    static final void lambda$createCache$0$CachedTaskDataLoader$51D2ILG_0()
    {
    }

    static final Subscription lambda$createCache$1$CachedTaskDataLoader$5166KOBMC4NMOOBECSNL4TBEDPGM4R357CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQN8QBC5THMURJ3ELP74PBEEGNL6TB2EDHN4QBGEHKMURHR0()
    {
        class .Lambda._cls5
            implements Subscription
        {

            public static final Subscription $instance = new .Lambda._cls5();

            public final void cancel(boolean flag)
            {
                CachedTaskDataLoader.lambda$createCache$0$CachedTaskDataLoader$51D2ILG_0();
            }


            private .Lambda._cls5()
            {
            }
        }

        return .Lambda._cls5..instance;
    }

    static final boolean lambda$loadAsync$2$CachedTaskDataLoader(TimeRange timerange, TaskData taskdata)
    {
        return timerange.intersects(taskdata.getTimeRange());
    }

    static final Collection lambda$loadAsync$3$CachedTaskDataLoader(TimeRange timerange, List list)
    {
        class .Lambda._cls4
            implements Predicate
        {

            private final TimeRange arg$1;

            public final boolean apply(Object obj)
            {
                return CachedTaskDataLoader.lambda$loadAsync$2$CachedTaskDataLoader(arg$1, (TaskData)obj);
            }

            .Lambda._cls4(TimeRange timerange)
            {
                arg$1 = timerange;
            }
        }

        return Collections2.filter(list, new .Lambda._cls4(timerange));
    }

    public final FluentFuture loadAsync(int i, int j)
    {
        TimeRange timerange = TimeRange.newAllDayFromJulianDay((TimeZone)timeZone.get(), i, j);
        Object obj = cache.getValueAsync();
        class .Lambda._cls2
            implements Function
        {

            private final TimeRange arg$1;

            public final Object apply(Object obj1)
            {
                return CachedTaskDataLoader.lambda$loadAsync$3$CachedTaskDataLoader(arg$1, (List)obj1);
            }

            .Lambda._cls2(TimeRange timerange)
            {
                arg$1 = timerange;
            }
        }

        class .Lambda._cls3
            implements Function
        {

            public static final Function $instance = new .Lambda._cls3();

            public final Object apply(Object obj1)
            {
                return ImmutableList.copyOf((Collection)obj1);
            }


            private .Lambda._cls3()
            {
            }
        }

        if (obj instanceof FluentFuture)
        {
            obj = (FluentFuture)obj;
        } else
        {
            obj = new ForwardingFluentFuture(((com.google.common.util.concurrent.ListenableFuture) (obj)));
        }
        return (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(((com.google.common.util.concurrent.ListenableFuture) (obj)), new .Lambda._cls2(timerange), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.BACKGROUND)), .Lambda._cls3..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

}
