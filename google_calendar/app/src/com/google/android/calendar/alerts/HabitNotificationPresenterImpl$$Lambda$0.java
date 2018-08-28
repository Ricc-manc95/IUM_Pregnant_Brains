// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.belong.FitOperationServiceHelper;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitNotificationPresenterImpl

final class arg._cls2
    implements Callable
{

    private final HabitNotificationPresenterImpl arg$1;
    private final Event arg$2;

    public final Object call()
    {
        boolean flag = true;
        HabitNotificationPresenterImpl habitnotificationpresenterimpl = arg$1;
        Event event = arg$2;
        if (habitnotificationpresenterimpl.fitOperationServiceHelper.performActivityCheck(1, event.getDescriptor().getKey(), false))
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
    }

    Q(HabitNotificationPresenterImpl habitnotificationpresenterimpl, Event event)
    {
        arg$1 = habitnotificationpresenterimpl;
        arg$2 = event;
    }
}
