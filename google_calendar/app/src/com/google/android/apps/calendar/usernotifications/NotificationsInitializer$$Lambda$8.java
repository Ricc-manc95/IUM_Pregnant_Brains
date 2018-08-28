// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            NotificationsInitializer

final class arg._cls1
    implements Runnable
{

    private final Consumer arg$1;

    public final void run()
    {
        NotificationsInitializer.lambda$maybeSubscribeToGrooveStoreHabitChanges$8$NotificationsInitializer(arg$1);
    }

    (Consumer consumer)
    {
        arg$1 = consumer;
    }
}
