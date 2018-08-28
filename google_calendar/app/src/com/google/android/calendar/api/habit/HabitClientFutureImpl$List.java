// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.common.ApiOperation;
import com.google.android.calendar.api.common.FuturePendingResult;
import com.google.android.gms.common.api.Result;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitClientFutureImpl, HabitFilterOptions

final class  extends FuturePendingResult
{

    public final HabitFilterOptions filter;
    public final HabitClientFutureImpl this$0;

    protected final Result calculateResult()
        throws Exception
    {
        class .Lambda._cls0
            implements Callable
        {

            private final HabitClientFutureImpl.List arg$1;

            public final Object call()
            {
                int j = 0;
                Object obj = arg$1;
                HabitClientFutureImpl habitclientfutureimpl = ((HabitClientFutureImpl.List) (obj)).this$0;
                obj = ((HabitClientFutureImpl.List) (obj)).filter;
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
                arg$1 = HabitClientFutureImpl.List.this;
            }
        }

        return (t>)HabitClientFutureImpl.callWithMetrics(new .Lambda._cls0(), ApiOperation.HABIT_LIST);
    }

    protected final Result createFailedResult(Throwable throwable)
    {
        return new t>(throwable);
    }

    .Lambda._cls0(HabitFilterOptions habitfilteroptions)
    {
        this$0 = HabitClientFutureImpl.this;
        super();
        filter = habitfilteroptions;
        CalendarExecutor.API.execute(super.future);
    }
}
