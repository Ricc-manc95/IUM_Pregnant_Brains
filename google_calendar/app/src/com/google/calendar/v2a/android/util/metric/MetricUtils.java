// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Absent;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeoutException;

// Referenced classes of package com.google.calendar.v2a.android.util.metric:
//            AutoValue_MetricUtils_Result

public class MetricUtils
{
    public static interface MemoryRecording
    {

        public abstract String getName();
    }

    public static interface MetricContext
    {

        public abstract void finish(Result result);

        public abstract void finish(boolean flag);
    }

    public static final class OneStepMeasurements extends Enum
    {

        private static final OneStepMeasurements $VALUES[];
        public static final OneStepMeasurements ACTIVITY_INIT;
        public static final OneStepMeasurements APP_INTERACTIVE;
        public Runnable action;

        public static OneStepMeasurements[] values()
        {
            return (OneStepMeasurements[])$VALUES.clone();
        }

        static 
        {
            ACTIVITY_INIT = new OneStepMeasurements("ACTIVITY_INIT", 0);
            APP_INTERACTIVE = new OneStepMeasurements("APP_INTERACTIVE", 1);
            $VALUES = (new OneStepMeasurements[] {
                ACTIVITY_INIT, APP_INTERACTIVE
            });
        }

        private OneStepMeasurements(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface Operation
    {

        public abstract String getAction();

        public abstract String getCategory();

        public abstract String getFullName();

        public abstract double getSampling();
    }

    public static abstract class Result
    {

        public static final Result CANCEL;
        public static final Result FAILURE;
        public static final Result SUCCESS;

        public static Result cancel(Optional optional, Optional optional1)
        {
            class Status extends Enum
            {

                private static final Status $VALUES[];
                public static final Status CANCEL;
                public static final Status FAILURE;
                public static final Status SUCCESS;

                public static Status[] values()
                {
                    return (Status[])$VALUES.clone();
                }

                static 
                {
                    SUCCESS = new Status("SUCCESS", 0);
                    FAILURE = new Status("FAILURE", 1);
                    CANCEL = new Status("CANCEL", 2);
                    $VALUES = (new Status[] {
                        SUCCESS, FAILURE, CANCEL
                    });
                }

                private Status(String s, int i)
                {
                    super(s, i);
                }
            }

            Status status1 = Status.CANCEL;
            java/lang/Enum.getClass();
            class .Lambda._cls6
                implements Function
            {

                private final Class arg$1;

                public final Object apply(Object obj)
                {
                    return arg$1.cast((Enum)obj);
                }

                .Lambda._cls6(Class class1)
                {
                    arg$1 = class1;
                }
            }

            optional = optional.transform(new .Lambda._cls6(java/lang/Enum));
            java/lang/Enum.getClass();
            class .Lambda._cls7
                implements Function
            {

                private final Class arg$1;

                public final Object apply(Object obj)
                {
                    return arg$1.cast((Enum)obj);
                }

                .Lambda._cls7(Class class1)
                {
                    arg$1 = class1;
                }
            }

            return new AutoValue_MetricUtils_Result(status1, optional, optional1.transform(new .Lambda._cls7(java/lang/Enum)));
        }

        public static Result failure(Optional optional, Optional optional1)
        {
            Status status1 = Status.FAILURE;
            java/lang/Enum.getClass();
            class .Lambda._cls4
                implements Function
            {

                private final Class arg$1;

                public final Object apply(Object obj)
                {
                    return arg$1.cast((Enum)obj);
                }

                .Lambda._cls4(Class class1)
                {
                    arg$1 = class1;
                }
            }

            optional = optional.transform(new .Lambda._cls4(java/lang/Enum));
            java/lang/Enum.getClass();
            class .Lambda._cls5
                implements Function
            {

                private final Class arg$1;

                public final Object apply(Object obj)
                {
                    return arg$1.cast((Enum)obj);
                }

                .Lambda._cls5(Class class1)
                {
                    arg$1 = class1;
                }
            }

            return new AutoValue_MetricUtils_Result(status1, optional, optional1.transform(new .Lambda._cls5(java/lang/Enum)));
        }

        public abstract Optional code();

        public abstract Optional source();

        public abstract Status status();

        static 
        {
            SUCCESS = new AutoValue_MetricUtils_Result(Status.SUCCESS, Absent.INSTANCE, Absent.INSTANCE);
            CANCEL = new AutoValue_MetricUtils_Result(Status.CANCEL, Absent.INSTANCE, Absent.INSTANCE);
            FAILURE = new AutoValue_MetricUtils_Result(Status.FAILURE, Absent.INSTANCE, Absent.INSTANCE);
        }

        public Result()
        {
        }
    }


    public static final List backends = new ArrayList();
    public static final List memoryBackends = new ArrayList();

    public MetricUtils()
    {
    }

    public static boolean isCancellationException(Throwable throwable)
    {
        return (throwable instanceof CancellationException) || (throwable instanceof TimeoutException) || (throwable instanceof InterruptedException);
    }

    static final MetricContext lambda$startMeasurement$4$MetricUtils(Operation operation, Function function)
    {
        return (MetricContext)function.apply(operation);
    }

    static final void lambda$startMeasurement$5$MetricUtils(Result result, MetricContext metriccontext)
    {
        metriccontext.finish(result);
    }

    static final void lambda$startMeasurement$6$MetricUtils(List list, Result result)
    {
        class .Lambda._cls4
            implements Consumer
        {

            private final Result arg$1;

            public final void accept(Object obj)
            {
                MetricUtils.lambda$startMeasurement$5$MetricUtils(arg$1, (MetricContext)obj);
            }

            .Lambda._cls4(Result result)
            {
                arg$1 = result;
            }
        }

        result = new .Lambda._cls4(result);
        if (list != null)
        {
            for (list = list.iterator(); list.hasNext(); result.accept(list.next())) { }
        }
    }

    static final Object lambda$withMetrics$2$MetricUtils(Operation operation, Callable callable, Predicate predicate)
        throws Exception
    {
        MetricContext metriccontext = startMeasurement(operation);
        try
        {
            operation = ((Operation) (callable.call()));
            metriccontext.finish(predicate.apply(operation));
        }
        // Misplaced declaration of an exception variable
        catch (Callable callable)
        {
            boolean flag;
            if ((callable instanceof CancellationException) || (callable instanceof TimeoutException) || (callable instanceof InterruptedException))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                operation = Result.CANCEL;
            } else
            {
                operation = Result.FAILURE;
            }
            metriccontext.finish(operation);
            throw callable;
        }
        return operation;
    }

