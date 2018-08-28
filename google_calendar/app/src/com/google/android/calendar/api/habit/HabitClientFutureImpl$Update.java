// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.common.ApiOperation;
import com.google.android.calendar.api.common.FuturePendingResult;
import com.google.android.gms.common.api.Result;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitClientFutureImpl, HabitModifications

final class xecute extends FuturePendingResult
{

    public final HabitModifications habit;
    public final HabitClientFutureImpl this$0;

    protected final Result calculateResult()
        throws Exception
    {
        class .Lambda._cls0
            implements Callable
        {

            private final HabitClientFutureImpl.Update arg$1;

            public final Object call()
            {
                boolean flag = true;
                Object obj = arg$1;
                HabitClientFutureImpl habitclientfutureimpl = ((HabitClientFutureImpl.Update) (obj)).this$0;
                obj = ((HabitClientFutureImpl.Update) (obj)).habit;
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
                arg$1 = HabitClientFutureImpl.Update.this;
            }
        }

        return ()HabitClientFutureImpl.callWithMetrics(new .Lambda._cls0(), ApiOperation.HABIT_UPDATE);
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
