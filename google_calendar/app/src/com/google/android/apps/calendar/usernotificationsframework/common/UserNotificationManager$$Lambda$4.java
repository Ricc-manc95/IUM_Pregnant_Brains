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

    private final Runnable arg$1;

    public final ListenableFuture call()
    {
        return UserNotificationManager.lambda$logOnFailureAsync$4$UserNotificationManager(arg$1);
    }

    (Runnable runnable)
    {
        arg$1 = runnable;
    }
}
