// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.habit.Habit;
import com.google.common.base.Pair;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            EventNotificationPlugin

final class arg._cls2
    implements Consumer
{

    private final EventNotificationPlugin arg$1;
    private final UserNotification arg$2;

    public final void accept(Object obj)
    {
        EventNotificationPlugin eventnotificationplugin = arg$1;
        UserNotification usernotification = arg$2;
        obj = (Pair)obj;
        eventnotificationplugin.habitNotificationPresenter.show((Habit)((Pair) (obj)).second, (Event)((Pair) (obj)).first, usernotification);
    }

    n(EventNotificationPlugin eventnotificationplugin, UserNotification usernotification)
    {
        arg$1 = eventnotificationplugin;
        arg$2 = usernotification;
    }
}
