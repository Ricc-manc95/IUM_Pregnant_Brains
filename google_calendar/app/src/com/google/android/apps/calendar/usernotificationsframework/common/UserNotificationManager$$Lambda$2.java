// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.common;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.common:
//            UserNotificationManager

final class arg._cls1
    implements AsyncCallable
{

    private final UserNotificationManager arg$1;

    public final ListenableFuture call()
    {
        return arg$1.lambda$markShownNotificationsAsFired$2$UserNotificationManager();
    }

    (UserNotificationManager usernotificationmanager)
    {
        arg$1 = usernotificationmanager;
    }
}
