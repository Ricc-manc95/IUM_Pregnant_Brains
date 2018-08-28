// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.common;

import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationState;
import com.google.android.apps.calendar.usernotificationsframework.storage.UserNotificationStore;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.common:
//            UserNotificationProcessor

final class arg._cls4
    implements AsyncCallable
{

    private final UserNotificationProcessor arg$1;
    private final UserNotification arg$2;
    private final UserNotificationState arg$3;
    private final Optional arg$4;

    public final ListenableFuture call()
    {
        UserNotificationProcessor usernotificationprocessor = arg$1;
        UserNotification usernotification = arg$2;
        UserNotificationState usernotificationstate = arg$3;
        Optional optional = arg$4;
        usernotificationprocessor.updateNotificationState(usernotification, usernotificationprocessor.userNotificationStore.getNotificationState(usernotification), usernotificationstate, optional, true);
        if (true)
        {
            return com.google.common.util.concurrent.uture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.uture(null);
        }
    }

    (UserNotificationProcessor usernotificationprocessor, UserNotification usernotification, UserNotificationState usernotificationstate, Optional optional)
    {
        arg$1 = usernotificationprocessor;
        arg$2 = usernotification;
        arg$3 = usernotificationstate;
        arg$4 = optional;
    }
}