    static final Object lambda$withMetrics$3$MetricUtils(Operation operation, Supplier supplier, Predicate predicate)
    {
        MetricContext metriccontext = startMeasurement(operation);
        try
        {
            operation = ((Operation) (supplier.get()));
            metriccontext.finish(predicate.apply(operation));
        }
        // Misplaced declaration of an exception variable
        catch (Supplier supplier)
        {
            boolean flag;
            if ((supplier instanceof CancellationException) || (supplier instanceof TimeoutException) || (supplier instanceof InterruptedException))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                operation = Result.CANCEL;
            } else
            {
                operation = Result.FAILURE;
            }
            metriccontext.finish(operation);
            throw supplier;
        }
        return operation;
    }

    public static void recordMemory(MemoryRecording memoryrecording)
    {
        for (Iterator iterator = memoryBackends.iterator(); iterator.hasNext(); ((Consumer)iterator.next()).accept(memoryrecording)) { }
    }

    public static MetricContext startMeasurement(Operation operation)
    {
        List list = backends;
        class .Lambda._cls2
            implements Function
        {

            private final Operation arg$1;

            public final Object apply(Object obj)
            {
                return MetricUtils.lambda$startMeasurement$4$MetricUtils(arg$1, (Function)obj);
            }

            public .Lambda._cls2(Operation operation)
            {
                arg$1 = operation;
            }
        }

        operation = new .Lambda._cls2(operation);
        class .Lambda._cls3
            implements MetricContext
        {

            private final List arg$1;

            public final void finish(Result result)
            {
                MetricUtils.lambda$startMeasurement$6$MetricUtils(arg$1, result);
            }

            public final void finish(boolean flag)
            {
                Result result;
                if (flag)
                {
                    result = Result.SUCCESS;
                } else
                {
                    result = Result.FAILURE;
                }
                finish(result);
            }

            public .Lambda._cls3(List list)
            {
                arg$1 = list;
            }
        }

        if (list instanceof RandomAccess)
        {
            operation = new com.google.common.collect.Lists.TransformingRandomAccessList(list, operation);
        } else
        {
            operation = new com.google.common.collect.Lists.TransformingSequentialList(list, operation);
        }
        return new .Lambda._cls3(ImmutableList.copyOf(operation));
    }

    public static Supplier withMetrics(Predicate predicate, Supplier supplier, Operation operation)
    {
        class .Lambda._cls1
            implements Supplier
        {

            private final Operation arg$1;
            private final Supplier arg$2;
            private final Predicate arg$3;

            public final Object get()
            {
                return MetricUtils.lambda$withMetrics$3$MetricUtils(arg$1, arg$2, arg$3);
            }

            .Lambda._cls1(Operation operation, Supplier supplier, Predicate predicate)
            {
                arg$1 = operation;
                arg$2 = supplier;
                arg$3 = predicate;
            }
        }

        return new .Lambda._cls1(operation, supplier, predicate);
    }

    public static ListenableFuture withMetrics(Function function, ListenableFuture listenablefuture, Operation operation)
    {
        List list = backends;
        operation = new .Lambda._cls2(operation);
        if (list instanceof RandomAccess)
        {
            operation = new com.google.common.collect.Lists.TransformingRandomAccessList(list, operation);
        } else
        {
            operation = new com.google.common.collect.Lists.TransformingSequentialList(list, operation);
        }
        function = new _cls1();
        operation = CalendarExecutor.BACKGROUND;
        if (function == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, function), operation);
            return listenablefuture;
        }
    }

    public static Callable withMetrics(Predicate predicate, Callable callable, Operation operation)
    {
        class .Lambda._cls0
            implements Callable
        {

            private final Operation arg$1;
            private final Callable arg$2;
            private final Predicate arg$3;

            public final Object call()
            {
                return MetricUtils.lambda$withMetrics$2$MetricUtils(arg$1, arg$2, arg$3);
            }

            .Lambda._cls0(Operation operation, Callable callable, Predicate predicate)
            {
                arg$1 = operation;
                arg$2 = callable;
                arg$3 = predicate;
            }
        }

        return new .Lambda._cls0(operation, callable, predicate);
    }

    static 
    {
        LogUtils.getLogTag(com/google/calendar/v2a/android/util/metric/MetricUtils);
    }

    private class _cls1
        implements FutureCallback
    {

        private final MetricContext val$metricContext;
        private final Function val$resultMapping;

        public final void onFailure(Throwable throwable)
        {
            MetricContext metriccontext = metricContext;
            if (MetricUtils.isCancellationException(throwable))
            {
                throwable = Result.CANCEL;
            } else
            {
                throwable = Result.FAILURE;
            }
            metriccontext.finish(throwable);
        }

        public final void onSuccess(Object obj)
        {
            metricContext.finish((Result)resultMapping.apply(obj));
        }

        _cls1()
        {
            metricContext = metriccontext;
            resultMapping = function;
            super();
        }
    }

}
