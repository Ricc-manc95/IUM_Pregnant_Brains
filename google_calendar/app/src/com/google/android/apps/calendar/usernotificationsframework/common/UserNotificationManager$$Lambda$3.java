// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.common;

import android.content.Context;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationCheckOrigin;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.common:
//            UserNotificationManager

final class arg._cls5
    implements AsyncFunction
{

    private final UserNotificationManager arg$1;
    private final Context arg$2;
    private final Integer arg$3;
    private final UserNotificationCheckOrigin arg$4;
    private final String arg$5;

    public final ListenableFuture apply(Object obj)
    {
        return arg$1.checkInternal(arg$2, arg$3, arg$4, arg$5);
    }

    (UserNotificationManager usernotificationmanager, Context context, Integer integer, UserNotificationCheckOrigin usernotificationcheckorigin, String s)
    {
        arg$1 = usernotificationmanager;
        arg$2 = context;
        arg$3 = integer;
        arg$4 = usernotificationcheckorigin;
        arg$5 = s;
    }
}
