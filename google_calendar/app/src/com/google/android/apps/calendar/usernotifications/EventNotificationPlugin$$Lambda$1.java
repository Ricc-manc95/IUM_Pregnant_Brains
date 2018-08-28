// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import android.os.SystemClock;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationState;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.utils.notification.NotificationPrefs;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            EventNotificationPlugin

final class arg._cls3
    implements Consumer
{

    private final EventNotificationPlugin arg$1;
    private final UserNotification arg$2;
    private final UserNotificationState arg$3;

    public final void accept(Object obj)
    {
        EventNotificationPlugin eventnotificationplugin;
        UserNotification usernotification;
        UserNotificationState usernotificationstate;
        icationPresenter icationpresenter;
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = false;
        eventnotificationplugin = arg$1;
        usernotification = arg$2;
        usernotificationstate = arg$3;
        obj = (Event)obj;
        icationpresenter = eventnotificationplugin.eventNotificationPresenter;
        if (usernotificationstate == UserNotificationState.NOT_FIRED) goto _L2; else goto _L1
_L1:
        icationpresenter.show(((Event) (obj)), usernotification, flag1, eventnotificationplugin.notificationPrefs);
        return;
_L2:
        long l = SystemClock.elapsedRealtime();
        if (eventnotificationplugin.lastPlayedSoundMillis == null || l > eventnotificationplugin.lastPlayedSoundMillis.longValue() + EventNotificationPlugin.MIN_SOUND_INTERVAL_MILLIS)
        {
            flag = true;
        }
        flag1 = flag;
        if (flag)
        {
            eventnotificationplugin.lastPlayedSoundMillis = Long.valueOf(l);
            NotificationPrefs notificationprefs = eventnotificationplugin.notificationPrefs;
            notificationprefs.defaultVibrate = null;
            notificationprefs.ringtone = null;
            flag1 = flag;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    nState(EventNotificationPlugin eventnotificationplugin, UserNotification usernotification, UserNotificationState usernotificationstate)
    {
        arg$1 = eventnotificationplugin;
        arg$2 = usernotification;
        arg$3 = usernotificationstate;
    }
}
