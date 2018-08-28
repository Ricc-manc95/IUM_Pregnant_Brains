// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventUtil;
import com.google.android.calendar.api.event.GooglePrivateData;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.android.calendar.utils.notification.NotificationPrefs;

// Referenced classes of package com.google.android.calendar.alerts:
//            EventNotificationInfo, NotificationManagerWrapperImpl, AlertServiceHelper

public final class EventNotificationPresenterImpl
    implements com.google.android.apps.calendar.usernotifications.EventNotificationPlugin.EventNotificationPresenter
{

    private final Context context;

    public EventNotificationPresenterImpl(Context context1)
    {
        context = context1;
    }

    public final void hide(UserNotification usernotification)
    {
        usernotification = EventNotificationInfo.getNotificationTag(usernotification);
        AlertServiceHelper.hideNotification(NotificationManagerWrapperImpl.getInstance(context), usernotification.hashCode(), usernotification);
    }

    public final void show(Event event, UserNotification usernotification, boolean flag, NotificationPrefs notificationprefs)
    {
        Object obj = event.getDescriptor().getKey();
        Object obj1 = event.getSummary();
        String s = EventUtil.getFirstLocationName(event);
        long l = event.getStartMillis();
        boolean flag3 = event.isEndTimeUnspecified();
        long l1 = event.getEndMillis();
        boolean flag4 = event.isAllDayEvent();
        boolean flag1;
        int i;
        boolean flag2;
        if (!event.hasLocalChanges() && event.getGooglePrivateData() != null && event.getGooglePrivateData().hasEveryoneDeclined() && !event.getGooglePrivateData().isEveryoneDeclinedDismissed())
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        usernotification = new EventNotificationInfo(((com.google.android.calendar.api.event.EventKey) (obj)), ((String) (obj1)), s, l, flag3, l1, flag4, flag2, usernotification);
        obj = context;
        obj1 = NotificationManagerWrapperImpl.getInstance(context);
        i = ((EventNotificationInfo) (usernotification)).tag.hashCode();
        if (!flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            event = null;
        } else
        {
            if (notificationprefs.ringtone == null)
            {
                notificationprefs.ringtone = PreferencesUtils.getRingtonePreference(notificationprefs.context);
            }
            event = notificationprefs.ringtone;
        }
        AlertServiceHelper.showNotification(((Context) (obj)), ((NotificationManagerWrapper) (obj1)), usernotification, i, event, notificationprefs.getDefaultVibrate(), true, true);
    }
}
