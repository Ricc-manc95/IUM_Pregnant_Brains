// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.common.ApiOperation;
import com.google.android.calendar.api.common.FuturePendingResult;
import com.google.android.gms.common.api.Result;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitFilterOptions, HabitClientFutureImpl

final class execute extends FuturePendingResult
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

            private final HabitClientFutureImpl.Count arg$1;

            public final Object call()
            {
                Object obj = arg$1;
                HabitClientFutureImpl habitclientfutureimpl = ((HabitClientFutureImpl.Count) (obj)).this$0;
                obj = ((HabitClientFutureImpl.Count) (obj)).filter;
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
                arg$1 = HabitClientFutureImpl.Count.this;
            }
        }

        .Lambda._cls0 _lcls0 = new .Lambda._cls0();
        return (.Lambda._cls0)MetricUtils.withMetrics(com.google.common.base.LWAYS_TRUE, _lcls0, apioperation).call();
    }

    protected final Result createFailedResult(Throwable throwable)
    {
        return new >(throwable);
    }

    .Lambda._cls0(HabitFilterOptions habitfilteroptions)
    {
        this$0 = HabitClientFutureImpl.this;
        super();
        filter = habitfilteroptions;
        CalendarExecutor.API.execute(super.future);
    }
}
