// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import android.content.Context;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            NotificationsInitializer

final class arg._cls1
    implements Runnable
{

    private final Context arg$1;

    public final void run()
    {
        NotificationsInitializer.lambda$initialize$2$NotificationsInitializer(arg$1);
    }

    (Context context)
    {
        arg$1 = context;
    }
}