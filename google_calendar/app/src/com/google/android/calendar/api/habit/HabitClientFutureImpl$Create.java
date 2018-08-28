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
//            HabitModifications, HabitClientFutureImpl

final class xecute extends FuturePendingResult
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

            private final HabitClientFutureImpl.Create arg$1;

            public final Object call()
            {
                int j = 0;
                Object obj1 = arg$1;
                Object obj = ((HabitClientFutureImpl.Create) (obj1)).this$0;
                obj1 = ((HabitClientFutureImpl.Create) (obj1)).habit;
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
                arg$1 = HabitClientFutureImpl.Create.this;
            }
        }

        .Lambda._cls0 _lcls0 = new .Lambda._cls0();
        return (.Lambda._cls0)MetricUtils.withMetrics(com.google.common.base.WAYS_TRUE, _lcls0, apioperation).call();
    }

    protected final Result createFailedResult(Throwable throwable)
    {
        return new (throwable);
    }

    .Lambda._cls0(HabitModifications habitmodifications)
    {
        this$0 = HabitClientFutureImpl.this;
        super();
        habit = habitmodifications;
        CalendarExecutor.API.execute(super.future);
    }
}
