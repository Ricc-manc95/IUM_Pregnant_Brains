// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            EventDeleteFlow

final class arg._cls1
    implements Consumer
{

    private final EventDeleteFlow arg$1;

    public final void accept(Object obj)
    {
        Object obj1 = arg$1;
        obj = (com.google.android.calendar.api.event.ification)obj;
        obj1.deletePendingResult = ((EventDeleteFlow) (obj1)).eventClient.delete(((EventDeleteFlow) (obj1)).event.getDescriptor(), ((EventDeleteFlow) (obj1)).scope, ((com.google.android.calendar.api.event.ification) (obj)));
        obj = ((EventDeleteFlow) (obj1)).deletePendingResult;
        obj1 = new ingResult(((EventDeleteFlow) (obj1)));
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.init>(((java.util.concurrent.Future) (obj)), ((com.google.common.util.concurrent.FutureCallback) (obj1))), calendarexecutor);
            return;
        }
    }

    (EventDeleteFlow eventdeleteflow)
    {
        arg$1 = eventdeleteflow;
    }
}
