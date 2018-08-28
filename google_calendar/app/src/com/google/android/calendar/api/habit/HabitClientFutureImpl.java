// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import com.google.android.calendar.api.common.ApiOperation;
import com.google.android.gms.common.api.PendingResult;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import java.io.IOException;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitClientBase, HabitApiStoreImpl, HabitFilterOptions, HabitModifications, 
//            HabitDescriptor

public final class HabitClientFutureImpl extends HabitClientBase
{

    public HabitClientFutureImpl(HabitApiStoreImpl habitapistoreimpl)
    {
        super(habitapistoreimpl);
    }

    protected static Object callWithMetrics(Callable callable, ApiOperation apioperation)
        throws IOException
    {
        callable = ((Callable) (MetricUtils.withMetrics(com.google.common.base.Predicates.ObjectPredicate.ALWAYS_TRUE, callable, apioperation).call()));
        return callable;
        callable;
_L2:
        throw callable;
        callable;
        throw new IOException(callable);
        callable;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final PendingResult count(HabitFilterOptions habitfilteroptions)
    {
        return new Count(habitfilteroptions);
    }

    public final PendingResult create(HabitModifications habitmodifications)
    {
        return new Create(habitmodifications);
    }

    public final PendingResult list(HabitFilterOptions habitfilteroptions)
    {
        return new List(habitfilteroptions);
    }

    public final PendingResult read(HabitDescriptor habitdescriptor)
    {
        return new Read(habitdescriptor);
    }

    public final PendingResult read(HabitDescriptor ahabitdescriptor[])
    {
        return new BulkRead(ahabitdescriptor);
    }

    public final PendingResult update(HabitModifications habitmodifications)
    {
        return new Update(habitmodifications);
    }

    private class Count extends FuturePendingResult
    {

        public final HabitFilterOptions filter;
        public final HabitClientFutureImpl this$0;

        protected final Result calculateResult()
            throws Exception
        {
            ApiOperation apioperation = ApiOperation.HABIT_COUNT;
            class .Lambda._cls0
                implements Callable
            {

                private final Count arg$1;

                public final Object call()
                {
                    Object obj = arg$1;
                    HabitClientFutureImpl habitclientfutureimpl = ((Count) (obj))._fld0;
                    obj = ((Count) (obj)).filter;
                    int j = ((HabitClientBase) (habitclientfutureimpl)).api.count(((HabitFilterOptions) (obj)));
                    int i;
                    if (j >= 0)
                    {
                        i = 0;
                    } else
                    {
                        i = 13;
                    }
                    return new HabitClientBase.Result(i, j, null, null);
                }

                .Lambda._cls0()
                {
                    arg$1 = Count.this;
                }
            }

            .Lambda._cls0 _lcls0 = new .Lambda._cls0();
            return (HabitClient.GenericResult)MetricUtils.withMetrics(com.google.common.base.Predicates.ObjectPredicate.ALWAYS_TRUE, _lcls0, apioperation).call();
        }

        protected final Result createFailedResult(Throwable throwable)
        {
            return new HabitClientBase.Result(throwable);
        }

        Count(HabitFilterOptions habitfilteroptions)
        {
            this$0 = HabitClientFutureImpl.this;
            super();
            filter = habitfilteroptions;
            CalendarExecutor.API.execute(super.future);
        }
    }


    private class Create extends FuturePendingResult
    {

        public final HabitModifications habit;
        public final HabitClientFutureImpl this$0;

        protected final Result calculateResult()
            throws Exception
        {
            ApiOperation apioperation = ApiOperation.HABIT_CREATE;
            class .Lambda._cls0
                implements Callable
            {

                private final Create arg$1;

                public final Object call()
                {
                    int j = 0;
                    Object obj1 = arg$1;
                    Object obj = ((Create) (obj1))._fld0;
                    obj1 = ((Create) (obj1)).habit;
                    obj = ((HabitClientBase) (obj)).api.create(((HabitModifications) (obj1)));
                    int i;
                    if (obj != null)
                    {
                        i = 0;
                    } else
                    {
                        i = 13;
                    }
                    if (obj != null)
                    {
                        j = 1;
                    }
                    return new HabitClientBase.Result(i, j, ((Habit) (obj)), null);
                }

                .Lambda._cls0()
                {
                    arg$1 = Create.this;
                }
            }

            .Lambda._cls0 _lcls0 = new .Lambda._cls0();
            return (HabitClient.ReadResult)MetricUtils.withMetrics(com.google.common.base.Predicates.ObjectPredicate.ALWAYS_TRUE, _lcls0, apioperation).call();
        }

        protected final Result createFailedResult(Throwable throwable)
        {
            return new HabitClientBase.Result(throwable);
        }

        Create(HabitModifications habitmodifications)
        {
            this$0 = HabitClientFutureImpl.this;
            super();
            habit = habitmodifications;
            CalendarExecutor.API.execute(super.future);
        }
    }


    private class List extends FuturePendingResult
    {

        public final HabitFilterOptions filter;
        public final HabitClientFutureImpl this$0;

        protected final Result calculateResult()
            throws Exception
        {
            class .Lambda._cls0
                implements Callable
            {

                private final List arg$1;

                public final Object call()
                {
                    int j = 0;
                    Object obj = arg$1;
                    HabitClientFutureImpl habitclientfutureimpl = ((List) (obj))._fld0;
                    obj = ((List) (obj)).filter;
                    Habit ahabit[] = ((HabitClientBase) (habitclientfutureimpl)).api.list(((HabitFilterOptions) (obj)));
                    boolean flag;
                    int i;
                    if (ahabit != null)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        i = 0;
                    } else
                    {
                        i = 13;
                    }
                    if (flag)
                    {
                        j = ahabit.length;
                    }
                    return new HabitClientBase.Result(i, j, null, ahabit);
                }

                .Lambda._cls0()
                {
                    arg$1 = List.this;
                }
            }

            return (HabitClient.ListResult)HabitClientFutureImpl.callWithMetrics(new .Lambda._cls0(), ApiOperation.HABIT_LIST);
        }

        protected final Result createFailedResult(Throwable throwable)
        {
            return new HabitClientBase.Result(throwable);
        }

        List(HabitFilterOptions habitfilteroptions)
        {
            this$0 = HabitClientFutureImpl.this;
            super();
            filter = habitfilteroptions;
            CalendarExecutor.API.execute(super.future);
        }
    }


