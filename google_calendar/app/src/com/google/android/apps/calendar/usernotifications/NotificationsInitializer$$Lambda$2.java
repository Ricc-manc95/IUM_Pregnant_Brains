// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import android.content.Context;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            NotificationsInitializer, EventNotificationPlugin

final class arg._cls2
    implements Consumer
{

    private final Context arg$1;
    private final EventNotificationPlugin arg$2;

    public final void accept(Object obj)
    {
        NotificationsInitializer.lambda$initialize$3$NotificationsInitializer(arg$1, arg$2, (String)obj);
    }

    (Context context, EventNotificationPlugin eventnotificationplugin)
    {
        arg$1 = context;
        arg$2 = eventnotificationplugin;
    }
}
