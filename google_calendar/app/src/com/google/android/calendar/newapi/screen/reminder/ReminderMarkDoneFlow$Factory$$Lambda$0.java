// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.gms.reminders.model.Task;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderMarkDoneFlow

final class arg._cls2
    implements Consumer
{

    private final String arg$1;
    private final Task arg$2;

    public final void accept(Object obj)
    {
        Object obj1 = arg$1;
        Object obj2 = arg$2;
        obj = (ReminderMarkDoneFlow)obj;
        boolean flag = Boolean.TRUE.equals(((Task) (obj2)).getArchived());
        obj1 = (FluentFuture)CalendarExecutor.BACKGROUND.submit(new arg._cls2(((ReminderMarkDoneFlow) (obj)), ((String) (obj1)), ((Task) (obj2))));
        obj = new arg._cls2(((ReminderMarkDoneFlow) (obj)), flag);
        obj2 = CalendarExecutor.MAIN;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj1)).addListener(new com.google.common.util.concurrent.(((java.util.concurrent.Future) (obj1)), ((com.google.common.util.concurrent.FutureCallback) (obj))), ((java.util.concurrent.Executor) (obj2)));
            return;
        }
    }

    (String s, Task task)
    {
        arg$1 = s;
        arg$2 = task;
    }
}
