// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import com.google.android.calendar.api.event.Event;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            EventNotificationPlugin

final class arg._cls1
    implements Function
{

    private final Event arg$1;

    public final Object apply(Object obj)
    {
        return EventNotificationPlugin.lambda$show$3$EventNotificationPlugin(arg$1, (com.google.android.calendar.api.habit.in..Lambda._cls4.arg._cls1)obj);
    }

    (Event event)
    {
        arg$1 = event;
    }
}