    private class Read extends FuturePendingResult
    {

        public final HabitDescriptor descriptor;
        public final HabitClientFutureImpl this$0;

        protected final Result calculateResult()
            throws Exception
        {
            class .Lambda._cls0
                implements Callable
            {

                private final Read arg$1;

                public final Object call()
                {
                    int j = 0;
                    Object obj1 = arg$1;
                    Object obj = ((Read) (obj1))._fld0;
                    obj1 = ((Read) (obj1)).descriptor;
                    obj = ((HabitClientBase) (obj)).api.read(((HabitDescriptor) (obj1)));
                    int i;
                    if (obj != null)
                    {
                        i = 0;
                    } else
                    {
                        i = 13;
                    }
                    if (obj != null)
                    {
                        j = 1;
                    }
                    return new HabitClientBase.Result(i, j, ((Habit) (obj)), null);
                }

                .Lambda._cls0()
                {
                    arg$1 = Read.this;
                }
            }

            return (HabitClient.ReadResult)HabitClientFutureImpl.callWithMetrics(new .Lambda._cls0(), ApiOperation.HABIT_READ);
        }

        protected final Result createFailedResult(Throwable throwable)
        {
            return new HabitClientBase.Result(throwable);
        }

        Read(HabitDescriptor habitdescriptor)
        {
            this$0 = HabitClientFutureImpl.this;
            super();
            descriptor = habitdescriptor;
            CalendarExecutor.API.execute(super.future);
        }
    }


    private class BulkRead extends FuturePendingResult
    {

        public final HabitDescriptor descriptors[];
        public final HabitClientFutureImpl this$0;

        protected final Result calculateResult()
            throws Exception
        {
            class .Lambda._cls0
                implements Callable
            {

                private final BulkRead arg$1;

                public final Object call()
                {
                    BulkRead bulkread = arg$1;
                    HabitClientFutureImpl habitclientfutureimpl = bulkread._fld0;
                    Habit ahabit[] = HabitApiStoreImpl.read(bulkread.descriptors);
                    return new HabitClientBase.Result(0, ahabit.length, null, ahabit);
                }

                .Lambda._cls0()
                {
                    arg$1 = BulkRead.this;
                }
            }

            return (HabitClient.BulkReadResult)HabitClientFutureImpl.callWithMetrics(new .Lambda._cls0(), ApiOperation.HABIT_BULK_READ);
        }

        protected final Result createFailedResult(Throwable throwable)
        {
            return new HabitClientBase.Result(throwable);
        }

        BulkRead(HabitDescriptor ahabitdescriptor[])
        {
            this$0 = HabitClientFutureImpl.this;
            super();
            descriptors = ahabitdescriptor;
            CalendarExecutor.API.execute(super.future);
        }
    }


    private class Update extends FuturePendingResult
    {

        public final HabitModifications habit;
        public final HabitClientFutureImpl this$0;

        protected final Result calculateResult()
            throws Exception
        {
            class .Lambda._cls0
                implements Callable
            {

                private final Update arg$1;

                public final Object call()
                {
                    boolean flag = true;
                    Object obj = arg$1;
                    HabitClientFutureImpl habitclientfutureimpl = ((Update) (obj))._fld0;
                    obj = ((Update) (obj)).habit;
                    if (((HabitModifications) (obj)).getOriginal() == null)
                    {
                        return new HabitClientBase.Result(13, 0, null, null);
                    }
                    int i;
                    if (((HabitClientBase) (habitclientfutureimpl)).api.update(((HabitModifications) (obj)), null) != null)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        i = ((flag) ? 1 : 0);
                    } else
                    {
                        i = 0;
                    }
                    return new HabitClientBase.Result(0, i, null, null);
                }

                .Lambda._cls0()
                {
                    arg$1 = Update.this;
                }
            }

            return (HabitClient.GenericResult)HabitClientFutureImpl.callWithMetrics(new .Lambda._cls0(), ApiOperation.HABIT_UPDATE);
        }

        protected final Result createFailedResult(Throwable throwable)
        {
            return new HabitClientBase.Result(throwable);
        }

        Update(HabitModifications habitmodifications)
        {
            this$0 = HabitClientFutureImpl.this;
            super();
            habit = habitmodifications;
            CalendarExecutor.API.execute(super.future);
        }
    }

}
