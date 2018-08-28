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

final class  extends FuturePendingResult
{

    public final HabitDescriptor descriptor;
    public final HabitClientFutureImpl this$0;

    protected final Result calculateResult()
        throws Exception
    {
        class .Lambda._cls0
            implements Callable
        {

            private final HabitClientFutureImpl.Read arg$1;

            public final Object call()
            {
                int j = 0;
                Object obj1 = arg$1;
                Object obj = ((HabitClientFutureImpl.Read) (obj1)).this$0;
                obj1 = ((HabitClientFutureImpl.Read) (obj1)).descriptor;
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
                arg$1 = HabitClientFutureImpl.Read.this;
            }
        }

        return (t>)HabitClientFutureImpl.callWithMetrics(new .Lambda._cls0(), ApiOperation.HABIT_READ);
    }

    protected final Result createFailedResult(Throwable throwable)
    {
        return new t>(throwable);
    }

    .Lambda._cls0(HabitDescriptor habitdescriptor)
    {
        this$0 = HabitClientFutureImpl.this;
        super();
        descriptor = habitdescriptor;
        CalendarExecutor.API.execute(super.future);
    }
}
