// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.logging;

import android.content.Context;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.logging:
//            NotificationLog

public final class arg._cls1
    implements Runnable
{

    private final Context arg$1;

    public final void run()
    {
        NotificationLog.lambda$cleanup$2$NotificationLog(arg$1);
    }

    public (Context context)
    {
        arg$1 = context;
    }
}
