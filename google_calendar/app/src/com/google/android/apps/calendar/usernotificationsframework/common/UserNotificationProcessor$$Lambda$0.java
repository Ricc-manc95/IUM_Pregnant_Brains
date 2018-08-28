// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.common;

import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationPlugin;
import com.google.android.apps.calendar.usernotificationsframework.storage.UserNotificationStore;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.common:
//            UserNotificationProcessor

final class arg._cls1
    implements AsyncCallable
{

    private final UserNotificationProcessor arg$1;

    public final ListenableFuture call()
    {
        Object obj = arg$1;
        obj = ((UserNotificationProcessor) (obj)).userNotificationStore.getNotifications(((UserNotificationProcessor) (obj)).userNotificationPlugin.getId());
        if (obj == null)
        {
            return com.google.common.util.concurrent.uture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.uture(obj);
        }
    }

    (UserNotificationProcessor usernotificationprocessor)
    {
        arg$1 = usernotificationprocessor;
    }
}
