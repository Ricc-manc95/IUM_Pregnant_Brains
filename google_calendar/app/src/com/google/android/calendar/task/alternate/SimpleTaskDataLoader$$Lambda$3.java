// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import android.accounts.Account;
import com.google.android.calendar.task.TaskUtils;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.gms.reminders.model.Task;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            SimpleTaskDataLoader, TaskItemConverter

final class arg._cls3
    implements Function
{

    private final SimpleTaskDataLoader arg$1;
    private final Account arg$2;
    private final int arg$3;

    public final Object apply(Object obj)
    {
        Object obj2 = arg$1;
        Object obj1 = arg$2;
        int i = arg$3;
        obj = (Task)obj;
        Object obj3 = ((SimpleTaskDataLoader) (obj2)).taskItemConverter;
        obj1 = ((Account) (obj1)).name;
        obj2 = ((TaskItemConverter) (obj3)).dateTimeService;
        obj3 = ((TaskItemConverter) (obj3)).dateTimeService;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        obj3 = DateTimeService.toDateTimeImpl((new DateTimeImpl(l, ((DateTimeService) (obj3)).calendarTimeZone)).withTime(0, 0, 0)).time;
        ((Time) (obj3)).writeFieldsToImpl();
        return TaskUtils.createTaskData(((Task) (obj)), ((String) (obj1)), i, ((DateTimeService) (obj2)), ((Time) (obj3)).impl.toMillis(false));
    }

    (SimpleTaskDataLoader simpletaskdataloader, Account account, int i)
    {
        arg$1 = simpletaskdataloader;
        arg$2 = account;
        arg$3 = i;
    }
}
