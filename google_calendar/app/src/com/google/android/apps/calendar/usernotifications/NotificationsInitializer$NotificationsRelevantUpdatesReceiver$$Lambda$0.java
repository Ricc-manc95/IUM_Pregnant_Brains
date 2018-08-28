// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import android.content.Context;
import com.google.android.apps.calendar.usernotificationsframework.common.UserNotificationManager;

final class arg._cls2
    implements Runnable
{

    private final Context arg$1;
    private final String arg$2;

    public final void run()
    {
        Context context = arg$1;
        String s = arg$2;
        UserNotificationManager usernotificationmanager = UserNotificationManager.instance;
        if (usernotificationmanager == null)
        {
            throw new NullPointerException(String.valueOf("Call initialize method first."));
        } else
        {
            ((UserNotificationManager)usernotificationmanager).check(context, s);
            return;
        }
    }

    (Context context, String s)
    {
        arg$1 = context;
        arg$2 = s;
    }
}
