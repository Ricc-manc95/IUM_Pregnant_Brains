// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.executors.ThrottlingExecutor;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            NotificationsInitializer

final class arg._cls1
    implements Runnable
{

    private final Consumer arg$1;

    public final void run()
    {
        Consumer consumer = arg$1;
        NotificationsInitializer.THROTTLING_EXECUTOR.execute(new <init>(consumer));
    }

    (Consumer consumer)
    {
        arg$1 = consumer;
    }
}
