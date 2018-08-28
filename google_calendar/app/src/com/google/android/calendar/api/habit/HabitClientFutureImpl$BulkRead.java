// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.common.ApiOperation;
import com.google.android.calendar.api.common.FuturePendingResult;
import com.google.android.gms.common.api.Result;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitClientFutureImpl, HabitDescriptor

final class cute extends FuturePendingResult
{

    public final HabitDescriptor descriptors[];
    public final HabitClientFutureImpl this$0;

    protected final Result calculateResult()
        throws Exception
    {
        class .Lambda._cls0
            implements Callable
        {

            private final HabitClientFutureImpl.BulkRead arg$1;

            public final Object call()
            {
                HabitClientFutureImpl.BulkRead bulkread = arg$1;
                HabitClientFutureImpl habitclientfutureimpl = bulkread.this$0;
                Habit ahabit[] = HabitApiStoreImpl.read(bulkread.descriptors);
                return new HabitClientBase.Result(0, ahabit.length, null, ahabit);
            }

            .Lambda._cls0()
            {
                arg$1 = HabitClientFutureImpl.BulkRead.this;
            }
        }

        return (descriptors)HabitClientFutureImpl.callWithMetrics(new .Lambda._cls0(), ApiOperation.HABIT_BULK_READ);
    }

    protected final Result createFailedResult(Throwable throwable)
    {
        return new .Lambda._cls0(throwable);
    }

    .Lambda._cls0(HabitDescriptor ahabitdescriptor[])
    {
        this$0 = HabitClientFutureImpl.this;
        super();
        descriptors = ahabitdescriptor;
        CalendarExecutor.API.execute(super.future);
    }
}
