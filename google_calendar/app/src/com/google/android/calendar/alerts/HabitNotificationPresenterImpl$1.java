// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.habit.Habit;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitNotificationPresenterImpl, HabitsAlerts

final class val.notificationId
    implements FutureCallback
{

    private final HabitNotificationPresenterImpl this$0;
    private final Habit val$habit;
    private final Event val$instance;
    private final UserNotification val$notification;
    private final int val$notificationId;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.e(HabitNotificationPresenterImpl.TAG, throwable, "Failed to perform fit activity check.", new Object[0]);
    }

    public final void onSuccess(Object obj)
    {
        if (((Boolean)obj).booleanValue())
        {
            HabitsAlerts.showFollowupNotification(context, val$habit, val$instance.getDescriptor().getKey(), val$notification, val$notificationId);
        }
    }

    s.UserNotification()
    {
        this$0 = final_habitnotificationpresenterimpl;
        val$habit = habit1;
        val$instance = event;
        val$notification = usernotification;
        val$notificationId = I.this;
        super();
    }
}
