// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.belong.FitOperationServiceHelper;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsAlerts

public class HabitNotificationPresenterImpl
    implements com.google.android.apps.calendar.usernotifications.EventNotificationPlugin.HabitNotificationPresenter
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alerts/HabitNotificationPresenterImpl);
    public final Context context;
    public final FitOperationServiceHelper fitOperationServiceHelper;

    public HabitNotificationPresenterImpl(Context context1)
    {
        context = context1;
        fitOperationServiceHelper = new FitOperationServiceHelper(context1);
    }

    public final void hide(UserNotification usernotification)
    {
        HabitsAlerts.cancel(context, usernotification.getEntityFingerprint().hashCode());
    }

    public final void show(final Habit habit, final Event instance, final UserNotification notification)
    {
        boolean flag = false;
        Object obj = com.google.android.calendar.api.event.NotificationInfo.NotificationType.fromInteger(notification.getType());
        final int notificationId = notification.getEntityFingerprint().hashCode();
        switch (((com.google.android.calendar.api.event.NotificationInfo.NotificationType) (obj)).ordinal())
        {
        default:
            LogUtils.wtf(TAG, "Unsupported notification type.", new Object[0]);
            return;

        case 2: // '\002'
            HabitsAlerts.showPreinstanceNotification(context, habit, instance.getDescriptor().getKey(), instance.getStartMillis(), instance.getEndMillis(), notification, notificationId);
            return;

        case 3: // '\003'
            HabitsAlerts.showRecommitNotification(context, habit, instance.getDescriptor().getKey(), instance.getStartMillis(), instance.getEndMillis(), notification, notificationId);
            return;

        case 4: // '\004'
        case 6: // '\006'
            class .Lambda._cls0
                implements Callable
            {

                private final HabitNotificationPresenterImpl arg$1;
                private final Event arg$2;

                public final Object call()
                {
                    boolean flag1 = true;
                    HabitNotificationPresenterImpl habitnotificationpresenterimpl = arg$1;
                    Event event = arg$2;
                    if (habitnotificationpresenterimpl.fitOperationServiceHelper.performActivityCheck(1, event.getDescriptor().getKey(), false))
                    {
                        flag1 = false;
                    }
                    return Boolean.valueOf(flag1);
                }

            .Lambda._cls0(Event event)
            {
                arg$1 = HabitNotificationPresenterImpl.this;
                arg$2 = event;
            }
            }

            if (habit.isFitIntegrationEnabled())
            {
                obj = (FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls0(instance));
            } else
            {
                if (obj == com.google.android.calendar.api.event.NotificationInfo.NotificationType.HABIT_FOLLOWUP)
                {
                    flag = true;
                }
                obj = Boolean.valueOf(flag);
                if (obj == null)
                {
                    obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                } else
                {
                    obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
                }
            }
            habit = new _cls1();
            instance = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
            if (habit == null)
            {
                throw new NullPointerException();
            } else
            {
                ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), habit), instance);
                return;
            }

        case 5: // '\005'
            HabitsAlerts.showBelongNotification(context, habit, instance.getDescriptor().getKey(), notification, notificationId);
            return;
        }
    }


    private class _cls1
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
                HabitsAlerts.showFollowupNotification(context, habit, instance.getDescriptor().getKey(), notification, notificationId);
            }
        }

        _cls1()
        {
            this$0 = HabitNotificationPresenterImpl.this;
            habit = habit1;
            instance = event;
            notification = usernotification;
            notificationId = i;
            super();
        }
    }

}
